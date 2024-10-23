package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Encoder Tester")
public class EncoderTester extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    boolean pressingA = false;

    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        int position = 0;
        boolean pressingA = false;
        boolean pressingB = false;

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a && !pressingA) {
                position += 10;
                pressingA = true;
            } else if (!gamepad1.a) {
                pressingA = false;
            }

            if (gamepad1.b && !pressingB) {
                position -= 10;
                pressingB = true;
            } else if (!gamepad1.b) {
                pressingB = false;
            }

            robot.armExtension.setPower(1);
            robot.armExtension.setTargetPosition(position);
            robot.armExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("position", position);
            telemetry.addData("Encoder Position", robot.armExtension.getCurrentPosition());
            telemetry.update();
        }

    }
}
