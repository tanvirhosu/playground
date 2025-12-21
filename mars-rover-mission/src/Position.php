<?php

namespace MarsRover;

/**
 * Position represents the x and y coordinates of a point on a grid.
 */
class Position
{
    public function __construct(
        public readonly int $x,
        public readonly int $y
    ) {
    }
}
