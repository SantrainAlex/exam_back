package barApp.barApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "commande")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "status is required")
    private String order_status;

    @NotBlank(message = "price is required")
    private int total_price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Order_cocktail> order_cocktails;

}
