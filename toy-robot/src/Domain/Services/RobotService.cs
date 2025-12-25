using System;
using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Interfaces;
using ToyRobotSimulator.Application.Commands;

namespace ToyRobotSimulator.Domain.Services;

public class RobotService
{
    private readonly Table _table;
    private readonly Robot _robot;
    private bool _isRobotInitialized;

    public RobotService(Table table)
    {
        _table = table ?? throw new ArgumentNullException(nameof(table));
        _robot = new Robot();
        _isRobotInitialized = false;
    }

    public void ExecuteCommand(IRobotCommand? command)
    {
        if (command == null) return;

        if (!_isRobotInitialized && command is not PlaceCommand) return;

        command.Execute(_robot, _table);

        if (command is PlaceCommand && _robot.IsPlaced) _isRobotInitialized = true;
    }
}


