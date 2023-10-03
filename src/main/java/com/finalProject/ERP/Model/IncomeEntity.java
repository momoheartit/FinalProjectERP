package com.finalProject.ERP.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
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
    
    @Column(name = "created")
    private LocalDate created;
    
    @Column(name = "approved")
    private LocalDate approved;

    public IncomeEntity(int amount, String project, LocalDate created) {
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
        if (project == null) {return "-";}
        return project;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getApproved() {
        if (approved == null){return null;}
        return approved;
    }
    
    public String getPartnerName(){
        return partner.getName();
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

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setApproved(LocalDate approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "IncomeEntity{" + "id=" + id + 
                ", partner=" + partner + 
                ", amount=" + amount + 
                ", project=" + project + 
                ", created=" + created + 
                ", approved=" + approved + '}';
    }

    
}
