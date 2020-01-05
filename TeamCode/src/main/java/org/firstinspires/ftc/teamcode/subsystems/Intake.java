package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Intake extends Subsystem {
    private boolean receive;

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private Servo rightServo;


    public Intake(HardwareMap map) {
        receive = true;

        leftMotor = map.dcMotor.get(Constants.Intake.LM);
        rightMotor = map.dcMotor.get(Constants.Intake.RM);
        rightServo = map.servo.get(Constants.Intake.RS);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(receive) receive();
        else off();
    }

    public void receive(){
        leftMotor.setPower(1);
        rightMotor.setPower(1);
    }
    public void off(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void setState(boolean receive){
        this.receive = receive;
    }
    public boolean getState() {return receive;}

    public void setRightServo(double a){
        rightServo.setPosition(a);
    }
}
