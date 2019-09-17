package persistence.impl;

import entity.Valve;
import org.hibernate.Session;
import utils.HibernateSessionFactory;

import java.util.List;

public class ValveImpl extends ModelImpl {


    private ModelImpl modelImpl = new ModelImpl();

    public Valve findValveByArticle(String article) {
        return (Valve) modelImpl.find("Valve", "article", article);
    }

    public List<Valve> findAllValve() {
        return modelImpl.findAll("Valve");
    }

    public void insertValve(List <Valve> valve) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(valve);
        session.getTransaction().commit();
        session.close();

    }

    public void delValveByArticle() {
        modelImpl.del("Valve", "article");
    }


    public List<Valve> findValveByComboBox(Double desiredKvs, Integer desiredDn, Integer desiredPorts, String desiredPn, String desiredConn, String desiredType) {

        Object[][] listComboBoxes = {
                {desiredKvs, "kvs"},
                {desiredDn, "dn"},
                {desiredPorts, "ports"},
                {desiredPn, "pn"},
                {desiredConn, "connection"},
                {desiredType, "type"}
        };
        return modelImpl.findModelByComboBox(listComboBoxes, "Valve");
    }


}
