package ru.ulstu.is.sbapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.patient.model.Patient;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class PatientDto implements Serializable {
    private Long id;
    @NotBlank(message = "Firstname can't be null or empty")
    private String FirstName;
    @NotBlank(message = "Lastname can't be null or empty")
    private String LastName;
    private String phoneNumber;

    private DistrictDto district;

    private Long districtId;

    public PatientDto(){

    }

    public PatientDto(Patient patient){
        this.id = patient.getId();
        this.FirstName = patient.getFirstName();
        this.LastName = patient.getLastName();
        this.phoneNumber = patient.getPhoneNumber();
        if(patient.getDistrict() != null){
            this.district = new DistrictDto(patient.getDistrict());
            districtId = patient.getDistrict().getId();
        }
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName(){
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public DistrictDto getDistrict(){
        return district;
    }

    public Long getDistrictId(){
        return districtId;
    }

    public void setDistrictId(Long districtId){
        this.districtId = districtId;
    }
}
