package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Dto.ConfigPredéfiniesEnchere;
import com.example.ddashmanagement.Dto.EnchereRequest;
import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Enchere;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Repository.EnchereRepository;
import com.example.ddashmanagement.Services.IEnchereService;
import com.example.ddashmanagement.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bid")
public class EnchereControlller {
    private final IEnchereService iEnchereService ;
    private final UserService userService;
    private final TaskScheduler taskScheduler;
    private EnchereRepository enchereRepository ;

    public EnchereControlller(IEnchereService iEnchereService, UserService userService, TaskScheduler taskScheduler) {
        this.iEnchereService = iEnchereService;
        this.userService = userService;
        this.taskScheduler = taskScheduler;
    }

    @PostMapping("/createEnchereBrouillon")
    public ResponseEntity<Enchere> publishBidBrouillon(@RequestBody EnchereRequest e) {
        System.out.println(e);
        Enchere publisheBid = iEnchereService.CreateEnchereBrouillon(e);
        return ResponseEntity.ok(publisheBid);
    }


    @PostMapping("/scheduleBidPublication/{idEnchere}")
    public Enchere scheduleBidPublication(@RequestBody ConfigPredéfiniesEnchere enchere,
                                          @RequestParam("publicationDate") LocalDateTime publicationDate,
                                          @PathVariable String idEnchere) {
     return  iEnchereService.scheduleBidPublication(enchere , publicationDate , idEnchere);
    }


    @PostMapping("/publishNow")
    public ResponseEntity<Enchere> publishBidNow(@RequestBody ConfigPredéfiniesEnchere e , @RequestParam String id) {
        Enchere publisheBid = iEnchereService.publishBidNow(e , id);
        return ResponseEntity.ok(publisheBid);
    }
    @PostMapping("/demandeModification/{enchereId}")
    public ResponseEntity<String> demandeModificationEnchere(@PathVariable String enchereId,
                                                             @RequestBody Enchere enchereModifications,
                                                             @RequestParam User user) {
        String result = iEnchereService.demandeModificationEnchere(enchereId, user, enchereModifications);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public List<Enchere> getAll(){
        return iEnchereService.getAll();
    }

    @GetMapping("/enchereDetail")
    public Enchere getEnchereDetailById(@RequestParam  String id){
        return iEnchereService.findBidById(id);
    }
    @PostMapping("/annuler")
    public ResponseEntity<String> annulerEnchereEtRembourser(
            @RequestParam("enchereId") String enchereId,
            @RequestParam("userId") String userId) {

        // Récupération de l'utilisateur à partir de l'ID
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body("Utilisateur non trouvé pour l'ID: " + userId);
        }

        // Appel du service pour annuler l'enchère et rembourser les participants
        String resultat = iEnchereService.annulerEnchereEtInformerParticipants(enchereId, user);

        // Renvoie la réponse basée sur le résultat de l'opération
        if (resultat.contains("succès")) {
            return ResponseEntity.ok(resultat);
        } else {
            return ResponseEntity.badRequest().body(resultat);
        }
    }

    @GetMapping("/findBid")
    public ResponseEntity<List<Enchere>> findBid(
            @RequestParam(required = false) CategoryFille category,
            @RequestParam(required = false) StatusEnchere status) {

        List<Enchere> encheres = iEnchereService.findEnchere(Optional.of(category), Optional.of(status));
        if(encheres.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(encheres);
        }
    }

    @PostMapping("/annulation/{enchereId}")
    public ResponseEntity<String> demandeAnnulationEnchere(@PathVariable String enchereId, @RequestBody User user) {
        String resultat = iEnchereService.demandeAnnulationEnchere(enchereId, user);
        return ResponseEntity.ok(resultat);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnchere(@PathVariable String id) {
        String resultat = iEnchereService.deleteEnchere(id);
        return ResponseEntity.ok(resultat);
    }

    @PostMapping("/valider/{demandeId}")
    public ResponseEntity<String> validerDemandeModification(@PathVariable String demandeId) {
        try {
            String result = iEnchereService.validerDemandeModification(demandeId);
            // Vous pourriez vouloir envoyer une notification push ici, selon la logique de votre application.
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Gérer l'exception si nécessaire et retourner une réponse appropriée
            return ResponseEntity.badRequest().body("Erreur lors de la validation de la demande: " + e.getMessage());
        }
    }
    @PutMapping("/epingler/{id}")
    public ResponseEntity<Enchere> epinglerEnchere(@PathVariable String id) {
        Enchere enchere = iEnchereService.epinglerEnchere(id);
        return ResponseEntity.ok(enchere);
    }

    @PutMapping("/desepingler/{id}")
    public ResponseEntity<Enchere> desepinglerEnchere(@PathVariable String id, @RequestParam Integer nombreMois) {
        Enchere enchere = iEnchereService.desepinglerEnchere(id, nombreMois);
        return ResponseEntity.ok(enchere);
    }

    @GetMapping("/status/{status}")
    public List<Enchere> getPremieresEncheresByStatus(@PathVariable StatusEnchere status) {
        return iEnchereService.getFirst20EncheresByStatus(StatusEnchere.valueOf(String.valueOf(status)));
    }

    @GetMapping("/gratuites")
    public List<Enchere> getEncheresGratuites() {
        return iEnchereService.getEncheresGratuit();
    }

    @GetMapping("/status/{status}/category/{categoryId}")
    public List<Enchere> getTop20EncheresByStatusAndCategory(@PathVariable StatusEnchere status, @PathVariable String categoryId) {
        return iEnchereService.getTop20EncheresByStatusAndCategory(StatusEnchere.valueOf(String.valueOf(status)), categoryId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Enchere> getEncheresByCategory(@PathVariable String categoryId) {
        return iEnchereService.getEncheresByCategory(categoryId);
    }


}

