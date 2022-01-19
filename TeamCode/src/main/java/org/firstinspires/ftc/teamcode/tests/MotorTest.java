package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MotorTest", group = "TeleOps")
public class MotorTest extends OpMode {


    private DcMotor motor;


    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("testMotor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        motor.setPower(gamepad1.left_stick_y*0.3);
        telemetry.addData("Motor Pos",motor.getCurrentPosition());

    }
}
