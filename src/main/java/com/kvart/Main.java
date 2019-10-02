package com.kvart;

import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {

        DataBases dataBases = new DataBases();

        dataBases.removeAll();

        dataBases.addGroup(new Group(generateId(),"Java"));
        dataBases.addGroup(new Group(generateId(),"Cpp"));
        dataBases.addGroup(new Group(generateId(),"Javascript"));

        dataBases.addStudent(new Student(generateId(),"Alex"));
        dataBases.addStudent(new Student(generateId(),"Ivan"));
        dataBases.addStudent(new Student(generateId(),"Nikolay"));
        dataBases.addStudent(new Student(generateId(),"Sergey"));
        dataBases.addStudent(new Student(generateId(),"Semen"));

        dataBases.getGroupName("Java").addStudent(dataBases.getStudentName("Alex"));
        dataBases.getGroupName("Java").addStudent(dataBases.getStudentName("Nikolay"));
        dataBases.getGroupName("Java").addStudent(dataBases.getStudentName("Semen"));

        dataBases.getGroupName("Cpp").addStudent(dataBases.getStudentName("Ivan"));
        dataBases.getGroupName("Cpp").addStudent(dataBases.getStudentName("Sergey"));
        dataBases.getGroupName("Cpp").addStudent(dataBases.getStudentName("Semen"));

        dataBases.getGroupName("Javascript").addStudent(dataBases.getStudentName("Alex"));
        dataBases.getGroupName("Javascript").addStudent(dataBases.getStudentName("Ivan"));

        dataBases.getStudentName("Nikolay").addGroup(dataBases.getGroupName("Javascript"));
        dataBases.getStudentName("Sergey").addGroup(dataBases.getGroupName("Java"));

        System.out.println(dataBases.getGroupInStudents(dataBases.getStudentName("Semen")));
        System.out.println(dataBases.getStudentsInGroup(dataBases.getGroupName("Java")));

    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
