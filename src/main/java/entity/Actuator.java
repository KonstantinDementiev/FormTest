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
    private String endPos;
    private String timePos;
    private String power;
    private String stroke;
    private String temperature;
    private double price;

    @ManyToMany
    @JoinTable(name = "kit", joinColumns = {@JoinColumn(name = "actuator_art")}, inverseJoinColumns = {@JoinColumn(name = "valve_art")})
    private Set<Valve> valves = new HashSet<Valve>();


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

    public String getEndPos() {
        return endPos;
    }

    public void setEndPos(String endPos) {
        this.endPos = endPos;
    }

    public String getTimePos() {
        return timePos;
    }

    public void setTimePos(String timePos) {
        this.timePos = timePos;
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

    public Set<Valve> getValves() {
        return valves;
    }

    public void setValves(Set<Valve> valves) {
        this.valves = valves;
    }


    @Override
    public String toString() {
        return "" + article;
    }
}