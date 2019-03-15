package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.utils.Defines;
import org.firstinspires.ftc.teamcode.utils.Song;
import org.firstinspires.ftc.teamcode.utils.Utils;

@TeleOp(name = "Manualcop")
public class Tele extends LinearOpMode
{
    private Controls controls;
    private Song song;

    private void setup()
    {
        Utils.init(hardwareMap);
        song = new Song(hardwareMap.appContext, R.raw.derp4);
        controls = new Controls(hardwareMap);
        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();
    }

    private void debug()
    {
        //telemetry.addData("Angle: ", (int)controls.getNormalizedAngle());
        //telemetry.addData("Distance: ", controls.getDistance());
        //telemetry.addData("Yellow: ", controls.getColor().isYellow());
        //telemetry.addData("Servo left: ", controls.getCollectorControls().servoLeft.getPosition());
        //telemetry.addData("Servo right: ", controls.getCollectorControls().servoRight.getPosition());
        String loc = "default";
        int l = Utils.getGoldLocation();
        if(l == Defines.MINERAL_LOCATION_CENTER)
            loc = "center";
        else if(l == Defines.MINERAL_LOCATION_LEFT)
            loc = "left";
        else if(l == Defines.MINERAL_LOCATION_RIGHT)
            loc = "right";
        telemetry.addData("Location: ", loc);
        telemetry.update();
    }

    private void controlJoystick()
    {
        controls.move(-gamepad1.left_stick_x, gamepad1.left_stick_y);
    }

    private void derp()
    {
        if(gamepad1.x)
            song.play();
        else if(gamepad1.y)
            song.stop();
    }

    private void controlGamepad1()
    {
        controlJoystick();
        controlPickup();
        rotate();
    }

    private void controlPickup()
    {
        if(controls.pickupControls.motorMove)
            return;
        if(gamepad1.right_bumper)
        {
            controls.pickupControls.motorMove = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    controls.pickupControls.goUp(1);
                    while(gamepad1.right_bumper)
                        try
                        {
                            Thread.sleep(5);
                        }
                        catch(InterruptedException e)
                        {

                        }
                    controls.pickupControls.goUp(0);
                    controls.pickupControls.motorMove = false;
                }
            }).run();
        }
        else if(gamepad1.left_bumper)
        {
            controls.pickupControls.motorMove = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    controls.pickupControls.goDown(1);
                    while(gamepad1.left_bumper)
                        try
                        {
                            Thread.sleep(5);
                        }
                        catch(InterruptedException e)
                        {

                        }
                    controls.pickupControls.goDown(0);
                    controls.pickupControls.motorMove = false;
                }
            }).run();
        }
    }

    private void rotate()
    {
        if (gamepad1.right_stick_x > 0.1)
            controls.rotateClockwise(gamepad1.right_stick_x);
        else if (gamepad1.right_stick_x < -0.1)
            controls.rotateClockwise(gamepad1.right_stick_x);
    }

    private void controlGamepad2()
    {
        if(gamepad2.right_trigger > 0.1 && !controls.collectorControls.baseMotorMove)
        {
            controls.collectorControls.baseMotorMove = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(gamepad2.right_trigger > 0.1)
                    {
                        controls.getCollectorControls().base(gamepad2.right_trigger);
                        try
                        {
                            Thread.sleep(5);
                        }
                        catch(InterruptedException e)
                        {

                        }
                    }
                    controls.collectorControls.base(0);
                    controls.collectorControls.baseMotorMove = false;
                }
            }).run();
        }
        else if(gamepad2.left_trigger > 0.1 && !controls.collectorControls.baseMotorMove)
        {
            controls.collectorControls.baseMotorMove = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(gamepad2.left_trigger > 0.1)
                    {
                        controls.getCollectorControls().base(-gamepad2.left_trigger);
                        try
                        {
                            Thread.sleep(5);
                        }
                        catch(InterruptedException e)
                        {

                        }
                    }
                    controls.collectorControls.base(0);
                    controls.collectorControls.baseMotorMove = false;
                }
            }).run();
        }
        if(!controls.collectorControls.servoExtenderMove)
        {
            if(gamepad2.left_stick_y >= 0.1 && gamepad2.left_stick_y <= -0.1)
            {
                controls.collectorControls.servoExtenderMove = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(gamepad2.left_stick_y >= 0.1 && gamepad2.left_stick_y <= -0.1)
                        {
                            double temp = 0.5 + gamepad2.left_stick_y / 2;
                            controls.collectorControls.extend(temp);
                            try
                            {
                                Thread.sleep(5);
                            }
                            catch(InterruptedException e)
                            {

                            }
                        }
                        controls.collectorControls.servoExtenderMove = false;
                    }
                }).run();
            }
        }
        double extender = 0.5 + gamepad2.left_stick_y / 2;
        controls.getCollectorControls().extend(extender);
        controls.getCollectorControls().arm(gamepad2.right_stick_y);
        if(gamepad2.y)
        {
            if(!controls.collectorControls.servoCollectorMove)
            {
                controls.collectorControls.servoCollectorMove = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        controls.collectorControls. collect();
                        while(gamepad2.y)
                        {
                            try
                            {
                                Thread.sleep(5);
                            }
                            catch(InterruptedException e)
                            {

                            }
                        }
                        controls.collectorControls.resetServos();
                        controls.collectorControls.servoCollectorMove = false;
                    }
                }).run();
            }
        }
    }

    public void runOpMode()
    {
        setup();
        controls.resetEngine();
        while(opModeIsActive())
        {
            derp();
            controls.resetEngine();
            controlGamepad1();
            controlGamepad2();
            debug();
        }
        Utils.deinnit();
    }
}