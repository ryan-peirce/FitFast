package fitfast.fitness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class Prediction 
{
    public static final int CALSPERPOUND = 3500;

    
    
    /*
    @pre-condition: takes two parameters: the first is a date object, the second is a String object
    @post-condition: returns the number of days between the two dates taken as parameters
    */
    private static int calcDiff(Date currentDate, String goalDate) 
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

        try 
        {
            Date date2 = myFormat.parse(goalDate);              // format goalDate to be in dd MM yyyy format
            long diff = date2.getTime() - currentDate.getTime();    //calculate difference in time between the two dates in milliseconds
            
            
            int timeBetween = (int) TimeUnit.DAYS.convert( diff, TimeUnit.MILLISECONDS ); // convert difference oin time to days
            return timeBetween;
            
        } 
        catch ( Exception e) 
        {
            System.err.println("Something Went Wrong!");
        }
        return 0;
    
    }
    /*
    @pre-condition: takes three parameters: the first two are doubles, the third is an int
    @post-condition: returns a double corresponding to the amount of calories that should be added or subtracted from maintainence calories
    */
    public static double calcDailyCals(double goalWeight, double currentWeight, int difference)
    {
        

        double goalWeightChange =  goalWeight - currentWeight;  // difference between goal weight and current weight

        
        double goalCalChange = CALSPERPOUND * goalWeightChange; // difference in calories between goal weight and current weight
        double dailyCalDiff = goalCalChange / difference;  // daily deficit or surplus in calories
        
        return dailyCalDiff;
        
        
        
        
        
        
        
    }
}
