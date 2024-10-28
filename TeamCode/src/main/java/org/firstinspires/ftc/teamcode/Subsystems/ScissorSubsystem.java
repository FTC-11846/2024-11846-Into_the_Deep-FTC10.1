package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ScissorSubsystem {

    DcMotor sL,sR;

    public ScissorSubsystem(HardwareMap hardwareMap){
        sL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sL.setDirection(DcMotor.Direction.REVERSE);
        sR.setDirection(DcMotor.Direction.REVERSE);

        sL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

}
