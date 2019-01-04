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
        controls.ResetEngine();
        controls.GoDown(0.5);
        sleep(500);
        controls.GoLeft(0.5);
        sleep(500);
    }

    private void setup()
    {
        controls = new Controls(hardwareMap);
        controls.GoUp(1);
        waitForStart();
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
