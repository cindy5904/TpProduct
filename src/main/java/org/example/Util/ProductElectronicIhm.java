package org.example.Util;

import org.example.Entity.ProductElectronic;
import org.example.Entity.ProductHousing;
import org.example.Repository.ProductElectronicRepository;
import org.example.Repository.ProductHousingRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ProductElectronicIhm {
    private Scanner scanner;
    private ProductElectronicRepository productElectronicRepository;

    public ProductElectronicIhm(Scanner scanner, EntityManager em) {
        this.scanner = scanner;
        productElectronicRepository = new ProductElectronicRepository(em);
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
                    createProductElectronic();
                    break;
                case 2:
                    getAllProductElectronic();
                    break;
                case 3:
                    getProductElectronicById();
                    ;
                    break;
                case 4:
                    updateProductElectronic();
                    ;
                    break;
                case 5:
                    removeProductElectronic();
                    ;
                    break;
                default:
                    return;
            }
        }
    }

    private void createProductElectronic () {
        System.out.println("creation d'un produit Electronique");
        System.out.println("Nom du produit : ");
        String nom = scanner.nextLine();
        System.out.println("prix du produit : ");
        int prix = scanner.nextInt();
        System.out.println("Durée de vie du produit : ");
        int duree = scanner.nextInt();
        scanner.nextLine();
        ProductElectronic productElectronic = ProductElectronic.builder()
                .nom(nom)
                .prix(prix)
                .dureeBatterie(duree)
                .build();

        productElectronicRepository.save(productElectronic);
    }

    private void getAllProductElectronic() {
        System.out.println("Afficher tous les produits électronique : ");
        List<ProductElectronic> productElectronics = productElectronicRepository.findAll();
        for (ProductElectronic productElectronic : productElectronics){
            System.out.println(productElectronic);
        }
    }

    private void getProductElectronicById() {
        System.out.println("afficher un produit par id");
        System.out.println("id du produit : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductElectronic productElectronic = productElectronicRepository.findById(id);
        if(productElectronic != null){
            System.out.println(productElectronic);
        }else {
            System.out.println("aucun produit trouvé");
        }
    }

    private void updateProductElectronic() {
        System.out.println("mise a jour d'un pproduit");
        System.out.println("id du produit");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductElectronic productElectronic = productElectronicRepository.findById(id);
        if(productElectronic != null){
            System.out.println("nom du produit : ( " + productElectronic.getNom() +" )" );
            productElectronic.setNom(scanner.nextLine());
            System.out.println("Prix : ( " + productElectronic.getPrix() +" )" );
            productElectronic.setPrix(scanner.nextInt());
            System.out.println("largeur : ( " + productElectronic.getDureeBatterie() +" )" );
            productElectronic.setDureeBatterie(scanner.nextInt());
            scanner.nextLine();
            productElectronicRepository.save(productElectronic);
        }else{
            System.out.println("aucun produit trouvé");
        }
    }

    private void removeProductElectronic() {
        System.out.println("Suppression du produit : ");
        System.out.println("id du produit");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductElectronic productElectronic = productElectronicRepository.findById(id);
        if (productElectronic != null) {
            productElectronicRepository.delete(productElectronic);
        } else {
            System.out.println("Aucun produit trouvé");
        }

    }
}
