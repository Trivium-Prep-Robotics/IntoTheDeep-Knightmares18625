package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
@Autonomous(name = "Observation State", group = "AUTO")
public class ObservationState extends LinearOpMode {
    private Follower follower;
    public static final Pose startPose = new Pose(8.000, 63.00, Point.CARTESIAN);
    public static final Pose scorePose = new Pose(39.000, 63.00, Point.CARTESIAN);

    public static Pose oneBack = new Pose(16.000, 23.000, Point.CARTESIAN);
    public static Pose oneUp = new Pose(129.000, 29.000, Point.CARTESIAN);
    public static Pose onePush = new Pose(14.000, 25.000, Point.CARTESIAN);

    public static Pose twoUp = new Pose(122.00, 23.00, Point.CARTESIAN);
    public static Pose twoPush = new Pose(18.00, 10.00, Point.CARTESIAN);

    public static Pose threeUp = new Pose(125.00, 16.00, Point.CARTESIAN);
    public static Pose threeCurve = new Pose(95.044, 0.205, Point.CARTESIAN);
    public static Pose threePush = new Pose(18.00, 8.00, Point.CARTESIAN);

    public static Pose specOneBack = new Pose(75.000, 28.000, Point.CARTESIAN);
    public static Pose specOnePic = new Pose(14.00, 24.00, Point.CARTESIAN);
    public static Pose SpecOnePlace = new Pose(38.304, 64.114, Point.CARTESIAN);

    public static double highChamber = 0.15;
    public static int extend = 3;
    public static double pull = 0.14;
    public static double wall = 0.10;



    private Path scorePreload, park;
    private PathChain push, specOne;

    @Override
    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap);
        StateArm arm = new StateArm();
        GearClaw claw = new GearClaw();

        Constants.setConstants(FConstants.class, LConstants.class);

        claw.openClosePose(0.375, 0.8); // I'm never letting jacob r touch this code again
        claw.sampSpecPose(0.2, 0.4);

        arm.armPower(1);
        arm.extendPower(1);

        claw.grabs();
        claw.specimen();

        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);

        waitForStart();

        arm.setArm(highChamber);
        arm.setSlide(extend);

        follower.followPath(scorePreload);

        arm.setArm(pull);
        claw.drops();
        arm.setSlide(0);
        arm.setArm(wall);

        follower.followPath(push);

        claw.grabs();

        follower.followPath(specOne);
        arm.setArm(highChamber);
        arm.setSlide(extend);
        arm.setArm(pull);
        claw.drops();
        arm.setSlide(0);

    }

    public void buildPath() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(scorePose)));
        scorePreload.setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading());

        push = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(scorePose),
                                new Point(oneBack),
                                new Point(oneUp),
                                new Point(onePush)
                        )
                )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))
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
                .build();

    }
}