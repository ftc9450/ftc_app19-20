package org.firstinspires.ftc.teamcode.driveModes;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MecaDrive extends MecanumDrive {
    private Drivetrain drive;
    private List<DcMotor> motors;
    private Gyroscope imu;
    private double ticks;

    //TODO: Add these into Constants
    private static final MotorConfigurationType MOTOR_CONFIG =
            MotorConfigurationType.getMotorType(NeveRest20Gearmotor.class);
    public static double getMaxRpm() {
        return MOTOR_CONFIG.getMaxRPM();
    }
    public static double rpmToVelocity(double rpm) {
        return rpm * 2 * Math.PI * Constants.MecaDrive.WHEEL_DIAMETER / 60.0;
    }
    private static double kV = 1.0/rpmToVelocity(getMaxRpm());

    //TODO: Add back wheelBase
    public MecaDrive(HardwareMap map) {
        super(kV, Constants.MecaDrive.kA, Constants.MecaDrive.kStatic, Constants.MecaDrive.TRACK_WIDTH,Constants.MecaDrive.WHEEL_BASE);
        drive = new Drivetrain(map);
        motors = Arrays.asList(drive.getLeftFront(), drive.getLeftBack(), drive.getRightBack(), drive.getRightFront());
        imu = new Gyroscope(map.get(BNO055IMU.class, "imu"));

        ticks = 537.6;

        for (DcMotor motor : motors){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        this.getDrive().getLeftFront().setDirection(DcMotor.Direction.FORWARD);
        this.getDrive().getLeftBack().setDirection(DcMotor.Direction.FORWARD);
        this.getDrive().getRightFront().setDirection(DcMotor.Direction.REVERSE);
        this.getDrive().getRightBack().setDirection(DcMotor.Direction.REVERSE);
        this.setLocalizer((new MecanumDrive.MecanumLocalizer(this,true)));
    }

    @Override
    public List<Double> getWheelPositions() {
        List<Double> wheelPositions = new ArrayList<>();
        for (DcMotor motor : motors) {
            //TODO: Turn ticks to inches
            wheelPositions.add(motor.getCurrentPosition()/ticks*4*Math.PI);
        }
        return wheelPositions;
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        drive.getLeftFront().setPower(v);
        drive.getLeftBack().setPower(v1);
        drive.getRightBack().setPower(v2);
        drive.getRightFront().setPower(v3);

    }

    @Override
    protected double getRawExternalHeading() {
        //TODO: Figure out if this is correct
        return imu.getAngle();
    }

    public Drivetrain getDrive() {
        return drive;
    }

    public Gyroscope getImu() {
        return imu;
    }

    public List<DcMotor> getMotors() {
        return motors;
    }

    public double getTicks() {
        return ticks;
    }

    public void vertical(double y){
        this.setDrivePower(new Pose2d(0,y,0));
    }
    public void horizontal(double x){
        this.setDrivePower(new Pose2d(x,0,0));
    }
    public void turn (double a){
        this.setDrivePower(new Pose2d(0,0,a));
    }

    public void fullMovement(double x, double y){
        this.setDrivePower(new Pose2d(x,y,0));
    }
}
