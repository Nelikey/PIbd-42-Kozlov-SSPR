package ru.ulstu.is.sbapp.district.service;

public class DistrictNotFoundException extends RuntimeException {
    public DistrictNotFoundException(Long id) {
        super(String.format("District with id [%s] is not found", id));
    }
}
