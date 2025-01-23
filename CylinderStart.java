/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MIEEC
 */
public class CylinderStart implements Cylinder {
    
    public int getPosition(){                                                   //da a posicao do cilindro start
        return SplitterConveyor.cylinderStart_getPosition();
    }
    
    public void moveForward(){                                                  //move o cilindro start para a frente
        SplitterConveyor.cylinderStart_moveBackward();
    }
    
    public void moveBackward(){                                                 //move o cilindro start para a tras
        SplitterConveyor.cylinderStart_moveForward();
    }
    
    public void stop(){                                                         //para o cilindro start
        SplitterConveyor.cylinderStart_stop();
    }


    public void gotoPosition(int position) {
        
        if (SplitterConveyor.cylinderStart_getPosition() != position) {         //caso ainda nao esteja numa posicao de um sensor 

            if (position == 0)
                SplitterConveyor.cylinderStart_moveBackward();                  //movemos para tras
            
            if (position == 1)
                SplitterConveyor.cylinderStart_moveForward();                   //movemos para a frente
            
            
            
            while (true) {
                 if (SplitterConveyor.cylinderStart_getPosition() == position) {//quano chegar a posicao pretendida
                     SplitterConveyor.cylinderStart_stop();                     //o cilindro para
                     break;
                 }
             }
        }
    }
    
    public void getPackage() {                                                  //metodo para colocar o pacote no tapete
       this.gotoPosition(1);
       this.gotoPosition(0);
    }
}

