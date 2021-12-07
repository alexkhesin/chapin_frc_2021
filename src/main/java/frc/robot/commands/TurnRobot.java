package frc.robot.commands;

import java.time.Instant;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TurnRobot extends CommandBase {
  private final DriveTrainSubsystem m_driveTrain;
  private boolean m_turnComplete = true;
  private double m_angle;

  public TurnRobot(DriveTrainSubsystem driveTrain, double angle) {
    m_driveTrain = driveTrain;
    m_angle = angle;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentGyroHeading = m_driveTrain.getCurrentHeading();
    SmartDashboard.putNumber("heading", currentGyroHeading);
    SmartDashboard.putNumber("angle", m_angle);
    if (currentGyroHeading < m_angle) {
      m_driveTrain.manualDrive(0, 0.8); // Rotate at 50% power
      m_turnComplete = false;
    } else {
      m_driveTrain.manualDrive(0, 0);
      m_turnComplete = true;
    }
    SmartDashboard.putBoolean("turnComplete", m_turnComplete);
    SmartDashboard.putNumber("time", Instant.now().getEpochSecond());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.manualDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_turnComplete;
  }
}