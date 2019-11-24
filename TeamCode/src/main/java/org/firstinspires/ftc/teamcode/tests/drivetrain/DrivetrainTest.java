package org.firstinspires.ftc.teamcode.tests.drivetrain;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.acmerobotics.roadrunner.kinematics.MecanumKinematics;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.MecaDrive;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.MotionTracker;

import java.util.Arrays;

@TeleOp(name = "Drivetrain Test", group = "Drivetrain")
public class DrivetrainTest extends OpMode {
    //private Drivetrain drive;
    private MecaDrive mecaDrive;
    //private Gyroscope imu;
    private MotionTracker tracker;
    //private DcMotor leftB,leftF,rightB,rightF;
    //private double x;
    //private double y;
    //private List<Double> wheelVelo;

    //TODO: Add these into Constants
    private static double kV,kA,kStatic,trackWidth,wheelBase,wheelDiam;
    private static final MotorConfigurationType MOTOR_CONFIG =
            MotorConfigurationType.getMotorType(NeveRest20Gearmotor.class);
    public static double getMaxRpm() {
        return MOTOR_CONFIG.getMaxRPM();
    }
    public static double rpmToVelocity(double rpm) {
        return rpm * 2 * Math.PI * wheelDiam / 60.0;
    }

    @Override
    public void init() {
        wheelDiam = 4;
        kV = 1.0/rpmToVelocity(getMaxRpm()); kA = 0; kStatic = 0; trackWidth=16; wheelBase = 10.5;
        mecaDrive = new MecaDrive(kV,kA,kStatic,trackWidth,wheelBase,hardwareMap);
        mecaDrive.getDrive().getLeftFront().setDirection(DcMotor.Direction.FORWARD);
        mecaDrive.getDrive().getLeftBack().setDirection(DcMotor.Direction.FORWARD);
        mecaDrive.getDrive().getRightFront().setDirection(DcMotor.Direction.REVERSE);
        mecaDrive.getDrive().getRightBack().setDirection(DcMotor.Direction.REVERSE);
        mecaDrive.setLocalizer((new MecanumDrive.MecanumLocalizer(mecaDrive,true)));
        //x = 0;
        //y = 0;
    }

    @Override
    public void loop() {
        /*mecaDrive.setDrivePower(new Pose2d(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x
        ));*/


        double FrontLeftVal =  gamepad1.left_stick_y - (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
        double FrontRightVal =  gamepad1.left_stick_y  + (gamepad1.left_stick_x) - -gamepad1.right_stick_x;
        double BackLeftVal = gamepad1.left_stick_y  + (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
        double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x;

        //Move range to between 0 and +1, if not already
        double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
        Arrays.sort(wheelPowers);

        if (wheelPowers[3] > 1) {
            FrontLeftVal /= wheelPowers[3];
            FrontRightVal /= wheelPowers[3];
            BackLeftVal /= wheelPowers[3];
            BackRightVal /= wheelPowers[3];
        }

        mecaDrive.setMotorPowers(FrontLeftVal,BackLeftVal,BackRightVal,FrontRightVal);

        mecaDrive.getLocalizer().update();
        Pose2d poseEstimate = mecaDrive.getLocalizer().getPoseEstimate();

        telemetry.addData("x",poseEstimate.getX());
        telemetry.addData("y",poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        for (DcMotor motor : mecaDrive.getMotors()){
            telemetry.addData("Motor Position (in)", motor.getCurrentPosition()/mecaDrive.getTicks()*4*Math.PI);
        }
        telemetry.update();
    }
}
