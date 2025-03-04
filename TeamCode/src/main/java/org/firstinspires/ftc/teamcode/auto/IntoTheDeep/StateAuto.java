package org.firstinspires.ftc.teamcode.auto.IntoTheDeep;

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

import org.firstinspires.ftc.teamcode.util.IntoTheDeep.Parts;
import org.firstinspires.ftc.teamcode.util.IntoTheDeep.OurRobot;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.LConstants;

/**
 * This Auto scores the pre-load specimen picks up another one from the wall scores it and parks (observation side)
 * Used at state
 *
 * SOME OTHER NOTES:
 * This used Pedro Pathing and you may notice my pathing was not the best, for future it is better
 * to do it with OpMode instead of LinearOpMode as it seems have to loop through when pathing, as
 * you can see I had to put while loops for each path.
 *
 * Also I believe my centripetalScaling must have been off as turning and splines did not work well
 * I was forced to use straight paths and any angle more than 90 degrees would not work.
 *
 * All that said this scored us 43 points at state
 */
@Config
@Autonomous(name = "State Auto", group = "AUTO")
public class StateAuto extends LinearOpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    public static final Pose startPose = new Pose(5.000, 63.00, Math.toRadians(0));
    public static final Pose scorePose = new Pose(39.5, 63.00, Math.toRadians(0));

    // tick positions used for arm and slide motors
    public static double highChamber = 0.20; // for scoring high chamber
    public static double extend = -1.2; // for extending arm
    public static double pull = 0.13; // pulling specimen down on high chamber
    public static double wall = 0.093; // grabbing wall height

    private Path scorePreload, back, grabOne, specOne, turnAgain, park;

    @Override
    public void runOpMode() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        OurRobot robot = new OurRobot();


        // Pedro Pathing init
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();

        robot.openClosePose(0.4, 0.8); // set claw positions
        robot.sampSpecPose(0.075, 0.6); // set wrist positions

        robot.armPower(1); // set arm power
        robot.extendPower(1); // set extend power

        robot.grabs();
        robot.specimen();

        waitForStart();

        // ready the arm to place
        robot.setArm(highChamber);
        robot.setSlide(extend);

        // move to chamber
        follower.followPath(scorePreload);
        while (follower.isBusy()) {
            follower.update();
        }

        // place specimen
        robot.setArm(pull);
        robot.drops();
        sleep(1000);
        robot.specimen();

        // back up a little
        follower.followPath(back);
        while (follower.isBusy()) {
            follower.update();
        }

        // set arm to grab from wall
        robot.setArm(wall);
        robot.setSlide(0);

        // go to wall
        follower.followPath(grabOne);
        while (follower.isBusy()) {
            follower.update();
        }

        // grab specimen
        robot.grabs();
        sleep(1000);

        // ready arm for scoring
        robot.setArm(highChamber);
        robot.setSlide(extend);

        // turn to score rotation
        follower.followPath(turnAgain);
        while (follower.isBusy()) {
            follower.update();
        }

        // score specimen
        follower.followPath(specOne);
        while (follower.isBusy()) {
            follower.update();
        }


        // place specimen
        robot.setArm(pull);
        robot.drops();

        // back up a little
        follower.followPath(back);
        while (follower.isBusy()) {
            follower.update();
        }
        // park
        follower.followPath(park);
        while (follower.isBusy()) {
            follower.update();
        }

        // reset arm and slide
        robot.setSlide(0);
        robot.setSlide(0);


    }

    // building the paths
    public void buildPaths() {
        scorePreload = new Path(new BezierLine(new Point(startPose), new Point(scorePose)));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(0));

        back = new Path(new BezierLine(new Point(scorePose), new Point(30, 63, Point.CARTESIAN)));
        back.setConstantHeadingInterpolation(Math.toRadians(0));

        grabOne = new Path(new BezierLine(new Point(30, 63, Point.CARTESIAN), new Point(0,-2, Point.CARTESIAN)));
        grabOne.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-90));

        turnAgain = new Path(new BezierLine(new Point(35.000, 23.000, Point.CARTESIAN), new Point(20.000, 23.000, Point.CARTESIAN)));
        turnAgain.setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(0));

        specOne = new Path(new BezierLine(new Point(15.000, 23.000, Point.CARTESIAN), new Point(scorePose)));
        specOne.setConstantHeadingInterpolation(Math.toRadians(0));

        park = new Path(new BezierLine(new Point(scorePose), new Point(0, .2)));
        park.setConstantHeadingInterpolation(Math.toRadians(0));
    }
}