package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Valve extends Model {

    @Id
    private String article;
    private double kvs;
    private Integer dn;
    private Integer ports;
    private String pn;
    private String connection;
    private String type;
    private String temperature;
    private double price;

    @ManyToMany(mappedBy = "valves")
    private Set<Actuator> actuators = new HashSet<Actuator>();


    public Valve() {
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public double getKvs() {
        return kvs;
    }

    public void setKvs(double kvs) {
        this.kvs = kvs;
    }

    public Integer getDn() {
        return dn;
    }

    public void setDn(Integer dn) {
        this.dn = dn;
    }

    public Integer getPorts() {
        return ports;
    }

    public void setPorts(Integer ports) {
        this.ports = ports;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Actuator> getActuators() {
        return actuators;
    }

    public void setActuators(Set<Actuator> actuators) {
        this.actuators = actuators;
    }

    @Override
    public String toString() {
        return "Valve{" + "article='" + article + '\'' + ", kvs=" + kvs + ", dn=" + dn + ", ports=" + ports + ", pn='" + pn + '\'' + ", connection='" + connection + '\'' + ", type='" + type + '\'' + ", temperature='" + temperature + '\'' + ", price=" + price + ", actuators=" + actuators + '}';
    }
}