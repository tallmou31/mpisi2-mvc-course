package sn.esmt.mpisi2.course.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sn.esmt.mpisi2.course.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addStudent(Student student, int courseId) {
        String sql = "INSERT INTO Student (name, grade, course_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getGrade(), courseId);
    }

    public void updateStudent(Student student, int courseId) {
        String sql = "UPDATE Student SET name = ?, grade = ?, course_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getGrade(), courseId, student.getId());
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM Student WHERE id = ?";
        jdbcTemplate.update(sql, studentId);
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT id, name, grade FROM Student WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{studentId}, BeanPropertyRowMapper.newInstance(Student.class));
    }

    public List<Student> getAllStudentByCourse(int courseId) {
        String sql = "SELECT id, name, grade FROM Student where course_id = ?";
        return jdbcTemplate.query(sql, new Object[]{courseId}, BeanPropertyRowMapper.newInstance(Student.class));
    }

}
