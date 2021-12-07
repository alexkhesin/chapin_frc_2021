package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlapsSubsystem;

public class FlapsCommand extends CommandBase {
  private final FlapsSubsystem m_flaps;
  private final double m_speed;

  public FlapsCommand(FlapsSubsystem flaps, double speed) {
    m_flaps = flaps;
    m_speed = speed;
    addRequirements(m_flaps);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_flaps.setSpeed(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flaps.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
