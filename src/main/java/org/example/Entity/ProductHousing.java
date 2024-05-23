package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
public class ProductHousing extends Product{
    private int largeur;
    private int longueur;

    public ProductHousing() {

    }

    @Override
    public String toString() {
        return super.toString() + ", ProductHousing{" +
                "largeur=" + largeur +
                ", longueur=" + longueur +
                '}';
    }
}
