package persistence.impl;

import entity.Model;
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

    Model find(String tableName, String columnName, String article) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query queryFind = session.createQuery("FROM " + tableName + " WHERE " + columnName + " = :paramName");
        queryFind.setParameter("paramName", article);
        models = queryFind.list();
        session.close();
        return models.get(0);
    }


    List findAll(String tableName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query queryFindAll = session.createQuery("FROM " + tableName);
        models = queryFindAll.list();
        session.close();
        return models;
    }

//    void add(Model model) {
//        session = HibernateSessionFactory.getSessionFactory().openSession();
//        session.beginTransaction();
//        model.setArticle("0000000");
//        session.save(model);
//        session.getTransaction().commit();
//        session.close();
//    }

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

     List findModelByComboBox(Object[][] listComboBoxes, String tableName) {

        StringBuilder strQuery = new StringBuilder("FROM " + tableName);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();


        for (int i = 0; i < listComboBoxes.length-1; i++) {

            if (listComboBoxes[i][0] != null) {
                if (strQuery.toString().equals("FROM " + tableName)) {
                    strQuery.append(" WHERE ").append(listComboBoxes[i][1]).append(" = :param").append(listComboBoxes[i][1]);
                } else {
                    strQuery.append(" AND ").append(listComboBoxes[i][1]).append(" = :param").append(listComboBoxes[i][1]);
                }
            }
        }

        Query queryFind = session.createQuery(strQuery.toString());

        for (int i = 0; i < listComboBoxes.length-1; i++) {
            if (listComboBoxes[i][0] != null) {
                queryFind.setParameter("param" + listComboBoxes[i][1], listComboBoxes[i][0]);
            }
        }

        List<Model> findedModels = queryFind.list();
        session.close();
        return findedModels;
    }


    private void printModel(List<Model> models) {
        for (Model model : models) {
            System.out.println(model.toString());
        }
    }
}
