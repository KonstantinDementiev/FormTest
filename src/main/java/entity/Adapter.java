package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adapter extends Model {

    @Id
    private String article;
    private String image;
    private double price;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "kit", joinColumns = {@JoinColumn(name = "adapter_art")}, inverseJoinColumns = {@JoinColumn(name = "valve_art")})
    private Set<Valve> valves = new HashSet<Valve>();

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "Adapter{" + "article='" + article + ", valves=" + valves + '\n' + '}';
    }
}
