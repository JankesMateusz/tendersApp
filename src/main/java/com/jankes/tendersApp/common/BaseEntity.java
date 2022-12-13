package com.jankes.tendersApp.common;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Override
    public int hashCode(){

        return getClass().hashCode();
    }

    public Long getId() {
        return id;
    }
}
