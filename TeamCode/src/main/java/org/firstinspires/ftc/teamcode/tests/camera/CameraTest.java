package org.firstinspires.ftc.teamcode.tests.camera;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.sensors.Camera;

@TeleOp(name = "Camera Test", group = "Camera Test")
public class CameraTest extends OpMode {
    private Camera camera;
    private Camera.Pipeline lastPipe;

    @Override
    public void init() {
        camera = new Camera(hardwareMap);
        lastPipe = camera.getPipeline();
    }

    @Override
    public void loop() {
        if(camera.getPipeline() == Camera.Pipeline.SKYSTONE){
            if(camera.isDetected()){
                //telemetry.addData("Skypoint: ", camera.getSkyPoint().x);
                //telemetry.addData("Center: ", camera.getRectCenterWidth());
            }
        }else{
            if(camera.isDetected()){
                //telemetry.addData("StonePoint: ", camera.getStonePoints().get(0).x);
                //telemetry.addData("Center: ", camera.getRectCenterWidth());
            }
        }

        if(gamepad1.a){
            if(lastPipe == camera.getPipeline()){
                camera.changePipeline();
            }
        }else{
            lastPipe = camera.getPipeline();
        }
        telemetry.addData("Pipline", camera.getPipeline());
        telemetry.addData("Detected", camera.isDetected());
        telemetry.update();
    }
}
