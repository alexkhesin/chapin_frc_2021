/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class LEDSubsystem extends SubsystemBase {
 /**
 * Creates a new LEDSubsystem.
 */
 private AddressableLED m_led;
 private AddressableLEDBuffer m_ledBuffer;
 public LEDSubsystem() {
 // PWM port is defined by the constant LED_STRIP_PORT
 m_led = new AddressableLED(LED_STRIP_PORT);
 // Set the number of LEDs
 m_ledBuffer = new AddressableLEDBuffer(NUMBER_OF_LEDS);
 m_led.setLength(m_ledBuffer.getLength());
 // Set the data
 m_led.setData(m_ledBuffer);
 m_led.start();
 }
 public void SetLEDColor(int red, int green, int blue) {
 for (var index = 0; index < m_ledBuffer.getLength(); index = index + 1) {
 m_ledBuffer.setRGB(index, red, green, blue); // Red, Green, Blue
 }
 m_led.setData(m_ledBuffer);
 m_led.start();
 }
 @Override
 public void periodic() {
 // This method will be called once per scheduler run
 }
}