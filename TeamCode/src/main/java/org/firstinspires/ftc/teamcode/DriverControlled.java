package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Demo TeleOp")
public class DriverControlled extends LinearOpMode {

    AmeliaHardware robot = AmeliaHardware.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();

        waitForStart();
        boolean pressingb = false;
        boolean pressinglt = false;
        boolean difference = true;

        while (opModeIsActive()) {

            //gamepad1 = Driver 1
            double movement = -gamepad1.left_stick_x;
            double strafing = gamepad1.right_stick_y;
            double turning = -gamepad1.left_stick_y;

            double demoWheel1 = movement - strafing - turning;
            double demoWheel2 = movement + strafing - turning;
            double demoWheel3 = movement + strafing + turning;
            double demoWheel4 = movement - strafing + turning;

            double max = Math.max(Math.abs(demoWheel1), Math.max(Math.abs(demoWheel2),
                    Math.max(Math.abs(demoWheel3), Math.abs(demoWheel4))));
            if (max < robot.maxSpeed) {
                robot.setPower(demoWheel1, demoWheel2, demoWheel3, demoWheel4);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower(demoWheel1 * scaleFactor,
                        demoWheel2 * scaleFactor,
                        demoWheel3 * scaleFactor, demoWheel4 * scaleFactor);


            }

            //gamepad2 = Driver 2
            if (gamepad2.a) {
            }


            if (gamepad2.b && !pressingb) {
                //action
                pressingb = true;

            } else if (!gamepad2.b) {
                pressingb = false;
            }

            //only for using trigger as a button

            if ((gamepad2.left_trigger > 0.1) && !pressinglt) {
                //close claw

                robot.demoServo.setPosition(0.2);
                pressinglt = true;
                difference = false;
            } else if ((gamepad2.left_trigger > 0.1) && !pressinglt) {
                // open claw

                robot.demoServo.setPosition(0.9);
                pressinglt = true;
                difference = true;
            } else if((gamepad2.left_trigger > 0.1)) {
                pressinglt = false;
            }


        }



    }

}
