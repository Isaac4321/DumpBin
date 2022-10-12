package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Robot;

class EncoderMath {
    public static final double PULSES_PER_MOTOR_REV = 537.7; //This is the pulses / ticks per motor revolution.
    public static final double DRIVE_GEAR_REDUCTION = 1.0; //Drive gear reduction is set to 1.0 if the wheel is directly mounted to the motor shaft. If not, calculate with following formula: (number of teeth on the large gear) / (number of teeth on the small gear)
    public static final double WHEEL_DIAMETER_INCHES = 3.77953; //The current wheel diameter which is used for the COUNTS_PER_CENTIMETRE calculation.
    public static final double PULSES_PER_CENTIMETRE = (PULSES_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415) * 2.54; // This variable is used for getting the necessary ticks / pulses for moving a distance (in centimetres).

}

public class DriveBaseComponent extends ComponentBase{

    private final double MOTOR_SPEED_MULT = 0.80;

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearLeftMotor;
    private DcMotor rearRightMotor;

    private Gamepad controller1;
    private final ElapsedTime time = new ElapsedTime();

    private boolean autoEnabled;

    public DriveBaseComponent(Robot robot, boolean autoEnabled) {
        super(robot);
        this.autoEnabled = autoEnabled;
        if (autoEnabled) initAutonomous(); else init();

    }

    @Override
    public void update() {
        drive();
    }

    private void init() {

        frontLeftMotor = robot.hardwareMap().get(DcMotor.class, "frontLeft");
        frontRightMotor = robot.hardwareMap().get(DcMotor.class, "frontRight");
        rearLeftMotor = robot.hardwareMap().get(DcMotor.class, "rearLeft");
        rearRightMotor = robot.hardwareMap().get(DcMotor.class, "rearRight");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    private void initAutonomous() {
        controller1 = robot.controller1();

        frontLeftMotor = robot.hardwareMap().get(DcMotor.class, "frontLeft");
        frontRightMotor = robot.hardwareMap().get(DcMotor.class, "frontRight");
        rearLeftMotor = robot.hardwareMap().get(DcMotor.class, "rearLeft");
        rearRightMotor = robot.hardwareMap().get(DcMotor.class, "rearRight");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public boolean motorsAreBusy() {
        return frontLeftMotor.isBusy() && frontRightMotor.isBusy() && rearLeftMotor.isBusy() && rearRightMotor.isBusy();
    }

    public void drive(int cm, double timeout) {
        time.reset();
        int targetPosition = (frontLeftMotor.getCurrentPosition() + (int) (cm * EncoderMath.PULSES_PER_CENTIMETRE));

        frontLeftMotor.setTargetPosition(targetPosition);
        frontRightMotor.setTargetPosition(targetPosition);
        rearLeftMotor.setTargetPosition(targetPosition);
        rearRightMotor.setTargetPosition(targetPosition);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(MOTOR_SPEED_MULT);
        frontRightMotor.setPower(MOTOR_SPEED_MULT);
        rearLeftMotor.setPower(MOTOR_SPEED_MULT);
        rearRightMotor.setPower(MOTOR_SPEED_MULT);

        while (motorsAreBusy() && time.seconds() < timeout) {

        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

    }

    public void strafe(double cm, double timeout) {

    }

    public void rotate(double deg, double timeout) {

    }


    private void drive() {
        double drive = -controller1.left_stick_y;
        double turn = controller1.left_stick_x;
        double rx = controller1.right_stick_x;



        double frontLeftPower = Range.clip(drive + turn + rx, -1.0, 1);
        double backLeftPower = Range.clip((drive - turn + rx), -1.0, 1);
        double frontRightPower = Range.clip((drive - turn - rx), -1.0, 1);
        double backRightPower = Range.clip((drive + turn - rx), -1.0, 1);

        //TODO: introduce joystick scaling
        frontLeftMotor.setPower(frontLeftPower * MOTOR_SPEED_MULT);
        frontRightMotor.setPower(backLeftPower * MOTOR_SPEED_MULT);
        rearLeftMotor.setPower(frontRightPower * MOTOR_SPEED_MULT);
        rearRightMotor.setPower(backRightPower * MOTOR_SPEED_MULT);
    }

}
