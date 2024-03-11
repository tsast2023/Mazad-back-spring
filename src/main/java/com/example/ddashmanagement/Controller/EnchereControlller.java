package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Dto.ConfigPredéfiniesEnchere;
import com.example.ddashmanagement.Dto.EnchereRequest;
import com.example.ddashmanagement.Entites.Enchere;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Services.IEnchereService;
import com.example.ddashmanagement.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bid")
public class EnchereControlller {
    private final IEnchereService iEnchereService ;
    private final UserService userService;

    public EnchereControlller(IEnchereService iEnchereService, UserService userService) {
        this.iEnchereService = iEnchereService;
        this.userService = userService;
    }

    @PostMapping("/createEnchereBrouillon")
    public ResponseEntity<Enchere> publishBidBrouillon(@RequestBody EnchereRequest e) {
        System.out.println(e);
        Enchere publisheBid = iEnchereService.CreateEnchereBrouillon(e);
        return ResponseEntity.ok(publisheBid);
    }


    @PostMapping("/publishNow")
    public ResponseEntity<Enchere> publishBidNow(@RequestBody ConfigPredéfiniesEnchere e , @RequestParam String id) {
        Enchere publisheBid = iEnchereService.publishBidNow(e , id);
        return ResponseEntity.ok(publisheBid);
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
}
