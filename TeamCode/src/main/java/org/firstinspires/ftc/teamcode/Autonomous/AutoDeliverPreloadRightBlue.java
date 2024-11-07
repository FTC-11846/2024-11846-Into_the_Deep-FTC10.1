package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ElevatorSubsystem;

@Autonomous
public class AutoDeliverPreloadRightBlue extends LinearOpMode {

    MecanumSubsystem mecanumSubsystem;
    ElevatorSubsystem elevatorSubsystem;
    IntakeSubsystem intakeSubsystem;

    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        mecanumSubsystem = new MecanumSubsystem(hardwareMap);
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

        waitForStart();
        // sleep(); functions may be needed after non-drivetrain functions
        encoderDriveToPosition("forward",.3,5000,3);
        encoderDriveToPosition("slideLeft",.3,500,3);
        elevatorSubsystem.encoderElevator(100);
        encoderDriveToPosition("forward",.3,200,3);
        elevatorSubsystem.encoderElevator(-100);
        sleep(2000);
        elevatorSubsystem.encoderElevator(100);
        encoderDriveToPosition("backward",.3,5000,3);

       // intakeSubsystem.sampleDropper(1);
    }

    public void encoderDriveToPosition(String direction, double power, int counts, double timeout){
        mecanumSubsystem.encoderDriveAuto(direction, power, counts);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < timeout && mecanumSubsystem.areMotorsBusy()){}
        mecanumSubsystem.driveMotorsOff();
    }

}
