/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tlope
 */
public class SplitterConveyor {

    static {
        System.load ("C:\\str\\SplitterConveyor\\x64\\Debug\\SplitterConveyor.dll");
    }
    
  static synchronized native public void initializeHardwarePorts();

    static synchronized native public int cylinder1_getPosition();

    static synchronized native public void cylinder1_moveForward();

    static synchronized native public void cylinder1_moveBackward();

    static synchronized native public void cylinder1_stop();
    
     static synchronized native public int cylinder2_getPosition();
    
     static synchronized native public void cylinder2_moveForward();
    
     static synchronized native public void cylinder2_moveBackward();
    
     static synchronized native public void cylinder2_stop();
     
     static synchronized native public int cylinderStart_getPosition();
   
     static synchronized native public void cylinderStart_moveForward();
       
     static synchronized native public void cylinderStart_moveBackward();
     
     static synchronized native public void cylinderStart_stop();

     static synchronized native public int getIdentificationSensors();
     
     static synchronized native public boolean button1_pressed();
     
     static synchronized native public boolean button2_pressed();
     
     static synchronized native public boolean buttonEnd_pressed();
     
     static synchronized native public boolean read_Pack_ID_1();
             
     static synchronized native public boolean read_Pack_ID_2();
     
     static synchronized native public void turn_Led_On();
     
     static synchronized native public void turn_Led_Off();
             
     static synchronized native public boolean verify_Led();
  
     static synchronized native public boolean sensorCylinder1();
     
     static synchronized native public boolean sensorCylinder2();

     static synchronized native public void conveyorMove();

     static synchronized native public void conveyorstop();
     
     static synchronized native public int read_state ();
     
     static synchronized native public void  rebuild_state (int porto);
     

}
