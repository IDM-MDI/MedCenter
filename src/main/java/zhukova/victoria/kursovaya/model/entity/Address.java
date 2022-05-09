package zhukova.victoria.kursovaya.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "adress")
@Getter @Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "flat")
    private String flat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id",
            referencedColumnName = "id")
    private City city;
}
