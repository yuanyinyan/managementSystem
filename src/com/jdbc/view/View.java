package com.jdbc.view;

import com.jdbc.action.StudentAction;
import com.jdbc.model.Student;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 视图层
 * <p/>
 * Created by yuanyin on 16/1/31.
 */
public class View {
    public static final String CONTEXT = "功能列表:\n" +
            "[MAIN/M]:主菜单\n" +
            "[QUERY/Q]:查看全部学生的信息\n" +
            "[GET/G]:查看某个学生的详细信息\n" +
            "[ADD/A]:添加学生信息\n" +
            "[UPDATE/U]:更新学生信息\n" +
            "[DELETE/D]:删除学生信息\n" +
            "[SEARCH/S]:查询学生信息(根据姓名,手机号来查询)\n" +
            "[EXIT/E]:退出系统\n" +
            "[BREAK/B]:退出当前功能,返回主菜单";

    public static final String OPERATION_MAIN = "MAIN";
    public static final String OPERATION_QUERY = "QUERY";
    public static final String OPERATION_GET = "GET";
    public static final String OPERATION_ADD = "ADD";
    public static final String OPERATION_UPDATE = "UPDATE";
    public static final String OPERATION_DELETE = "DELETE";
    public static final String OPERATION_SEARCH = "SEARCH";
    public static final String OPERATION_EXIT = "EXIT";
    public static final String OPERATION_BREAK = "BREAK";

    private Scanner scanner = new Scanner(System.in);
    private Student student = new Student();
    private StudentAction action = new StudentAction();
    private String previous = OPERATION_MAIN;
    private int step = 0;

    public static void main(String[] args) {
        View view = new View();
        view.start();
    }

    public void start() {
        System.out.println("欢迎来到学生管理系统!");
        System.out.println(CONTEXT);
        while (scanner.hasNext()) {
            String in = scanner.next();
            if (in.equals(OPERATION_EXIT) || in.toUpperCase().equals(OPERATION_EXIT.substring(0, 1))) {
                System.out.println("您已成功退出学生管理系统.");
                break;
            } else if (in.equals(OPERATION_MAIN) || in.toUpperCase().equals(OPERATION_MAIN.substring(0, 1))) {
                main();
            } else if (in.equals(OPERATION_BREAK) || in.toUpperCase().equals(OPERATION_BREAK.substring(0, 1))) {
                step = 0;
                previous = OPERATION_MAIN;
            } else if (isOrder(in, OPERATION_UPDATE)) {
                update(in);
            } else if (isOrder(in, OPERATION_QUERY)) {
                query();
            } else if (isOrder(in, OPERATION_SEARCH)) {
                search(in);
            } else if (isOrder(in, OPERATION_DELETE)) {
                delete(in);
            } else if (isOrder(in, OPERATION_GET)) {
                get(in);
            } else if (isOrder(in, OPERATION_ADD)) {
                add(in);
            } else {
                System.out.println("您输入的指令有误,请重新输入");
            }
        }
    }

    public boolean isOrder(String in, String order) {
        return (previous.equals(OPERATION_MAIN) || previous.equals(order))
                && (in.equals(order) || in.toUpperCase().equals(order.substring(0, 1)) || previous.equals(order));
    }

    public void main() {
        step = 0;
        previous = OPERATION_MAIN;
        System.out.println(CONTEXT);
    }

