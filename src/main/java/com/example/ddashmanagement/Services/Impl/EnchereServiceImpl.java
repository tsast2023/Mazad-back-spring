package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Dto.ConfigPredéfiniesEnchere;
import com.example.ddashmanagement.Dto.EnchereRequest;
import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Entites.*;
import com.example.ddashmanagement.Repository.EnchereRepository;
import com.example.ddashmanagement.Repository.UserRepository;
import com.example.ddashmanagement.Services.IEnchereService;
import com.example.ddashmanagement.Services.IServiceDemande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EnchereServiceImpl implements IEnchereService {

    private final EnchereRepository enchereRepository ;

    public EnchereServiceImpl(EnchereRepository enchereRepository, TaskScheduler taskScheduler, IServiceDemande iServiceDemande, UserRepository userRepository) {
        this.enchereRepository = enchereRepository;
        this.taskScheduler = taskScheduler;

        this.iServiceDemande = iServiceDemande;
        this.userRepository = userRepository;
    }

    @Autowired
    @Qualifier("monTaskScheduler")
    private  TaskScheduler taskScheduler;
    private final IServiceDemande iServiceDemande;
    private final UserRepository userRepository;


    @Override
    public Enchere CreateEnchereBrouillon(EnchereRequest e) {
        Enchere enchere = new Enchere();
        enchere.setVille(e.getVille());
        enchere.setPrixMagasin(e.getPrixMagasin());
        enchere.setPrixMazedOnline(e.getPrixMazedOnline());
        enchere.setProduct(e.getProducts());
        enchere.setStatus(StatusEnchere.Brouillon);
        return enchereRepository.save(enchere);
    }

    @Override
    public Enchere publishBidNow(ConfigPredéfiniesEnchere enchere , String IdEnchere) {
        Enchere e = enchereRepository.findById(IdEnchere).get();
        e.setCoutClic(enchere.getCoutClic());
        e.setCoutParticipation(enchere.getCoutParticipation());
        e.setValeurMajoration(enchere.getValeurMajoration());
        e.setRemboursement(enchere.getRemboursement());
        e.setFacilité(enchere.getFacilité());
        e.setUnité(enchere.getUnité());
        e.setNombreMois(enchere.getNombreMois());
        e.setNombreParticipant(enchere.getNombreParticipant());
        e.setDatedeclenchement(enchere.getDatedeclenchement());
        e.setDatefermeture(enchere.getDatefermeture());
        e.setStatus(StatusEnchere.Ouverte);
        return enchereRepository.save(e);
    }

    @Override
    public Enchere saveBidBrouillon(ConfigPredéfiniesEnchere enchere, String IdEnchere) {
        Enchere e = enchereRepository.findById(IdEnchere).get();
        e.setCoutClic(enchere.getCoutClic());
        e.setCoutParticipation(enchere.getCoutParticipation());
        e.setValeurMajoration(enchere.getValeurMajoration());
        e.setRemboursement(enchere.getRemboursement());
        e.setFacilité(enchere.getFacilité());
        e.setUnité(enchere.getUnité());
        e.setNombreMois(enchere.getNombreMois());
        e.setNombreParticipant(enchere.getNombreParticipant());
        e.setDatedeclenchement(enchere.getDatedeclenchement());
        e.setDatefermeture(enchere.getDatefermeture());
        return enchereRepository.save(e);
    }

    @Override
    public Enchere findBidById(String id) {
        return enchereRepository.findById(id).get();
    }


    @Override
    public Enchere scheduleBidPublication(ConfigPredéfiniesEnchere enchere, LocalDateTime publicationDate, String IdEnchere) {
        Enchere e = enchereRepository.findById(IdEnchere).get();
        e.setStatus(StatusEnchere.Brouillon);
        e.setCoutClic(enchere.getCoutClic());
        e.setCoutParticipation(enchere.getCoutParticipation());
        e.setValeurMajoration(enchere.getValeurMajoration());
        e.setRemboursement(enchere.getRemboursement());
        e.setFacilité(enchere.getFacilité());
        e.setUnité(enchere.getUnité());
        e.setNombreMois(enchere.getNombreMois());
        e.setNombreParticipant(enchere.getNombreParticipant());
        e.setDatedeclenchement(enchere.getDatedeclenchement());
        e.setDatefermeture(enchere.getDatefermeture());
        Enchere savedBid = enchereRepository.save(e);

        taskScheduler.schedule(() -> {
            savedBid.setStatus(StatusEnchere.Ouverte);
            enchereRepository.save(savedBid);
        }, java.sql.Timestamp.valueOf(publicationDate));

        return savedBid;
    }

    @Override
    public List<Enchere> getAll() {
        return enchereRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public String demandeAnnulationEnchere(String enchereId, User user) {
        Optional<Enchere> EnchereOptionel = enchereRepository.findById(enchereId);

        if (EnchereOptionel.isPresent()) {
            Enchere enchere = EnchereOptionel.get();
            if (enchere.getStatus().equals(StatusEnchere.Ouverte)) {
                String EnchereId = String.valueOf(EnchereOptionel.get());
                String adminId = user.getId();

                iServiceDemande.DemandeAnnulationEnchere(EnchereId , adminId);

               /* NotificationMessage notificationMessage = new NotificationMessage();
                notificationMessage.setTitle("Demande de Modification");
                notificationMessage.setBody("Une demande de Modification a été effectuée pour le produit " + ProductId);
                notificationMessage.setRecipentToken("TOKEN_DU_SUPER_ADMIN"); // Remplacez TOKEN_DU_SUPER_ADMIN par le token du dispositif du super admin

                // Envoyer la notification au super admin
                String result = firebaseMessagingService.SendNotificationByToken(notificationMessage);

                return result;*/
                return "Demande de modification envoyée avec succées.";

            } else {
                return "L'enchere n'est pas publié et ne peut pas être modifié.";
            }
        }
        else {
            // Gérer le cas où le produit n'est pas trouvé
            return "Enchere non trouvé pour l'identifiant: " + enchereId;
        }
    }

    @Override
    public String deleteEnchere(String id) {
        Enchere e = enchereRepository.findById(id).get();
        if(e.getStatus().equals(StatusEnchere.Brouillon)){
            enchereRepository.deleteById(id);
            return "enchere supprimée avec succées";
        }
        else {
            return "on ne peut pas  supprimer cette enchére";
        }
    }

    @Override
    public String demandeModificationEnchere(String enchereId, User user, Enchere enchereModifications) {
        Optional<Enchere> enchereOptional = enchereRepository.findById(enchereId);
        if (!enchereOptional.isPresent()) {
            return "Enchère non trouvée pour l'identifiant: " + enchereId;
        }

        Enchere e = enchereOptional.get();
        switch (e.getStatus()) {
            case Brouillon:
                copierProprietesModifiables(e, enchereModifications);
                enchereRepository.save(e);
                return "Enchère modifiée avec succès.";
            case Terminée:
                e.setNombreMois(enchereModifications.getNombreMois());
                enchereRepository.save(e);
                return "Nombre de mois avant de cacher l'enchère modifié avec succès.";
            default:
                iServiceDemande.DemandeModificationEnchere(enchereId, user.getId(), enchereModifications);
              //  String superAdminId = trouverSuperAdminId(); // Méthode fictive pour obtenir l'ID du super administrateur
               // notificationService.envoyerNotification(superAdminId, "Nouvelle Demande de Modification", "Une nouvelle demande de modification pour l'enchère ID: " + enchereId + " a été soumise par l'utilisateur ID: " + user.getId() + ".");

                return "Demande de modification envoyée avec succès.";
        }
    }

    @Override
    public String annulerEnchereEtInformerParticipants(String enchereId, User requester) {
        Optional<Enchere> enchereOptionnelle = enchereRepository.findById(enchereId);

        if (!enchereOptionnelle.isPresent()) {
            return "Enchère non trouvée pour l'identifiant: " + enchereId;
        }

        Enchere enchere = enchereOptionnelle.get();

        if (!enchere.getStatus().equals(StatusEnchere.Ouverte)) {
            return "L'enchère n'est pas ouverte et ne peut pas être annulée.";
        }

        Collection<User> participants = enchere.getParticipants();
        for (User participant : participants) {
            // Supposons que chaque utilisateur a un objet 'Solde' pour gérer son solde financier
            Solde solde = participant.getSolde();
            solde.setSoldeAquisition(solde.getSoldeAquisition() + enchere.getCoutParticipation());
            userRepository.save(participant); // Assurez-vous que la méthode save de votre UserRepository gère correctement la mise à jour du solde

            // Envoyer la notification de remboursement aux participants
           // notificationService.envoyerNotification(participant, "Enchère Annulée", "L'enchère " + enchere.getProduct().getLibelleProduct() + " a été annulée. Votre coût de participation a été remboursé. Votre nouveau solde est " + solde.getSoldeAquisition() + ".");
        }

        // Mise à jour du statut de l'enchère et sauvegarde
        enchere.setStatus(StatusEnchere.Annulée);
        enchereRepository.save(enchere);

        // Envoyer une notification à l'utilisateur ayant demandé l'annulation
        // notificationService.envoyerNotification(requester, "Demande d'annulation traitée", "Votre demande d'annulation de l'enchère " + enchere.getNom() + " a été traitée avec succès. Les participants ont été remboursés.");

        return "Enchère annulée et participants remboursés avec succès.";
    }

    @Override
    public String validerDemandeModification(String demandeId) {
        DemandesSuperAdmin demandeModification = iServiceDemande.findById(demandeId);


        // Recherche de l'enchère à modifier
        Enchere enchere = enchereRepository.findById(demandeModification.getEnchereId())
                .orElseThrow(() -> new RuntimeException("Enchère non trouvée."));

        enchere.setCoutClic(demandeModification.getEnchere().getCoutClic());
        enchere.setNombreMois(demandeModification.getEnchere().getNombreMois());
        enchere.setRemboursement(demandeModification.getEnchere().getRemboursement());
        enchere.setUnité(demandeModification.getEnchere().getUnité());
        enchere.setVille(demandeModification.getEnchere().getVille());
        enchere.setCoutParticipation(demandeModification.getEnchere().getCoutParticipation());
        enchere.setDatefermeture(demandeModification.getEnchere().getDatefermeture());
        enchere.setProduct(demandeModification.getEnchere().getProduct());
        enchere.setFacilité(demandeModification.getEnchere().getFacilité());
        enchere.setDatedeclenchement(demandeModification.getEnchere().getDatedeclenchement());
        enchere.setDatefermeture(demandeModification.getEnchere().getDatefermeture());
        enchere.setStatus(demandeModification.getEnchere().getStatus());
        enchere.setPrixMagasin(demandeModification.getEnchere().getPrixMagasin());
        enchere.setFacilité(demandeModification.getEnchere().getFacilité());
        enchere.setPrixMazedOnline(demandeModification.getEnchere().getPrixMazedOnline());
        enchere.setNombreParticipant(demandeModification.getEnchere().getNombreParticipant());
        enchere.setValeurMajoration(demandeModification.getEnchere().getValeurMajoration());
        enchereRepository.save(enchere);

        // Envoi de la notification push à l'administrateur qui a fait la demande
       // String adminId = demandeModification.getAdminId(); // Supposition que vous avez un champ adminId dans votre objet demande
       // String notificationToken = getAdminNotificationToken(adminId); // Vous devez implémenter cette méthode pour récupérer le token de notification de l'admin
       // String message = "Votre demande de modification a été validée.";
        ///sendPushNotification(notificationToken, message); // Vous devez implémenter cette méthode

        return "La demande de modification a été validée et les modifications appliquées.";
    }

    @Override
    public List<Enchere> findEnchere(Optional<CategoryFille> category, Optional<StatusEnchere> status) {
        return enchereRepository.findByStatusOrCategory(status.orElse(null) , category.orElse(null));
    }

    @Override
    public Enchere epinglerEnchere(String id) {
        Enchere e = enchereRepository.findById(id).get();
        e.setStatus(StatusEnchere.Terminée);
        return enchereRepository.save(e);
    }

    @Override
    public Enchere desepinglerEnchere(String id, Integer nombreMois) {
        Enchere e = enchereRepository.findById(id).get();
        e.setNombreMois(nombreMois);
        return enchereRepository.save(e);
    }


    private void copierProprietesModifiables(Enchere source, Enchere modifications) {
        // Supposons que ces méthodes existent dans votre classe Enchere
        source.setCoutClic(modifications.getCoutClic());
        source.setCoutParticipation(modifications.getCoutParticipation());
        source.setValeurMajoration(modifications.getValeurMajoration());
        source.setRemboursement(modifications.getRemboursement());
        source.setFacilité(modifications.getFacilité());
        source.setUnité(modifications.getUnité());
        source.setNombreMois(modifications.getNombreMois());
        source.setNombreParticipant(modifications.getNombreParticipant());
        source.setDatedeclenchement(modifications.getDatedeclenchement());
        source.setDatefermeture(modifications.getDatefermeture());
        source.setVille(modifications.getVille());
        source.setPrixMagasin(modifications.getPrixMagasin());
        source.setPrixMazedOnline(modifications.getPrixMazedOnline());
        source.setProduct(modifications.getProduct());
    }

    }



