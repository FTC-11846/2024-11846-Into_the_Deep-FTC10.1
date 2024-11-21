package com.example.meepmeeptest;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(90), Math.toRadians(90), 18)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-24.5, 70.5, Math.toRadians(90)))
                        .forward(-35)
                        .strafeRight(15)
                        .forward(-5)
                        .forward(5)

//                        .strafeLeft(39)
//                        .forward(20)
//                        .turn(Math.toRadians(180))
//                        .strafeRight(10)
//                        .forward(25)
//                        .forward(-25)
//                        .strafeRight(10)
//                        .forward(25)
//                        .forward(-25)

                        //deliver pixel to human player with bucket possibly x3
                        //pickup sample x3
                        //score
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}