    public void query() {
        try {
            List<Student> studentList = action.query();
            for (Student student1 : studentList) {
                System.out.println(student1.getId() + ",姓名:" + student1.getUser_name());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void get(String in) {
        previous = OPERATION_GET;
        if (previous.equals(OPERATION_GET)) {
            step++;
        }
        switch (step) {
            case 1:
                System.out.println("请输入要查询的学生ID");
                break;
            case 2:
                try {
                    int id = Integer.valueOf(in);
                    if (id < 1) {
                        throw new NumberFormatException();
                    }
                    Student student1 = action.get(id);
                    if (student1 != null) {
                        System.out.println(student);
                        step = 0;
                        previous = OPERATION_MAIN;
                        student = new Student();
                    } else {
                        System.out.println("您查询的学生ID不存在,请重新输入");
                        step--;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("您输入的格式有误,请重新输入正整数ID");
                    step--;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void add(String in) {
        previous = OPERATION_ADD;
        if (previous.equals(OPERATION_ADD)) {
            step++;
        }
        switch (step) {
            case 1:
                System.out.println("请输入学生的[姓名]");
                break;
            case 2:
                student.setUser_name(in);
                System.out.println("请输入学生的[年龄]");
                break;
            case 3:
                try {
                    int age = Integer.valueOf(in);
                    if (age < 0) {
                        throw new NumberFormatException();
                    }
                    student.setAge(age);
                    System.out.println("请输入学生的[性别],0:表示女,1表示男");
                } catch (NumberFormatException e) {
                    System.out.println("您输入的格式有误,请重新输入");
                    step--;
                }
                break;
            case 4:
                try {
                    int sex = Integer.valueOf(in);
                    if (1 == sex || 0 == sex) {
                        student.setSex(sex);
                        System.out.println("请输入学生的[生日],格式如:yyyy-MM-dd");
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("您输入的格式有误,请重新输入");
                    step--;
                }
                break;
            case 5:
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    student.setBirthday(format.parse(in));
                    System.out.println("请输入学生的[邮箱]");
                } catch (ParseException e) {
                    System.out.println("您输入的格式有误,请重新输入");
                    step--;
                }
                break;
            case 6:
                student.setEmail(in);
                System.out.println("请输入学生的[手机号]");
                break;
            case 7:
                student.setMobile(in);
                try {
                    action.add(student);
                    System.out.println("新增学生成功!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("新增学生失败!");
                } finally {
                    step = 0;
                    previous = OPERATION_MAIN;
                    student = new Student();
                }
                break;
        }
    }

    public void update(String in) {
        previous = OPERATION_UPDATE;
        if (previous.equals(OPERATION_UPDATE)) {
            step++;
        }
        Student updateStu = new Student();
        switch (step) {
            case 1:
                System.out.println("请输入要更新的学生编号:");
                break;
            case 2:
                try {
                    int id = Integer.valueOf(in);
                    if (id < 1) {
                        throw new NumberFormatException();
                    }
                    updateStu = action.get(id);
                    if (updateStu != null) {
                        System.out.println("姓名(如果不更新该字段,则输入null)");
                    } else {
                        System.out.println("您输入的学生ID不存在,请重新输入");
                        step--;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("您输入的格式有误,请重新输入正整数ID");
                    step--;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                if (!in.equals("null")) {
                    updateStu.setUser_name(in);
                }
                System.out.println("年龄:");
                break;
            case 4:
                if (!in.equals("null")) {
                    try {
                        int age = Integer.valueOf(in);
                        if (age < 0) {
                            throw new NumberFormatException();
                        }
                        updateStu.setAge(age);
                        System.out.println("性别:(0代表女,1代表男)");
                    } catch (NumberFormatException e) {
                        System.out.println("您输入的格式有误,请重新输入");
                        step--;
                    }
                } else {
                    System.out.println("性别:(0代表女,1代表男)");
                }
                break;
            case 5:
                if (!in.equals("null")) {
                    try {
                        int sex = Integer.valueOf(in);
                        if (sex == 0 || sex == 1) {
                            updateStu.setSex(sex);
                            System.out.println("生日,格式如:yyyy-MM-dd");
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("您输入的格式有误,请重新输入");
                        step--;
                    }
                } else {
                    System.out.println("生日,格式如:yyyy-MM-dd");
                }
                break;
            case 6:
                if (!in.equals("null")) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        updateStu.setBirthday(format.parse(in));
                        System.out.println("邮箱:");
                    } catch (ParseException e) {
                        System.out.println("您输入的格式有误,请重新输入");
                        step--;
                    }
                } else {
                    System.out.println("邮箱:");
                }
                break;
            case 7:
                if (!in.equals("null")) {
                    updateStu.setEmail(in);
                }
                System.out.println("手机号:");
                break;
            case 8:
                if (!in.equals("null")) {
                    updateStu.setMobile(in);
                }
                try {
                    action.edit(updateStu);
                    System.out.println("更新成功!");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    step = 0;
                    previous = OPERATION_MAIN;
                }
                break;
        }
    }

    public void delete(String in) {
        previous = OPERATION_DELETE;
        if (previous.equals(OPERATION_DELETE)) {
            step++;
        }
        switch (step) {
            case 1:
                System.out.println("请输入要删除的学生编号,可以删除多个,用逗号分隔,如1,2");
                break;
            case 2:
                String[] idList = in.split(",");
                try {
                    for (String id : idList) {
                        action.del(Integer.valueOf(id));
                    }
                    System.out.println("删除成功");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    step = 0;
                    previous = OPERATION_MAIN;
                }
                break;
        }
    }

    public void search(String in) {
        previous = OPERATION_SEARCH;
        if (previous.equals(OPERATION_SEARCH)) {
            step++;
        }
        switch (step) {
            case 1:
                System.out.println("可以根据姓名查询");
                System.out.println("或根据姓名和手机号查询,以逗号分隔,如:小明,12345678900");
                break;
            case 2:
                String[] infoList = in.split(",");
                String name = "";
                String mobile = " ";
                switch (infoList.length) {
                    case 1:
                        name = infoList[0];
                        break;
                    case 2:
                        name = infoList[0];
                        mobile = infoList[1];
                        break;
                }
                List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("name", "user_name");
                param.put("rela", "like");
                param.put("value", "'%" + name + "%'");
                params.add(param);
                param = new HashMap<String, Object>();
                param.put("name", "mobile");
                param.put("rela", "like");
                param.put("value", "'%" + mobile + "%'");
                params.add(param);
                try {
                    List<Student> studentList = action.query(params);
                    System.out.println(studentList);
                    step = 0;
                    previous = OPERATION_MAIN;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
