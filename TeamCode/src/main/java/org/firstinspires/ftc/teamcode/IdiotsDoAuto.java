package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name = "Idiot Auto")
public class IdiotsDoAuto extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    @Override
    public void runOpMode() throws InterruptedException {
       robot.init (hardwareMap);
       waitForStart();
       moveArm(300);
       extendArm(300);

    }
    public void moveArm(int ticks) {
        robot.armVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.armVertical.setTargetPosition(ticks);
        robot.armVertical.setPower(0.5);

        robot.armVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && robot.armVertical.isBusy()) {

        }
        robot.armVertical.setPower(0);
        robot.armVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
        public void extendArm(int ticks){
            robot.armExtension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.armExtension.setTargetPosition(ticks);
            robot.armExtension.setPower(0.5);

            robot.armVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (opModeIsActive()&&robot.armExtension.isBusy()){

            }
            robot.armExtension.setPower(0);
            robot.armExtension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }


