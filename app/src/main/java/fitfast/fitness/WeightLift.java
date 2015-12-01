package fitfast.fitness;
import java.util.*;

public class WeightLift
{

    private static double calories;


    /*
    @pre-condition: takes six parameters, three doubles and three ints
    @post-condition: returns calories burned by weightlifting activity as an int based on parameters
    */
    public static int calcCalories( double bodyWeight, double weight, int reps, int sets, double coeff, int subType )
    {   

        
        
        if (subType == 0 )      //workout that doesn't incorporate body weight
            calories =  weight * coeff * reps * sets ;      //calories burned by weightlifting activity
        else    // workout includes bodyweight 
            calories = ( weight + bodyWeight ) * coeff * reps * sets;  //calories burned by weightlifting activity
        
        int cals = (int) calories;
        
        return cals;
    }

    

    
}
