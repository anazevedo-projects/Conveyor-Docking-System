
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
public class StopThread extends Thread {
    
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
        this.setInterrupted (false);
        
        while (!interrupted){
       //quando a thread do menu enviar um 's' por mailbox temos de adormecer todas as threads em ciclo até que o stop_resume seja atualizado com 'r'      
            if (Stop_ResumeThread.stop_resume == 's'){          
            
                  
                        try {

                             Button1Thread.sleep(1);
                             Button2Thread.sleep(1);
                             ButtonEndThread.sleep(1);
                             Cylinder1Thread.sleep(1);
                             Cylinder2Thread.sleep(1);
                             Menu_CalibrationThread.sleep(1);
                             PackageIdentifyThread.sleep(1);
                             Package_ManagerThread.sleep(1);
                             Start_PackThread.sleep(1);

                         } catch (InterruptedException ex) {
                             Logger.getLogger(Stop_ResumeThread.class.getName()).log(Level.SEVERE, null, ex);                

                         }
                   
            }
            
        }
    
    
    
    
    
    }    
    
    
    
    
    
    
    
    
    
}
