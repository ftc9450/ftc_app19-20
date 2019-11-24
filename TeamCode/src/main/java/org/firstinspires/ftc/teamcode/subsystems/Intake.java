package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Intake extends Subsystem {
    private IntakeState state;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private CRServo leftServo;
    private CRServo rightServo;


    public enum IntakeState{
        OFF,ON;
    }

    public Intake(HardwareMap map) {
        state = IntakeState.OFF;

        leftMotor = map.dcMotor.get(Constants.Intake.LM);
        rightMotor = map.dcMotor.get(Constants.Intake.RM);
        leftServo = map.crservo.get(Constants.Intake.LS);
        rightServo = map.crservo.get(Constants.Intake.RS);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(state == IntakeState.ON) receive();
        else off();
    }

    public void receive(){
        leftServo.setPower(1);
        rightServo.setPower(1);
        leftMotor.setPower(1);
        rightMotor.setPower(1);
    }
    public void off(){
        leftServo.setPower(0);
        rightServo.setPower(0);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void setState(IntakeState state){
        this.state = state;
    }
}
