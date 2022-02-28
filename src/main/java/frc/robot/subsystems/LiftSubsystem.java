package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LiftSubsystem extends SubsystemBase {
  PWMVictorSPX m_motor;

  public LiftSubsystem() {
    // The device is Spark, but for some reason PWMVictorSPX works better
    m_motor = new PWMVictorSPX(Constants.LIFT_PWM_PORT);   // 3 is the RIO PWM port
  }

  public void setSpeed(double speed) {
    SmartDashboard.putNumber("lift motor speed", speed);
    m_motor.set(speed); // the % output of the motor, between -1 and 1
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
  }
}