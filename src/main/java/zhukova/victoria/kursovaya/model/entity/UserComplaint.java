package zhukova.victoria.kursovaya.model.entity;

import javax.persistence.*;


@Entity
@Table(name = "user_complaint")
public class UserComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;
}
