/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
public class DriveForward50 extends CommandBase {
 /**
 * Creates a new DriveForward50.
 */
 // Reference to the constructed drive train from RobotContainer to be
 // used to drive our robot
 private final DriveTrainSubsystem m_driveTrain;
 public DriveForward50(DriveTrainSubsystem driveTrain) {
 // Use addRequirements() here to declare subsystem dependencies.
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
 m_driveTrain.manualDrive (0.5,0); // Drive straight forward at 50%
 }
 // Called once the command ends or is interrupted.
 @Override
 public void end(boolean interrupted) {
 m_driveTrain.manualDrive (0,0); // Stop the robot
 }
 // Returns true when the command should end.
 @Override
 public boolean isFinished() {
 return false;
 }
}