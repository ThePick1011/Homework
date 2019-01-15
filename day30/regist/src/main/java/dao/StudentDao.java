package dao;

import domain.Student;

import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 数据访问对象
public class StudentDao {

    // 查询所有
    public List<Student> findAll() {
        try (Connection conn = JdbcUtils.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select * from student")) {
                ResultSet rs = stmt.executeQuery();
                List<Student> list = new ArrayList<>();
                while (rs.next()) {
                    Student stu = new Student();
                    stu.setSid(rs.getInt("sid"));
                    stu.setSname(rs.getString("name"));
                    stu.setBirthday(rs.getDate("birthday"));
                    stu.setSex(rs.getString("sex"));
                    list.add(stu);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // 添加学生
    public void insert(Student student) {
        try (Connection con = JdbcUtils.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement("insert into student(name,birthday,sex)values (?,?,?)")) {
                statement.setString(1, student.getSname());
                statement.setDate(2, new java.sql.Date(student.getBirthday().getTime()));
                statement.setString(3, student.getSex());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 根据 id 查询
    public Student findById(int sid) {
        try (Connection con = JdbcUtils.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement("select * from student where sid=?")) {
                statement.setInt(1, sid);
                ResultSet resultSet = statement.executeQuery();
                Student student = new Student();
                if(resultSet.next()){
                    student.setSid(resultSet.getInt("sid"));
                    student.setSname(resultSet.getString("name"));
                    student.setBirthday(resultSet.getDate("birthday"));
                    student.setSex(resultSet.getString("sex"));
                }
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }

    // 修改学生
    public void update(Student student) {
        try(Connection con = JdbcUtils.getConnection()){
            try(PreparedStatement statement = con.prepareStatement("update student set name=?,birthday=?,sex=? where sid=?")){
                statement.setString(1,student.getSname());
                statement.setDate(2,new java.sql.Date(student.getBirthday().getTime()));
                statement.setString(3,student.getSex());
                statement.setInt(4,student.getSid());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    // 删除学生
    public void delete(int sid) {

        try (Connection connection = JdbcUtils.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from student where sid=?");) {
                statement.setInt(1, sid);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
