package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ElevatorSubsystem {

    DcMotor eL,eR;
    Servo h1, h2;

    public ElevatorSubsystem(HardwareMap hardwareMap){
        eL = hardwareMap.get(DcMotor.class,"eL");
        eR = hardwareMap.get(DcMotor.class,"eR");
        h1 = hardwareMap.get(Servo.class,"h1");
        h2 = hardwareMap.get(Servo.class,"h2");

        h2.setDirection(Servo.Direction.REVERSE);

        eL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        eR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        eL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        eR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        eL.setDirection(DcMotor.Direction.REVERSE);
        eR.setDirection(DcMotor.Direction.REVERSE);

        eL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        eR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void elevatorLift(double power){
        eL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        eR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        eL.setPower(power);
        eR.setPower(power);
    }

    public void encoderElevator(double counts){
        eL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        eR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int lTarget = Math.toIntExact(Math.round(eL.getCurrentPosition() + counts));
        int rTarget = Math.toIntExact(Math.round(eR.getCurrentPosition() + counts));
        eL.setTargetPosition(lTarget);
        eR.setTargetPosition(rTarget);
        if(counts < 0){
            eL.setPower(-0.75);
            eR.setPower(-0.75);
        } else {
            eL.setPower(0.75);
            eR.setPower(0.75);
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

    public int ElevatorLCounts(){
        return eL.getCurrentPosition();
    }

    public int ElevatorRCounts(){
        return eR.getCurrentPosition();
    }

}
