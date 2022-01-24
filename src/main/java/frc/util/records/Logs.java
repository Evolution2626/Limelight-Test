package frc.util.records;

import java.util.Vector;
/**
 * Add your docs here.
 */
public class Logs {
    public static Vector<Evenement> evenements = new Vector<Evenement>();
     
    public static void addEvenement(String description){
        evenements.add(new Evenement(description));

    }

    public static void eraseVector(){
        evenements.clear();
    }

    public static String toText(){
       
        return evenements.toString();
    }
}