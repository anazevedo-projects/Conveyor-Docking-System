
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tlope
 */
public class Button1Thread extends Thread{
    private boolean interrupted;
    public static boolean state_dock_1;
    Mechanism mec1;
    
    public Button1Thread(){
        Button1Thread.state_dock_1 = true;                                                                //true=desbloqueada
        mec1 = new Mechanism ();
    }
    
    @Override
    public boolean isInterrupted () {
        return interrupted;
    }
   
    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }
     @Override
     
    public void run() {
         this.setInterrupted (false);
         int i=0;
         while (!interrupted){
             
             if (mec1.switchStation1Pressed()){                                                            //entra se o botao da dock 1 for pressionado
                 
                 try {
                     Thread.sleep(500);
                 } catch (InterruptedException ex) {                                                       //A colocação do sleep deve-se ao facto de a alteração dos bits ser mais rápida que o código
                     Logger.getLogger(Button1Thread.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 state_dock_1 = !state_dock_1;                                                             //muda o estado da dock 1  
                 try {
                     Thread.sleep(500);                                                                    //A colocação do sleep deve-se ao facto de a alteração dos bits ser mais rápida que o código
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Button1Thread.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                                                                                                          
             }
             
             
             
             
             
             
             
         }
    }
        
}
    
    
    
    
    
    
