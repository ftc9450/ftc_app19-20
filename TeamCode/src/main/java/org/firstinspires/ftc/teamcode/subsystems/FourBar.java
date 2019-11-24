package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class FourBar extends Subsystem {
    private double height; //Height in inches above resting state

    private Servo topServo;
    private Servo bottomServo;

    public FourBar(HardwareMap map) {
        height = 0;
        topServo = map.servo.get(Constants.FourBar.TS);
        bottomServo = map.servo.get(Constants.FourBar.BS);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        move();
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void move(){

    }
}
