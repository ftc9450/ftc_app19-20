package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Constants;

public class FourBar extends Subsystem {
    private int position; //Height in inches above resting state

    private DcMotor fbMotor;

    public FourBar(HardwareMap map) {
        position = 0;
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

    public void setPosition(int position){
        if(this.position == 4 && position > 0){
            this.position = 0;
        }else if(this.position == 0 && position < 0){
            this.position = 4;
        }else{
            this.position +=position;
        }
    }

    public void move(){
        switch(position){
            case 4:
                fbMotor.setTargetPosition(1440/2);//180
                break;
            case 3:
                fbMotor.setTargetPosition(1440*3/16);
                break;
            case 2:
                fbMotor.setTargetPosition(1440/8);//45
                break;
            case 1:
                fbMotor.setTargetPosition(1440/16);
                break;
            case 0:
                fbMotor.setTargetPosition(0);
                break;
        }
    }

    public void reset(){ //For when the motor position breaks down during manuel control phase
        fbMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fbMotor.setTargetPosition(0);
    }
}
