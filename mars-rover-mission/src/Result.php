<?php

namespace MarsRover;

/**
 * Result of rover exploration: final position and direction.
 */
class Result
{
    public function __construct(
        public readonly Position $position,
        public readonly Direction $direction,
        public readonly bool $obstacleFound = false,
        public readonly ?Position $obstaclePosition = null
    ) {
    }

    public function show(): void
    {
        if ($this->obstacleFound && $this->obstaclePosition) {
            // Format: "O:<x>:<y> <Direction>"

            echo \sprintf(
                'O:%s:%s %s (Obstacle found at %s %s)',
                $this->position->x,
                $this->position->y,
                $this->direction->value,
                $this->obstaclePosition->x,
                $this->obstaclePosition->y
            );
            return;
        }

        echo \sprintf('%s %s %s', $this->position->x, $this->position->y, $this->direction->value);
    }
}
