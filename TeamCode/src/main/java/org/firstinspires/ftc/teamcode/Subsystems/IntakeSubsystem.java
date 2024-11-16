package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem {

    DcMotor ex, im;
    Servo sd,sd2, grabTop, grabBtm;
    RevColorSensorV3 cs;

    //specimen grabber position constants
    public static final double grabIntitPos = 0;
    public static final double grabSafePos =   .25;
    public static final double grabActivePos = .6;


    public IntakeSubsystem(HardwareMap hardwareMap){
        ex = hardwareMap.get(DcMotor.class, "ex");
        im = hardwareMap.get(DcMotor.class, "im");
        sd = hardwareMap.get(Servo.class, "sd");
        sd2 = hardwareMap.get(Servo.class, "sd2");
        grabTop = hardwareMap.get(Servo.class, "grabTop");
        grabBtm = hardwareMap.get(Servo.class, "grabBtm");
   //     cs = hardwareMap.get(RevColorSensorV3.class, "cs");

        sd2.setDirection(Servo.Direction.REVERSE);
        ex.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        im.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ex.setDirection(DcMotor.Direction.FORWARD);
        im.setDirection(DcMotor.Direction.FORWARD);

        ex.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        im.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ex.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        im.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void extend(double power){
        ex.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ex.setPower(power);
    }

    public double extensionEncoderCounts(){
        return  ex.getCurrentPosition();
    }

    public void encoderExtend(double counts){
        ex.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int xTarget = Math.toIntExact(Math.round(ex.getCurrentPosition() + counts));
        ex.setTargetPosition(xTarget);
        if(counts < 0){
            ex.setPower(-0.75);
        } else {
            ex.setPower(0.75);
        }
    }

    public void intake(double power) {
        im.setPower(power);
    }

    public void sampleDropper(double position){
        sd.setPosition(position);
        sd2.setPosition(position);
    }

//   public void specimenGrabIncrement(double position){
//        double newGrabPos = specimenGrabPosition() + position;
//       grabTop.setPosition(newGrabPos);
//       grabBtm.setPosition(newGrabPos);
//    }

    //0 = servo position don't match
    //4 = not working and grab servos are not in correct
    //1 = grabber is in scoring position
    //2 = grabber is in the safe position
    //3 = grabber is in the init position
    public double specimenGrabPosition (int comboPosition) {
        double grabTopPos = grabTop.getPosition();
        double grabBtmPos = grabBtm.getPosition();
        if (grabTopPos != grabBtmPos) {
            return 0;
        } else if (grabTopPos == grabActivePos) {
            return 1;
        }else if(grabTopPos == grabSafePos){
            return 2;
        }else if(grabTopPos == grabIntitPos){
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

    public double sampleD1(){
        return sd.getPosition();
    }
    public double sampleD2(){
        return sd2.getPosition();
    }
    public void incrementdropper(boolean forward,double increment){
        if (forward){
            sd.setPosition(sd.getPosition() + increment);

        }else{
            sd.setPosition(-increment);
        }

    }
}
