/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tlope
 */
public class Labwork2 {
    
    public static void main (String[] args){
         SplitterConveyor.initializeHardwarePorts();
         
         Mechanism mec = new Mechanism();
         mec.conveyorMove();
         //Criacao das threads
         Cylinder1Thread worker1 = new Cylinder1Thread();
         Cylinder2Thread worker2 = new Cylinder2Thread();
         Menu_CalibrationThread worker4 = new Menu_CalibrationThread();
         PackageIdentifyThread worker5 = new PackageIdentifyThread();
         Package_ManagerThread worker6 = new Package_ManagerThread();
         Start_PackThread worker7 = new Start_PackThread();
         Button1Thread worker8 = new Button1Thread();
         Button2Thread worker9 = new Button2Thread();
         ButtonEndThread worker10 = new ButtonEndThread();
         LedThread worker11 = new LedThread();
         Stop_ResumeThread worker12 = new Stop_ResumeThread();
         StopThread worker13 = new StopThread();
         
         //inicializacao e execucao das threads
         worker1.start();
         worker2.start();
         worker4.start();
         worker5.start();
         worker6.start();
         worker7.start();
         worker8.start();
         worker9.start();
         worker10.start();
         worker11.start();
         worker12.start();
         worker13.start();

         
    }

}
