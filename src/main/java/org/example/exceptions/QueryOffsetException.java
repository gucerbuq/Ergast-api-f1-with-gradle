package org.example.exceptions;

/**
 * Date: 23.03.17
 *
 * @author olerom
 */
public class QueryOffsetException extends IllegalArgumentException {
    public QueryOffsetException(String msg) {
        super(msg);
    }
}
