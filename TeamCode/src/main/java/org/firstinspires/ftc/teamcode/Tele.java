package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Tele extends LinearOpMode
{
    DcMotor motorLeft, motorRight;
    double speed = 0.5;
    double minSpeed = 0.1, maxSpeed = 1.0;
    double speedStep = 0.1;
    {
    public boolean pressedX = false, pressedY = false;

    private void setup()
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLeft.setDirection((DcMotorSimple.Direction.REVERSE));
        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();
    }

    private void GoBackward()
    {
        motorLeft.setPower(-speed);
        motorRight.setPower(-speed);
    }

    private void GoFrontward()
    {
        motorLeft.setPower(speed);
        motorRight.setPower(speed);
    }

    private void GoRight()
    {
        motorLeft.setPower(speed);
        motorRight.setPower(-speed);
    }

    private void GoLeft()
    {
        motorRight.setPower(speed);
        motorLeft.setPower(-speed);
    }

    private void ControlSpeed()
    {
        if(gamepad1.x)
        {
            if(!pressedX && speed > minSpeed)
            {
                speed -= speedStep;
                pressedX = true;
            }
        }
        else if(pressedX)
        {
            pressedX = false;
        }
        if(gamepad1.y)
        {
            if(!pressedY && speed < maxSpeed)
            {
                speed += speedStep;
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
            motorLeft.setPower(speed);
            motorRight.setPower(speed / 2);
        }
        else if(gamepad1.dpad_right && gamepad1.dpad_down)
        {
            motorLeft.setPower(-speed);
            motorRight.setPower(-speed / 2);
        }
        else if(gamepad1.dpad_down && gamepad1.dpad_left)
        {
            motorLeft.setPower(-speed / 2);
            motorRight.setPower(-speed);
        }
        else if(gamepad1.dpad_left && gamepad1.dpad_up)
        {
            motorLeft.setPower(speed / 3);
            motorRight.setPower(speed);
        }
        else if(gamepad1.dpad_up)
        {
            GoFrontward();
        }
        else if(gamepad1.dpad_down)
        {
            GoBackward();
        }
        else if(gamepad1.dpad_right)
        {
            GoRight();
        }
        else if(gamepad1.dpad_left)
        {
            GoLeft();
        }
    }

    private void ControlLeftJoystick()
    {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;

        double speedLeft = y;
        double speedRight = y;
        motorLeft.setPower(speedLeft);
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
        }
    }

    private void ControlEngine()
    {
        motorLeft.setPower(0);
        motorRight.setPower(0);

        ControlSpeed();
        ControlDpad();
        ControlLeftJoystick();
        ControlBackButtons();
    }

    private void ControlBackButtons()
    {
        if(gamepad1.right_bumper)
            motorRight.setPower(-speed);
        else if(gamepad1.right_trigger != 0)
            motorRight.setPower(gamepad1.right_trigger);
        if(gamepad1.left_bumper)
            motorLeft.setPower(-speed);
        else if(gamepad1.left_trigger != 0)
            motorLeft.setPower(gamepad1.left_trigger);
    }

    public void runOpMode()
    {
        setup();
        while(opModeIsActive())
        {
            ControlEngine();
            telemetry.addData("Status", "Running");
            telemetry.addData("Speed", speed);
            telemetry.update();
        }
    }
}
