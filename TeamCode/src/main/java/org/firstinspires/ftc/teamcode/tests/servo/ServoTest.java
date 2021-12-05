package org.firstinspires.ftc.teamcode.tests.servo;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;
import org.firstinspires.ftc.teamcode.util.MotionTracker;

import java.util.Arrays;

@TeleOp(name = "ServoTest", group = "Drivetrain")
public class ServoTest extends OpMode {
    //private Drivetrain drive;
    private MecaDrive mecaDrive;
    //private Gyroscope imu;
    private MotionTracker tracker;
    //private DcMotor leftB,leftF,rightB,rightF;
    //private double x;
    //private double y;
    //private List<Double> wheelVelo;
    private Servo servo1;
    private Servo servo2;
    private CRServo cr1;
    private DcMotor motor;



    @Override
    public void init() {
        cr1 = hardwareMap.crservo.get("foundServoRight");
        cr1.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        cr1.setPower(gamepad1.left_stick_y);
        telemetry.addData("Servo",cr1.getPower());
        telemetry.update();
    }
}
