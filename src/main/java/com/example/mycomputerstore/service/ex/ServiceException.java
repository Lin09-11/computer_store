package com.example.mycomputerstore.service.ex;

/**
 * 因为整个业务的异常只有一种情况下才会产生:只有运行时才会产生,不运行不会产生
 * 业务异常的基类:throw new ServiceException("业务层产生未知的异常")
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
