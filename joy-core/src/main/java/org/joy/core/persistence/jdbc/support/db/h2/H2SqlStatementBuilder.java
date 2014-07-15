package org.joy.core.persistence.jdbc.support.db.h2;

import org.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

/**
 * SqlStatementBuilder supporting H2-specific delimiter changes.
 */
public class H2SqlStatementBuilder extends SqlStatementBuilder {
    @Override
    protected String extractAlternateOpenQuote(String token) {
        if (token.startsWith("$$")) {
            return "$$";
        }
        return null;
    }
}
