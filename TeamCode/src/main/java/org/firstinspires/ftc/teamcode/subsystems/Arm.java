package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Arm extends Subsystem {
    private DcMotor motorIntake, motorRotate, motorWheel;

    private Servo servoGrab;
    private boolean wheelState = false;
    private int intakeState = 0;
    private int armPosition = 0;
    private final int ARM_MULTIPLE = 100;

    private double assumePowerE;
    private double assumePowerR;

    private double limitEU = 10000;
    private double limitRU = 10000;
    private double limitED = -10000;
    private double limitRD = -10000;

    public Arm(HardwareMap map) {
        motorIntake = map.dcMotor.get(Constants.Arm.MOTOR_INTAKE);
        motorRotate = map.dcMotor.get(Constants.Arm.MOTOR_ROTATE);
        motorWheel = map.dcMotor.get(Constants.Arm.MOTOR_ROTATE);
        motorRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorIntake.setDirection(DcMotorSimple.Direction.REVERSE);

        motorRotate.setTargetPosition(0);
        motorRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRotate.setPower(0.3);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        motorRotate.setTargetPosition(armPosition*ARM_MULTIPLE);

        if(wheelState){
            motorWheel.setPower(0.3);
        }else{
            motorWheel.setPower(0);
        }

        switch (intakeState){
            case 1:motorIntake.setPower(0.3);break;
            case 0:motorIntake.setPower(0);break;
            case -1:motorIntake.setPower(-0.3);break;

        }

    }
    public void setMotorPowerE(double a){
        assumePowerE = a*0.5;
    }
    public void setMotorPowerR(double a){
        assumePowerR = a*0.5;
    }

    public boolean isWheelState() {
        return wheelState;
    }

    public void setWheelState(boolean wheelState) {
        this.wheelState = wheelState;
    }

    public void incrementArm(){
        armPosition++;
    }
    public void decrementArm(){
        armPosition--;
    }

    public void setIntakeState(int intakeState) {
        this.intakeState = intakeState;
    }

    public int getArmPosition() {
        return armPosition;
    }
}
