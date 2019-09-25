package com.kvart;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public void addStudent (Student student) {
        students.add(student);
        student.addGroupForClass(new Group(this.name));
        DataBases dataBases = new DataBases();
        dataBases.addGroupStudent(new GroupStudent(this.id, student.getId()));
    }

    public void addStudentForClass (Student student) {
        students.add(student);
    }

    public int getId() {
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
}
