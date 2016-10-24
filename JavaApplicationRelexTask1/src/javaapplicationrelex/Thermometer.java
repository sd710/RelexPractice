package javaapplicationrelex;
/*
    Термометр
        Температура
        Get, Set
        Форматированная печать данных
        Изменение значения
*/
public class Thermometer {
    private double temperature; 
    
    public Thermometer(double temperature){
        this.temperature = temperature;
    }
    
    public void setTemperature(double tempData){
        temperature = tempData;                
    }
    
    public double getTemperature(){
        return temperature;
    }
    
    public String getPrintTemperature(){
        return String.format("%.1f", temperature);
    }
    
    public void changeTemperature(){
        
        temperature = Math.random() * 22 + 25;
    }
}
