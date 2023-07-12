package sn.esmt.mpisi2.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.esmt.mpisi2.course.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    
}
