/**
 * Created by Simon on 2016-04-10.
 */
public class TestBanque {


    //methode principale de test
    public static void testBanqueMain() {

        int okCounter = 0;
        int totalCounter = 0;
        int errorCounter = 0;

        Date d1 = new Date(10,4,2016);
        System.out.println("**************************************");
        System.out.println("***         TESTS: BANQUE          ***");
        System.out.println("**************************************");

        //TEST constructeur:
        System.out.println("\n** Tests: Constructeur");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        Banque b1 = new Banque(new Date(10,4,2016));
        System.out.println("ok");
        okCounter++;


        //TEST ouvrirCompte:
        System.out.println("\n** Tests: ouvrirCompte");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        b1.ouvrirCompte(500, 1, new Date (1,4,2015));
        b1.ouvrirCompte(550, 2, new Date(1,3,2012));
        b1.ouvrirCompte(600, 3, new Date(1,1,2000));
        b1.ouvrirCompte(15, 4, new Date(2, 28, 2010));
        System.out.println("ok");
        okCounter++;
        //cas invalide: soldeInit < minSolde
        totalCounter++;
        System.out.println("Cas invalide: soldeInit < minSolde");
        try {
            b1.ouvrirCompte(Compte.getMinSolde()-1, 5, new Date (1,4,2015));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: maxNum comptes déjà atteint
        totalCounter++;
        System.out.println("Cas invalide: nombre max de comptes déjà atteint");
        Banque b2 = new Banque(new Date(10,4,2016));
        b2.ouvrirCompte(500, 1, new Date (1,4,2015));
        b2.ouvrirCompte(550, 2, new Date(1,3,2012));
        b2.ouvrirCompte(600, 3, new Date(1,1,2000));
        b2.ouvrirCompte(15, 4, new Date(2, 28, 2010));
        b2.ouvrirCompte(100, 5, new Date(2, 28, 2010));
        try {
            b2.ouvrirCompte(1000, 6, new Date(1,1,2000));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch (IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: compte existe déjà
        totalCounter++;
        System.out.println("Cas invalide: le compte existe déjà");
        try{
            b1.ouvrirCompte(500, 1, new Date (1,4,2015));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch (IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }



        //TEST fermerCompte:
        System.out.println("\n** Tests: fermerCompte");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        Banque b3 = new Banque(new Date(10,4,2016));
        b3.ouvrirCompte(1, 1, new Date (1,4,2015)); //ouvre un nouv compte avec solde =1
        b3.retraitC(1,1); //retire le solde du compte
        b3.fermerCompte(1, new Date(10,4,2016)); //ferme le compte
        System.out.println("ok");
        okCounter++;
        //cas invalide: compte inexistant
        totalCounter++;
        System.out.println("Cas invalide: compte inexistant");
        try{
            b3.fermerCompte(2, new Date(10,4,2016));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: solde inégale à minSolde
        totalCounter++;
        System.out.println("Cas invalide: solde inégal à minSolde");
        try{
            b3.ouvrirCompte(100, 2, new Date(1,1,2000));
            b3.fermerCompte(2, new Date(10,4,2016));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: le compte a déjà une date de fermeture
        totalCounter++;
        System.out.println("Cas invalide: Le compte est déjà fermé (date de fermeture présente)");
        b3.ouvrirCompte(100, 3, new Date(1,1,2000));
        b3.retraitC(3, 100);
        b3.fermerCompte(3, new Date(10,4,2015));
        try{
            b3.fermerCompte(3, new Date(10,4,2016));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: QuotaDepotLiquide dépassé
        totalCounter++;
        System.out.println("Cas invalide: Quote Dépot Liquide dépassé");
        b3.ouvrirCompte(100,4, new Date(1,1,2000));
        b3.retraitC(4,100);
        b3.comptes().get(4).setQuotaDepotLiquide(Compte.getMaxDepotLiquide()+1);
        try{
            b3.fermerCompte(4, new Date(10,4,2016));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }


        //TEST supprimerCompte:
        System.out.println("\n** Tests: supprimerCompte");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        Banque b4 = new Banque(new Date(10,4,2016));
        b4.ouvrirCompte(1, 1, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(1,1); //retire le solde du compte
        b4.fermerCompte(1, new Date(10,4,2012)); //ferme le compte
        b4.supprimerCompte(1, new Date(10,4,2016));
        System.out.println("ok");
        okCounter++;
        //cas invalide: compte inexistant
        totalCounter++;
        System.out.println("Cas invalide: compte inexistant");
        try{
            b4.supprimerCompte(2, new Date(10,4,2016));
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: solde inégale à minSolde
        totalCounter++;
        b4.ouvrirCompte(1, 2, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(2,1); //retire le solde du compte
        b4.fermerCompte(2, new Date(10,4,2012)); //ferme le compte
        b4.comptes().get(2).setSolde(100);
        System.out.println("Cas invalide: solde inégale à minSolde");
        try{
            b4.supprimerCompte(2, d1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: 2 ans d'attente pas respecté
            //année pas ok
        totalCounter++;
        b4.ouvrirCompte(100, 3, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(3,100); //retire le solde du compte
        b4.fermerCompte(3, new Date(10,4,2015)); //ferme le compte
        System.out.println("Cas invalide: 2 ans d'attente pas respecté (année pas ok)");
        try{
            b4.supprimerCompte(3, d1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
            //année ok, mois non
        totalCounter++;
        b4.ouvrirCompte(100, 4, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(4,100); //retire le solde du compte
        b4.fermerCompte(4, new Date(10,5,2014)); //ferme le compte
        System.out.println("Cas invalide: 2 ans d'attente pas respecté (année ok, mois non)");
        try{
            b4.fermerCompte(4, d1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
            //année, mois ok, jour non
        totalCounter++;
        b4.ouvrirCompte(100, 5, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(5,100); //retire le solde du compte
        b4.fermerCompte(5, new Date(11,5,2014)); //ferme le compte
        System.out.println("Cas invalide: 2 ans d'attente pas respecté (année+mois ok, jour non)");
        try{
            b4.supprimerCompte(5, d1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //TEST retraitC:
        Banque b5 = new Banque(d1);
        b5.ouvrirCompte(400, 1, d1);
        System.out.println("\n** Tests: retraitC");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        b5.retraitC(1, 100);
        System.out.println("ok");
        okCounter++;
        //cas invalide: compte inexistant
        totalCounter++;
        System.out.println("Cas invalide: compte inexistant");
        try{
            b5.retraitC(2, 100);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: montant retrait non positif
        totalCounter++;
        System.out.println("Cas invalide: montant retiré ne peut pas être négatif");
        try{
            b5.retraitC(1, -100);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: solde inférieur au solde minimal
        totalCounter++;
        System.out.println("Cas invalide: retrait fait passer en dessous du solde minimal");
        try{
            b5.retraitC(1, 600);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //TEST depotC:
        System.out.println("\n** Tests: depotC");
        //cas valide
        totalCounter++;
        Banque b6 = new Banque(d1);
        b6.ouvrirCompte(100, 1, d1);
        System.out.println("Cas valide:");
        b6.depotC(1, 100);
        System.out.println("ok");
        okCounter++;
        //cas invalide: compte inexistant
        totalCounter++;
        System.out.println("Cas invalide: compte inexistant");
        try{
            b6.depotC(2,100);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: compte fermé
        totalCounter++;
        b6.retraitC(1, 200);
        b6.fermerCompte(1, d1);
        System.out.println("Cas invalide: compte fermé");
        try{
            b6.depotC(1, 100);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //TEST depotLC
        System.out.println("\n** Tests: depotLC");
        //cas valide
        totalCounter++;
        Banque b7 = new Banque(d1);
        b7.ouvrirCompte(100, 1, d1);
        System.out.println("Cas valide:");
        b7.depotLC(1, 100);
        System.out.println("ok");
        okCounter++;
        //cas invalide: compte inexistant
        totalCounter++;
        System.out.println("Cas invalide: compte inexistant");
        try{
            b7.depotLC(2, 100);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: dépot fait dépasser le Quota Dépot Liquide
        totalCounter++;
        System.out.println("Cas invalide: dépot fait dépasser le quota dépot liquide");
        try{
            b7.depotLC(1, 99999999);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: le compte est fermé
        totalCounter++;
        b7.retraitC(1, 200);
        b7.fermerCompte(1, d1);
        System.out.println("Cas invalide: compte fermé");
        try{
            b7.depotLC(1, 100);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //TEST virementC
        System.out.println("\n** Tests: virementC");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        Banque b8 = new Banque(d1);
        b8.ouvrirCompte(100, 1, d1);
        b8.ouvrirCompte(100, 2, d1);
        b8.virementC(1, 2, 50);
        System.out.println("ok");
        okCounter++;
        //cas invalide: compte 1 inexistant
        totalCounter++;
        System.out.println("Cas invalide: compte 1 inexistant");
        try{
            b8.virementC(3, 2, 50);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: compte 2 inexistant
        totalCounter++;
        System.out.println("Cas invalide: compte 2 inexistant");
        try{
            b8.virementC(2, 3, 50);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: compte 1 et compte 2 identique
        totalCounter++;
        System.out.println("Cas invalide: compte 1 et 2 identiques");
        try{
            b8.virementC(1,1,50);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: montant à transférer est négatif
        totalCounter++;
        System.out.println("Cas invalide: transfert d'un montant négatif");
        try{
            b8.virementC(1, 2, -1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: le virement fait passer le solde sous le solde minimal
        totalCounter++;
        b8.ouvrirCompte(10, 3, d1);
        System.out.println("Cas invalide: virement fait passer compte 1 sous le solde minimal (on suppose solde min = 0)");
        try{
            b8.virementC(3, 1, 11);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: le compte 1 est fermé
        totalCounter++;
        b8.ouvrirCompte(100, 4, new Date(1,1,2000));
        b8.retraitC(4, 100);
        b8.fermerCompte(4, new Date(10,4,2012));
        System.out.println("Cas invalide: compte 1 est fermé");
        try{
            b8.virementC(4, 2, 1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: le compte 2 est fermé
        totalCounter++;
        System.out.println("Cas invalide: compte 2 est fermé");
        try{
            b8.virementC(2,4, 1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //TEST bilanV
        System.out.println("\n** Tests: bilanV");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");

        System.out.println("ok");
        okCounter++;
        //cas invalide: le jour ne correspond pas
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: le mois ne correspond pas
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: le jour et le mois ne correspondent pas
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //TEST transactionVersAutreBanque
        System.out.println("\n** Tests: transactionVersAutreBanque");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");

        System.out.println("ok");
        okCounter++;
        //cas invalide: compte inexistant
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: montant transféré négatif
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: compte fermé
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: nouveau solde ne respecte pas le solde minimal
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //TEST transactionProvenanceAutreBanque
        System.out.println("\n** Tests: transactionVersAutreBanque");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");

        System.out.println("ok");
        okCounter++;
        //cas invalide: compte inexistant
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: montant transféré négatif
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: compte fermé
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        //cas invalide: nouveau solde ne respecte pas le solde minimal
        totalCounter++;
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        //Résumé des tests
        System.out.println("");
        System.out.println("NOMBRE DE TESTS EFFECTUÉS: " + totalCounter);
        System.out.println("SUCCÈS: " + okCounter);
        System.out.println("ÉCHECS: " + errorCounter);


    }
}
