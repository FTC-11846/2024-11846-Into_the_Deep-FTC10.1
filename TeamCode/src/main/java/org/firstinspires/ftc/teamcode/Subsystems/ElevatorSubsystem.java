package org.firstinspires.ftc.teamcode.Subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
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
   //     public static final double hangInitPos = 0;
   //     public static final double hangReadyPos = 1;

    /*  As of 2024-11-17, Planned servos for hanging, not implemented yet
        hangLowBar = hardwareMap.get(Servo.class,"hangLow");
        hangHighBar = hardwareMap.get(Servo.class,"hangHigh");

        hangHighBar.setDirection(Servo.Direction.REVERSE);
    */

//        These don't work like we need. By design, it only resists external forces, it does NOT
//        apply power from the battery to hold the current position.  The solution for that is to use
//        RUN_TO_POSITION, even in TeleOp.  See URL below for more info, including sample code..
//        https://ftcforum.firstinspires.org/forum/ftc-technology/android-studio/6443-getting-motors-to-hold-their-position
//        elevatorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        elevatorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorL.setDirection(DcMotor.Direction.REVERSE);
        elevatorR.setDirection(DcMotor.Direction.FORWARD);

        elevatorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorL.setTargetPosition(0);
        elevatorR.setTargetPosition(0);
        elevatorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorL.setPower(.7);
        elevatorR.setPower(.7);
    }

    public void elevatorLift(double power){

        if(power > 0.03) {
            elevatorL.setTargetPosition(elevatorL.getCurrentPosition() + 200);
            elevatorR.setTargetPosition(elevatorR.getCurrentPosition() + 200);
        } else if(power < -0.03){
            elevatorL.setTargetPosition(elevatorL.getCurrentPosition() - 200);
            elevatorR.setTargetPosition(elevatorR.getCurrentPosition() - 200);
    }
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
    public class Liftup implements Action {

        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevatorL.setPower(0.8);
                elevatorR.setPower(0.8);
                initialized = true;
            }
            double Lpos = elevatorL.getCurrentPosition();
            double Rpos = elevatorR.getCurrentPosition();
            packet.put("liftLPos", Lpos);
            packet.put("liftRPos",Rpos);
            if (Rpos < .0) {
                // true causes the action to rerun
                return true;
            } else {
                // false stops action rerun
                elevatorL.setPower(0);
                elevatorR.setPower(0);
                return false;
            }

        }
    }
    public Action liftUp() {
        return new Liftup();
    }
    public class LiftDown implements Action {

        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevatorL.setPower(-0.8);
                elevatorR.setPower(-0.8);
                initialized = true;
            }
            double Lpos = elevatorL.getCurrentPosition();
            double Rpos = elevatorR.getCurrentPosition();
            packet.put("liftLPos", Lpos);
            packet.put("liftRPos", Rpos);
            if (Rpos < 0.0) {
                // true causes the action to rerun
                return true;
            } else {
                // false stops action rerun
                elevatorL.setPower(0);
                elevatorR.setPower(0);
                return false;
            }
        }
    }
        public Action liftDown() {
            return new LiftDown();
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
//public int hangGetPosition() {
//    double hangLowBar = hangLowBar.getPosition();
//    double hangHighBar = hangHighBar.getPosition();
//     if (hangLowBar == hangInitPos ) {
//        return 0;
//    }else if(hangLowBar == hangReadyPos){
//        return 1;
//}

    public int ElevatorLCounts(){
        return elevatorL.getCurrentPosition();
    }
    public int ElevatorLTgt(){
        return elevatorL.getTargetPosition();
    }
    public double ElevatorLGetPwr(){
        return elevatorL.getPower();
    }
    public int ElevatorRTgt(){
        return elevatorR.getTargetPosition();
    }
    public double ElevatorRGetPwr(){
        return elevatorR.getPower();
    }

    public int ElevatorRCounts(){
        return elevatorR.getCurrentPosition();
    }

    public DcMotor.ZeroPowerBehavior EleBrakeR(){return elevatorR.getZeroPowerBehavior();}
}
