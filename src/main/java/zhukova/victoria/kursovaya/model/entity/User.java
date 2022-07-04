package zhukova.victoria.kursovaya.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id",
    referencedColumnName = "id")
    private UserInfo info;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "disease_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private List<Disease> diseaseBook;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_complaint",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<UserComplaint> complaints;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "request",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<UserRequest> requests;

}
