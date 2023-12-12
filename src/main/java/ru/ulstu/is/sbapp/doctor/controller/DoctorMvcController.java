package ru.ulstu.is.sbapp.doctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.district.service.DistrictService;
import ru.ulstu.is.sbapp.doctor.service.DoctorService;
import ru.ulstu.is.sbapp.dto.DistrictDto;
import ru.ulstu.is.sbapp.dto.DoctorDto;
import ru.ulstu.is.sbapp.patient.service.PatientService;

import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorMvcController {
    private final DoctorService doctorService;
    private final DistrictService districtService;
    private final PatientService patientService;

    public DoctorMvcController(DoctorService doctorService, DistrictService districtService, PatientService patientService){
        this.doctorService = doctorService;
        this.districtService = districtService;
        this.patientService = patientService;
    }

    @GetMapping
    public String getDoctors(Model model) {
        model.addAttribute("doctors",
                doctorService.findAllDoctors().stream()
                        .map(DoctorDto::new)
                        .toList());
        return "doctor";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editDoctor(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("districts",
                districtService.findAllDistricts().stream()
                        .map(DistrictDto::new)
                        .toList());
        if (id == null || id <= 0) {
            model.addAttribute("doctorDto", new DoctorDto());
        }
        else {
            model.addAttribute("doctorId", id);
            model.addAttribute("doctorDto", new DoctorDto(doctorService.findDoctor(id)));
        }
        return "doctor-edit";
    }

    @PostMapping(value = {"/edit", "/edit/{id}"})
    public String saveDoctor(@PathVariable(required = false) Long id,
                               @ModelAttribute @Valid DoctorDto doctorDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "doctor-edit";
        }
        if (id == null || id <= 0) {
            doctorService.addDoctor(doctorDto.getFirstName(), doctorDto.getLastName(), doctorDto.getDistrictId());
        } else {
            doctorService.updateDoctor(id, doctorDto.getFirstName(), doctorDto.getLastName(), doctorDto.getDistrictId());
        }
        return "redirect:/doctor";
    }

    @PostMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctor";
    }

}
