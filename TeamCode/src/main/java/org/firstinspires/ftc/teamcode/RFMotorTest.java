package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Test TeleOP")
public class RFMotorTest extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        boolean pressingA = false;
        boolean pressingB = false;
        while(opModeIsActive()){
            if (gamepad2.a && !pressingA) {
                robot.rf.setPower(-0.5);
                pressingA = true;
                pressingB = false;
            } else if (gamepad2.b && !pressingB){
                robot.rf.setPower(0.5);
                pressingB = true;
                pressingA = false;
            } else if((!gamepad2.a) && (!gamepad2.b)){
                pressingA = false;
                pressingB = false;
                robot.rf.setPower(0);
            }


        }
    }

}
