package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.util.Constants;

//The Servo on the side
public class Hook extends Subsystem {
    private Servo servo;
    private Servo rot;
    private Servo foundServoLeft;
    //private CRServo foundServoLeft;
    private Servo foundServoRight;
    private boolean isUp;
    private boolean isDownFound;
    private boolean isClamped;

    public Hook(HardwareMap map) {
        servo = map.servo.get(Constants.Hook.SERVO);
        rot = map.servo.get(Constants.Hook.ROT);
        //foundServoLeft = map.servo.get(Constants.Hook.FOUND_SERVO_Left);
        //foundServoLeft = map.crservo.get(Constants.Hook.FOUND_SERVO_Left);
        foundServoRight = map.servo.get(Constants.Hook.FOUND_SERVO_Right);
        isUp = true;
        isClamped = true;
        isDownFound = true;
    }

    public void setState(boolean isUp){ this.isUp = isUp;}
    public boolean getState(){return isUp;}
    public void setStateFound(boolean isUp){ this.isDownFound = isUp;}
    public boolean getStateFound(){return isDownFound;}
    public boolean getRotState(){return isClamped;}
    public void setRotState(boolean rot){isClamped = rot;}

    @Override
    public void stop() {

    }
    @Override
    public void loop() {

        //foundServoLeft.setPosition(Servo.MIN_POSITION);
        //foundServoRight.setPosition(Servo.MAX_POSITION);
        if(isClamped){
            rot.setPosition(Servo.MAX_POSITION);

        }else{
            rot.setPosition(Servo.MIN_POSITION);

        }
        if(isUp){
            servo.setPosition(Servo.MAX_POSITION);
        }else{
            servo.setPosition(Servo.MIN_POSITION);
        }

        if(isDownFound){
            //foundServoLeft.setPower(1.0);
            //foundServoLeft.setPosition(0.8);
            foundServoRight.setPosition(0.8);
        }else{
            //foundServoLeft.setPower(0.0);
            //foundServoLeft.setPosition(Servo.MIN_POSITION);
            foundServoRight.setPosition(Servo.MIN_POSITION);
        }

    }
}
