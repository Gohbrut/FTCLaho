package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class Test1 extends LinearOpMode
{
    DcMotor motorLeft, motorRight;
    double speed = 0.5;

    private void setup()
    {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();
    }

    private void ControlEngine()
    {
        motorLeft.setPower(0);
        motorRight.setPower(0);
        if(gamepad1.x && speed > 0.1)
            speed -= 0.1;
        else if(gamepad1.y && speed < 1.0)
            speed += 0.1;
        if(gamepad1.dpad_up)
        {
            motorLeft.setPower(speed);
            motorRight.setPower(-speed);
        }
        else if(gamepad1.dpad_down)
        {
            motorLeft.setPower(-speed);
            motorRight.setPower(speed);
        }
        else if(gamepad1.dpad_right)
        {
            motorRight.setPower(-speed);
            motorLeft.setPower(speed / 2);
        }
        else if(gamepad1.dpad_left)
        {
            motorLeft.setPower(speed);
            motorRight.setPower(-speed / 2);
        }
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
