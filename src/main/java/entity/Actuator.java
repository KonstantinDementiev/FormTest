package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actuator extends Model {

    @Id
    private String article;
    private String voltage;
    private String signaltype;
    private String nonc;
    private String endpos;
    private String timepos;
    private String power;
    private String stroke;
    private Double price;
    private String imageurl;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "valve_actuator", joinColumns = {@JoinColumn(name = "actuator_art")}, inverseJoinColumns = {@JoinColumn(name = "valve_art")})
    private Set<Valve> valves = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "actuators")
    private Set<Adapter> adapters = new HashSet<>();

    public Actuator() {
    }

    public Actuator(String article, String voltage, String signaltype, String nonc, String endpos, String timepos, String power, String stroke, Double price, String imageurl) {
        this.article = article;
        this.voltage = voltage;
        this.signaltype = signaltype;
        this.nonc = nonc;
        this.endpos = endpos;
        this.timepos = timepos;
        this.power = power;
        this.stroke = stroke;
        this.price = price;
        this.imageurl = imageurl;
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

    public String getSignaltype() {
        return signaltype;
    }

    public void setSignaltype(String signal) {
        this.signaltype = signal;
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

    public Set<Valve> getValves() {
        return valves;
    }

    public void setValves(Set<Valve> valvesAct) {
        this.valves = valvesAct;
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
        buffer.append(this.signaltype);
        buffer.append(this.nonc);
        buffer.append(this.endpos);
        buffer.append(this.timepos);
        buffer.append(this.power);
        buffer.append(this.stroke);
        buffer.append(this.price);
        buffer.append(this.imageurl);
        return buffer.toString().hashCode();
    }

    @Override
    public String toString() {
        return "Actuator{" +
                "article='" + article + '\'' +
                ", voltage='" + voltage + '\'' +
                ", signaltype='" + signaltype + '\'' +
                ", nonc='" + nonc + '\'' +
                ", endpos='" + endpos + '\'' +
                ", timepos='" + timepos + '\'' +
                ", power='" + power + '\'' +
                ", stroke='" + stroke + '\'' +
                ", price=" + price +
                '}' + "\n";
    }
}