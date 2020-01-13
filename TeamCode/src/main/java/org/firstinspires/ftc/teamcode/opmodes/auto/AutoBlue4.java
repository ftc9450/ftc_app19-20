package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;
import org.firstinspires.ftc.teamcode.sensors.CameraVuforia;
import org.firstinspires.ftc.teamcode.subsystems.Hook;

@Autonomous(name = "Not Good", group = "Auto")
public class AutoBlue4 extends LinearOpMode {

    private MecaDriveMain mecaDrive;
    private Hook hook;
    private CameraVuforia cameraVuforia;
    private double speed = 0.5;
    private double[] targets = {-745,590,750,200};
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
        while(mecaDrive.getWheelPositions().get(0) > targets[0]){
            mecaDrive.horizontal(speed);
        }
        double finalmove = mecaDrive.getWheelPositions().get(0)+targets[2];
        /*while(!cameraVuforia.isTargetVisible()){
            mecaDrive.vertical(-speed);
            cameraVuforia.loop(telemetry);
        }*/
        double lastPos = mecaDrive.getWheelPositions().get(0);
        double nextPos = mecaDrive.getWheelPositions().get(0)-targets[1];
        while(mecaDrive.getWheelPositions().get(0) > nextPos){
            mecaDrive.horizontal(speed);
        }
        hook.setState(false);
        hook.loop();
        while(mecaDrive.getWheelPositions().get(0) < lastPos){
            mecaDrive.horizontal(-speed);
        }
        mecaDrive.horizontal(0);
        while(mecaDrive.getWheelPositions().get(0) < finalmove){
            mecaDrive.vertical(speed);
        }
        double rotateticks = mecaDrive.getWheelPositions().get(0)+targets[3];
        while(mecaDrive.getWheelPositions().get(0) > rotateticks){
            mecaDrive.turn(speed);
        }
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
