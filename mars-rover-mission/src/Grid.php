<?php

namespace MarsRover;

/**
 * Grid class represents the exploration area boundaries.
 * Stores obstacle positions and provides methods to check for validity and obstacles.
 */
class Grid
{
    private int $width;
    private int $height;
    /** @var Position[] */
    private array $obstacles = [];

    private function __construct(
        int $width,
        int $height,
        array $obstacles = []
    ) {
        $this->width = $width;
        $this->height = $height;
        $this->obstacles = $obstacles;
    }

    // -- Start: Named constructor -- //
    public static function create(int $width, int $height): self
    {
        return new self($width, $height);
    }

    /**
     * @param Position[] $obstacles
     */
    public static function createWithObstacles(int $width, int $height, array $obstacles): self
    {
        return new self($width, $height, $obstacles);
    }
    // -- End: Named constructor -- //

    public function addObstacleFromCoords(array $coords): void
    {
        $this->obstacles[] = new Position($coords[0], $coords[1]);
    }

    public function hasObstacle(Position $position): bool
    {
        foreach ($this->obstacles as $obstacle) {
            if ($obstacle->x === $position->x && $obstacle->y === $position->y) {
                return true;
            }
        }
        return false;
    }

    public function isValidPosition(Position $position): bool
    {
        return $position->x >= 0 && $position->x < $this->width &&
            $position->y >= 0 && $position->y < $this->height;
    }
}
