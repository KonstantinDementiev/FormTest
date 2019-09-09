package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Nuts extends Model {

    @Id
    private String article;
    private double price;

    @OneToMany (mappedBy = "nuts")
    private Set<Valve> valves = new HashSet<Valve>();


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



    @Override
    public String toString() {
        return "Nuts{" +
                "article='" + article + '\'' +
                ", price=" + price +
                '}';
    }
}
