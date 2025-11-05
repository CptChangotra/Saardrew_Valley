# Saardrew Valley

An organic farming simulation project developed as part of the Software Engineering Lab at Saarland University. This educational project simulates realistic farming operations including crop cultivation, environmental influences, and farm management.

Designed with the principles of Software Design Patterns as taught in the lectures of Software Engineering Lab at Saarland University. 

PlantUML was used to design the UML diagrams and Sequence Diagrams along with draw.io for the State Diagram.

#### For the most detailed implementation plan of this project, do refer to group19-implementation/Implementation_Plan.md

## Success and Results

Passed **97/100** Mandatory Tests against the Reference (96 needed to pass)

Passed **60/60** Optional Tests

Caught **29/30** bugs against the buggy reference by designing our own System Tests for edge cases.

Achieved **93-97%** Unit test coverage.

All thanks to the team Work and co-operation, these were the key to our success.


## Description

Saardrew Valley is a comprehensive organic farming simulator that models key aspects of sustainable agriculture. The simulation operates in discrete two-week time steps (ticks) and can span multiple years, capturing the complexity of:

- **Crop Management**: Growing field crops (potato, wheat, oat, pumpkin) and plantation crops (apple, grape, almond, cherry)
- **Environmental Dynamics**: Simulating sunlight, soil moisture, cloud movement, and rainfall
- **Farm Operations**: Automated machine-based farming activities including sowing, weeding, mowing, cutting, irrigating, and harvesting
- **Organic Farming Practices**: Crop rotation, natural pest control, and environmental sustainability
- **Dynamic Events**: Random incidents like animal attacks, droughts, bee pollination events, and city expansion

## Disclaimer

This is an educational project developed for the Software Engineering Lab course at Saarland University. It is not intended for commercial use and serves as a learning exercise in:
- Software design and architecture
- Team collaboration and project management
- Test-driven development
- Code quality and maintainability
- Version control and CI/CD practices

**Course**: Software Engineering Lab  
**Institution**: Saarland University, Chair of Software Engineering  
**Academic Year**: Summer Semester 2025

## Team

*   Yun-Jhu Wang
*   Jawad Jbara
*   Ivan Burmistrov
*   Maryna Dernovaia
*   Vaasu Changotra
*   Andrei Covaci
*   Saad Malick

## Repository Structure

The repository is organized into two main directories representing the design and implementation phases of the Software Engineering Lab:

### 📁 `group19-design/`
Contains all design documentation and UML diagrams created during the design phase:

- **ClassDiagram.pdf** - Complete UML class diagram showing all classes, properties, methods, and relationships
- **StateDiagram.pdf** - State machine diagram modeling the simulation flow from initialization to termination
- **SequenceParser.pdf** - Sequence diagram demonstrating the initialization process with max_ticks=0
- **SequenceSim.pdf** - Sequence diagram showing handling of a single simulation tick (tick 32)
- **Sequence3.pdf** - Sequence diagram for handling tick 44 with cloud movement and incidents
- **ImplementationPlan.md** - Detailed time and work plan with task assignments and dependencies
- **README.md** - Design phase documentation and references

### 📁 `group19-implementation/`
Contains the complete Kotlin implementation following the approved design:

#### Core Files
- **build.gradle.kts** - Gradle build configuration with dependencies, detekt code analysis, and test setup
- **settings.gradle.kts** - Gradle project settings
- **gradle.properties** - Gradle properties and JVM configuration
- **gradlew / gradlew.bat** - Gradle wrapper scripts for Unix/Windows platforms
- **Implementation_Report.md** - Comprehensive report documenting individual contributions and responsibilities

