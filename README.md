# Saardrew Valley

This is an educational project made by a team of 7 people under the Software Engineering Chair of Saarland University.

## Description

Saardrew Valley is a farm simulation game where players manage a farm, grow crops, and deal with various events. The project is implemented in Kotlin and is built using Gradle.

## Team

*   Yun-Jhu Wang
*   Jawad Jbara
*   Ivan Burmistrov
*   Maryna Dernovaia
*   Vaasu Changotra
*   Andrei Covaci
*   Saad Malick

## Repository Structure

The repository is organized into two main directories:

### 📁 `group19-design/`
Contains all design documentation and diagrams for the project:

- **ClassDiagram.pdf** - UML class diagram showing the structure of all classes and their relationships
- **StateDiagram.pdf** - State machine diagram illustrating game state transitions
- **SequenceParser.pdf** - Sequence diagram for the parser component
- **SequenceSim.pdf** - Sequence diagram for the simulation flow
- **Sequence3.pdf** - Additional sequence diagram for specific interactions
- **ImplementationPlan.md** - Detailed implementation plan and milestones
- **README.md** - Design phase documentation

### 📁 `group19-implementation/`
Contains the actual Kotlin implementation and build configuration:

#### Core Files
- **build.gradle.kts** - Gradle build configuration with dependencies and build tasks
- **settings.gradle.kts** - Gradle project settings
- **gradle.properties** - Gradle properties and configuration
- **gradlew / gradlew.bat** - Gradle wrapper scripts for Unix/Windows
- **Implementation_Report.md** - Comprehensive report on the implementation process

#### Source Code
- **src/** - Main source code directory containing Kotlin implementation
  - Game logic modules (Map, Farms, Scenario)
  - Core game mechanics
  - Event handling systems

#### Configuration & Testing
- **config/** - Configuration files for the game
- **test_farms.json** - Test data for farm configurations
- **test_map.json** - Test data for game map layouts
- **test_scenario.json** - Test data for game scenarios
- **schedule.txt** - Project schedule and timeline

#### Build Artifacts
- **gradle/** - Gradle wrapper files
- **stdout** - Standard output file

## Game Modules

The project is divided into the following modules:

*   **Map:** Handles the game map, including tiles and pathfinding
*   **Farms:** Manages the farm, including machines, actions, and sowing plans
*   **Scenario:** Implements various game events, such as incidents and clouds

## Building and Running

This project uses Gradle as its build system. To build and run the project:

```bash
cd group19-implementation
./gradlew build
```

## Documentation

- Design documentation and UML diagrams can be found in the `group19-design/` directory
- Implementation details are documented in `group19-implementation/Implementation_Report.md`
- Class structure and relationships are visualized in `group19-design/ClassDiagram.pdf`

## Disclaimer

This is an educational project and is not intended for commercial use.

## License

This project is licensed under the MIT License - see the LICENSE file for details.