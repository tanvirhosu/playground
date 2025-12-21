# Mars Rover Mission

## Statement
You’re part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop a software that translates the commands sent from earth to instructions that are understood by the rover. Rovers are expensive, make sure the software works as expected.
- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a collection of commands. (E.g.) FFRRFFFRL
- The rover can move forward (f), left (l) and right (r).
- Suppose we are on a really weird planet that is square (nxn).
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

## Technical decisions

- [Initial approach](./docs/0-initial-approach.md)

## Execution

### Tech requirements
- PHP +v8.5 

### Manual execution via console
Give a look to [manual tests script](./tests/manual/run.sh) for more details.
```bash
php src/index.php < tests/manual/1-happy-path.in
php src/index.php < tests/manual/2-invalid-movement.in
php src/index.php < tests/manual/3-obstacle-detection.in
php src/index.php < tests/manual/4-invalid-grid.in
php src/index.php < tests/manual/5-invalid-obstacle-count.in
php src/index.php < tests/manual/6-invalid-coords.in

# or
sh tests/manual/run.sh
```