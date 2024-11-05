package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ScissorSubsystem;

@Autonomous
public class AutoDeliverPreloadRightBlue extends LinearOpMode {

    MecanumSubsystem mecanumSubsystem;
    ScissorSubsystem scissorSubsystem;
    IntakeSubsystem intakeSubsystem;

    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        mecanumSubsystem = new MecanumSubsystem(hardwareMap);
        scissorSubsystem = new ScissorSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

        waitForStart();
        // sleep(); functions may be needed after non-drivetrain functions
  //go forward half a square
        //strafe left till buckets
        //rotate left 90 raise scissor and extend then drop to score
        sleep(10000);
        encoderDriveToPosition("forward",.3,1000,1);
        scissorSubsystem.encoderScissor(-556);
       // intakeSubsystem.sampleDropper(1);
    }

    public void encoderDriveToPosition(String direction, double power, int counts, double timeout){
        mecanumSubsystem.encoderDriveAuto(direction, power, counts);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < timeout && mecanumSubsystem.areMotorsBusy()){}
        mecanumSubsystem.driveMotorsOff();
    }

}
