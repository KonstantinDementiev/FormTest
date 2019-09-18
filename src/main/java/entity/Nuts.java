package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Nuts extends Model {

    @Id
    private String article;
    private double price;

    @OneToMany (mappedBy = "nuts")
    private Set<Valve> valves;

    public Nuts() {
    }

    @Override
    public String getArticle() {
        return article;
    }

    @Override
    public void setArticle(String article) {
        this.article = article;
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
        return "Nuts{" +
                "article='" + article + '\'' +
                ", price=" + price +
                '}';
    }
}
