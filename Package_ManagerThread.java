
import static java.lang.Thread.interrupted;
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
public class Package_ManagerThread extends Thread{
    
    private boolean interrupted;
    public static char pacote_dock1;
    public  static char pacote_dock2;
    public static char pacote_dockEnd;
    
    public Package_ManagerThread (){
         this.pacote_dock1 ='\0';
         this.pacote_dock2= '\0';
         this.pacote_dockEnd = '\0';
        
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
            
            //Esta Thread fica sempre presa até que a Thread Menu_CalibrationThread envie pela mbx o tipo de pacotes que devem ir para cada uma das Docks 1 e 2
            
            try {
                pacote_dock1 = Menu_CalibrationThread.queue_package_dock.take();                            //recebemos o tipo da Dock 1 
     
            } catch (InterruptedException ex) {
                Logger.getLogger(Package_ManagerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                pacote_dock2= Menu_CalibrationThread.queue_package_dock.take();                            //recebemos o tipo da Dock 2
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Package_ManagerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if ((pacote_dock1=='A' && pacote_dock2== 'B') || (pacote_dock1=='B' && pacote_dock2=='A')){
                //Quando o único tipo que sobra e o tipo C e vai para a Dock End
                pacote_dockEnd = 'C';
                
            }
            
            if ((pacote_dock1=='B' && pacote_dock2== 'C') || (pacote_dock1=='C' && pacote_dock2=='B')){
                //Quando o único tipo que sobra e o tipo A e vai para a Dock End
                pacote_dockEnd = 'A';
                
            }
            
            if ((pacote_dock1=='C' && pacote_dock2== 'A') || (pacote_dock1=='A' && pacote_dock2=='C')){
                //Quando o único tipo que sobra e o tipo B e vai para a Dock End
                pacote_dockEnd = 'B';
                
            }
            
        }
       
    }
}
