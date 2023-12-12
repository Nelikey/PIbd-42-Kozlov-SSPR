package ru.ulstu.is.sbapp.district.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.district.model.District;
import ru.ulstu.is.sbapp.district.repository.DistrictRepository;
import ru.ulstu.is.sbapp.dto.DistrictDto;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {
    private final ValidatorUtil validatorUtil;
    private final DistrictRepository districtRepository;
    //private final DoctorService doctorService;

    public DistrictService(ValidatorUtil validatorUtil, DistrictRepository districtRepository) {
        this.validatorUtil = validatorUtil;
        this.districtRepository = districtRepository;
        //this.doctorService = doctorService;
    }

    @Transactional
    public District addDistrict(String description) {
        final District district = new District(description);
        validatorUtil.validate(district);
        return districtRepository.save(district);
    }

    @Transactional(readOnly = true)
    public District findDistrict(Long id) {
        final Optional<District> district = districtRepository.findById(id);
        // если нулл, то вызывается exception
        return district.orElseThrow(() -> new DistrictNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    @Transactional
    public District updateDistrict(Long id, String description) {
        final District currentDistrict = findDistrict(id);
        currentDistrict.setDescription(description);
        //currentDistrict.setDoctor(doctor);
        validatorUtil.validate(currentDistrict);
        return districtRepository.save(currentDistrict);
    }
    @Transactional
    public DistrictDto updateDistrict (DistrictDto districtDto){
        //Long doctorid = districtDto.getDoctor().getId();
        //Doctor doctor = doctorService.findDoctor(doctorid);
        return new DistrictDto(updateDistrict(districtDto.getId(), districtDto.getDescription()));
    }

    @Transactional
    public District deleteDistrict(Long id) {
        final District currentDistrict = findDistrict(id);
        districtRepository.delete(currentDistrict);
        return currentDistrict;
    }

    @Transactional
    public void deleteAllDistricts() {
        districtRepository.deleteAll();
    }
}

