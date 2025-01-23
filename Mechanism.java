/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIEEC
 */
public class Mechanism {
    
    /* Funcao responsavel por colocar o tapete em movimento */
    public void conveyorMove(){
        SplitterConveyor.conveyorMove();
    }
        
    /* Funcao responsavel por parar o tapete */
    public void conveyorstop(){
        SplitterConveyor.conveyorstop();
    }
    
    /* Funcao que deteta sempre que o botao da dock1 e pressionado
    * Output: 1 - o botao da dock1 foi pressionado
    *         0 - o botao nao foi pressionado
    */
    public boolean switchStation1Pressed(){
        return SplitterConveyor.button1_pressed();
    }
    
    /* Funcao que deteta sempre que o botao da dock2 e pressionado
    * Output: 1 - o botao da dock2 foi pressionado
    *         0 - o botao nao foi pressionado
    */
    public boolean switchStation2Pressed(){
        return SplitterConveyor.button2_pressed();
    }
    
    /* Funcao que deteta sempre que o botao da dockEnd e pressionado
    * Output: 1 - o botao da dockEnd foi pressionado
    *         0 - o botao nao foi pressionado
    */
    public boolean switchStationEndPressed(){
        return SplitterConveyor.buttonEnd_pressed();
    }
    
    /* Funcao que deteta quando uma marca magnetica passa pelo sensor 1 da identificacao (porto 1.bit 5)
    * Output: 1 - passou uma marca
    *         0 - nao passou nenhuma
    */
    public boolean read_ID_1(){
        
        return SplitterConveyor.read_Pack_ID_1();    
    }
    
    /* Funcao que deteta quando uma marca magnetica passa pelo sensor 2 da identificacao (porto 1.bit 6)
    * Output: 1 - passou uma marca
    *         0 - nao passou nenhuma
    */
    public boolean read_ID_2(){
        
        return SplitterConveyor.read_Pack_ID_2();    
    }
    
    /* Funcao que liga o LED */
    public void LedOn(){
        SplitterConveyor.turn_Led_On();
    }
    
    /* Funcao que desliga o LED */
    public void LedOff(){
        SplitterConveyor.turn_Led_Off();
    }
}
