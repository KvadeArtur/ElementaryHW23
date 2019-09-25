package com.kvart;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Group> groups = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public void addGroup (Group group) {
        groups.add(group);
        group.addStudentForClass(new Student(this.name));
        DataBases dataBases = new DataBases();
        dataBases.addGroupStudent(new GroupStudent(group.getId(), this.id));
    }

    public void addGroupForClass (Group group) {
        groups.add(group);
    }

    public int getId() {
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
}
