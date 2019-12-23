package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Vector2D;
import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;

@TeleOp(name = "RR Test", group = "TeleOps")
public class Telop_RRTest extends OpMode {

    SubsystemManager subsystemManager;
    MecaDrive mecaDrive;
    Intake intake;
    FourBar fourBar;
    Hook hook;
    //Drivetrain drivetrain;

    @Override
    public void init() {

        mecaDrive = new MecaDrive(hardwareMap); //TODO: put in parameters
        fourBar = new FourBar(hardwareMap);
        intake = new Intake(hardwareMap);
        hook = new Hook(hardwareMap);
        //drivetrain = new Drivetrain(hardwareMap);

        subsystemManager = new SubsystemManager();
        subsystemManager = subsystemManager.add(fourBar).add(intake).add(hook);
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
        if(gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0){
            mecaDrive.fullMovement(gamepad1.left_stick_x,gamepad1.left_stick_y);
        }else{
            mecaDrive.turn(Math.tan(gamepad1.right_stick_y/gamepad1.right_stick_x));
        }



        if (gamepad1.a) {//toggles intake
            intake.setState(!intake.getState());
        }

        if (gamepad2.b) { //toggles hook
            hook.setState(!hook.getState());
        }

        if (gamepad2.left_bumper) { }

        if(gamepad2.x){
            fourBar.setClawState(!fourBar.getClawState());
        }
        //control fourbar position
        if(gamepad2.dpad_right){ }
        fourBar.setPosition(-1);
        if(gamepad2.dpad_up){
            fourBar.setPosition(1);
        }

        if(gamepad2.dpad_down){ }

        subsystemManager.loop();

        mecaDrive.getLocalizer().update();
        Pose2d poseEstimate = mecaDrive.getLocalizer().getPoseEstimate();

        telemetry.addData("x",poseEstimate.getX());
        telemetry.addData("y",poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("4B position: ",fourBar.getPosition());
        for (DcMotor motor : mecaDrive.getMotors()){
            telemetry.addData("Motor Position (in)", motor.getCurrentPosition()/mecaDrive.getTicks()*4*Math.PI);
        }
        telemetry.update();
    }



}