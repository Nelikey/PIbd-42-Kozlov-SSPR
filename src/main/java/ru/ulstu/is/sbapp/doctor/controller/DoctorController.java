package ru.ulstu.is.sbapp.doctor.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.district.repository.DistrictRepository;
import ru.ulstu.is.sbapp.district.service.DistrictService;
import ru.ulstu.is.sbapp.doctor.service.DoctorService;
import ru.ulstu.is.sbapp.dto.DoctorDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API+"/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final DistrictService districtService;

    private final DistrictRepository districtRepository;

    public DoctorController(DoctorService doctorService, DistrictService districtService, DistrictRepository districtRepository) {
        this.doctorService = doctorService;
        this.districtService = districtService;
        this.districtRepository = districtRepository;
    }

    @GetMapping("/{id}")
    public DoctorDto getDoctor(@PathVariable Long id) {
        return new DoctorDto(doctorService.findDoctor(id));
    }

    @GetMapping("/")
    public List<DoctorDto> getDoctors() {
        return doctorService.findAllDoctors().stream()
                .map(DoctorDto::new)
                .toList();
    }

    @PostMapping("/")
    public DoctorDto createDoctor(@RequestBody @Valid DoctorDto doctorDto) {
        return new DoctorDto(doctorService.addDoctor(doctorDto.getFirstName(), doctorDto.getLastName(), districtRepository.getById(doctorDto.getDistrict().getId())));
    }

    /*@PatchMapping("/{id}")
    public DoctorDto updateDoctor(@RequestBody @Valid DoctorDto doctorDto) {
        return doctorService.updateDoctor(doctorDto);
    }*/

    @PutMapping("/{id}")
    public DoctorDto updateDoctor(@PathVariable Long id,
                                  @RequestBody @Valid DoctorDto doctorDto) {
        return new DoctorDto(doctorService.updateDoctor(id, doctorDto.getFirstName(), doctorDto.getLastName(), districtRepository.getById(doctorDto.getDistrict().getId())));
    }

    @DeleteMapping("/{id}")
    public DoctorDto deleteDoctor(@PathVariable Long id) {
        return new DoctorDto(doctorService.deleteDoctor(id));
    }
}