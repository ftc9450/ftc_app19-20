package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class FourBar extends Subsystem {
    private int position; //Height in inches above resting state
    private boolean clawState;

    private DcMotor fbMotor;
    private Servo clawServo;

    public FourBar(HardwareMap map) {
        position = 0;
        //fbMotor = map.dcMotor.get(Constants.FourBar.FBM);
        //clawServo = map.servo.get(Constants.FourBar.CS);
        fbMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        reset();//keep motor in lowest position when starting the bot.
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if(clawState){ clawServo.setPosition(0.4); }
        else{ clawServo.setPosition(Servo.MIN_POSITION);}
        //move
    }

    public void setPosition(int position){
        if(this.position + position > 4){
            this.position = 0;
        }else if(this.position + position < 0){
            this.position = 4;
        }else{
            this.position +=position;
        }
    }

    public void move(){
        switch(position){
            case 4:
                fbMotor.setTargetPosition(1440/2);//180
                fbMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
            case 3:
                fbMotor.setTargetPosition(1440*3/16);
                fbMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
            case 2:
                fbMotor.setTargetPosition(1440/8);//45
                fbMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
            case 1:
                fbMotor.setTargetPosition(1440/16);
                fbMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
            case 0:
                fbMotor.setTargetPosition(0);
                fbMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
        }
    }

    public void setFbMotor(double a){
        fbMotor.setPower(a*0.3);
    }
    public void setClawState(boolean clawState){this.clawState = clawState; }
    public boolean getClawState(){return clawState;}
    public int getPosition(){ return position;}


    public void reset(){ //For when the motor position breaks down during manuel control phase
        fbMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fbMotor.setTargetPosition(0);
    }
}
