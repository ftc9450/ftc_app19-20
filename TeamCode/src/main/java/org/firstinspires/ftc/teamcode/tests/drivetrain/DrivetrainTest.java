package org.firstinspires.ftc.teamcode.tests.drivetrain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.MotionTracker;

import java.util.Arrays;

@TeleOp(name = "Drivetrain Test", group = "Drivetrain")
public class DrivetrainTest extends OpMode {
    private Drivetrain drive;
    private Gyroscope imu;
    private MotionTracker tracker;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    @Override
    public void init() {
        drive = new Drivetrain(hardwareMap);
        imu = new Gyroscope(hardwareMap.get(BNO055IMU.class, "imu"));
        leftFront = hardwareMap.dcMotor.get(Constants.Drivetrain.LF);
        leftBack = hardwareMap.dcMotor.get(Constants.Drivetrain.LB);
        rightFront = hardwareMap.dcMotor.get(Constants.Drivetrain.RF);
        rightBack = hardwareMap.dcMotor.get(Constants.Drivetrain.RB);
    }

    @Override
    public void loop() {
        double FrontLeftVal =  gamepad1.left_stick_y - (gamepad1.left_stick_x)  + gamepad1.right_stick_x;
        double FrontRightVal =  gamepad1.left_stick_y  + (gamepad1.left_stick_x) - gamepad1.right_stick_x;
        double BackLeftVal = gamepad1.left_stick_y  + (gamepad1.left_stick_x)  + gamepad1.right_stick_x;
        double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - gamepad1.right_stick_x;

        //Move range to between 0 and +1, if not already
        double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
        Arrays.sort(wheelPowers);

        if (wheelPowers[3] > 1) {
            FrontLeftVal /= wheelPowers[3];
            FrontRightVal /= wheelPowers[3];
            BackLeftVal /= wheelPowers[3];
            BackRightVal /= wheelPowers[3];
        }
        leftFront.setPower(FrontLeftVal);
        leftBack.setPower(BackLeftVal);
        rightFront.setPower(FrontRightVal);
        rightBack.setPower(BackRightVal);
    }
}
