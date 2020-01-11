package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;
import org.firstinspires.ftc.teamcode.sensors.CameraVuforia;
import org.firstinspires.ftc.teamcode.subsystems.Hook;

@Autonomous(name = "AutoBlue_Found", group = "Auto")
public class AutoBlue_Found extends LinearOpMode {

    private MecaDriveMain mecaDrive;
    private Hook hook;
    private CameraVuforia cameraVuforia;
    private double speed = 0.5;
    private double[] targets = {700,-600,-1900,0};
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
        //for(int i = 0; i < 1;i++)
        mainloop();
        //endMovement();
    }

    public void mainloop(){
        /*
         * TODO: Change horizontal values to negatives for blue side.
         */
        while(mecaDrive.getWheelPositions().get(0) < targets[0]){
            mecaDrive.vertical(speed);
        }
        double backmovement = mecaDrive.getWheelPositions().get(0);
        while(mecaDrive.getWheelPositions().get(0) > targets[1]){
            mecaDrive.horizontal(speed);
        }
        hook.setStateFound(true);
        hook.loop();
        while(mecaDrive.getWheelPositions().get(0) < backmovement){
            mecaDrive.horizontal(-speed);
        }
        hook.setStateFound(false);
        hook.loop();
        while(mecaDrive.getWheelPositions().get(0) > targets[2]){
            mecaDrive.vertical(-speed);
        }
        mecaDrive.horizontal(0);


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
