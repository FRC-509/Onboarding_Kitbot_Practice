// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.Constants;
import frc.robot.Constants.IDs;
import frc.robot.Constants.intakeConstants;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;

public class Intake extends SubsystemBase {
  public final TalonFX intakeMotor = new TalonFX(IDs.intakeMotorID);
  public final VoltageOut intakeVoltageOut = new VoltageOut(0).withEnableFOC(false);

  private final NetworkTable intakeTable = NetworkTableInstance.getDefault().getTable("Intake");
  private final NetworkTableEntry intakeStateEntry = intakeTable.getEntry("Intaking");  
  private final NetworkTableEntry intakeVelocityEntry = intakeTable.getEntry("IntakeVelocity");
  

  public Intake() {
    TalonFXConfiguration intakeConfig = new TalonFXConfiguration();

    // PID values
    intakeConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    //aplying the Configs
    intakeMotor.getConfigurator().apply(intakeConfig);
  }

  public void intake(){
    intakeMotor.setControl(intakeVoltageOut.withOutput(intakeConstants.kIntakeSpeed));
    
  }

  public void outtake(){
    intakeMotor.setControl(intakeVoltageOut.withOutput(intakeConstants.kOuttakeSpeed));
  }

  private void stop(){
    intakeMotor.setControl(intakeVoltageOut.withOutput(0));
  }

  public boolean getIntakeState(){
    return Math.abs(intakeMotor.getVelocity().getValueAsDouble()) > 0.1;
  }

  public double getIntakeVelocity(){
    return intakeMotor.getVelocity().getValueAsDouble();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    intakeStateEntry.setBoolean(getIntakeState());
    intakeVelocityEntry.setDouble(getIntakeVelocity());
  }

}
