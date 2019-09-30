package persistence.impl;

import entity.Valve;
import org.hibernate.Session;
import utils.HibernateSessionFactory;

import java.util.List;
import java.util.Set;

public class ValveImpl extends ModelImpl {


    private ModelImpl modelImpl = new ModelImpl();

    public Valve findValveByArticle(String article) {
        return (Valve) modelImpl.find("Valve", "article", article);
    }

    public Set<Valve> findAllValve() {
        return modelImpl.findAll("Valve");
    }

    public void insertValve(Valve valve) {
        modelImpl.insertModel(valve);
    }

    public void updateAllValves(Set<Valve> valves) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        delAllValves();
        for (Valve list : valves) {
            session.save(list);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void delValveByArticle() {
        modelImpl.delByArticle("Valve", "article");
    }

    public void delAllValves() {
        modelImpl.delAll("Valve");
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
