using System;
using ToyRobotSimulator.Application.Commands;

namespace ToyRobotSimulator.Infrastructure.IO;

/*
 * Handles console input/output operations.
 * Encapsulates all I/O concerns in the infrastructure layer.
 */
public class ConsoleIo(RobotCommandHandler robotCommandHandler)
{
    private readonly RobotCommandHandler _robotCommandHandler =
        robotCommandHandler ?? throw new ArgumentNullException(nameof(robotCommandHandler));

    public void Run()
    {
        Console.WriteLine("Toy Robot Simulator");
        Console.WriteLine("Enter commands (PLACE X,Y,DIRECTION | MOVE | LEFT | RIGHT | REPORT)");
        Console.WriteLine("Press Ctrl+C to exit\n");

        while (true)
        {
            var input = Console.ReadLine();
            if (input == null) break;
            _robotCommandHandler.Handle(input);
        }
    }
}
