package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;

@TeleOp(name = "Manuel", group = "TeleOps")
public class Telop_v1 extends OpMode {

    SubsystemManager subsystemManager;
    //MecaDrive mecaDrive;
    Intake intake;
    FourBar fourBar;
    Hook hook;
    Drivetrain drivetrain;

    @Override
    public void init() {

        //mecaDrive = new MecaDrive(); //TODO: put in parameters
        fourBar = new FourBar(hardwareMap);
        intake = new Intake(hardwareMap);
        hook = new Hook(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);

        subsystemManager = new SubsystemManager();
        subsystemManager = subsystemManager.add(fourBar).add(intake).add(hook).add(drivetrain);
    }

    public void loop(){
        // TODO: put updated teleop drive code
        //Vector2D v = new Vector2D();
        double x = gamepad1.left_stick_x + (gamepad1.dpad_left? -0.5: gamepad1.dpad_right? 0.5:0);
        double y = -gamepad1.left_stick_y + (gamepad1.dpad_down? -0.5: gamepad1.dpad_up? 0.5:0);
        float z = gamepad1.right_stick_x + (gamepad1.right_trigger - gamepad1.left_trigger)/2;
        double[] driveSignal = new double[]{0,0,0,0};
        double modifier = gamepad1.right_bumper? 0.5:1;
        driveSignal[0]= x + y + z; // up on left stick is -1.
        driveSignal[1]= -x + y + z;
        driveSignal[2]= -x + y - z;
        driveSignal[3]= x + y - z;
        for (int i = 0; i < 4; i++) driveSignal[i] *= modifier;
        drivetrain.setPower(driveSignal);


        if (gamepad1.a) {//toggles intake
            intake.setState(!intake.getState());
        }

        if (gamepad2.b) { //toggles hook
            hook.setState(!hook.getState());
        }

        if (gamepad2.left_bumper) { }


        //control fourbar
        if(gamepad2.dpad_right){ }
            fourBar.setPosition(-1);
        if(gamepad2.dpad_up){
            fourBar.setPosition(1);
        }

        if(gamepad2.dpad_down){ }

        subsystemManager.loop();
    }



}
