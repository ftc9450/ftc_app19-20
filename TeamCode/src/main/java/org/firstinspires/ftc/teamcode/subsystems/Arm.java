package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Arm extends Subsystem {
    private DcMotor motor;
    private Servo servoGrab;
    private Servo servoBack;
    public Arm(HardwareMap map) {
        motor = map.dcMotor.get(Constants.Arm.MOTOR);
        servoBack = map.servo.get(Constants.Arm.SERVO_BACK);
        servoGrab = map.servo.get(Constants.Arm.SERVO_GRAB);
    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        
    }
}
