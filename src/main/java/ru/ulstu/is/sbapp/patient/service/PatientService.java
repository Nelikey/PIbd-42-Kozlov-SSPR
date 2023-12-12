package ru.ulstu.is.sbapp.patient.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.district.model.District;
import ru.ulstu.is.sbapp.district.repository.DistrictRepository;
import ru.ulstu.is.sbapp.district.service.DistrictNotFoundException;
import ru.ulstu.is.sbapp.doctor.model.Doctor;
import ru.ulstu.is.sbapp.dto.PatientDto;
import ru.ulstu.is.sbapp.patient.model.Patient;
import ru.ulstu.is.sbapp.patient.repository.PatientRepository;
import ru.ulstu.is.sbapp.district.service.DistrictService;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final ValidatorUtil validatorUtil;
    private final PatientRepository patientRepository;
    private final DistrictRepository districtRepository;
    private final DistrictService districtService;

    public PatientService(ValidatorUtil validatorUtil, PatientRepository patientRepository, DistrictRepository districtRepository, DistrictService districtService) {
        this.validatorUtil = validatorUtil;
        this.patientRepository = patientRepository;
        this.districtRepository = districtRepository;
        this.districtService = districtService;
    }

    @Transactional
    public Patient addPatient(String firstName, String lastName, String phonenumber, District district) {
        final Patient patient = new Patient(firstName, lastName, phonenumber, district);
        validatorUtil.validate(patient);
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient addPatient(String firstName, String lastName, String phonenumber, Long districtId) {
        final District district;
        district = districtService.findDistrict(districtId);
        final Patient patient = new Patient(firstName, lastName, phonenumber, district);
        validatorUtil.validate(patient);
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient addPatient(String firstName, String lastName, String phonenumber) {
        final Patient patient = new Patient(firstName, lastName, phonenumber);
        validatorUtil.validate(patient);
        return patientRepository.save(patient);
    }

    @Transactional
    public PatientDto addPatient(PatientDto patientDto){
        String firstname = patientDto.getFirstName();
        String lastname = patientDto.getLastName();
        String phonenumber = patientDto.getPhoneNumber();
        District district = null;
        if (patientDto.getDistrict() != null){
            district = districtRepository.findById(patientDto.getDistrict().getId()).orElseThrow(()-> new DistrictNotFoundException(patientDto.getDistrict().getId()));
        }
        return new PatientDto(addPatient(firstname, lastname, phonenumber, district));
    }

    @Transactional(readOnly = true)
    public Patient findPatient(Long id) {
        final Optional<Patient> patient = patientRepository.findById(id);
        // если нулл, то вызывается exception
        return patient.orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public Patient updatePatient(Long id, String firstName, String lastName, String phonenumber, District district) {
        final Patient currentPatient = findPatient(id);
        currentPatient.setFirstName(firstName);
        currentPatient.setLastName(lastName);
        currentPatient.setPhoneNumber(phonenumber);
        currentPatient.setDistrict(district);
        validatorUtil.validate(currentPatient);
        return patientRepository.save(currentPatient);
    }

    @Transactional
    public Patient updatePatient(Long id, String firstName, String lastName, String phonenumber) {
        final Patient currentPatient = findPatient(id);
        currentPatient.setFirstName(firstName);
        currentPatient.setLastName(lastName);
        currentPatient.setPhoneNumber(phonenumber);
        validatorUtil.validate(currentPatient);
        return patientRepository.save(currentPatient);
    }

    @Transactional
    public PatientDto updatePatient (PatientDto patientDto){
        String firstname = patientDto.getFirstName();
        String lastname = patientDto.getLastName();
        Long districtid = patientDto.getDistrict().getId();
        District district = districtService.findDistrict(districtid);
        return new PatientDto(updatePatient(patientDto.getId(), firstname, lastname, patientDto.getPhoneNumber(), district));
    }

    @Transactional
    public Patient updatePatient(Long id, String firstName, String lastName, String phonenumber, Long districtId) {
        final District district;
        district = districtService.findDistrict(districtId);
        final Patient currentPatient = findPatient(id);
        currentPatient.setFirstName(firstName);
        currentPatient.setLastName(lastName);
        currentPatient.setPhoneNumber(phonenumber);
        currentPatient.setDistrict(district);
        validatorUtil.validate(currentPatient);
        return patientRepository.save(currentPatient);
    }

    @Transactional
    public Patient deletePatient(Long id) {
        final Patient currentPatient = findPatient(id);
        patientRepository.delete(currentPatient);
        return currentPatient;
    }

    @Transactional
    public void deleteAllPatients() {
        patientRepository.deleteAll();
    }
}

