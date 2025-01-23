
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tlope
 */
public class PackageIdentifyThread extends Thread {
    
    private boolean interrupted;
    private boolean state_ID_1;
    private boolean state_ID_2;
    public static ArrayBlockingQueue<Character> queue_package_block_dock = null;
    public static Semaphore Sem_cylinder1 = null;
    public static Semaphore Sem_cylinder2 = null;
    public static Semaphore Sem_unblock_start = null;
    public static boolean dockEnd_aux;
    public static boolean dock1_aux;
    public static boolean dock2_aux;
    Mechanism mec;
    public static int counter_dockEnd;
    
    public PackageIdentifyThread(){
        this.state_ID_1 = false;
        this.state_ID_2 = false;
        this.Sem_cylinder1 = new Semaphore(0);
        this.Sem_cylinder2 = new Semaphore(0);
        this.Sem_unblock_start = new Semaphore(1);
        this.dockEnd_aux = false;
        this.dock1_aux = false;
        this.dock2_aux = false;
        mec = new Mechanism();
        this.queue_package_block_dock = new ArrayBlockingQueue (5);
        this.counter_dockEnd=0;
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
        char type;
        int aux=0;
        type = '0';
        
        this.setInterrupted(false);
        while (!interrupted){
           
           //Caso as variaveis de estado dos sensores ja estejam a true so as voltamos a alterar para false quando o pacote terminar de ser entregue ao tapete
           if(state_ID_1 != true)
            state_ID_1 = mec.read_ID_1();       
           
           if(state_ID_2 != true)
            state_ID_2 = mec.read_ID_2();       
           
           //Quando o cilindro Start chega a posicao 1 significa que esta a libertar um pacote
            if ((SplitterConveyor.cylinderStart_getPosition() == 1) && (aux==0)){
                
                //Nessa posicao estamos nas condicoes de identificar o tipo de pacote que por la esta a passar
                
                //quando so um dos sensores ativa, ou seja, temos um pacote com duas marcas magneticas (tipo B)
                if((!state_ID_1 && state_ID_2 ) || (state_ID_1 && !state_ID_2)){
                  type = 'B';
                } 
                
                //quando os dois sensores ativam, ou seja, temos um pacote com tres marcas magneticas (tipo C)
                if (state_ID_1 && state_ID_2){
                  type = 'C';
                }

                //quando nenhum dos sensores ativa, ou seja, temos um pacote com uma marca magnetica (tipo A)
                if (!state_ID_2 && !state_ID_1){
                  type = 'A';
                }
                
               //para evitar que o programa identifique o pacote mais que uma vez durante o movimento do cilindro Start
               aux++;

               if ((type == Package_ManagerThread.pacote_dock1) && Button1Thread.state_dock_1){
                   //Quando se deteta um pacote da Dock1(disponivel = state_dock_1 = true) alertamos o cilindro 1 que quando passar um pacote por ele este deve guardar o pacote na dock1
                   Sem_cylinder1.release();
               } else {
                   
                   if ((type == Package_ManagerThread.pacote_dock1) && !(Button1Thread.state_dock_1)){       //entramos nesta condicao quando a dock1 esta fechada

                       queue_package_block_dock.add('1');           //enviamos para a thread do LED o caracter '1' para esta sinalizar que a dock1 esta fechadas - pisca de 1000ms em 1000ms
                       
                       //A seguir temos que verificar se existe alguma dock disponivel para onde possamos enviar o pacote
                       
                       if (Button2Thread.state_dock_2){             //se a dock2 estiver disponivel(state_dock_2 = true)

                          PackageIdentifyThread.dock2_aux=true;     //colocamos esta variavel a true para sinalizar que a dock2 vai receber um pacote de um tipo diferente
                          Sem_cylinder2.release();                  //damos ordem ao cilindro 2 para guardar na dock2 o proximo pacote que passar por ele 

                       } else {                                                 //caso a dock2 tambem nao esteja disponivel

                           
                            if (ButtonEndThread.state_dock_End){                //verificamos se a dockEnd esta disponivel para receber o pacote (state_dock_end = true)
                                PackageIdentifyThread.dockEnd_aux=true;         //colocamos esta variavel a true para sinalizar que a dockEnd vai receber um pacote de um tipo diferente

                                while (!SplitterConveyor.sensorCylinder2()){    //enquanto o pacote nao passar no cilindro 2 siginifica que ainda nao chegou ao dockEnd 

                                }
                                
                                counter_dockEnd=counter_dockEnd+1;              //o pacote ja "chegou" a dockEnd e por isso incrementamos o numero de pacotes que esta dock ja recebeu

                                if (dockEnd_aux == true){                      
                                    dockEnd_aux = false;                        //Colocamos a variavel a false porque ja entregou o pacote 
                                    SplitterConveyor.turn_Led_Off();            //Desligamos o LED que estava a sinalizar que um pacote estava a ir para uma dock que nao era a dele
                                           
                                }
                                Sem_unblock_start.release();                    //Ja podemos libertar o cilindro Start para novos pedidos de entrega de pacotes
                            } 

                        }



                    }
                }


                if ((type == Package_ManagerThread.pacote_dock2) && Button2Thread.state_dock_2){
                    //Quando se deteta um pacote da Dock2(disponivel = state_dock_2 = true) alertamos o cilindro 2 que quando passar um pacote por ele este deve guardar o pacote na dock2
                    Sem_cylinder2.release(); 
                } else {

                    if ((type == Package_ManagerThread.pacote_dock2) && !(Button2Thread.state_dock_2)){     //entramos nesta condicao quando a dock2 esta fechada
                        
                        //enviamos para a thread do LED o caracter '1' para esta sinalizar que a dock2 esta fechadas - pisca de 1000ms em 1000ms
                        queue_package_block_dock.add('1');
                        
                         if (Button1Thread.state_dock_1){                   //se a dock1 estiver disponivel(state_dock_1 = true)
                            PackageIdentifyThread.dock1_aux=true;           //colocamos esta variavel a true para sinalizar que a dock1 vai receber um pacote de um tipo diferente
                            Sem_cylinder1.release();                        //damos ordem ao cilindro 1 para guardar na dock1 o proximo pacote que passar por ele
                        } else {
                             //caso a dock1 tambem nao esteja disponivel

                            if (ButtonEndThread.state_dock_End){             //verificamos se a dockEnd esta disponivel para receber o pacote (state_dock_end = true)  

                                PackageIdentifyThread.dockEnd_aux=true;      //colocamos esta variavel a true para sinalizar que a dockEnd vai receber um pacote de um tipo diferente

                                while (!SplitterConveyor.sensorCylinder2()){    //enquanto o pacote nao passar no cilindro 2 siginifica que ainda nao chegou ao dockEnd

                                }
                                
                                counter_dockEnd=counter_dockEnd+1;              //o pacote ja "chegou" a dockEnd e por isso incrementamos o numero de pacotes que esta dock ja recebeu
                                
                                if (dockEnd_aux == true){              
                                   dockEnd_aux = false;                         //Colocamos a variavel a false porque ja entregou o pacote 

                                   SplitterConveyor.turn_Led_Off();             //Desligamos o LED que estava a sinalizar que um pacote estava a ir para uma dock que nao era a dele

                                }
                                Sem_unblock_start.release();                    //Ja podemos libertar o cilindro Start para novos pedidos de entrega de pacotes
                           }

                        }

                    }
                }

                if ((type == Package_ManagerThread.pacote_dockEnd) && ButtonEndThread.state_dock_End){
                    //Detetamos um pacote da DockEnd e esta esta disponivel (state_dock_End = true) 
                    
                    while (!SplitterConveyor.sensorCylinder2()){        //enquanto o pacote nao passar no cilindro 2 siginifica que ainda nao chegou ao dockEnd
                    
                    }
                    
                    counter_dockEnd=counter_dockEnd+1;                 //o pacote ja "chegou" a dockEnd e por isso incrementamos o numero de pacotes que esta dock ja recebeu

                    Sem_unblock_start.release();                       //Ja podemos libertar o cilindro Start para novos pedidos de entrega de pacotes
                    
                } else {
                    
                    if ((type == Package_ManagerThread.pacote_dockEnd) && !(ButtonEndThread.state_dock_End)){   //entramos nesta condicao quando a dockEnd esta fechada

                        queue_package_block_dock.add('2');
                        
                        if (Button1Thread.state_dock_1){                        //se a dock1 estiver disponivel(state_dock_1 = true)
                            PackageIdentifyThread.dock1_aux=true;               //colocamos esta variavel a true para sinalizar que a dock1 vai receber um pacote de um tipo diferente
                             Sem_cylinder1.release();                           //damos ordem ao cilindro 1 para guardar na dock1 o proximo pacote que passar por ele
                         } else {
                              //caso a dock1 tambem nao esteja disponivel
                              
                              if (Button2Thread.state_dock_2){                  //verificamos se a dock2 esta disponivel para receber o pacote (state_dock_2 = true)
                                   PackageIdentifyThread.dock2_aux=true;        //colocamos esta variavel a true para sinalizar que a dock2 vai receber um pacote de um tipo diferente
                                   Sem_cylinder2.release();                     //damos ordem ao cilindro 2 para guardar na dock2 o proximo pacote que passar por ele
                                }
                            }

                    }
                }
           }

           //Quando o cilindro Start chegar a posicao inicial(0) 
           if ((SplitterConveyor.cylinderStart_getPosition() == 0) && (aux !=0)){
               //voltamos a colocar as variaveis que identificam o pacote a false
               state_ID_1 = false;
               state_ID_2 = false;
               //colocamos a variavel aux novamente a zero para que da proxima vez que identifiquemos um pacote esta Thread entre na condicao que deteta que o cylinder start chegou a posicao 1
               aux=0;                 
           }
        
    }
     
  }
}