package com.kvart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student {

    private String id;
    private String name;
    private List<Group> groups = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String id, String name, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.groups = groups;
    }

    public void addGroup (Group group) throws SQLException {
        groups.add(group);
        group.addStudentForClass(new Student(this.name));
        DataBases dataBases = new DataBases();
        dataBases.addGroupStudent(new GroupStudent(generateId(), group.getId(), this.id));
    }

    public void addGroupForClass (Group group) {
        groups.add(group);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groups=" + groups +
                '}';
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
