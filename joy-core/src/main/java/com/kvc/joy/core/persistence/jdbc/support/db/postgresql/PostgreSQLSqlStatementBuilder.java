package com.kvc.joy.core.persistence.jdbc.support.db.postgresql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kvc.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

/**
 * SqlStatementBuilder supporting PostgreSQL specific syntax.
 */
public class PostgreSQLSqlStatementBuilder extends SqlStatementBuilder {
    /**
     * Matches $$, $BODY$, $xyz123$, ...
     */
    /*private -> for testing*/
    static final String DOLLAR_QUOTE_REGEX = "\\$[A-Za-z0-9_]*\\$.*";

    @Override
    protected String extractAlternateOpenQuote(String token) {
        Matcher matcher = Pattern.compile(DOLLAR_QUOTE_REGEX).matcher(token);
        if (matcher.find()) {
            return token.substring(matcher.start(), matcher.end());
        }
        return null;
    }
}
