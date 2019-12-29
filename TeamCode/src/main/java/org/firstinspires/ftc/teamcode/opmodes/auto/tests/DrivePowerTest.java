package org.firstinspires.ftc.teamcode.opmodes.auto.tests;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;
import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;

@Autonomous(name = "DrivePowerTest", group = "Auto")
public class DrivePowerTest extends LinearOpMode {
    private MecaDriveMain mecaDrive;



    public void beginning(){
        //start bot on the space closest to the center of the field
        mecaDrive = new MecaDriveMain(hardwareMap);

    }

    @Override
    public void runOpMode() throws InterruptedException {
        beginning();
        waitForStart();
        while (mecaDrive.getPoseEstimate().getX()<5){
            mecaDrive.vertical(1);
            mecaDrive.updatePoseEstimate();
            telemetry.addData("X: ",mecaDrive.getPoseEstimate().getX());
            telemetry.addData("Y",mecaDrive.getPoseEstimate().getY());
            for(DcMotor motor : mecaDrive.getMotors()){
                telemetry.addData("Motor: ", motor.getCurrentPosition());
            }
            telemetry.update();
        }
        mecaDrive.stop();

    }

}
