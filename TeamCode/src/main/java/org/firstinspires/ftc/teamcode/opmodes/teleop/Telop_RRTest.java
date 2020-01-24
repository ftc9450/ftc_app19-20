package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driveModes.MecaDriveMain;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Vector2D;
import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;

@TeleOp(name = "RR Test", group = "TeleOps")
public class Telop_RRTest extends OpMode {

    SubsystemManager subsystemManager;
    MecaDriveMain mecaDrive;
    Intake intake;
    //FourBar fourBar;
    //Hook hook;
    //Arm arm;
    //Drivetrain drivetrain;
    //boolean previousHook;

    //boolean previousHookF;
    //boolean previousGrab;

    @Override
    public void init() {

        mecaDrive = new MecaDriveMain(hardwareMap); //TODO: put in parameters
        //fourBar = new FourBar(hardwareMap);
        //intake = new Intake(hardwareMap);
        //hook = new Hook(hardwareMap); previousHook = hook.getState();  previousHookF = hook.getStateFound();
        // arm = new Arm(hardwareMap); previousGrab = arm.grabState();
        //drivetrain = new Drivetrain(hardwareMap);

        subsystemManager = new SubsystemManager();
        //subsystemManager = subsystemManager.add(arm);//.add(fourBar).add(intake)
    }

    public void loop(){
        // TODO: put updated teleop drive code
        /*Vector2D v = new Vector2D();
        v.x = gamepad1.left_stick_x + (gamepad1.dpad_left? -0.5: gamepad1.dpad_right? 0.5:0);
        v.y = -gamepad1.left_stick_y + (gamepad1.dpad_down? -0.5: gamepad1.dpad_up? 0.5:0);
        float z = gamepad1.right_stick_x + (gamepad1.right_trigger - gamepad1.left_trigger)/2;
        double[] driveSignal = new double[]{0,0,0,0};
        double modifier = gamepad1.right_bumper? 0.5:1;
        driveSignal[0]= v.x + v.y + z; // up on left stick is -1.
        driveSignal[1]= -v.x + v.y + z;
        driveSignal[2]= -v.x + v.y - z;
        driveSignal[3]= v.x + v.y - z;
        for (int i = 0; i < 4; i++) driveSignal[i] *= modifier;
        drivetrain.setPower(driveSignal);*/
        mecaDrive.fullMovement(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_y);
        /*
        arm.setMotorPowerE(gamepad2.left_stick_y*0.5);
        arm.setMotorPowerR(gamepad2.right_stick_y*0.5);
        if (gamepad2.a && previousGrab == arm.grabState()) { //toggles hook
            arm.setGrabState();
        }else if(!gamepad2.a){ previousGrab = arm.grabState(); }

         */


        /*if (gamepad1.a) {//toggles intake
            intake.setState(!intake.getState());
        }*/

        /*if (gamepad1.b && previousHook == hook.getState()) { //toggles hook
            hook.setState(!hook.getState());
        }else if(!gamepad1.b){ previousHook = hook.getState(); }
        if (gamepad1.a && previousHookF == hook.getStateFound()) { //toggles hook
            hook.setStateFound(!hook.getStateFound());
        }else if(!gamepad1.a){ previousHookF = hook.getStateFound(); }*/

        /*if(gamepad2.x){
            fourBar.setClawState(!fourBar.getClawState());
        }*/
        //control fourbar position
        /*if(gamepad2.dpad_right){ }
        fourBar.setPosition(-1);
        if(gamepad2.dpad_up){
            fourBar.setPosition(1);
        }*/

        if(gamepad2.dpad_down){ }

        subsystemManager.loop();

        mecaDrive.getLocalizer().update();
        Pose2d poseEstimate = mecaDrive.getLocalizer().getPoseEstimate();

        telemetry.addData("x",poseEstimate.getX());
        telemetry.addData("y",poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        //telemetry.addData("4B position: ",fourBar.getPosition());
        for (DcMotor motor : mecaDrive.getMotors()){
            telemetry.addData("Motor Position", motor.getCurrentPosition());
        }
        telemetry.update();
    }



}
