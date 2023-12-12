package ru.ulstu.is.sbapp.patient.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.district.repository.DistrictRepository;
import ru.ulstu.is.sbapp.dto.PatientDto;
import ru.ulstu.is.sbapp.patient.model.Patient;
import ru.ulstu.is.sbapp.patient.service.PatientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API+"/patient")
public class PatientController {
    private final PatientService patientService;

    private final DistrictRepository districtRepository;

    public PatientController(PatientService patientService, DistrictRepository districtRepository) {
        this.patientService = patientService;
        this.districtRepository = districtRepository;
    }

    @GetMapping("/{id}")
    public PatientDto getPatient(@PathVariable Long id) {
        return new PatientDto(patientService.findPatient(id));
    }

    @GetMapping("/")
    public List<PatientDto> getPatients() {
        return patientService.findAllPatients().stream()
                .map(PatientDto::new)
                .toList();
    }

    @PostMapping("/")
    public PatientDto createPatient(@RequestBody @Valid PatientDto patientDto)
    {
        return new PatientDto(patientService.addPatient(patientDto.getFirstName(), patientDto.getLastName(), patientDto.getPhoneNumber(), districtRepository.getById(patientDto.getDistrict().getId())));
    }

    @PutMapping("/{id}")
    public PatientDto updatePatient(@PathVariable Long id,
                                    @RequestBody @Valid PatientDto patientDto) {
        return new PatientDto(patientService.updatePatient(id, patientDto.getFirstName(), patientDto.getLastName(), patientDto.getPhoneNumber(), districtRepository.getById(patientDto.getDistrict().getId())));
    }

    @DeleteMapping("/{id}")
    public Patient deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }
}