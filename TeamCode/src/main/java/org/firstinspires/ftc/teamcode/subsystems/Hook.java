package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.util.Constants;

//The Servo on the side
public class Hook extends Subsystem {
    private Servo servo;
    private Servo foundServo;
    private boolean isUp;
    private boolean isDownFound;

    public Hook(HardwareMap map) {
        servo = map.servo.get(Constants.Hook.SERVO);
        foundServo = map.servo.get(Constants.Hook.FOUND_SERVO);
        isUp = true;
        isDownFound = false;
    }

    public void setState(boolean isUp){ this.isUp = isUp;}
    public boolean getState(){return isUp;}
    public void setStateFound(boolean isUp){ this.isDownFound = isUp;}
    public boolean getStateFound(){return isDownFound;}

    @Override
    public void stop() {

    }
    @Override
    public void loop() {
        if(isUp){
            servo.setPosition(Servo.MAX_POSITION);
        }else{
            servo.setPosition(Servo.MIN_POSITION);
        }
        if(isDownFound){
            foundServo.setPosition(/*0.3*/ Servo.MAX_POSITION);
        }else{
            foundServo.setPosition(0.4);
        }
    }
}
