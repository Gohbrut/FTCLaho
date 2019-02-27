package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.utils.Defines;

@Autonomous(name = "Robocop")
public class Auto extends LinearOpMode
{
    private Controls controls;
    private Servo claimServo;
    public static int startAngle;
    public static float startAnglePrecise;
    private final boolean startCrater = false;

    private void debug()
    {
        //telemetry.addData("Start angle: ", startAngle);
        //telemetry.addData("Angle: ", (int)controls.getNormalizedAngle());
        //telemetry.addData("Distance: ", controls.getDistance());
        //telemetry.addData("Yellow: ", controls.getColor().isYellow());
        //`telemetry.update();
    }

    private void park()
    {
        controls.goLeft(0.8);
        sleep(500);
        controls.goLeft(0);
        controls.collectorControls.extend(0);
        sleep(2000);
        controls.collectorControls.extend(0.5);
    }

    private void claim()
    {
        controls.goLeft(0.8);
        sleep(950);
        controls.goLeft(0);
        controls.rotateClockwise(1);
        sleep(500);
        controls.rotateClockwise(0);
        //
        claimServo.setPosition(0.0);
        sleep(1500);
        claimServo.setPosition(0.5);
        controls.rotateCounterClockwise(1);
        sleep(500);
        controls.rotateCounterClockwise(0);
        controls.goRight(1);
        sleep(300);
        controls.goRight(0);
    }

    private void rotate(int angle)
    {

    }

    private void land()
    {
        controls.goUp(1);
        sleep(13000);
        controls.goUp(0);
        sleep(4000);
        controls.rotateClockwise(1);
        sleep(50);
        controls.rotateClockwise(0);
        controls.goFrontward(1);
        sleep(150);
        controls.goFrontward(0);
        controls.goLeft(1);
        sleep(750);
        controls.goLeft(0);
        controls.goBackward(1);
        sleep(200);
        controls.goBackward(0);

        //
        //controls.goRight(1);
        //sleep(750);
        //controls.goRight(0);
        //controls.goFrontward(1);
        //sleep(1000);
        //controls.goFrontward(0);
    }

    private void setup()
    {
        controls = new Controls(hardwareMap);
        claimServo = hardwareMap.get(Servo.class, "Servo claim");
        waitForStart();
        controls.resetEngine();
    }

    public void runOpMode()
    {
        setup();
        land();
        //sample();
        if(startCrater)
            park();
        else
            claim();
    }
}
