package sn.esmt.mpisi2.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.esmt.mpisi2.course.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
