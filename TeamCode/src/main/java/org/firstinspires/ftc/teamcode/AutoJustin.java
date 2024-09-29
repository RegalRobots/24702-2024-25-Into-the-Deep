package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "DemoAuto")
public class AutoJustin extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    HardwareJustin robot = HardwareJustin.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();


        move(10, 1);
        move(-10,1);
        move(15, 1);


    }

    //create method to forward/backward
    public void move(double distance, double speed) {

        //converting to ticks to inches
        double wheelCircumference = 4 * Math.PI;
        double wheelRPM = 560;
        double ticks = (distance * (wheelRPM/wheelCircumference));

        //set power to 0
        robot.setPower(0,0,0,0);

        //Set Target Position
        robot.demoWheel1.setTargetPosition((int) Math.round(ticks));
        robot.demoWheel2.setTargetPosition((int) Math.round(ticks));
        robot.demoWheel3.setTargetPosition((int) Math.round(ticks));
        robot.demoWheel4.setTargetPosition((int) Math.round(ticks));

        //Reset encoder - get rid of old value
        robot.demoWheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Run To Position
        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Setting power
        robot.setPower(speed, speed, speed, speed);

        //Keep the robot going to target position until wheel gets there
        while (opModeIsActive() && (robot.demoWheel1.isBusy())) {

        }
        //Set power to 0 - stops robot
        robot.setPower(0,0,0,0);

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    //Method to turn
    public void turn(int ticks, double speed) {

        robot.demoWheel1.setTargetPosition(ticks);
        robot.demoWheel2.setTargetPosition(ticks);
        robot.demoWheel3.setTargetPosition(ticks);
        robot.demoWheel4.setTargetPosition(ticks);

        robot.demoWheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(-speed, -speed, speed, speed);
        //actual turning
        while (opModeIsActive() && (robot.demoWheel1.isBusy())) {

        }
        robot.setPower(0,0,0,0);

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void timeMove(double speed, double time) {
        runtime.reset();

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.setPower(-speed,speed,-speed,speed);

        while (runtime.seconds() < time) {

        }
        robot.setPower(0,0,0,0);

        robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}
