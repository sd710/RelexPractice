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
import java.util.List;

public class WaterCart {
    //список клумб и дата последнего полива соответсвющей клумбы
    ArrayList<Sensor> listBedsSensors;
    
    public WaterCart(){
        listBedsSensors = new ArrayList<>();
    }
    
    public WaterCart(ArrayList<Flowerbed> flowerbeds) {
        listBedsSensors = new ArrayList<>();
        flowerbeds.stream().forEach((flowerbed) -> {
            listBedsSensors.add(new Sensor(flowerbed.getNameFlowerbed()));
        });
    }
    
    // для сохранения в файл информации о состоянии всех клумб --2 задача--
    public List<String> GetAllInformation(){
        List<String> allInformation = new ArrayList<>();        
        for(Sensor tmpFlowerbed : listBedsSensors){
            allInformation.add(tmpFlowerbed.getNameFlowerbed());
            allInformation.add(tmpFlowerbed.getLastWateringDate());
            allInformation.add(tmpFlowerbed.getPrintTemperature());
            //--3 задача-- добавление в файл вермени полива и перемещения к клумбе
            allInformation.add(String.valueOf(tmpFlowerbed.getTimeMoveToFlowerbed()));
            allInformation.add(String.valueOf(tmpFlowerbed.getTimeWatering()));
        }
        return allInformation;
    }
    
    // добавление клумбы с имеющимися характеристиками --2 задача--
    public void AddFlowerbed(String name, String lasteDateWatering, double temperature){
        listBedsSensors.add(new Sensor(name, lasteDateWatering, temperature));
    }
    
    //--3 задача--
    public void AddFlowerbed(String name, String lasteDateWatering, double temperature, int timeMoveTo, int timeWatering){
        listBedsSensors.add(new Sensor(name, lasteDateWatering, temperature, timeMoveTo, timeWatering));
    }
    
    public void AddFlowerbed(Sensor newFlowerbed){
        listBedsSensors.add(newFlowerbed);
    }
    
    public void checkFlorebed(){
        listBedsSensors.stream().map((flowerbed) -> {
            flowerbed.changeTemperature();
            return flowerbed;
        }).forEach((flowerbed) -> {
            if (flowerbed.GetHumiditySensor(new Date()))
                goToFlowerbed(flowerbed);
            else
                System.out.printf("Клумбу %s не нужно поливать, температура: %s\n\n", flowerbed.getNameFlowerbed(), flowerbed.getPrintTemperature());
        });
    }
    
    /* перемещение к заданной клумбе */ //--3 задача-- сколько займет дорога к конкретнйо клумбе
    public void goToFlowerbed(Sensor flowers){   
        System.out.printf("Я еду к клумбе %s... Дорога займёт %d мин...\n", flowers.getNameFlowerbed(), flowers.getTimeMoveToFlowerbed());
        waterFlowers(flowers);
    }
    
    /* полив заданной клумбы */ //--3 задача-- полив конкретной клумбы
    public void waterFlowers(Sensor flowers){
        System.out.println("Я на месте");

        System.out.printf("Я поливаю клумбу %s... Это займёт %d мин...\n", flowers.getNameFlowerbed(), flowers.getTimeWatering());
        flowers.setLastWateringDate(new Date());
        endWaterFlowers(flowers);
    }
    
    /* окончание полива */
    public void endWaterFlowers(Sensor flowers){
        System.out.printf("Клумба полита, %s\n\n", flowers.getLastWateringDate());
    }
}