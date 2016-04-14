import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

/**
 * Created by Simon on 2016-04-03.
 */


public class Compte {

    private int solde;
    private int nip;
    private Date ouverture;
    private Date fermeture;
    private int quotaDepotLiquide;

    private static int minSolde = 0;
    private static int maxDepotLiquide = 10000;

    //getters
    public int getSolde() {
        return solde;
    }

    public int getNip() {
        return nip;
    }
    
    public Date getOuverture() {
        return ouverture;
    }

    public int getQuotaDepotLiquide() {
        return quotaDepotLiquide;
    }

    public Date getFermeture() {
        return fermeture;
    }

    public static int getMinSolde() {
        return minSolde;
    }

    public static int getMaxDepotLiquide() {
        return maxDepotLiquide;
    }

    //Les 'setters' sont utilisés seulement pour les tests unitaires et devraient être désactivé dans un programme final
    public void setQuotaDepotLiquide(int dl) {
        quotaDepotLiquide = dl;
    }

    public void setSolde(int so) {solde = so; }

    public void setDateO(Date douv) {ouverture = douv; }

    public void setDateF(Date dferm) {fermeture = dferm; }

    //Constructeur de la classe Compte
    public Compte(int so, int ni, Date ouv, Date ferm, int quotaDL) {
        if (ferm != null || so < minSolde) {
            throw new IllegalArgumentException("Précond: Constructeur de compte: Un compte doit être ouvert et avec un solde >= au solde minimal.");
        }
        if (!(quotaDL <= maxDepotLiquide)) {
            throw new IllegalArgumentException("Précond: Constructeur de compte: Le montant en dépot liquide ne doit pas être supérieur au maximum permissible.");
        }
        solde = so;
        nip = ni;
        ouverture = ouv;
        fermeture = ferm;
        quotaDepotLiquide = quotaDL;

        //test des invariants de Compte
        try {
            //teste les invariants
            invariants();
        }
        catch (IllegalStateException e) {
            //relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Constructeur Compte - " + e.getMessage());
        }

    }

    public void retrait(int n) {
        //test des préconditions
        if (fermeture != null || solde-n < minSolde) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être ouvert et ouvert avec un solde >= au solde minimal.");
        }

        //corps de la méthode
        solde = solde-n;

        //test des invariants
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Compte, méthode retrait - " + e.getMessage());
        }
    }

    public void depot(int n) {
        //test des préconditions
        if (fermeture != null || solde+n < minSolde) {
            throw new IllegalArgumentException("Précond: Compte, méthode depot: Un compte doit être ouvert et avec un solde >= au solde minimal.");
        }

        //corps de la méthode
        solde = solde + n;

        //test des invariants
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Compte, méthode depot - " + e.getMessage());
        }
    }

    public void depotLiquide(int n) {
        //test des préconditions
        if (fermeture != null || solde+n < minSolde) {
            throw new IllegalArgumentException("Précond: Compte, méthode depotLiquide: Un compte doit être ouvert et avec un solde >= au solde minimal.");
        }
        if (!(quotaDepotLiquide+n <= maxDepotLiquide)) {
            throw new IllegalArgumentException("Précond: Compte, méthode depotLiquide: le montant déposé fait dépasser le maxDepotLiquide.");
        }

        //corps de la méthode
        solde = solde + n;
        quotaDepotLiquide = quotaDepotLiquide + n;

        //test des invariants
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Compte, méthode depotLiquide - " + e.getMessage());
        }
    }

    public void modifierNIP(int nNIP) {
        //test des préconditions
        if(nNIP == nip) {
            throw new IllegalArgumentException("Précond: Compte, méthode modifierNIP: Le nouveau NIP doit être différent de l'ancien.");
        }

        //corps de la méthode
        nip = nNIP;

        //test des invariants
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Compte, méthode modifierNIP - " + e.getMessage());
        }
    }

    public void reinitDepotLiquide() {
        //test des préconditions
        //aucune précondition

        //corps de la méthode
        quotaDepotLiquide = 0;

        //test des invariants
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Compte, méthode reinitDepotLiquide - " + e.getMessage());
        }
    }

    //Test les invariants de la classe Compte
    private void invariants() {
        if (fermeture != null || solde < minSolde) {
            throw new IllegalStateException("Invariant de Compte: Un compte doit être ouvert et avec un solde >= au solde minimal.");
        }
        if (!(quotaDepotLiquide <= maxDepotLiquide)) {
            throw new IllegalStateException("Invariant de Compte: Le montant en dépot liquide ne doit pas être supérieur au maximum permissible.");
        }
    }

}
