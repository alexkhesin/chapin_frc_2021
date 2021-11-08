/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import static frc.robot.Constants.LEFT_TALON_LEADER;
import static frc.robot.Constants.RANGE_FINDER_PORT;
import static frc.robot.Constants.RIGHT_TALON_LEADER;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveTrainSubsystem.
   */
  private final WPI_TalonSRX m_rightLeader; // Declare motor controllers variables
  private final WPI_TalonSRX m_leftLeader;
  private final DifferentialDrive m_safety_drive; // Declare drive train core function
  private int percentComplete = 0;
  private final AHRS navx;
  private AnalogInput Rangefinder;
  private final LEDSubsystem m_LEDSubsystem;

  public DriveTrainSubsystem() {
    m_rightLeader = new WPI_TalonSRX(RIGHT_TALON_LEADER); // Instantiate motor controllers
    m_leftLeader = new WPI_TalonSRX(LEFT_TALON_LEADER);
    m_safety_drive = new DifferentialDrive(m_leftLeader, m_rightLeader);
    // setup the Talon to use an external shaft encoder, set direction and reset
    m_rightLeader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    m_rightLeader.setSensorPhase(true);
    m_rightLeader.setSelectedSensorPosition(0);
    navx = new AHRS(SPI.Port.kMXP);
    Rangefinder = new AnalogInput(RANGE_FINDER_PORT);
    m_LEDSubsystem = new LEDSubsystem();
  }

  public void manualDrive(double move, double turn) {
    move = move * 0.5;
    turn = turn * 0.5; // Reduce speed for testing purposes
    m_safety_drive.arcadeDrive(move, turn);
    m_safety_drive.feed();
    // Test the Gyro by displaying the current value on the shuffleboard
    double currentHeading = get_current_heading();
    int currentHeadingInteger = (int) (currentHeading);
    SmartDashboard.putNumber("RobotHeading", currentHeadingInteger);
    // Test the Rangefinder by displaying the current value on the shuffleboard
    double rangeToWall = getRangeFinderDistance();
    int rangeToWallInteger = (int) (rangeToWall);
    SmartDashboard.putNumber("Range to Wall", rangeToWallInteger);
    // Test the LED Strip
    m_LEDSubsystem.SetLEDColor(((int) (64 - move * 2 * 64)), ((int) (64 + move * 2 * 64)), 0); // Red Green Blue

  }

  public Boolean driveForwardInches(double motorSpeed, int inchesToDrive, Boolean resetEncoder) {
    Boolean driveComplete = false;
    int currentShaftEncoderValue = 0;
    int targetShaftEncoderCount = 0;
    int convertRotationsToInches = 500; // Will need tested, calibrated and revised
    // Reset the shaft encoder value
    if (resetEncoder == true) {
      m_rightLeader.setSelectedSensorPosition(0);
    }
    // Set the speed of the motors using arcade drive with no turn
    m_safety_drive.arcadeDrive(motorSpeed, 0);
    m_safety_drive.feed();
    // Check the encoder
    currentShaftEncoderValue = m_rightLeader.getSelectedSensorPosition();
    targetShaftEncoderCount = inchesToDrive * convertRotationsToInches;
    if (currentShaftEncoderValue > targetShaftEncoderCount) {
      driveComplete = true;
    }
    percentComplete = (int) (100 * (currentShaftEncoderValue / (double) targetShaftEncoderCount));
    SmartDashboard.putNumber("Encoder % Complete", percentComplete);
    return driveComplete;
  }

  public void reset_gyro() {
    navx.reset();
  }

  public double get_current_heading() {
    return navx.getAngle();
  }

  // for finding the distance from the range finder
  public double getRangeFinderDistance() {
    double rangefinderVoltage = Rangefinder.getAverageVoltage();
    double distanceInInches = (rangefinderVoltage * 65.4) - 7.2;
    return distanceInInches;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}