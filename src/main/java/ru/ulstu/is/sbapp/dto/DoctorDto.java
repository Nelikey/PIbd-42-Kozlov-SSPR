package ru.ulstu.is.sbapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.district.model.District;
import ru.ulstu.is.sbapp.doctor.model.Doctor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DoctorDto implements Serializable {
    private Long id;
    @NotBlank(message = "Firstname can't be null or empty")
    private String FirstName;
    @NotBlank(message = "Lastname can't be null or empty")
    private String LastName;
    private District district;

    private Long districtId;

    public DoctorDto(){

    }

    public DoctorDto(Doctor doctor){
        this.id = doctor.getId();
        this.FirstName = doctor.getFirstName();
        this.LastName = doctor.getLastName();
        if(doctor.getDistrict() != null) {
            this.district = doctor.getDistrict();
            districtId = doctor.getDistrict().getId();
        }
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }
    public String getFirstName(){return FirstName;}

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName(){return LastName;}

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public District getDistrict() {return district;}

    public void setDistrict(District district) {
        this.district = district;
    }

    public Long getDistrictId(){
        return districtId;
    }

    public void setDistrictId(Long districtId){
        this.districtId = districtId;
    }

    /*public Long getDistrictId(){
        return district.getId();
    }

    public void setDistrictId(Long id){
        district.
    }*/

    /*@Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, district);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "district = " + district + ")";
    }*/
}
