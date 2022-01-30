// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PlaceRobotLimelightCommand extends PIDCommand {
  /** Creates a new PlaceRobotLimelightCommand. */
  public PlaceRobotLimelightCommand(Limelight limelight, Drivetrain drivetrain, double targetHeight) {
    super(
        // The controller that the command will use (Still need to be adjusted and tuned)
        new PIDController(1, 1, 0),
        // This should return the measurement
        () -> limelight.getTargetDistance(targetHeight),
        // This should return the setpoint (can also be a constant)
        () -> Constants.GAMES_CONSTANTS.SHOOTING_DISTANCE,
        // This uses the output
        output -> {
          // Use the output here
          drivetrain.arcadeDrive(output, 0);
        });
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(limelight, drivetrain);

    // Configure additional PID options by calling `getController` here.

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
