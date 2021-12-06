package org.firstinspires.ftc.teamcode.tests.drivetrain;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;

@TeleOp(name = "Coordinate Movement", group = "Drivetrain")
public class CoordinateMovementTest extends OpMode {
    private MecaDriveMain mecaDrive;

    @Override
    public void init() {
        mecaDrive = new MecaDriveMain(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            mecaDrive.movementCoordinates(0,0,Math.PI,5);
        }else if(gamepad1.x){
            mecaDrive.movementCoordinates(0.5,0,0,5);
        }else if(gamepad1.b){
            mecaDrive.movementCoordinates(0.5,0.5,0,5);
        }else if(gamepad1.y){
            mecaDrive.movementCoordinates(0.5,0.5,0,5);
        }

        mecaDrive.getLocalizer().update();
        Pose2d poseEstimate = mecaDrive.getLocalizer().getPoseEstimate();
        telemetry.addData("X",mecaDrive.getX());
        telemetry.addData("Y",mecaDrive.getY());
        telemetry.addData("Angle", mecaDrive.getRadians());
        telemetry.addData("RRx",poseEstimate.getX());
        telemetry.addData("RRy",poseEstimate.getY());
        telemetry.addData("RRheading", poseEstimate.getHeading());
        for (DcMotor motor : mecaDrive.getMotors()){
            telemetry.addData("Motor Position (in)", motor.getCurrentPosition()/mecaDrive.getTicks()*4*Math.PI);
        }
        telemetry.update();
    }
}
