package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.controls.Controls;

@Autonomous(name = "Robocop")
public class Auto extends LinearOpMode
{
    private Controls controls;

    private void park()
    {

    }

    private void claim()
    {

    }

    private void sample()
    {

    }

    private void land()
    {

    }

    private void setup()
    {
        controls = new Controls(hardwareMap);
    }

    public void runOpMode()
    {
        setup();
        land();
        sample();
        claim();
        park();
    }
}
