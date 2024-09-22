package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;




@Autonomous (name = "DemoAuto")
public class AutoTeam6 extends LinearOpMode {
        HardwareTeam6 robot = HardwareTeam6.getInstance();
    private ElapsedTime runtime = new ElapsedTime();

        public void runOpMode() {
            robot.init(hardwareMap);

                    waitForStart();
                    //move 10 inches
                    move(10,1);


                }
//creates method to move forward and backward
 public void move(double distance, double speed) {
        //converting the ticks to inches
            double wheelCircumfrence = 4 * Math.PI;
            double wheelRPM = 560;
            double ticks = (distance * (wheelRPM/wheelCircumfrence));
        //set power to 0
             robot.setPower(0,0,0,0);
             //set target position
             robot.demoWheel1.setTargetPosition((int) Math.round(ticks));
             robot.demoWheel2.setTargetPosition((int) Math.round(ticks));
             robot.demoWheel3.setTargetPosition((int) Math.round(ticks));
             robot.demoWheel4.setTargetPosition((int) Math.round(ticks));
                //resets encoder
             robot.demoWheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             robot.demoWheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             robot.demoWheel3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
             robot.demoWheel4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //sets run to position
             robot.demoWheel1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             robot.demoWheel2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             robot.demoWheel3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             robot.demoWheel4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    //setting power
             robot.setPower(speed, speed, speed, speed);
            //keep the robot going to target position until wheel gets there
             while (opModeIsActive() && (robot.demoWheel1.isBusy())) {

             }
             //stops robot
             robot.setPower(0,0,0,0);

     robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

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

     robot.setPower(speed, speed, speed, speed);

     while (opModeIsActive() && (robot.demoWheel1.isBusy())) {

     }
     robot.setPower(0,0,0,0);

     robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
 }
public void timeMove(double time, double speed) {
        runtime.reset();
    robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    robot.setPower(-speed, speed, -speed, speed);

    while (runtime.seconds() < time ) {

    }
    robot.setPower(0,0,0,0);
    robot.demoWheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.demoWheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.demoWheel3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    robot.demoWheel4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



            }


 }

