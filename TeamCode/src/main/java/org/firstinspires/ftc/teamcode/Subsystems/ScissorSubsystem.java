package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScissorSubsystem {

    DcMotor sL,sR;
    Servo h1, h2;

    public ScissorSubsystem(HardwareMap hardwareMap){
        sL = hardwareMap.get(DcMotor.class,"sL");
        sR = hardwareMap.get(DcMotor.class,"sR");
        h1 = hardwareMap.get(Servo.class,"h1");
        h2 = hardwareMap.get(Servo.class,"h2");

        h2.setDirection(Servo.Direction.REVERSE);

        sL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sL.setDirection(DcMotor.Direction.REVERSE);
        sR.setDirection(DcMotor.Direction.REVERSE);

        sL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void scissorLift(double power){
        sL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sL.setPower(power);
        sR.setPower(power);
    }

    public void encoderScissor(double counts){
        sL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int lTarget = Math.toIntExact(Math.round(sL.getCurrentPosition() + counts));
        int rTarget = Math.toIntExact(Math.round(sR.getCurrentPosition() + counts));
        sL.setTargetPosition(lTarget);
        sR.setTargetPosition(rTarget);
        if(counts < 0){
            sL.setPower(-0.75);
            sR.setPower(-0.75);
        } else {
            sL.setPower(0.75);
            sR.setPower(0.75);
        }

    }

    public void hang(){
        h1.setPosition(0.5);
        h2.setPosition(0.56);

        if(h1.getPosition() == 0.5 && h2.getPosition() == 0.56){

        }
    }
    public void openHang() {
        h1.setPosition(0.91);
        h2.setPosition(1);
    }

    public int scissorLCounts(){
        return sL.getCurrentPosition();
    }

    public int scissorRCounts(){
        return sR.getCurrentPosition();
    }

}
