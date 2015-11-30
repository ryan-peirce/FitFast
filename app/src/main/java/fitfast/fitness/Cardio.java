package fitfast.fitness;
import java.util.*;

public class Cardio
{
    private static double calories;



    public static int calcCalories(double bodyWeight, double distance, double time, double coeff)
    {

        double pace = ( distance / time ) * 60;

        calories =  bodyWeight * pace  * coeff * time;
        int cals = (int) calories;
        
        return cals;
    }

    
    

}




