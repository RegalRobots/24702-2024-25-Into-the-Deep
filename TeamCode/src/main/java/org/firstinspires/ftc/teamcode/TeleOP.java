package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


//close value right = .154, open = .298, close left = .684, open  = .538
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
            double movement = -gamepad1.left_stick_y;
            double strafing = gamepad1.left_stick_x;
            double turning = -gamepad1.right_stick_x;

            double rf = movement - strafing - turning;
            double rb = movement + strafing - turning;
            double lf = movement + strafing + turning;
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
            if(gamepad2.left_stick_y > 0.1) {
                //robot.armVertical.setPower(0.5);
            } else if(gamepad2.left_stick_y < -0.1){
                //robot.armVertical.setPower(-0.5);
            }

            if(gamepad2.right_stick_y > 0.1) {
                robot.armVertical.setPower(0.2);
            }
            else if(gamepad2.right_stick_y < -0.1){
                robot.armVertical.setPower(-0.5);
            } else{
                robot.armVertical.setPower(0);
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
                    robot.leftServo.setPosition(0.538);
                    robot.rightServo.setPosition(0.298);//may be wrong position
                    difference = true;
                } else {
                    //Close claw
                    robot.leftServo.setPosition(0.684);
                    robot.rightServo.setPosition(0.154);
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
