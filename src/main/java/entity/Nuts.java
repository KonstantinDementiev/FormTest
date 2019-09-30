package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nuts extends Model {

    @Id
    private String article;
    private Double price;

//    @OneToMany (mappedBy = "nuts")
//    private Set<Valve> valves;

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
    public int hashCode() {
        String str = this.article;
        return str.hashCode();
    }

    @Override
    public String toString() {
        return "Nuts{" +
                "article='" + article + '\'' +
                ", price=" + price +
                '}';
    }
}
