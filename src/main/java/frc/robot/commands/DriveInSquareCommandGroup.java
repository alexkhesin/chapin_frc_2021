package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;

// NOTE: Consider using this command inline, rather than writing a subclass. For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveInSquareCommandGroup extends SequentialCommandGroup {
  public DriveInSquareCommandGroup(DriveTrainSubsystem m_driveTrainSubsystem) {
    /*
    addCommands(new DriveForwardTwoFeetCommand(m_driveTrainSubsystem), new TurnRobotToLeft90(m_driveTrainSubsystem),
        new DriveForwardTwoFeetCommand(m_driveTrainSubsystem), new TurnRobotToLeft90(m_driveTrainSubsystem),
        new DriveForwardTwoFeetCommand(m_driveTrainSubsystem), new TurnRobotToLeft90(m_driveTrainSubsystem),
        new DriveForwardTwoFeetCommand(m_driveTrainSubsystem), new TurnRobotToLeft90(m_driveTrainSubsystem));
        */
  }
}