package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Constants;

public class PickupControls
{
    private DcMotor motorPickup;

    public PickupControls(HardwareMap hardwareMap)
    {
        motorPickup = hardwareMap.get(DcMotor.class, Constants.MOTOR_PICKUP);
    }

    public void goUp(double speed)
    {
        motorPickup.setPower(speed);
    }

    public void goDown(double speed)
    {
        motorPickup.setPower(-speed);
    }

    public void reset()
    {
        motorPickup.setPower(0);
    }
}
