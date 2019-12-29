package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Hook extends Subsystem {
    private Servo servo;
    private boolean isUp;

    public Hook(HardwareMap map) {
        servo = map.servo.get(Constants.Hook.SERVO);
        isUp = true;
    }

    public void setState(boolean isUp){ this.isUp = isUp;}
    public boolean getState(){return isUp;}

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
    }
}
