package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Bumpers {
    private TouchSensor tsb;
    private TouchSensor tsl;
    private TouchSensor tsr;

    public Bumpers(HardwareMap map){
        tsb = map.touchSensor.get(Constants.Bumpers.TSB);
        tsl = map.touchSensor.get(Constants.Bumpers.TSL);
        tsr = map.touchSensor.get(Constants.Bumpers.TSR);
    }

    public boolean touchLeft(){ return tsl.isPressed(); }
    public boolean touchRight(){ return tsr.isPressed(); }
    public boolean touchBack(){ return tsb.isPressed(); }

}
