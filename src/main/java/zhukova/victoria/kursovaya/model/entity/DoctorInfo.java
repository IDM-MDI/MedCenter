package zhukova.victoria.kursovaya.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "doctor_info")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DoctorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "position_id",
            referencedColumnName = "id")
    private DoctorPosition position;

    @ManyToOne()
    @JoinColumn(name = "med_center_id",
            referencedColumnName = "id")
    private MedCenter medCenter;
}
