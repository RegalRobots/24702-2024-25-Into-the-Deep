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
public class PIDFControl{

    private PIDController controller;

    double Kp;
    double Ki;
    double Kd;
    double feedForward;
    public final double ticksInDegree = (700 / 180);


    PIDFControl(double Kp, double Ki, double Kd, double feedForward) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.feedForward = feedForward;
        controller = new PIDController(Kp, Ki, Kd);

    }


    public void updatePid(DcMotorEx motor, int target){
        controller.setPID(Kp, Ki, Kd);
        int verticalArmPosition = motor.getCurrentPosition();
        double pid = controller.calculate(verticalArmPosition, target);
        double ff = Math.cos(Math.toRadians(target / ticksInDegree)) * feedForward;

        double power = pid + ff;

        motor.setPower(power);


    }
}
