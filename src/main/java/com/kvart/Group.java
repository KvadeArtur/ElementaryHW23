package com.kvart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private String id;
    private String name;
    private List<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(String id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public void addStudent (Student student) throws SQLException {
        students.add(student);
        student.addGroupForClass(new Group(this.name));
        DataBases dataBases = new DataBases();
        dataBases.addGroupStudent(new GroupStudent(generateId(), this.id, student.getId()));
    }

    public void addStudentForClass (Student student) {
        students.add(student);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
