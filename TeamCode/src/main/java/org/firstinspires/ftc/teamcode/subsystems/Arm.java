package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Arm extends Subsystem {
    private DcMotor motorExtend;
    private DcMotor motorRotate;

    private Servo servoGrab;
    private Servo servoBack;

    private double assumePowerE;
    private double assumePowerR;

    private double limitEU = 10000;
    private double limitRU = 10000;
    private double limitED = -10000;
    private double limitRD = -10000;

    private boolean grabState = false;
    public Arm(HardwareMap map) {
        motorExtend = map.dcMotor.get(Constants.Arm.MOTOR_EXTEND);
        motorRotate = map.dcMotor.get(Constants.Arm.MOTOR_ROTATE);
        servoBack = map.servo.get(Constants.Arm.SERVO_BACK);
        servoGrab = map.servo.get(Constants.Arm.SERVO_GRAB);
        motorRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
            servoGrab.setPosition(Servo.MIN_POSITION);
        }

    }
    public void setMotorPowerE(double a){
        assumePowerE = a;
    }
    public void setMotorPowerR(double a){
        assumePowerR = a;
    }

    public void setGrabState(boolean a){
        grabState = a;
    }
    public boolean getGrabState(){
        return grabState;
    }
}
