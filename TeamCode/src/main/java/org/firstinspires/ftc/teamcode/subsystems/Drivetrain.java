package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.MotionTracker;

/**
 * Created by dhruv on 1/20/18.
 */

public class Drivetrain extends Subsystem {
    private DrivetrainState state;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private double maxPower;
    private int targetPosition;
    private double targetAngle;

    public enum DrivetrainState{
        Linear, Turning
    }

    public Drivetrain(DcMotor lf, DcMotor lb, DcMotor rf, DcMotor rb) {
        leftFront = lf;
        leftBack = lb;
        rightFront = rf;
        rightBack = rb;
        maxPower=Constants.Drivetrain.HIGH_POWER;
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        maxPower = 1;
        this.setState(DrivetrainState.Linear);
    }

    public Drivetrain(HardwareMap map) {
        leftFront = map.dcMotor.get(Constants.Drivetrain.LF);
        leftBack = map.dcMotor.get(Constants.Drivetrain.LB);
        rightFront = map.dcMotor.get(Constants.Drivetrain.RF);
        rightBack = map.dcMotor.get(Constants.Drivetrain.RB);
        maxPower=Constants.Drivetrain.HIGH_POWER;

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        maxPower = 1;
        this.setState(DrivetrainState.Linear);
    }//uwu

    public void setPower(double power) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);
    }

    public void setPower(double driveSignal[]) {
        double scale = maxPower;
        double diff=Math.abs(rightFront.getPower() - (driveSignal[2] * maxPower));
        if(diff > 1){
            scale *= 1 - (diff / 4.0);
        }
        leftFront.setPower(driveSignal[0] * scale);
        leftBack.setPower(driveSignal[1] * scale);
        rightFront.setPower(driveSignal[2] * scale);
        rightBack.setPower(driveSignal[3] * scale);
    }
    public void moveFB(double distance){}
    public void pivot(double degrees){}
    public void moveFB(double distance, double power, boolean forward, MotionTracker tracker){ // distance (in inches) and power will always be positive
        tracker.enableAndResetEncoders();
        double leftPower = power * Constants.Drivetrain.FB_LEFT_POWER;
        double rightPower = power * Constants.Drivetrain.FB_RIGHT_POWER;
        int clicks = (int) (distance * Constants.MotionTracker.CLICKS_PER_INCH);
        if(forward){
            setPower(new double[]{leftPower,leftPower,rightPower,rightPower});
            while( tracker.getYEncoderValue()<clicks){}
        }else{
            setPower(new double[]{-leftPower,-leftPower,-rightPower,-rightPower});
            while( tracker.getYEncoderValue()>(-clicks)){}
        }
        setPower(new double[]{0,0,0,0});
        /*
        state = DrivetrainState.Linear;
        int clicks = (int) (distance * Constants.MotionTracker.CLICKS_PER_INCH);
        int initialEncoderValue = tracker.getYEncoderValue();
        double leftPower = power * Constants.Drivetrain.FB_LEFT_POWER;
        double rightPower = power * Constants.Drivetrain.FB_RIGHT_POWER;
        if(forward){
            setPower(new double[]{leftPower,leftPower,rightPower,rightPower});
            try{while(tracker.getYEncoderValue() - initialEncoderValue < clicks - Constants.Drivetrain.FB_THRESHOLD) tracker.updatePosition();}catch(Exception e){}
        } else{
            setPower(new double[]{-leftPower,-leftPower,-rightPower,-rightPower});
            try{while(initialEncoderValue - tracker.getYEncoderValue() < clicks - Constants.Drivetrain.FB_THRESHOLD) tracker.updatePosition();}catch(Exception e){}
        }
        setPower(new double[]{0,0,0,0});
        tracker.updatePosition();*/
    }

    public void enableAndResetEncoders(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void disconnectEncoders(){
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setState(DrivetrainState state){
        this.state = state;
    }

    public DrivetrainState getState(){
        return state;
    }

    public String toString(){
        return String.valueOf((leftFront.getCurrentPosition()+rightFront.getCurrentPosition())/2 );
    }

    @Override
    public void stop() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    public int getPosition() {
        return -leftBack.getCurrentPosition();
    }

    public Drivetrain setTargetPosition(int distance) {
        targetPosition = distance + this.getPosition();
        return this;
    }

    public int getTargetPosition() {
        return targetPosition - this.getPosition();
    }

    public Drivetrain setTargetAngle(int angle) {
        targetAngle = angle + getAngle();
        return this;
    }

    public double getTargetAngle() {
        return targetAngle - getAngle();
    }

    public boolean isBusy() {
        return Math.abs(this.getPosition() - 10) < 10;
    }

    public double getAngle() {
        return 0;
    }

    //backleft
    //frontright
    @Override
    public void loop() {
        switch (state) {
            case Linear:
                if (isBusy()) {
                    double error = this.getTargetPosition() - this.getPosition();
                    this.setPower(1.0 / (1.0 + Math.exp(-error/2500 + 4)));
                } else {
                    this.stop();
                }
                break;
            case Turning:
                double error = Math.abs(this.getTargetAngle() - this.getAngle());
                if (error > 10) {
                    double power = 1.0 / (1.0 + Math.exp(-error/2500 + 4));
                    this.setPower(new double[]{power, power, -power, -power});
                } else {
                    this.stop();
                }
        }
    }

    public DcMotor getLeftFront() {
        return leftFront;
    }

    public DcMotor getLeftBack() {
        return leftBack;
    }

    public DcMotor getRightFront() {
        return rightFront;
    }

    public DcMotor getRightBack() {
        return rightBack;
    }
}
