package chkir.resourciumoptimaii.entities;

import chkir.resourciumoptimaii.enums.EquipmentStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "equipment")
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



//    @ManyToMany(mappedBy = "equipment")
//    private List<Task> tasks;

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(Date buy_date) {
        this.buy_date = buy_date;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

}
