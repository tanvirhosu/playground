# Toy Robot Simulator

## Description
Simulation of a toy robot moving on a square tabletop, i.e: dimensions n units x n units (i.e: 5x5)

The application can read the following commands:
```
PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT
```
- *PLACE* will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.
- The origin (0,0) can be considered to be the SOUTH WEST most corner.
- The first valid command to the robot is a *PLACE* command, after that, any sequence of commands may be issued, in any order, including another *PLACE* command. The application should discard all commands in the sequence until a valid *PLACE* command has been executed.
- *MOVE* will move the toy robot one unit forward in the direction it is currently facing.
- *LEFT* and *RIGHT* will rotate the robot 90 degrees in the specified direction without changing the position of the robot.
- *REPORT* will announce the X,Y and F of the robot. This can be in any form, but standard output is sufficient.
- A robot that is not on the table can choose to ignore the *MOVE*, *LEFT*, *RIGHT* and *REPORT* commands.
- There are no other obstructions on the table surface.
- The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed.
- <ins>**Constraints**</ins>
  - The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot.
  - Any move that would cause the robot to fall must be ignored.

## Technical decisions
- [Initial approach](./docs/0-first-approach.md)

## Execution
- Ensure you have .NET 10 SDK installed (v10.0.101 - C# 14). OS compatibility: cross-platform (macOS, Linux, Windows)
  - Verify before proceeding:
    ```bash
    dotnet --version
    # OR
    dotnet --list-sdks
    ```
  - OR download from https://dotnet.microsoft.com/en-us/download/dotnet/10.0
- Clone the repository
  ```bash
  git clone https://github.com/tanvirhosu/playground/tree/csharp/toy-robot
  ```
- Navigate to the project directory
  ```bash
  cd toy-robot/src
  ```
- Give a look at the project file `ToyRobotSimulator.csproj` to ensure it is correctly configured.
- Build and run the .NET console application
  ```bash
  dotnet build
  dotnet run [--project ToyRobotSimulator.csproj]
  ``` 
- Enter commands interactively in the terminal, use `Ctrl + C` to exit the application. See example below:
  ```bash
  # Interactive example
  PLACE 0,0,NORTH
  MOVE
  REPORT # Output: 0,1,NORTH
  ```
  ```bash
  # Movement test
  PLACE 0,0,NORTH
  MOVE
  REPORT # Output: 0,1,NORTH
  LEFT
  REPORT # Output: 0,1,WEST
  MOVE
  MOVE
  MOVE
  REPORT # Output: 0,1,WEST
  RIGHT
  MOVE
  REPORT # Output: 0,2,NORTH
  ```
  ```bash
  # Boundary test
  PLACE 4,4,NORTH
  MOVE
  REPORT # Output: 4,4,NORTH
  MOVE
  MOVE
  REPORT # Output: 4,4,NORTH
  RIGHT
  MOVE
  REPORT # Output: 4,4,EAST
  MOVE
  MOVE
  REPORT # Output: 4,4,EAST
  ```
  ```bash
  # Invalid commands test
  MOVE
  LEFT
  RIGHT
  REPORT
  PLACE 2,2,SOUTH
  REPORT # Output: 2,2,SOUTH
  ```
  ``` bash
  # Out of bounds test
  PLACE 5,5,NORTH
  REPORT
  PLACE -1,0,NORTH
  REPORT
  PLACE 0,0,NORTH
  REPORT # Output: 0,0,NORTH
  PLACE 0,0,INVALID
  REPORT # Output: 0,0,NORTH
- Alternatively, you can provide commands via a text file using pipe or input redirection:
  ```bash
  cat commands.txt | dotnet run
  # Or
  dotnet run < commands.txt
  ```
  Where `commands.txt` contains, for example:
  ```txt
  PLACE 0,0,NORTH
  MOVE
  REPORT
  ```
