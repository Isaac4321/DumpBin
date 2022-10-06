package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Robot;

public class DriveBaseComponent extends ComponentBase{

    private final double MOTOR_SPEED_MULT = 0.80;

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearLeftMotor;
    private DcMotor rearRightMotor;

    private Gamepad controller1;

    public DriveBaseComponent(Robot robot) {
        super(robot);

    }

    @Override
    public void update() {
        drive();
    }

    @Override
    public void init() {
        controller1 = robot.controller1();

        frontLeftMotor = robot.hardwareMap().get(DcMotor.class, "frontLeft");
        frontRightMotor = robot.hardwareMap().get(DcMotor.class, "frontRight");
        rearLeftMotor = robot.hardwareMap().get(DcMotor.class, "rearLeft");
        rearRightMotor = robot.hardwareMap().get(DcMotor.class, "rearRight");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
