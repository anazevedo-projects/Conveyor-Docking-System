
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
public class Cylinder2Thread extends Thread {
    private boolean interrupted;
    public static int counter_dock2;
    
    
     public Cylinder2Thread (){
        counter_dock2=0;
        
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
        Cylinder2 cylinder = new Cylinder2();
        Mechanism mec = new Mechanism();
        while (!interrupted){
            
            try {
                PackageIdentifyThread.Sem_cylinder2.acquire();                                      //faz um P sobre o semaforo para desbloquear esta thread (V feito no PackageIdentifyThread)
            } catch (InterruptedException ex) {
                Logger.getLogger(Cylinder2Thread.class.getName()).log(Level.SEVERE, null, ex);      
            }
            
            while(!SplitterConveyor.sensorCylinder2()){                                             //enquanto o pacote nao passa no sensor do cylinder 2 fica neste ciclo infinito
                
            }
            
            mec.conveyorstop();                                                                     //quando o pacote passa no sensor o conveyor para
            cylinder.getPackage();                                                                  //o cilindro poe o pacote na dock
             if (PackageIdentifyThread.dock2_aux == true){                                          //esta a true quando essa dock esta a ser usada em substituicao de outra
                PackageIdentifyThread.dock2_aux = false;                                            //volta a estar livre
                SplitterConveyor.turn_Led_Off();                                                    //o LED apaga        
            }
            
            counter_dock2= counter_dock2+1;                                                         //somar 1 aos pacotes que entram na dock
            mec.conveyorMove();                                                                     //conveyor volta a mover
            PackageIdentifyThread.Sem_unblock_start.release();                                      //faz um V do semaforo para desbloquear o cylinder start para realizar novos pedidos de colocacao do pacote no tapete
                
               
            
    
        }
        

    }
}