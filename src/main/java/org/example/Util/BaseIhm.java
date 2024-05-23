package org.example.Util;

import org.example.Repository.ProductElectronicRepository;
import org.example.Repository.ProductFoodRepository;
import org.example.Repository.ProductHousingRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class BaseIhm {
    EntityManagerFactory emf =  Persistence.createEntityManagerFactory("produit");
    EntityManager em = emf.createEntityManager();
    ProductHousingRepository productHousingRepository;
    ProductElectronicRepository productElectronicRepository;
    ProductFoodRepository productFoodRepository;

    Scanner scanner;

    public BaseIhm() {
        scanner = new Scanner(System.in);
        productHousingRepository = new ProductHousingRepository(em);
        productElectronicRepository= new ProductElectronicRepository(em);
        productFoodRepository = new ProductFoodRepository(em);
    }

    public void start() {
        while (true){
            System.out.println("Menu : ");
            System.out.println("1/ crée un produit alimentaire");
            System.out.println("2/ créer un produit électronique");
            System.out.println("3/ créer un produit de la maison");

            int entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry){
                case 1:
                    ProductFoodIhm productFoodIhm = new ProductFoodIhm(scanner, em);
                    productFoodIhm.start();
                    break;
                case 2:
                    ProductElectronicIhm productElectronicIhm = new ProductElectronicIhm(scanner, em);
                    productElectronicIhm.start();
                    break;
                case 3:
                    ProductHousingIhm productHousingIhm = new ProductHousingIhm(scanner, em);
                    productHousingIhm.start();
                    break;
                default:
                    return;
            }
        }
    }
}
