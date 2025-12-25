using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Interfaces;

namespace ToyRobotSimulator.Application.Commands;

public class MoveCommand : IRobotCommand
{
    public void Execute(Robot robot, Table table) => robot.Move(table);
}
