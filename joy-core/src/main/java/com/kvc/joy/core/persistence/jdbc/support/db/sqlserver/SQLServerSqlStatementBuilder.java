package com.kvc.joy.core.persistence.jdbc.support.db.sqlserver;

import com.kvc.joy.core.persistence.jdbc.support.db.Delimiter;
import com.kvc.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

/**
 * SqlStatementBuilder supporting SQL Server-specific delimiter changes.
 */
public class SQLServerSqlStatementBuilder extends SqlStatementBuilder {
    @Override
    protected Delimiter getDefaultDelimiter() {
        return new Delimiter("GO", true);
    }

    @Override
    protected String extractAlternateOpenQuote(String token) {
        if (token.startsWith("N'")) {
            return "N'";
        }
        return null;
    }

    @Override
    protected String computeAlternateCloseQuote(String openQuote) {
        return "'";
    }
}
