package org.example.exceptions;

/**
 * Date: 23.03.17
 *
 * @author olerom
 */
public class QueryLimitException extends IllegalArgumentException {
    public QueryLimitException(String msg) {
        super(msg);
    }
}
