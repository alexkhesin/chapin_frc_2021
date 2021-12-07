package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveForward50;
import frc.robot.commands.DriveInSquareCommandGroup;
import frc.robot.commands.DriveManuallyCommand;
import frc.robot.commands.FlapsCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.commands.TurnRobot;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FlapsSubsystem;
import frc.robot.subsystems.LiftSubsystem;

public class RobotContainer {// The robot's subsystems and commands are defined here...
  private final XboxController m_controller = new XboxController(0);
  private final DriveTrainSubsystem m_driveTrainSubsystem = new DriveTrainSubsystem();
  private final LiftSubsystem m_lift = new LiftSubsystem();
  private final FlapsSubsystem m_flaps = new FlapsSubsystem();
  private final DriveInSquareCommandGroup m_autonomousCommandOne = new DriveInSquareCommandGroup(m_driveTrainSubsystem);

  public RobotContainer() {
    // One day we should switch to using
    // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
    m_driveTrainSubsystem.setDefaultCommand(new DriveManuallyCommand(m_driveTrainSubsystem, m_controller,
        XboxController.Axis.kLeftY, XboxController.Axis.kRightX));

    JoystickButton button_X = new JoystickButton(m_controller, XboxController.Button.kX.value);
    button_X.whileHeld(new LiftCommand(m_lift, 0.5));
    JoystickButton button_Y = new JoystickButton(m_controller, XboxController.Button.kY.value);
    button_Y.whileHeld(new LiftCommand(m_lift, -0.5));

    JoystickButton button_B = new JoystickButton(m_controller, XboxController.Button.kB.value);
    button_B.whenPressed(new TurnRobot(m_driveTrainSubsystem, 90).withName("turn 90"));

    JoystickButton button_bumperLeft = new JoystickButton(m_controller, XboxController.Button.kBumperLeft.value);
    button_bumperLeft.whileHeld(new FlapsCommand(m_flaps, 0.5));
    JoystickButton button_bumperRight = new JoystickButton(m_controller, XboxController.Button.kBumperRight.value);
    button_bumperRight.whileHeld(new FlapsCommand(m_flaps, -0.5));

    // Add information to the Shuffleboard for monitoring and Troubleshooting
    Shuffleboard.getTab("DriveTrainDisplay");
    // This code adds values to the "DriveTrainDisplay" Tab on the Shuffleboard.
    // This code is dependent on two methods added to the RobotContainer to access
    // the data to be displayed
    Shuffleboard.getTab("DriveTrainDisplay").addNumber("Joystick-Y", () -> {
      return m_controller.getY(Hand.kLeft);
    }).withWidget(BuiltInWidgets.kNumberSlider).withPosition(6, 3);
    Shuffleboard.getTab("DriveTrainDisplay").addNumber("Joystick-X", this::getJoystickXValue)
        .withWidget(BuiltInWidgets.kNumberSlider).withPosition(6, 4);
    // This adds a command to the Shuffleboard
    Shuffleboard.getTab("DriveTrainDisplay").add(new DriveForward50(m_driveTrainSubsystem)).withPosition(6, 0)
        .withSize(2, 2);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autonomousCommandOne;
  }

  // These are used by the commands to add data to the shuffleboard (See the
  // RobotContainer Constructor)
  private double getJoystickXValue() {
    return m_controller.getX(Hand.kRight);
  }

  private double getJoystickYValue() {
    return m_controller.getY(Hand.kLeft);
  }
}