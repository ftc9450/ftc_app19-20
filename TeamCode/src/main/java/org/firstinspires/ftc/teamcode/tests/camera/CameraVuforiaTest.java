package org.firstinspires.ftc.teamcode.tests.camera;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.sensors.CameraVuforia;

@TeleOp(name = "CameraVuforia Test", group = "Camera Test")
public class CameraVuforiaTest extends OpMode {
    CameraVuforia cv;

    @Override
    public void init() {
        cv = new CameraVuforia(hardwareMap);
    }

    @Override
    public void loop() {
        cv.loop(telemetry);
    }
}
