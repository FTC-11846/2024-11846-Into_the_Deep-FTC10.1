package org.firstinspires.ftc.teamcode.Opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ScissorSubsystem;

@TeleOp(name = "Teleop", group = "TeleOp")
public class Teleop extends OpMode {

    MecanumSubsystem mecanumSubsystem;
    ScissorSubsystem scissorSubsystem;
    IntakeSubsystem intakeSubsystem;

    @Override
    public void init(){
        mecanumSubsystem = new MecanumSubsystem(hardwareMap);
        scissorSubsystem = new ScissorSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

    }

    @Override
    public void loop(){

        double forward = -gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        double lift = gamepad2.left_stick_y;
        double extend = -gamepad2.right_stick_y;

        if(gamepad2.a){
            intakeSubsystem.intake(1);
        } else if(gamepad2.b){
            intakeSubsystem.intake(-1);
        } else {
            intakeSubsystem.intake(0);
        }

        if(gamepad2.x){
            intakeSubsystem.sampleDropper(1);
        }else{
            intakeSubsystem.sampleDropper(0);
        }

        mecanumSubsystem.TeleOperatedDrive(forward, -strafe, turn);
        scissorSubsystem.scissorLift(lift);
        intakeSubsystem.extend(extend);
    }

}
