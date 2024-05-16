package barApp.barApp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "price S is required")
    private int taille_S_price;

    @NotBlank(message = "price M is required")
    private int taille_M_price;

    @NotBlank(message = "price L is required")
    private int taille_L_price;

    @NotBlank(message = "status is required")
    private Boolean status;

    @OneToMany(mappedBy = "ingredient_cocktail")
    private List<Ingredient> cocktail_ingredient;

    @OneToMany(mappedBy = "cocktail_category")
    private List<Category> cocktail_category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu", referencedColumnName = "id")
    private Menu menu_cocktail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_cocktail", referencedColumnName = "id")
    private Order_cocktail order_cocktail;


}
