using System;

namespace ToyRobotSimulator.Domain.Entities;

public class Table
{
    public int Width { init; get; }
    public int Height { init; get; }

    public Table(int width = 5, int height = 5)
    {
        if (width <= 0 || height <= 0)
            throw new ArgumentException("Table dimensions must be greater than 0");

        Width = width;
        Height = height;
    }

    public bool IsValidPosition(Position position) =>
        position.X >= 0 && position.X < Width && position.Y >= 0 && position.Y < Height;
}
