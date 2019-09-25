package com.kvart;

import javax.persistence.*;

@Entity
@Table(name = "groups_students")
public class GroupStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int idGroup;
    private int idStudent;

    public GroupStudent() {
    }

    public GroupStudent(int idGroup, int idStudent) {
        this.idGroup = idGroup;
        this.idStudent = idStudent;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public int getIdStudent() {
        return idStudent;
    }
}
