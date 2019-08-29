package persistence.impl;

import entity.Model;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModelImpl {

    private static List<Model> models = new ArrayList<Model>();
    private static final Logger LOG = LoggerFactory.getLogger(ActuatorImpl.class);
    private Scanner scanner;
    private Session session;

    public void find(String tableName, String columnName) {
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

    public void findAll(String tableName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query queryFindAll = session.createQuery("FROM " + tableName);
        models = queryFindAll.list();
        printModel(models);
        session.close();
    }

    public void add(Model model) {
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

    public void del(String tableName, String columnName) {
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

    public void update() {
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


    public void crud(String tableName, String columnName) {
        Model model= new Model();
        try {
            System.out.println("Please, choose your operation: 1 - Find, 2 - Find all, 3 - Add, 4 - Delete, 5 - Update");
            scanner = new Scanner(System.in);
            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    find(tableName, columnName);
                    break;

                case 2:
                    findAll(columnName);
                    break;

                case 3:
                    add(model);
                    break;

                case 4:
                    del(tableName, columnName);
                    break;

                case 5:
                    update();
                    break;

                default:
                    System.out.println("You did not enter a number from 1 to 4");
                    scanner.close();
                    break;
            }

            System.out.println("Transaction successful!");
            LOG.info("Transaction successful!");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.out.println("Transaction failed!");
            LOG.error("Transaction failed!", e);
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.shutdown();
        }
    }

    private void printModel(List<Model> models) {
        for (Model model : models) {
            System.out.println(model.toString());
        }
    }
}
