package org.firstinspires.ftc.teamcode.basics;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This is the basics of Motor and Servo coding
 */

@Autonomous(group="samples", name = "Auto code")
public class MotorServoMovement extends LinearOpMode {
    /**
     * This is where you declare all of your motors and servos
     * Here we have just our wheels and one servo
     */
    DcMotor frontRight, frontLeft, backRight, backLeft, motor;

    Servo servo1;

    public void runOpMode() throws InterruptedException {
        /**
         * Here we are setting the variables to the actual motor/servo
         * The name in the "" is the name of the motor/servo in the control hub
         */
        frontLeft = hardwareMap.dcMotor.get("Front Left");
        frontRight = hardwareMap.dcMotor.get("Front Right");
        backLeft = hardwareMap.dcMotor.get("Back Left");
        backRight = hardwareMap.dcMotor.get("Back Right");
        motor = hardwareMap.dcMotor.get("Motor");

        servo1 = hardwareMap.servo.get("Servo One");

        frontRight.setDirection(DcMotor.Direction.REVERSE); // You need this to keep them going the
        backRight.setDirection(DcMotor.Direction.REVERSE); // same direction.

        telemetry.addData(">", "Press Play to start op mode"); // This will be added to the drivers hub screen
        telemetry.update(); // Will update the driver hub screen so that the above will appear
        waitForStart(); // Everything after this will happen after the start button is pressed

        // HOW TO TELL A MOTOR TO MOVE

        motor.setPower(0.60);
        /*
        - first tell it what motor you are trying to access (in this case "motor")
        - second to tell it you want it to move it by putting ".setPower"
        - third you tell it how to move (the number in the parenthesis)
          > positive numbers make it go counter-clockwise, negative ones make it go clockwise
          > the number is the percentage of power it uses, 1/-1 is 100% 0 is 0%

        So in this case we are telling the motor named "motor" to go counter clockwise at 60% power
         */

        /**
         * This block of code is an example of going forward
         * It will go forward 100% for 10 seconds
         */

        frontRight.setPower(1); // This sets the power to 100%
        frontLeft.setPower(1); // The power is set in decimal form, so:
        backRight.setPower(1); // 1 = 100%, 0.80 = 80%, and so on
        backLeft.setPower(1);

        sleep(10000); // This sleeps for 10000 milliseconds (10 seconds), basically it won't read the next bit of code for 10 seconds

        frontRight.setPower(0); // This sets the power to 0% (stops it)
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        /**
         * This block of code is an example of turning inplace to the right
         *      - turning inplace: the center point of the turn is the center of the robot
         * It will turn right at 45% power for 2 seconds
         */

        frontRight.setPower(-0.45); // The right side of the robot goes backwards
        backRight.setPower(-0.45);
        frontLeft.setPower(0.45); // The left side of the robot goes forward at the same power
        backLeft.setPower(0.45); // which results in the robot turning inplace

        sleep(2000);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        /**
         * This block of code is an example of turning left about the left side of the robot
         *      - The center point of the turn will be the left side of the robot
         * It will turn left at 60% power for 5 seconds
         */

        frontRight.setPower(0.60); // The right side goes forward
        backRight.setPower(0.60);
        frontLeft.setPower(0); // The left side stays still
        backLeft.setPower(0); // which results in a wider turn left

        sleep(5000);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        /**
         * This block of code is an example of strafing
         *      - For a better explanation: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
         * This will move to the right at 80 % power for 7 seconds
         */

        frontRight.setPower(-0.80); // By having 2 wheels diagonal move the same direction
        backLeft.setPower(-0.80); // and the 2 others moving in the opposite direction
        backRight.setPower(0.80); // the robot will strafe
        frontLeft.setPower(0.80);

        sleep(7000);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        /**
         * Servos are similar to motors but they move much slower and are programmed differently
         * A common place to see servos are in claws
         *
         * Pretend for this example that the closed position is 0.95 and the open position is 0.05
         * The range of positions in a servo is 0-1
         */

        // This example closes the claw
        servo1.setPosition(0.95);

        // This example opens the claw
        servo1.setPosition(0.05);

        // Say you want to grab a bigger thing and so you want it too not close as much
        servo1.setPosition(0.65);

    }
}
