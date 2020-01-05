package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;
import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;
import org.firstinspires.ftc.teamcode.sensors.Bumpers;
import org.firstinspires.ftc.teamcode.sensors.Camera;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.util.Constants;

@Autonomous(name = "AutoRed4", group = "Auto")
public class AutoRed4 extends LinearOpMode {

    private MecaDriveMain mecaDrive;
    private Camera camera;
    private boolean hooked = false;
    private boolean stone = false;
    private Hook hook;
    private double speed = 0.5;
    private double[] targets = {};
    //private FourBar fourbar;
    //private Intake intake;


    public void beginning(){
        //start bot on the space closest to the center of the field
        mecaDrive = new MecaDriveMain(hardwareMap);
        camera = new Camera(hardwareMap);
        hook = new Hook(hardwareMap);
        //fourbar = new FourBar(hardwareMap);
        //intake = new Intake(hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        beginning();
        waitForStart();
        //for(int i = 0; i < 1;i++)
        mainloop();
        //endMovement();
    }

    public void mainloop(){
        while(mecaDrive.getMotors().get(0).getCurrentPosition() > -50){
            mecaDrive.horizontal(-speed);
            getData();
        }
        while(true){}
        /*while(camera.isDetected()){
            mecaDrive.vertical(-speed);
            getData();
        }
        while(true){}
        int nextTarget = mecaDrive.getMotors().get(0).getCurrentPosition()+20;
        while(mecaDrive.getMotors().get(0).getCurrentPosition() < nextTarget){
            mecaDrive.vertical(speed);
            getData();
        }
        while(mecaDrive.getMotors().get(0).getCurrentPosition() < nextTarget){
            mecaDrive.horizontal(-speed);
            getData();
        }
        while(true){}
        hook.setState(!hook.getState());
        hook.loop();
        while(mecaDrive.getMotors().get(0).getCurrentPosition() < nextTarget){
            mecaDrive.horizontal(speed);
            getData();
        }
        while(true){}
        while(mecaDrive.getMotors().get(0).getCurrentPosition() < nextTarget){
            mecaDrive.vertical(speed);
            getData();
        }
        while(true){}*/
    }

    public void endMovement(){
        while(mecaDrive.getPoseEstimate().getX() < -8 ){
            mecaDrive.horizontalAuto(10);
        }
        stop();
    }
    public void getData(){
        telemetry.addData("X",mecaDrive.getPoseEstimate().getX());
        telemetry.addData("Y", mecaDrive.getPoseEstimate().getY());
        telemetry.addData("Motor1", mecaDrive.getMotors().get(0));
        telemetry.update();
    }

}
