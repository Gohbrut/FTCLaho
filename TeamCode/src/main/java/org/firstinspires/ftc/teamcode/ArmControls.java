package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmControls {

//    private DcMotor motor1;

    private Servo lowerArm;
    private Servo upperArm;

    public ArmControls(HardwareMap hardwareMap) {
        this.lowerArm = hardwareMap.get(Servo.class, Constants.LOWER_ARM);
        this.upperArm = hardwareMap.get(Servo.class, Constants.UPPER_ARM);
    }

    /**
     * Rotate the lower component of the arm
     * @param position The position to rotate at
     * @param forward Whether it should rotate forwards or backwards
     * @return true if the arm rotated, false if it has already reached the maximum rotation point
     */
    public boolean rotateLowerArm(double position, boolean forward) {
        if (position > Servo.MAX_POSITION || position < Servo.MIN_POSITION)
            return false;

        lowerArm.setDirection(forward ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
        lowerArm.setPosition(position);

        return true;
    }

    /**
     * Rotate the upper component of the arm
     * @param position The position to rotate at
     * @param forward Whether it should rotate forwards or backwards
     * @return true if the arm rotated, false if it has already reached the maximum rotation point
     */
    public boolean rotateUpperArm(double position, boolean forward) {
        if (position > Servo.MAX_POSITION || position < Servo.MIN_POSITION)
            return false;

        upperArm.setDirection(forward ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
        upperArm.setPosition(position);

        return true;
    }
}
