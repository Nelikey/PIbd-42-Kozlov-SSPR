package ru.ulstu.is.sbapp.district.model;

import ru.ulstu.is.sbapp.doctor.model.Doctor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "\"district\"")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;

    //@OneToOne(mappedBy = "district")
    //private Doctor doctor;

    public District() {

    }

    public District(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(id, district.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
