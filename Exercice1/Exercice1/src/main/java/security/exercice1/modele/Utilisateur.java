package security.exercice1.modele;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Utilisateur {

    private int idUtilisateur;
    private static int IDS=0;

    private String login;
    private String motDePasse;

    public Utilisateur(String login, String motDePasse) {
        this.idUtilisateur = IDS++;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public boolean verifierMotDePasse(String motDePasse){
        return this.motDePasse.equals(motDePasse);
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();

        root.put("login", this.login);
        try {
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
