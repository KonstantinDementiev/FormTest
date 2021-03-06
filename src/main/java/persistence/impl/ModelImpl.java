package persistence.impl;

import entity.Model;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.ModelRepository;
import utils.HibernateSessionFactory;

import java.util.*;

class ModelImpl implements ModelRepository {

    private static List<Model> models = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(ActuatorImpl.class);
    private Session session;

    Model find(String tableName, String columnName, String article) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query queryFind = session.createQuery("FROM " + tableName + " WHERE " + columnName + " = :paramName");
        queryFind.setParameter("paramName", article);
        models = queryFind.list();
        session.close();
        if (models.size() != 0) {
            return models.get(0);
        } else {
            return null;
        }
    }

    Set findAll(String tableName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query queryFindAll = session.createQuery("FROM " + tableName);
        models = queryFindAll.list();
        Set<Model> model = new HashSet<>(models);
        session.close();
        return model;
    }

    void insertModel(Model model) {

        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
    }

    void saveOrUpdateModel(Model model) {

        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(model);
        session.getTransaction().commit();
        session.close();
    }

    void del(Model model) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(model);
        session.getTransaction().commit();
        session.close();
    }

    void delByArticle(String tableName, String columnName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Insert " + columnName + " for deleting: ");
        Query deleteQuery = session.createQuery("DELETE " + tableName + " WHERE " + columnName + " = :paramName");
        deleteQuery.setParameter("paramName", "xxxxxxxx");
        int result = deleteQuery.executeUpdate();
        if (result > 0) {
            System.out.println(result + " element(s) has been deleted!");
        }
        session.getTransaction().commit();
        session.close();
    }

    void delAll(String tableName) {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteQuery = session.createQuery("DELETE FROM " + tableName);
        int result = deleteQuery.executeUpdate();
        if (result > 0) {
            System.out.println(result + " element(s) has been deleted!");
        }
        session.getTransaction().commit();
        session.close();
    }

    void update() {
        session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Insert name for updating: ");

        Query queryUpdate = session.createQuery("FROM Actuator WHERE article = :paramName");
        queryUpdate.setParameter("paramName", "ooooooooooo");
        models = queryUpdate.list();
        System.out.println("Insert new name: ");

        for (Model modelUp : models) {
            modelUp.setArticle("ttttttttt");
            session.update(modelUp);
        }
        session.getTransaction().commit();
        System.out.println(models.size() + " element(s) has been updated!");
        session.close();
    }

    List findModelByComboBox(Object[][] listComboBoxes, String tableName) {

        StringBuilder strQuery = new StringBuilder("FROM " + tableName);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        for (int i = 0; i < listComboBoxes.length; i++) {

            if (listComboBoxes[i][0] != null) {
                if (strQuery.toString().equals("FROM " + tableName)) {
                    strQuery.append(" WHERE ").append(listComboBoxes[i][1]).append(" = :param").append(listComboBoxes[i][1]);
                } else {
                    strQuery.append(" AND ").append(listComboBoxes[i][1]).append(" = :param").append(listComboBoxes[i][1]);
                }
            }
        }

        Query queryFind = session.createQuery(strQuery.toString());

        for (int i = 0; i < listComboBoxes.length; i++) {
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
