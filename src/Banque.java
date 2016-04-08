import java.util.HashMap;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import Compte;


public class Banque{

    private HashMap<int, Compte> comptes = new HashMap<int Compte>();
    private int soldeG;
    private int soldeV;
    private int entrees;
    private int sorties;
    private int gains;
    private Date dateEF;

    private static int fraisTransaction = 2;
    private static int maxNum = 29999;

    public Banque(Date d) {
        soldeG = 0;
        soldeV = 0;
        entrees = 0;
        sorties = 0;
        gains = 0;
        dateEF = d;

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Constructeur Banque - " + e.getMessage());
        }

    }

    public void ouvrirCompte(int soldeInit, int nc, Date o) {

        //test des préconditions
        if (!(soldeInit>minSolde)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.size()<maxNum)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (comptes.containsKey(nc)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        entrees=entrees+soldeInit;
        soldeV=soldeV+soldeInit;
        comptes.put(nc, new Compte(soldeInit, 0, o, null, 0));

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, ouvrirCompte - " + e.getMessage());
        }
    }

    public void fermerCompte(int nc, Date f) {

        //test des préconditions
        if (!(comptes.containsKey(nc))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getSolde()==minSolde)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getFermeture()==null)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getQuotaDepotLiquide()<=maxDepotLiquide)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        //f within Date

        comptes.replace(nc, new Compte(0, comptes.get(nc).getNip(), comptes.get(nc).getOuverture(), f, comptes.get(nc).getQuotaDepotLiquide()));

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, fermerCompte - " + e.getMessage());
        }
    }

    public void supprimerCompte(int nc, Date d) {

        //test des préconditions
        if (!(comptes.containsKey(nc))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getSolde()==minSolde)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        //fermeture within Date
        //Truc compliqué

        comptes.remove(nc);

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, supprimerCompte - " + e.getMessage());
        }
    }

    public void retraitC(int nc, int n) {

        //test des préconditions
        if (!(comptes.containsKey(nc))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(n>0)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getFermeture() == null || comptes.get(nc).getSolde()-n>=minSolde)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        soldeV=soldeV-n;
        sorties=sorties+n;
        comptes.get(nc).retrait(n);

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, retraitC - " + e.getMessage());
        }
    }

    public void depotC(int nc, int n) {

        //test des préconditions
        if (!(comptes.containsKey(nc))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getFermeture() == null)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        soldeV=soldeV+n;
        entrees=entrees+n;
        comptes.get(nc).depot(n);

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, depotC - " + e.getMessage());
        }
    }

    public void depotLC(int nc, int n) {

        //test des préconditions
        if (!(comptes.containsKey(nc))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getQuotaDepotLiquide()+n <= maxDepotLiquide)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getFermeture() == null)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        soldeV=soldeV+n;
        entrees=entrees+n;
        comptes.get(nc).depotLiquide(n);

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, depotLC - " + e.getMessage());
        }
    }

    public void virementC(int nc1, int nc2, int n) {

        //test des préconditions
        if (!(comptes.containsKey(nc1))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.containsKey(nc2))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(nc1!=nc2)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(n>0)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc1).getFermeture() == null || comptes.get(nc1).getSolde()-n>=minSolde)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc2).getFermeture() == null)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        comptes.get(nc1).retrait(n);
        comptes.get(nc2).depot(n);

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, virementC - " + e.getMessage());
        }
    }

    public void bilanV(Date d) {

        //test des préconditions
        if (!(d.j()==dateEF.j())) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(d.m()==dateEF.m())) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        int NsoldeV=0;
        comptes.forEach((k, v)->NsoldeV+=comptes.get(k).getSolde());
        soldeV=NsoldeV;
        int NsoldeG=0;
        comptes.forEach((k, v)->NsoldeG+=comptes.get(k).getSolde());
        soldeG=NsoldeG;
        entrees=0;
        sorties=0;
        comptes.forEach((k, v)->comptes.get(k).reinitDepotLiquide());

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, bilanV - " + e.getMessage());
        }
    }

    public void transactionVersAutreBanque(int nc, int m) {

        //test des préconditions
        if (!(comptes.containsKey(nc))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(m>=0)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getFermeture()==null)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getSolde()-(m+fraisTransaction)>=minSolde)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        comptes.get(nc).retrait(m+fraisTransaction);
        gains=gains+fraisTransaction;
        sorties=sorties+m;
        soldeV=soldeV-m;

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, transactionVersAutreBanque - " + e.getMessage());
        }
    }

    public void transactionEnProvenanceAutreBanque(int nc, int m) {

        //test des préconditions
        if (!(comptes.containsKey(nc))) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(m>=0)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getFermeture()==null)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }
        if (!(comptes.get(nc).getSolde()-(m+fraisTransaction)>=minSolde)) {
            throw new IllegalArgumentException("Précond: Compte, méthode retrait: Un compte doit être soit fermé, soit ouvert avec un solde >= au solde minimal.");
        }

        comptes.get(nc).depot(m+fraisTransaction);
        gains=gains+fraisTransaction;
        entrees=entrees+m;
        soldeV=soldeV+m;

        //test des invariants de Banque
        try {
            invariants();
        }
        catch (IllegalStateException e) {
            //récupère le message d'erreur et indique où le problème se situe
            throw new IllegalStateException("Banque, transactionEnProvenanceAutreBanque - " + e.getMessage());
        }
    }

    public HashMap<int, Compte> comptes() {
        return comptes;
    }

    public int fraisTrans() {
        return fraisTransaction;
    }

    public int entrees() {
        return entrees;
    }

    public int sorties() {
        return sorties;
    }

    private void invariants() {
        if (!(soldeG + entrees - sorties == soldeV)) {
            throw new IllegalStateException("Invariant de Banque: le solde général additionné aux entrées puis soustrait aux sorties n'est pas égal au solde vérifié ");
        }
    }
}