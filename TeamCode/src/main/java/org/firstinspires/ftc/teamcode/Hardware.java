package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

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
        rf = hwMap.get(DcMotor.class, "demoWheel1");
        rf.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setPower(0);

        rb = hwMap.get(DcMotor.class, "demoWheel2");
        rb.setDirection(DcMotorSimple.Direction.REVERSE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setPower(0);

        lf = hwMap.get(DcMotor.class, "demoWheel3");
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lf.setPower(0);

        lb = hwMap.get(DcMotor.class, "demoWheel4");
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setPower(0);
        demoServo = hwMap.get(Servo.class, "demoServo");

    }

    public void setPower(double fr, double br, double fl, double bl){
        rf.setPower(Range.clip(fr, -maxSpeed, maxSpeed));
        rb.setPower(Range.clip(br, -maxSpeed, maxSpeed));
        lf.setPower(Range.clip(fl, -maxSpeed, maxSpeed));
        lb.setPower(Range.clip(bl, -maxSpeed, maxSpeed));
    }
}
