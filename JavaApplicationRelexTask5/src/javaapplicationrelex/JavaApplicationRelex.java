package javaapplicationrelex;

public class JavaApplicationRelex {
    public static void main(String[] args){
        
        //Gardener gard = new Gardener();
        //теперь считываем из файла --2 задача--
        //Gardener gard = new Gardener("WateringInformation");
        //--4 задача-- количество машин и имя файла откуда считывать
        
        Gardener gard = new Gardener(2, "WateringInformation");
        gard.startWorkDay();
    }
}
