<?php

/**
 * Mars Rover Mission - Main Entry Point for the console application.
 * 
 * This file includes or imports all necessary classes and components required for the Mars Rover Mission application.
 * It serves as the dependency loader for the rover navigation system.
 * 
 * @package MarsRover
 * @author Tanvir Hossain <tanvirhosu@gmail.com>
 */

declare(strict_types=1);

require_once __DIR__ . '/Position.php';
require_once __DIR__ . '/Direction.php';
require_once __DIR__ . '/Grid.php';
require_once __DIR__ . '/Result.php';
require_once __DIR__ . '/Rover.php';

use MarsRover\Grid;
use MarsRover\Rover;
use MarsRover\Position;
use MarsRover\Direction;

function main(): void
{
    // -- Start: Inputs -- //
    // Grid
    $gridSizeStr = readLineInputWithMessage("Enter grid size (N for NxN e.g., 10): ");
    if (!is_numeric($gridSizeStr) || (int) $gridSizeStr <= 0) {
        fwrite(STDERR, "Error: Grid size must be a positive number.\n");
        exit(1);
    }
    $gridSize = (int) $gridSizeStr;
    $grid = Grid::create($gridSize, $gridSize);

    // Obstacles
    $numOfObstaclesStr = readLineInputWithMessage("Enter number of obstacles (e.g., 1): ");
    if (!is_numeric($numOfObstaclesStr) || (int) $numOfObstaclesStr < 0) {
        fwrite(STDERR, "Error: Number of obstacles must be a non-negative number.\n");
        exit(1);
    }
    $numOfObstacles = (int) $numOfObstaclesStr;
    for ($obstacleIndex = 0; $obstacleIndex < $numOfObstacles; ++$obstacleIndex) {
        $obsStr = readLineInputWithMessage("Enter obstacle $obstacleIndex (x y e.g., 1 2): ");
        $obstacleCoords = sscanf($obsStr, "%d %d");

        if (
            !is_array($obstacleCoords) || count($obstacleCoords) !== 2 ||
            !is_numeric($obstacleCoords[0]) || !is_numeric($obstacleCoords[1])
        ) {
            fwrite(STDERR, "Error: Invalid obstacle coordinates. Format: x y\n");
            exit(1);
        }
        $grid->addObstacleFromCoords($obstacleCoords);
    }

    // Rover position and direction
    $roverCoordsWithDirection = readLineInputWithMessage("Enter rover start position and direction (x y d e.g., 0 0 N): ");
    $coordsWithDirection = explode(' ', $roverCoordsWithDirection);
    if (count($coordsWithDirection) !== 3) {
        fwrite(STDERR, "Error: Invalid rover start position. Format: x y D\n");
        exit(1);
    }
    [$x, $y, $directionCharacter] = $coordsWithDirection;

    if (!is_numeric($x) || !is_numeric($y)) {
        fwrite(STDERR, "Error: Rover coordinates must be numbers.\n");
        exit(1);
    }
    $roverStartingDirection = Direction::parseFromString($directionCharacter);
    $roverStartingPosition = new Position((int) $x, (int) $y);

    // Commands
    $commands = readLineInputWithMessage("Enter commands (e.g., FFRRFFFRL): ");
    if (!preg_match('/^[FLR]+$/i', $commands)) {
        fwrite(STDERR, "Error: Commands must be a string of F, L, and R.\n");
        exit(1);
    }
    // -- End: Inputs -- //


    // Rover simulation
    $rover = new Rover($grid, $roverStartingPosition, $roverStartingDirection);
    $result = $rover->execute($commands);

    // Report
    $result->show();
}

main();

function readLineInputWithMessage(string $message): string
{
    echo $message;
    $input = fgets(STDIN);

    if ($input === false) {
        fwrite(STDERR, "\nError: Unexpected end of input.\n");
        exit(1);
    }

    return trim($input);
}