#### Source Code Structure
- **src/main/kotlin/** - Main application source code
  - Map module: Tile system, coordinates, categories, pathfinding (BFS algorithm)
  - Farms module: Farm entities, machines, actions, sowing plans
  - Scenario module: Cloud movement, incidents, environmental simulation
  - Parser module: JSON configuration file parsing and validation
  - Simulation module: Core tick-based simulation logic
  
- **src/main/resources/schema/** - JSON schemas for validation
  - Map configuration schema
  - Farms configuration schema
  - Scenario configuration schema

#### Testing
- **src/test/kotlin/** - Unit tests using JUnit 5 and Mockito
- **src/systemtest/kotlin/** - System tests for end-to-end functionality
- Test coverage includes unit tests, integration tests, and comprehensive system tests

#### Configuration & Test Data
- **config/** - Sample configuration files
- **test_farms.json** - Test data for farm configurations with machines and sowing plans
- **test_map.json** - Test data for map layouts with various tile types
- **test_scenario.json** - Test data for scenarios with clouds and incidents
- **schedule.txt** - Project timeline and milestones

#### Build Artifacts
- **gradle/** - Gradle wrapper JAR and properties
- **stdout** - Standard output file for simulation logs

## Simulation Overview

### Core Concepts

**Time System**: The simulation uses a tick-based time system where each tick represents a fortnight (2 weeks). A year consists of 24 ticks (12 months × 2 ticks/month).

**Map System**: The simulation takes place on a 2D map composed of:
- **Octagonal tiles**: Regular octagons for fields, plantations, and forests
- **Square tiles**: Rotated 45° squares filling gaps, used for farmsteads, meadows, villages, and roads

**Tile Categories**:
- **FARMSTEAD**: Storage for machines and harvest
- **FIELD**: Annual crop cultivation (potato, wheat, oat, pumpkin)
- **PLANTATION**: Perennial crops (apple, grape, almond, cherry)
- **FOREST**: Wildlife habitat, source of animal attacks
- **MEADOW**: Natural areas supporting pollinators
- **VILLAGE**: Populated areas that block cloud movement
- **ROAD**: Transportation routes

### Environmental System

**Sunlight**: Monthly average sunshine hours for Saarbrücken, reduced by cloud coverage

**Soil Moisture**: 
- Starts at maximum capacity for each tile
- Decreases over time (100L/tick for planted tiles, 70L/tick for fallow)
- Replenished by rainfall and irrigation
- Critical for plant health and harvest quality

**Clouds**:
- Dynamic entities that move based on airflow patterns
- Rain when containing ≥5,000L water and conditions are met
- Can merge when occupying the same tile
- Dissipate when empty, reaching maximum duration, or entering villages

### Plant Life Cycles

**Field Crops**:
- **Potato**: Sow April-May, harvest September-October (1,000 kg/tile estimate)
- **Wheat**: Sow October, harvest June-July (1,500 kg/tile estimate)
- **Oat**: Sow late March, harvest July-August (1,200 kg/tile estimate)
- **Pumpkin**: Sow May-June, harvest September-October (500 kg/tile estimate)

**Plantation Crops**:
- **Apple**: Cut November/February, harvest September-October (1,700 kg/tile estimate)
- **Almond**: Cut November/February, harvest August-October (800 kg/tile estimate)
- **Cherry**: Cut November/February, harvest July (1,200 kg/tile estimate)
- **Grape**: Cut July-August, harvest September (1,200 kg/tile estimate)

### Farm Operations

**Machines**: Each farm owns multiple machines with specific capabilities:
- **Actions**: SOWING, CUTTING, MOWING, WEEDING, IRRIGATING, HARVESTING
- **Specialization**: Each machine works on specific plant types
- **Duration**: Variable action completion times (measured in days)
- **Pathfinding**: Uses BFS algorithm to navigate map

**Action Prioritization**:
1. Fulfill active sowing plans (by tick, then ID)
2. Harvest ripe plantation crops
3. Harvest ripe field crops
4. Cut plantation trees
5. Machine-specific actions (irrigate, weed fields; irrigate, mow plantations)

**Sowing Plans**: Coordinated by organic farming association to ensure crop diversity and market optimization

### Incidents

Dynamic events that impact the simulation:

- **Cloud Creation**: Instantiates new clouds with specified water content and duration
- **Animal Attack**: Wildlife from forests damages adjoining crops (50% reduction for fields/grapes, 10% for tree fruits)
- **Bee Happy**: Enhanced pollination from meadows increases harvest for blooming plants
- **Drought**: Reduces soil moisture to 0L, permanently damages plantations
- **Broken Machine**: Temporarily or permanently disables farm equipment
- **City Expansion**: Villages grow, converting adjacent tiles and blocking paths

### Harvest Calculation

The harvest estimate is computed every tick considering:
- **Initial estimate**: Set at sowing/cutting or start of simulation
- **Environmental factors**: Sunlight excess (10% penalty per 25h over threshold), soil moisture deficit (50g penalty per 100L shortage)
- **Timing penalties**: Late sowing (20% per tick), missed weeding (10% per tick), late harvesting (plant-specific)
- **Incidents**: Animal attacks, droughts, enhanced pollination

## Project Modules

### Map Module
- Tile system with coordinates and categories
- Adjacency relationships and traversal rules
- Airflow and cloud movement logic
- Breadth-First Search (BFS) pathfinding algorithm
- Environmental properties (soil moisture, sunlight)

### Farms Module
- Farm entities with multiple tiles (farmsteads, fields, plantations)
- Machine management and action execution
- Sowing plan processing
- Action prioritization and scheduling
- Harvest collection and storage

### Scenario Module
- Cloud lifecycle (creation, movement, rain, merging, dissipation)
- Incident system with multiple event types
- Environmental simulation (sunlight, rainfall)
- Time management (tick-based progression)

### Parser Module
- JSON configuration file parsing
- Schema validation using provided JSON schemas
- Error handling and reporting
- Configuration integrity checks

### Simulation Module
- Tick-based simulation engine
- Deterministic execution order (moisture → clouds → farms → incidents → harvest calculation)
- Comprehensive logging system (DEBUG, INFO, IMPORTANT levels)
- Statistics generation and reporting

## Technical Implementation

### Technologies
- **Language**: Kotlin 2.1.21
- **Build System**: Gradle with Kotlin DSL
- **JDK**: Eclipse Temurin 21.0.8 (Java 21 LTS)
- **Testing**: JUnit 5, Mockito-Kotlin
- **Code Quality**: detekt static analysis
- **Coverage**: JaCoCo integration

### Code Quality Standards
- Static analysis with detekt
- Automated style checking
- Documentation requirements for public functions
- Best practices from SE course lectures
- No reflection, no external network connections
- File I/O limited to configuration and output

### Command-Line Interface

```bash
./gradlew run --args="--map <path> --farms <path> --scenario <path> --max_ticks <int> --log_level <level> [--start_year_tick <int>] [--out <path>]"
```

**Required Parameters**:
- `--map`: Path to map configuration JSON
- `--farms`: Path to farms configuration JSON  
- `--scenario`: Path to scenario configuration JSON
- `--max_ticks`: Maximum simulation ticks (1-1000)
- `--log_level`: Logging detail (DEBUG/INFO/IMPORTANT)

**Optional Parameters**:
- `--start_year_tick`: Starting tick within year (1-24, default: 1)
- `--out`: Output file path (default: stdout)

## Building and Running

### Prerequisites
- Java 21 (Eclipse Temurin 21.0.8 or compatible)
- Git for version control
- IntelliJ IDEA (recommended IDE)

### Build Commands

```bash
# Navigate to implementation directory
cd group19-implementation

# Build the project
./gradlew build

# Run tests
./gradlew test

# Run system tests
./gradlew systemTest

# Run code quality checks
./gradlew detekt

# Generate coverage report
./gradlew jacocoTestReport
```

### Running the Simulation

```bash
# Example: Run 50-tick simulation starting in January
./gradlew run --args="--map test_map.json --farms test_farms.json --scenario test_scenario.json --start_year_tick 1 --max_ticks 50 --log_level INFO --out output.log"
```

## Testing Strategy

The project includes comprehensive testing at multiple levels:

### Unit Tests
- Individual class and method testing
- Mocked dependencies using Mockito
- Located in `src/test/kotlin/`
- Run with `./gradlew test`

### Integration Tests
- Testing module interactions
- Validating component integration
- Also in `src/test/kotlin/`

### System Tests
- End-to-end simulation testing
- Configuration file-based scenarios
- Tests run against both implementation and reference
- Mutation testing to ensure test quality
- Located in `src/systemtest/kotlin/`
- Run with `./gradlew systemTest`

### Coverage Goals
- High statement coverage across all modules
- Branch coverage for conditional logic
- Mutation testing score meets project requirements

## Documentation

### Design Documents
- Complete UML class diagram: `group19-design/ClassDiagram.pdf`
- State machine diagram: `group19-design/StateDiagram.pdf`
- Sequence diagrams: `group19-design/Sequence*.pdf`
- Implementation plan: `group19-design/ImplementationPlan.md`

### Implementation Reports
- Contribution documentation: `group19-implementation/Implementation_Report.md`
- Code documentation: Inline KDoc comments throughout source code
- Test documentation: Test class and method descriptions

### Validation
All design documents were reviewed and approved by the Software Engineering Chair through:
- Design Review: Feedback session on initial design
- Design Defense: Approval of design understanding (individual and group)
- Code Review: Implementation feedback session

## Development Process

This project followed a structured software engineering process:

1. **Design Phase** (~2 weeks)
   - UML modeling (class, state, sequence diagrams)
   - Implementation planning with task distribution
   - Design review and defense presentations
   - Specification changes incorporated

2. **Implementation Phase** (~2-3 weeks)
   - Kotlin implementation following approved design
   - Continuous integration with automated testing
   - Code reviews and quality checks
   - Test suite development (unit, integration, system)

3. **Quality Assurance**
   - detekt static analysis (automated)
   - System test validation against reference implementation
   - Mutation testing for test suite quality
   - Manual code review by course staff

## Specification Compliance

The implementation adheres to the detailed specification document provided by the Software Engineering Chair, covering:
- Organic farming simulation mechanics
- Environmental system behavior
- Crop growth and harvest algorithms
- Machine action prioritization
- Incident effects and timing
- Logging format and levels
- JSON configuration schemas


## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Course**: Software Engineering Lab  
**Institution**: Saarland University, Chair of Software Engineering  
**Academic Year**: Summer Semester 2025
