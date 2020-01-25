package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Vector2D;
import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;

@TeleOp(name = "MainBot", group = "TeleOps")
public class Telop_RRTest extends OpMode {

    SubsystemManager subsystemManager;
    MecaDriveMain mecaDrive;
    Intake intake;
    Grabber grabber;
    //FourBar fourBar;
    Hook hook;
    //Arm arm;
    //Drivetrain drivetrain;
    boolean previousHook;
    boolean previousHookF;
    boolean previousRot;
    int previousIntake;
    boolean speedChange;

    @Override
    public void init() {

        mecaDrive = new MecaDriveMain(hardwareMap);
        speedChange = true;

        //fourBar = new FourBar(hardwareMap);
        //intake = new Intake(hardwareMap);
        grabber = new Grabber(hardwareMap);
        hook = new Hook(hardwareMap); previousHook = hook.getState();  previousHookF = hook.getStateFound(); previousRot = hook.getRotState();
        // arm = new Arm(hardwareMap); previousGrab = arm.grabState();
        //drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap); previousIntake = intake.getState();
        subsystemManager = new SubsystemManager();
        subsystemManager = subsystemManager.add(hook).add(intake).add(grabber);//.add(fourBar).add(intake)
    }

    public void loop(){
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


        //intake controls
        if (gamepad2.a && previousIntake == intake.getState() && !gamepad2.y) {//toggles intake
            if(intake.getState() !=0){
                intake.setState(0);
            }else{
                intake.setState(-1);
            }
        }else if(gamepad2.y && previousIntake == intake.getState() && !gamepad2.a){
            if(intake.getState() != 1){
                intake.setState(1);
            }else{
                intake.setState(-1);
            }
        } else if(!gamepad2.a && !gamepad2.y){
            previousIntake = intake.getState();
        }

        if (gamepad1.b && previousHook == hook.getState()) { //toggles hook
            hook.setState(!hook.getState());
        }else if(!gamepad1.b){ previousHook = hook.getState(); }

        if (gamepad1.a && previousHookF == hook.getStateFound()) { //toggles hook
            hook.setStateFound(!hook.getStateFound());
        }else if(!gamepad1.a){ previousHookF = hook.getStateFound(); }

        if (gamepad1.y && previousRot == hook.getRotState()) { //toggles hook
            hook.setRotState(!hook.getRotState());
        }else if(!gamepad1.y){ previousRot = hook.getRotState(); }

        /*if(gamepad2.x){
            fourBar.setClawState(!fourBar.getClawState());
        }*/
        //control fourbar position
        /*if(gamepad2.dpad_right){ }
        fourBar.setPosition(-1);
        if(gamepad2.dpad_up){
            fourBar.setPosition(1);
        }*/

        //grabber controls
        if (gamepad2.dpad_up){
            grabber.setLiftState(1);
        }else if(gamepad2.dpad_down){
            grabber.setLiftState(0);
        } else{
            grabber.setLiftState(-1);
        }

        if (gamepad2.dpad_right){
            grabber.setServoState(1);
        }else if(gamepad2.dpad_left){
            grabber.setServoState(0);
        } else{
            grabber.setServoState(-1);
        }

        subsystemManager.loop();

        mecaDrive.getLocalizer().update();
        Pose2d poseEstimate = mecaDrive.getLocalizer().getPoseEstimate();

        telemetry.addData("x",poseEstimate.getX());
        telemetry.addData("y",poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("Drive Speed", mecaDrive.getSpeed());
        telemetry.addData("Found Hook",hook.getStateFound());
        telemetry.addData("Hook",hook.getState());
        telemetry.addData("Rot Hook",hook.getRotState());
        //telemetry.addData("4B position: ",fourBar.getPosition());
        for (DcMotor motor : mecaDrive.getMotors()){
            telemetry.addData("Motor Position", motor.getCurrentPosition());
        }
        telemetry.update();
    }



}
