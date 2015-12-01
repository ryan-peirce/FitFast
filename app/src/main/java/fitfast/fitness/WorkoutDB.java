package fitfast.fitness;
import java.util.*;
/*
unused class, used for documentation and reference
*/
public class WorkoutDB
{
  private static Map<String,Integer> idList = new HashMap<>();
  private static Map<Integer,Double> coList = new HashMap<>();

  public WorkoutDB()
  {
    super();
    loadDB();
  }

  public static void loadDB()
  {
    idList.clear();
    //cardio
    idList.put("run",0);
    idList.put("walk",1);
    
    //bodyweight
    idList.put("pushups",10);
    idList.put("situps",11);

    
    
    //weightlifiting
    idList.put("curls",100);
    idList.put("benchpress",101);
    idList.put("squats",102);
    idList.put("deadlifts",103);
    
    coList.clear();
    coList.put(0,0.0124);
    coList.put(1,0.0085);

    coList.put(10,0.0009);          // 0.036 per min *assume 40/min, 0.0009 cal/pushup
    coList.put(11,0.000655);          // 0.0262 per min *assume 40/min, 0.000655 cal/pushup
    
    
    
    coList.put(100,0.005832861);    
    coList.put(101,0.00661057);  // cal/pound/rep  //physics/anatomy calculation
    coList.put(102,0.00972);    //assume distance lifted is 1.7ft
    coList.put(103,0.00972);    //assume distance lifted is 1.7ft
  }

  public static Integer getId(String name)
  {
    if(idList.containsKey(name)){
      return idList.get(name);
    }
    return -1;
  }

  public static double getCoefficient(Integer id)
  {
    if(coList.containsKey(id)){
      return coList.get(id);
    }
    return -1.0;
  }

}
