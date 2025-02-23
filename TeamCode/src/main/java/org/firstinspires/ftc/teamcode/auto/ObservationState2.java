package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.localization.localizers.PinpointLocalizer;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.StateArm;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.LConstants;

@Config
@Autonomous(name = "Observation State 2", group = "AUTO")
public class ObservationState2 extends OpMode {
    private Follower follower;
    Parts robot;
//    StateArm arm;
//    GearClaw claw;
    private Timer pathTimer, actionTimer, opmodeTimer;
    /** This is the variable where we store the state of our auto.
     * It is used by the pathUpdate method. */
    private int pathState;

    /* Create and Define Poses + Paths
     * Poses are built with three constructors: x, y, and heading (in Radians).
     * Pedro uses 0 - 144 for x and y, with 0, 0 being on the bottom left.
     * (For Into the Deep, this would be Blue Observation Zone (0,0) to Red Observation Zone (144,144).)
     * Even though Pedro uses a different coordinate system than RR, you can convert any roadrunner pose by adding +72 both the x and y.
     * This visualizer is very easy to use to find and create paths/pathchains/poses: <https://pedro-path-generator.vercel.app/>
     * Lets assume our robot is 18 by 18 inches
     * Lets assume the Robot is facing the human player and we want to score in the bucket */


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
    public static int extend = -1;
    public static double pull = 0.10;
    public static double wall = 0.10;



    private Path scorePreload, park;
    private PathChain pushOne, pushTwo, pushThree, specOneGet, specOne;

    public void buildPaths() {

        /* There are two major types of paths components: BezierCurves and BezierLines.
         *    * BezierCurves are curved, and require >= 3 points. There are the start and end points, and the control points.
         *    - Control points manipulate the curve between the start and end points.
         *    - A good visualizer for this is [this](https://pedro-path-generator.vercel.app/).
         *    * BezierLines are straight, and require 2 points. There are the start and end points.
         * Paths have can have heading interpolation: Constant, Linear, or Tangential
         *    * Linear heading interpolation:
         *    - Pedro will slowly change the heading of the robot from the startHeading to the endHeading over the course of the entire path.
         *    * Constant Heading Interpolation:
         *    - Pedro will maintain one heading throughout the entire path.
         *    * Tangential Heading Interpolation:
         *    - Pedro will follows the angle of the path such that the robot is always driving forward when it follows the path.
         * PathChains hold Path(s) within it and are able to hold their end point, meaning that they will holdPoint until another path is followed.
         * Here is a explanation of the difference between Paths and PathChains <https://pedropathing.com/commonissues/pathtopathchain.html> */

        /* This is our scorePreload path. We are using a BezierLine, which is a straight line. */
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(scorePose)));
        scorePreload.setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading());

        pushOne = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(scorePose), new Point(oneBack), new Point(oneUp), new Point(onePush)))
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))
                .build();

        pushTwo = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(onePush), new Point(twoUp), new Point(twoPush)))
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();

        pushThree = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(twoPush), new Point(threeUp), new Point(threeCurve), new Point(threePush)))
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();

        specOneGet = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(threePush), new Point(specOneBack), new Point(specOnePic)))
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .build();

        specOne = follower.pathBuilder()
                .addPath(new BezierLine(new Point(specOnePic), new Point(SpecOnePlace)))
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .build();
    }

    private void autonomousPathUpdate() {
        switch (pathState) {
            case 0:

//                arm.setArm(highChamber);
//                arm.setSlide(extend);

                follower.followPath(scorePreload);
                setPathState(1);

                break;
            case 1:

                if(!follower.isBusy()) {
//                    arm.setArm(pull);
//                    claw.drops();
//                    arm.setSlide(0);
//                    arm.setArm(wall);

                    follower.followPath(pushOne,true);
                    setPathState(2);
                }
                break;
            case 2:
                if(!follower.isBusy()) {
//                    claw.grabs();
                    follower.followPath(pushTwo, true);
//                    arm.setArm(highChamber);
//                    arm.setSlide(extend);
//                    arm.setArm(pull);
//                    claw.drops();
//                    arm.setSlide(0);
                    setPathState(3);
                }
                break;

            case 3:
                if(!follower.isBusy()) {
                    follower.followPath(pushThree, true);
                    setPathState(4);
                }
                break;

            case 4:
                if(!follower.isBusy()) {
                    follower.followPath(specOneGet, true);
                    setPathState(5);
                }
                break;

            case 5:
                if(!follower.isBusy()) {
                    follower.followPath(specOne, true);
                    setPathState(6);
                }
                break;

            case 6:
                if(!follower.isBusy()) {
                    setPathState(-1);
                }
        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();

        telemetry.addData("Path State", pathState);
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", follower.getPose().getHeading());
        telemetry.update();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        robot = new Parts(hardwareMap);
//        arm = new StateArm();
//        claw = new GearClaw();

//        claw.openClosePose(0.375, 0.8); // I'm never letting jacob r touch this code again
//        claw.sampSpecPose(0.2, 0.4);

//        arm.armPower(1);
//        arm.extendPower(1);

//        claw.grabs();
//        claw.specimen();

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    @Override
    public void stop() {
    }
}