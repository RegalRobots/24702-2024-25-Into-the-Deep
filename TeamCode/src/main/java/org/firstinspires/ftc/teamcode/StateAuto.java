package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Autonomous(name = "States auto")
public class StateAuto extends OpMode {
    Hardware robot = Hardware.getInstance();
    double armSpeed = 0.5;
    enum State{
        RAISE_ARMS,
        GO_TO_BAR,
        HANG_SPECIMEN,
        GO_TO_SAMPLE,
        GRAB_SPECIMEN,
        UP_AND_ROTATE,
        GO_TO_BASKET,
        DROP_IN_BASKET,
        DONE
    }
    State state = State.RAISE_ARMS;
    public void init() {
        robot.init(hardwareMap);
        state = State.RAISE_ARMS;
    }
    public void loop() {
        switch (state) {
            case RAISE_ARMS:
                armExtend(1200, armSpeed);
                armVertical(2350, armSpeed);
                if (robot.armVertical.getCurrentPosition() < 2350 && (robot.armExtension.getCurrentPosition() > 1200 ) ){
                    robot.armExtension.setPower(0);
                    robot.armVertical.setPower(0);
                    state = State.GO_TO_BAR;
                }
                break;
            case GO_TO_BAR:
                move(26, 0.4);
                if (!robot.rf.isBusy()){
                    robot.setPower(0, 0, 0, 0);
                    state = State.HANG_SPECIMEN;
                }
                break;

            case HANG_SPECIMEN:
                armVertical(-200, armSpeed);
                while (robot.armVertical.isBusy()) {

                }
                robot.armVertical.setPower(0);
                armExtend(-850, armSpeed);
                if (!robot.armExtension.isBusy()) {
                    robot.armExtension.setPower(0);
                    state = State.GO_TO_SAMPLE;
                }
                break;

            case GO_TO_SAMPLE:
                openClaw();
                move(-12, 0.5);
                while (robot.rf.isBusy()) {

                }
                robot.setPower(0, 0, 0, 0);
                turn(-1000, 0.3);
                while (robot.rf.isBusy()) {

                }
                robot.setPower(0, 0, 0, 0);
                move(39, 0.4);
                while (robot.rf.isBusy()) {

                }
                robot.setPower(0, 0, 0, 0);
                turn(1000, 0.4);
                while (robot.rf.isBusy()) {

                }
                robot.setPower(0, 0, 0, 0);
                move(8, 0.4);
                while (robot.rf.isBusy()) {

                }
                robot.setPower(0, 0, 0, 0);
                state = State.GRAB_SPECIMEN;
                break;

            case GRAB_SPECIMEN:
                armExtend(750, armSpeed);
                armVertical(-2800, armSpeed);
                if (!robot.armExtension.isBusy()) {
                    robot.armExtension.setPower(0);
                }
                if (!robot.armVertical.isBusy()) {
                    robot.armVertical.setPower(0);
                }
                closeClaw();
                break;

            case UP_AND_ROTATE:
                armVertical(3700, armSpeed);
                turn(-2000, 0.4);
                if (!robot.rf.isBusy()){
                    robot.setPower(0,0 , 0,0);
                }
                if(!robot.armVertical.isBusy()){
                    robot.armVertical.setPower(0);
                }
                if(!robot.armVertical.isBusy() && !robot.rf.isBusy()){
                    state = State.GO_TO_BASKET;
                }
                break;

            case GO_TO_BASKET:
                move(12, 0.4);
                while (robot.rf.isBusy()) {

                }
                robot.setPower(0, 0, 0, 0);
                turn(500, 0.4);
                while (robot.rf.isBusy()) {

                }
                robot.setPower(0, 0, 0, 0);
                move(4, 0.4);
                break;

            case DROP_IN_BASKET:
                armExtend(1550, 0.4);
                move(5.5, 0.4);
                if(!robot.rf.isBusy() && !robot.armVertical.isBusy()){
                    robot.armExtension.setPower(0);
                    robot.setPower(0, 0, 0, 0);
                    openClaw();
                    state = State.DONE;
                }
                break;
            case DONE:
                break;
            default:
                telemetry.addData("Auto", "Finished");
        }
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
    public void armVertical(int ticks, double speed) {
        ticks = (ticks);
        //move arm down until it hits touch sensor, then stop and reset encoder:
        robot.armVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.armVertical.setTargetPosition(ticks);
        robot.armVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.armVertical.setPower(0.5);

        robot.armVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void armExtend(int ticks, double power) {
        ticks = -(ticks);
        robot.armExtension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.armExtension.setTargetPosition(ticks);
        robot.armExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.armExtension.setPower(power);


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

        robot.rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void timeMovement(double speed, double time){
//        runtime.reset();
        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.setPower(-speed, speed, -speed, speed);

//        while (runtime.seconds() < time){
//        }

        robot.setPower(0, 0,0, 0);
    }



}

