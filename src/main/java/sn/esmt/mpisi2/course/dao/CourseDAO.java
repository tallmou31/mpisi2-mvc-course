package sn.esmt.mpisi2.course.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sn.esmt.mpisi2.course.models.Course;

import java.util.List;

@Repository
public class CourseDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> getAllCourses() {
        String sql = "SELECT id, name FROM Course";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Course.class));
    }

    public void addCourse(Course course) {
        String sql = "INSERT INTO Course (name) VALUES (?)";
        jdbcTemplate.update(sql, course.getName());
    }

    public void updateCourse(Course course) {
        String sql = "UPDATE Course SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, course.getName(), course.getId());
    }

    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM Course WHERE id = ?";
        jdbcTemplate.update(sql, courseId);
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT id, name FROM Course WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{courseId}, BeanPropertyRowMapper.newInstance(Course.class));
    }
}
