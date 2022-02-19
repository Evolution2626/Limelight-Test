// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ControlWinchCommand extends CommandBase {

  private Climber climber;

  private double speed;

  public ControlWinchCommand(Climber climber, Double speed) {
    this.climber = climber;
    this.speed = speed;
    addRequirements(climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.tournerWinch(speed);
    System.out.println("winch");
  }

  @Override
  public void end(boolean interrupted) {
    climber.tournerWinch(0);
    System.out.println("winch end");
  }
}
