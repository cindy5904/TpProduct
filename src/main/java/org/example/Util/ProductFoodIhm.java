package org.example.Util;

import org.example.Entity.ProductElectronic;
import org.example.Entity.ProductFood;
import org.example.Entity.ProductHousing;
import org.example.Repository.ProductFoodRepository;
import org.example.Repository.ProductHousingRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductFoodIhm {
    private Scanner scanner;
    private ProductFoodRepository productFoodRepository;

    public ProductFoodIhm(Scanner scanner, EntityManager em) {
        this.scanner = scanner;
        productFoodRepository = new ProductFoodRepository(em);
    }
    public void start () {
        int entry;
        while (true) {
            System.out.println("--- Menu Produit de la maison ---");
            System.out.println("1/ Créer un produit");
            System.out.println("2/ Afficher tous les produits");
            System.out.println("3/ Afficher un produit par son Id");
            System.out.println("4/ Modifier un produit");
            System.out.println("5/ Supprimer un produit");
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    createProductFood();
                    break;
                case 2:
                    getAllProductFood();
                    break;
                case 3:
                    getProductElectronicById();
                    ;
                    break;
                case 4:
                    updateProductFood();
                    ;
                    break;
                case 5:
                    removeProductFood();
                    ;
                    break;
                default:
                    return;
            }
        }
    }
    private void createProductFood () {
        System.out.println("creation d'un produit alimentaire");
        System.out.println("Nom du produit : ");
        String nom = scanner.nextLine();
        System.out.println("prix du produit : ");
        int prix = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Date de péremption : ");
        LocalDate datePeremption = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (datePeremption == null) {
            try {
                String input = scanner.nextLine();
                datePeremption = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Veuillez entrer la date au format YYYY-MM-DD : ");
            }
        }


        ProductFood productFood = ProductFood.builder()
                .nom(nom)
                .prix(prix)
                .dateExpiration(datePeremption)
                .build();

        productFoodRepository.save(productFood);
    }

    private void getAllProductFood() {
        System.out.println("Afficher tous les produits alimentaires : ");
        List<ProductFood> productFoods = productFoodRepository.findAll();
        for (ProductFood productFood : productFoods){
            System.out.println(productFood);
        }
    }

    private void getProductElectronicById() {
        System.out.println("afficher un produit par id");
        System.out.println("id du produit : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductFood productFood = productFoodRepository.findById(id);
        if(productFood != null){
            System.out.println(productFood);
        }else {
            System.out.println("aucun produit trouvé");
        }
    }
    private void updateProductFood() {
        System.out.println("mise a jour d'un pproduit");
        System.out.println("id du produit");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductFood productFood = productFoodRepository.findById(id);
        if(productFood != null){
            System.out.println("nom du produit : ( " + productFood.getNom() +" )" );
            productFood.setNom(scanner.nextLine());
            System.out.println("Prix : ( " + productFood.getPrix() +" )" );
            productFood.setPrix(scanner.nextInt());
            System.out.println("Date d'expiration : ( " + productFood.getDateExpiration() +" )" );
            LocalDate datePeremption = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (datePeremption == null) {
                try {
                    String input = scanner.nextLine();
                    if (input.trim().isEmpty()) {

                        datePeremption = productFood.getDateExpiration();
                    } else {
                        datePeremption = LocalDate.parse(input, formatter);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Format de date invalide.");
                }
            }

            productFood.setDateExpiration(datePeremption);

            scanner.nextLine();
            productFoodRepository.save(productFood);
        }else{
            System.out.println("aucun peripherique trouvé");
        }


    }
    private void removeProductFood() {
        System.out.println("Suppression du produit : ");
        System.out.println("id du produit");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductFood productFood = productFoodRepository.findById(id);
        if (productFood != null) {
            productFoodRepository.delete(productFood);
        } else {
            System.out.println("Aucun produit trouvé");
        }

    }

}
