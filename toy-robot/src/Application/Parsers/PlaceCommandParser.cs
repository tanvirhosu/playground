using ToyRobotSimulator.Domain.Entities;
using ToyRobotSimulator.Domain.Interfaces;
using ToyRobotSimulator.Domain.Extensions;
using ToyRobotSimulator.Application.Commands;

namespace ToyRobotSimulator.Application.Parsers;

public class PlaceCommandParser
{
  public static IRobotCommand? Parse(string[] placeCommandParts)
  {
    if (placeCommandParts.Length < 2) return null;

    var arguments = placeCommandParts[1].Trim();
    var parts = arguments.Split(',');

    if (parts.Length != 3)
      return null;

    try
    {
      var x = int.Parse(parts[0].Trim());
      var y = int.Parse(parts[1].Trim());
      var directionString = parts[2].Trim();

      if (!DirectionExtensions.TryParse(directionString, out var direction))
        return null;

      var position = new Position(x, y);
      return new PlaceCommand(position, direction);
    }
    catch
    {
      return null;
    }
  }
}