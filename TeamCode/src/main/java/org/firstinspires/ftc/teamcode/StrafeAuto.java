package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Autonomous (name = "Strafing Auto")
public class StrafeAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();

    double armSpeed = 0.5;
    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "What's Up Brothers!");
        telemetry.update();
        waitForStart();
//
//        //START AUTONOMOUS PROGRAM
//        //move 18 inches
        armExtend(1200, armSpeed);
        armVertical(2350, armSpeed);
        move(26,  0.4);
        armVertical(-200, armSpeed);
        armExtend(-900, armSpeed);
        openClaw();

        move(-4, 0.5);

        strafe(48, 0.5);
        armExtend(1000, armSpeed);
        armVertical(-2800, armSpeed);
        closeClaw();
        sleep(500);
        armVertical(3700, armSpeed);
//        armExtend(1470, armSpeed);
        turn(-2000, 0.4);
        move(12, 0.4);
        turn(650, 0.4);
        move(4, 0.4);
//        armExtend(500, armSpeed);
        armExtend(1550, armSpeed);
        move(5.5, 0.4);
        openClaw();

        move(-3, 0.4);
        armExtend(-1400, armSpeed);
        turn(-2300, 1);
        move(40, 1);
        armVertical(-700, armSpeed);



        //turns
    } //arm horizontal 1461, arm vertical 2077
    /*
    create some methods to make the arm move upward a certain amt of ticks, down a certain amount
    of ticks, and extend the arm out and in a certain amount of ticks.
     */

    public void armVertical(int ticks, double speed) {
        ticks = (ticks);
        //move arm down until it hits touch sensor, then stop and reset encoder:
        robot.armVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.armVertical.setTargetPosition(ticks);
        robot.armVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.armVertical.setPower(0.5);
        while (opModeIsActive() && (robot.armVertical.isBusy())) {

        }


        robot.armVertical.setPower(0);
        robot.armVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //opens the claw to the open positions
    public void openClaw(){
        robot.leftServo.setPosition(0.3);
        robot.rightServo.setPosition(0.55);

    }
    //closes the claw to the close positions
    public void closeClaw(){
        robot.leftServo.setPosition(.67);
        robot.rightServo.setPosition(.1);
    }
    public void armExtend(int ticks, double power) {
        ticks = -(ticks);
        robot.armExtension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.armExtension.setTargetPosition(ticks);
        robot.armExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.armExtension.setPower(power);
        while (opModeIsActive() && (robot.armExtension.isBusy())) {
            if (robot.armExtension.getCurrent(CurrentUnit.AMPS) > 4){
                stop();
            }
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
        robot.rf.setTargetPosition(-ticks);
        robot.rb.setTargetPosition(-ticks);
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

    public void strafe(double distance, double speed){

        //converts ticks to inches
        double wheelCircumference = 4*Math.PI;
        double wheelRPM = 560;
        double ticks = (distance * (wheelRPM/wheelCircumference));

        //set power to 0
        robot.setPower(0, 0, 0, 0);

        //set target position
        robot.rf.setTargetPosition((int)Math.round(ticks));
        robot.rb.setTargetPosition((int)Math.round(-ticks));
        robot.lf.setTargetPosition((int)Math.round(-ticks));
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
        robot.setPower(speed, -(speed),-speed, speed);


        //keep robot going to target position
        while (opModeIsActive() && (robot.rf.isBusy())){

            telemetry.addData("RF Power", robot.rf.getPower());
            telemetry.addData("RF Position", robot.rf.getCurrentPosition());
            telemetry.addData("RB Power", robot.rb.getPower());
            telemetry.addData("RB Position", robot.rb.getCurrentPosition());
            telemetry.addData("LF Power", robot.lf.getPower());
            telemetry.addData("LF Position", robot.lf.getCurrentPosition());
            telemetry.addData("LB Power", robot.lb.getPower());
            telemetry.addData("LB Position", robot.lb.getCurrentPosition());
            telemetry.update();



        }
        telemetry.addData("RF Power", robot.rf.getPower());
        telemetry.addData("RF Position", robot.rf.getCurrentPosition());
        telemetry.addData("RB Power", robot.rb.getPower());
        telemetry.addData("RB Position", robot.rb.getCurrentPosition());
        telemetry.addData("LF Power", robot.lf.getPower());
        telemetry.addData("LF Position", robot.lf.getCurrentPosition());
        telemetry.addData("LB Power", robot.lb.getPower());
        telemetry.addData("LB Position", robot.lb.getCurrentPosition());
        telemetry.update();

        //set power to 0
        robot.setPower(0, 0,0, 0);
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
