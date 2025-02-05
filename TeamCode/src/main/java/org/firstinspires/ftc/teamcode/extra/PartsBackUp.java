package org.firstinspires.ftc.teamcode.extra;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class PartsBackUp {

    // declaring parts
    public static DcMotor FR, FL, BR, BL;
    public static DcMotor piv1, piv2;
    public static DcMotor slide;
    public static Servo claw;

    public static IMU imu;
    IMU.Parameters myIMUparameters;

    public static int armLow;
    public static int armHigh;
    public static int slideLow;
    public static int slideHigh;

    public static int setArm = 0;
    public static int setSlide = 0;

    public PartsBackUp(HardwareMap hardwareMap) {

        // assigning drive train motors
        FR = hardwareMap.get(DcMotor.class, "rightFront");
        FL = hardwareMap.get(DcMotor.class, "leftFront");
        BR = hardwareMap.get(DcMotor.class, "rightBack");
        BL = hardwareMap.get(DcMotor.class, "leftBack");

        // reverse left side
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        // when setPower(0) -> motors brake
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        piv1 = hardwareMap.get(DcMotor.class, "pivot 1");
        piv2 = hardwareMap.get(DcMotor.class, "pivot 2");
//        piv1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        piv2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // reverse left
        piv2.setDirection(DcMotorSimple.Direction.REVERSE);

        slide = hardwareMap.get(DcMotor.class, "slide");
//        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // reverse
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
        // when setPower(0) -> motors brake
        piv1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        piv2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw = hardwareMap.get(Servo.class, "claw");

        // IMU
        imu = hardwareMap.get(IMU.class, "imu"); // Initializing IMU in Drivers Hub
        // Reconfiguring IMU orientation
        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );
        imu.initialize(myIMUparameters);
        imu.resetYaw();
    }
}
