package persistence.impl;

import entity.Valve;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;
import java.util.List;

public class ValveImpl extends ModelImpl {


    private String tableName = "Valve";
    private ModelImpl modelImpl = new ModelImpl();

    public void findValveByArticle() {
        modelImpl.find(tableName, "article");
    }

    public List<Valve> findAllValve() {
        return modelImpl.findAll(tableName);
    }

    public void addValve() {
        Valve valve = new Valve();
        modelImpl.add(valve);
    }

    public void delValveByArticle() {
        modelImpl.del(tableName, "article");
    }


    public List<Valve> findValveByComboBox(Double desiredKvs, Integer desiredDn, Integer desiredPorts, String desiredPn, String desiredConn, String desiredType) {

        StringBuilder strQuery = new StringBuilder("FROM Valve");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

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


}
