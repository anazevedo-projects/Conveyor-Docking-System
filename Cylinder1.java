

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIEEC
 */
public class Cylinder1 implements Cylinder{
    
    public int getPosition(){                                                   //da a posicao do cilindro 1
        return SplitterConveyor.cylinder1_getPosition();
    }
    
    public void moveForward(){                                                  //move o cilindro 1 para a frente
        SplitterConveyor.cylinder1_moveForward();
    }
    
    public void moveBackward(){                                                 //move o cilindro 1 para tras
        SplitterConveyor.cylinder1_moveBackward();
    }
    
    public void stop(){                                                         //para o cilindro 1
        SplitterConveyor.cylinder1_stop();
    }
    
    public void gotoPosition(int position){
                    
        
        if (SplitterConveyor.cylinder1_getPosition() != position) {             //caso ainda nao esteja numa posicao de um sensor 

            if (position == 0)
                SplitterConveyor.cylinder1_moveBackward();                      //movemos para tras
            
            if (position == 1)
                SplitterConveyor.cylinder1_moveForward();                       //movemos para a frente
            
            
            
            while (true) {
                 if (SplitterConveyor.cylinder1_getPosition() == position) {    //quando chegar a posicao pretendida
                     SplitterConveyor.cylinder1_stop();                         //o cilindro para
                     break;
                 }
             }
        }
    
      
        
    }
    
    public void getPackage() {                                                  //metodo para colocar o pacote na dock 1
       this.gotoPosition(1);
       this.gotoPosition(0);
    }
}
