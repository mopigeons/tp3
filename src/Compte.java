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

    //Constructeur de la classe Compte
    public Compte(int so, int ni, Date ouv, Date ferm, int quotaDL) {
        if (!(ferm != null || so >= minSolde)) {
            throw new IllegalArgumentException("Précond: Constructeur de compte: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(quotaDL <= maxDepotLiquide)) {
            throw new IllegalArgumentException("Précond: Constructeur de compte: Le montant en dépot liquide ne doit pas être supérieur au maximum permissible.");
        }
        solde = so;
        nip = ni;
        ouverture = ouv;
        fermeture = ferm;
        quotaDepotLiquide = quotaDL;
        invariants();
    }

    //Test les invariants de la classe Compte
    private void invariants() {
        if (!(fermeture != null || solde >= minSolde)) {
            throw new IllegalStateException("Invariant de Compte: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(quotaDepotLiquide <= maxDepotLiquide)) {
            throw new IllegalStateException("Invariant de Compte: Le montant en dépot liquide ne doit pas être supérieur au maximum permissible.");
        }
    }

}
