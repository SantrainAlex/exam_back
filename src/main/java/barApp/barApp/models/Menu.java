package barApp.barApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="name is required")
    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barmaker", referencedColumnName = "id")
    private Barmaker barmaker;

    @OneToMany(mappedBy = "menu_cocktail")
    private List<Cocktail> menu_cocktail;


}
