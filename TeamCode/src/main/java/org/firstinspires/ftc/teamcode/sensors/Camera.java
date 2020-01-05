package org.firstinspires.ftc.teamcode.sensors;

import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;
import com.disnodeteam.dogecv.detectors.skystone.StoneDetector;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.List;

public class Camera {

    private OpenCvCamera camera;
    private SkystoneDetector skystoneDetector;
    private StoneDetector stoneDetector;
    private Pipeline pipeline;

    private Rect skyRect;
    private int rectCenterWidth;
    private Point skyPoint;

    private List<Rect> stoneRects;
    private List<Point> stonePoints;


    public enum Pipeline{
        SKYSTONE,STONE;
    }

    public Camera(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        camera.openCameraDevice();
        skystoneDetector = new SkystoneDetector();
        stoneDetector = new StoneDetector();
        /*camera.setPipeline(skystoneDetector);
        pipeline = Pipeline.SKYSTONE;

        skystoneDetector.useDefaults();
        skystoneDetector.areaScoringMethod = DogeCV.AreaScoringMethod.PERFECT_AREA;
        skystoneDetector.maxAreaScorer.weight = 0.005;
        skystoneDetector.ratioScorer.weight = 5;
        skystoneDetector.ratioScorer.perfectRatio = 1.0;*/

        camera.setPipeline(stoneDetector);
        pipeline = Pipeline.STONE;

        stoneDetector.useDefaults();
        stoneDetector.areaScoringMethod = DogeCV.AreaScoringMethod.PERFECT_AREA;
        stoneDetector.maxAreaScorer.weight = 0.005;

        camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

    }

    public void changePipeline(){
        if(pipeline == Pipeline.SKYSTONE){
            camera.setPipeline(stoneDetector);
            pipeline = Pipeline.STONE;
        }else{
            camera.setPipeline(skystoneDetector);
            pipeline = Pipeline.SKYSTONE;
        }
    }

    public boolean isDetected(){
        if(pipeline == Pipeline.SKYSTONE){
            return skystoneDetector.isDetected();

        }else{
            return stoneDetector.isDetected();

        }
    }

    public void loop(){
        if(pipeline == Pipeline.SKYSTONE){
            if(isDetected()){
                skyRect = skystoneDetector.foundRectangle();
                rectCenterWidth = skyRect.width/2;
                skyPoint = skystoneDetector.getScreenPosition();
            }
        }else{
            if(isDetected() && stoneDetector.foundRectangles()!=null && stoneDetector.foundRectangles()!=null){
                stoneRects = stoneDetector.foundRectangles();
                rectCenterWidth = stoneRects.get(0).width/2;
                stonePoints = stoneDetector.foundScreenPositions();
            }
        }
        
    }

    public Rect getSkyRect() { return skyRect; }
    public Point getSkyPoint(){ return skyPoint; }
    public Pipeline getPipeline(){return pipeline;}

    public List<Rect> getStoneRect(){ return stoneRects; }
    public List<Point> getStonePoints(){ return stonePoints;}

    public int getRectCenterWidth(){ return rectCenterWidth;}
}
