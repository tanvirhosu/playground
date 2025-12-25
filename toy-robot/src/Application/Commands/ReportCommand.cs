using System;
using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Interfaces;

namespace ToyRobotSimulator.Application.Commands;

public class ReportCommand(Action<string> outputAction) : IRobotCommand
{
    private readonly Action<string> _outputAction =
        outputAction ?? throw new ArgumentNullException(nameof(outputAction));

    public void Execute(Robot robot, Table table)
    {
        var report = robot.GetReport();
        if (!string.IsNullOrEmpty(report))
        {
            _outputAction(report);
        }
    }
}
