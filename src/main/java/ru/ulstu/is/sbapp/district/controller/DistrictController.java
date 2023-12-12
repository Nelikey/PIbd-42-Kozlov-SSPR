package ru.ulstu.is.sbapp.district.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.district.service.DistrictService;
import ru.ulstu.is.sbapp.dto.DistrictDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/district")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/{id}")
    public DistrictDto getDistrict(@PathVariable Long id) {
        return new DistrictDto(districtService.findDistrict(id));
    }

    @GetMapping("/")
    public List<DistrictDto> getDistricts() {
        return districtService.findAllDistricts().stream()
                .map(DistrictDto::new)
                .toList();
    }

    @PostMapping("/")
    public DistrictDto createDistrict(@RequestBody @Valid DistrictDto districtDto) {
        return new DistrictDto(districtService.addDistrict(districtDto.getDescription()));
    }

    @PutMapping("/{id}")
    public DistrictDto updateDistrict(@PathVariable Long id,
                                      @RequestBody @Valid DistrictDto districtDto) {
        return new DistrictDto(districtService.updateDistrict(id, districtDto.getDescription()));
    }

    @DeleteMapping("/{id}")
    public DistrictDto deleteDistrict(@PathVariable Long id) {
        return new DistrictDto(districtService.deleteDistrict(id));
    }
}