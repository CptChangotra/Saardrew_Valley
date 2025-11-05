package general.incidentsTests

import de.unisaarland.cs.se.selab.farms.Machine
import de.unisaarland.cs.se.selab.incidents.BrokenMachine
import de.unisaarland.cs.se.selab.map.MapController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BrokenMachineTests {
    val mockMapController: MapController = Mockito.mock(MapController::class.java)

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test machine breaks down`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = 1
        )
        val machineId = 1
        val duration = 5
        val machines = listOf(machine)

        val brokenMachineIncident = BrokenMachine(1, 1, mockMapController, machineId, duration, machines)

        brokenMachineIncident.apply()

        assertEquals(5, machine.isReady)
    }

    @Test
    fun `broken machine on already broken machine`() {
        // Given

        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = -1
        )
        val machineId = 1
        val duration = 5
        val machines = listOf(machine)

        val brokenMachineIncident = BrokenMachine(1, 1, mockMapController, machineId, duration, machines)

        brokenMachineIncident.apply()

        assertEquals(-1, machine.isReady)
    }

    @Test
    fun `machine broken for 4 ticks is ready on 5th tick`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = 1
        )
        val machineId = 1
        val breakdownDuration = 4
        val machines = listOf(machine)
        val incidentTick = 1

        val brokenMachineIncident = BrokenMachine(
            1,
            incidentTick,
            mockMapController,
            machineId,
            breakdownDuration,
            machines
        )

        brokenMachineIncident.apply()

        assertEquals(breakdownDuration, machine.isReady)

        // Simulate ticks
        for (currentTick in incidentTick until breakdownDuration + 2) {
            val isMachineReadyForWork = machine.isReady != -1 && machine.isReady <= currentTick
            if (currentTick < breakdownDuration) {
                // For ticks 1, 2, 3, 4 the machine should not be ready
                assertEquals(false, isMachineReadyForWork, "Machine should be broken in tick $currentTick")
            } else {
                // At tick 5 and onwards, the machine should be ready
                assertEquals(true, isMachineReadyForWork, "Machine should be ready in tick $currentTick")
            }
        }
    }

    @Test
    fun `machine with isReady -1 is never ready`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = -1 // Machine is permanently broken
        )
        val machines = listOf(machine)

        // Simulate multiple ticks to ensure the machine never becomes ready
        for (currentTick in 1..10) {
            val isMachineReadyForWork = machine.isReady != -1 && machine.isReady <= currentTick
            assertEquals(false, isMachineReadyForWork, "Machine should not be ready in tick $currentTick")
        }

        // Also, ensure that another BrokenMachine incident doesn't change its state
        val brokenMachineIncident = BrokenMachine(1, 1, mockMapController, 1, 5, machines)
        brokenMachineIncident.apply()
        assertEquals(-1, machine.isReady, "isReady should remain -1 after another breakdown")
    }

    @Test
    fun `machine broken for 1 tick is ready on the next tick`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = 1 // Machine is initially ready
        )
        val machines = listOf(machine)
        val incidentTick = 1
        val breakdownDuration = 1

        val brokenMachineIncident = BrokenMachine(
            1,
            incidentTick,
            mockMapController,
            machine.id,
            breakdownDuration,
            machines
        )
        brokenMachineIncident.apply()

        // The machine is broken in the current tick (1) and will be ready in the next tick (2).
        val readyTick = breakdownDuration
        assertEquals(readyTick, machine.isReady)

        // In the next tick, the machine should not be ready.
        var isMachineReadyForWork = machine.isReady != -1 && machine.isReady < incidentTick
        assertEquals(false, isMachineReadyForWork, "Machine should not be ready in tick $incidentTick")

        // In the next to next tick, the machine should be ready.
        val nextTick = breakdownDuration + 1
        isMachineReadyForWork = machine.isReady != -1 && machine.isReady <= nextTick
        assertEquals(true, isMachineReadyForWork, "Machine should be ready in tick $nextTick")
    }

    @Test
    fun `test getMachines getter`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = 1
        )
        val machines = listOf(machine)

        val brokenMachineIncident = BrokenMachine(1, 1, mockMapController, 1, 5, machines)
        assertEquals(machines, brokenMachineIncident.machines, "getMachines should return the machines list")
    }

    @Test
    fun `machine with duration -1 becomes permanently broken`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = 5 // Machine is initially ready in 5 ticks
        )
        val machines = listOf(machine)
        val breakdownDuration = -1 // Permanent breakdown

        val brokenMachineIncident = BrokenMachine(1, 1, mockMapController, 1, breakdownDuration, machines)
        brokenMachineIncident.apply()

        assertEquals(-1, machine.isReady, "Machine should become permanently broken when duration is -1")
    }

    @Test
    fun `machine not found in list throws error`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = 1
        )
        val machines = listOf(machine)
        val nonExistentMachineId = 999 // Machine ID that doesn't exist in the list

        val brokenMachineIncident = BrokenMachine(1, 1, mockMapController, nonExistentMachineId, 5, machines)

        // This should throw an error because the machine is not found
        try {
            brokenMachineIncident.apply()
            assertEquals(true, false, "Expected an error to be thrown when machine is not found")
        } catch (e: IllegalStateException) {
            assertEquals("machine should not be null", e.message, "Expected specific error message")
        }
    }

    @Test
    fun `machine with higher isReady value keeps higher value`() {
        val machine = Machine(
            id = 1,
            name = "Demomachine",
            actions = listOf("MOWING", "HARVESTING"),
            plants = listOf("PUMPKIN"),
            duration = 2,
            lastShedLocation = 4,
            location = 7,
            currentAction = null,
            farmId = 1,
            isReady = 10 // Machine is ready in 10 ticks
        )
        val machines = listOf(machine)
        val breakdownDuration = 5 // Shorter duration than current isReady

        val brokenMachineIncident = BrokenMachine(1, 1, mockMapController, 1, breakdownDuration, machines)
        brokenMachineIncident.apply()

        // Machine should keep the higher value (10) since max(5, 10) = 10
        assertEquals(10, machine.isReady, "Machine should keep higher isReady value")
    }
}
