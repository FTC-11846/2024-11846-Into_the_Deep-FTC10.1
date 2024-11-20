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
                .setConstraints(60, 60, Math.toRadians(270), Math.toRadians(270), 18)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-24.5, 70.5, Math.toRadians(270)))
                        .forward(35)
                        .strafeLeft(15)
                        .forward(5)
                        .forward(-5)
                        .strafeRight(39)
                        .forward(-20)
                        //pickup sample
                        //score
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}