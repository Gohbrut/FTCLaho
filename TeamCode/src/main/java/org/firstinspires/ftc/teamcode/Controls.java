package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Controls
{
    private DcMotor motorLeft, motorRight, baseEngine, armEngine;
//    private Servo armServo1;
    private double positionServo;

    public ArmControls armControls;

    public Controls(HardwareMap hardwareMap)
    {
        setupBase(hardwareMap);
        setupArm(hardwareMap);
    }

    public void MoveBaseEngine(double testSpeed)
    {
        baseEngine.setPower(testSpeed);
    }

//    public void MoveArm(double speed)
//    {
//        if(speed >= 0)
//        {
//            if(positionServo - speed >= Servo.MIN_POSITION)
//                positionServo -= speed;
//        }
//        else
//        {
//            if(positionServo + speed <= Servo.MAX_POSITION)
//                positionServo += speed;
//        }
////        armServo1.setPosition(positionServo);
//        armEngine.setPower(speed * 10);
//    }

    public void ResetEngine()
    {
        armEngine.setPower(0);
        motorLeft.setPower(0);
        motorRight.setPower(0);
        baseEngine.setPower(0);
    }

    public void GoBackwardLeft(double speedEngine)
    {
        motorLeft.setPower(-speedEngine / 2);
        motorRight.setPower(-speedEngine);
    }

    public void GoBackwardRight(double speedEngine)
    {
        motorLeft.setPower(-speedEngine);
        motorRight.setPower(-speedEngine / 2);
    }

    public void GoFrontwardLeft(double speedEngine)
    {
        motorLeft.setPower(speedEngine / 3);
        motorRight.setPower(speedEngine);
    }

    public void GoFrontwordRight(double speedEngine)
    {
        motorLeft.setPower(speedEngine);
        motorRight.setPower(speedEngine / 2);
    }

    public void GoBackward(double speedEngine)
    {
        motorLeft.setPower(-speedEngine);
        motorRight.setPower(-speedEngine);
    }

    public void GoFrontward(double speedEngine)
    {
        motorLeft.setPower(speedEngine);
        motorRight.setPower(speedEngine);
    }

    public void GoRight(double speedEngine)
    {
        motorLeft.setPower(speedEngine);
        motorRight.setPower(-speedEngine);
    }

    public void GoLeft(double speedEngine)
    {
        motorRight.setPower(speedEngine);
        motorLeft.setPower(-speedEngine);
    }

    // Private class methods

    private void setupBase(HardwareMap hardwareMap) {
        motorLeft = hardwareMap.get(DcMotor.class, Constants.MOTOR_LEFT);
        motorRight = hardwareMap.get(DcMotor.class, Constants.MOTOR_RIGHT);
//        armServo1 = hardwareMap.get(Servo.class, "armServo1");
//        armEngine = hardwareMap.get(DcMotor.class, "armEngine");
        baseEngine = hardwareMap.get(DcMotor.class, Constants.BASE_MOTOR);
//        positionServo = armServo1.getPosition();
        motorLeft.setDirection((DcMotorSimple.Direction.REVERSE));
    }

    private void setupArm(HardwareMap hardwareMap) {
        armControls = new ArmControls(hardwareMap);
    }
}
