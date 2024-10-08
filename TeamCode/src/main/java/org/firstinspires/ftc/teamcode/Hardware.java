package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/*
This is what position to connect the motors to on the control hub
Names refer to variable names

rf - 0
lf - 1
lb - 2
rb - 3
 */
public class Hardware {
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;
    public Servo demoServo;

    public static double maxSpeed = 0.5;
    private static Hardware instance = null;
    public static Hardware getInstance() {
        if(instance == null){
            instance = new Hardware();
        }
        return instance;
    }
    public void init(HardwareMap hwMap){
        rf = hwMap.get(DcMotor.class, "rf");
        rf.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setPower(0);
        //1

        rb = hwMap.get(DcMotor.class, "rr");
        rb.setDirection(DcMotorSimple.Direction.REVERSE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setPower(0);
        //3

        lf = hwMap.get(DcMotor.class, "lf");
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lf.setPower(0);
        //0

        lb = hwMap.get(DcMotor.class, "lr");
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setPower(0);

        //demoServo = hwMap.get(Servo.class, "demoServo");

    }

    public void setPower(double fr, double br, double fl, double bl){
        rf.setPower(Range.clip(fr, -maxSpeed, maxSpeed));
        rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        lf.setPower(Range.clip(fl, -maxSpeed, maxSpeed));
        lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
    }
}
