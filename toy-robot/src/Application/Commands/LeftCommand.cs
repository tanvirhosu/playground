using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Interfaces;

namespace ToyRobotSimulator.Application.Commands;

public class LeftCommand : IRobotCommand
{
    public void Execute(Robot robot, Table table) => robot.TurnLeft();
}
