package org.company.com.domain.model;

/**
 * @param <R> Type of the response
 * @param <T> type of the value of the request
 */
public interface Controller<R, T> {

    R execute(T request) throws Exception;
}
