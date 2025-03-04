# How to Use This
This repository is a fork of the [ftcRobotControler](https://github.com/FIRST-Tech-Challenge/FtcRobotController). This repository has extra skeleton code to have a head start when starting the season.

The two repositories that fork from this are [18625-Trivium-Knightmares](https://github.com/Trivium-Prep-Robotics/18625-Trivium-Knightmares) and [14254-Trivium-Knightlights](https://github.com/Trivium-Prep-Robotics/14254-Trivium-Knightligths)

![image](https://github.com/user-attachments/assets/6c02b179-7de0-48e2-8849-99d6d2f8d264)

## Updating this Repo
As of right now this repo is ahead
![image](https://github.com/user-attachments/assets/11f62a3e-fc08-4635-81d9-bf846b843240)
But if it is ever behind you need update this repo

![image](https://github.com/user-attachments/assets/c7c4e973-367f-4cb0-b707-4ad3ff2c1b4e)

- To start you can use the git command `git remote -v` to check that you have the upstream set.

  - If you get this:

![image](https://github.com/user-attachments/assets/9e8fddb2-db40-4c07-9a16-f21c25ec8c66)

Your good, but if you got this:

![image](https://github.com/user-attachments/assets/d4d73de4-ac05-4120-919c-118643cdd72b)

Type `git remote add upstream https://github.com/FIRST-Tech-Challenge/FtcRobotController` and then when you do that again you can check with `git remote -v`

- The next step is to download the recent changes into the local Git base. To do this use `git fetch upstream`.

- Next step is to use `git merge upstream/master --commit -m "merge from upstream"`

- Perfect. To finalize it just use `git push origin master`

The command line should look like this:
```
git remote add upstream https://github.com/FIRST-Tech-Challenge/FtcRobotControllerhttps://github.com/FIRST-Tech-Challenge/FtcRobotController
git fetch upstream
git merge upstream/main --commit -m "merge from upstream"
git push origin main
```

## What is in this repo?
This is good skeleton code to start the season.

- The [teleOp folder](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/teleOp) already has skeleton teleOp code that already has field centric drive and imports the parts, arm, claw, and drive classes that are used.
- The [auto folder](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/teleOp) already has skeleton auto code as well.

### [Parts](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Parts.java)
This class has the robot configured in it. 
- The drive train motors all you have to do is change their name to what is in the control hub configuration.
- Has other motors as well like piv1, piv2, and slides
  - Simply delete this and add in the motors you use, these are what our robot had and I left them as an example of other motors
- Servo
  - We have the claw and wrist servo, again delete these and put any servos that you use instead.
 - I also have variables in there and you can put any that you want to use throughout the code it just has to be public static to be accessible outside of the class
### [util](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util)
Interfaces, Robot class, and other util
#### We have:
- [[Arm interface]((https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Arm.java)
  - Methods for a robot with pivot and extend
- [Claw interface](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/parts/Claw.java)
  - Methods for a claw with a wrist
- [Drive interface](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Drive.java) drive methods.
- [Robot](https://github.com/Trivium-Prep-Robotics/Trivium-Robotics/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/util/Robot.java) is the class with all the methods, implement all the interfaces you use, and define all of them (IF YOU DON'T USE THEM LEAVE THEM BLANK, BUT STILL HAVE THEM).
- Add any interfaces and classes you would like depending on what your robot is. The one I would recommend using generally all the time is the drive one until you are advanced enough to use something such as RoadRunner or Pedro Pathing.
- If you make your own interfaces be sure to add the interface here.
- Also add any extra methods to the interfaces if you have extra, for example if your arm has an elbow, add those methods

# Coding sources
- [w3schools java](https://www.w3schools.com/java/default.asp) - great source for understanding java concepts.
- [Codecademy java course](https://www.codecademy.com/learn/learn-java) - if you want to take an intro course to java.
- [Game Manual 0 - software](https://gm0.org/en/latest/docs/software/index.html#) - this is an amazing source for FTC specific coding basics, concepts, etc. Even outside of the software section gm0 is a great source for everything FTC.
- [Pedro Pathing](https://pedropathing.com/) - this is the documentation for Pedro Pathing.
- [RoadRunner](https://rr.brott.dev/docs/v1-0/installation/) - documentation for RoadRunner use (at the moment this documentation sucks).
- [Grok 3](https://grok.com/) - this is just an AI that helped me a lot even in the process of making this lol.
- [Programmer's journal](https://docs.google.com/document/d/1O9rBL9iYiBHXd6xWO5uZ8Y3aDJPRSqbhFk_Z6YAwZxg/edit?usp=sharing) - I never used this, and I regret it. Basically if you have any unique issues you could check here to see if it has ever happened, but also if you solve your issue add it here and how you  fixed it.
