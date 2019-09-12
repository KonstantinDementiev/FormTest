package entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class Adapter extends Model{

    @Id
    private String article;
    private double price;

    @OneToMany
    private Set<Valve> valve = new HashSet<Valve>();
    @OneToMany
    private Set<Actuator> actuator = new HashSet<Actuator>();


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Adapter{" +
                "article='" + article + '\'' +
                ", price=" + price +
                '}';
    }
}
