package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Grabber extends Subsystem {
    private int up;
    private int servoOpen;

    private DcMotor motor;
    //private DcMotor rightMotor;
    private Servo rightServo;
    private Servo leftServo;


    public Grabber(HardwareMap map) {
        up = -1;
        servoOpen = -1;

        motor = map.dcMotor.get(Constants.Grabber.GR);
        //rightMotor = map.dcMotor.get(Constants.Intake.RM);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightServo = map.servo.get(Constants.Grabber.RS);
        leftServo = map.servo.get(Constants.Grabber.LS);
        //rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void stop() {
        liftOff();
        servosOff();
    }

    @Override
    public void loop() {
        if(up == 1) {
            up();
        }else if(up == 0){
            down();
        }else{
            liftOff();
        }
        if(servoOpen == 1) {
            open();
        }
        else if(servoOpen == 0){
            close();
        }else{
            servosOff();
        }
    }

    public void up(){
        motor.setPower(0.4);
        //rightMotor.setPower(0.2);
    }
    public void down(){
        motor.setPower(-0.4);
        //rightMotor.setPower(-0.2);
    }
    public void liftOff(){
        motor.setPower(0);
        //rightMotor.setPower(0);
    }

    public void open(){
        leftServo.setPosition(leftServo.getPosition() + 0.01);
        rightServo.setPosition(leftServo.getPosition() - 0.01);
    }
    public void close() {
        leftServo.setPosition(leftServo.getPosition() - 0.01);
        rightServo.setPosition(leftServo.getPosition() + 0.01);
    }
    public void servosOff(){
        leftServo.setPosition(leftServo.getPosition());
        rightServo.setPosition(leftServo.getPosition());
    }


        /*leftServo.getController().pwmDisable();
        rightServo.getController().pwmDisable();
        leftServo.getController().pwmEnable();
        rightServo.getController().pwmEnable();*/


    public void setLiftState(int up){
        this.up = up;
    }
    public void setServoState(int servoOpen){
        this.servoOpen = servoOpen;
    }
    public int getState() {return up;}

    /*public void setRightServo(double a){
        rightServo.setPosition(a);
    }

    public boolean isServoOpen() {
        return servoOpen;
    }

    public void setServoOpen(boolean servoOpen) {
        this.servoOpen = servoOpen;
    }*/
}
