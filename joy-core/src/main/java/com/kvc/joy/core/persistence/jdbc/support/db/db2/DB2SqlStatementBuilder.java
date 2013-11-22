package com.kvc.joy.core.persistence.jdbc.support.db.db2;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.support.db.Delimiter;
import com.kvc.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

/**
 * SqlStatementBuilder supporting DB2-specific delimiter changes.
 */
public class DB2SqlStatementBuilder extends SqlStatementBuilder {
    /**
     * Are we currently inside a BEGIN END; block?
     */
    private boolean insideBeginEndBlock;

    /**
     * Holds the beginning of the statement.
     */
    private String statementStart = "";

    @Override
    protected Delimiter changeDelimiterIfNecessary(String line, Delimiter delimiter) {
        if (StringTool.countMatches(statementStart, " ") < 4) {
            statementStart += line;
            statementStart += " ";
        }

        if (statementStart.startsWith("CREATE FUNCTION")
                || statementStart.startsWith("CREATE PROCEDURE")
                || statementStart.startsWith("CREATE TRIGGER")
                || statementStart.startsWith("CREATE OR REPLACE FUNCTION")
                || statementStart.startsWith("CREATE OR REPLACE PROCEDURE")
                || statementStart.startsWith("CREATE OR REPLACE TRIGGER")) {
            if (line.startsWith("BEGIN")) {
                insideBeginEndBlock = true;
            }

            if (line.endsWith("END;")) {
                insideBeginEndBlock = false;
            }
        }

        if (insideBeginEndBlock) {
            return null;
        }
        return getDefaultDelimiter();
    }
}
