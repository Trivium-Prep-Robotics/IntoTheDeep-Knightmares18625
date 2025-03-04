package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Parts {

    // declaring parts
    // TODO: get rid of and/or add any parts you need
    public static DcMotor FR, FL, BR, BL;
    public static DcMotor piv1, piv2;
    public static DcMotor slide;
    public static Servo claw, wrist;


    public static IMU imu;
    IMU.Parameters myIMUparameters;

    /**
     * important variables
     */

    public static double armPower;
    public static double extendPower;
    public static double driveMaxSpd = 1;
    public static double driveSlwSpd = 0.5;

    public static double openClaw;
    public static double closeClaw;

    public static double sample;
    public static double specimen;

    /**
     * You can put any other varaibles you'd like to here as well, just make sure they are public static
     * I'd recommend stuff like the following:
     */
    public static double ticksPerRev; // for encoders
    public static double armGearRatio; // if you use gears for example

    public Parts(HardwareMap hardwareMap) {


        // TODO: assign drive train names here
        FR = hardwareMap.get(DcMotor.class, "rightFront");
        FL = hardwareMap.get(DcMotor.class, "leftFront");
        BR = hardwareMap.get(DcMotor.class, "rightRear");
        BL = hardwareMap.get(DcMotor.class, "leftRear");

        // TODO: reverse any motors that go the opposite direction
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        // when setPower(0) -> motors brake
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // TODO: assigning other motors used here
        piv1 = hardwareMap.get(DcMotor.class, "pivotOne");
        piv2 = hardwareMap.get(DcMotor.class, "pivotTwo");
        slide = hardwareMap.get(DcMotor.class, "slide");

        // TODO: reverse any motors you need
        // we had two motors for our pivot
        piv1.setDirection(DcMotorSimple.Direction.REVERSE);
        piv2.setDirection(DcMotorSimple.Direction.REVERSE);

        // if you use encoders this is needed
        piv1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        piv2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        piv1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        piv2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // when setPower(0) -> motors brake
        piv1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        piv2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // TODO: assigning servos here
        claw = hardwareMap.get(ServoImplEx.class, "claw");
        wrist = hardwareMap.get(ServoImplEx.class, "wrist");

        // TODO: assign and set up IMU here
        imu = hardwareMap.get(IMU.class, "imu");
        // Reconfiguring IMU orientation
        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        // TODO: this is what you'll need to change this is for the orientation of your control hub
                        RevHubOrientationOnRobot.LogoFacingDirection.UP, // what direction the logo is facing
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT // what direction the USB is facing
                )
        );
        imu.initialize(myIMUparameters);
        imu.resetYaw();
    }
}