package org.example.Entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
@Data
@Entity
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
@SuperBuilder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    protected String nom;
    protected int prix;

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
