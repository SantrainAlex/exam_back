package barApp.barApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name = "order_cocktail")
@Data
public class Order_cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "size_cocktail is required")
    private String size_cocktail;

    @NotBlank(message = "status is required")
    private String status;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail", referencedColumnName = "id")
    private Cocktail cocktail;



}
