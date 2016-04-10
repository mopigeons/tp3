/**
 * Created by Simon on 2016-04-10.
 */
public class TestBanque {


    //methode principale de test
    public static void testBanqueMain() {
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
            System.out.println(e);
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
            System.out.println(e);
        }
        //cas invalide: compte existe déjà
        System.out.println("Cas invalide: le compte existe déjà");

        //TEST fermerCompte:
        System.out.println("\n** Tests: fermerCompte");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: solde inégale à minSolde
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: le compte a déjà une date de fermeture
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: QuotaDepotLiquide dépassé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }

        //TEST supprimerCompte:
        System.out.println("\n** Tests: supprimerCompte");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: solde inégale à minSolde
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: 2 ans d'attente pas respecté
            //année pas ok
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
            //année ok, mois non
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
            //année, mois ok, jour non
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }

        //TEST retraitC:
        System.out.println("\n** Tests: retraitC");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: solde non positif
            //solde 0
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
            //solde négatif
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: solde inférieur au solde minimal
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }

        //TEST depotC:
        System.out.println("\n** Tests: depotC");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: compte fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }

        //TEST depotLC
        System.out.println("\n** Tests: depotLC");
        //cas valide
        System.out.println("Cas valide:");

        System.out.println("ok");
        //cas invalide: compte inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: dépot fait dépasser le Quota Dépot Liquide
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: le compte est fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

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

        }
        //cas invalide: compte 2 inexistant
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: compte 1 et compte 2 identique
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: montant à transférer est négatif
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: le virement fait passer le solde sous le solde minimal
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: le compte 1 est fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: le compte 2 est fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

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

        }
        //cas invalide: le mois ne correspond pas
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: le jour et le mois ne correspondent pas
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

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

        }
        //cas invalide: montant transféré négatif
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: compte fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: nouveau solde ne respecte pas le solde minimal
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

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

        }
        //cas invalide: montant transféré négatif
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: compte fermé
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }
        //cas invalide: nouveau solde ne respecte pas le solde minimal
        System.out.println("Cas invalide:");
        try{

        } catch(IllegalArgumentException e) {

        }


    }
}
