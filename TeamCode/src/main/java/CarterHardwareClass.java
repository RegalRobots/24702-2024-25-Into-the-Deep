import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class CarterHardwareClass {
    public DcMotor demoWheelFR;
    public DcMotor demoWheelBR;
    public DcMotor demoWheelFL;
    public DcMotor demoWheelBL;
    
    public Servo demoServo;

    public static double maxSpeed = 1;

    private static CarterHardwareClass myInstance = null;

    public static CarterHardwareClass getInstance() {
        if (myInstance == null) {
            myInstance = new CarterHardwareClass();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {
        demoWheelFL = hwMap.get(DcMotor.class, "demoWheelFL");
        demoWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheelFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheelFL.setPower(0);

        demoWheelFR = hwMap.get(DcMotor.class, "demoWheelFR");
        demoWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheelFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheelFR.setPower(0);

        demoWheelBL = hwMap.get(DcMotor.class, "demoWheelBL");
        demoWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheelBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheelBL.setPower(0);

        demoWheelBR = hwMap.get(DcMotor.class, "demoWheelBR");
        demoWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoWheelBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        demoWheelBR.setPower(0);

        /*
        for reversing wheels:
        demoWheelFL.setDirection(DcMotorSimple.Direction.REVERSE);
        demoWheelBL.setDirection(DcMotorSimple.Direction.REVERSE);
        */

        demoServo = hwMap.get(Servo.class, "demoServo");
    }

    public void setPower(double FLwheel, double FRwheel, double BLwheel, double BRwheel) {
        demoWheelFL.setPower(Range.clip(FLwheel, -maxSpeed, maxSpeed));
        demoWheelFR.setPower(Range.clip(FRwheel, -maxSpeed, maxSpeed));
        demoWheelBL.setPower(Range.clip(BLwheel, -maxSpeed, maxSpeed));
        demoWheelBR.setPower(Range.clip(BRwheel, -maxSpeed, maxSpeed));
    }

}
