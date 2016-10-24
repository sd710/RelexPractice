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

    public Flowerbed(String nameFlowerbed) {
        this.nameFlowerbed = nameFlowerbed;
    } 
    
    public void setNameFlowerbed(String name){
        nameFlowerbed = name;
    }
    
    public String getNameFlowerbed(){
        return nameFlowerbed;
    }
}
