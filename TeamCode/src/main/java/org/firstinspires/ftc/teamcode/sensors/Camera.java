package org.firstinspires.ftc.teamcode.sensors;

import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;
import com.disnodeteam.dogecv.detectors.skystone.StoneDetector;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.ArrayList;
import java.util.List;

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

    public int isDetected(){
        if(pipeline == Pipeline.SKYSTONE){
            if(skystoneDetector.isDetected()){
                return 1;
            }else{
                return 2;
            }

        }else{
            if(stoneDetector.isDetected()){
                return -1;
            }else{
                return -2;
            }
        }
    }

    public void loop(){
        Rect skyRect = skystoneDetector.foundRectangle();
        List<Rect> stoneRect = stoneDetector.foundRectangles();
        
    }

}
