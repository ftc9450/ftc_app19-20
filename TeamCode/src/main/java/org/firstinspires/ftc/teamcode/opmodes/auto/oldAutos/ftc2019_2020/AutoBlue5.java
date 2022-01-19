package org.firstinspires.ftc.teamcode.opmodes.auto.oldAutos.ftc2019_2020;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;
import org.firstinspires.ftc.teamcode.sensors.CameraVuforia;
import org.firstinspires.ftc.teamcode.subsystems.Hook;

@Autonomous(name = "AutoBlue_Skystone", group = "Auto")
public class AutoBlue5 extends LinearOpMode {

    private MecaDriveMain mecaDrive;
    private Hook hook;
    private CameraVuforia cameraVuforia;
    private double speed = 0.5;
    private double[] targets = {745,590,750,200};
    //private FourBar fourbar;
    //private Intake intake;


    public void beginning(){
        //start bot on the space closest to the center of the field
        mecaDrive = new MecaDriveMain(hardwareMap);
        cameraVuforia = new CameraVuforia(hardwareMap);
        hook = new Hook(hardwareMap);
        //fourbar = new FourBar(hardwareMap);
        //intake = new Intake(hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        beginning();
        waitForStart();
        hook.loop();
        //for(int i = 0; i < 1;i++)
        mainloop();
        //endMovement();
    }

    public void mainloop(){
        /*
         * TODO: Change horizontal values to negatives for blue side.
         */
        while(mecaDrive.getMotors().get(0).getCurrentPosition() < targets[0]+targets[1]){
            mecaDrive.fullMovement(-speed,0,0);
        }
        double finalmove = mecaDrive.getMotors().get(0).getCurrentPosition()+targets[2];
        /*while(!cameraVuforia.isTargetVisible()){
            mecaDrive.vertical(-speed);
            cameraVuforia.loop(telemetry);
        }*/
        double lastPos = mecaDrive.getMotors().get(0).getCurrentPosition();
        double nextPos = mecaDrive.getMotors().get(0).getCurrentPosition()+targets[1];
        mecaDrive.horizontal(0);
        hook.setState(false);
        hook.loop();
        while(mecaDrive.getMotors().get(0).getCurrentPosition() > targets[0]){
            mecaDrive.fullMovement(speed,0,0);
        }
        mecaDrive.horizontal(0);
        while(mecaDrive.getMotors().get(0).getCurrentPosition() < 1850){
            mecaDrive.fullMovement(0,speed,0);
        }
        /*double rotateticks = mecaDrive.getMotors().get(0).getCurrentPosition()+targets[3];
        while(mecaDrive.getMotors().get(0).getCurrentPosition() > rotateticks){
            mecaDrive.turn(speed);
        }*/
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
        telemetry.addData("Motor1", mecaDrive.getMotors().get(0).getCurrentPosition());
        telemetry.update();
    }

}
