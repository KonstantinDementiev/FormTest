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
    private String imageurl;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "valvesAct")
    private Set<Actuator> actuators = new HashSet<Actuator>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "valvesAda")
    private Set<Adapter> adapters = new HashSet<Adapter>();

    @ManyToOne
    @JoinColumn(name = "nutsart")
    private Nuts nuts;


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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Nuts getNuts() {
        return nuts;
    }

    public void setNuts(Nuts nuts_art) {
        this.nuts = nuts_art;
    }

    public Set<Actuator> getActuators() {
        return actuators;
    }

    public void setActuators(Set<Actuator> actuators) {
        this.actuators = actuators;
    }

    public Set<Adapter> getAdapters() {
        return adapters;
    }

    public void setAdapters(Set<Adapter> adapters) {
        this.adapters = adapters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valve valve = (Valve) o;
        if (this.hashCode() == valve.hashCode()) return true;
        return false;
    }

    @Override
    public int hashCode() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.article);
        buffer.append(this.kvs);
        buffer.append(this.dn);
        buffer.append(this.ports);
        buffer.append(this.pn);
        buffer.append(this.connection);
        buffer.append(this.type);
        buffer.append(this.temperature);
        buffer.append(this.price);
        return buffer.toString().hashCode();
    }

    @Override
    public String toString() {
        return "Valve{" + "article='" + article + '\'' + '}';
    }
}