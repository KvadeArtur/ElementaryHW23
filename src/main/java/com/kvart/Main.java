package com.kvart;

public class Main {
    public static void main(String[] args) {

        DataBases dataBases = new DataBases();

        dataBases.removeAllStudent();

        dataBases.addGroup(new Group("Java"));
        dataBases.addGroup(new Group("Cpp"));
        dataBases.addGroup(new Group("Javascript"));

        dataBases.addStudent(new Student("Alex"));
        dataBases.addStudent(new Student("Ivan"));
        dataBases.addStudent(new Student("Nikolay"));
        dataBases.addStudent(new Student("Sergey"));
        dataBases.addStudent(new Student("Semen"));

        dataBases.getGroup(1).addStudent(dataBases.getStudent("Alex"));
        dataBases.getGroup(1).addStudent(dataBases.getStudent("Nikolay"));
        dataBases.getGroup(1).addStudent(dataBases.getStudent("Semen"));

        dataBases.getGroup(2).addStudent(dataBases.getStudent("Ivan"));
        dataBases.getGroup(2).addStudent(dataBases.getStudent("Sergey"));
        dataBases.getGroup(2).addStudent(dataBases.getStudent("Semen"));

        dataBases.getGroup(3).addStudent(dataBases.getStudent("Alex"));
        dataBases.getGroup(3).addStudent(dataBases.getStudent("Ivan"));

        dataBases.getStudent("Nikolay").addGroup(dataBases.getGroup(3));
        dataBases.getStudent("Sergey").addGroup(dataBases.getGroup(1));

        System.out.println(dataBases.getGroupInStudents(dataBases.getStudent("Semen")));
        System.out.println(dataBases.getStudentsInGroup(dataBases.getGroup(1)));

    }
}
