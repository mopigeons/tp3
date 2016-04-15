

public class TestTransaction {

    //methode principale de test
    public static void testTransactionsMain() {

        int okCounter = 0;
        int totalCounter = 0;
        int errorCounter = 0;

        System.out.println("**************************************");
        System.out.println("***      TESTS: TRANSACTIONS       ***");
        System.out.println("**************************************");

        System.out.println("\n** Tests: ajouterCompteABanque");
        System.out.println("Cas valide:");
        totalCounter++;
        Transactions t = new Transactions();
        t.ajouterNouvelleBanque();
        t.ajouterCompteABanque(0, 100, 1, new Date(13, 4, 2016));
        System.out.println("ok");
        okCounter++;

        System.out.println("\n** Tests: fermerCompteABanque");
        System.out.println("Cas valide:");
        totalCounter++;
        t.fermerCompteABanque(0, 1, new Date(15, 4, 2016));
        System.out.println("ok");
        okCounter++;

        System.out.println("\n** Tests: transactionBancaire");
        System.out.println("Cas valide:");
        totalCounter++;
        Transactions t1 = new Transactions();
        t1.ajouterNouvelleBanque();
        t1.ajouterNouvelleBanque();
        t1.ajouterCompteABanque(0, 100, 1, new Date(13, 4, 2016));
        t1.ajouterCompteABanque(1, 100, 2, new Date(13, 4, 2016));
        t1.transactionBancaire(0, 1, 1, 2, 10);
        System.out.println("ok");
        okCounter++;

        System.out.println("Cas invalide: l'une des banques ne fait pas partie des banques dans Transactions");
        totalCounter++;
        try {
            t1.transactionBancaire(0, 1, 3, 2, 0);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: les deux banques sont identiques");
        totalCounter++;
        try {
            t1.transactionBancaire(0, 1, 0, 1, 10);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: au moins l'un des deux comptes n'existe pas dans la banque à laquelle il appartient");
        totalCounter++;
        try {
            t1.transactionBancaire(0, 1, 1, 1, 0);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: au moins un des comptes est fermé");
        totalCounter++;
        try {
            t1.ajouterCompteABanque(0, 100, 3, new Date(13, 4, 2016));
            t1.fermerCompteABanque(0, 3, new Date(15, 4, 2016));
            t1.transactionBancaire(0, 3, 1, 2, 10);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le solde n'est pas positif");
        totalCounter++;
        try {
            t1.transactionBancaire(0, 1, 1, 2, -10);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le compte transmetteur n'a pas un solde assez élevé pour faire la transaction");
        totalCounter++;
        try {
            t1.transactionBancaire(0, 1, 1, 2, 200);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le compte receveur n'a pas un solde assez élevé pour faire la transaction");
        totalCounter++;
        try {
            t1.ajouterCompteABanque(1, 1, 3, new Date(13, 4, 2016));
            t1.transactionBancaire(0, 1, 1, 3, 1);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
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