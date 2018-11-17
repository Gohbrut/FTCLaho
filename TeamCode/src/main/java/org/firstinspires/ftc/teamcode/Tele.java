package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Tele extends LinearOpMode
{
    private Controls controls;
    double positionServoStep = 0.05;
    double speedEngine = 0.5;
    double minEngineSpeed = 0.1, maxEngineSpeed = 1.0;
    double speedEngineStep = 0.1;
    public boolean pressedX = false, pressedY = false;

    private void setup()
    {
        hardwareMap.get("");
        controls = new Controls();
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

    private void ControlLeftJoystick()
    {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;

        double speedLeft = y;
        double speedRight = y;
        /* motorLeft.setPower(speedLeft);
        motorRight.setPower(speedRight);
        if(x >= 0 && y >= 0)
        {
            motorLeft.setPower(y);
            motorRight.setPower((y + x / 4) / 2);
        }
        else if(x >= 0 && y < 0)
        {
            motorLeft.setPower(y);
            motorRight.setPower((y + x / 4) / 2);
        }
        else if(x < 0 && y < 0)
        {
            motorRight.setPower(y);
            motorLeft.setPower((y + x / 4) / 2);
        }
        else if(x < 0 && y >= 0)
        {
            motorRight.setPower(y);
            motorLeft.setPower((y + x / 4) / 2);
        }*/
    }

    private void ControlEngine()
    {
        controls.ResetEngine();
        ControlEngineSpeed();
        ControlDpad();
        ControlLeftJoystick();
    }

    private void ControlServo()
    {
        if(gamepad1.a)
            controls.MoveServo(-positionServoStep);
        else if(gamepad1.b)
            controls.MoveServo(positionServoStep);
    }

    public void runOpMode()
    {
        setup();
        while(opModeIsActive())
        {
            ControlEngine();
            ControlServo();
            telemetry.addData("Status", "Running");
            telemetry.addData("Speed", speedEngine);
            telemetry.update();
        }
    }
}
