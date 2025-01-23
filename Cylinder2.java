/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIEEC
 */
public class Cylinder2 implements Cylinder {
    
    public int getPosition(){                                                   //da a posicao do cilindro 2
        return SplitterConveyor.cylinder2_getPosition();
    }
    
    public void moveForward(){                                                  //move o cilindro 2 para a frente
        SplitterConveyor.cylinder2_moveForward();
    }
    
    public void moveBackward(){                                                 //move o cilindro 2 para a tras
        SplitterConveyor.cylinder2_moveBackward();
    }
    
    public void stop(){                                                         //para o cilindro 2
        SplitterConveyor.cylinder2_stop();
    }


    public void gotoPosition(int position) {
        
          if (SplitterConveyor.cylinder2_getPosition() != position) {           //caso ainda nao esteja numa posicao de um sensor 

            if (position == 0)
                SplitterConveyor.cylinder2_moveBackward();                      //movemos para tras
            
            if (position == 1)
                SplitterConveyor.cylinder2_moveForward();                       //movemos para a frente
            
            
            
            while (true) {
                 if (SplitterConveyor.cylinder2_getPosition() == position) {    //quando chegar a posicao pretendida
                     SplitterConveyor.cylinder2_stop();                         //o cilindro para
                     break;
                 }
             }
        }
    }
    
    public void getPackage() {                                                  //metodo para colocar o pacote na dock 2
       this.gotoPosition(1);
       this.gotoPosition(0);
    }
}
