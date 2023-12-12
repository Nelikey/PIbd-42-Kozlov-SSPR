package ru.ulstu.is.sbapp.doctor.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.district.repository.DistrictRepository;
import ru.ulstu.is.sbapp.district.service.DistrictNotFoundException;
import ru.ulstu.is.sbapp.doctor.model.Doctor;
import ru.ulstu.is.sbapp.doctor.repository.DoctorRepository;
import ru.ulstu.is.sbapp.dto.DoctorDto;
import ru.ulstu.is.sbapp.district.model.District;
import ru.ulstu.is.sbapp.district.service.DistrictService;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final ValidatorUtil validatorUtil;
    private final DoctorRepository doctorRepository;
    private final DistrictRepository districtRepository;
    private final DistrictService districtService;

    public DoctorService(ValidatorUtil validatorUtil, DoctorRepository doctorRepository, DistrictRepository districtRepository, DistrictService districtService) {
        this.validatorUtil = validatorUtil;
        this.doctorRepository = doctorRepository;
        this.districtRepository = districtRepository;
        this.districtService = districtService;
    }

    @Transactional
    public Doctor addDoctor(String firstName, String lastName, Long districtId) {
        final District district;
        district = districtService.findDistrict(districtId);
        final Doctor doctor = new Doctor(firstName, lastName, district);
        validatorUtil.validate(doctor);
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor addDoctor(String firstName, String lastName, District district) {
        final Doctor doctor = new Doctor(firstName, lastName, district);
        validatorUtil.validate(doctor);
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor addDoctor(String firstName, String lastName){
        if(!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Doctor FIO is null or empty");
        }
        final Doctor doctor = new Doctor(firstName, lastName);
        validatorUtil.validate(doctor);
        return doctorRepository.save(doctor);
    }

    @Transactional
    public DoctorDto addDoctor(DoctorDto doctorDto){
        String firstname = doctorDto.getFirstName();
        String lastname = doctorDto.getLastName();
        District district = null;
        if (doctorDto.getDistrict() != null){
            district = districtRepository.findById(doctorDto.getDistrict().getId()).orElseThrow(()-> new DistrictNotFoundException(doctorDto.getDistrict().getId()));
        }
        return new DoctorDto(addDoctor(firstname, lastname, district));
    }

    @Transactional(readOnly = true)
    public Doctor findDoctor(Long id) {
        final Optional<Doctor> doctor = doctorRepository.findById(id);
        // если нулл, то вызывается exception
        return doctor.orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional
    public Doctor updateDoctor(Long id, String firstName, String lastName, @Lazy District district) {
        final Doctor currentDoctor = findDoctor(id);
        currentDoctor.setFirstName(firstName);
        currentDoctor.setLastName(lastName);
        currentDoctor.setDistrict(district);
        validatorUtil.validate(currentDoctor);
        return doctorRepository.save(currentDoctor);
    }

    @Transactional
    public Doctor updateDoctor(Long id, String firstName, String lastName, Long districtId) {
        final District district;
        district = districtService.findDistrict(districtId);
        final Doctor currentDoctor = findDoctor(id);
        currentDoctor.setFirstName(firstName);
        currentDoctor.setLastName(lastName);
        currentDoctor.setDistrict(district);
        validatorUtil.validate(currentDoctor);
        return doctorRepository.save(currentDoctor);
    }

    @Transactional
    public DoctorDto updateDoctor (DoctorDto doctorDto){
        String firstname = doctorDto.getFirstName();
        String lastname = doctorDto.getLastName();
        Long districtid = doctorDto.getDistrict().getId();
        District district = districtService.findDistrict(districtid);
        return new DoctorDto(updateDoctor(doctorDto.getId(), firstname, lastname, district));
    }

    @Transactional
    public Doctor deleteDoctor(Long id) {
        final Doctor currentDoctor = findDoctor(id);
        doctorRepository.delete(currentDoctor);
        return currentDoctor;
    }

    @Transactional
    public void deleteAllDoctors() {
        doctorRepository.deleteAll();
    }
}

