package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem {

    DcMotor ex, im;
    Servo sd,sd2;

    public IntakeSubsystem(HardwareMap hardwareMap){
        ex = hardwareMap.get(DcMotor.class, "ex");
        im = hardwareMap.get(DcMotor.class, "im");
        sd = hardwareMap.get(Servo.class, "sd");
        sd2 = hardwareMap.get(Servo.class, "sd2");

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
    public double sampleD1(){
        return sd.getPosition();
    }
    public double sampleD2(){
        return sd2.getPosition();
    }

}
