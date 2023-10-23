package chkir.resourciumoptimaii.entities;

import chkir.resourciumoptimaii.enums.EquipmentStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;

    @Temporal(TemporalType.DATE)
    private Date buy_date;

    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @ManyToMany
    @JoinTable(name = "equipment_reservation", joinColumns = @JoinColumn(name = "equipment_id"), inverseJoinColumns = @JoinColumn(name = "reservation_id"))
    private List<Reservation> reservations;

    @ManyToMany(mappedBy = "equipment")
    private List<Task> tasks;

    // Getter and Setter methods
}
