# Problem analysis
Planetary surface (environment)
- Fixed size: n x n to simulate a Mars rover's movement on square grid
- Coordinates without wrapping: movement from (0, 0) to (n-1, n-1)
- Obstacles: checked before moving
  - Fixed number: nobstacles
  - Fixed size: 1 x 1

A rover with
- Position (x, y)
- Direction North, East, South, West

External commands (input)
- F: move forward one step in the current direction
- L: rotate 90 degrees left without changing position
- R: rotate 90 degrees right without changing position

Output (report)
- Final position (x, y)
- Final direction
- If obstacle found → stop execution and report

# Problem design strategy - Keep It Simple
Object-oriented design without over-engineering, just enough to make it work with SRP and OCP:
- Main: main entry point for the application that handles input validation and output reporting
- Grid: manager of boundaries and obstacles
- Position: value for coordinate (x:int, y:int)
- Direction: handle orientation and turning
- Rover: main entity that holds state and execute commands to move and turn
- Result: final state of the rover and any obstacle found


# Problem verification
Data flow: Command string → parsed → executed → result
Scenarios:
- Happy path: valid basic movements, no obstacles.
  ```txt
  Input:
    Grid size: 10x10
    Obstacles: 0
    Start: 0 0 N
    Commands: FFRFLF (Moved North twice, turned Right (East), moved Forward 1, turned Left (North), moved Forward 1)
  Result: 1 3 N
  ```
- Invalid input: e.g. grid size
  ```txt
  Input:
    Grid size: axa
  Result: Invalid grid size
  ```
- Invalid movement: move out of bounds
  ```txt
  Input:
    Grid size: 5x5
    Obstacles: 0
    Start: 0 0 S
    Commands: F
  Result: 0 0 S
  ```
- Obstacle detection: stop execution and report
  ```txt
  Input:
    Grid size: 5x5
    Obstacles: 1
    Obstacle positions: 0 2
    Start: 0 0 N
    Commands: FFF (Move Forward 3 times)
  Result: O:0:1 N (Obstacle found at 0 2)
  ```
