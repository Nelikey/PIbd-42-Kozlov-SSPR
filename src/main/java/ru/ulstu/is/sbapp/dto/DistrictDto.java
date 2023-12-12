package ru.ulstu.is.sbapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.district.model.District;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class DistrictDto implements Serializable {
    private Long id;
    @NotBlank(message = "Description can't be null or empty")
    private String Description;

    public DistrictDto(){

    }

    public DistrictDto(District District){
        this.id = District.getId();
        this.Description = District.getDescription();
        /*if(District.getDoctor() != null) {
            this.doctor = new DoctorForDistrictDto(District.getDoctor());
        }*/
    }
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId(){return id;}
    public String getDescription(){
        return Description;
    }

    public void setDescription(String description){
        Description = description;
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(id, Description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "description = " + Description + ")";
    }*/
}
