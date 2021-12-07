package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveManuallyCommand extends CommandBase {
    private final DriveTrainSubsystem m_driveTrain;
    private final XboxController m_driverController;
    private final XboxController.Axis m_moveAxis;
    private final XboxController.Axis m_turnAxis;

    public DriveManuallyCommand(DriveTrainSubsystem driveTrain, XboxController driverController,
            XboxController.Axis moveAxis, XboxController.Axis turnAxis) {
        m_driveTrain = driveTrain;
        m_driverController = driverController;
        m_moveAxis = moveAxis;
        m_turnAxis = turnAxis;
        addRequirements(m_driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Axis are inverted, negate them so positive is forward
        // Drive with split arcade drive.
        // That means that the Y axis of the left stick moves forward
        // and backward, and the X of the right stick turns left and right.
        m_driveTrain.manualDrive(m_driverController.getRawAxis(m_moveAxis.value),
                m_driverController.getRawAxis(m_turnAxis.value));
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}