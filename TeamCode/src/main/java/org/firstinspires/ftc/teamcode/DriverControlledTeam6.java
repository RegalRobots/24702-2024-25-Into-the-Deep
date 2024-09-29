package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Demo TeleOp")
public class DriverControlledTeam6 extends LinearOpMode {

    HardwareTeam6 robot = HardwareTeam6.getInstance();

    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();


        waitForStart();

        //"Easy way to program it" - BiancaGL

        boolean pressingb = false;
        boolean pressinglt = false;
        boolean togglelt = !false;

        while (opModeIsActive()) {

            //gamepad1 is Driver 1 (controls movement of the bot)
            double movement = -gamepad1.left_stick_x;
            double strafing = gamepad1.right_stick_y;
            double turning = -gamepad1.left_stick_y;

            double demoWheel1 = movement - strafing - turning;
            double demoWheel2 = movement + strafing - turning;
            double demoWheel3 = movement + strafing + turning;
            double demoWheel4 = movement - strafing + turning;

            double max = Math.max(Math.abs(demoWheel1),
                    Math.max(Math.abs(demoWheel2),
                            Math.max(Math.abs(demoWheel3),
                                    Math.abs(demoWheel4))));
            if (max < robot.maxSpeed) {
                robot.setPower(demoWheel1, demoWheel2, demoWheel3, demoWheel4);
            } else {
                double scaleFactor = max / robot.maxSpeed;
                robot.setPower(demoWheel1 * scaleFactor, demoWheel2 * scaleFactor,
                        demoWheel3 * scaleFactor, demoWheel4 * scaleFactor);
            }

            //gamepad2 is Driver 2, controls the arm and other crap

            //bad way (dont do this)
            if (gamepad2.a) {
                //idk
            }

            //do this if button is pressed
            if (gamepad2.b && !pressingb) {
                //action here
                pressingb = true;
            } else if (!pressingb) {
                pressingb = false;
            }

            //how to use trigger as a button
            if (gamepad2.left_trigger > 0.1) {}

            //use trigger to toggle something
            if ((gamepad2.left_trigger > 0.1) && !pressinglt && togglelt) {
                //action
                pressinglt = true;
                togglelt = false;
            } else if ((gamepad2.left_trigger > 0.1) && !pressinglt && !togglelt) {
                //other action
                pressinglt = true;
                togglelt = true;
            } else if (!(gamepad2.left_trigger > 0.1)) {
                pressinglt = false;
            }

//            if ((gamepad2.left_trigger) && !pressinglt) {
//                //action here
//                if (!togglelt) {
//                    //set servo to close
//                    togglelt = true;
//                } else {
//                    //set servo to open
//                    togglelt = false;
//                }
//
//                pressinglt = true;
//            } else if (!pressinglt) {
//                pressinglt = false;
//            }

        }
    }
}
