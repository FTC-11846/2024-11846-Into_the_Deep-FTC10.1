package org.firstinspires.ftc.teamcode.Opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ElevatorSubsystem;

@TeleOp(name = "Teleop", group = "TeleOp")
public class Teleop extends OpMode {

    MecanumSubsystem mecanumSubsystem;
    ElevatorSubsystem elevatorSubsystem;
    IntakeSubsystem intakeSubsystem;

    @Override
    public void init(){
        mecanumSubsystem = new MecanumSubsystem(hardwareMap);
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

    }

    @Override
    public void loop(){

        double forward = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;
        double lift = gamepad2.left_stick_y;
        double extend = -gamepad2.right_stick_y;

        if(gamepad2.left_trigger >= 0.1){
            intakeSubsystem.intake(-1);
        } else if(gamepad2.right_trigger >= 0.1){
            intakeSubsystem.intake(1);
        } else {
            intakeSubsystem.intake(0);
        }

        if(gamepad2.x){
            intakeSubsystem.sampleDropper(0.01);
        }else if(gamepad2.y){
            intakeSubsystem.sampleDropper(0.89);
        }


        if(gamepad2.dpad_down){
            elevatorSubsystem.openHang();
        }else {
            elevatorSubsystem.hang();
        }

        if(gamepad2.left_bumper){
            intakeSubsystem.specimenGrabIncrement(0.1);
        }else if(gamepad2.right_bumper){
            intakeSubsystem.specimenGrabIncrement(-0.1);
        }

        mecanumSubsystem.TeleOperatedDrive(forward, -strafe, turn);
        elevatorSubsystem.elevatorLift(lift);
        intakeSubsystem.extend(extend);

        telemetry.addData("ElevatorLCounts", elevatorSubsystem.ElevatorLCounts());
        telemetry.addData("ElevatorRCounts", elevatorSubsystem.ElevatorRCounts());
        telemetry.addData("lr",mecanumSubsystem.encoderDrivelr());
        telemetry.addData("lf",mecanumSubsystem.encoderDrivelf());
        telemetry.addData("rr",mecanumSubsystem.encoderDriverrr());
        telemetry.addData("rf",mecanumSubsystem.encoderDriverrf());
        telemetry.addData("specimenGrabberPosition",intakeSubsystem.specimenGrabPosition());
        telemetry.addData("extensionEncoderPosition", intakeSubsystem.extensionEncoderCounts());
        telemetry.update();
    }

}
