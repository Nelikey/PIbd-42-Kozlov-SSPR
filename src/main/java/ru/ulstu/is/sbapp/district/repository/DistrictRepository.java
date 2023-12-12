package ru.ulstu.is.sbapp.district.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.district.model.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
}