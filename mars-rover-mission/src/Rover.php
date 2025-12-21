<?php

namespace MarsRover;

use Exception;

/**
 * Rover exploration: perform a series of movements on a grid with obstacles and report the final position and direction.
 */
class Rover
{
    public function __construct(
        private Grid $grid,
        private Position $position,
        private Direction $direction
    ) {
    }

    public function execute(string $listOfCommands): Result
    {
        $commands = str_split(strtoupper($listOfCommands));

        try {
            foreach ($commands as $command) {
                if ($command === 'L')
                    $this->turnLeft();
                elseif ($command === 'R')
                    $this->turnRight();
                elseif ($command === 'F') {
                    $this->moveForward();
                } else
                    throw new Exception("Invalid command: $command");
            }
        } catch (Exception $e) {
            return new Result(
                $this->position,
                $this->direction,
                obstacleFound: true,
                obstaclePosition: $this->position
            );
        }

        return new Result($this->position, $this->direction);
    }

    private function turnLeft(): void
    {
        $directions = Direction::cases();
        $roverDirectionIndex = array_search($this->direction, $directions);
        $roverNewDirectionIndex = ($roverDirectionIndex - 1 + 4) % 4;
        $this->direction = $directions[$roverNewDirectionIndex];
    }

    private function turnRight(): void
    {
        $directions = Direction::cases();
        $roverDirectionIndex = array_search($this->direction, $directions);
        $roverNewDirectionIndex = ($roverDirectionIndex + 1 + 4) % 4;
        $this->direction = $directions[$roverNewDirectionIndex];
    }

    private function moveForward(): void
    {
        $nextPosition = $this->calculateNextPosition();

        if (!$this->grid->isValidPosition($nextPosition))
            return;

        if ($this->grid->hasObstacle($nextPosition)) {
            throw new Exception("Obstacle found at " . $nextPosition->x . " " . $nextPosition->y);
        }

        $this->position = $nextPosition;
    }

    private function calculateNextPosition(): Position
    {
        $nextX = $this->position->x;
        $nextY = $this->position->y;

        switch ($this->direction) {
            case Direction::North:
                $nextY++;
                break;
            case Direction::East:
                $nextX++;
                break;
            case Direction::South:
                $nextY--;
                break;
            case Direction::West:
                $nextX--;
                break;
        }

        return new Position($nextX, $nextY);
    }
}
