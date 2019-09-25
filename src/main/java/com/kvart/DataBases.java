package com.kvart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DataBases {

    private SessionFactory sessionFactory;

    public DataBases() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void addGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
        }
    }

    public void addStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }
    }

    public void addGroupStudent(GroupStudent groupStudent) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(groupStudent);
            transaction.commit();
        }
    }

    public Group getGroup(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Group WHERE id = :id ", Group.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public Student getStudent(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Student WHERE name = :name ", Student.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
    }

    public void updateGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();
        }
    }

    public void updateStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        }
    }

    public int removeAllStudent() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", Student.class.getSimpleName());
            Query query = session.createQuery(hql);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    public List<Student> getStudentsInGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            List<GroupStudent> groupStudents = session.createQuery("FROM GroupStudent", GroupStudent.class).list();
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            List<Student> studentsGroup = new ArrayList<>();

            List<Integer> idStudent = new ArrayList<>();

            for (int i = 0; i < groupStudents.size(); i++) {
                if (groupStudents.get(i).getIdGroup() == group.getId()) {
                    idStudent.add(groupStudents.get(i).getIdStudent());
                }
            }

            for (int i = 0; i < idStudent.size(); i++) {
                for (int j = 0; j < students.size(); j++) {
                    if (idStudent.get(i) == students.get(j).getId()) {
                        studentsGroup.add(students.get(j));
                    }
                }
            }

            return studentsGroup;
        }
    }

    public List<Group> getGroupInStudents(Student student) {
        try (Session session = sessionFactory.openSession()) {
            List<GroupStudent> groupStudents = session.createQuery("FROM GroupStudent", GroupStudent.class).list();
            List<Group> groups = session.createQuery("FROM Group", Group.class).list();
            List<Group> groupsStudent = new ArrayList<>();

            List<Integer> idGroup = new ArrayList<>();

            for (int i = 0; i < groupStudents.size(); i++) {
                if (groupStudents.get(i).getIdStudent() == student.getId()) {
                    idGroup.add(groupStudents.get(i).getIdGroup());
                }
            }

            for (int i = 0; i < idGroup.size(); i++) {
                for (int j = 0; j < groups.size(); j++) {
                    if (idGroup.get(i) == groups.get(j).getId()) {
                        groupsStudent.add(groups.get(j));
                    }
                }
            }

            return groupsStudent;
        }
    }

}
