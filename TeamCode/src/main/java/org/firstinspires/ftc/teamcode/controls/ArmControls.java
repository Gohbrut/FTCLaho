package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.Constants;

public class ArmControls {

//    private DcMotor motor1;

    private Servo servoLowerArm;
    private Servo servoUpperArm;

    public ArmControls(HardwareMap hardwareMap) {
        this.servoLowerArm = hardwareMap.get(Servo.class, Constants.SERVO_LOWER_ARM);
        this.servoUpperArm = hardwareMap.get(Servo.class, Constants.SERVO_UPPER_ARM);
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

        servoLowerArm.setDirection(forward ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
        servoLowerArm.setPosition(position);

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

        servoUpperArm.setDirection(forward ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
        servoUpperArm.setPosition(position);

        return true;
    }
}
