package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class HardwareTeam6 {

    public DcMotor demoWheel1;
    public DcMotor demoWheel2;
    public DcMotor demoWheel3;
    public DcMotor demoWheel4;
    public Servo demoServo;

    public static double maxSpeed = 1;

    private static HardwareTeam6 myInstance = null;

    public static HardwareTeam6 getInstance() {
        if (myInstance == null) {
            myInstance = new HardwareTeam6();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {

        demoWheel1 = hwMap.get(DcMotor.class, "demoWheel1");
        demoWheel1.setDirection(DcMotorSimple.Direction.REVERSE);
        demoWheel1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel1.setPower(0);

        demoWheel2 = hwMap.get(DcMotor.class, "demoWheel2");
        demoWheel2.setDirection(DcMotorSimple.Direction.REVERSE);
        demoWheel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel2.setPower(0);

        demoWheel3 = hwMap.get(DcMotor.class, "demoWheel3");
        demoWheel3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel3.setPower(0);

        demoWheel4 = hwMap.get(DcMotor.class, "demoWheel4");
        demoWheel4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel4.setPower(0);

        demoServo = hwMap.get(Servo.class, "demoServo");

    }

    public void setPower(double wheelDemo1, double wheelDemo2, double wheelDemo3, double wheelDemo4) {

        demoWheel1.setPower(Range.clip(wheelDemo1, -maxSpeed, maxSpeed));
        demoWheel2.setPower(Range.clip(wheelDemo2, -maxSpeed, maxSpeed));
        demoWheel3.setPower(Range.clip(wheelDemo3, -maxSpeed, maxSpeed));
        demoWheel4.setPower(Range.clip(wheelDemo4, -maxSpeed, maxSpeed));

    }
}


