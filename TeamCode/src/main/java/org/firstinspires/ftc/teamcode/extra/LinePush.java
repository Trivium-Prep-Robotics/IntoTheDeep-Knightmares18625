package org.firstinspires.ftc.teamcode.extra;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.StateArm;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.LConstants;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Point;

@Config
@Autonomous(name = "Push with line paths", group = "extra")
@Disabled
public class LinePush extends LinearOpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    public static final Pose startPose = new Pose(8.000, 63.00, Math.toRadians(0));
    public static final Pose scorePose = new Pose(39.000, 63.00, Math.toRadians(0));

    public static Pose oneBack = new Pose(16.000, 23.000, Math.toRadians(90));
    public static Pose oneUp = new Pose(129.000, 29.000, Math.toRadians(90));
    public static Pose onePush = new Pose(14.000, 25.000, Math.toRadians(90));

    public static Pose twoUp = new Pose(122.00, 23.00, Math.toRadians(90));
    public static Pose twoPush = new Pose(18.00, 10.00, Math.toRadians(90));

    public static Pose threeUp = new Pose(125.00, 16.00, Math.toRadians(90));
    public static Pose threeCurve = new Pose(95.044, 0.205, Math.toRadians(90));
    public static Pose threePush = new Pose(18.00, 8.00, Math.toRadians(90));

    public static Pose specOneBack = new Pose(75.000, 28.000, Math.toRadians(180));
    public static Pose specOnePic = new Pose(14.00, 24.00, Math.toRadians(180));
    public static Pose SpecOnePlace = new Pose(38.304, 64.114, Point.CARTESIAN);

    public static double highChamber = 0.15;
    public static int extend = -1;
    public static double pull = 0.10;
    public static double wall = 0.05;



    private Path scorePreload, push1, push2, push3, push4, push5, push6, push7, push8, park;
    private PathChain specOne;

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

        follower.followPath(push1, true);
        while (follower.isBusy()) {
            follower.update();
        }
        follower.followPath(push2, true);
        while (follower.isBusy()) {
            follower.update();
        }
        follower.followPath(push3, true);
        while (follower.isBusy()) {
            follower.update();
        }
        follower.followPath(push4, true);
        while (follower.isBusy()) {
            follower.update();
        }
        follower.followPath(push5, true);
        while (follower.isBusy()) {
            follower.update();
        }
        follower.followPath(push6, true);
        while (follower.isBusy()) {
            follower.update();
        }
        follower.followPath(push7, true);
        while (follower.isBusy()) {
            follower.update();
        }
        follower.followPath(push8, true);
        while (follower.isBusy()) {
            follower.update();
        }

//        claw.grabs();

//        follower.followPath(specOne);
//        arm.setArm(highChamber);
//        arm.setSlide(extend);
//        arm.setArm(pull);
//        claw.drops();
//        arm.setSlide(0);

    }

    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(scorePose)));
        scorePreload.setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading());

        push1 = new Path(new BezierLine(
                new Point(39.000, 63.000, Point.CARTESIAN),
                new Point(37.000, 40.000, Point.CARTESIAN)
        ));
        push1.setConstantHeadingInterpolation(scorePose.getHeading());
        push2 = new Path(new BezierLine(
                new Point(37.000, 40.000, Point.CARTESIAN),
                new Point(60.922, 24.580, Point.CARTESIAN)
        ));
        push2.setConstantHeadingInterpolation(scorePose.getHeading());
        push3 = new Path(new BezierLine(
                new Point(60.922, 24.580, Point.CARTESIAN),
                new Point(19.664, 22.122, Point.CARTESIAN)
        ));
        push3.setConstantHeadingInterpolation(scorePose.getHeading());
        push4 = new Path(new BezierLine(
                new Point(19.664, 22.122, Point.CARTESIAN),
                new Point(60.922, 22.942, Point.CARTESIAN)
        ));
        push4.setConstantHeadingInterpolation(scorePose.getHeading());
        push5 = new Path(new BezierLine(
                new Point(60.922, 22.942, Point.CARTESIAN),
                new Point(60.922, 15.568, Point.CARTESIAN)
        ));
        push5.setConstantHeadingInterpolation(scorePose.getHeading());
        push6 = new Path(new BezierLine(
                new Point(60.922, 15.568, Point.CARTESIAN),
                new Point(18.640, 14.134, Point.CARTESIAN)
        ));
        push6.setConstantHeadingInterpolation(scorePose.getHeading());
        push7 = new Path(new BezierLine(
                new Point(18.640, 14.134, Point.CARTESIAN),
                new Point(60.922, 12.495, Point.CARTESIAN)
        ));
        push7.setConstantHeadingInterpolation(scorePose.getHeading());
        push8 = new Path(new BezierLine(
                new Point(60.922, 12.495, Point.CARTESIAN),
                new Point(22.737, 10.856, Point.CARTESIAN)
        ));
        push8.setConstantHeadingInterpolation(scorePose.getHeading());

        /*push = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(scorePose),
                                new Point(oneBack),
                                new Point(oneUp),
                                new Point(onePush)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))
                .addPath(
                        // Line 3
                        new BezierCurve(
                                new Point(onePush),
                                new Point(twoUp),
                                new Point(twoPush)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .addPath(
                        // Line 4
                        new BezierCurve(
                                new Point(twoPush),
                                new Point(threeUp),
                                new Point(threeCurve),
                                new Point(threePush)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .addPath(
                        // Line 5
                        new BezierCurve(
                                new Point(threePush),
                                new Point(specOneBack),
                                new Point(specOnePic)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .build();

        specOne = follower.pathBuilder()
                .addPath(
                        // Line 6
                        new BezierLine(
                                new Point(specOnePic),
                                new Point(SpecOnePlace)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .build();*/

    }
}