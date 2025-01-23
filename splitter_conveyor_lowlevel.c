#include <conio.h>
#include <stdio.h>
#include <interface.h>

void initializeHardwarePorts() {
	createDigitalInput(0);
	createDigitalInput(1);
	createDigitalOutput(2);
}

void setBitValue(uInt8* variable, int n_bit, int new_value_bit)
// given a byte value, set the n bit to value
{
	uInt8 mask_on = (uInt8)(1 << n_bit);
	uInt8 mask_off = ~mask_on;
	if (new_value_bit)  *variable |= mask_on;
	else *variable &= mask_off;
}

int getBitValue(uInt8 value, uInt8 n_bit)
// given a byte value, returns the value of bit n
{
	return(value & (1 << n_bit));
}


int cylinder1_getPosition()
{
	int v = readDigitalU8(0);
	if (!getBitValue(v, 4))
		return 0;
	else if (!getBitValue(v, 3))
		return 1;
	return(-1);
}


void cylinder1_moveForward()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 4, 1);
	setBitValue(&p, 3, 0);
	writeDigitalU8(2, p);
}


void cylinder1_moveBackward()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 4, 0);
	setBitValue(&p, 3, 1);
	writeDigitalU8(2, p);
}


void cylinder1_stop()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 4, 0);
	setBitValue(&p, 3, 0);
	writeDigitalU8(2, p);
}





int cylinder2_getPosition()
{
	int v = readDigitalU8(0);
	if (!getBitValue(v, 2))
		return 0;
	else if (!getBitValue(v, 1))
		return 1;
	return(-1);
}


void cylinder2_moveForward()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 6, 1);
	setBitValue(&p, 5, 0);
	writeDigitalU8(2, p);
}



void cylinder2_moveBackward()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 6, 0);
	setBitValue(&p, 5, 1);
	writeDigitalU8(2, p);
}


void cylinder2_stop()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 6, 0);
	setBitValue(&p, 5, 0);
	writeDigitalU8(2, p);
}




int cylinderStart_getPosition()
{
	int v = readDigitalU8(0);
	if (getBitValue(v, 6))
		return 0;
	else if (getBitValue(v, 5))
		return 1;
	return(-1);
}


void cylinderStart_moveForward()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 1, 1);
	setBitValue(&p, 0, 0);
	writeDigitalU8(2, p);
}


void cylinderStart_moveBackward()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 1, 0);
	setBitValue(&p, 0, 1);
	writeDigitalU8(2, p);
}


void cylinderStart_stop()
{
	uInt8 p = readDigitalU8(2);
	setBitValue(&p, 1, 0);
	setBitValue(&p, 0, 0);
	writeDigitalU8(2, p);
}







int getIdentificationSensors() {
	uInt8 p1 = readDigitalU8(1);
	uInt8 p0 = readDigitalU8(0);

	if (!(getBitValue(p0, 0)))
		return 1;
	if (getBitValue(p1, 7))
		return 2;
	return 0;

}


bool button1_pressed() {
	uInt8 p1 = readDigitalU8(1);

	if (getBitValue(p1, 4))
		return TRUE;
	return FALSE;
}


bool button2_pressed() {
	uInt8 p1 = readDigitalU8(1);

	if (getBitValue(p1, 3))
		return TRUE;
	return FALSE;
}


bool buttonEnd_pressed() {
	uInt8 p1 = readDigitalU8(1);

	if (getBitValue(p1, 2))
		return TRUE;
	return FALSE;
}


bool read_Pack_ID_1() {
	uInt8 p1 = readDigitalU8(1);

	if (getBitValue(p1, 5))
		return TRUE;
	return FALSE;

}


bool read_Pack_ID_2() {
	uInt8 p1 = readDigitalU8(1);

	if (getBitValue(p1, 6))
		return TRUE;
	return FALSE;

}


void turn_Led_On() {
	uInt8 p2 = readDigitalU8(2);

	setBitValue(&p2, 7, 1);
	writeDigitalU8(2, p2);
}

void turn_Led_Off() {
	uInt8 p2 = readDigitalU8(2);

	setBitValue(&p2, 7, 0);
	writeDigitalU8(2, p2);
}

bool verify_Led() {
	uInt8 p2 = readDigitalU8(2);

	if (getBitValue(p2, 7))
		return TRUE;
	return FALSE;

}



bool sensorCylinder1() {
	uInt8 p = readDigitalU8(0);


	if (getBitValue(p, 0))
		return TRUE;
	return FALSE;


}


bool sensorCylinder2() {
	uInt8 p = readDigitalU8(1);


	if (getBitValue(p, 7))
		return TRUE;
	return FALSE;


}


void conveyorMove() {
	uInt8 p = readDigitalU8(2);

	setBitValue(&p, 2, 1);
	writeDigitalU8(2, p);
}



void conveyorstop() {
	uInt8 p = readDigitalU8(2);

	setBitValue(&p, 2, 0);
	writeDigitalU8(2, p);
}

int read_state() {
	uInt8 p2 = readDigitalU8(2);
	return (int) p2;

}


void rebuild_state(int porto) {

	writeDigitalU8(2, porto);

}