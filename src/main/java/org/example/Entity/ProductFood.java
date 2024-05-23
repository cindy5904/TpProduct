package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
public class ProductFood extends Product{
    private LocalDate dateExpiration;


    public ProductFood() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + ", ProductFood{" +
                "dateExpiration=" + dateExpiration +
                '}';
    }
}


