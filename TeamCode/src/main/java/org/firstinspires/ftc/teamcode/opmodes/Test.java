package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.controls.SensorControls;
import org.firstinspires.ftc.teamcode.utils.Color;
import org.firstinspires.ftc.teamcode.utils.Defines;
import org.firstinspires.ftc.teamcode.utils.Utils;

@TeleOp
public class Test extends LinearOpMode
{

    private void debug()
    {
        int pos = Utils.getGoldLocation();
        if(pos == Defines.MINERAL_LOCATION_CENTER)
            telemetry.addData("Mineral pos: ", "Center");
        else if(pos == Defines.MINERAL_LOCATION_LEFT)
            telemetry.addData("Mineral pos: ", "Left");
        else if(pos == Defines.MINERAL_LOCATION_RIGHT)
            telemetry.addData("Mineral pos: ", "Right");
        else
            telemetry.addData("Mineral pos: ", pos);
        telemetry.update();
    }

    private void setup()
    {
        Utils.init(hardwareMap);
    }

    @Override
    public void runOpMode()
    {
        setup();
        waitForStart();
        while(opModeIsActive())
        {
            debug();
        }
    }
}
