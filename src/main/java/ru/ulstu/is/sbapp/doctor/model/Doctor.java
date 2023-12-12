package ru.ulstu.is.sbapp.doctor.model;

import ru.ulstu.is.sbapp.district.model.District;
import ru.ulstu.is.sbapp.district.service.DistrictService;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "\"doctor\"")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @ManyToOne
    @JoinColumn(name="district_id")
    private District district;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, District district) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.district = district;
    }

    public Doctor(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public District getDistrict(){
        return district;
    }

    public void setDistrict(District district){
        this.district = district;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName= '" + firstName + '\'' +
                ", lastName= '" + lastName + '\'' +
                '}';
    }
}
