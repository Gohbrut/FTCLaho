package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.ArmControls;
import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.controls.SensorControls;
import org.firstinspires.ftc.teamcode.utils.Color;

@TeleOp
public class Test extends LinearOpMode
{
    SensorControls sensorControls;
    ArmControls armControls;
    Controls controls;

    private void setup()
    {
        sensorControls = new SensorControls(hardwareMap);
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
            if(gamepad1.x)
            {
                telemetry.update();
                telemetry.addData("Distance: ", sensorControls.getDistance());
                Color c = sensorControls.getColor();
                telemetry.addData("Color: ", c.red + ";" + c.green + ";" + c.blue);
                telemetry.addData("Orientation: ", sensorControls.getOrientation());
                telemetry.addData("Overall acceleration: ", sensorControls.getOverallAcceleration());
            }
        }
    }
}
