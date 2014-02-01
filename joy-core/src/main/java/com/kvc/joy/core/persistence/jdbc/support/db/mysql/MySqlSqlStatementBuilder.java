package com.kvc.joy.core.persistence.jdbc.support.db.mysql;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.support.db.Delimiter;
import com.kvc.joy.core.persistence.jdbc.support.db.SqlStatementBuilder;

import java.util.regex.Pattern;

/**
 * SqlStatementBuilder supporting MySQL-specific delimiter changes.
 */
public class MySqlSqlStatementBuilder extends SqlStatementBuilder {
    /**
     * The keyword that indicates a change in delimiter.
     */
    private static final String DELIMITER_KEYWORD = "DELIMITER";
    private final String[] charSets = {
            "ARMSCII8","ASCII","BIG5","BINARY","CP1250","CP1251","CP1256","CP1257","CP850","CP852","CP866","CP932",
            "DEC8","EUCJPMS","EUCKR","GB2312","GBK","GEOSTD8","GREEK","HEBREW","HP8","KEYBCS2","KOI8R","KOI8U","LATIN1",
            "LATIN2","LATIN5","LATIN7","MACCE","MACROMAN","SJIS","SWE7","TIS620","UCS2","UJIS","UTF8"
    };

    @Override
    public Delimiter extractNewDelimiterFromLine(String line) {
        if (line.toUpperCase().startsWith(DELIMITER_KEYWORD)) {
            return new Delimiter(line.substring(DELIMITER_KEYWORD.length()).trim(), false);
        }

        return null;
    }

    @Override
    protected Delimiter changeDelimiterIfNecessary(String line, Delimiter delimiter) {
        if (line.toUpperCase().startsWith(DELIMITER_KEYWORD)) {
            return new Delimiter(line.substring(DELIMITER_KEYWORD.length()).trim(), false);
        }

        return delimiter;
    }

    @Override
    public boolean isCommentDirective(String line) {
        return line.matches("^" + Pattern.quote("/*!") + "\\d{5} .*" + Pattern.quote("*/") + ";?");
    }

    @Override
    public boolean isSingleLineComment(String line) {
        return line.startsWith("--") || line.startsWith("#");
    }

    @Override
    protected String removeEscapedQuotes(String token) {
        String noEscapedBackslashes = StringTool.replace(token, "\\\\","");
        String noBackslashEscapes = StringTool.replace(StringTool.replace(noEscapedBackslashes, "\\'", ""), "\\\"", "");
        return StringTool.replace(noBackslashEscapes, "''", "");
    }

    @Override
    protected String removeCharsetCasting(String token) {
        if(token.startsWith("_")) {
            for(String charSet: charSets) {
                String cast = "_" + charSet;
                if(token.startsWith(cast)) {
                    return token.substring(cast.length());
                }
            }
        }
        // If now matches are found for charset casting then return token
        return token;
    }

    @Override
    protected String extractAlternateOpenQuote(String token) {
        if (token.startsWith("\"")) {
            return "\"";
        }
        // to be a valid bitfield or hex literal the token must be at leas three characters in length
        // i.e. b'' otherwise token may be string literal ending in [space]b'
        if (token.startsWith("B'") && token.length() > 2) {
            return "B'";
        }
        if (token.startsWith("X'") && token.length() > 2) {
            return "X'";
        }
        return null;
    }

    @Override
    protected String computeAlternateCloseQuote(String openQuote) {
        if ("B'".equals(openQuote) || "X'".equals(openQuote)) {
            return "'";
        }
        return openQuote;
    }
}
