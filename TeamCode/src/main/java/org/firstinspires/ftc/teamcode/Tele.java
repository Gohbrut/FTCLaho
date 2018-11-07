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

    private void setup()
    {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);
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
        motorRight.setPower(speed / 4);
    }

    private void GoLeft()
    {
        motorRight.setPower(speed);
        motorLeft.setPower(speed / 4);
    }

    private void ControlSpeed()
    {
        if(gamepad1.x && speed > minSpeed)
            speed -= speedStep;
        else if(gamepad1.y && speed < maxSpeed)
            speed += speedStep;
    }

    private void ControlDpad()
    {
        if(gamepad1.dpad_up)
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
        double tmp = speed;
        
        speed = tmp;
    }

    private void ControlEngine()
    {
        motorLeft.setPower(0);
        motorRight.setPower(0);

        ControlSpeed();
        ControlDpad();
        ControlLeftJoystick();
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
