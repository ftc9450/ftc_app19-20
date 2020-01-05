package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.sensors.Bumpers;

/**
 * Created by dhruv on 1/22/18.
 */

public class Constants {
    private static final String vuforiakey = "AYwm7lb/////AAAAGeQI9HT4B0R2unLNBq/DsId49BJr71nKGdfP8X8fnmtD0Jna47KLigPBytLYBjzOIl6uCfYWbIXHc3FqoabxIITohKJ4VvPispe5kGGFFJyQEIifEL1Bc511jOl00pyY2Tr/YOGwjGk7lSXQ0QrScHVaiwIOM3mUUlsv9Ethn1OCZB2AVhT91gnrUKryxBwfLCGjqpmYdWOVDsJTloDiowWMez0U42S9sILVevNguQXZqTr1uURaUx5Voy+2N6FVK5p4dvraac9+LD6YskUCLqWsK2XVruCpCsRWZxfrqylNyni2ll5AW3Mekw/hSSzfjx70eyKXyaLRiOj4UhHKCjeqWjFCePt0Vb59tyqd9KhS\n";
    public static class Drivetrain {
        public static final double HIGH_POWER = 1;
        public static final double LOW_POWER = 0.25;
        public static final int STRAFEINCH = 135;
        public static final int INCH = 40;//1120 cpr for neverest 40
        public static final int DEGREE = 18;
        public static final int FB_THRESHOLD = 500; // TODO: check value
        public static final int LR_THRESHOLD = 500; // TODO: check value
        public static final int FB_PID_THRESHOLD = 500; // TODO: check value
        public static final int LR_PID_THRESHOLD = 500; // TODO: check value
        public static final double FB_LEFT_POWER = 1.0; // TODO: check value
        public static final double FB_RIGHT_POWER = 1.0; // TODO: check value
        public static final double LR_FRONT_POWER = 1.0; // TODO: check value
        public static final double LR_REAR_POWER = 0.9; // TODO: check value
        public static final double PIVOT_POWER = 0.3;
        public static final String LF = "frontleft";
        public static final String LB = "backleft";
        public static final String RF = "frontright";
        public static final String RB = "backright";
        public static final String[] motors = {LF, LB, RF, RB};
    }

    public class MecaDrive{
        public static final double kA = 0;
        public static final double kStatic = 0;
        public static final double TRACK_WIDTH = 16;
        public static final double WHEEL_BASE = 10.5;

        public static final double TRACK_WIDTH2 = 14;
        public static final double WHEEL_BASE2 = 14;


        public static final double WHEEL_DIAMETER = 4;
        public static final double SPEED_MULTIPLIER = .5;
    }

    public class FourBar{
        public static final String FBM = "fourBarMotor";
        public static final String CS = "clawServo";
    }
    public class Arm{
        public static final String MOTOR = "";
        public static final String SERVO_GRAB = "";
        public static final String SERVO_BACK = "";
    }

    public class Bumpers{
        public static final String TSB = "touchBack";
        public static final String TSL = "touchLeft";
        public static final String TSR = "touchRight";
    }

    public class Intake{
        public static final String LM = "leftMotorIntake";
        public static final String RM = "rightMotorIntake";
    }

    public class Hook{
        public static final String SERVO = "hookServo";
    }

    public class MotionTracker{
        public static final String FB = Drivetrain.RB;
        public static final String LR = "frontleft";
        public static final double CLICKS_PER_INCH = 114.59/5; // cpr of 1440
    }
    public class Auto{
        public static final double MAX_PIVOT_POWER = 0.3;
        public static final double MIN_PIVOT_POWER = 0.1;
        public static final double PIVOT_POWER=0.2;
        public static final double PIVOT_THRESHOLD = 0.1;
        public static final String MA = "marker";
    }
    public class EncoderValue{
        private static final int nevrest40 = 28;
        private static final int neverst60 =420;
        private static final double neverst20 = 134.4;
        public static final int torqueNado = 1440;
    }
    public class Vuforia {
        public static final String KEY = "AYwm7lb/////AAAAGeQI9HT4B0R2unLNBq/DsId49BJr71nKGdfP8X8fnmtD0Jna47KLigPBytLYBjzOIl6uCfYWbIXHc3FqoabxIITohKJ4VvPispe5kGGFFJyQEIifEL1Bc511jOl00pyY2Tr/YOGwjGk7lSXQ0QrScHVaiwIOM3mUUlsv9Ethn1OCZB2AVhT91gnrUKryxBwfLCGjqpmYdWOVDsJTloDiowWMez0U42S9sILVevNguQXZqTr1uURaUx5Voy+2N6FVK5p4dvraac9+LD6YskUCLqWsK2XVruCpCsRWZxfrqylNyni2ll5AW3Mekw/hSSzfjx70eyKXyaLRiOj4UhHKCjeqWjFCePt0Vb59tyqd9KhS";
    }
    public static double floatToDouble(float f) {
        Float d=new Float(f);
        return d.doubleValue();
    }

    public static float doubleToFloat(double d){
        Double f=new Double(d);
        return f.floatValue();
    }
}
