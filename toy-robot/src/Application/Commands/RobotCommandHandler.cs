using System;
using ToyRobotSimulator.Application.Parsers;
using ToyRobotSimulator.Domain.Services;

namespace ToyRobotSimulator.Application.Commands;

public class RobotCommandHandler(RobotService robotService, RobotCommandParser robotCommandParser)
{
    private readonly RobotService _robotService = robotService ?? throw new ArgumentNullException(nameof(robotService));

    private readonly RobotCommandParser _robotCommandParser =
        robotCommandParser ?? throw new ArgumentNullException(nameof(robotCommandParser));

    public void Handle(string? command)
    {
        if (command is null) return;

        var robotCommand = _robotCommandParser.Parse(command);
        if (robotCommand != null)
        {
            _robotService.ExecuteCommand(robotCommand);
        }
    }
}
