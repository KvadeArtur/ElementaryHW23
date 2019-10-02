package com.kvart;

public class GroupStudent {

    private String id;
    private String id_group;
    private String id_student;

    public GroupStudent() {
    }

    public GroupStudent(String id, String id_group, String id_student) {
        this.id = id;
        this.id_group = id_group;
        this.id_student = id_student;
    }

    public String getId() {
        return id;
    }

    public String getId_group() {
        return id_group;
    }

    public String getId_student() {
        return id_student;
    }
}
