package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.Controls;

@TeleOp(name = "Manualcop")
public class Tele extends LinearOpMode
{
    private Controls controls;
    double speedEngine = 0.5;
    double minEngineSpeed = 0.1, maxEngineSpeed = 1.0;
    double speedEngineStep = 0.1;
    public boolean pressedX = false, pressedY = false;

    private void setup()
    {
        controls = new Controls(hardwareMap);
        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();
    }

    private void ControlEngineSpeed()
    {
        if(gamepad1.x)
        {
            if(!pressedX && speedEngine > minEngineSpeed)
            {
                speedEngine -= speedEngineStep;
                pressedX = true;
            }
        }
        else if(pressedX)
        {
            pressedX = false;
        }
        if(gamepad1.y)
        {
            if(!pressedY && speedEngine < maxEngineSpeed)
            {
                speedEngine += speedEngineStep;
                pressedY = true;
            }
        }
        else if(pressedY)
        {
            pressedY = false;
        }
    }

    private void ControlDpad()
    {
        if(gamepad1.dpad_up && gamepad1.dpad_right)
        {
            controls.GoFrontwordRight(speedEngine);
        }
        else if(gamepad1.dpad_right && gamepad1.dpad_down)
        {
            controls.GoBackwardRight(speedEngine);
        }
        else if(gamepad1.dpad_down && gamepad1.dpad_left)
        {
            controls.GoBackwardLeft(speedEngine);
        }
        else if(gamepad1.dpad_left && gamepad1.dpad_up)
        {
            controls.GoFrontwardLeft(speedEngine);
        }
        else if(gamepad1.dpad_up)
        {
            controls.GoFrontward(speedEngine);
        }
        else if(gamepad1.dpad_down)
        {
            controls.GoBackward(speedEngine);
        }
        else if(gamepad1.dpad_right)
        {
            controls.GoRight(speedEngine);
        }
        else if(gamepad1.dpad_left)
        {
            controls.GoLeft(speedEngine);
        }
    }

    private void ControlPickup()
    {
        if(gamepad1.left_bumper)
            controls.GoDown(speedEngine);
        else if(gamepad1.right_bumper)
            controls.GoUp(speedEngine);
    }

    private void ControlRotation()
    {
        if(gamepad1.a)
            controls.RotateClockwise(speedEngine);
        else if(gamepad1.b)
            controls.RotateCounterClockwise(speedEngine);
    }

    private void ControlEngine()
    {
        controls.ResetEngine();
        ControlEngineSpeed();
        ControlDpad();
        ControlRotation();
    }

    public void runOpMode()
    {
        setup();
        while(opModeIsActive())
        {
            ControlEngine();
            ControlPickup();
            telemetry.addData("Status", "Running");
            telemetry.addData("Speed", speedEngine);
            telemetry.update();
        }
    }
}
