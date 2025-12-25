using System;
using ToyRobotSimulator.Application.Commands;
using ToyRobotSimulator.Application.Parsers;
using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Services;
using ToyRobotSimulator.Infrastructure.IO;

/*
 * Entry point of the application.
 * Composition root: central or bootstrap point to include all dependency in order to run the application.
 */
namespace ToyRobotSimulator;

internal class Program
{
    private static void Main()
    {
        var table = new Table { Width = 5, Height = 5 };

        var robotService = new RobotService(table);
        var robotCommandParser = new RobotCommandParser(Console.WriteLine);
        var robotCommandHandler = new RobotCommandHandler(robotService, robotCommandParser);
        var consoleIo = new ConsoleIo(robotCommandHandler);

        consoleIo.Run();
    }
}
