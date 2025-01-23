
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
public class Cylinder1Thread extends Thread {
    private boolean interrupted;
    public static int counter_dock1;
    
    
    
    public Cylinder1Thread (){
        counter_dock1=0;
        
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
        char type = '\0'; 
        this.setInterrupted (false);
        Cylinder1 cylinder = new Cylinder1();
        Mechanism mec = new Mechanism();
        while (!interrupted){
            
            try {
                PackageIdentifyThread.Sem_cylinder1.acquire();                                  //faz um P sobre o semaforo para desbloquear esta thread (V feito no PackageIdentifyThread)
            } catch (InterruptedException ex) {
                Logger.getLogger(Cylinder2Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while(!SplitterConveyor.sensorCylinder1()){                                         //enquanto o pacote nao passa no sensor do cylinder 1 fica neste ciclo infinito
                
            }
            
            mec.conveyorstop();                                                                 //quando o pacote passa no sensor o conveyor para
            cylinder.getPackage();                                                              //o cilindro poe o pacote na dock
            if (PackageIdentifyThread.dock1_aux == true){                                       //esta a true quando essa dock esta a ser usada em substituicao de outra
                PackageIdentifyThread.dock1_aux = false;                                        //volta a estar livre
                SplitterConveyor.turn_Led_Off();                                                //o LED apaga
              
            }
            
            counter_dock1= counter_dock1+1;                                                     //somar 1 aos pacotes que entram na dock
            mec.conveyorMove();                                                                 //conveyor volta a mover
            PackageIdentifyThread.Sem_unblock_start.release();                                  //faz um V do semaforo para desbloquear o cylinder start para realizar novos pedidos de colocacao do pacote no tapete
    
        }
        

    }
}
