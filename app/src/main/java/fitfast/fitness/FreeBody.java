package fitfast.fitness;
import java.util.*;

public class FreeBody 
{
    private static double calories;

    
    public static int calcCalories( double bodyWeight, int reps, int sets, double coeff, int subtype )
    {

        calories = bodyWeight * coeff * reps * sets;

        int cals = (int) calories;
        
        return cals;
    }

    
    
}
