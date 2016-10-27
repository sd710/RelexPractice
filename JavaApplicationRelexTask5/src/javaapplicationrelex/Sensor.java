package javaapplicationrelex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sensor {
    private Flowerbed flowerbed;
    private Thermometer temperature;
    private Date lastWateringDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private boolean humiditySensor;
    static private int countFlowerbed;
    
    private int timeWatering;
    private int timeMoveToFlowerbeds;
    
    public Sensor(String name){
        this.flowerbed = new Flowerbed(name);
        this.lastWateringDate = null;
        this.temperature = new Thermometer(0);
        humiditySensor = false;
        
        //--3 задача-- если информации нет, то принято считать то что каждая новая клумба находится дальше
        // время в пути больше на 5 минут.
        countFlowerbed++;
        timeMoveToFlowerbeds = countFlowerbed * 5;
        timeWatering = countFlowerbed + 10;
    }
    
    // добавление клумбы с имеющимися характеристиками --2 задача--
    public Sensor(String name, String lastWaterung, double temperature){
        flowerbed = new Flowerbed(name);
        this.temperature = new Thermometer(temperature);
        
        try {
            lastWateringDate = dateFormat.parse(lastWaterung);
        } catch (ParseException ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
        humiditySensor = false;
    }
    
    //--3 задача--
    public Sensor(String name, String lastWaterung, double temperature, int timeMoveTo, int timeWatering ){
        flowerbed = new Flowerbed(name);
        this.temperature = new Thermometer(temperature);
        
        try {
            lastWateringDate = dateFormat.parse(lastWaterung);
        } catch (ParseException ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.timeMoveToFlowerbeds = timeMoveTo;
        this.timeWatering = timeWatering;
        countFlowerbed++;
        humiditySensor = false;
    }
    
    public int getTimeWatering()
    {
        return this.timeWatering;
    }
    
    public int getTimeMoveToFlowerbed()
    {
        return this.timeMoveToFlowerbeds;
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
        //currentCalendar.add(Calendar.HOUR, -4);
        currentCalendar.add(Calendar.SECOND, -11);
        Calendar lastWatering = Calendar.getInstance();
        if (lastWateringDate != null){
            lastWatering.setTime(lastWateringDate);
            humiditySensor = currentCalendar.after(lastWatering);
        }
    }
    
    public void setLastWateringDate(Date currentDate){
        lastWateringDate = currentDate;
    }
    
    public String getLastWateringDate(){
        return dateFormat.format(lastWateringDate);
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
