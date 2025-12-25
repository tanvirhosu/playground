# Problem analysis
Command-line application that simulates a robot moving on a nxn grid table.
- Domain constraints:
  - Table dimensions: nxn units (i.e. 5x5)
  - Origin point: (0,0) at SOUTH WEST corner
  - Robot can face: NORTH, SOUTH, EAST, WEST
  - Robot must never fall off the table
- Commands:
  - PLACE X,Y,F: places robot at (X,Y) facing F (must be valid)
  - MOVE: moves robot one unit forward in the direction it is facing
  - LEFT / RIGHT: rotates robot 90 degrees to the left or right
  - REPORT: outputs current position and direction of robot
- Some validation rules to take care of:
  - Ignore all commands before first valid PLACE
  - Ignore invalid PLACE commands (out of bounds)
  - Ignore MOVE commands that would cause falling off table
  - Invalid moves are ignored but don't stop execution

# Design decisions
- Language: C# 14 with .NET 10 SDK
- Project type: Console application
- Architecture: separation of concern based on clean architecture principles:
  - **Domain layer**: core business logic and entities
  - **Application layer**: use cases and orchestration
  - **Infrastructure layer**: technical concerns (I/O operations)
    ```
    src/
    ├── Domain/
    │   ├── Entities/         # Robot, Position, Direction, Table
    │   ├── Services/         # RobotService
    │   └── Interfaces/       # Contracts
    ├── Application/
    │   ├── Commands/         # Command handlers
    │   └── Parsers/          # Command parsers
    ├── Infrastructure/
    │   └── IO/               # Console I/O
    └── Program.cs            # Entry point: composition root or bootstrap
    ```
- Patterns applied:
  - **Strategy pattern**: robot command abstraction with concrete implementations
  - **Factory pattern**: create appropriate robot command objects based on string input (command parsing)
