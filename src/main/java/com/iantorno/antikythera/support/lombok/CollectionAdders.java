package com.iantorno.antikythera.support.lombok;

import com.iantorno.antikythera.model.BaseNode;

import java.util.Collection;

public interface CollectionAdders<E> {
    boolean add(E e);
    boolean addAll(Collection<? extends E> c);
}
