using System;
using ToyRobotSimulator.Domain.Entities;

namespace ToyRobotSimulator.Domain.Extensions;

/*
 * Direction extensions for turning left/right using enum lookup table.
 * Some direction utilities.
 */
public static class DirectionExtensions
{
    public static Direction TurnLeft(this Direction direction) =>
        (Direction)(((int)direction - 1 + 4) % 4);

    public static Direction TurnRight(this Direction direction) =>
        (Direction)(((int)direction + 1) % 4);

    public static string ToRepresentation(this Direction direction) =>
        direction switch
        {
            Direction.North => "NORTH",
            Direction.East => "EAST",
            Direction.South => "SOUTH",
            Direction.West => "WEST",
            _ => throw new InvalidOperationException($"Unknown direction: {direction}")
        };

    public static bool TryParse(string directionString, out Direction direction)
    {
        direction = Direction.North;

        var parsedDirection = directionString.ToUpper() switch
        {
            "NORTH" => Direction.North,
            "EAST" => Direction.East,
            "SOUTH" => Direction.South,
            "WEST" => Direction.West,
            _ => throw new InvalidOperationException($"Unknown direction: {directionString}")
        };

        direction = parsedDirection;
        return true;
    }
}
