package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;


/*

!pip install roboflow

from roboflow import Roboflow
rf = Roboflow(api_key="k5AkbaVmEfV8ps4YHZj3")
project = rf.workspace("8579-classical-engineers").project("8579-into-the-deep-samples")
version = project.version(7)
dataset = version.download("coco-mmdetection")

 */
public class LimelightWorker {
    Limelight3A limelight = hardwareMap.get(Limelight3A.class, "John Doe"); //
    public void init() {
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(0); //Needs to be changed to select the trained pipeline

        limelight.start(); //starts training(polling) the data at 100 p/s
    }
    //Used in the While Op Mode is active loop
    public void latestResult(){
        LLResult result = limelight.getLatestResult(); //Gets the result of the data polling
        if (result != null){
            if (result.isValid()){
                Pose3D botPose = result.getBotpose();
                telemetry.addData("Target X",result.getTx());
                telemetry.addData("Target Y", result.getTy());
                telemetry.addData("Bot Position", botPose.toString());
            }
        }

    }
}
