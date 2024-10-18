package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Encoder Tester")
public class EncoderTester extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    boolean pressingA = false;
    public void runOpMode(){
        telemetry.addData("Status", "This is the encoder test");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()){

            if(gamepad1.a && !pressingA){
                telemetry.addData("Encoder Position", robot.armVertical.getCurrentPosition());
                telemetry.update();
                pressingA = true;
            } else if(!gamepad1.a && pressingA){
                pressingA = false;
            }
        }
    }
}
