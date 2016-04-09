/**
 * Created by echal.
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.IntStream;

public class Transactions {
    private static int minSolde = 0;
    private int compteur = -1;
    private Date dateOuverture = new Date (15,5,2016);
    private Map<Integer, Banque> banques;

    public Transactions() {
        banques = new HashMap<>();
    }

    public void transactionBancaire (int b1, int n1, int b2, int n2, int m) {

        if (!banques.containsKey(b1) && !banques.containsKey(b2)){
            throw new IllegalArgumentException("Precond de Transactions: b1 ou b2 ne font pas partie des banques.");
        }
        if (b1 == b2){
            throw new IllegalArgumentException("Precond de Transactions: b1 et b2 ont le même numéro de banque.");

        }
        if (banques.get(b1) == banques.get(b2)){
            throw new IllegalArgumentException("Precond de Transactions: les 2 banques ont des numéros différents mais sont en fait la même banque.");

        }
        if (!banques.get(b1).comptes().containsKey(n1) && !banques.get(b2).comptes().containsKey(n2)){
            throw new IllegalArgumentException("Precond de Transactions: le(s) compte(s) ciblés n'existent pas dans leur banque respective.");
        }
        if ((banques.get(b1).comptes().get(n1).getFermeture() == null) && (banques.get(b2).comptes().get(n2).getFermeture() == null){
            throw new IllegalArgumentException("Precond de Transactions: le(s) compte(s) ciblés sont fermés.");
        }
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
        banques.get(b1).transactionVersAutreBanque(n1, m);
        banques.get(b2).transactionEnProvenanceAutreBanque(n2,m);
            //les numéros de banque sont associés à une banque unique

            //#4: "Prendre soin que le solde global des montants qui sortent des banques soit le même que celui reçu par ces banques"
            //Vérification que les entrées et les sorties des banques soient égales (pas de création ou de perte d'argent)  [n'arrive pas à vérifier: "exceeded time limit"]
            //(+ over (for i::banques.dom yield banques[i].entrees)) = (+ over (for j::banques.dom yield banques[j].sorties))

        /*if (IntStream.of(mois31Jours).anyMatch(x -> x == mois)){
            if (jour < 1 && jour > 31){
                throw new IllegalArgumentException("Invariant de Date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        }*/
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
    }


    //Test les invariants de la classe Transactions
    private void invariants() {
        // num de banque associé à une banque unique
        // for
            // for
                throw new IllegalArgumentException("Invariant de Transactions: 2 banques ont le même numéro de banque dans 'banques'.");
    }
}

//    Iterator<Entry<Integer, comptes>> it = comptes.iterator();
/*
while(it.hasNext()){

        }
        for (int i = 0; i < comptes.count ){
        banques.put(i,comptes[i]);
        }*/