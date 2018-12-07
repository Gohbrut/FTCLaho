package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.Constants;

public class Controls
{
    private DcMotor motorUpLeft, motorUpRight, motorDownLeft,  motorDownRight;

    public ArmControls armControls;
    public PickupControls pickupControls;
    public SensorControls sensorControls;

    public Controls(HardwareMap hardwareMap)
    {
        motorDownRight = hardwareMap.get(DcMotor.class, Constants.MOTOR_DOWN_RIGHT);
        motorDownLeft = hardwareMap.get(DcMotor.class, Constants.MOTOR_DOWN_LEFT);
        motorUpRight = hardwareMap.get(DcMotor.class, Constants.MOTOR_UP_RIGHT);
        motorUpLeft = hardwareMap.get(DcMotor.class, Constants.MOTOR_UP_LEFT);
        motorUpLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDownLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        pickupControls = new PickupControls(hardwareMap);
        //sensorControls = new SensorControls(hardwareMap);
        //armControls = new ArmControls(hardwareMap);
    }

    public void ResetEngine()
    {
        pickupControls.reset();
        motorUpLeft.setPower(0);
        motorUpRight.setPower(0);
        motorDownLeft.setPower(0);
        motorDownRight.setPower(0);
    }

    public void GoBackwardLeft(double speedEngine)
    {
        motorUpLeft.setPower(-speedEngine);
        motorUpRight.setPower(-speedEngine);
    }

    public void GoBackwardRight(double speedEngine)
    {
        motorDownLeft.setPower(-speedEngine);
        motorUpRight.setPower(-speedEngine);
    }

    public void GoFrontwardLeft(double speedEngine)
    {
        motorDownLeft.setPower(speedEngine);
        motorUpRight.setPower(speedEngine);
    }

    public void GoFrontwordRight(double speedEngine)
    {
        motorUpLeft.setPower(speedEngine);
        motorDownRight.setPower(speedEngine);
    }

    public void GoBackward(double speedEngine)
    {
        motorDownLeft.setPower(-speedEngine);
        motorDownRight.setPower(-speedEngine);
    }

    public void GoFrontward(double speedEngine)
    {
        motorUpLeft.setPower(speedEngine);
        motorUpRight.setPower(speedEngine);
    }

    public void GoRight(double speedEngine)
    {
        motorDownRight.setPower(speedEngine);
        motorUpRight.setPower(speedEngine);
    }

    public void GoLeft(double speedEngine)
    {
        motorDownLeft.setPower(speedEngine);
        motorUpLeft.setPower(-speedEngine);
    }
}
