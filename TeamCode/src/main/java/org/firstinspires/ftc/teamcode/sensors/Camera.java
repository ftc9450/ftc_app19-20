package org.firstinspires.ftc.teamcode.sensors;

import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;
import com.disnodeteam.dogecv.detectors.skystone.StoneDetector;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvInternalCamera;

public class Camera {

    private OpenCvCamera camera;
    private SkystoneDetector skystoneDetector;
    private StoneDetector stoneDetector;
    private Pipeline pipeline;
    private Rect rect;
    public enum Pipeline{
        SKYSTONE,STONE;
    }

    public Camera(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        camera.openCameraDevice();
        skystoneDetector = new SkystoneDetector();
        stoneDetector = new StoneDetector();
        camera.setPipeline(skystoneDetector);
        pipeline = Pipeline.SKYSTONE;

    }

    public void changePipeline(){
        if(pipeline == Pipeline.SKYSTONE){
            camera.setPipeline(stoneDetector);
            pipeline = Pipeline.STONE;
        }else{
            camera.setPipeline(skystoneDetector);
        }
    }

    public boolean isDetected(){
        if(pipeline == Pipeline.SKYSTONE){
            return skystoneDetector.isDetected();

        }else{
            return stoneDetector.isDetected();
        }
    }
}
