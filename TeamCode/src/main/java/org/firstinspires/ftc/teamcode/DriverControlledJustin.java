package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name= "Demo TeleOp")
public class DriverControlledJustin extends LinearOpMode {


    HardwareJustin robot = HardwareJustin.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status","Hello, Drivers!");
        telemetry.update();

        waitForStart();

        boolean pressingb = false;
        boolean pressinglt = false;
        //this boolean helps tell the difference between the two commands
        boolean difference = false;
        while(opModeIsActive()){

            //gamepad1 = Driver 1
            double movement = -gamepad1.left_stick_x;
            double strafing = -gamepad1.right_stick_y;
            double turning = -gamepad1.left_stick_y;

            double demoWheel1 = movement - strafing - turning;
            double demoWheel2 = movement + strafing - turning;
            double demoWheel3 = movement + strafing + turning;
            double demoWheel4 = movement - strafing + turning;

            double max = Math.max(Math.abs(demoWheel1), Math.max(Math.abs(demoWheel2),
                    Math.max(Math.abs(demoWheel3), Math.abs(demoWheel4))));
            if (max < robot.maxSpeed) {
                robot.setPower(demoWheel1,demoWheel2, demoWheel3, demoWheel4);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower(demoWheel1 * scaleFactor,
                        demoWheel2 * scaleFactor,
                        demoWheel3 * scaleFactor, demoWheel4 * scaleFactor);

            }

            if(gamepad2.a){
                robot.demoWheel1.setPower(1);
            }

            if(gamepad2.a) {

            }
            if(gamepad2.b && !pressingb){
                //action

                pressingb=true;


            } else if (gamepad2.b) {
                pressingb = false;

            }
            //only for using the trigger as a button
            if((gamepad2.left_trigger>0.1)&& !pressinglt){
                pressinglt=true;
                difference = false;
            } else if ((gamepad2.left_trigger>0.1)&& !pressinglt && !difference) {
                //open claw
                pressinglt = true;
                difference = true;
            }else if ((gamepad2.left_trigger>0.1)){
                pressinglt = false;
            }




        }

    }


}
