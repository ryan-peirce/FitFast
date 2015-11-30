package fitfast.fitness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Prediction 
{
    public static final int CALSPERPOUND = 3500;

    
    
    
    private static int calcDiff(Date currentDate, String goalDate) 
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

        try 
        {
            Date date2 = myFormat.parse(goalDate);
            long diff = date2.getTime() - currentDate.getTime();
            
            
            int timeBetween = (int) TimeUnit.DAYS.convert( diff, TimeUnit.MILLISECONDS );
            return timeBetween;
            
        } 
        catch ( Exception e) 
        {
            System.err.println("Something Went Wrong!");
        }
        return 0;
    
    }

    public static double calcDailyCals(double goalWeight, double currentWeight, int difference)
    {
        

        double goalWeightChange =  goalWeight - currentWeight;

        
        double goalCalChange = CALSPERPOUND * goalWeightChange;
        double dailyCalDiff = goalCalChange / difference;
        
        return dailyCalDiff;
        
        
        
        
        
        
        
    }
}
