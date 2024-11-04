package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test Auto")
public class PIDTuner extends OpMode {

    public static double Ki = 0;
    public static double Kp = 0;

    public static double Kd = 0;

    public static double feedForward = 0;
    PIDFControl pid = new PIDFControl(Ki, Kp, Kd,feedForward);

    public static int target = 0;
    Hardware robot = Hardware.getInstance();


    public void init(){
        robot.init(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    }
    public void loop(){

        pid.updatePid(robot.rf, target);
        pid.updatePid(robot.rb, target);
        pid.updatePid(robot.lf, target);
        pid.updatePid(robot.lb, target);


        telemetry.addData("pos", robot.rf.getCurrentPosition());
        telemetry.addData("target", target);
        telemetry.update();

    }
}
