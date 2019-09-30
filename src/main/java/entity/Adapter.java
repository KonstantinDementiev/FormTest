package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adapter extends Model {

    @Id
    private String article;
    private String imageurl;
    private Double price;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "valve_adapter", joinColumns = {@JoinColumn(name = "adapter_art")}, inverseJoinColumns = {@JoinColumn(name = "valve_art")})
    private Set<Valve> valves = new HashSet<Valve>();

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "actuator_adapter", joinColumns = {@JoinColumn(name = "adapter_art")}, inverseJoinColumns = {@JoinColumn(name = "actuator_art")})
    private Set<Actuator> actuators = new HashSet<Actuator>();


    public Adapter() {
    }

    @Override
    public String getArticle() {
        return article;
    }

    @Override
    public void setArticle(String article) {
        this.article = article;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String image) {
        this.imageurl = image;
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

    public void setValves(Set<Valve> valvesAda) {
        this.valves = valvesAda;
    }

    public Set<Actuator> getActuators() {
        return actuators;
    }

    public void setActuators(Set<Actuator> actuators) {
        this.actuators = actuators;
    }

    @Override
    public String toString() {
        return "Adapter{" +
                "article='" + article + '\'' +
                ", image='" + imageurl + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adapter adapter = (Adapter) o;
        if (this.hashCode()== adapter.hashCode())return true;
        return false;
    }

    @Override
    public int hashCode() {
        String str = this.article;
        return str.hashCode();
    }

}
