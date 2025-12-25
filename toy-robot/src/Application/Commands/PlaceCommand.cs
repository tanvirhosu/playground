using System;
using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Interfaces;

namespace ToyRobotSimulator.Application.Commands;

public class PlaceCommand(Position position, Direction direction) : IRobotCommand
{
    public void Execute(Robot robot, Table table)
    {
        try
        {
            robot.Place(position, direction, table);
        }
        catch (InvalidOperationException)
        {
            // Do nothing
        }
    }
}
