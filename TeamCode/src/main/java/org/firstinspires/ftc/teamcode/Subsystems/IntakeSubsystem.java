package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem {

    DcMotor inExtendMotor, intakeMotor;
    Servo dropper, grabTop, grabBtm;
    RevColorSensorV3 cs;
    CRServo test;

    //specimen grabber position constants
    public static final double grabInitPos = 0;
    public static final double grabSafePos =   0.3; //0.3
    public static final double grabActivePos = 0.6; //0.6


    public IntakeSubsystem(HardwareMap hardwareMap){
        inExtendMotor = hardwareMap.get(DcMotor.class, "inExtendMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        dropper = hardwareMap.get(Servo.class, "dropper");
        grabTop = hardwareMap.get(Servo.class, "grabTop");
        grabBtm = hardwareMap.get(Servo.class, "grabBtm");
   //     cs = hardwareMap.get(RevColorSensorV3.class, "cs");
        grabTop.setDirection(Servo.Direction.FORWARD);

   //  Was this just for bug testing? -->     test.getDirection();

        inExtendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        inExtendMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        inExtendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        inExtendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void extend(double power){
        inExtendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        inExtendMotor.setPower(power);
    }

    public double extensionEncoderCounts(){
        return  inExtendMotor.getCurrentPosition();
    }

    public void encoderExtend(double counts){
        inExtendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int xTarget = Math.toIntExact(Math.round(inExtendMotor.getCurrentPosition() + counts));
        inExtendMotor.setTargetPosition(xTarget);
        if(counts < 0){
            inExtendMotor.setPower(-0.75);
        } else {
            inExtendMotor.setPower(0.75);
        }
    }

    public void intake(double power) {
        intakeMotor.setPower(power);
    }

    public void sampleDropper(double position){
        dropper.setPosition(position);
    }

    public void specimenGrabSetPosition(double newGrabPos){
        grabTop.setPosition(newGrabPos);
        grabBtm.setPosition(newGrabPos);
    }

    public double specimenGrabGetTop(){
    return grabTop.getPosition();
    }
    public double specimenGrabGetBtm(){
        return grabBtm.getPosition();
    }


    //0 = servo position don't match
    //4 = not working and grab servos are not in correct
    //1 = grabber is in scoring position
    //2 = grabber is in the safe position
    //3 = grabber is in the init position
    public int specimenGrabGetPosition() {
        double grabTopPos = grabTop.getPosition();
        double grabBtmPos = grabBtm.getPosition();
        if (grabTopPos != grabBtmPos) {
            return 0;
        } else if (grabBtmPos == grabActivePos) {
            return 1;
        }else if(grabBtmPos == grabSafePos){
            return 2;
        }else if(grabBtmPos == grabInitPos){
            return 3;
        }
        return 4;
    }

    public int colorSensorBlue(){
        return cs.blue();
    }
    public int colorSensorRed(){
        return cs.red();
    }
    public int colorSensorGreen(){
        return cs.green();
    }

    public String colorSensed(){
        if (cs.red()>200){
            return "red";
        }else if(cs.blue()>200){
            return "blue";
        }else if(cs.red()>100&cs.green()>100){
            return "yellow";
        }else
            return "null";
    }

    public double sampleDropGetPos(){
        return dropper.getPosition();
    }

    public void incrementdropper(boolean forward,double increment){
        if (forward){
            dropper.setPosition(dropper.getPosition() + increment);

        }else{
            dropper.setPosition(-increment);
        }

    }
}

