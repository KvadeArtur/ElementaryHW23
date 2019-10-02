package com.kvart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBases {

    private Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DataBases() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/GroupStudent",
                "postgres", " ");
        maybeCreateGroupStudentTable();
        maybeCreateGroupsTable();
        maybeCreateStudentsTable();
    }

    private void maybeCreateGroupStudentTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS group_student (\n" +
                    "_id uuid PRIMARY KEY,\n" +
                    "id_group varchar(100), \n" +
                    "id_student varchar(100) \n" +
                    ");"
            );
        }
    }

    private void maybeCreateStudentsTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS students (\n" +
                    "_id uuid PRIMARY KEY, \n" +
                    "name varchar(100) \n" +
                    ");\n"
//                    "alter table students \n" +
//                    "add foreign key (_id) \n" +
//                    "references group_student(id_student);"
            );
        }
    }

    private void maybeCreateGroupsTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS groups (\n" +
                    "_id uuid PRIMARY KEY,\n" +
                    "name varchar(100) \n" +
                    ");\n"
//                    "alter table groups\n" +
//                    "add foreign key (_id)\n" +
//                    "references group_student(id_group);"
            );
        }
    }

    public void addGroup(Group group) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO groups VALUES ('%s', '%s');",
                    group.getId(), group.getName());
            statement.execute(request);
        }
    }

    public void addStudent(Student student) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO students VALUES ('%s', '%s');",
                    student.getId(), student.getName());
            statement.execute(request);
        }
    }

    public void addGroupStudent(GroupStudent groupStudent) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO group_student VALUES ('%s', '%s', '%s');",
                    groupStudent.getId(), groupStudent.getId_group(), groupStudent.getId_student());
            statement.execute(request);
        }
    }

    public Group getGroup(String id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM groups WHERE _id = '%s';", id);
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                String idg = resultSet.getString("_id");
                String nameg = resultSet.getString("name");
                return new Group(idg, nameg);
            }
        }
        return null;
    }

    public Group getGroupName(String name) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM groups WHERE name = '%s';", name);
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                String idg = resultSet.getString("_id");
                String nameg = resultSet.getString("name");
                return new Group(idg, nameg);
            }
        }
        return null;
    }

    public Student getStudent(String id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM students WHERE _id = '%s';", id);
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                String ids = resultSet.getString("_id");
                String names = resultSet.getString("name");
                //List<Group> groups = getGroupInStudents(new Student(ids, names));
                return new Student(ids, names);
            }
        }
        return null;
    }

    public Student getStudentName(String name) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM students WHERE name = '%s';", name);
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                String ids = resultSet.getString("_id");
                String names = resultSet.getString("name");
                return new Student(ids, names);
            }
        }
        return null;
    }

    public void removeAll() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM groups;");
            System.out.println("Deleted " + count + " rows from table groups");
        }

        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM students;");
            System.out.println("Deleted " + count + " rows from table students");
        }
    }

    public List<Student> getStudentsInGroup(Group group) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM group_student WHERE id_group = '%s';", group.getId());
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                String idStudent = resultSet.getString("id_student");
                students.add(getStudent(idStudent));
            }
        }
        return students;
    }

    public List<Group> getGroupInStudents(Student student) throws SQLException {
        List<Group> groups = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM group_student WHERE id_student = '%s';", student.getId());
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                String idGroup = resultSet.getString("id_group");
                groups.add(getGroup(idGroup));
            }
        }
        return groups;
    }

}
