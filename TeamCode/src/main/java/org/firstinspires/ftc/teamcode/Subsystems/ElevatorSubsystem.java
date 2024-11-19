package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ElevatorSubsystem {

    // Left & Right is from the view of someone in front of the robot, looking at it.
    // In the future, switch to vehicle passenger perspective to match path/driving toolkits.
    DcMotor elevatorL, elevatorR;
    // Not Impl yet! --> Servo hangLowBar, hangHighBar;

    public ElevatorSubsystem(HardwareMap hardwareMap){
        elevatorL = hardwareMap.get(DcMotor.class,"elevatorL");
        elevatorR = hardwareMap.get(DcMotor.class,"elevatorR");

    /*  As of 2024-11-17, Planned servos for hanging, not implemented yet
        hangLowBar = hardwareMap.get(Servo.class,"hangLow");
        hangHighBar = hardwareMap.get(Servo.class,"hangHigh");

        hangHighBar.setDirection(Servo.Direction.REVERSE);
    */

        elevatorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        elevatorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorL.setDirection(DcMotor.Direction.REVERSE);
        elevatorR.setDirection(DcMotor.Direction.FORWARD);

        elevatorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void elevatorLift(double power){
        elevatorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorL.setPower(power);
        elevatorR.setPower(power);
    }

    public void encoderElevator(double counts){
        elevatorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int lTarget = Math.toIntExact(Math.round(elevatorL.getCurrentPosition() + counts));
        int rTarget = Math.toIntExact(Math.round(elevatorR.getCurrentPosition() + counts));
        elevatorL.setTargetPosition(lTarget);
        elevatorR.setTargetPosition(rTarget);
        if(counts < 0){
            elevatorL.setPower(-0.75);
            elevatorR.setPower(-0.75);
        } else {
            elevatorL.setPower(0.75);
            elevatorR.setPower(0.75);
        }

    }

//    public void hang(){
//        hangLowBar.setPosition(0.5);
//        hangHighBar.setPosition(0.56);
//
//        if(hangLowBar.getPosition() == 0.5 && hangHighBar.getPosition() == 0.56){
//
//        }
//    }
//    public void openHang() {
//        hangLowBar.setPosition(0.91);
//        hangHighBar.setPosition(1);
//    }

    public int ElevatorLCounts(){
        return elevatorL.getCurrentPosition();
    }

    public int ElevatorRCounts(){
        return elevatorR.getCurrentPosition();
    }

}
