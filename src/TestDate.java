/**
 * \file TestDate.java
 * \brief Vérification de la spécification de la classe Date
 * \author Étienne Chalifour 111011736 B-IFT, Marc-Gabriel Morin-Grandmont 111103591 B-IFT, Simon Rodier 111083079 B-IFT
 * \date Avril 2016
 *
 * Travail pratique #3
 *
 */

public class TestDate {

    //methode principale de test
    public static void testDateMain() {

        int okCounter = 0;
        int totalCounter = 0;
        int errorCounter = 0;

        System.out.println("**************************************");
        System.out.println("****         TESTS: DATE          ****");
        System.out.println("**************************************");


        //TEST constructeur:
        System.out.println("\n** Tests: Constructeur");
        //cas valide
        totalCounter++;
        System.out.println("Cas valide:");
        Date d1 = new Date(17, 8, 2003);
        System.out.println("ok");
        okCounter++;

        System.out.println("Cas invalide: le jour est inférieur à 1");
        totalCounter++;
        try {
            d1 = new Date(0, 5, 1999);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le jour est supérieur à 31 pour les mois ayant 31 jours");
        totalCounter++;
        try {
            d1 = new Date(32, 1, 2000);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le jour est supérieur à 30 pour les mois ayant 30 jours");
        totalCounter++;
        try {
            d1 = new Date(31, 4, 2000);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le jour est supérieur à 28 pour le mois de Février et l'année n'est pas bissextile");
        totalCounter++;
        try {
            d1 = new Date(29, 2, 1999);
            throw new IllegalStateException("ERREUR: Cas invalide doit provoquer une exception");
        }
        catch(IllegalArgumentException e) {
            System.out.println("*" + e.getMessage());
            okCounter++;
        } catch(IllegalStateException e) {
            System.out.println("*" + e.getMessage());
            errorCounter++;
        }

        System.out.println("Cas invalide: le jour est supérieur à 29 pour le mois de Février et l'année est bissextile");
        totalCounter++;
        try {
            d1 = new Date(30, 2, 2016);
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