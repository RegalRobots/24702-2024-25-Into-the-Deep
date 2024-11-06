package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


//close value right = .154, open = .298, close left = .684, open  = .538
@TeleOp(name = "Demon TeleOP")
public class TeleOP extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        int position = 0;
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();

        waitForStart();
        boolean clawIsOpen = false;
        boolean pressingB = false;
        boolean pressingLT = false;
        boolean tooFar = false;
        boolean isStalling = false;
        double ticks = 0;
        while (opModeIsActive()){
            //gamepad1 = Driver 1
            double movement = -gamepad1.left_stick_y;
            double strafing = gamepad1.left_stick_x;
            double turning = gamepad1.right_stick_x;

            double rf = movement - strafing - turning;
            double rb = movement + strafing - turning;
            double lf = movement + strafing + turning;
            double lb = movement - strafing + turning;
            ticks = -(robot.armExtension.getCurrentPosition());
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
            //only runs if the game button is he  ld down
            //gamepad 2 = driver 2
            if (gamepad2.a){

            }
            if (gamepad2.dpad_left){

            }
            if (gamepad2.a){
                //robot.rf.setPower(1);
            }

            if(gamepad2.left_stick_y < -0.1 && (!tooFar)) {
                telemetry.addData("Status", "This is going, should be going forward");
                robot.armExtension.setPower(-0.5);
            } else if(gamepad2.left_stick_y > 0.1){
                telemetry.addData("Status", "This is going, should be going backwards");
                robot.armExtension.setPower(0.5);
            } else {

                robot.armExtension.setPower(0);
//                if (ticks < 2850) {
//                    robot.armExtension.setTargetPosition(2700);
//                } else if (ticks > 100) {
//                    robot.armExtension.setTarge'tPosition(500);
//                } else {
//                    robot.armExtension.setPower(0);
//                }
            }


            if(ticks < 2850){
                tooFar = false;
            } else{
                tooFar = true;
            }


           if(gamepad2.right_stick_y > 0.1) {
                robot.armVertical.setPower(-1);
            }
            else if(gamepad2.right_stick_y < -0.1){
                robot.armVertical.setPower(1);
            } else{
                robot.armVertical.setPower(0);
            }
            /*if(gamepad2.b && !pressingB){
                telemetry.addData("Extention Position", robot.armExtension.getCurrentPosition());
                telemetry.addData("Horizontal Position", robot.armVertical.getCurrentPosition());
                telemetry.update();
                pressingB = true;
            } else if (!gamepad2.b){
                pressingB = false;
            }*/
            //only for using trigger as a button

            if ((gamepad2.left_trigger > 0.1)&& !pressingLT){
                if(!clawIsOpen){
                    //Open claw
                    robot.leftServo.setPosition(0.559);
                    robot.rightServo.setPosition(0.24);//may be wrong position
                    clawIsOpen = true;
                } else {
                    //Close claw
                    robot.leftServo.setPosition(.67);
                    robot.rightServo.setPosition(.1);
                    clawIsOpen = false;
                }
                pressingLT = true;
            }
            else if(!(gamepad2.left_trigger >0.1)){
                pressingLT = false;
            }
//            robot.armExtension.setPower(1);
//            robot.armExtension.setTargetPosition();
//            robot.armExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            /*
            if(robot.armExtension.getCurrent(CurrentUnit.AMPS) > 5){
                isStalling = true;
                position += 1;
            } else{
                isStalling = false;
            }
//            if(robot.armExtension.getCurrent(CurrentUnit.AMPS ) < 0.5){
//                isStalling = false;
//            }
            if(isStalling && (position < 11)){
                robot.armExtension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            } else{
                if (robot.armExtension.getCurrent(CurrentUnit.AMPS) < 0.3){
                    robot.armExtension.setPower(1);
                }
            }

            */
            telemetry.addData("Position", ticks);
            telemetry.addData("Arm Vertical", robot.armVertical.getCurrentPosition());
            telemetry.addData("Arm Horizontal Position", ticks);

            telemetry.addData("Extension Voltabge", robot.armExtension.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Hello", position);
//            telemetry.addData("Arm Vertical Position", robot.armVertical.getCurrentPosition());
            telemetry.update();
        }
    }
}
