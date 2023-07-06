package sn.esmt.mpisi2.course.services;

import sn.esmt.mpisi2.course.models.Course;
import sn.esmt.mpisi2.course.models.Student;

import java.util.List;

public interface ICourseService {
    List<Course> getAllCourses();
    List<Student> getStudentsByCourse(int courseId) throws ClassNotFoundException;

    Student addStudentInCourse(Student student, int courseId) throws ClassNotFoundException;

    Course addCourse(Course course);

    Course getCourseById(int courseId) throws ClassNotFoundException;
}
