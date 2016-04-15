/**
 * \file Compte.java
 * \brief Définition d'une banque et ses méthodes associés
 * \author Étienne Chalifour 111011736 B-IFT, Marc-Gabriel Morin-Grandmont 111103591 B-IFT, Simon Rodier 111083079 B-IFT
 * \date Avril 2016
 *
 * Travail pratique #3
 *
 */
import java.util.HashMap;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.util.Iterator;
import java.util.Set;


public class Banque{

    private HashMap<Integer, Compte> comptes;
    private int soldeG;
    private int soldeV;
    private int entrees;
    private int sorties;
    private int gains;
    private Date dateEF;

    private static int fraisTransaction = 3;
    private static int maxNum = 5;

    public Banque(Date d) {
        this.comptes = new HashMap<Integer, Compte>();
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
        if (!(soldeInit>=0)) { //Parce qu'on ne peut pas récupérer directement le soldeMin d'un compte non créé
            throw new IllegalArgumentException("Banque, ouvrirCompte : Le solde initial doit être supérieur au solde minimal");
        }
        if (!(comptes.size()<maxNum)) {
            throw new IllegalArgumentException("Banque, ouvrirCompte : Le nombre maximal de compte pour cette banque est atteint");
        }
        if (comptes.containsKey(nc)) {
            throw new IllegalArgumentException("Banque, ouvrirCompte : Le compte spécifié est déjà dans la banque");
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
            throw new IllegalArgumentException("Banque, fermerCompte : Le compte spécifié n'est pas dans la banque");
        }
        if (!(comptes.get(nc).getSolde()==Compte.getMinSolde())) {
            throw new IllegalArgumentException("Banque, fermerCompte : Il reste de l'argent dans le compte");
        }
        if (!(comptes.get(nc).getFermeture()==null)) {
            throw new IllegalArgumentException("Banque, fermerCompte : Le compte ne peut pas être fermé pour le moment");
        }
        if (!(comptes.get(nc).getQuotaDepotLiquide()<=Compte.getMaxDepotLiquide())) {
            throw new IllegalArgumentException("Banque, fermerCompte : Le quota de dépôt liquide est dépassé");
        }

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
            throw new IllegalArgumentException("Banque, supprimerCompte : Le compte spécifié n'est pas dans la banque");
        }
        if (!(comptes.get(nc).getSolde()==Compte.getMinSolde())) {
            throw new IllegalArgumentException("Banque, supprimerCompte : Il reste de l'argent dans le compte");
        }
        if (!((d.getAn()>comptes.get(nc).getFermeture().getAn()+2)
                || (d.getAn()==comptes.get(nc).getFermeture().getAn()+2 && d.getMois()>comptes.get(nc).getFermeture().getMois())
                || (d.getAn()==comptes.get(nc).getFermeture().getAn()+2 && d.getMois()==comptes.get(nc).getFermeture().getMois() && d.getJour()>comptes.get(nc).getFermeture().getJour()))) {
                    throw new IllegalArgumentException("Banque, supprimerCompte : On ne peut pas supprimer le compte pour l'instant");
                }

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
            throw new IllegalArgumentException("Banque, retraitC : Le compte spécifié n'est pas dans la banque");
        }
        if (!(n>0)) {
            throw new IllegalArgumentException("Banque, retraitC : Le montant retiré doit être positif");
        }
        if (!(comptes.get(nc).getFermeture() == null || comptes.get(nc).getSolde()-n>=Compte.getMinSolde())) {
            throw new IllegalArgumentException("Banque, retraitC : Le solde du compte doit être supérieur au solde minimal");
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
            throw new IllegalArgumentException("Banque, depotC : Le compte spécifié n'est pas dans la banque");
        }
        if (!(comptes.get(nc).getFermeture() == null)) {
            throw new IllegalArgumentException("Banque, depotC : Le compte doit être ouvert");
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
            throw new IllegalArgumentException("Banque, depotLC : Le compte spécifié n'est pas dans la banque");
        }
        if (!(comptes.get(nc).getQuotaDepotLiquide()+n <= Compte.getMaxDepotLiquide())) {
            throw new IllegalArgumentException("Banque, depotLC : Le quota pour le dépôt en liquide à été atteint");
        }
        if (!(comptes.get(nc).getFermeture() == null)) {
            throw new IllegalArgumentException("Banque, depotLC : Le compte doit être ouvert");
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
            throw new IllegalArgumentException("Banque, virementC : Le compte spécifié n'est pas dans la banque");
        }
        if (!(comptes.containsKey(nc2))) {
            throw new IllegalArgumentException("Banque, virementC : Le compte spécifié n'est pas dans la banque");
        }
        if (nc1==nc2) {
            throw new IllegalArgumentException("Banque, virementC : Les deux comptes sont identiques");
        }
        if (!(n>0)) {
            throw new IllegalArgumentException("Banque, virementC : Le montant doit être positif");
        }
        if (!(comptes.get(nc1).getFermeture() == null || comptes.get(nc1).getSolde()-n>=Compte.getMinSolde())) {
            throw new IllegalArgumentException("Banque, virementC : Le solde du compte doit être supérieur au solde minimal");
        }
        if (!(comptes.get(nc2).getFermeture() == null)) {
            throw new IllegalArgumentException("Banque, virementC : Le compte doit être ouvert");
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
        if (!(d.getJour()==dateEF.getJour())) {
            throw new IllegalArgumentException("Banque, bilanV : Les jours doivent correspondre");
        }
        if (!(d.getMois()==dateEF.getMois())) {
            throw new IllegalArgumentException("Banque, bilanV : Les mois doivent correspondre");
        }

        int NsoldeV=0;
        int NsoldeG=0;
        Set cles = comptes.keySet();
        Iterator it = cles.iterator();
        while(it.hasNext())
        {
            Integer cle = (Integer) it.next();
            NsoldeG+=comptes.get(cle).getSolde();
            NsoldeV+=comptes.get(cle).getSolde();
            comptes.get(cle).reinitDepotLiquide();
        }
        soldeV=NsoldeV;
        soldeG=NsoldeG;
        entrees=0;
        sorties=0;

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
            throw new IllegalArgumentException("Banque, transactionVersAutreBanque : Le compte spécifié n'est pas dans la banque");
        }
        if (!(m>=0)) {
            throw new IllegalArgumentException("Banque, transactionVersAutreBanque : Le montant à transférer doit être positif");
        }
        if (!(comptes.get(nc).getFermeture()==null)) {
            throw new IllegalArgumentException("Banque, transactionVersAutreBanque : Le compte doit être ouvert");
        }
        if (!(comptes.get(nc).getSolde()-(m+fraisTransaction)>=Compte.getMinSolde())) {
            throw new IllegalArgumentException("Banque, transactionVersAutreBanque : Le solde du compte doit être supérieur au solde minimal");
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
            throw new IllegalArgumentException("Banque, transactionEnProvenanceAutreBanque : Le compte spécifié n'est pas dans la banque");
        }
        if (!(m>=0)) {
            throw new IllegalArgumentException("Banque, transactionEnProvenanceAutreBanque : Le montant à transférer doit être positif");
        }
        if (!(comptes.get(nc).getFermeture()==null)) {
            throw new IllegalArgumentException("Banque, transactionEnProvenanceAutreBanque : Le compte doit être ouvert");
        }
        if (!(comptes.get(nc).getSolde()-(m+fraisTransaction)>=Compte.getMinSolde())) {
            throw new IllegalArgumentException("Banque, transactionEnProvenanceAutreBanque : Le solde du compte doit être supérieur au solde minimal");
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

    public HashMap<Integer, Compte> comptes() {
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
