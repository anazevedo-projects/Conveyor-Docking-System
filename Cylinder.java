/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author MIEEC
 */
public interface Cylinder {
    
    public int getPosition();               //da a posicao do cilindro
    
    public void moveForward();              //move o cilindro para a frente
    
    public void moveBackward();             //move o cilindro para tras
    
    public void stop();                     //para o cilindro
    
    public void gotoPosition(int position); //move o cilindro para uma determinada posicao
    
    public void getPackage();               //coloca o package na dock pretendida
}
