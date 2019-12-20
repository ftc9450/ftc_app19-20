package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;
import org.firstinspires.ftc.teamcode.sensors.Camera;
import org.firstinspires.ftc.teamcode.util.Constants;

@Autonomous(name = "AutoBlue", group = "Auto")
public class AutoBlue extends LinearOpMode {

    private MecaDrive mecaDrive;
    private Camera camera;
    private boolean hooked = false;


    //kV functions, TODO: find way to simplify
    private static double kV;
    private static final MotorConfigurationType MOTOR_CONFIG =
            MotorConfigurationType.getMotorType(NeveRest20Gearmotor.class);
    public static double getMaxRpm() {
        return MOTOR_CONFIG.getMaxRPM();
    }
    public static double rpmToVelocity(double rpm) {
        return rpm * 2 * Math.PI * Constants.MecaDrive.WHEEL_DIAMETER / 60.0;
    }

    public void beginning(){
        //start bot on the space closest to the center of the field
        kV = 1.0/rpmToVelocity(getMaxRpm());
        mecaDrive = new MecaDrive(kV,
                Constants.MecaDrive.kA,
                Constants.MecaDrive.kStatic,
                Constants.MecaDrive.TRACK_WIDTH,
                Constants.MecaDrive.WHEEL_BASE,hardwareMap);
        mecaDrive.getDrive().getLeftFront().setDirection(DcMotor.Direction.FORWARD);
        mecaDrive.getDrive().getLeftBack().setDirection(DcMotor.Direction.FORWARD);
        mecaDrive.getDrive().getRightFront().setDirection(DcMotor.Direction.REVERSE);
        mecaDrive.getDrive().getRightBack().setDirection(DcMotor.Direction.REVERSE);
        mecaDrive.setLocalizer((new MecanumDrive.MecanumLocalizer(mecaDrive,true)));

        camera = new Camera(hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        int limit = 3;
        for(int i = 0; i < limit; i++){
            mainLoop();
        }
        endMovement();
    }

    public void mainLoop(){

    }
    public void endMovement(){
        
    }

    public Pose2d vertical(double y){ return new Pose2d(0,y,0); }
    public Pose2d horizontal (double x){ return new Pose2d(x,0,0); }
    public Pose2d turn (double a){return new Pose2d(0,0,a); }
}
