package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Actuator extends Model {

    @Id
    private String article;
    private String voltage;
    private String signal;
    private String nonc;
    private String endpos;
    private String timepos;
    private String power;
    private String stroke;
    private double price;
    private String imageurl;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "valve_actuator", joinColumns = {@JoinColumn(name = "actuator_art")}, inverseJoinColumns = {@JoinColumn(name = "valve_art")})
    private Set<Valve> valvesAct = new HashSet<Valve>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "actuators")
    private Set<Adapter> adapters = new HashSet<Adapter>();



    public Actuator() {
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getNonc() {
        return nonc;
    }

    public void setNonc(String nonc) {
        this.nonc = nonc;
    }

    public String getEndpos() {
        return endpos;
    }

    public void setEndpos(String endPos) {
        this.endpos = endPos;
    }

    public String getTimepos() {
        return timepos;
    }

    public void setTimepos(String timePos) {
        this.timepos = timePos;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
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

    public Set<Valve> getValvesAct() {
        return valvesAct;
    }

    public void setValvesAct(Set<Valve> valvesAct) {
        this.valvesAct = valvesAct;
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
        Actuator actuator = (Actuator) o;
        if (this.hashCode()== actuator.hashCode())return true;
        return false;
    }

    @Override
    public int hashCode() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.article);
        buffer.append(this.voltage);
        buffer.append(this.signal);
        buffer.append(this.nonc);
        buffer.append(this.endpos);
        buffer.append(this.timepos);
        buffer.append(this.power);
        buffer.append(this.price);
        return buffer.toString().hashCode();
    }

    @Override
    public String toString() {
        return "Actuator{" + "article='" + article + '\'' + ", voltage='" + voltage + '\'' + ", signal='" + signal + '\'' + ", nonc='" + nonc + '\'' + ", endpos='" + endpos + '\'' + ", timepos='" + timepos + '\'' + ", power='" + power + '\'' + ", stroke='" + stroke + '\'' + ", price=" + price + '}';
    }
}