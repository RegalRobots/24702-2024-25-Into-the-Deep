package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Auto Code")
public class Auto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();


    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();
        waitForStart();
        //move 18 inches
        move(18, 1);
        //turns
        turn(1000, 1);
        //
    }

    //create method to move robot forwards or backwards a certain distance and speed
    public void move(double distance, double speed){

        //converts ticks to inches
        double wheelCircumference = 4*Math.PI;
        double wheelRPM = 560;
        double ticks = (distance * (wheelRPM/wheelCircumference));

        //set power to 0
        robot.setPower(0, 0, 0, 0);

        //set target position
        robot.rf.setTargetPosition((int)Math.round(ticks));
        robot.rb.setTargetPosition((int)Math.round(ticks));
        robot.lf.setTargetPosition((int)Math.round(ticks));
        robot.lb.setTargetPosition((int)Math.round(ticks));

        //reset encoder to get rid of old value
        robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set run to position
        robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //setting power
        robot.setPower(speed, speed,speed, speed);
        //keep robot going to target position
        while (opModeIsActive() && (robot.rf.isBusy())){

        }
        //set power to 0
        robot.setPower(0, 0,0, 0);
    }
    //method to turn
     public void turn(int ticks, double speed){
         robot.rf.setTargetPosition(ticks);
         robot.rb.setTargetPosition(ticks);
         robot.lf.setTargetPosition(ticks);
         robot.lb.setTargetPosition(ticks);

         robot.rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         robot.rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         robot.lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         robot.lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         robot.rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         robot.rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         robot.lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         robot.lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

         robot.setPower(-speed, -speed,speed, speed);

         while (opModeIsActive() && (robot.rf.isBusy())){

         }
         robot.setPower(0, 0,0, 0);
         robot.rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     }
     public void timeMovement(double speed, double time){
            runtime.reset();
         robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

         robot.setPower(-speed, speed, -speed, speed);

         while (runtime.seconds() < time){

         }

         robot.setPower(0, 0,0, 0);
     }

}
