package persistence.impl;

import entity.Model;
import entity.Valve;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ModelImpl {

    private static List<Model> models = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(ActuatorImpl.class);
    private Scanner scanner;
    private Session session;

    void find(String tableName, String columnName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Insert " + columnName + " to find: ");
        scanner = new Scanner(System.in);
        String queryName = scanner.nextLine();
        Query queryFind = session.createQuery("FROM " + tableName + " WHERE " + columnName + " = :paramName");
        queryFind.setParameter("paramName", queryName);
        models = queryFind.list();
        printModel(models);
        scanner.close();
        session.close();
    }


    List findAll(String tableName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query queryFindAll = session.createQuery("FROM " + tableName);
        models = queryFindAll.list();
        session.close();
        return models;
    }

    void add(Model model) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Insert article of new " + model.getClass().getSimpleName() + ":");
        scanner = new Scanner(System.in);
        model.setArticle(scanner.nextLine());
        session.save(model);
        session.getTransaction().commit();
        scanner.close();
        session.close();
    }

    void del(String tableName, String columnName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Insert " + columnName + " for deleting: ");
        scanner = new Scanner(System.in);
        String deleteName = scanner.nextLine();
        Query deleteQuery = session.createQuery("DELETE " + tableName + " WHERE " + columnName + " = :paramName");
        deleteQuery.setParameter("paramName", deleteName);
        int result = deleteQuery.executeUpdate();
        if (result > 0) {
            System.out.println(result + " element(s) has been deleted!");
        }
        session.getTransaction().commit();
        scanner.close();
        session.close();
    }

    void update() {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Insert name for updating: ");
        scanner = new Scanner(System.in);
        String updateName = scanner.nextLine();
        Query queryUpdate = session.createQuery("FROM Actuator WHERE article = :paramName");
        queryUpdate.setParameter("paramName", updateName);
        models = queryUpdate.list();
        System.out.println("Insert new name: ");
        scanner = new Scanner(System.in);
        String rename = scanner.nextLine();

        for (Model modelUp : models) {
            modelUp.setArticle(rename);
            session.update(modelUp);
        }
        session.getTransaction().commit();
        System.out.println(models.size() + " element(s) has been updated!");
        scanner.close();
        session.close();
    }

    public List<Model> findModelByComboBox(List<Object> listComboBoxes, Model tableName) {


        //Double desiredKvs, Integer desiredDn, Integer desiredPorts, String desiredPn, String desiredConn, String desiredType

        StringBuilder strQuery = new StringBuilder("FROM " + tableName.getClass().getSimpleName());
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();


        for (int i = 0; i < listComboBoxes.size(); i++) {

            if (listComboBoxes.get(i) != null) {
                if (strQuery.toString().equals("FROM " + tableName.getClass().getSimpleName())) {
                    strQuery.append(" WHERE " +  = :paramKvs");
                } else {
                    strQuery.append(" AND kvs = :paramKvs");
                }
            }


        }


        if (desiredKvs != null) {
            if (strQuery.toString().equals("FROM Valve")) {
                strQuery.append(" WHERE kvs = :paramKvs");
            } else {
                strQuery.append(" AND kvs = :paramKvs");
            }
        }

        if (desiredDn != null) {
            if (strQuery.toString().equals("FROM Valve")) {
                strQuery.append(" WHERE dn = :paramDn");
            } else {
                strQuery.append(" AND dn = :paramDn");
            }
        }

        if (desiredPorts != null) {
            if (strQuery.toString().equals("FROM Valve")) {
                strQuery.append(" WHERE ports = :paramPorts");
            } else {
                strQuery.append(" AND ports = :paramPorts");
            }
        }

        if (desiredPn != null) {
            if (strQuery.toString().equals("FROM Valve")) {
                strQuery.append(" WHERE pn = :paramPn");
            } else {
                strQuery.append(" AND pn = :paramPn");
            }
        }

        if (desiredConn != null) {
            if (strQuery.toString().equals("FROM Valve")) {
                strQuery.append(" WHERE connection = :paramConnection");
            } else {
                strQuery.append(" AND connection = :paramConnection");
            }
        }

        if (desiredType != null) {
            if (strQuery.toString().equals("FROM Valve")) {
                strQuery.append(" WHERE type = :paramType");
            } else {
                strQuery.append(" AND type = :paramType");
            }
        }

        Query queryFind = session.createQuery(strQuery.toString());

        if (desiredKvs != null) {
            queryFind.setParameter("paramKvs", desiredKvs);
        }
        if (desiredDn != null) {
            queryFind.setParameter("paramDn", desiredDn);
        }
        if (desiredPorts != null) {
            queryFind.setParameter("paramPorts", desiredPorts);
        }
        if (desiredPn != null) {
            queryFind.setParameter("paramPn", desiredPn);
        }
        if (desiredConn != null) {
            queryFind.setParameter("paramConnection", desiredConn);
        }
        if (desiredType != null) {
            queryFind.setParameter("paramType", desiredType);
        }

        List<Valve> findedValves = queryFind.list();
        session.close();
        return findedValves;
    }


    private void printModel(List<Model> models) {
        for (Model model : models) {
            System.out.println(model.toString());
        }
    }
}
