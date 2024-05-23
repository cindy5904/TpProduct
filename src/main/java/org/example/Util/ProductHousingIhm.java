package org.example.Util;

import org.example.Entity.ProductHousing;
import org.example.Repository.ProductHousingRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ProductHousingIhm {
    private Scanner scanner;
    private ProductHousingRepository productHousingRepository;

    public ProductHousingIhm(Scanner scanner, EntityManager em) {
        this.scanner = scanner;
        productHousingRepository = new ProductHousingRepository(em);
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
                    createProductHousing();
                    break;
                case 2:
                    getAllProductHousing();
                    break;
                case 3:
                    getProductHousingById();
                    ;
                    break;
                case 4:
                    updateProductHousing();
                    ;
                    break;
                case 5:
                    removeProductHousing();
                    ;
                    break;
                default:
                    return;
            }
        }
    }
    private void createProductHousing () {
        System.out.println("creation d'un produit de la maison");
        System.out.println("Nom du produit : ");
        String nom = scanner.nextLine();
        System.out.println("prix du produit : ");
        int prix = scanner.nextInt();
        System.out.println("Largeur : ");
        int largeur = scanner.nextInt();
        System.out.println("Longueur : ");
        int longueur = scanner.nextInt();
        scanner.nextLine();
        ProductHousing productHousing = ProductHousing.builder()
                .nom(nom)
                .prix(prix)
                .largeur(largeur)
                .longueur(longueur).
                build();

        productHousingRepository.save(productHousing);
    }

    private void getAllProductHousing() {
        System.out.println("Afficher tous les produits de la maison : ");
        List<ProductHousing> productHousings = productHousingRepository.findAll();
        for (ProductHousing productHousing : productHousings){
            System.out.println(productHousing);
        }
    }

    private void getProductHousingById() {
        System.out.println("afficher un produit par id");
        System.out.println("id du produit : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductHousing productHousing = productHousingRepository.findById(id);
        if(productHousing != null){
            System.out.println(productHousing);
        }else {
            System.out.println("aucun produit trouvé");
        }
    }

    private void updateProductHousing() {
        System.out.println("mise a jour d'un pproduit");
        System.out.println("id du produit");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductHousing productHousing = productHousingRepository.findById(id);
        if(productHousing != null){
            System.out.println("nom du produit : ( " + productHousing.getNom() +" )" );
            productHousing.setNom(scanner.nextLine());
            System.out.println("Prix : ( " + productHousing.getPrix() +" )" );
            productHousing.setPrix(scanner.nextInt());
            System.out.println("largeur : ( " + productHousing.getLargeur() +" )" );
            productHousing.setLargeur(scanner.nextInt());
            System.out.println("longueur : ( " + productHousing.getLongueur() +" )" );
            productHousing.setLongueur(scanner.nextInt());
            scanner.nextLine();
            productHousingRepository.save(productHousing);
        }else{
            System.out.println("aucun peripherique trouvé");
        }
    }

    private void removeProductHousing() {
        System.out.println("Suppression du produit : ");
        System.out.println("id du produit");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductHousing productHousing = productHousingRepository.findById(id);
        if (productHousing != null) {
            productHousingRepository.delete(productHousing);
        } else {
            System.out.println("Aucun produit trouvé");
        }

    }


}
