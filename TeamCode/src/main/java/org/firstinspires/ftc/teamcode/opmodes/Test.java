package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.controls.SensorControls;
import org.firstinspires.ftc.teamcode.utils.Color;

@TeleOp
public class Test extends LinearOpMode
{
    Controls controls;

    private void setup()
    {
        //armControls = new ArmControls(hardwareMap);
        //controls = new Controls(hardwareMap);
    }

    @Override
    public void runOpMode()
    {
        setup();
        waitForStart();
        while(opModeIsActive())
        {
        }
    }
}
