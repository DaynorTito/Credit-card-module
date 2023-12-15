package org.company.com.mock;

import java.util.UUID;

public interface InserterTable<T> {
    UUID insertOne(T entity);
}
