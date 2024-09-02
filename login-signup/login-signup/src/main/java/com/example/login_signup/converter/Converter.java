package com.example.signup.converter;

public interface Converter<E, M> {
    E convertToEntity(E e, M m);
    M convertToModel(E e, M m);
}
