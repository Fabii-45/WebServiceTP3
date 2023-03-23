package security.exercice1.modele;

import org.springframework.stereotype.Component;
import security.exercice1.modele.exception.LoginDejaUtiliseException;
import security.exercice1.modele.exception.UtilisateurInexistantException;

import java.util.HashMap;
import java.util.Map;

@Component("facadeUtilisateurs")
public class FacadeUtilisateurs {

    /** Stocke l'ensemble des utilisateurs inscrits */
    private Map<String,Utilisateur> utilisateursMap;

    public FacadeUtilisateurs() {
        utilisateursMap = new HashMap<>();
    }

    /**
     * Retourne l'identifiant d'un utilisateur, d'après son login (c.-à-d. son adresse email).
     * @param login le login de l'utilisateur dont on veut l'identifiant
     * @return l'identifiant de l'utilisateur.
     * @throws UtilisateurInexistantException s'il n'existe pas d'utilisateur avec ce login
     */
    public int getUtilisateurIntId(String login) throws UtilisateurInexistantException {
        if (utilisateursMap.containsKey(login)) {
            return this.utilisateursMap.get(login).getIdUtilisateur();
        } else {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Retourne un utilisateur, d'après son login.
     * @param login le login de l'utilisateur dont on veut l'identifiant
     * @return l'utilisateur.
     * @throws UtilisateurInexistantException s'il n'existe pas d'utilisateur avec ce login
     */
    public Utilisateur getUtilisateurByLogin(String login) throws UtilisateurInexistantException {
        return utilisateursMap.get(login);
    }

    /**
     * Inscrit un nouvel utilisateur.
     * @param login      le login de l'utilisateur (c.-à-d. son adresse email)
     * @param motDePasse le mot de passe de l'utilisateur
     * @return l'identifiant de l'utilisateur inscrit.
     * @throws LoginDejaUtiliseException si le login est déjà utilisé par un autre utilisateur
     */
    public int inscrireUtilisateur(String login, String motDePasse) throws LoginDejaUtiliseException {
        if (utilisateursMap.containsKey(login)) {
            throw new LoginDejaUtiliseException();
        } else {
            Utilisateur utilisateur = new Utilisateur(login, motDePasse);
            utilisateursMap.put(utilisateur.getLogin(), utilisateur);
            return utilisateur.getIdUtilisateur();
        }
    }

    /**
     * Vérifie si un mot de passe est correct (devient inutile dans la version finale).
     * @param login      le login de l'utilisateur
     * @param motDePasse le mot de passe à vérifier
     * @return _true_ si le mot de passe est correct, _false_ sinon.
     */
    public boolean verifierMotDePasse(String login, String motDePasse) {
        if (utilisateursMap.containsKey(login)) {
            return utilisateursMap.get(login).verifierMotDePasse(motDePasse);
        } else {
            return false;
        }
    }
}