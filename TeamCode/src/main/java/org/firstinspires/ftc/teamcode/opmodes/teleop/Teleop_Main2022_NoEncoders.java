package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;

@TeleOp(name = "MainBot2022_NoEncoders", group = "TeleOps")
public class Teleop_Main2022_NoEncoders extends OpMode {

    MecaDriveMain mecaDrive;
    boolean speedChange,armChange;
    SubsystemManager subsystemManager;
    Arm arm;

    @Override
    public void init() {
        mecaDrive = new MecaDriveMain(hardwareMap);
        speedChange = true;
        arm = new Arm(hardwareMap);
        subsystemManager = (new SubsystemManager()).add(arm);


    }

    @Override
    public void loop() {
        //Player 1
        mecaDrive.fullMovement(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_y);
        if(gamepad1.dpad_up && !gamepad1.dpad_down && speedChange){
            speedChange = false;
            mecaDrive.addSpeed();
        }else if(!gamepad1.dpad_up && gamepad1.dpad_down && speedChange){
            speedChange = false;
            mecaDrive.minusSpeed();
        }else if(!gamepad1.dpad_up && !gamepad1.dpad_down){
            speedChange = true;
        }

        //Player 2

        //Toggles Arm Position
        /*if(gamepad2.dpad_up && armChange){
            armChange = false;
            arm.incrementArm();
        }else if(gamepad2.dpad_down && armChange){
            armChange = false;
            arm.decrementArm();
        }else if(!gamepad2.dpad_up && !gamepad2.dpad_down){
            armChange = true;
        }*/
        arm.getMotorRotate().setPower(0.6*gamepad2.left_stick_y);

        //Toggles Wheel Motor
        if(gamepad2.b){
            arm.setWheelState(true);
        }else if(!gamepad2.b){
            arm.setWheelState(false);
        }
        //Toggles Intake Motor
        if(gamepad2.x){
            arm.setIntakeState(1);
        }else if(gamepad2.a){
            arm.setIntakeState(-1);
        }else{
            arm.setIntakeState(0);
        }



        mecaDrive.getLocalizer().update();
        Pose2d poseEstimate = mecaDrive.getLocalizer().getPoseEstimate();

        subsystemManager.loop();

        telemetry.addData("x",poseEstimate.getX());
        telemetry.addData("y",poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("Drive Speed", mecaDrive.getSpeed());
        telemetry.addData("Arm Position", arm.getArmPosition());
        telemetry.addData("Encoder Arm",arm.getMotorRotate().getCurrentPosition());
        telemetry.addData("Arm Motor",arm.getMotorRotate().getPower());
        //telemetry.addData("4B position: ",fourBar.getPosition());
        for (DcMotor motor : mecaDrive.getMotors()){
            telemetry.addData("Motor Position", motor.getCurrentPosition());
        }
        telemetry.update();

    }
}
