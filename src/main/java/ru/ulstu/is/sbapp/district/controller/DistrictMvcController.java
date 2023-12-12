package ru.ulstu.is.sbapp.district.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.district.service.DistrictService;
import ru.ulstu.is.sbapp.doctor.service.DoctorService;
import ru.ulstu.is.sbapp.dto.DistrictDto;
import ru.ulstu.is.sbapp.patient.service.PatientService;

import javax.validation.Valid;

@Controller
@RequestMapping("/district")
public class DistrictMvcController {
    private final DistrictService districtService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public DistrictMvcController(DistrictService districtService, DoctorService doctorService, PatientService patientService){
        this.districtService = districtService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping
    public String getDistricts(Model model) {
        model.addAttribute("districts",
                districtService.findAllDistricts().stream()
                        .map(DistrictDto::new)
                        .toList());
        return "district";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editDistrict(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("districtDto", new DistrictDto());
        }
        else {
            model.addAttribute("districtId", id);
            model.addAttribute("districtDto", new DistrictDto(districtService.findDistrict(id)));
        }
        return "district-edit";
    }

    @PostMapping(value = {"/edit", "/edit/{id}"})
    public String saveDistrict(@PathVariable(required = false) Long id,
                          @ModelAttribute @Valid DistrictDto districtDto,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "district-edit";
        }
        if (id == null || id <= 0) {
            districtService.addDistrict(districtDto.getDescription());
        } else {
            districtService.updateDistrict(id, districtDto.getDescription());
        }
        return "redirect:/district";
    }

    @PostMapping("/delete/{id}")
    public String deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
        return "redirect:/district";
    }
    
}
