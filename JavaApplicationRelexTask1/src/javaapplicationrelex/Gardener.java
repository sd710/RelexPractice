/*
    Садовник дает команду Машине (WaterCart) начать работу.
    Проверка состоянии клумбы
    Перемещение к клумбе
    Полив клумбы
*/
package javaapplicationrelex;

import java.util.ArrayList;

public class Gardener {
    public void startWorkDay() throws InterruptedException{
        ArrayList<Flowerbed> LFlowerbeds = new ArrayList<>();
        LFlowerbeds.add(new Flowerbed("A"));
        LFlowerbeds.add(new Flowerbed("B"));
        LFlowerbeds.add(new Flowerbed("C"));
        LFlowerbeds.add(new Flowerbed("D"));        
        
        WaterCart waterCart = new WaterCart(LFlowerbeds);
        
        while(true){
            waterCart.checkFlorebed();
            Thread.sleep(10000);
        }
    }
}
