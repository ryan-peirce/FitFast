package fitfast.fitness;
import java.util.*;

public class WeightLift
{

    private static double calories;


    
    public static int calcCalories( double bodyWeight, double weight, int reps, int sets, double coeff, int subType )
    {   

        
        
        if (subType == 0 )
            calories =  weight * coeff * reps * sets ;
        else
            calories = ( weight + bodyWeight ) * coeff * reps * sets;
        
        int cals = (int) calories;
        
        return cals;
    }

    

    
}
