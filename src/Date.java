/**
 * Created by Simon on 2016-04-03.
 */
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.util.stream.IntStream;


public class Date {

    private int jour,mois,an;
    //Constructeur de la classe Date
    public Date(int j, int m, int a) {
        int[] mois31Jours = {1,3,5,7,8,10,12};
        int[] mois30Jours = {4,6,9,11};
        // On vérifie les jours s'il s'agit d'un mois qui a 31 jours
        if (IntStream.of(mois31Jours).anyMatch(x -> x == m)){
            if (j < 1 || j > 31){
                throw new IllegalArgumentException("Précond: Constructeur de date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        }
        // On vérifie les jours s'il s'agit d'un mois qui a 30 jours
        else if (IntStream.of(mois30Jours).anyMatch(x -> x == m)){
            if (j < 1 || j > 30){
                throw new IllegalArgumentException("Précond: Constructeur de date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        }
        // On vérifie les années bissextile
        else if (m == 2 && ((a % 4)==0 && (a % 100)!=0 ) || ((a % 400) == 0)) {
            if (j < 1 || j > 29){
                throw new IllegalArgumentException("Précond: Constructeur de date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois) [année bissextile].");
            }
        } else {
            // On vérifie les jours s'il s'agit d'un mois qui a 31 jours
            if (j < 1 || j > 28){
                throw new IllegalArgumentException("Précond: Constructeur de date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        }


        jour = j;
        mois = m;
        an = a;

        // Test les invariants de Date
        try {
            //Test les invariants
            invariants();
        }
        catch (IllegalStateException e) {
            // Relance le message d'erreur avec un identifiant de la méthode qui cause le problème
            throw new IllegalStateException("Constructeur Date - " + e.getMessage());
        }

    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public int getAn() {
        return an;
    }

    //Test les invariants de la classe Date
    private void invariants() {
        int[] mois31Jours = {1,3,5,7,8,10,12};
        int[] mois30Jours = {4,6,9,11};

        if (IntStream.of(mois31Jours).anyMatch(x -> x == mois)){
            if (jour < 1 && jour > 31){
                throw new IllegalArgumentException("Invariant de Date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        }
        else if (IntStream.of(mois30Jours).anyMatch(x -> x == mois)){
            if (jour < 1 && jour > 30){
                throw new IllegalArgumentException("Invariant de Date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        }
        else if (mois == 2 && ((an % 4)==0 && (an % 100)!=0 ) || ((an % 400) == 0 && (an % 100)!=0)) {
            if (jour < 1 && jour > 29){
                throw new IllegalArgumentException("Invariant de Date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        } else {
            if (jour < 1 && jour > 28){
                throw new IllegalArgumentException("Invariant de Date: Les mois ne respectent pas les règles du calendrier (trop ou pas assez de jours dans le mois).");
            }
        }
    }
}
