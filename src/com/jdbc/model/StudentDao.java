package com.jdbc.model;

import com.jdbc.db.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据访问方法
 * <p/>
 * Created by yuanyin on 16/1/30.
 */
public class StudentDao {

    public void addStudent(Student student) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " INSERT INTO student" +
                " (user_name,sex,age,birthday,email,mobile," +
                " create_user,create_date,update_user,update_date,isdel)" +
                " VALUES(" +
                " ?,?,?,?,?,?,?,current_date,?,current_date,?)";
        PreparedStatement ptmt = connection.prepareStatement(sql);

        ptmt.setString(1, student.getUser_name());
        ptmt.setInt(2, student.getSex());
        ptmt.setInt(3, student.getAge());
        ptmt.setDate(4, new Date(student.getBirthday().getTime()));
        ptmt.setString(5, student.getEmail());
        ptmt.setString(6, student.getMobile());
        ptmt.setString(7, student.getCreate_user());
        ptmt.setString(8, student.getUpdate_user());
        ptmt.setInt(9, 0);
        ptmt.execute();
    }

    public void updateStudent(Student student) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " UPDATE student" +
                " SET user_name =?,sex=?,age=?,birthday=?,email=?,mobile=?," +
                " update_user=?,update_date=current_date,isdel=?" +
                " WHERE id=?";
        PreparedStatement ptmt = connection.prepareStatement(sql);

        ptmt.setString(1, student.getUser_name());
        ptmt.setInt(2, student.getSex());
        ptmt.setInt(3, student.getAge());
        ptmt.setDate(4, new Date(student.getBirthday().getTime()));
        ptmt.setString(5, student.getEmail());
        ptmt.setString(6, student.getMobile());
        ptmt.setString(7, student.getUpdate_user());
        ptmt.setInt(8, student.getIsdel());
        ptmt.setInt(9, student.getId());
        ptmt.execute();
    }

    public void delStudent(int id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement ptmt = connection.prepareStatement(sql);

        ptmt.setInt(1, id);
        ptmt.execute();
    }

    public List<Student> query() throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        Connection connection = DBUtil.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT id,user_name,age FROM student");

        Student student;
        while (resultSet.next()) {
            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setUser_name(resultSet.getString("user_name"));
            student.setAge(resultSet.getInt("age"));
            studentList.add(student);
        }
        return studentList;
    }

    public List<Student> query(String name, String mobile) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM student WHERE user_name LIKE ? AND mobile LIKE ?";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setString(1, "%" + name + "%");
        ptmt.setString(2, "%" + mobile + "%");
        ResultSet resultSet = ptmt.executeQuery();

        return getStudentList(resultSet);
    }

    public List<Student> query(java.util.Date birthday) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM student WHERE birthday=?";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setDate(1, new Date(birthday.getTime()));
        ResultSet resultSet = ptmt.executeQuery();

        return getStudentList(resultSet);
    }

    public List<Student> query(List<Map<String, Object>> params) throws SQLException {
        Connection connection = DBUtil.getConnection();
        StringBuilder sb = new StringBuilder();
        //SELECT * FROM student WHERE 1=1 AND user_name="小美" AND… …
        //SELECT * FROM student WHERE 1=0 OR user_name="小美" OR… …
        sb.append("SELECT * FROM student WHERE 1=0 ");

        if (params != null && params.size() > 0) {
            for (Map<String, Object> param : params) {
                sb.append("OR ").append(param.get("name")).append(" ").append(param.get("rela")).append("").append
                        (param.get("value"));
            }
        }

        PreparedStatement ptmt = connection.prepareStatement(sb.toString());
        ResultSet resultSet = ptmt.executeQuery();

        return getStudentList(resultSet);
    }

    public Student getStudent(int id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = " SELECT * FROM student WHERE id=?";
        PreparedStatement ptmt = connection.prepareStatement(sql);
        ptmt.setInt(1, id);
        ResultSet resultSet = ptmt.executeQuery();

        List<Student> studentList = getStudentList(resultSet);
        if (studentList.size() > 0) {
            return studentList.get(0);
        } else {
            return null;
        }
    }

    private List<Student> getStudentList(ResultSet resultSet) throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        Student student;
        while (resultSet.next()) {
            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setUser_name(resultSet.getString("user_name"));
            student.setSex(resultSet.getInt("sex"));
            student.setAge(resultSet.getInt("age"));
            student.setBirthday(resultSet.getDate("birthday"));
            student.setEmail(resultSet.getString("email"));
            student.setMobile(resultSet.getString("mobile"));
            student.setCreate_user(resultSet.getString("create_user"));
            student.setCreate_data(resultSet.getDate("create_date"));
            student.setUpdate_user(resultSet.getString("update_user"));
            student.setCreate_data(resultSet.getDate("update_date"));
            student.setIsdel(resultSet.getInt("isdel"));
            studentList.add(student);
        }
        return studentList;
    }

}
