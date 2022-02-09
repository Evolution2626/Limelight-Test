// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.util.Range;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AlignLimelightCommand extends PIDCommand {
  /** Creates a new AlignLimelight. */
  public AlignLimelightCommand(Limelight limelight, Drivetrain drivetrain) {


    super(
        // The controller that the command will use (Still need to be adjusted and tuned)
        new PIDController(0.05, 0, 0),
        // This should return the measurement
        () -> limelight.getdegRotationToTarget(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          drivetrain.driveTank(-output, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(limelight, drivetrain);

    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Range.inRange(-0.1, 0.1, getController().getPositionError());
  }
}
