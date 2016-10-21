/*
    Садовник дает команду Машине (WaterCart) начать работу.
    Проверка состоянии клумбы
    Перемещение к клумбе
    Полив клумбы
*/
package javaapplicationrelex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gardener {
    public void startWorkDay() throws InterruptedException{
        //Flowerbed flowerbed = new Flowerbed("A");
        ArrayList<Flowerbed> LFlowerbeds = new ArrayList<>();
        LFlowerbeds.add(new Flowerbed("A"));
        LFlowerbeds.add(new Flowerbed("B"));
        LFlowerbeds.add(new Flowerbed("C"));
        LFlowerbeds.add(new Flowerbed("D"));
        
        try {
            FileWriter fWriter = new FileWriter("test.txt");
            fWriter.write("qwerty");
                    
        } catch (IOException ex) {
            Logger.getLogger(Gardener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        WaterCart waterCart = new WaterCart(LFlowerbeds);
        
        while(true){
            //flowerbed.changeTemperature();
            LFlowerbeds.stream().map((fl) -> {
                fl.changeTemperature();
                return fl;
            }).forEach((fl) -> {
                if (!waterCart.checkFlowerbed(fl)){
                    waterCart.goToFlowerbed(fl);
                    waterCart.waterFlowers(fl);
                }
                else
                    System.out.printf("Клумбу %s не нужно поливать, температура: %s\n", fl.getNameFlowerbed(), fl.getPrintFormatTemperature());
            });
           Thread.sleep(10000);
        }
    }
}
