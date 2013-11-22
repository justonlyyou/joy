package com.kvc.joy.core.persistence.jdbc.support.db.hsql;

import com.kvc.joy.core.persistence.jdbc.support.db.Delimiter;
import com.kvc.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

/**
 * SqlStatementBuilder supporting Hsql-specific delimiter changes.
 */
public class HsqlSqlStatementBuilder extends SqlStatementBuilder {
    /**
     * Are we inside a BEGIN ATOMIC block.
     */
    private boolean insideAtomicBlock;

    @Override
    protected Delimiter changeDelimiterIfNecessary(String line, Delimiter delimiter) {
        if (line.contains("BEGIN ATOMIC")) {
            insideAtomicBlock = true;
        }

        if (line.endsWith("END;")) {
            insideAtomicBlock = false;
        }

        if (insideAtomicBlock) {
            return null;
        }
        return getDefaultDelimiter();
    }
}
