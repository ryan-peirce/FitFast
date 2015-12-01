package fitfast.fitness;
import java.util.*;

public class FreeBody 
{
    private static double calories;

    /*
    @pre-condition: takes five parameters: two doubles and an int
    @post-condition: returns an int of calories burned by workout based on parameteres
    */
    public static int calcCalories( double bodyWeight, int reps, int sets, double coeff, int subtype )
    {

        calories = bodyWeight * coeff * reps * sets;    // formula for calories burned

        int cals = (int) calories;
        
        return cals;
    }

    
    
}
