package sn.esmt.mpisi2.course.services;

import org.springframework.stereotype.Service;
import sn.esmt.mpisi2.course.models.Course;
import sn.esmt.mpisi2.course.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService{
    private static final List<Course> COURSE_LIST = new ArrayList<>();
    private static final AtomicInteger ID_COURSE_GENERATOR = new AtomicInteger(0);
    private static final AtomicInteger ID_STUDENT_GENERATOR = new AtomicInteger(0);


    @Override
    public List<Course> getAllCourses() {
        return COURSE_LIST;
    }

    @Override
    public List<Student> getStudentsByCourse(int courseId) {
        return COURSE_LIST.stream().filter(c -> c.getId() == courseId)
                .flatMap(c -> c.getStudents().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Student addStudentInCourse(Student student, int courseId) throws ClassNotFoundException{
        var course = getCourseById(courseId);
        student.setId(ID_STUDENT_GENERATOR.incrementAndGet());
        course.addStudent(student);
        return student;
    }

    @Override
    public Course addCourse(Course course) {
        course.setId(ID_COURSE_GENERATOR.incrementAndGet());
        COURSE_LIST.add(course);

        return course;
    }

    @Override
    public Course getCourseById(int courseId) throws ClassNotFoundException {
        return COURSE_LIST.stream().filter(c -> c.getId() == courseId)
                .findFirst()
                .orElseThrow(() -> new ClassNotFoundException("Course not found"));
    }
}
