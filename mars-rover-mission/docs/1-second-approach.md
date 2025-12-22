# Problem analysis
Same conditions as in the initial approach. But can be considered to add some more features or variations to the problem.
- Handle grid wrapping around edges
- Add new commands: Backward 'B', Jump 'J', Undo 'U'
- Add multiple rovers
- Extend planet shape with different types of grids like spherical.

# Problem design strategy - Patterns for Extensible, Maintainable and Testable
- Direction and movement representation with **State Pattern**: the goal is to separate the direction-specific logic from the `Rover` class, instead will delegate the job to `DirectionState` where each direction (North, East, South, West) should know its own state object that handles movement and rotation.

- Inputs parsing with **Command Pattern**: the goal is to separate the input-specific logic from the `Rover::execute` method, instead will delegate the job to `Command` where the Parser should know how to deal with each command (F, B, L, R), so Rover will execute them.

- Abstraction of surface ground with **common definitions**, then different implementations should be applied based on the type of grid or surface (SquareGrid, SphericalGrid, ..).

- Error handling with **custom or generic exceptions** for clarity like *ObstacleFoundException*, *InvalidCommandException*, etc.

- Use automated tests with PHPUnit. Apply **Builder Pattern** to construct different objects for different test scenarios, e.g. `RoverTest` using the *RoverBuilder* withObstacle, withStartingPosition, etc.
