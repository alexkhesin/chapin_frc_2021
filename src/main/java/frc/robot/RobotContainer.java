/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package frc.robot;
import static frc.robot.Constants.DRIVER_LEFT_AXIS;
import static frc.robot.Constants.DRIVER_REMOTE_PORT;
import static frc.robot.Constants.DRIVER_RIGHT_AXIS;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveForward50;
import frc.robot.commands.DriveForwardTwoFeetCommand;
import frc.robot.commands.DriveInSquareCommandGroup;
import frc.robot.commands.DriveManuallyCommand;
import frc.robot.commands.DriveToWallCommand;
import frc.robot.commands.TurnRobotToLeft90;
import frc.robot.subsystems.DriveTrainSubsystem;
/**
* This class is where the bulk of the robot should be declared. Since
* Command-based is a "declarative" paradigm, very little robot logic should
* actually be handled in the {@link Robot} periodic methods (other than the
* scheduler calls). Instead, the structure of the robot (including subsystems,
* commands, and button mappings) should be declared here.
*/
public class RobotContainer {
 // The robot's subsystems and commands are defined here...
 private JoystickButton driver_X;
 private JoystickButton driver_Y;
 private JoystickButton driver_B;
 private JoystickButton driver_A;
 private final XboxController m_driver_controller = new XboxController(DRIVER_REMOTE_PORT);
 private final DriveTrainSubsystem m_driveTrainSubsystem = new DriveTrainSubsystem();
// private final DriveManuallyCommand m_DriveManuallyCommand = new DriveManuallyCommand(m_driveTrainSubsystem, m_driver_controller);
 private final DriveInSquareCommandGroup autonomousCommandOne = new DriveInSquareCommandGroup(m_driveTrainSubsystem);
 /**
 * The container for the robot. Contains subsystems, OI devices, and commands.
 */
 public RobotContainer() {
 // Configure the button bindings
 configureButtonBindings();
 m_driveTrainSubsystem.setDefaultCommand(new DriveManuallyCommand(m_driveTrainSubsystem, m_driver_controller));
 driver_X = new JoystickButton(m_driver_controller, XboxController.Button.kX.value);
 driver_X.whileHeld(new DriveForward50(m_driveTrainSubsystem));
 driver_Y = new JoystickButton(m_driver_controller, XboxController.Button.kY.value);
 driver_Y.whenPressed(new DriveForwardTwoFeetCommand(m_driveTrainSubsystem));
 driver_B = new JoystickButton(m_driver_controller, XboxController.Button.kB.value);
 driver_B.whenPressed(new TurnRobotToLeft90(m_driveTrainSubsystem));
 driver_A = new JoystickButton(m_driver_controller, XboxController.Button.kA.value);
 driver_A.whileHeld (new DriveToWallCommand( m_driveTrainSubsystem));
 // Add information to the Shuffleboard for monitoring and Troubleshooting
 Shuffleboard.getTab("DriveTrainDisplay");
 // This code adds values to the "DriveTrainDisplay" Tab on the Shuffleboard.
 // This code is dependent on two methods added to the RobotContainer to access
 // the data to be displayed
 Shuffleboard.getTab("DriveTrainDisplay").addNumber("Joystick-Y", this::getJoystickYValue)
 .withWidget(BuiltInWidgets.kNumberSlider).withPosition(6, 3);
 Shuffleboard.getTab("DriveTrainDisplay").addNumber("Joystick-X", this::getJoystickXValue)
 .withWidget(BuiltInWidgets.kNumberSlider).withPosition(6, 4);
 // This adds a command to the Shuffleboard
 Shuffleboard.getTab("DriveTrainDisplay").add(new DriveForward50(m_driveTrainSubsystem)).withPosition(6, 0)
 .withSize(2, 2);
 }
 /**
 * Use this method to define your button->command mappings. Buttons can be
 * created by instantiating a {@link GenericHID} or one of its subclasses
 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
 */
 private void configureButtonBindings() {
 }
 /**
 * Use this to pass the autonomous command to the main {@link Robot} class.
 *
 * @return the command to run in autonomous
 */
 public Command getAutonomousCommand() {
 // An ExampleCommand will run in autonomous
 return autonomousCommandOne;
 }
 // These are used by the commands to add data to the shuffleboard (See the
 // RobotContainer Constructor)
 public double getJoystickXValue() {
 return m_driver_controller.getRawAxis(DRIVER_RIGHT_AXIS);
 }
 public double getJoystickYValue() {
 return m_driver_controller.getRawAxis(DRIVER_LEFT_AXIS);
 }
}