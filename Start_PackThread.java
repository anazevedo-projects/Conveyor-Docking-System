
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matil
 */
public class Start_PackThread extends Thread{
    private boolean interrupted;
    
    
    
    
    @Override
    public boolean isInterrupted () {
        return interrupted;
    }
   
    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }
    

     @Override
    public void run() {
       this.setInterrupted(false);
       CylinderStart c_start = new CylinderStart();
       while(!interrupted){
       
           try {
               Menu_CalibrationThread.Sem_start_pack.acquire();                                 //So queremos libertar o cylinder start quando o utilizador faz 'P' e existe pelo menos uma Dock livre            
               
           } catch (InterruptedException ex) {
               Logger.getLogger(Start_PackThread.class.getName()).log(Level.SEVERE, null, ex);  
           }
           try {
               PackageIdentifyThread.Sem_unblock_start.acquire();                               //So queremos libertar o cylinder start quando o pacote que esta no tapete chegar ao seu destino
           } catch (InterruptedException ex) {
               Logger.getLogger(Start_PackThread.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           c_start.getPackage();                                                               //poe o pacote no tapete
       }
    }
    
}
