package com.jankes.tendersApp.common;

public interface DtoMapper<S, T> {
    S toDto(T entity);
    T toEntity(S dto);
}
