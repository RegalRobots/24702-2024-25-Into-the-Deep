package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@Config
@TeleOp(name = "PID Controller")
public class PIDFControl extends OpMode {

    private PIDController controller;

    Hardware robot = Hardware.getInstance();
    public static double Ki = 0;
    public static double Kp = 0;
    public static double Kd = 0;

    public static double feedForward = 0;

    public static int target = 0;
    public final double ticksInDegree = (700 / 180);

    @Override
    public void init(){
        robot.init(hardwareMap);
        controller = new PIDController(Kp, Ki, Kd);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    }

    @Override
    public void loop() {
        controller.setPID(Kp, Ki, Kd);
        int verticalArmPosition = robot.armVertical.getCurrentPosition();
        double pid = controller.calculate(verticalArmPosition, target);
        double ff = Math.cos(Math.toRadians(target / ticksInDegree)) * feedForward;

        double power = pid + ff;

        robot.armVertical.setPower(power);

        telemetry.addData("pos", verticalArmPosition);
        telemetry.addData("target", target);
        telemetry.update();
    }
}
