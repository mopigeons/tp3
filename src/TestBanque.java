/**
 * Created by Simon on 2016-04-10.
 */
public class TestBanque {


    //methode principale de test
    public static void testBanqueMain() {
        Date d1 = new Date(10,4,2016);
        System.out.println("**************************************");
        System.out.println("***         TESTS: BANQUE          ***");
        System.out.println("**************************************");

        //TEST constructeur:
        System.out.println("\n** Tests: Constructeur");
        //cas valide
        System.out.println("Cas valide:");
        Banque b1 = new Banque(new Date(10,4,2016));
        System.out.println("ok");


        //TEST ouvrirCompte:
        System.out.println("\n** Tests: ouvrirCompte");
        //cas valide
        System.out.println("Cas valide:");
        b1.ouvrirCompte(500, 1, new Date (1,4,2015));
        b1.ouvrirCompte(550, 2, new Date(1,3,2012));
        b1.ouvrirCompte(600, 3, new Date(1,1,2000));
        b1.ouvrirCompte(15, 4, new Date(2, 28, 2010));
        System.out.println("ok");
        //cas invalide: soldeInit < minSolde
        System.out.println("Cas invalide: soldeInit < minSolde");
        try {
            b1.ouvrirCompte(Compte.getMinSolde()-1, 5, new Date (1,4,2015));
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: maxNum comptes déjà atteint
        System.out.println("Cas invalide: nombre max de comptes déjà atteint");
        Banque b2 = new Banque(new Date(10,4,2016));
        b2.ouvrirCompte(500, 1, new Date (1,4,2015));
        b2.ouvrirCompte(550, 2, new Date(1,3,2012));
        b2.ouvrirCompte(600, 3, new Date(1,1,2000));
        b2.ouvrirCompte(15, 4, new Date(2, 28, 2010));
        b2.ouvrirCompte(100, 5, new Date(2, 28, 2010));
        try {
            b2.ouvrirCompte(1000, 6, new Date(1,1,2000));
        } catch (IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: compte existe déjà
        System.out.println("Cas invalide: le compte existe déjà");
        try{
            b1.ouvrirCompte(500, 1, new Date (1,4,2015));
        } catch (IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }



        //TEST fermerCompte:
        System.out.println("\n** Tests: fermerCompte");
        //cas valide
        System.out.println("Cas valide:");
        Banque b3 = new Banque(new Date(10,4,2016));
        b3.ouvrirCompte(1, 1, new Date (1,4,2015)); //ouvre un nouv compte avec solde =1
        b3.retraitC(1,1); //retire le solde du compte
        b3.fermerCompte(1, new Date(10,4,2016)); //ferme le compte
        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide: compte inexistant");
        try{
            b3.fermerCompte(2, new Date(10,4,2016));
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: solde inégale à minSolde
        System.out.println("Cas invalide: solde inégal à minSolde");
        try{
            b3.ouvrirCompte(100, 2, new Date(1,1,2000));
            b3.fermerCompte(2, new Date(10,4,2016));
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: le compte a déjà une date de fermeture
        System.out.println("Cas invalide: Le compte est déjà fermé (date de fermeture présente)");
        b3.ouvrirCompte(100, 3, new Date(1,1,2000));
        b3.retraitC(3, 100);
        b3.fermerCompte(3, new Date(10,4,2015));
        try{
            b3.fermerCompte(3, new Date(10,4,2016));
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: QuotaDepotLiquide dépassé
        System.out.println("Cas invalide: Quote Dépot Liquide dépassé");
        b3.ouvrirCompte(100,4, new Date(1,1,2000));
        b3.retraitC(4,100);
        b3.comptes().get(4).setQuotaDepotLiquide(Compte.getMaxDepotLiquide()+1);
        try{
            b3.fermerCompte(4, new Date(10,4,2016));
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }


        //TEST supprimerCompte:
        System.out.println("\n** Tests: supprimerCompte");
        //cas valide
        System.out.println("Cas valide:");
        Banque b4 = new Banque(new Date(10,4,2016));
        b4.ouvrirCompte(1, 1, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(1,1); //retire le solde du compte
        b4.fermerCompte(1, new Date(10,4,2012)); //ferme le compte
        b4.supprimerCompte(1, new Date(10,4,2016));
        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide: compte inexistant");
        try{
            b4.supprimerCompte(2, new Date(10,4,2016));
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: solde inégale à minSolde
        b4.ouvrirCompte(1, 2, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(2,1); //retire le solde du compte
        b4.fermerCompte(2, new Date(10,4,2012)); //ferme le compte
        b4.comptes().get(2).setSolde(100);
        System.out.println("Cas invalide: solde inégale à minSolde");
        try{
            b4.supprimerCompte(2, d1);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: 2 ans d'attente pas respecté
            //année pas ok
        b4.ouvrirCompte(100, 3, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(3,100); //retire le solde du compte
        b4.fermerCompte(3, new Date(10,4,2015)); //ferme le compte
        System.out.println("Cas invalide: 2 ans d'attente pas respecté (année pas ok)");
        try{
            b4.supprimerCompte(3, d1);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
            //année ok, mois non
        b4.ouvrirCompte(100, 4, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(4,100); //retire le solde du compte
        b4.fermerCompte(4, new Date(10,5,2014)); //ferme le compte
        System.out.println("Cas invalide: 2 ans d'attente pas respecté (année ok, mois non)");
        try{
            b4.fermerCompte(4, d1);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
            //année, mois ok, jour non
        b4.ouvrirCompte(100, 5, new Date (1,4,2010)); //ouvre un nouv compte avec solde =1
        b4.retraitC(5,100); //retire le solde du compte
        b4.fermerCompte(5, new Date(11,5,2014)); //ferme le compte
        System.out.println("Cas invalide: 2 ans d'attente pas respecté (année+mois ok, jour non)");
        try{
            b4.supprimerCompte(5, d1);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }

        //TEST retraitC:
        Banque b5 = new Banque(d1);
        b5.ouvrirCompte(400, 1, d1);
        System.out.println("\n** Tests: retraitC");
        //cas valide
        System.out.println("Cas valide:");
        b5.retraitC(1, 100);
        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide: compte inexistant");
        try{
            b5.retraitC(2, 100);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: montant retrait non positif
        System.out.println("Cas invalide: montant retiré ne peut pas être négatif");
        try{
            b5.retraitC(1, -100);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: solde inférieur au solde minimal
        System.out.println("Cas invalide: retrait fait passer en dessous du solde minimal");
        try{
            b5.retraitC(1, 600);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }

        //TEST depotC:
        System.out.println("\n** Tests: depotC");
        //cas valide
        Banque b6 = new Banque(d1);
        b6.ouvrirCompte(100, 1, d1);
        System.out.println("Cas valide:");
        b6.depotC(1, 100);
        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide: compte inexistant");
        try{
            b6.depotC(2,100);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: compte fermé
        b6.retraitC(1, 200);
        b6.fermerCompte(1, d1);
        System.out.println("Cas invalide: compte fermé");
        try{
            b6.depotC(1, 100);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }

        //TEST depotLC
        System.out.println("\n** Tests: depotLC");
        //cas valide
        Banque b7 = new Banque(d1);
        b7.ouvrirCompte(100, 1, d1);
        System.out.println("Cas valide:");
        b7.depotLC(1, 100);
        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide: compte inexistant");
        try{
            b7.depotLC(2, 100);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: dépot fait dépasser le Quota Dépot Liquide
        System.out.println("Cas invalide: dépot fait dépasser le quota dépot liquide");
        try{
            b7.depotLC(1, 99999999);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: le compte est fermé
        b7.retraitC(1, 200);
        b7.fermerCompte(1, d1);
        System.out.println("Cas invalide: compte fermé");
        try{
            b7.depotLC(1, 100);
        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }

        //TEST virementC
        System.out.println("\n** Tests: virementC");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte 1 inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: compte 2 inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: compte 1 et compte 2 identique
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: montant à transférer est négatif
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: le virement fait passer le solde sous le solde minimal
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: le compte 1 est fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: le compte 2 est fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }

        //TEST bilanV
        System.out.println("\n** Tests: bilanV");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: le jour ne correspond pas
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: le mois ne correspond pas
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: le jour et le mois ne correspondent pas
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }

        //TEST transactionVersAutreBanque
        System.out.println("\n** Tests: transactionVersAutreBanque");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: montant transféré négatif
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: compte fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: nouveau solde ne respecte pas le solde minimal
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }

        //TEST transactionProvenanceAutreBanque
        System.out.println("\n** Tests: transactionVersAutreBanque");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: montant transféré négatif
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: compte fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }
        //cas invalide: nouveau solde ne respecte pas le solde minimal
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
        }


    }
}
