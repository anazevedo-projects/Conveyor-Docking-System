
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
public class LedThread extends Thread{

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
        char type = '0';
        while (!interrupted){
             
            //Esta Thread so vai desbloquear quando:
            //1 - a thread PackageIdentify identifica o pacote e ao tentar enviar para a dock respetiva percebe que a dock esta fechada.
                //1.1 - Dock1 ou Dock2 bloqueada: recebe da mbx o caracter '1' 
                //1.2 - DockEnd bloqueada: recebe da mbx o caracetr '2'
            //2 - no momento em que o utilizador faz 'P'(liberta o pacote) a thread Package_ManagerThread deteta que todas as docks estao bloqueadas.
            //Neste caso recebe da mbx o caracter '3'
            //3 - STOP EMERGENCY - recebe da mbx o caracter '4'
            
            try {
                type = PackageIdentifyThread.queue_package_block_dock.take();               //Fica aqui presa ate que receba alguma coisa na mbx(queue_package_block_dock)
                
                //A variavel type determina o comportamento do LED
            } catch (InterruptedException ex) {
                Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //type = '1' - O LED deve piscar de 1000ms em 1000ms - isto acontece quando a Dock1, Dock2 ou mesmo as duas ao mesmo tempo estao bloqueadas e tenham pedido o envio de um pacote para uma destas
            while ((type=='1') && ((PackageIdentifyThread.dock1_aux == true) || (PackageIdentifyThread.dock2_aux == true) || (PackageIdentifyThread.dockEnd_aux == true) )){
                
                //Este ciclo while termina quando a dock que estava a ser utilizada em substituicao da dock de configuracao recebe o pacote(dockX_aux = false)
                //Neste caso pode estar a ser usada qualquer umas das docks dependendo do tipo de pacote que estaria a ser enviado quando se detetou que a sua dock estava bloqueada:
                    //1 - Dock1 caso a Dock2 esteja bloqueada
                    //2 - Dock2 caso a Dock1 esteja bloqueada
                    //3 - DockEnd caso a Dock1 e Dock2 estejam bloqueadas
                
                //System.out.println("PISCA 1000");                                                 //para debugging se for necessário ver a que ritmo o LED pisca
                
                SplitterConveyor.turn_Led_On();                                                     //Ligamos o LED
                
                try {
                    Thread.sleep(1000);                                                             //Adormecemos a Thread por 1000ms antes de desligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                SplitterConveyor.turn_Led_Off();                                                    //Desligamos o LED
                
                try {
                    Thread.sleep(1000);                                                             //Voltamos a adormecer a Thread por 1000ms antes de ligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //type = '2' - O LED deve piscar de 3000ms em 3000ms - isto acontece quando a DockEnd esta bloqueada e tenham pedido o envio de um pacote para esta 
            //Pode tambem acontecer que a Dock1 esteja bloqueada ao mesmo tempo que a DockEnd ou que a Dock2 esteja bloqueada ao mesmo tempo que a DockEnd
            while(type=='2' && ((PackageIdentifyThread.dock1_aux== true) || (PackageIdentifyThread.dock2_aux== true))){
                
                //Este ciclo while termina quando a dock que estava a ser utilizada em substituicao da dock End recebe o pacote(dockX_aux = false)
                //Neste caso só duas das docks podem estar a ser usadas em substituicao de outra:
                    //1 - Dock1 caso esteja apenas a DockEnd bloqueada ou caso esteja a DockEnd e a Dock2
                    //2 - Dock2 caso esteja apenas a DockEnd bloqueada ou caso esteja a DockEnd e a Dock1
                
                //System.out.println("PISCA 3000");                                             //para debugging se for necessário ver a que ritmo o LED pisca
                
                SplitterConveyor.turn_Led_On();                                                 //Ligamos o LED
                
                try {
                    Thread.sleep(3000);                                                         //Adormecemos a Thread por 3000ms antes de desligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                SplitterConveyor.turn_Led_Off();                                                //Desligamos o LED
                
                try {
                    Thread.sleep(3000);                                                         //Voltamos a adormecer a Thread por 3000ms antes de ligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            
            //type = '3' - o LED deve piscar de 500ms em 500ms - isto acontece quando todo o sistema esta bloqueado(todas as docks se encontram bloqueadas
            while(type == '3' && (Button1Thread.state_dock_1 == false) && (Button2Thread.state_dock_2 == false) && (ButtonEndThread.state_dock_End ==false)){
                
                //Este ciclo while termina quando qualquer umas das docks desbloquear (state_dock_X = true)
                
                //System.out.println("PISCA 500");                                              //para debugging se for necessário ver a que ritmo o LED pisca
                
                SplitterConveyor.turn_Led_On();                                                 //Ligamos o LED
                
                try {
                    Thread.sleep(500);                                                          //Adormecemos a Thread por 500ms antes de desligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                SplitterConveyor.turn_Led_Off();                                                //Desligamos o LED
                
                try {
                    Thread.sleep(1000);                                                         //Voltamos a adormecer a Thread por 1000ms antes de ligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //type = '4' - EMERGENCY STOP - o LED deve piscar de 400ms em 400ms - ocorre quando o utilizador selecionar no MENU a opcao de Stop
            while((type == '4') && (Stop_ResumeThread.stop_resume == 's')){
                
                //Este ciclo while so termina quando o utilizador selecionar no MENU a opcao Resume(stop_resume = 'r')
                
                //System.out.println("PISCA 400");                                             //para debugging se for necessário ver a que ritmo o LED pisca
                
                SplitterConveyor.turn_Led_On();                                                //Ligamos o LED
                
                try {
                    Thread.sleep(400);                                                         //Adormecemos a Thread por 400ms antes de desligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                SplitterConveyor.turn_Led_Off();                                                //Desligamos o LED
                
                try {
                    Thread.sleep(400);                                                          //Voltamos a adormecer a Thread por 400ms antes de ligar o LED
                } catch (InterruptedException ex) {
                    Logger.getLogger(LedThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            type='0';                                                                           //Sempre que terminamos um dos ciclo queremos reinicializar a variavel type
                    
        }
    }
}

                  
