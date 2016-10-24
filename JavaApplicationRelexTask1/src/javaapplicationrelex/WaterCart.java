/*
    Машина
    Список обрабатываемых клумб
    Проверка клумб для полива
        Если температура больше 30, проверка времени последнего полива, если время null, то нужно полить
    Перемещение к клумбе
    Полив клумбы
        Запоминать время полива
    Окончание полива
*/
package javaapplicationrelex;

import java.util.ArrayList;
import java.util.Date;

public class WaterCart {
    //список клумб и дата последнего полива соответсвющей клумбы
    ArrayList<Sensor> listBedsSensors;
    
    
    public WaterCart(ArrayList<Flowerbed> flowerbeds) {
        listBedsSensors = new ArrayList<>();
        for(Flowerbed flowerbed : flowerbeds){
            listBedsSensors.add(new Sensor(flowerbed.getNameFlowerbed()));
        }
    }
    
    public void checkFlorebed(){
        for (Sensor flowerbed: listBedsSensors){
            flowerbed.changeTemperature();
            if (flowerbed.GetHumiditySensor(new Date()))
                goToFlowerbed(flowerbed);
            else
                System.out.printf("Клумбу %s не нужно поливать, температура: %s\n", flowerbed.getNameFlowerbed(), flowerbed.getPrintTemperature());
        }
    }
    
    /* перемещение к заданной клумбе */
    public void goToFlowerbed(Sensor flowers){   
        System.out.printf("Я еду к клумбе %s... Дорога займёт 5 мин...\n", flowers.getNameFlowerbed());
        waterFlowers(flowers);
    }
    
    /* полив заданной клумбы */
    public void waterFlowers(Sensor flowers){
        System.out.println("Я на месте");

        System.out.printf("Я поливаю клумбу %s... Это займёт 10 мин...\n", flowers.getNameFlowerbed());
        flowers.setLastWateringDate(new Date());
        endWaterFlowers();
    }
    
    /* окончание полива */
    public void endWaterFlowers(){
        System.out.println("Клумба полита\n");
    }
}