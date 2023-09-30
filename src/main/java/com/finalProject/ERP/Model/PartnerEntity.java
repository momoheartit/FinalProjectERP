package com.finalProject.ERP.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "db__partners")
public class PartnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String contact;

    // Getterek és setterek

    public PartnerEntity(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }
    
    public PartnerEntity(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PartnerEntity{" + "id=" + id + ", name=" + name + ", contact=" + contact + '}';
    }
    
}
