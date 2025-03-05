# How to Use This
This repository is a fork of the [Trivium-Robotics](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/tree/master) repo. This is Knightmares' code

## Updating this Repo

To start use the command `git remote -v` to check that the upstream is set, if not then use: `git remote add upstream https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/tree/master` to set it. Again just double check with `git remote -v`

- The next step is to download the recent changes into the local Git base. To do this use `git fetch upstream`.

- Next step is to use `git merge upstream/master --no-commit`
  - We don't commit because we are going to restore the README.md file
  - To do this we use `git checkout HEAD -- TeamCode\src\main\java\org\firstinspires\ftc\teamcode\README.md`
  - If you have any other files/folders to restore you can use the same command for those
- Once you are ready to commit use `git commit -m "message"`
- Perfect. To finalize it just use `git push origin master`

The command line should look like this:
```
git remote add upstream https://github.com/FIRST-Tech-Challenge/FtcRobotControllerhttps://github.com/FIRST-Tech-Challenge/FtcRobotController
git fetch upstream
git merge upstream/main --no-commit
git checkout HEAD -- TeamCode\src\main\java\org\firstinspires\ftc\teamcode\README.md
git commit -m "message"
git push origin main
```

## What is in this repo?
This is good skeleton code to start the season.

- The [teleOp folder](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/teleOp) already has skeleton teleOp code that already has field centric drive and imports the parts, arm, claw, and drive classes that are used.
- The [auto folder](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/auto) already has skeleton auto code as well.

### [util](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util)
Interfaces, Robot class, and other util

#### [Parts](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Parts.java)
This class has the robot configured in it. 
- The drive train motors all you have to do is change their name to what is in the control hub configuration.
- Has other motors as well like piv1, piv2, and slides
  - Simply delete this and add in the motors you use, these are what our robot had and I left them as an example of other motors
- Servo
  - We have the claw and wrist servo, again delete these and put any servos that you use instead.
 - I also have variables in there and you can put any that you want to use throughout the code it just has to be public static to be accessible outside of the class
#### We have:
- [Arm interface](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Arm.java)
  - Methods for a robot with pivot and extend
- [Claw interface](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Claw.java)
  - Methods for a claw with a wrist
- [Drive interface](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Drive.java) drive methods.
- [Robot](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Robot.java) is the class with all the methods, implement all the interfaces you use, and define all of them (IF YOU DON'T USE THEM LEAVE THEM BLANK, BUT STILL HAVE THEM).
- Add any interfaces and classes you would like depending on what your robot is. The one I would recommend using generally all the time is the drive one until you are advanced enough to use something such as RoadRunner or Pedro Pathing.
- Also add any extra methods to the interfaces if you have extra, for example if your arm has an elbow, add those methods

### Past years
You'll notice that there are folders named after past seasons, this is simply the old code for those seasons

# Coding sources
- [w3schools java](https://www.w3schools.com/java/default.asp) - great source for understanding java concepts.
- [Codecademy java course](https://www.codecademy.com/learn/learn-java) - if you want to take an intro course to java.
- [Game Manual 0 - software](https://gm0.org/en/latest/docs/software/index.html#) - this is an amazing source for FTC specific coding basics, concepts, etc. Even outside of the software section gm0 is a great source for everything FTC.
- [Pedro Pathing](https://pedropathing.com/) - this is the documentation for Pedro Pathing.
- [RoadRunner](https://rr.brott.dev/docs/v1-0/installation/) - documentation for RoadRunner use (at the moment this documentation sucks).
- [Grok 3](https://grok.com/) - this is just an AI that helped me a lot even in the process of making this lol.
- [Programmer's journal](https://docs.google.com/document/d/1O9rBL9iYiBHXd6xWO5uZ8Y3aDJPRSqbhFk_Z6YAwZxg/edit?usp=sharing) - I never used this, and I regret it. Basically if you have any unique issues you could check here to see if it has ever happened, but also if you solve your issue add it here and how you  fixed it.
