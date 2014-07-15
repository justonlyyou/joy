package org.joy.core.persistence.jdbc.support.db.derby;

import org.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

/**
 * SqlStatementBuilder supporting Derby-specific delimiter changes.
 */
public class DerbySqlStatementBuilder extends SqlStatementBuilder {
    @Override
    protected String extractAlternateOpenQuote(String token) {
        if (token.startsWith("$$")) {
            return "$$";
        }
        return null;
    }
}
