package com.qfedu.one2many;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Demo {
    private static SqlSessionFactory factory = null;

    static {
        try {
            //想要使用Resources.getResourceAsReader()方法必须放在静态代码块中
            Reader resourceAsReader = Resources.getResourceAsReader("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(resourceAsReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据部门did查询员工---嵌套结果的第一种写法(多对一的关系)
    private static void findByDid1() {
        SqlSession session = factory.openSession();
        DepartmentDao mapper = session.getMapper(DepartmentDao.class);
        Department department = mapper.findByDid1(1);
        List<Employee> empList = department.getEmpList();
        for (Employee employee : empList) {
            System.out.println(employee);
        }
    }

    private static void findByDid2() {
        SqlSession session = factory.openSession();
        DepartmentDao mapper = session.getMapper(DepartmentDao.class);
        Department department = mapper.findByDid2(2);
        List<Employee> empList = department.getEmpList();
        for (Employee employee : empList) {
            System.out.println(empList);
        }
    }

    private static void findByDid3() {
        SqlSession session = factory.openSession();
        DepartmentDao mapper = session.getMapper(DepartmentDao.class);
        Department department = mapper.findByDid3(1);
        List<Employee> empList = department.getEmpList();
        for (Employee employee : empList) {
            System.out.println(employee);
        }
    }

    private static void findAll() {
        SqlSession session = factory.openSession();
        DepartmentDao mapper = session.getMapper(DepartmentDao.class);
        List<Department> all = mapper.findAll();
        for (Department department : all) {
            List<Employee> empList = department.getEmpList();
            for (Employee employee : empList) {
                System.out.println(employee);
            }
        }
    }


    //根据员工eid查询部门---嵌套结果的写法
    private static void findByEid() {
        SqlSession session = factory.openSession();
        //获取接口的代理对象
        EmployeeDao mapper = session.getMapper(EmployeeDao.class);
        //调用接口中的方法,如果接口中方法不存在会报错
        Employee employee = mapper.findByEid(2);
        Department department = employee.getDepartment();
        System.out.println(department);
    }

    //根据员工eid查询部门---嵌套查询的写法
    private static void findByEid1() {
        SqlSession session = factory.openSession();
        EmployeeDao mapper = session.getMapper(EmployeeDao.class);
        Employee employee = mapper.findByEid1(1);
        Department department = employee.getDepartment();
        System.out.println(department);
    }

    public static void main(String[] args) {
        //findByDid1();
        //findByDid2();
        //findByDid3();
        findAll();

        //findByEid();
        //findByEid1();
    }
}
