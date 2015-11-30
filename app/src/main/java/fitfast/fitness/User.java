package fitfast.fitness;
import java.util.*;

public class User
{
    public static double CalcBMI( double bodyWeight, double height )
    {
        return ( 703 * ( bodyWeight / ( height * height ) ) );
    }

    public static double CalcBMR( boolean gender, double bodyWeight, double height, int age )
    {


        if ( gender )
        {
            return 655 + ( 4.35 * bodyWeight ) + ( 4.7 * height ) - ( 4.7 * age );  // female
        }
        else
        {
            return 66 + ( 6.23 * bodyWeight ) + ( 12.7 * height ) - ( 6.8 * age );     // male
        }
    }
    
    public static double CalcDailyCals(  boolean gender, double bodyWeight, double height, int age, int activityLevel )
    {
        double baseCal = CalcBMR( gender, bodyWeight, height, age );
        double coefficient = 1;
        
        switch( activityLevel )
        {
            case 1:
                coefficient = 1.2;
                break;
            case 2:
                coefficient  = 1.375;
                break;
            case 3:
                coefficient = 1.55;
                break;
            case 4:
                coefficient = 1.725;
                break;
            case 5:
                coefficient = 1.9;
                break;
        }
        
        return baseCal * coefficient;
        
    }
}