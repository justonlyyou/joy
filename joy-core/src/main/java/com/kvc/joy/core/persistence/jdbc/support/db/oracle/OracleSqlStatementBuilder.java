package com.kvc.joy.core.persistence.jdbc.support.db.oracle;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.support.db.Delimiter;
import com.kvc.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

/**
 * SqlStatementBuilder supporting Oracle-specific PL/SQL constructs.
 */
public class OracleSqlStatementBuilder extends SqlStatementBuilder {
    /**
     * Delimiter of PL/SQL blocks and statements.
     */
    private static final Delimiter PLSQL_DELIMITER = new Delimiter("/", true);

    /**
     * Holds the beginning of the statement.
     */
    private String statementStart = "";

    @Override
    protected Delimiter changeDelimiterIfNecessary(String line, Delimiter delimiter) {
        if (line.matches("DECLARE|DECLARE\\s.*") || line.matches("BEGIN|BEGIN\\s.*")) {
            return PLSQL_DELIMITER;
        }

        if (StringTool.countMatches(statementStart, " ") < 4) {
            statementStart += line;
            statementStart += " ";
        }

        if (statementStart.startsWith("CREATE FUNCTION")
                || statementStart.startsWith("CREATE PROCEDURE")
                || statementStart.startsWith("CREATE PACKAGE")
                || statementStart.startsWith("CREATE TYPE")
                || statementStart.startsWith("CREATE TRIGGER")
                || statementStart.startsWith("CREATE OR REPLACE FUNCTION")
                || statementStart.startsWith("CREATE OR REPLACE PROCEDURE")
                || statementStart.startsWith("CREATE OR REPLACE PACKAGE")
                || statementStart.startsWith("CREATE OR REPLACE TYPE")
                || statementStart.startsWith("CREATE OR REPLACE TRIGGER")){
            return PLSQL_DELIMITER;
        }

        return delimiter;
    }

    @Override
    protected String simplifyLine(String line) {
        String simplifiedQQuotes = StringTool.replace(StringTool.replace(line, "q'(", "q'["), ")'", "]'");
        return super.simplifyLine(simplifiedQQuotes);
    }

    @Override
    protected String extractAlternateOpenQuote(String token) {
        if (token.startsWith("Q'") && (token.length() >= 3)) {
            return token.substring(0, 3);
        }
        return null;
    }

    @Override
    protected String computeAlternateCloseQuote(String openQuote) {
        char specialChar = openQuote.charAt(2);
        switch (specialChar) {
            case '[':
                return "]'";
            case '(':
                return ")'";
            case '{':
                return "}'";
            case '<':
                return ">'";
            default:
                return specialChar + "'";
        }
    }
}
