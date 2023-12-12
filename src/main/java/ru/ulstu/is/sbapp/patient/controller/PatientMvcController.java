package ru.ulstu.is.sbapp.patient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.district.service.DistrictService;
import ru.ulstu.is.sbapp.doctor.service.DoctorService;
import ru.ulstu.is.sbapp.dto.DistrictDto;
import ru.ulstu.is.sbapp.patient.service.PatientService;
import ru.ulstu.is.sbapp.dto.PatientDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/patient")
public class PatientMvcController {
    private final PatientService patientService;
    private final DistrictService districtService;
    private final DoctorService doctorService;

    public PatientMvcController(PatientService patientService, DistrictService districtService, DoctorService doctorService){
        this.patientService = patientService;
        this.districtService = districtService;
        this.doctorService = doctorService;
    }

    @GetMapping
    public String getPatients(Model model) {
        model.addAttribute("patients",
                patientService.findAllPatients().stream()
                        .map(PatientDto::new)
                        .toList());
        return "patient";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editPatient(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("districts",
                districtService.findAllDistricts().stream()
                        .map(DistrictDto::new)
                        .toList());
        if (id == null || id <= 0) {
            model.addAttribute("patientDto", new PatientDto());
        }
        else {
            model.addAttribute("patientId", id);
            model.addAttribute("patientDto", new PatientDto(patientService.findPatient(id)));
        }
        return "patient-edit";
    }

    @PostMapping(value = {"/edit", "/edit/{id}"})
    public String savePatient(@PathVariable(required = false) Long id,
                             @ModelAttribute @Valid PatientDto patientDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "patient-edit";
        }
        if (id == null || id <= 0) {
            patientService.addPatient(patientDto.getFirstName(), patientDto.getLastName(), patientDto.getPhoneNumber(), patientDto.getDistrictId());
        } else {
            patientService.updatePatient(id, patientDto.getFirstName(), patientDto.getLastName(), patientDto.getPhoneNumber(), patientDto.getDistrictId());
        }
        return "redirect:/patient";
    }

    @PostMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patient";
    }

}
