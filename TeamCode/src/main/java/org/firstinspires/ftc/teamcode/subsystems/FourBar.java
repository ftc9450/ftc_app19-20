package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Constants;

public class FourBar extends Subsystem {
    private double height; //Height in inches above resting state

    private DcMotor fbMotor;

    public FourBar(HardwareMap map) {
        height = 0;
        fbMotor = map.dcMotor.get(Constants.FourBar.FBM);
        reset();//keep motor in lowest position when starting the bot.
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
        fbMotor.setTargetPosition((int)(height*538));
    }

    public void reset(){ //For when the motor position breaks down during manuel control phase
        fbMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fbMotor.setTargetPosition(0);
    }
}
