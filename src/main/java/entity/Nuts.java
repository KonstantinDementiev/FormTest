package entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Nuts extends Model {

    @Id
    private String article;
    private Double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "nuts")
    private Set<Valve> valves = new HashSet<>();

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

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Valve> getValves() {
        return valves;
    }

    public void setValves(Set<Valve> valves) {
        this.valves = valves;
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
                ", valves=" + valves +
                '}';
    }
}
