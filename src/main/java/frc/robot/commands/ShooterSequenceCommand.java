// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShooterSequenceCommand extends SequentialCommandGroup {
  /** Creates a new ShooterSequenceCommand. */

  boolean stopCommand = false;

  public ShooterSequenceCommand(Limelight limelight, Drivetrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    limelight.setPipeline(0); //tape pipeline
    
    if (limelight.getIsTargetFound()) {
      addCommands(new AlignLimelightCommand(limelight, drivetrain), new PlaceRobotLimelightCommand(limelight, drivetrain, Constants.GAMES_CONSTANTS.HUB_TAPE_HEIGHT));
    } else {
      stopCommand = true;
    }

    addRequirements(limelight, drivetrain);
  }


  @Override
  public boolean isFinished() {
    return stopCommand;
  }
}
