
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
public class ButtonEndThread extends Thread{
    private boolean interrupted;
     public static boolean state_dock_End;
    Mechanism mec3;
     
      public ButtonEndThread(){
        this.state_dock_End = true;                                                                        //true=desbloqueada
        mec3 = new Mechanism ();
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
             if (mec3.switchStationEndPressed()){                                                       //entra se o botao da dock End for pressionado
                 
                 try {
                     Thread.sleep(500);                                                                 //A colocação do sleep deve-se ao facto de a alteração dos bits ser mais rápida que o código
                 } catch (InterruptedException ex) {
                     Logger.getLogger(ButtonEndThread.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 state_dock_End = !state_dock_End;                                                      //muda o estado da dock End
                 
                 try {
                     Thread.sleep(500);                                                                 //A colocação do sleep deve-se ao facto de a alteração dos bits ser mais rápida que o código
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Button2Thread.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
             }
             
  
             
         }
    }
    
    
    
    
    
}
