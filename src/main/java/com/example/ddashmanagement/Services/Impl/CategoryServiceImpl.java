package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.DemandesSuperAdmin;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Repository.CategoryFilleRepository;
import com.example.ddashmanagement.Services.IServiceCategory;
import com.example.ddashmanagement.Services.IServiceDemande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements IServiceCategory {
    @Autowired
    CategoryFilleRepository categoryFilleRepository;

    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @Autowired
    IServiceDemande iServiceDemande;


    @Override
    public CategoryFille createCategorie(CategoryFille c) {
        System.out.println("CategoryFille" + c);
        CategoryFille category = new CategoryFille();
        if (c.getCategories().isEmpty()) {
            category.setLibeléCategorie(c.getLibeléCategorie());
            category.setType(TypeCategory.CATEGORYPARENTE);

        } else {
            category.setCategories(c.getCategories());
            category.setLibeléCategorie(c.getLibeléCategorie());
            category.setType(TypeCategory.CATEGORYFILLE);
        }
        return categoryFilleRepository.save(category);

    }

    @Override
    public CategoryFille findCategorieById(String id) {

        return categoryFilleRepository.findById(id).get();
    }

    @Override
    public CategoryFille updateCategorie(String id, CategoryFille c) {

        CategoryFille cat = categoryFilleRepository.findById(id).orElseThrow(() -> new RuntimeException("Entité non trouvée avec id " + id));

        if (cat.getProducts().isEmpty()) {
            cat.setLibeléCategorie(c.getLibeléCategorie());
            cat.setCritere(c.getCritere());
        }

        return categoryFilleRepository.save(cat);
    }


    @Override
    public List<CategoryFille> findAllCategorie() {
        return categoryFilleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public void deleteCategorie(CategoryFille c) {
        if (c.getProducts().isEmpty()) {
            categoryFilleRepository.delete(c);
        }
    }



    @Override
    public String ChangerStatusDemande(String idCategory, User userDetails) {
        Optional<CategoryFille> categoryOptional = categoryFilleRepository.findById(idCategory);

        if (categoryOptional.isPresent()) {
            CategoryFille cat = categoryOptional.get();
            if (cat.getType().equals(TypeCategory.CATEGORYFILLE) && !cat.getProducts().isEmpty()) {
                String categoryId = String.valueOf(categoryOptional.get());
                String adminId = userDetails.getId();

                iServiceDemande.DemandeDesactivationCategory(categoryId, adminId);

               /* NotificationMessage notificationMessage = new NotificationMessage();
                notificationMessage.setTitle("Demande de désactivation");
                notificationMessage.setBody("Une demande de désactivation a été effectuée pour la catégorie " + categoryId);
                notificationMessage.setRecipentToken("TOKEN_DU_SUPER_ADMIN"); // Remplacez TOKEN_DU_SUPER_ADMIN par le token du dispositif du super admin

                // Envoyer la notification au super admin
                String result = firebaseMessagingService.SendNotificationByToken(notificationMessage);

                return result;*/
                return "Demande de désactivation envoyée pour la catégorie fille.";

            } else if (cat.getType().equals(TypeCategory.CATEGORYPARENTE) && cat.getCategories().stream().allMatch(category -> category.getStatus() == StatusCategorie.DESACTIVER)) {
                String categoryId = String.valueOf(categoryOptional.get());
                String adminId = userDetails.getId();

                iServiceDemande.DemandeDesactivationCategory(categoryId, adminId);

               /* NotificationMessage notificationMessage = new NotificationMessage();
                notificationMessage.setTitle("Demande de désactivation");
                notificationMessage.setBody("Une demande de désactivation a été effectuée pour la catégorie " + categoryId);
                notificationMessage.setRecipentToken("TOKEN_DU_SUPER_ADMIN"); // Remplacez TOKEN_DU_SUPER_ADMIN par le token du dispositif du super admin

                // Envoyer la notification au super admin
                String result = firebaseMessagingService.SendNotificationByToken(notificationMessage);

                return result;*/
                return "Demande de désactivation envoyée pour la catégorie fille.";
            }else {
                // Gérer le cas où la catégorie est une catégorie fille sans produits ou une catégorie parente avec des catégories filles actives
                return "Impossible de changer le statut de la catégorie. Veuillez vérifier les conditions.";
            }

        } else {
            // Gérer le cas où la catégorie n'est pas trouvée
            return "catégorie non trouvée pour l'identifiant: " + idCategory;
        }
    }



    @Override
    public List<CategoryFille> findCategories
            (Optional<EtatCategory> etat,
             Optional<StatusCategorie> status,
             Optional<TypeCategory> type) {
        return categoryFilleRepository.findByCriteria(etat.orElse(null), type.orElse(null), status.orElse(null));
    }

    @Override
    public String validerChangementStatusCategorie(String idDemande) {
        Optional<DemandesSuperAdmin> demandeOptional = Optional.ofNullable(iServiceDemande.findById(idDemande));

        if (demandeOptional.isPresent()) {
            DemandesSuperAdmin demande = demandeOptional.get();
            Optional<CategoryFille> categoryOptional = categoryFilleRepository.findById(demande.getCategoryId());

            if (categoryOptional.isPresent()) {
                CategoryFille category = categoryOptional.get();
                category.setStatus(StatusCategorie.DESACTIVER);
                categoryFilleRepository.save(category);

                // Supposons que vous avez un champ status dans DemandeDesactivation


                // Envoyer la notification à l'utilisateur
                // User user = demande.getUser(); // Supposons que DemandeDesactivation a un lien vers User
                //  String message = "Votre demande de désactivation pour la catégorie " + category.getNom() + " a été validée.";
                // notificationService.envoyerNotification(user, message);

                return "Le statut de la catégorie a été mis à jour avec succès. Notification envoyée à l'utilisateur.";
            } else {
                return "Catégorie non trouvée pour l'identifiant: " + demande.getCategoryId();
            }
        } else {
            return "Demande de désactivation non trouvée pour l'identifiant: " + idDemande;
        }


    }

}
