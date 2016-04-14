/**
 * Created by echal on 16-04-12.
 */
public class TestCompte {
    //methode principale de test
    public static void testCompteMain() {

        int okCounter = 0;
        int totalCounter = 0;
        int errorCounter = 0;

        Date dateOuverture = new Date(15, 5, 2016);
        Date dateFermeture = new Date(20, 5, 2016);
        System.out.println("**************************************");
        System.out.println("***         TESTS: COMPTE          ***");
        System.out.println("**************************************");


        //TEST constructeur:
        System.out.println("\n** Tests: Constructeur");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        Compte c1 = new Compte(10, 0, dateOuverture, null, 0);
        System.out.println("ok");
        okCounter++;

        System.out.println("Cas invalide: la date de fermeture n'est pas null");
        totalCounter++;
        try {
            Compte c2 = new Compte(10, 0, dateOuverture, (new Date(20,5,2016)), 0);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le solde est plus petit que minSolde");
        totalCounter++;
        try {
            Compte c3 = new Compte(-1, 0, dateOuverture, null, 5000);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le quota dépôt liquide du compte excède le maximum autorisé");
        totalCounter++;
        try {
            Compte c3 = new Compte(10, 0, dateOuverture, null, 50000);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("\n** Tests: retrait");
        System.out.println("Cas valide:");
        totalCounter++;
        c1 = new Compte(10, 0, dateOuverture, null, 0);
        c1.retrait(5);
        System.out.println("ok");
        okCounter++;

        System.out.println("Cas invalide: la date de fermeture n'est pas null");
        totalCounter++;
        try {
            c1 = new Compte(10, 0, dateOuverture, null, 0);
            c1.setDateF(dateFermeture);
            c1.retrait(5);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le solde - retrait est plus petit que minSolde");
        totalCounter++;
        try {
            c1 = new Compte(10, 0, dateOuverture, null, 0);
            c1.retrait(15);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        System.out.println("\n** Tests: depot");
        System.out.println("Cas valide:");
        totalCounter++;
        c1 = new Compte(10, 0, dateOuverture, null, 0);
        c1.depot(100);
        System.out.println("ok");
        okCounter++;

        System.out.println("Cas invalide: la date de fermeture n'est pas null");
        totalCounter++;
        try {
            c1 = new Compte(10, 0, dateOuverture, null, 0);
            c1.setDateF(dateFermeture);
            c1.depot(100);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        System.out.println("Cas invalide: le solde + depot est plus petit que minSolde (opération inverse)");
        totalCounter++;
        try {
            c1 = new Compte(10, 0, dateOuverture, null, 0);
            c1.depot(-20);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("\n** Tests: depotLiquide");
        System.out.println("Cas valide:");
        totalCounter++;
        c1 = new Compte(10, 0, dateOuverture, null, 0);
        c1.depotLiquide(100);
        System.out.println("ok");
        okCounter++;

        System.out.println("Cas invalide: la date de fermeture n'est pas null");
        totalCounter++;
        try {
            c1 = new Compte(10, 0, dateOuverture, null, 0);
            c1.setDateF(dateFermeture);
            c1.depotLiquide(5);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        System.out.println("Cas invalide: le solde + depôt est plus petit que minSolde (opération inverse)");
        totalCounter++;
        try {
            c1 = new Compte(10, 0, dateOuverture, null, 0);
            c1.depotLiquide(-20);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }
        System.out.println("Cas invalide: le montant du dépôt dépasse le quota du dépôt liquide");
        totalCounter++;
        try {
            c1 = new Compte(10, 0, dateOuverture, null, 0);
            c1.depotLiquide(50000);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("\n** Tests: modifierNIP");
        System.out.println("Cas valide:");
        totalCounter++;
        c1 = new Compte(10, 1857, dateOuverture, null, 0);
        c1.modifierNIP(5930);
        System.out.println("ok");
        okCounter++;

        System.out.println("Cas invalide: NIP identique à l'ancien");
        totalCounter++;
        try {
            c1 = new Compte(10, 1857, dateOuverture, null, 0);
            c1.modifierNIP(1857);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("\n** Tests: reinitDepotLiquide");
        System.out.println("Cas valide:");
        totalCounter++;
        c1 = new Compte(10, 0, dateOuverture, null, 5000);
        c1.reinitDepotLiquide();
        System.out.println("ok");
        okCounter++;

        //Résumé des tests
        System.out.println("");
        System.out.println("NOMBRE DE TESTS EFFECTUÉS: " + totalCounter);
        System.out.println("SUCCÈS: " + okCounter);
        System.out.println("ÉCHECS: " + errorCounter);
    }
}
