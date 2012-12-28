/**
 * @(#)WordException.java, 2012-12-22. 
 * 
 * All rights reserved.
 */
package com.word.server.exceptions;


/**
 *
 * @author Mn
 *
 */
public class WordException extends Exception {

    private static final long serialVersionUID = 1211580266844851925L;

    public static final int CODE_UNKNOWN_EXCEPTION = 600;
    public static final int CODE_CONFIG_EXCEPTION = 601;
    public static final int CODE_UNKNOWN_HOST_EXCEPTION = 602;
    public static final int CODE_UNKNOWN_URI = 603;
    public static final int CODE_WORD_NOT_EXSITS = 604;
    
    private int exceptionType;
    
    public WordException(int exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }
    
    public WordException() {
        this("", CODE_UNKNOWN_EXCEPTION);
    }

    public WordException(String message, Throwable cause) {
        this(message, cause, CODE_UNKNOWN_EXCEPTION);
    }

    public WordException(String message) {
        this(message, CODE_UNKNOWN_EXCEPTION);
    }

    public WordException(Throwable cause) {
        this(null, cause, CODE_UNKNOWN_EXCEPTION);
    }
    
    public WordException(String message, Throwable cause, int exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }

    public WordException(String message, int exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public WordException(Throwable cause, int exceptionType) {
        this(null, cause, exceptionType);
    }
    
    public int getExceptionType() {
        return exceptionType;
    }
    
    public void setExceptionType(int type) {
        exceptionType = type;
    }
}
