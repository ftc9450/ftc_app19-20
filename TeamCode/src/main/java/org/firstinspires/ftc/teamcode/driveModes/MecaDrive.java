package org.firstinspires.ftc.teamcode.driveModes;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Gyroscope;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MecaDrive extends MecanumDrive {
    private Drivetrain drive;
    private List<DcMotor> motors;
    private Gyroscope imu;
    private double ticks;

    //TODO: Add back wheelBase
    public MecaDrive(double kV, double kA, double kStatic, double trackWidth,double wheelBase, HardwareMap map) {
        super(kV, kA, kStatic, trackWidth,wheelBase);
        drive = new Drivetrain(map);
        motors = Arrays.asList(drive.getLeftFront(), drive.getLeftBack(), drive.getRightBack(), drive.getRightFront());
        imu = new Gyroscope(map.get(BNO055IMU.class, "imu"));

        ticks = 537.6;

        for (DcMotor motor : motors){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
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
}
