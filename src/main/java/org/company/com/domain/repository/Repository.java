package org.company.com.domain.repository;

import java.util.UUID;

/**
 * Base class for all repositories.
 *
 * @param <T> Entity type
 */
public interface Repository<T> {

    UUID insertOne(T entity) throws Exception;

    T getOne(UUID id) throws Exception;

    boolean updateOne(T entity) throws Exception;
    
    boolean deleteOne(UUID id) throws Exception;
}
