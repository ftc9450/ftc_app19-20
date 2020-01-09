package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Arm extends Subsystem {
    private DcMotor motorExtend;
    private DcMotor motorRotate;

    private Servo servoGrab;
    private boolean grabState = false;

    private double assumePowerE;
    private double assumePowerR;

    private double limitEU = 10000;
    private double limitRU = 10000;
    private double limitED = -10000;
    private double limitRD = -10000;

    public Arm(HardwareMap map) {
        motorExtend = map.dcMotor.get(Constants.Arm.MOTOR_EXTEND);
        motorRotate = map.dcMotor.get(Constants.Arm.MOTOR_ROTATE);
        servoGrab = map.servo.get(Constants.Arm.SERVO_GRAB);
        motorRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorExtend.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(motorExtend.getCurrentPosition() >= limitED && motorExtend.getCurrentPosition() <= limitEU){
            motorExtend.setPower(assumePowerE);
        }else{
            motorExtend.setPower(0);
        }
        if(motorRotate.getCurrentPosition() >= limitRD && motorRotate.getCurrentPosition() <= limitRU){
            motorRotate.setPower(assumePowerR);
        }else{
            motorRotate.setPower(0);
        }

        if(grabState){
            servoGrab.setPosition(Servo.MAX_POSITION);
        }else{
            servoGrab.setPosition(0.6);
        }

    }
    public void setMotorPowerE(double a){
        assumePowerE = a*0.5;
    }
    public void setMotorPowerR(double a){
        assumePowerR = a*0.5;
    }
    public void setGrabState(){
        grabState = !grabState;
    }
    public boolean grabState(){
        return grabState;
    }
}
