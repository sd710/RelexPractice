package javaapplicationrelex;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaApplicationRelex {
    public static void main(String[] args){
        
        //Gardener gard = new Gardener();
        //теперь считываем из файла --2 задача--
        Gardener gard = new Gardener("WateringInformation");
        gard.startWorkDay();
    }
}
