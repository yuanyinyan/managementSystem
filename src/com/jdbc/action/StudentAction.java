package com.jdbc.action;

import com.jdbc.model.Student;
import com.jdbc.model.StudentDao;

import java.sql.SQLException;
import java.time.*;
import java.util.*;

/**
 * Created by yuanyin on 16/1/30.
 */
public class StudentAction {
    public static void main(String[] args) throws SQLException {
        StudentDao studentDao = new StudentDao();
        Student student = new Student();
        student.setUser_name("小美");
        student.setSex(1);
        student.setAge(15);
        student.setBirthday(new Date());
        student.setMobile("18999990678");
        student.setEmail("1666666@qq.com");
        student.setCreate_user("admin");
        student.setUpdate_user("admin");
        student.setIsdel(1);

//        student.setId(11);
//        studentDao.delStudent(11);
//        studentDao.updateStudent(student);

//        studentDao.addStudent(student);

//        List<Student> studentList=studentDao.query();
//        for (Student student1 : studentList) {
//            System.out.println(student1.getUser_name());
//        }

//        System.out.println(studentDao.query("美","189"));

        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", "user_name");
        param.put("rela", "like");
        param.put("value", "'%美%'");
        params.add(param);
        param = new HashMap<String, Object>();
        param.put("name", "mobile");
        param.put("rela", "like");
        param.put("value", "'%0000%'");
        params.add(param);
//        System.out.println(studentDao.query(params));

        LocalDateTime localDateTime=LocalDateTime.of(2016,1,30,0,0,0);
        ZoneId zoneId= ZoneId.systemDefault();
        Instant instant=localDateTime.atZone(zoneId).toInstant();
        Date date=Date.from(instant);
        System.out.println(studentDao.query(date));

    }
}
