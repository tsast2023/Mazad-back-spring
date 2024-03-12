package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Services.IServiceCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    IServiceCategory iServiceCategory;

    @GetMapping("/getAll")
    public List<CategoryFille> getAll(){
       return iServiceCategory.findAllCategorie() ;
        }
    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategorie(@RequestBody CategoryFille  categorie , BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Le libelé de la catégorie ne doit pas être vide");
        }
        iServiceCategory.createCategorie(categorie);
        return ResponseEntity.ok("Catégorie enregistrée avec succès");
    }
    @GetMapping("/findCategories")
    public ResponseEntity<List<CategoryFille>> findCategories(
            @RequestParam(required = false) EtatCategory etat,
            @RequestParam(required = false) StatusCategorie status,
            @RequestParam(required = false) TypeCategory type) {

        List<CategoryFille> categories = iServiceCategory.findCategories(Optional.of(etat), Optional.of(status), Optional.of(type));
        if(categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(categories);
        }
    }
    @PutMapping("/updateCategory")
    public CategoryFille update(@RequestParam  String id , @RequestBody CategoryFille c) {
        return iServiceCategory.updateCategorie(id , c);
    }
    @PostMapping("/changerStatus")
    public String ChangeStaus(@RequestParam String id , @AuthenticationPrincipal User user){
        return iServiceCategory.ChangerStatusDemande(id , user);

    }
    @PutMapping("/validerChangementStatus/{idDemande}")
    public ResponseEntity<String> validerChangementStatusCategorie(@PathVariable String idDemande) {
        String resultat = iServiceCategory.validerChangementStatusCategorie(idDemande);
        return ResponseEntity.ok(resultat);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable String id) {
        CategoryFille category = iServiceCategory.findCategorieById(id); // Supposons que cette méthode existe pour récupérer la catégorie

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Catégorie non trouvée pour l'identifiant: " + id);
        }

        iServiceCategory.deleteCategorie(category);

        // Vérifier si la catégorie a été supprimée
        if (iServiceCategory.findCategorieById(id) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La catégorie ne peut pas être supprimée car elle contient des produits.");
        } else {
            return ResponseEntity.ok().body("Catégorie supprimée avec succès.");
        }
    }

}
