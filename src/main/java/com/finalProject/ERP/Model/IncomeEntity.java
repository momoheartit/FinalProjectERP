package com.finalProject.ERP.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "db__income")
public class IncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "partner", referencedColumnName = "id")
    private PartnerEntity partner;

    private int amount;
    private String project;
    
    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private LocalDateTime created;
    
    @Column(name = "approved", columnDefinition = "TIMESTAMP")
    private LocalDateTime approved;

    public IncomeEntity(int amount, String project, LocalDateTime created) {
        this.amount = amount;
        this.project = project;
        this.created = created;
        
        approved = null;
        partner = null;
    }
    
    public IncomeEntity(){}

    public int getId() {
        return id;
    }

    public PartnerEntity getPartner() {
        if (partner == null){return null;}
        return partner;
    }

    public int getAmount() {
        return amount;
    }

    public String getProject() {
        return project;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getApproved() {
        if (approved == null){return null;}
        return approved;
    }

    public void setPartner(PartnerEntity partner) {
        this.partner = partner;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setApproved(LocalDateTime approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "IncomeEntity{" + "id=" + id + ", partner=" + partner + ", amount=" + amount + ", project=" + project + ", created=" + created + ", approved=" + approved + '}';
    }
    
    
}
