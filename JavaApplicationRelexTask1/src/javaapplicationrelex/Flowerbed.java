package javaapplicationrelex;
/*
    Клумба
        Имя клумбы
        Get, set (имя клумбы, температура)
        Проверка значения термометра, если больше 30 градусов, подать сигнал
        Изменение значения термометра
        
*/
public class Flowerbed {
    private String nameFlowerbed;
    private Thermometer temperature;

    public Flowerbed(String nameFlowerbed) {
        this.nameFlowerbed = nameFlowerbed;
        this.temperature = new Thermometer();
    } 
    
    public void setNameFlowerbed(String name){
        nameFlowerbed = name;
    }
    
    public String getNameFlowerbed(){
        return nameFlowerbed;
    }
    
    public void setTemperature(Thermometer temp){
        temperature = temp;
    }
    
    public double getTemperature(){
        return temperature.getTemperature();
    }
    
    public String getPrintFormatTemperature(){
        return temperature.getPrintTemperature();
    }
    
    public boolean checkTemperature(){
        return temperature.getTemperature() > 30;
    }
    
    public void changeTemperature(){
        temperature.changeTemperature();
    }
}
