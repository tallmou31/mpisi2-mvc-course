package sn.esmt.mpisi2.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.esmt.mpisi2.course.models.Course;
import sn.esmt.mpisi2.course.models.Student;
import sn.esmt.mpisi2.course.repository.CourseRepository;
import sn.esmt.mpisi2.course.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service("jpa")
public class CourseServiceJPA implements ICourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByCourse(int courseId) {
        return courseRepository.findById(courseId).stream()
                .flatMap(s -> s.getStudents().stream()).collect(Collectors.toList());
    }

    @Override
    public Student addStudentInCourse(Student student, int courseId) throws ClassNotFoundException{
        var course = getCourseById(courseId);
        student.setCourse(course);
        studentRepository.save(student);
        return student;
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(int courseId) throws ClassNotFoundException {
        return courseRepository.findById(courseId).orElseThrow(() -> new ClassNotFoundException("Course not found"));
    }
}
