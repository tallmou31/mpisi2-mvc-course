package sn.esmt.mpisi2.course.services.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentFormDTO {
    private int id;
    @NotNull
    @NotBlank
    private String name;

    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
