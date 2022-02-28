package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FlapsSubsystem extends SubsystemBase {
  MotorControllerGroup m_flaps;

  public FlapsSubsystem() {
    PWMVictorSPX front = new PWMVictorSPX(Constants.FRONT_FLAP_PWM_PORT);
    PWMVictorSPX rear = new PWMVictorSPX(Constants.REAR_FLAP_PWM_PORT);
    rear.setInverted(true);
    m_flaps = new MotorControllerGroup(front, rear);
  }

  public void setSpeed(double speed) {
    SmartDashboard.putNumber("flaps speed", speed);
    m_flaps.set(speed); // the % output of the motor, between -1 and 1
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
  }
}