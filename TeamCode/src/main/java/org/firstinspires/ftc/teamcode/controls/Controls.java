package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.Constants;

public class Controls
{
    private DcMotor motorUpLeft, motorUpRight, motorDownLeft,  motorDownRight;
    private PickupControls pickupControls;
    private SensorControls sensorControls;
    private static boolean pickupControlsEnabled = true;
    private static boolean controlsEnabled = true;
    private static boolean sensorControlsEnabled = false;

    public void GoDown(double speedEngine)
    {
        if(pickupControlsEnabled)
        {
            pickupControls.goDown(speedEngine);
        }
    }

    public void GoUp(double speedEngine)
    {
        if(pickupControlsEnabled)
        {
            pickupControls.goUp(speedEngine);
        }
    }

    public Controls(HardwareMap hardwareMap)
    {
        if(controlsEnabled)
        {
            motorDownRight = hardwareMap.get(DcMotor.class, Constants.MOTOR_DOWN_RIGHT);
            motorDownLeft = hardwareMap.get(DcMotor.class, Constants.MOTOR_DOWN_LEFT);
            motorUpRight = hardwareMap.get(DcMotor.class, Constants.MOTOR_UP_RIGHT);
            motorUpLeft = hardwareMap.get(DcMotor.class, Constants.MOTOR_UP_LEFT);
            motorUpLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            motorDownLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if(pickupControlsEnabled)
        {
            pickupControls = new PickupControls(hardwareMap);
        }

        if(sensorControlsEnabled)
        {
            sensorControls = new SensorControls(hardwareMap);
        }
    }

    public void ResetEngine()
    {
        if(pickupControlsEnabled)
        {
            pickupControls.reset();
        }

        if(controlsEnabled)
        {
            motorUpLeft.setPower(0);
            motorUpRight.setPower(0);
            motorDownLeft.setPower(0);
            motorDownRight.setPower(0);
        }
    }

    public void RotateCounterClockwise(double speed)
    {
        motorUpLeft.setPower(-speed);
        motorUpRight.setPower(speed);
        motorDownLeft.setPower(-speed);
        motorDownRight.setPower(speed);
    }

    public void RotateClockwise(double speed)
    {
        motorUpLeft.setPower(speed);
        motorUpRight.setPower(-speed);
        motorDownLeft.setPower(speed);
        motorDownRight.setPower(-speed);
    }

    public void GoBackwardLeft(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(-speedEngine);
            motorUpRight.setPower(-speedEngine);
        }
    }

    public void GoBackwardRight(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownLeft.setPower(-speedEngine);
            motorUpRight.setPower(-speedEngine);
        }
    }

    public void GoFrontwardLeft(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownLeft.setPower(speedEngine);
            motorUpRight.setPower(speedEngine);
        }
    }

    public void GoFrontwordRight(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(speedEngine);
            motorDownRight.setPower(speedEngine);
        }
    }

    public void GoBackward(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(-speedEngine);
            motorUpRight.setPower(-speedEngine);
            motorDownLeft.setPower(-speedEngine);
            motorDownRight.setPower(-speedEngine);
        }
    }

    public void GoFrontward(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorUpLeft.setPower(speedEngine);
            motorUpRight.setPower(speedEngine);
            motorDownLeft.setPower(speedEngine);
            motorDownRight.setPower(speedEngine);
        }
    }

    public void GoRight(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownRight.setPower(speedEngine);
            motorUpRight.setPower(-speedEngine);
            motorUpLeft.setPower(speedEngine);
            motorDownLeft.setPower(-speedEngine);
        }
    }

    public void GoLeft(double speedEngine)
    {
        if(controlsEnabled)
        {
            motorDownLeft.setPower(speedEngine);
            motorUpLeft.setPower(-speedEngine);
            motorUpRight.setPower(speedEngine);
            motorDownRight.setPower(-speedEngine);
        }
    }
}
