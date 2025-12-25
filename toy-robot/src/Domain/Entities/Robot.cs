using System;
using ToyRobotSimulator.Domain.Extensions;

namespace ToyRobotSimulator.Domain.Entities;

/**
 * Represents the robot with its state and movement capabilities.
 * Encapsulates all robot state and behavior.
 */
public class Robot
{
    private Position _position;
    private Direction _direction;

    public bool IsPlaced { get; private set; }

    public Robot() => (_position, _direction, IsPlaced) = (new Position(0, 0), Direction.North, false);

    public void Place(Position position, Direction direction, Table table)
    {
        if (!table.IsValidPosition(position))
            throw new InvalidOperationException($"Cannot place robot at invalid position: {position}");

        (_position, _direction, IsPlaced) = (position, direction, true);
    }

    public void Move(Table table)
    {
        if (!IsPlaced) return;

        var nextPosition = GetNextPosition(_position, _direction);
        if (table.IsValidPosition(nextPosition)) (_position, IsPlaced) = (nextPosition, true);
    }

    public void TurnLeft()
    {
        if (!IsPlaced) return;
        _direction = _direction.TurnLeft();
    }


    public void TurnRight()
    {
        if (!IsPlaced) return;
        _direction = _direction.TurnRight();
    }

    public string GetReport()
    {
        if (!IsPlaced) return string.Empty;
        return $"{_position.X},{_position.Y},{_direction.ToRepresentation()}";
    }

    private static Position GetNextPosition(Position position, Direction direction) =>
        direction switch
        {
            // Record syntax
            Direction.North => position with { Y = position.Y + 1 },
            Direction.South => position with { Y = position.Y - 1 },
            Direction.East => position with { X = position.X + 1 },
            Direction.West => position with { X = position.X - 1 },
            _ => throw new InvalidOperationException($"Unknown direction: {direction}")
        };
}
