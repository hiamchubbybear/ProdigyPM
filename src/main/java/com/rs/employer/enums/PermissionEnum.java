package com.rs.employer.enums;

import jakarta.persistence.PersistenceContext;

@PersistenceContext
public enum PermissionEnum {

    PERMIT_ALL,
    UPDATE_USER,
    DELETE_MS,
    ADD_USER,
    UPDATE_PRODUCT,
    DELETE_PRODUCT,
    ADD_PRODUCT,
    UPDATE_RESOURCES,
    DELETE_RESOURCES,
    ADD_RESOURCES,
    UPDATE_ROLE,
    DELETE_ROLE,
    ADD_ROLE,
    UPDATE_PERMISSION,
    DELETE_PERMISSION,
    ADD_PERMISSION,

}
