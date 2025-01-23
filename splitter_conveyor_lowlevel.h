#pragma once

#include <interface.h>

void initializeHardwarePorts();
void setBitValue(uInt8* variable, int n_bit, int new_value_bit);
int getBitValue(uInt8 value, uInt8 n_bit);
int cylinder1_getPosition();
void cylinder1_moveForward();
void cylinder1_moveBackward();
void cylinder1_stop();

int cylinder2_getPosition();
void cylinder2_moveForward();
void cylinder2_moveBackward();
void cylinder2_stop();

int cylinderStart_getPosition();
void cylinderStart_moveForward();
void cylinderStart_moveBackward();
void cylinderStart_stop();

int getIdentificationSensors();

bool button1_pressed();
bool button2_pressed();
bool buttonEnd_pressed();
bool read_Pack_ID_1();
bool read_Pack_ID_2();
void turn_Led_On();
void turn_Led_Off();
bool verify_Led();


bool sensorCylinder1();
bool sensorCylinder2();




void conveyorMove();
void conveyorstop();
int read_state();
void rebuild_state(int porto);