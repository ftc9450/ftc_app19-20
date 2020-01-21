package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;
import org.firstinspires.ftc.teamcode.sensors.Camera;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;

@TeleOp(name = "RR Test PracBot", group = "TeleOps")
public class Telop_RRTestSub extends OpMode {

    SubsystemManager subsystemManager;
    MecaDrive mecaDrive;
    Intake intake;
    //FourBar fourBar;
    Hook hook;
    //boolean previousHook;

    boolean previousHookF;
    //boolean previousClaw;
    int previousIntake;
    //boolean previousIntakeServos;
    boolean speedChange;

    //boolean fbBuffer;

    @Override
    public void init() {

        mecaDrive = new MecaDrive(hardwareMap); //TODO: put in parameters
        //fourBar = new FourBar(hardwareMap); previousClaw = fourBar.getClawState(); fbBuffer = true;
        intake = new Intake(hardwareMap); previousIntake = intake.getState();
        hook = new Hook(hardwareMap); previousHookF = hook.getStateFound();

        speedChange = true;

        subsystemManager = new SubsystemManager();
        subsystemManager = subsystemManager.add(intake);
        subsystemManager = subsystemManager.add(hook);
        //subsystemManager = subsystemManager.add(fourBar).add(intake).add(hook);
    }

    public void loop(){
        //drive control
        mecaDrive.fullMovement(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);

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
        /*
        if (gamepad2.b && previousIntakeServos == intake.isServoOpen()) {//toggles intake
            intake.setServoOpen(!intake.isServoOpen());
        }else if(!gamepad2.b){
            previousIntakeServos = intake.isServoOpen();
        }
        */

        //hook controls
        if (gamepad1.a && previousHookF == hook.getStateFound()) { //toggles hook
            hook.setStateFound(!hook.getStateFound());
        }else if(!gamepad1.a){ previousHookF = hook.getStateFound(); }
        /*
        if (gamepad1.b && previousHook == hook.getState()) { //toggles hook
            hook.setState(!hook.getState());
        }else if(!gamepad1.b){ previousHook = hook.getState(); }
        if (gamepad1.a && previousHookF == hook.getStateFound()) { //toggles hook
            hook.setStateFound(!hook.getStateFound());
        }else if(!gamepad1.a){ previousHookF = hook.getStateFound(); }

        //control claw
        if(gamepad2.x && previousClaw == fourBar.getClawState()){
            fourBar.setClawState(!fourBar.getClawState());
        }else if(!gamepad2.x){
            previousClaw = fourBar.getClawState();
        }
        */

        //control fourbar position
        /*if(gamepad2.dpad_down && fbBuffer){
            fourBar.setPosition(-1);
            fbBuffer = false;
        }
        else if(gamepad2.dpad_up && fbBuffer){
            fourBar.setPosition(1);
            fbBuffer = false;
        }else if(!gamepad2.dpad_up && !gamepad2.dpad_down){
            fbBuffer = true;
        }*/
        //fourBar.setFbMotor(gamepad2.left_stick_y);


        subsystemManager.loop();

        mecaDrive.getLocalizer().update();
        Pose2d poseEstimate = mecaDrive.getLocalizer().getPoseEstimate();

        telemetry.addData("x",poseEstimate.getX());
        telemetry.addData("y",poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("Drive Speed", mecaDrive.getSpeed());
        //telemetry.addData("4B position: ",fourBar.getPosition());
        telemetry.addData("Previous Intake",previousIntake);
        telemetry.addData("Intake", intake.getState());
        //telemetry.addData("Previous InServo", previousIntakeServos);
        telemetry.addData("Found Hook",hook.getStateFound());
        //telemetry.addData("Previ Found",previousHookF);
        //telemetry.addData("Previ Claw",previousClaw);
        //telemetry.addData("Claw",fourBar.getClawState());
        for (DcMotor motor : mecaDrive.getMotors()){
            telemetry.addData("Motor Position", motor.getCurrentPosition());
        }
        telemetry.update();


    }



}
