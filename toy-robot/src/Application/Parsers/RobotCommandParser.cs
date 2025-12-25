using System;
using ToyRobotSimulator.Domain.Interfaces;
using ToyRobotSimulator.Application.Commands;

namespace ToyRobotSimulator.Application.Parsers;

public class RobotCommandParser(Action<string> outputAction)
{
    private readonly Action<string> _outputAction =
        outputAction ?? throw new ArgumentNullException(nameof(outputAction));

    public IRobotCommand? Parse(string command)
    {
        if (string.IsNullOrWhiteSpace(command)) return null;

        var commandParts = (command.Trim()).Split(' ', StringSplitOptions.RemoveEmptyEntries);

        if (commandParts.Length == 0) return null;

        var robotCommand = commandParts[0].ToUpper();

        return robotCommand switch
        {
            "PLACE" => PlaceCommandParser.Parse(commandParts),
            "MOVE" => new MoveCommand(),
            "LEFT" => new LeftCommand(),
            "RIGHT" => new RightCommand(),
            "REPORT" => new ReportCommand(_outputAction),
            _ => null
        };
    }
}
