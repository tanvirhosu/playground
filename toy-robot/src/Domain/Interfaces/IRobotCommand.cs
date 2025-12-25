using ToyRobotSimulator.Domain.Entities;

namespace ToyRobotSimulator.Domain.Interfaces;

public interface IRobotCommand
{
    void Execute(Robot robot, Table table);
}
