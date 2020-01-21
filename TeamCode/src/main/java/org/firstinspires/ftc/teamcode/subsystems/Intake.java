package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Intake extends Subsystem {
    private int receive;
    private boolean servoOpen;

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    //private Servo rightServo;
    //private Servo leftServo;


    public Intake(HardwareMap map) {
        receive = -1;
        servoOpen = true;

        leftMotor = map.dcMotor.get(Constants.Intake.LM);
        rightMotor = map.dcMotor.get(Constants.Intake.RM);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightServo = map.servo.get(Constants.Intake.RS);
        //leftServo = map.servo.get(Constants.Intake.LS);
        //rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightServo.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(receive == 0) {
            receive();
        }else if(receive == 1){
            remove();
        }else{ off();}
        //if(servoOpen){
        //    open();
        //}else{
        //    close();
        //}
    }

    public void receive(){
        leftMotor.setPower(-0.2);
        rightMotor.setPower(0.2);
    }
    public void remove(){
        leftMotor.setPower(0.2);
        rightMotor.setPower(-0.2);
    }
    public void off(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    /*
    public void open(){
        leftServo.setPosition(0.3);
        rightServo.setPosition(0.2);
    }
    public void close(){
        leftServo.setPosition(0.7);
        rightServo.setPosition(0.8);


        /*leftServo.getController().pwmDisable();
        rightServo.getController().pwmDisable();
        leftServo.getController().pwmEnable();
        rightServo.getController().pwmEnable();*/


    public void setState(int receive){
        this.receive = receive;
    }
    public int getState() {return receive;}

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
