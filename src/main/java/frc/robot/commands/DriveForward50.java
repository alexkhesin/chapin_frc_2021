package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveForward50 extends CommandBase {
  private final DriveTrainSubsystem m_driveTrain;

  public DriveForward50(DriveTrainSubsystem driveTrain) {
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.manualDrive(0.5, 0); // Drive straight forward at 50%
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.manualDrive(0, 0); // Stop the robot
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}