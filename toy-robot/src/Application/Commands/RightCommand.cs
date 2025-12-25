using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Interfaces;

namespace ToyRobotSimulator.Application.Commands;

public class RightCommand : IRobotCommand
{
    public void Execute(Robot robot, Table table) => robot.TurnRight();
}
