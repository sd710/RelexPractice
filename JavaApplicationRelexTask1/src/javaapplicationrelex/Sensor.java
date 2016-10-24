package javaapplicationrelex;

import java.util.Calendar;
import java.util.Date;

public class Sensor {
    private Flowerbed flowerbed;
    private Thermometer temperature;
    Date lastWateringDate;
    private boolean humiditySensor;
    
    public Sensor(String name){
        this.flowerbed = new Flowerbed(name);
        this.lastWateringDate = null;
        this.temperature = new Thermometer(0);
        humiditySensor = false;
    }
    
    public Sensor(String name, Date lastWaterung, double temperature){
        flowerbed = new Flowerbed(name);
        this.temperature = new Thermometer(temperature);
        lastWateringDate = lastWaterung;
        humiditySensor = false;
    }
    
    public Flowerbed getFlowerbed(){
        return this.flowerbed;
    }
    
    public void changeTemperature(){
        temperature.changeTemperature();
    }
    
    private void CheckTemperature(){
        humiditySensor = temperature.getTemperature() > 30;
    }
    
    private void checkLastWatering(Date currentDate){
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);
        currentCalendar.add(Calendar.HOUR, -4);
        //currentCalendar.add(Calendar.SECOND, -3);
        Calendar lastWatering = Calendar.getInstance();
        if (lastWateringDate != null){
            lastWatering.setTime(lastWateringDate);
            humiditySensor = currentCalendar.after(lastWatering);
        }
    }
    
    public void setLastWateringDate(Date currentDate){
        lastWateringDate = currentDate;
    }
    
    public String getPrintTemperature(){
        return temperature.getPrintTemperature();
    }
    public void setTemoerature(double temperature){
        this.temperature.setTemperature(temperature);
    }
    
    public String getNameFlowerbed(){
        return flowerbed.getNameFlowerbed();
    }
    public boolean GetHumiditySensor(Date currentDate){
        this.CheckTemperature();
        if (humiditySensor)
            this.checkLastWatering(currentDate);
        return humiditySensor;
    }
}
