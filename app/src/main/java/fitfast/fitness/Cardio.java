package fitfast.fitness;
import java.util.*;

public class Cardio
{
    private static double calories;


    /*
    @pre-condition: takes four doubles as parameters
    @post-condition: returns the number of calories burned as an int based on parameters
    */
    public static int calcCalories(double bodyWeight, double distance, double time, double coeff)
    {

        double pace = ( distance / time ) * 60;

        calories =  bodyWeight * pace  * coeff * time;  // cardio formula
        int cals = (int) calories;
        
        return cals;
    }

    
    

}




