package ru.jpol.vocabot.entity;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "created", nullable = false)
    private Timestamp created;

    @Column(name = "updated", nullable = false)
    private Timestamp updated;

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
