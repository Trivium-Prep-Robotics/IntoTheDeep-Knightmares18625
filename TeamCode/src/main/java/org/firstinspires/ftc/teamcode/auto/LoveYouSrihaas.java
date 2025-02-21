package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.StateArm;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.LConstants;

@Config
@Autonomous(name = "Srihaas, your so kissable", group = "AUTO")
public class LoveYouSrihaas extends LinearOpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    public static final Pose startPose = new Pose(5.000, 63.00, Math.toRadians(0));
    public static final Pose scorePose = new Pose(39.000, 63.00, Math.toRadians(0));

    public static double highChamber = 0.20;
    public static int extend = -1;
    public static double pull = 0.16;
    public static double wall = 0.10;

    private Path scorePreload, back, grabOne, specOne, turnAgain;

    @Override
    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap);
        StateArm arm = new StateArm();
        GearClaw claw = new GearClaw();


        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();

        claw.openClosePose(0.375, 0.8); // I'm never letting jacob r touch this code again
        claw.sampSpecPose(0.2, 0.4);

        arm.armPower(1);
        arm.extendPower(1);

        claw.grabs();
        claw.specimen();


        waitForStart();

        arm.setArm(highChamber);
        arm.setSlide(extend);

        follower.followPath(scorePreload);
        while (follower.isBusy()) {
            follower.update();
        }

        arm.setArm(pull);
        claw.drops();
        arm.setSlide(0);
        arm.setArm(wall);
        claw.specimen();

        follower.followPath(back);
        while (follower.isBusy()) {
            follower.update();
        }

        follower.followPath(grabOne);
        while (follower.isBusy()) {
            follower.update();
        }

        claw.grabs();
        sleep(1000);

        arm.setArm(highChamber);
        arm.setSlide(extend);

        follower.followPath(turnAgain);
        while (follower.isBusy()) {
            follower.update();
        }

        follower.followPath(specOne);
        while (follower.isBusy()) {
            follower.update();
        }

        arm.setArm(pull);
        claw.drops();
        arm.setSlide(0);
        arm.setArm(0);


    }

    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(scorePose)));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(0));

        back = new Path(new BezierLine(new Point(scorePose), new Point(30, 63, Point.CARTESIAN)));
        back.setConstantHeadingInterpolation(Math.toRadians(0));

        grabOne = new Path(new BezierLine(new Point(30, 63, Point.CARTESIAN), new Point(8.000, 5.000, Point.CARTESIAN)));
        grabOne.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-90));

        turnAgain = new Path(new BezierLine(new Point(35.000, 23.000, Point.CARTESIAN), new Point(20.000, 23.000, Point.CARTESIAN)));
        turnAgain.setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(0));

        specOne = new Path(new BezierLine(new Point(15.000, 23.000, Point.CARTESIAN), new Point(scorePose)));
        specOne.setConstantHeadingInterpolation(Math.toRadians(0));
    }
}