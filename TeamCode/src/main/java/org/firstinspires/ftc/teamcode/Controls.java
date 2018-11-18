package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Controls
{
    DcMotor motorLeft, motorRight, testEngine, baseEngine;
    Servo testServo;
    double positionServo;

    public Controls(HardwareMap hardwareMap)
    {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        testServo = hardwareMap.get(Servo.class, "testServo");
        testEngine = hardwareMap.get(DcMotor.class, "testMotor");
        baseEngine = hardwareMap.get(DcMotor.class, "baseMotor");
        positionServo = testServo.getPosition();
        motorLeft.setDirection((DcMotorSimple.Direction.REVERSE));
    }

    public void MoveBaseEngine(double testSpeed)
    {
        testEngine.setPower(testSpeed);
    }

    public void MoveTestEngine(double testSpeed)
    {
        testEngine.setPower(testSpeed);
    }

    public void MoveServo(double speedServo)
    {
        if(speedServo >= 0)
        {
            if(positionServo - speedServo >= Servo.MIN_POSITION)
                positionServo -= speedServo;
        }
        else
        {
            if(positionServo + speedServo <= Servo.MAX_POSITION)
                positionServo += speedServo;
        }
        testServo.setPosition(positionServo);
    }

    public void ResetEngine()
    {
        motorLeft.setPower(0);
        motorRight.setPower(0);
        testEngine.setPower(0);
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
}
