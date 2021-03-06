// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private CANSparkMax frontRight;
  private CANSparkMax frontLeft;
  private CANSparkMax backRight;
  private CANSparkMax backLeft;
  private DifferentialDrive differentialDrive;


  public Drivetrain() {

    //differentialDrive = new DifferentialDrive(backLeft, backRight);

    frontRight = new CANSparkMax(Constants.CAN.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
    frontLeft = new CANSparkMax(Constants.CAN.FRONT_LEFT_MOTOR, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.CAN.BACK_RIGHT_MOTOR, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.CAN.BACK_LEFT_MOTOR, MotorType.kBrushless);

    frontRight.setIdleMode(IdleMode.kBrake);
    frontLeft.setIdleMode(IdleMode.kBrake);
    backRight.setIdleMode(IdleMode.kBrake);
    backLeft.setIdleMode(IdleMode.kBrake);

    frontRight.setClosedLoopRampRate(500);
    frontLeft.setClosedLoopRampRate(500);
    backRight.setClosedLoopRampRate(500);
    backLeft.setClosedLoopRampRate(500);

    frontRight.setInverted(true);
    frontLeft.setInverted(false);
    backRight.setInverted(true);
    backLeft.setInverted(false);

    frontRight.follow(backRight);
    frontLeft.follow(backLeft);

    setAllCurrentLimit(25, 20);

    resetEncoder();
    

  }

  public void setAllCurrentLimit(int stall, int free){
    frontRight.setSmartCurrentLimit(stall,free);
    frontLeft.setSmartCurrentLimit(stall,free);
    backRight.setSmartCurrentLimit(stall,free);
    backLeft.setSmartCurrentLimit(stall,free);
  }

  public void driveTank(double left, double right){
    backRight.set(right);
    backLeft.set(left);
  }
  public void arcadeDrive(double speed, double rotation){
    //differentialDrive.arcadeDrive(speed, rotation);
  }


  public void driveTankVolts(double left, double right){
    backRight.setVoltage(right);
    backLeft.setVoltage(left);
  }

  public void resetEncoder(){
    frontRight.getEncoder().setPosition(0);
    frontLeft.getEncoder().setPosition(0);
    backRight.getEncoder().setPosition(0);
    backLeft.getEncoder().setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
