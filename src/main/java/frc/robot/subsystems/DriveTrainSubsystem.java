package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  private final DifferentialDrive m_drive;
  private final AHRS m_navx;

  public DriveTrainSubsystem() {
    WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.FRONT_LEFT_TALON_ID);
    WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.REAR_LEFT_TALON_ID);
    SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
 
    WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.FRONT_RIGHT_TALON_ID);
    WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.REAR_RIGHT_TALON_ID);
    SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
 
    m_drive = new DifferentialDrive(left, right);

    m_navx = new AHRS(SPI.Port.kMXP);
  }

  public void manualDrive(double move, double turn) {
     // Reduce speed, full 1.0 makes the motors unhappy 
    move = move * 0.5;
    turn = turn * 0.5;

    SmartDashboard.putNumber("move", move);
    SmartDashboard.putNumber("turn", turn);

    m_drive.arcadeDrive(move, turn);
    // m_drive.feed();
  }

  public void resetGyro() {
    m_navx.reset();
  }

  public double getCurrentHeading() {
    return m_navx.getAngle();
  }

  // This method is called once per scheduler run
  @Override
  public void periodic() {
    SmartDashboard.putNumber("gyro angle", m_navx.getAngle());
    SmartDashboard.putNumber("gyro yaw", m_navx.getYaw());
    SmartDashboard.putBoolean("gyro calubrating", m_navx.isCalibrating());
  }
}