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

    public static final double grabInitPos = 0;
    public static final double grabSafePos =   0.3; //0.3
    public static final double grabActivePos = 0.6; //0.6
    public static final double dropperReceivePos = 0.01;
    public static final double dropperScorePos = 0.6;

    @Override
    public void init(){
        mecanumSubsystem = new MecanumSubsystem(hardwareMap);
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        intakeSubsystem.specimenGrabSetPosition(grabSafePos);
        elevatorSubsystem.openHang();
    }

    @Override
    public void loop(){

        double forward = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;
        double lift = gamepad2.left_stick_y;
        double extend = -gamepad2.right_stick_y;

        if(gamepad2.left_trigger >= 0.1){
            intakeSubsystem.intake(1);
        } else if(gamepad2.right_trigger >= 0.1){
            intakeSubsystem.intake(-1);
        } else {
            intakeSubsystem.intake(0);
        }

        if(gamepad2.x){
            intakeSubsystem.sampleDropper(-dropperReceivePos);
        }else if(gamepad2.y){
            intakeSubsystem.sampleDropper(-dropperScorePos);
        }

        // on a check current position and toggle to other position
        if(gamepad2.a) {
            int currentGrabPos = intakeSubsystem.specimenGrabGetPosition();

            //if currently active, set grabber to safe position
            if (currentGrabPos == 1) {
                intakeSubsystem.specimenGrabSetPosition(grabSafePos);
            } else if (currentGrabPos == 2) {
                intakeSubsystem.specimenGrabSetPosition(grabActivePos);
            }
        }
        // use function specimenGrabPosition to get current status of grabber position so we know whether
        //to set our position to active or safe
//        if(gamepad2.b) {
//            int  currentHangPos = elevatorSubsystem.hangGetPosition();
//
//            //if currently active, set grabber to safe position
//            if (currentHangPos == hangInitPo) {
//                elevatorSubsystem.openHang();
//            } else if (currentGrabPos == hangReadyPos) {
//                elevatorSubsystem.closeHang();
//            }
//        }




        if(gamepad2.b){
            elevatorSubsystem.openHang();
        }else if(gamepad2.dpad_down){
            elevatorSubsystem.hang();
        }

//        if(gamepad2.left_bumper){
//        }else if(gamepad2.right_bumper){
//        }

        mecanumSubsystem.TeleOperatedDrive(forward, -strafe, turn);
        elevatorSubsystem.elevatorLift(lift);
        intakeSubsystem.extend(extend);

        telemetry.addData("ElevatorLCounts", elevatorSubsystem.ElevatorLCounts());
        telemetry.addData("ElevatorRCounts", elevatorSubsystem.ElevatorRCounts());
        telemetry.addData("lr",mecanumSubsystem.encoderDrivelr());
        telemetry.addData("lf",mecanumSubsystem.encoderDrivelf());
        telemetry.addData("rr",mecanumSubsystem.encoderDriverrr());
        telemetry.addData("rf",mecanumSubsystem.encoderDriverrf());
        telemetry.addData("specimenGrabberPosition",intakeSubsystem.specimenGrabGetPosition());
        telemetry.addData("extensionEncoderPosition", intakeSubsystem.extensionEncoderCounts());
        telemetry.addData("grabBtm",intakeSubsystem.specimenGrabGetBtm());
        telemetry.update();
    }

}
