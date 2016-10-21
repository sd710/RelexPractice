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
import java.util.Calendar;
import java.util.Date;

public class WaterCart {
    //список клумб и дата последнего полива соответсвющей клумбы
    ArrayList<WaterFlowerbedTimer> waterFlowerbedsTimer = new ArrayList<>();

    public WaterCart(ArrayList<Flowerbed> LFlowerbeds) {
        LFlowerbeds.stream().forEach((fl) -> {
            waterFlowerbedsTimer.add(new WaterFlowerbedTimer(fl.getNameFlowerbed(), null));
        });
    }
    
    public boolean checkFlowerbed(Flowerbed flowers){
        for (WaterFlowerbedTimer flowerbed : waterFlowerbedsTimer){
            if (flowerbed.nameFlowerbed.equalsIgnoreCase(flowers.getNameFlowerbed())){
                System.out.printf("Температура клумбы %s: %s \n", flowers.getNameFlowerbed(), flowers.getPrintFormatTemperature());
                if (flowers.checkTemperature()){
                    //System.out.println("Температура больше 30 C, Если поливали больше чем 4 часа назад или не поливали вообще, то надо полить");
                    if (flowerbed.dateWaterFlowerbed != null){
                        Date currentDate = new Date();
                        Calendar currentCalendar = Calendar.getInstance();
                        currentCalendar.setTime(currentDate);
                        currentCalendar.add(Calendar.HOUR, -4);
                        //currentCalendar.add(Calendar.SECOND, -3);
                        Calendar lastWatering = Calendar.getInstance();
                        lastWatering.setTime(flowerbed.dateWaterFlowerbed);
                        if (currentCalendar.after(lastWatering))
                            return false;
                        else
                            System.out.printf("Время последнего полива %s Поливать рано!!!\n", flowerbed.dateWaterFlowerbed.toString());
                    } else{
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }
    /* перемещение к заданной клумбе */
    public void goToFlowerbed(Flowerbed flowers){      
        System.out.printf("Я еду к клумбе %s... Дорога займёт 5 мин...\n", flowers.getNameFlowerbed());
    }
    
    /* полив заданной клумбы */
    public void waterFlowers(Flowerbed flowers){
        System.out.println("Я на месте");

        System.out.printf("Я поливаю клумбу %s... Это займёт 10 мин...\n", flowers.getNameFlowerbed());
        for (WaterFlowerbedTimer flowerbed : waterFlowerbedsTimer){
            if (flowerbed.nameFlowerbed.equalsIgnoreCase(flowers.getNameFlowerbed())){
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                flowerbed.dateWaterFlowerbed = date;
                endWaterFlowers();
                return;
            }
        }
    }
    
    /* окончание полива */
    public void endWaterFlowers(){
        System.out.println("Клумба полита\n");
    }
}