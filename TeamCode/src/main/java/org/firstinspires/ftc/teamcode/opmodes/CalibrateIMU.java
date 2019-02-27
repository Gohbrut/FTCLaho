package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.utils.Defines;
import java.io.File;
import java.io.IOException;

@TeleOp
public class CalibrateIMU extends LinearOpMode
{
    private BNO055IMU imu;

    private void calibrateGyro()
    {
        while(!imu.isGyroCalibrated())
        {

        }
    }

    public void runOpMode()
    {
        waitForStart();
        telemetry.addData("Started", "calibration!");
        telemetry.update();
        imu = hardwareMap.get(BNO055IMU.class, Defines.SENSOR_IMU);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        imu.initialize(parameters);
        telemetry.addData("Initialized", "imu!");
        calibrateGyro();
        telemetry.addData("Calibrated", "gyro!");
        telemetry.update();
        File file = AppUtil.getInstance().getSettingsFile(Defines.IMU_CALIBRATION_HOME);
        try
        {
            file.createNewFile();
        }
        catch(IOException e)
        {

        }
        ReadWriteFile.writeFile(file, imu.readCalibrationData().serialize());
        telemetry.addData("Done", "calibrating!");
        telemetry.update();
        while(opModeIsActive())
        {
            String angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle + ";" +
                    imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle + ";" +
                    imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
            telemetry.addData("Angles", angles);
            telemetry.update();
        }
    }
}
