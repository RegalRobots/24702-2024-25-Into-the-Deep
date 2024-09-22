package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareTeam6 {
    public DcMotor demoWheel1;
    public DcMotor demoWheel2;
    public DcMotor demoWheel3;
    public DcMotor demoWheel4;
    public Servo demoServo;

//    public static double maxSpeed = 1;
    private static HardwareTeam6 myInstance = null;
    public double maxSpeed = (1);

    public static HardwareTeam6 getInstance() {
        if (myInstance == null) {
            myInstance = new HardwareTeam6();
        }
        return myInstance;
    }
    public void init(HardwareMap hwmap) {

        demoWheel1 = hwmap.get(DcMotor.class, "demoWheel1");
        demoWheel1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel1.setPower(0);


        demoWheel2 = hwmap.get(DcMotor.class, "demoWheel1");
        demoWheel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel2.setPower(0);


        demoWheel3 = hwmap.get(DcMotor.class, "demoWheel1");
        demoWheel3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel3.setPower(0);


        demoWheel4 = hwmap.get(DcMotor.class, "demoWheel1");
        demoWheel4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheel4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheel4.setPower(0);

        demoServo = hwmap.get(Servo.class,  "demoServo");

    }
    public void setPower(double wheelDemo1, double wheelDemo2,double wheelDemo3, double wheelDemo4){

        demoWheel1.setPower(Range.clip(wheelDemo1, -maxSpeed, maxSpeed));
        demoWheel2.setPower(Range.clip(wheelDemo2, -maxSpeed, maxSpeed));
        demoWheel3.setPower(Range.clip(wheelDemo3, -maxSpeed, maxSpeed));
        demoWheel4.setPower(Range.clip(wheelDemo4, -maxSpeed, maxSpeed));


        }

    }