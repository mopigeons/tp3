/**
 * \file Transactions.java
 * \brief Définition des transactions inter-banque
 * \author Étienne Chalifour 111011736 B-IFT, Marc-Gabriel Morin-Grandmont 111103591 B-IFT, Simon Rodier 111083079 B-IFT
 * \date Avril 2016
 *
 * Travail pratique #3
 *
 */
import java.util.Map;
import java.util.HashMap;

public class Transactions {
    private static int minSolde = 0;
    private int compteur = -1;
    private Date dateOuverture = new Date (15,5,2016);
    private Map<Integer, Banque> banques;

    public Transactions() {
        banques = new HashMap<>();
    }

    public void transactionBancaire (int b1, int n1, int b2, int n2, int m) {
        // On vérifie que les banques font patie du système
        if (!banques.containsKey(b1) && !banques.containsKey(b2)){
            throw new IllegalArgumentException("Precond de Transactions: b1 ou b2 ne font pas partie des banques.");
        }
        // Les banques doivent être différentes
        if (b1 == b2){
            throw new IllegalArgumentException("Precond de Transactions: b1 et b2 ont le même numéro de banque.");// Les banques doivent être différentes
        }
        // On vérifie que les 2 banques ne sont pas la même banque sous des numéros différents
        if (banques.get(b1) == banques.get(b2)){
            throw new IllegalArgumentException("Precond de Transactions: les 2 banques ont des numéros différents mais sont en fait la même banque.");

        }
        // On vérifie que les comptes existent dans leur banque respective
        if (!banques.get(b1).comptes().containsKey(n1) && !banques.get(b2).comptes().containsKey(n2)){
            throw new IllegalArgumentException("Precond de Transactions: le(s) compte(s) ciblés n'existent pas dans leur banque respective.");
        }
        // On vérifie que les comptes ne sont pas fermés
        if (!(banques.get(b1).comptes().get(n1).getFermeture() == null) && (banques.get(b2).comptes().get(n2).getFermeture() == null)){
            throw new IllegalArgumentException("Precond de Transactions: le(s) compte(s) ciblés sont fermés.");
        }
        // Transfert valide >0
        if (m <= 0){
            throw new IllegalArgumentException("Precond de Transactions: transfert nul ou inversé. Le transfert dans être positif");
        }
        //on vérifie que minSolde est respecté pour le compte transmetteur
        if ((banques.get(b1).comptes().get(n1).getSolde() - m - (banques.get(b1).fraisTrans()) < minSolde)){
            throw new IllegalArgumentException("Precond de Transactions: fonds insuffisants dans le compte source pour procéder à la transaction.");
        }
        //on vérifie que minSolde est respecté pour le compte recevant - au cas où que m < frais de transaction
        if ((banques.get(b2).comptes().get(n2).getSolde() + m - (banques.get(b2).fraisTrans()) < minSolde)){
            throw new IllegalArgumentException("Precond de Transactions: fonds insuffisants dans le compte source pour procéder à la transaction.");
        }
        // On exécute le transfert
        banques.get(b1).transactionVersAutreBanque(n1, m);
        banques.get(b2).transactionEnProvenanceAutreBanque(n2,m);

        //Test les invariants de la classe Transactions
        try {
            //Test les invariants
            invariants();
        }
        catch (IllegalStateException e) {
            // Relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Transactions, méthode transactionBancaire - " + e.getMessage());
        }
    }
    public void ajouterNouvelleBanque() {
        compteur++;
        banques.put(compteur, new Banque(dateOuverture));
        //Test les invariants de la classe Transactions
        try {
            //Test les invariants
            invariants();
        } catch (IllegalStateException e) {
            // Relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Transactions, méthode ajouterNouvelleBanque - " + e.getMessage());
        }
    }
    public void ajouterCompteABanque(int nb, int solde, int nc, Date d) {
        banques.get(nb).ouvrirCompte(solde, nc, d);
        //Test les invariants de la classe Transactions
        try {
            //Test les invariants
            invariants();
        } catch (IllegalStateException e) {
            // Relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Transactions, méthode ajouterCompteABanque - " + e.getMessage());
        }
    }
    public void fermerCompteABanque(int nb, int nc, Date d) {
        banques.get(nb).fermerCompte(nc, d);
        //Test les invariants de la classe Transactions
        try {
            //Test les invariants
            invariants();
        } catch (IllegalStateException e) {
            // Relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Transactions, méthode ajouterCompteABanque - " + e.getMessage());
        }
    }
    //Test les invariants de la classe Transactions
    private void invariants() {
        // numéro de banque associé à une banque unique
        for (int i = 0; i <= banques.size(); i++ )
             for (int j = i+1 ; j<= banques.size(); j++){
                    if (banques.get(i) == banques.get(j)){
                     throw new IllegalArgumentException("Invariant de Transactions: 2 banques ont le même numéro de banque dans 'banques'.");
                 }
             }
    }
}