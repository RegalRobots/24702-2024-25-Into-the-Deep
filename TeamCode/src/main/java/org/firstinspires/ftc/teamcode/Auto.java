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

        //START AUTONOMOUS PROGRAM
        //move 18 inches
<<<<<<< Updated upstream
        armExtend(2000, 0.5);
        armVertical(1850);
        move(21,  0.5);
        armExtend(-600, 1);
        openClaw();

        //turns
=======
        armExtend(2000);
        move(26,  0.5);
        //turns


        armVertical(250);


>>>>>>> Stashed changes
    }
    /*
    create some methods to make the arm move upward a certain amt of ticks, down a certain amount
    of ticks, and extend the arm out and in a certain amount of ticks.
     */

    public void armVertical(int ticks) {
<<<<<<< Updated upstream
        ticks = -(ticks);
=======
>>>>>>> Stashed changes
        //move arm down until it hits touch sensor, then stop and reset encoder:
        robot.armVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.armVertical.setTargetPosition(ticks);
        robot.armVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.armVertical.setPower(0.5);
<<<<<<< Updated upstream
        while (opModeIsActive() && (robot.armVertical.isBusy())) {
=======
        while (opModeIsActive() && (robot.rf.isBusy())) {
>>>>>>> Stashed changes

        }


        robot.armVertical.setPower(0);
        robot.armVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

<<<<<<< Updated upstream
    //opens the claw to the open positions
    public void openClaw(){
        robot.leftServo.setPosition(0.538);
        robot.rightServo.setPosition(0.298);

    }
    //closes the claw to the close positions
    public void closeClaw(){
        robot.leftServo.setPosition(0.684);
        robot.rightServo.setPosition(0.1);
    }
    public void armExtend(int ticks, double power) {
        ticks = -(ticks);
        robot.armExtension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.armExtension.setTargetPosition(ticks);
        robot.armExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.armExtension.setPower(power);
        while (opModeIsActive() && (robot.armExtension.isBusy())) {
=======
    public void armExtend(int ticks) {
        robot.armExtension.setTargetPosition(ticks);
        robot.armExtension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.armExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.armExtension.setPower(0.5);
        while (opModeIsActive() && (robot.rf.isBusy())) {
>>>>>>> Stashed changes

        }

        robot.armExtension.setPower(0);
        robot.rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }



    //do not change anything after this point!!! (other than creating more methods)


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