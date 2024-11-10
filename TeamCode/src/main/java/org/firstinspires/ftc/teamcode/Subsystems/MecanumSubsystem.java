package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumSubsystem {

    DcMotor lf, lr, rf, rr;
    

    public MecanumSubsystem(HardwareMap hardwareMap){

        lf = hardwareMap.get(DcMotor.class, "lf");
        lr = hardwareMap.get(DcMotor.class,"lr");
        rr = hardwareMap.get(DcMotor.class,"rr");
        rf = hardwareMap.get(DcMotor.class,"rf");


        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lf.setDirection(DcMotor.Direction.REVERSE);
        rf.setDirection(DcMotor.Direction.REVERSE);
        rr.setDirection(DcMotor.Direction.REVERSE);
        lr.setDirection(DcMotor.Direction.REVERSE);

        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void TeleOperatedDrive(double forward, double strafe, double turn) {

        double[] speeds = {
                (forward + strafe + turn),
                (forward - strafe - turn),
                (forward - strafe + turn),
                (forward + strafe - turn)
        };

        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        }

        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        lf.setPower(speeds[0]);
        rf.setPower(-1*speeds[1]);
        lr.setPower(speeds[2]);
        rr.setPower(-1*speeds[3]);
    }


    public boolean areMotorsBusy(){
        return lf.isBusy() || rf.isBusy() || lr.isBusy() || rr.isBusy();
    }

    public void driveMotorsOff(){
        lf.setPower(0);
        rf.setPower(0);
        lr.setPower(0);
        rr.setPower(0);
    }

    /**
     * @param direction
     * direction should either be forward, backward, slideLeft, slideRight, rotateLeft, or rotateRight
     */
    public void encoderDriveAuto(String direction, double power, int counts){

        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        switch (direction) {
            case "forward":
                lf.setTargetPosition(lf.getCurrentPosition() + counts);
                rf.setTargetPosition(rf.getCurrentPosition() + counts);
                lr.setTargetPosition(lr.getCurrentPosition() + counts);
                rr.setTargetPosition(rr.getCurrentPosition() + counts);
                lf.setPower(power);
                rf.setPower(power);
                lr.setPower(power);
                rr.setPower(power);
                break;
            case "backward":
                lf.setTargetPosition(lf.getCurrentPosition() - counts);
                rf.setTargetPosition(rf.getCurrentPosition() - counts);
                lr.setTargetPosition(lr.getCurrentPosition() - counts);
                rr.setTargetPosition(rr.getCurrentPosition() - counts);
                lf.setPower(-power);
                rf.setPower(-power);
                lr.setPower(-power);
                rr.setPower(-power);
                break;
            case "slideLeft":
                lf.setTargetPosition(lf.getCurrentPosition() + counts);
                rf.setTargetPosition(rf.getCurrentPosition() - counts);
                lr.setTargetPosition(lr.getCurrentPosition() - counts);
                rr.setTargetPosition(rr.getCurrentPosition() + counts);
                lf.setPower(power);
                rf.setPower(-power);
                lr.setPower(-power);
                rr.setPower(power);
                break;
            case "slideRight":
                lf.setTargetPosition(lf.getCurrentPosition() - counts);
                rf.setTargetPosition(rf.getCurrentPosition() + counts);
                lr.setTargetPosition(lr.getCurrentPosition() + counts);
                rr.setTargetPosition(rr.getCurrentPosition() - counts);
                lf.setPower(-power);
                rf.setPower(power);
                lr.setPower(power);
                rr.setPower(-power);
                break;
            case "rotateLeft":
                lf.setTargetPosition(lf.getCurrentPosition() - counts);
                rf.setTargetPosition(rf.getCurrentPosition() + counts);
                lr.setTargetPosition(lr.getCurrentPosition() - counts);
                rr.setTargetPosition(rr.getCurrentPosition() + counts);
                lf.setPower(-0.75);
                rf.setPower(0.75);
                lr.setPower(-0.75);
                rr.setPower(0.75);
                break;
            case "rotateRight":
                lf.setTargetPosition(lf.getCurrentPosition() + counts);
                rf.setTargetPosition(rf.getCurrentPosition() - counts);
                lr.setTargetPosition(lr.getCurrentPosition() + counts);
                rr.setTargetPosition(rr.getCurrentPosition() - counts);
                lf.setPower(0.75);
                rf.setPower(-0.75);
                lr.setPower(0.75);
                rr.setPower(-0.75);
                break;
            default:
                break;
        }
    }
}

