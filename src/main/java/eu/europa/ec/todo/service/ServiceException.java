package eu.europa.ec.todo.service;

public class ServiceException extends Exception {
    public ServiceException(String msg, Exception e) {
        super(msg, e);
    }
}
