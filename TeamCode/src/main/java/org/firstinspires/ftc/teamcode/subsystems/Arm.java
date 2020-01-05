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

    private double limitEU;
    private double limitED;
    private double limitRU;
    private double limitRD;
    public Arm(HardwareMap map) {
        motorExtend = map.dcMotor.get(Constants.Arm.MOTOR_EXTEND);
        motorRotate = map.dcMotor.get(Constants.Arm.MOTOR_ROTATE);
        servoBack = map.servo.get(Constants.Arm.SERVO_BACK);
        servoGrab = map.servo.get(Constants.Arm.SERVO_GRAB);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(motorExtend.getCurrentPosition() > limitED && motorExtend.getCurrentPosition() < limitEU){
            motorExtend.setPower(assumePowerE);
        }else{
            motorExtend.setPower(0);
        }
        if(motorRotate.getCurrentPosition() > limitRD && motorRotate.getCurrentPosition() < limitRU){
            motorRotate.setPower(assumePowerR);
        }else{
            motorRotate.setPower(0);
        }
    }
    public void setMotorPowerE(double a){
        assumePowerE = a;
    }
    public void setMotorPowerR(double a){
        assumePowerR = a;
    }
}
