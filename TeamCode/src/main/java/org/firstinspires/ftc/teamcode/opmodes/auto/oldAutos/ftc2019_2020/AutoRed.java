package org.firstinspires.ftc.teamcode.opmodes.auto.oldAutos.ftc2019_2020;

import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.driveModes.MecaDrive;
import org.firstinspires.ftc.teamcode.sensors.Bumpers;
import org.firstinspires.ftc.teamcode.sensors.Camera;
import org.firstinspires.ftc.teamcode.subsystems.FourBar;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.old.Intake;
import org.firstinspires.ftc.teamcode.util.Constants;

@Autonomous(name = "AutoRed", group = "Auto")
public class AutoRed extends LinearOpMode {

    private MecaDrive mecaDrive;
    private Camera camera;
    private boolean hooked = false;
    private boolean stone = false;
    private Hook hook;
    private FourBar fourbar;
    private Intake intake;
    private Bumpers bumpers;

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
        mecaDrive = new MecaDrive(hardwareMap);
        camera = new Camera(hardwareMap);
        hook = new Hook(hardwareMap);
        fourbar = new FourBar(hardwareMap);
        intake = new Intake(hardwareMap);
        bumpers = new Bumpers(hardwareMap);
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
        if(!stone){
            while(camera.getSkyPoint().x > camera.getRectCenterWidth() || bumpers.touchRight()){
                mecaDrive.horizontal(-10);
            }
            if(bumpers.touchRight()){
                camera.changePipeline();
                while (camera.getStonePoints().get(0).x < camera.getRectCenterWidth()){
                    mecaDrive.horizontal(10);
                }
            }
        }else{
            while (camera.getStonePoints().get(0).x > camera.getRectCenterWidth()|| bumpers.touchRight()){
                mecaDrive.horizontal(-10);
            }
        }

        intake.receive();
        mecaDrive.vertical(-24);
        intake.off();
        mecaDrive.turn(90);
        while(mecaDrive.getPoseEstimate().getX() > 22 ){
            mecaDrive.horizontal(10);
        }
        if(!hooked){
            hooked = true;
            hook.setState(true); hook.loop();
        }
        mecaDrive.vertical(24);
        hook.setState(false); hook.loop();
        fourbar.setClawState(true); fourbar.loop();
        fourbar.setPosition(4); fourbar.loop();
        fourbar.setClawState(false); fourbar.loop();
        fourbar.setPosition(-4); fourbar.loop();
        mecaDrive.turn(-90);
    }

    public void endMovement(){
        while(mecaDrive.getPoseEstimate().getX() < -8 ){
            mecaDrive.horizontal(10);
        }
        stop();
    }

}
