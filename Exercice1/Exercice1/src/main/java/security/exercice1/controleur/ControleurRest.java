package security.exercice1.controleur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.exercice1.modele.FacadeApplication;
import security.exercice1.modele.FacadeUtilisateurs;
import security.exercice1.modele.Question;
import security.exercice1.modele.Utilisateur;
import security.exercice1.modele.exception.LoginDejaUtiliseException;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControleurRest {

    @Autowired
    FacadeUtilisateurs facadeUtilisateurs;
    FacadeApplication facadeApplication;



    @PostMapping(value = "/inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        try {
            int idUtilisateur = facadeUtilisateurs.inscrireUtilisateur(body.getLogin(), body.getMotDePasse());
            String location = "http://localhost:8080/api/utilisateur/"+idUtilisateur;
            root.put("location", location);
            root.put("idUtilisateur", idUtilisateur);
            root.put("utilisateur", facadeUtilisateurs.getUtilisateurByLogin(body.getLogin()).toString());
            return ResponseEntity.ok(mapper.writeValueAsString(root));
        } catch(LoginDejaUtiliseException e) {
            root.put("erreur", "Pseudo déjà pris !");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mapper.writeValueAsString(root));
        } catch(Exception e) {
            root.put("erreur", "Erreur technique !");
            return ResponseEntity.internalServerError().body(mapper.writeValueAsString(root));
        }
    }





}
