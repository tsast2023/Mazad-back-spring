package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Ennum.StatusProduct;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Repository.ProductRepository;
import com.example.ddashmanagement.Services.IServiceDemande;
import com.example.ddashmanagement.Services.IServiceProduct;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IServiceProduct {
    private final ProductRepository productRepository;

    private final IServiceDemande iServiceDemande;

    public ProductServiceImpl(ProductRepository productRepository, IServiceDemande iServiceDemande) {
        this.productRepository = productRepository;
        this.iServiceDemande = iServiceDemande;
    }


    @Override
    public Product createProduct(Product p) {
        p.setStatus(StatusProduct.Brouillon);
        return productRepository.save(p);
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public String DemandeupdateProduct(String id, User userDetails, Product p) {
        Optional<Product> ProductOptional = productRepository.findById(id);

        if (ProductOptional.isPresent()) {
            Product product = ProductOptional.get();
            if (product.getStatus().equals(StatusProduct.Publié)) {
                String ProductId = String.valueOf(ProductOptional.get());
                String adminId = userDetails.getId();

                iServiceDemande.DemandeModificationProduct(ProductId, adminId, p);

               /* NotificationMessage notificationMessage = new NotificationMessage();
                notificationMessage.setTitle("Demande de Modification");
                notificationMessage.setBody("Une demande de Modification a été effectuée pour le produit " + ProductId);
                notificationMessage.setRecipentToken("TOKEN_DU_SUPER_ADMIN"); // Remplacez TOKEN_DU_SUPER_ADMIN par le token du dispositif du super admin

                // Envoyer la notification au super admin
                String result = firebaseMessagingService.SendNotificationByToken(notificationMessage);

                return result;*/
                return "Demande de modification envoyée avec succées.";

            } else {
                return "Le produit n'est pas publié et ne peut pas être modifié.";
            }
        }
         else {
            // Gérer le cas où le produit n'est pas trouvé
            return "Produit non trouvé pour l'identifiant: " + id;
        }
    }



    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public void deleteProduct(Product c) {

    }

    @Override
    public boolean ProductExists(String id) {
        return false;
    }
}
