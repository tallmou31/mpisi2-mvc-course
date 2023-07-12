package sn.esmt.mpisi2.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.esmt.mpisi2.course.models.Course;
import sn.esmt.mpisi2.course.models.Student;
import sn.esmt.mpisi2.course.services.ICourseService;
import sn.esmt.mpisi2.course.services.dto.CourseFormDTO;
import sn.esmt.mpisi2.course.services.dto.StudentFormDTO;

import javax.validation.Valid;

@Controller
public class CourseController {

    @Autowired
    //@Qualifier("jdbc")
    @Qualifier("jpa")
    private ICourseService courseService;


    @GetMapping("/")
    public String showCoursesList(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/courses/new")
    public String showCourseForm(Model model) {
        model.addAttribute("courseFormDTO", new CourseFormDTO());
        return "courseForm";
    }

    @PostMapping("/courses")
    public String createCourse(@Valid CourseFormDTO courseFormDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "courseForm";
        }

        Course course = new Course();
        course.setName(courseFormDTO.getName());

        courseService.addCourse(course);

        return "redirect:/";
    }

    @GetMapping("/courses/students/{courseId}")
    public String showStudentsList(@PathVariable int courseId, Model model) throws ClassNotFoundException {
        model.addAttribute("course", courseService.getCourseById(courseId));
        model.addAttribute("students", courseService.getStudentsByCourse(courseId));
        return "students";
    }

    @GetMapping("/courses/students/new/{courseId}")
    public String showStudentForm(@PathVariable int courseId, Model model) throws ClassNotFoundException  {
        model.addAttribute("studentFormDTO", new StudentFormDTO());
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "studentForm";
    }

    @PostMapping("/courses/students/new/{courseId}")
    public String createStudent(@PathVariable int courseId, @Valid
                                StudentFormDTO studentFormDTO, BindingResult result, Model model) throws ClassNotFoundException  {
        if (result.hasErrors()) {
            model.addAttribute("course", courseService.getCourseById(courseId));
            return "studentForm";
        }

        var student = new Student();
        student.setName(studentFormDTO.getName());
        student.setGrade(studentFormDTO.getGrade());
        courseService.addStudentInCourse(student, courseId);

        return "redirect:/courses/students/"+courseId;
    }


}





