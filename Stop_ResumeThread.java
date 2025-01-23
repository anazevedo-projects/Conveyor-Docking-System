
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
public class Stop_ResumeThread extends Thread {
    
    private boolean interrupted;
    public static char stop_resume;
    public static int bits_state; 
    
    
    public Stop_ResumeThread(){
        stop_resume = '0';
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
        int control_cycle = 0;          //variavel de controlo para so parar os atuadores do sistema uma vez durante o emergency stop
         while (!interrupted){
            //rececao na mailbox para fazer resume ou stop dependendo da opcao selecionada
            try {
                stop_resume = Menu_CalibrationThread.queue_stop_resume.take();             
            } catch (InterruptedException ex) {
                Logger.getLogger(Stop_ResumeThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            //caso seja recebido na mailbox o carater 's', temos de guardar os bits do porto 2 -atuadores- e parar todos os atuadores          
            if (stop_resume == 's'){
                if (control_cycle == 0){
                    bits_state = SplitterConveyor.read_state();
                    
                    SplitterConveyor.conveyorstop();
                    SplitterConveyor.cylinder1_stop();
                    SplitterConveyor.cylinder2_stop();
                    SplitterConveyor.cylinderStart_stop();
                    control_cycle = 1;
                }
             
           }
             
             
            //caso seja recebido na mailbox o carater 'r', temos de acordar as threads que tinham sido postas a dormir na StopThread       
            if (stop_resume == 'r'){
                    try {
                        Button1Thread.sleep(0);
                        Button2Thread.sleep(0);
                        ButtonEndThread.sleep(0);
                        Cylinder1Thread.sleep(0);
                        Cylinder2Thread.sleep(0);
                        LedThread.sleep(0);
                        Menu_CalibrationThread.sleep(0);
                        PackageIdentifyThread.sleep(0);
                        Package_ManagerThread.sleep(0);
                        Start_PackThread.sleep(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Stop_ResumeThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                //repor no porto 2 os bits guardados    
                SplitterConveyor.rebuild_state(bits_state);
                
                //reinicializar as variaveis de controlo
                stop_resume = '0';
                control_cycle = 0;
                
            }
             
        
        
        }
    }
    
    
    
    
    
    
    
}
