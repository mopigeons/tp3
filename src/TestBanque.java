/**
 * Created by Simon on 2016-04-10.
 */
public class TestBanque {


    //methode principale de test
    public static void testBanqueMain() {
        //TEST constructeur:
        //cas valide
        Banque b1 = new Banque(new Date(10,4,2016));


        //TEST ouvrirCompte:
        //cas valide
        b1.ouvrirCompte(500, 1, new Date(1,4,2015));
        b1.ouvrirCompte(550, 2, new Date(1,3,2012));
        b1.ouvrirCompte(600, 3, new Date(1,1,2000));
        b1.ouvrirCompte(15, 4, new Date(2, 28, 2010));

        //cas invalide: soldeInit < minSolde
        //cas invalide: maxNum comptes déjà atteint
        //cas invalide: compte existe déjà

        //TEST fermerCompte:
        //cas valide
        //cas invalide: compte inexistant
        //cas invalide: solde inégale à minSolde
        //cas invalide: le compte a déjà une date de fermeture
        //cas invalide: QuotaDepotLiquide dépassé

        //TEST supprimerCompte:
        //cas valide
        //cas invalide: compte inexistant
        //cas invalide: solde inégale à minSolde
        //cas invalide: 2 ans d'attente pas respecté
            //année pas ok
            //année ok, mois non
            //année, mois ok, jour non

        //TEST retraitC:
        //cas valide
        //cas invalide: compte inexistant
        //cas invalide: solde non positif
            //solde 0
            //solde négatif
        //cas invalide: solde inférieur au solde minimal

        //TEST depotC:
        //cas valide
        //cas invalide: compte inexistant
        //cas invalide: compte fermé

        //TEST depotLC
        //cas valide
        //cas invalide: compte inexistant
        //cas invalide: dépot fait dépasser le Quota Dépot Liquide
        //cas invalide: le compte est fermé

        //TEST virementC
        //cas valide
        //cas invalide: compte 1 inexistant
        //cas invalide: compte 2 inexistant
        //cas invalide: compte 1 et compte 2 identique
        //cas invalide: montant à transférer est négatif
        //cas invalide: le virement fait passer le solde sous le solde minimal
        //cas invalide: le compte 1 est fermé
        //cas invalide: le compte 2 est fermé

        //TEST bilanV
        //cas valide
        //cas invalide: le jour ne correspond pas
        //cas invalide: le mois ne correspond pas
        //cas invalide: le jour et le mois ne correspondent pas

        //TEST transactionVersAutreBanque
        //cas valide
        //cas invalide: compte inexistant
        //cas invalide: montant transféré négatif
        //cas invalide: compte fermé
        //cas invalide: nouveau solde ne respecte pas le solde minimal

        //TEST transactionProvenanceAutreBanque
        //cas valide
        //cas invalide: compte inexistant
        //cas invalide: montant transféré négatif
        //cas invalide: compte fermé
        //cas invalide: nouveau solde ne respecte pas le solde minimal


    }
}
