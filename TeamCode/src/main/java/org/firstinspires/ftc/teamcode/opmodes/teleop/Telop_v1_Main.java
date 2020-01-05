package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SubsystemManager;
import org.firstinspires.ftc.teamcode.util.Vector2D;
import org.firstinspires.ftc.teamcode.subsystems.Hook;

@TeleOp(name = "ManuelMain", group = "TeleOps")
public class Telop_v1_Main extends OpMode {

    SubsystemManager subsystemManager;
    //MecaDrive mecaDrive;
    Arm arm;
    Hook hook;
    Drivetrain drivetrain;


    boolean previousHook;
    boolean previousHookF;

    @Override
    public void init() {

        //mecaDrive = new MecaDrive(); //TODO: put in parameters]
        hook = new Hook(hardwareMap); previousHook = hook.getState(); previousHookF = hook.getStateFound();
        drivetrain = new Drivetrain(hardwareMap);
        arm = new Arm(hardwareMap);
        subsystemManager = new SubsystemManager();
        subsystemManager = subsystemManager.add(drivetrain).add(arm).add(hook);
    }

    public void loop(){
        // TODO: put updated teleop drive code
        Vector2D v = new Vector2D();
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
        drivetrain.setPower(driveSignal);



        if (gamepad1.b && previousHook == hook.getState()) { //toggles hook
            hook.setState(!hook.getState());
        }else if(!gamepad1.b){ previousHook = hook.getState(); }

        if (gamepad1.a && previousHookF == hook.getStateFound()) { //toggles hook
            hook.setStateFound(!hook.getStateFound());
        }else if(!gamepad1.a){ previousHookF = hook.getStateFound(); }


        arm.setServoBack(gamepad2.right_trigger);
        arm.setMotorPowerE(gamepad2.left_stick_y*0.5);
        arm.setMotorPowerR(gamepad2.right_stick_y*0.5);




        if(gamepad2.dpad_down){ }

        subsystemManager.loop();
    }



}
