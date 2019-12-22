package org.firstinspires.ftc.teamcode.tests.camera;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.sensors.Camera;

@TeleOp(name = "Camera Test", group = "Camera Test")
public class CameraTest extends OpMode {
    private Camera camera;

    @Override
    public void init() {
        camera = new Camera(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Skypoint:", camera.getSkyPoint().x);
        telemetry.addData("Center", camera.getRectCenterWidth());
        telemetry.update();
    }
}
