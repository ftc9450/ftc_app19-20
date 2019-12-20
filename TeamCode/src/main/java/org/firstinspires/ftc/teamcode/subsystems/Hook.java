package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Hook extends Subsystem {
    private Servo servo;
    private boolean isDown;

    public Hook(HardwareMap map) {
        servo = map.servo.get(Constants.Hook.SERVO);
        isDown = false;
    }

    public void setState(boolean isDown){ this.isDown = isDown;}
    public boolean getState(){return isDown;}

    @Override
    public void stop() {

    }
    @Override
    public void loop() {
        if(isDown){
            servo.setPosition(Servo.MAX_POSITION);
        }else{
            servo.setPosition(Servo.MIN_POSITION);
        }
    }
}
