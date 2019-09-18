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
    private String normalyopenclose;
    private String onoffendpos;
    private String timepos;
    private String power;
    private String stroke;
    private double price;
    private String actuatorimageurl;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "valve_actuator", joinColumns = {@JoinColumn(name = "actuator_art")}, inverseJoinColumns = {@JoinColumn(name = "valve_art")})
    private Set<Valve> valves = new HashSet<Valve>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "actuators")
    private Set<Adapter> adapters = new HashSet<Adapter>();


    public Actuator() {
    }

    public Actuator(String article, String voltage, String signal, String normalyopenclose, String onoffendpos, String timepos, String power, String stroke, double price, String actuatorimageurl) {
        this.article = article;
        this.voltage = voltage;
        this.signal = signal;
        this.normalyopenclose = normalyopenclose;
        this.onoffendpos = onoffendpos;
        this.timepos = timepos;
        this.power = power;
        this.stroke = stroke;
        this.price = price;
        this.actuatorimageurl = actuatorimageurl;
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

    public String getNormalyopenclose() {
        return normalyopenclose;
    }

    public void setNormalyopenclose(String nonc) {
        this.normalyopenclose = nonc;
    }

    public String getOnoffendpos() {
        return onoffendpos;
    }

    public void setOnoffendpos(String endPos) {
        this.onoffendpos = endPos;
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

    public String getActuatorimageurl() {
        return actuatorimageurl;
    }

    public void setActuatorimageurl(String imageurl) {
        this.actuatorimageurl = imageurl;
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
        buffer.append(this.signal);
        buffer.append(this.normalyopenclose);
        buffer.append(this.onoffendpos);
        buffer.append(this.timepos);
        buffer.append(this.power);
        buffer.append(this.stroke);
        buffer.append(this.price);
        buffer.append(this.actuatorimageurl);
        return buffer.toString().hashCode();
    }

    @Override
    public String toString() {
        return "Actuator{" +
                "article='" + article + '\'' +
                ", voltage='" + voltage + '\'' +
                ", signal='" + signal + '\'' +
                ", nonc='" + normalyopenclose + '\'' +
                ", endpos='" + onoffendpos + '\'' +
                ", timepos='" + timepos + '\'' +
                ", power='" + power + '\'' +
                ", stroke='" + stroke + '\'' +
                ", price=" + price +
                '}' + "\n";
    }
}