package zhukova.victoria.kursovaya.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "disease_book")
public class DiseaseBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

}
