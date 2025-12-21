<?php

namespace MarsRover;

/**
 * Direction represents the four cardinal directions (North, East, South, West).
 */
enum Direction: string
{
    case North = 'N';
    case East = 'E';
    case South = 'S';
    case West = 'W';

    /**
     * @param string $direction
     * @return Direction
     */
    public static function parseFromString(string $direction): self
    {
        $direction = strtoupper($direction);

        return match ($direction) {
            self::North->value => self::North,
            self::East->value => self::East,
            self::South->value => self::South,
            self::West->value => self::West,
            default => self::North,
        };
    }
}
