package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Demo TeleOP")
public class TeleOP extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();
        waitForStart();
        boolean difference = false;
        boolean pressingB = false;
        boolean pressingLT = false;
        while (opModeIsActive()){
            //gamepad1 = Driver 1
            double movement = -gamepad1.left_stick_x;
            double strafing = gamepad1.right_stick_y;
            double turning = -gamepad1.left_stick_y;

            double rf = movement - strafing - turning;
            double rb = movement + strafing - turning;
            double lf = movement + strafing - turning;
            double lb = movement - strafing + turning;
            double max = Math.max(Math.abs(rf), Math.max(Math.abs(rb), Math.max(Math.abs(lf), Math.abs(lb))));
            if (max < robot.maxSpeed) {
                robot.setPower(rf, rb, lf, lb);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower((rf) * scaleFactor,
                        (rb) * scaleFactor,
                        (lf) * scaleFactor,
                        (lb) * scaleFactor);
            }
            //only runs if the game button is held down
            //gamepad 2 = driver 2
            if (gamepad2.a){

            }
            if (gamepad2.dpad_left){

            }
            if (gamepad2.a){
                //robot.rf.setPower(1);
            }
            if(gamepad2.b && !pressingB){
                //action
                pressingB = true;
            } else if (!gamepad2.b){
                pressingB = false;
            }
            //only for using trigger as a button

            if ((gamepad2.left_trigger > 0.1)&& !pressingLT){
                if(!difference){
                    //Open claw
                    robot.demoServo.setPosition(0.9); //may be wrong position
                    difference = true;
                } else {
                    //Close claw
                    robot.demoServo.setPosition(0.2);
                    difference = false;
                }
                pressingLT = true;
            }
            else if(!(gamepad2.left_trigger >0.1)){
                pressingLT = false;
            }
        }
    }
}
