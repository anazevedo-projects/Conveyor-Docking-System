
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
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
public class Menu_CalibrationThread extends Thread{
     private boolean interrupted;
     public static ArrayBlockingQueue<Character> queue_package_dock;
     public static ArrayBlockingQueue<Character> queue_stop_resume;
     public static Semaphore Sem_start_pack = null;
   
   public Menu_CalibrationThread(){
        this.queue_package_dock = new ArrayBlockingQueue (2);
        this.queue_stop_resume = new ArrayBlockingQueue (5);
        this.Sem_start_pack = new Semaphore(0);
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
        this.setInterrupted(false);
        char c = '\0';
        char sub_c = '\0';
        char pacote_dock1 = '\0';
        char pacote_dock2 = '\0';
        
        while (!interrupted){
          System.out.println("----------------------------------------------");
          System.out.println("Escolha uma das seguintes oções:");
          System.out.println("A-Calibration");
          System.out.println("B-Configuration");
          System.out.println("C-Status");
          System.out.println ("D-Stop");
          System.out.println("E-Resume");
          System.out.println("F-Exit");
          System.out.println("----------------------------------------------");
          
            Scanner scan = new Scanner(System.in);

            c = scan.next().charAt(0);

            scan.nextLine();
               
            //Caso o utilizador pressione a tecla P e exista pelo menos uma dock livre (state_dock_x=true) entao podemos libertar o pacote   
            if ((c=='P') && !((Button1Thread.state_dock_1== false) && (Button2Thread.state_dock_2==false) && (ButtonEndThread.state_dock_End==false))){
                Sem_start_pack.release();   //Libertamos o cilindro Start para colocarmos o pacote no tapete
            } else {
                //entramos nesta condicao sempre que o utilizador pretenda colocar um pacote mas todas as docks estejam fechadas(state = false) 
                if (Button1Thread.state_dock_1== false && Button2Thread.state_dock_2==false && ButtonEndThread.state_dock_End==false){  
                    //como todas as docks estao fechadas entramos num estado de bloqueio total do sistema 
                    PackageIdentifyThread.queue_package_block_dock.add('3');  //neste caso queremos que o LED pisque periodicamente de 500ms em 500ms - bloqueio total                                 
                }

            }
          
          //O utilizador quer fazer uma calibracao ao sistema
          if(c == 'A'){
            
            do {
                //SubMenu da calibracao
                System.out.println("----------------------------------------------");
                System.out.println("Escolha uma das seguintes oções:");
                System.out.println("1-CylinderStart");
                System.out.println("2-Cylinder 1");
                System.out.println("3-Cylinder 2");
                System.out.println("4-Conveyor");
                System.out.println("5-Exit");
                System.out.println("----------------------------------------------");

                c = scan.next().charAt(0);
            
                scan.nextLine();
                
                //Calibracao do cilindro start
                if (c == '1'){
                    
                    do{
                        //SubMenu do cilindro start
                        System.out.println("----------------------------------------------");
                        System.out.println("---------Cyilinder Start control mode---------");
                        System.out.println("----------------------------------------------");
                        System.out.println("1-Move Forward");
                        System.out.println("2-Move Backward");
                        System.out.println("3-Stop");
                        System.out.println("4-Exit");
                        System.out.println("----------------------------------------------");
                      
                        sub_c = scan.next().charAt(0);
            
                        scan.nextLine();
                        
                       switch (sub_c){
                           case '1': SplitterConveyor.cylinderStart_moveForward(); break;   //faz com que o cilindro start se mova para a frente
                           case '2': SplitterConveyor.cylinderStart_moveBackward(); break;  //faz com que o cilindro start se mova para tras
                           case '3': SplitterConveyor.cylinderStart_stop(); break;          //faz com que o cilindro start pare
                           
                       } 
                    } while (sub_c != '4');
                    
                } 
                
                //Calibracao do cilindro 1
                if (c == '2'){
                    
                     do{
                        //SubMenu do cilindro 1
                        System.out.println("----------------------------------------------");
                        System.out.println("-----------Cyilinder 1 control mode-----------");
                        System.out.println("----------------------------------------------");
                        System.out.println("1-Move Forward");
                        System.out.println("2-Move Backward");
                        System.out.println("3-Stop");
                        System.out.println("4-Exit");
                        System.out.println("----------------------------------------------");
                      
                        sub_c = scan.next().charAt(0);
            
                        scan.nextLine();
                        
                        switch (sub_c){
                            case '1': SplitterConveyor.cylinder1_moveForward(); break;      //faz com que o cilindro 1 se mova para a frente
                            case '2': SplitterConveyor.cylinder1_moveBackward(); break;     //faz com que o cilindro 1 se mova para tras
                            case '3': SplitterConveyor.cylinder1_stop(); break;             //faz com que o cilindro 1 pare
                        }
                        
                    
                    } while (sub_c != '4');
                    
                    
                }
                
                //Calibracao do cilindro 2
                if (c == '3'){
                     do{
                        //SubMenu do cilindro 2
                        System.out.println("----------------------------------------------");
                        System.out.println("-----------Cyilinder 2 control mode-----------");
                        System.out.println("----------------------------------------------");
                        System.out.println("1-Move Forward");
                        System.out.println("2-Move Backward");
                        System.out.println("3-Stop");
                        System.out.println("4-Exit");
                        System.out.println("----------------------------------------------");
                      
                        sub_c = scan.next().charAt(0);
            
                        scan.nextLine();
                        
                        
                        switch (sub_c){
                           case '1': SplitterConveyor.cylinder2_moveForward(); break;       //faz com que o cilindro 2 se mova para a frente
                           case '2': SplitterConveyor.cylinder2_moveBackward(); break;      //faz com que o cilindro 2 se mova para tras
                           case '3': SplitterConveyor.cylinder2_stop(); break;              //faz com que o cilindro 2 pare
                            
                        }
                        
                    
                    } while (sub_c != '4');
                    
                    
                    
                }
                
                //Calibracao do Tapete
                if (c == '4'){
                     do{
                        //SubMenu do Tapete
                        System.out.println("----------------------------------------------");
                        System.out.println("-------------Conveyor control mode------------");
                        System.out.println("----------------------------------------------");
                        System.out.println("1-Move");;
                        System.out.println("2-Stop");
                        System.out.println("3-Exit");
                        System.out.println("----------------------------------------------");
                      
                        sub_c = scan.next().charAt(0);
            
                        scan.nextLine();
                        
                        switch (sub_c){
                           case '1': SplitterConveyor.conveyorMove(); break;        //faz o tapete mover-se
                           case '2': SplitterConveyor.conveyorstop(); break;        //para o tapete
                            
                        }
                        
                    
                    } while (sub_c != '3');
                    
                    
                }
                
            } while (c != '5');
            
          }
          
          //Configuracao do Sistema - dentro desta opcao do Menu vamos denifir qual o tipo de pacote que as docks devem receber
          if(c == 'B'){
              //Definicao do pacote que vai para a Dock1
              System.out.println("----------------------------------------------");
              System.out.println("Tipo Pacote Dock1:");
              pacote_dock1 = scan.next().charAt(0);
            
              scan.nextLine();
              
              //Definicao do pacote que vai para a Dock2
              System.out.println("----------------------------------------------");
              System.out.println("Tipo Pacote Dock2:");
              pacote_dock2 = scan.next().charAt(0);
            
              scan.nextLine();
              System.out.println("----------------------------------------------");
              
              //Vamos enviar esta informacao para a Thread Package_ManagerThread para definir o tipo de pacote que vai para a DockEnd 
              queue_package_dock.add(pacote_dock1);
              queue_package_dock.add(pacote_dock2);
              //de notar que a dockEnd fica com o pacote que a dock1 e a dock2 nao pretenderem
              
          }
          
          //Mostra ao utilizador o tipo de pacote que cada Dock recebe e a quantidade de pacotes que cada uma ja recebeu
          if (c== 'C'){
              System.out.println("----------------------------------------------");
              System.out.println ("------------Packets per Dock(type)-----------");
              System.out.println ("----------------------------------------------");
              System.out.print ("Dock 1(" + Package_ManagerThread.pacote_dock1 + "):" + Cylinder1Thread.counter_dock1 + " ");
              if (Button1Thread.state_dock_1)
                System.out.println ("free");
              else
                System.out.println ("block");
              System.out.print ("Dock 2(" + Package_ManagerThread.pacote_dock2 + "):" + Cylinder2Thread.counter_dock2 + " ");
               if (Button2Thread.state_dock_2)
                System.out.println ("free");
              else
                System.out.println ("block");
              System.out.print ("Dock end(" + Package_ManagerThread.pacote_dockEnd + "):" + PackageIdentifyThread.counter_dockEnd + " ");
               if (ButtonEndThread.state_dock_End)
                System.out.println ("free");
              else
                System.out.println ("block");
              System.out.println ("----------------------------------------------");
          }
          
          //Stop Emergency
          if (c == 'D') {  
              PackageIdentifyThread.queue_package_block_dock.add('4');                       //para informar ao LED para piscar de 400ms em 400ms
              queue_stop_resume.add('s');       //Informa a Thread Stop_ResumeThread que o sistema deve entrar em paragem de emergencia
          }    
              
          //Resume Operation
          if (c == 'E') {
           
              queue_stop_resume.add('r');     //Informa a Thread Stop_ResumeThread que o sistema pode voltar ao estado que estava antes do Stop Emergency (fazer Resume)
          
          }  
          
          //EXIT
          if (c=='F'){

             System.exit(0);                 //Termina todo o programa
          }
        
        
        }
    }
}
