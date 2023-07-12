package sn.esmt.mpisi2.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.esmt.mpisi2.course.dao.CourseDAO;
import sn.esmt.mpisi2.course.dao.StudentDAO;
import sn.esmt.mpisi2.course.models.Course;
import sn.esmt.mpisi2.course.models.Student;

import java.util.List;

@Service("jdbc")
public class CourseServiceJDBC implements ICourseService{

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentDAO studentDAO;


    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    @Override
    public List<Student> getStudentsByCourse(int courseId) {
        return studentDAO.getAllStudentByCourse(courseId);
    }

    @Override
    public Student addStudentInCourse(Student student, int courseId) throws ClassNotFoundException{
        getCourseById(courseId);
        studentDAO.addStudent(student, courseId);
        return student;
    }

    @Override
    public Course addCourse(Course course) {
        courseDAO.addCourse(course);
        return course;
    }

    @Override
    public Course getCourseById(int courseId) throws ClassNotFoundException {
        var course = courseDAO.getCourseById(courseId);
        if(course == null) {
            throw new ClassNotFoundException("Course not found");
        }
        return course;
    }
}
