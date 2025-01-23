
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
public class Button2Thread extends Thread{
   private boolean interrupted;
   public static boolean state_dock_2;
   Mechanism mec2;
   
     public Button2Thread(){
        this.state_dock_2 = true;                                                   //true=desbloqueada
        mec2 = new Mechanism ();
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
        
         while (!interrupted){
             if (mec2.switchStation2Pressed()){                                                      //entra se o botao da dock 2 for pressionado
                 
                 try {
                     Thread.sleep(500);                                                              //A colocação do sleep deve-se ao facto de a alteração dos bits ser mais rápida que o código
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Button2Thread.class.getName()).log(Level.SEVERE, null, ex);
                 }

                 state_dock_2 = !state_dock_2;                                                       //muda o estado da dock 2
                 
                 try {
                     Thread.sleep(500);
                 } catch (InterruptedException ex) {                                                 //A colocação do sleep deve-se ao facto de a alteração dos bits ser mais rápida que o código
                     Logger.getLogger(Button2Thread.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             
             
             
             
             
             
             
         }
    }
}
