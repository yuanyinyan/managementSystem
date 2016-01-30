package com.jdbc.action;

import com.jdbc.model.Student;
import com.jdbc.model.StudentDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 控制层
 * <p/>
 * Created by yuanyin on 16/1/30.
 */
public class StudentAction {

    public void add(Student student) throws SQLException {
        StudentDao studentDao = new StudentDao();
        studentDao.addStudent(student);
    }

    public void edit(Student student) throws SQLException {
        StudentDao dao = new StudentDao();
        dao.updateStudent(student);
    }

    public void del(int id) throws SQLException {
        StudentDao dao = new StudentDao();
        dao.delStudent(id);
    }

    public List<Student> query() throws SQLException {
        StudentDao dao = new StudentDao();
        return dao.query();
    }

    public List<Student> query(List<Map<String, Object>> params) throws SQLException {
        StudentDao dao = new StudentDao();
        return dao.query(params);
    }

    public Student get(int id) throws SQLException {
        StudentDao dao = new StudentDao();
        return dao.getStudent(id);
    }
}
