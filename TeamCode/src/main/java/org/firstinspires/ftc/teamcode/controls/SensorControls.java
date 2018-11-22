package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.utils.Color;
import org.firstinspires.ftc.teamcode.utils.Constants;

public class SensorControls
{
    private DistanceSensor distanceSensor;
    private ColorSensor colorSensor;
    private BNO055IMU imuSensor;

    public SensorControls(HardwareMap hardwareMap)
    {
        distanceSensor = hardwareMap.get(DistanceSensor.class, Constants.SENSOR_DISTANCE);
        colorSensor = hardwareMap.get(ColorSensor.class, Constants.SENSOR_COLOR);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        imuSensor = hardwareMap.get(BNO055IMU.class, Constants.SENSOR_IMU);
        imuSensor.initialize(parameters);
    }

    public double getDistance()
    {
        return distanceSensor.getDistance(DistanceUnit.CM);
    }

    public Orientation getOrientation()
    {
        return imuSensor.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
    }

    public Acceleration getOverallAcceleration()
    {
        return imuSensor.getOverallAcceleration();
    }

    public Color getColor()
    {
        return new Color(colorSensor.red(), colorSensor.green(), colorSensor.blue(), colorSensor.alpha());
    }
}
