package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.controls.Controls;
import org.firstinspires.ftc.teamcode.utils.Defines;
import org.firstinspires.ftc.teamcode.utils.Song;
import org.firstinspires.ftc.teamcode.utils.Utils;

@Autonomous(name = "Robocop")
public class Auto extends LinearOpMode
{
    private Controls controls;
    private Servo claimServo;
    private final boolean startCrater = false;
    private int location = -1;
    private int dist = 400;

    private void debug()
    {
        //telemetry.addData("Start angle: ", startAngle);
        //telemetry.addData("Angle: ", (int)controls.getNormalizedAngle());
        //telemetry.addData("Distance: ", controls.getDistance());
        //telemetry.addData("Yellow: ", controls.getColor().isYellow());
        telemetry.addData("Location; ", location);
        telemetry.update();
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
        if(location == 1)
        {
            controls.rotateClockwise(1);
            sleep(100);
            controls.rotateClockwise(0);
        }
        else if(location == 2)
        {
            controls.rotateCounterClockwise(1);
            sleep(100);
            controls.rotateCounterClockwise(0);
        }
        controls.goLeft(1);
        sleep(550);
        controls.goLeft(0);
        claimServo.setPosition(0.0);
        sleep(1500);
        claimServo.setPosition(0.5);
        //Song song = new Song(hardwareMap.appContext, R.raw.simonel);
        //song.play();0
        if(location == 1)
        {
        }
        else if(location == 2)
        {
            controls.rotateClockwise(1);
            sleep(300);
            controls.rotateClockwise(0);
        }
        else
        {
            controls.rotateClockwise(1);
            sleep(200);
            controls.rotateClockwise(0);
        }
        controls.goBackward(1);
        sleep(3750);
        controls.goBackward(0);
        /*controls.goLeft(0.8);
        sleep(950);
        controls.goLeft(0);
        if(location == 2)
        {
            controls.goFrontward(1);
            sleep(dist);
            controls.goFrontward(0);
        }
        else if(location == 1)
        {
            controls.goBackward(1);
            sleep(dist);
            controls.goBackward(0);
        }
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
        controls.goRight(0);*/
    }

    private void land()
    {
        //controls.goUp(1);
        //sleep(13000);
        //controls.goUp(0);
        //controls.rotateClockwise(1);
        //sleep(50);
        //controls.rotateClockwise(0);
        sample();
        controls.goFrontward(1);
        sleep(50);
        controls.goFrontward(0);
        controls.goLeft(1);
        sleep(400);
        controls.goLeft(0);
        controls.goBackward(1);
        sleep(50);
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
        Utils.init(hardwareMap);
        controls = new Controls(hardwareMap);
        claimServo = hardwareMap.get(Servo.class, "Servo claim");
        waitForStart();
        controls.resetEngine();
    }

    private void x()
    {
        if(location == 1)
        {
            controls.goFrontward(1);
            sleep(dist);
            controls.goFrontward(0);
        }
        else if(location == 2)
        {
            controls.goBackward(1);
            sleep(dist);
            controls.goBackward(0);
        }
        controls.goLeft(1);
        sleep(1100);
        controls.goLeft(0);
    }

    private void sample()
    {
        int left = 0;
        int center = 0;
        int right = 0;
        int time = 700;
        for(int i = 0; i < time; i++)
        {
            int res = Utils.getGoldLocation();
            if(res == Defines.MINERAL_LOCATION_CENTER)
                center++;
            else if(res == Defines.MINERAL_LOCATION_LEFT)
                left++;
            else if(res == Defines.MINERAL_LOCATION_RIGHT)
                right++;
            sleep(1);
        }
        /*controls.rotateClockwise(0.5);
        sleep(25);
        controls.rotateClockwise(0);
        for(int i = 0; i < time; i++)
        {
            int res = Utils.getGoldLocation();
            if(res == Defines.MINERAL_LOCATION_CENTER)
                center++;
            else if(res == Defines.MINERAL_LOCATION_LEFT)
                left++;
            else if(res == Defines.MINERAL_LOCATION_RIGHT)
                right++;
            sleep(1);
        }
        controls.rotateCounterClockwise(0.5);
        sleep(50);
        controls.rotateCounterClockwise(0);
        for(int i = 0; i < time; i++)
        {
            int res = Utils.getGoldLocation();
            if(res == Defines.MINERAL_LOCATION_CENTER)
                center++;
            else if(res == Defines.MINERAL_LOCATION_LEFT)
                left++;
            else if(res == Defines.MINERAL_LOCATION_RIGHT)
                right++;
            sleep(1);
        }
        controls.rotateClockwise(0.5);
        sleep(25);
        controls.rotateClockwise(0);*/
        if(left > right && left > center)
            location = 1;
        else if(right > left && right > center)
            location = 2;
    }

    public void runOpMode()
    {
        setup();
        land();
        debug();
        x();
        claim();
        Utils.deinnit();
    }
}
