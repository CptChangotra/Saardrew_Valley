# Implementation Report

## Individual Contributions

The following table summarizes the actual contributions of each group member, including deviations from the planned responsibilities.

| Member     | Implemented Components | Testing Contributions | Additional Work |
|------------|------------------------|-----------------------|-----------------|
| Yun-Jhu Wang      | Implementation of the Map and Plant| Unit tests for mapController, farmController | System tests for parser, sowing plan, plantation and incidents. |
| Jawad Jbara       | Implementation of the Map and Plant| Unit test for machine router | Writing systemtests about the farm part in general and some validation tests |
| Ivan Burmistrov      | Implementation of the Machine Router| Farm package tests, Incident tests | Bugfixes in actions and Farm Controller. Systemtests for farms, machines and incidents behavior |
| Maryna Dernovaia     | Implementation of the Farm Controller & Actions | System/Unit tests for Clouds | SimulationController, bugfixing Incidents + crazy number of system tests for everything |
| Vaasu Changotra       | Implementation of the Clouds | Unit tests and System Tests for: Cloud logic and all of the Incidents | System tests for testing harvest estimation and apples. Debugging and brainstorming overall order of logging |
| Andrei Covaci       | Implementation of the Parser + smaller contributions to Incidents | Unit/Intergration test for Map, Map Parser, Machine Controller; System tests for general farm behaviour | Small changes or bug fixes to all parts of the project |
| Saad Malick       | Implementation of the Incidents | Unit tests Map Parser & farm controller |  |

---

## Adjustments from the Implementation Plan

- Overall, the core implementation was done according to the plan.
- We finished most parts of the project earlier by 1-2 days (Map Controller, Farm & Machine Controllers, Parser, Cloud Controller), so we got started on system tests eariler
- Deviations from the plan are due to finding & fixing bugs
    - some bug fixes consisted of more substantial rewriting of core logic - this was usually done by the person assigned to the module, with the help of someone else (hence the potential overlap in implementation for some files)
    - other bugs were small, localised logic errors (usually done by the person responsible for the module, and sometimes by someone randomly stumbling upon the bug)
    - especially during the last few days, the overlap between responsabilities became a bit murky as we tried to fix all issues; when fixing bugs in some modules (Incidents, Clouds, etc.), the person making the changes and commits may not have been the one responsible for the module. However, the people assigned to the module in question were always involved when changes were being made to their module

---

## Detailed Timeline

### Yun-Jhu Wang

- **Day 1:** Implementation of Plant and functions in MapController.
- **Day 2:** Implementation of Plant and functions in MapController.
- **Day 3:** Fixing MapController and writing unit tests for MapController.
- **Day 4:** Added new method to MapController and unit tests for FarmController.
- **Day 5:** Wrote system tests for map parser and bee happy.
- **Day 6:** Implemented bee happy, wrote farm parser and some incident system tests.
- **Day 7:** Wrote a full simulation of apple and found bugs.
- **Day 8:** Wrote system tests for scenario parser, apple with various scenarios and found bugs.
- **Day 9:** Wrote system tests for soil moisture, and animal attack and found bugs.
- **Day 10:** Wrote irrigation system tests and more parser test for edge cases.
- **Day 11:** Cleaned up some tests.

### Jawad Jbara
- **Day 1:** Added Plants and worked on enviroment phase methodes in Mapcontroller.
- **Day 2:** Implementing plantation reset methode and the logger
- **Day 3:** fixing small bugs in machinerouter and added a system test for it
- **Day 4:** worked on systemtest for irrigation and cherry
- **Day 5:** fixing the cherry test and reporting bugs to farm package people
- **Day 6:** wrote validation systemtests and a big sowing test
- **Day 7:** fixing some tests
- **Day 8:** writing systemtests about machine continuation
- **Day 9-10:** helping others members in debugging and writing unit tests for farm parser and sowingActions, especially scheduling weeding actions

### Ivan Burmistrov
- **Day 1:** Implemented the MachineRouter(MR) with inevitably many bugs while implementing only 2/3 of the class logic.
- **Day 2:** Been fighting the wrong config of Detekt and therefore split the MR into class and a helper. Implemented 05% of the logic though it did have quite a number of bugs, which I didn't know at the time. Cleaned up the code.
- **Day 3:** Syncing the implementation of MR with FarmController(FC) implementation written by Marina. Finishing up the code, adding the ActionFilters to make the code even more beatiful (every1 has their own standards duh).
- **Day 4:** Writing tests, debugging my own implementation (MR, its helper MachineTurnExecutor(MTE)). Writing tests for Actions and MR, MTE, FC. Many bugs were found, but the real question is, how many more are there to discover?
- **Day 5:** MORE TESTS!1!! Testing Incidents in coordination with whole Farm package. Fixing minor bugs and comprehending how otterly foolish I were to make those.
- **Day 6-10** Just writing systemtests and fixing bugs in MachineRouter, Farms and Incidents. Some slight opmizations here and there. Special thanks goes to the machine level logic (esp Irrigation) that has being changed so many times I aint even got enough fingers to count (I only have about 5 fingers). I dont think I remember what exactly ive done on each one of those days so I'll write it as a huge blob since thats basically what I was doing the whole time.
- **Day 11** Unit tests go brrrr

### Maryna Dernovaia
- **Day 1** : Implementation simple functions in FarmController, starting implementing action rescheduling
- **Day 2** : Finalizing implementing FarmController
- **Day 3** : Implementing all the actions + adjustments in FarmController accordingly
- **Day 4-5** : Writing system/unit tests for Clouds
- **Day 6-7** : Fixing Clouds, writing more system tests for Clouds/Incidents + fixing bugs in Farms/Actions
- **Weekend between day 7 and 8** : Writing million-billion system tests for edge cases and finding most of the mutants
- **Day 8-9** : Writing another million-trillion system tests for incidents/full simulation + fixing bugs in FarmController/Actions/Incidents/Clouds/moving some logic to MapController
- **Day 10** : Fixing last bugs, writing unit tests for Clouds to increase coverage
- **Day 11** : Sleeping

### Vaasu Changotra
- **Day 1:** Wrote the pseudocode and made notes on paper to understand the proper flow of actions.
- **Day 2:** Finalized the logic and started writing the logic onto IDE and pushing.
- **Day 3:** Wrote all the functions needed for CloudController and debugged. Added helper functions to avoid a big code blob.
- **Day 4:** Wrote unit tests for Incident Cloud Creation and debugged CloudController.
- **Day 5:** Debugging Cloud controller order of operation in lists and writing unit tests for Incidents.
- **Day 6:** Started writing Unit test for Clouds to debug the CloudController. Found the bugs on the spot and started debugging them. Started writing Unit tests for incidents
- **Day 7:** Fixed the logging issues for Cloud dissipation due to Village tile or raining. Also brainstormed Cloud creation and helped debug cloud creation. Wrote system tests for clouds and incidents.
- **Day 8:** Refactored CloudController to avoid a blob and added helper functions. Added logic to make the work in accordance with the Incident Phase for City Expansion and Cloud Creation by using a boolean isInCloudPhase. Wrote system tests for broken machine and harvest estimation.
- **Day 9:** To avoid concurrency issues, used a while loop isEmpty check to process all clouds. Made the code cleaner and shorter by integrating code into single function(s) to achieve the same result. Wrote more unit tests for incidents. More brainstorming for tests.
- **Day 10:** To avoid concurrency issues, switched to Array Queue. Wrote system tests for harvest estimate and incidents, and brainstorming for other tests to identify edge cases. Once I passed all component, optional and full cloud tests, I went on to wrote more tests for incidents to achieve highest coverage.
- **Day 11:** Cleaner code and final touches. More system tests.

### Andrei Covaci
- **Day 1:** Started work on the Parser, brainstormed ways too simplify verification process
- **Day 2:** Got parsing & validation for map.json mostly done, started work on the farms; fixed various detekt errors in the project to speed up the build timeline
- **Day 3:** Mostly finished with parsing & validation for farms, started work on scenario parsing & validation
- **Day 4:** Wrapped up with parser, worked a little on Incidents, wrote some tests for the Map
- **Day 5:** Helped Saad with Incidents, started work on unit/integration testing
- **Day 6-7:** Wrote unit tests for Machine router and system tests for sowing plans. General dubugging
- **Day 8:** Tested a bigger log trace against the reference implementation, and started debugging based on errors from there
- **Day 9:** Debugged various part of the project (parser, clouds, farm controller)
- **Day 10:** Fixed logger for farms
- **Day 11:** Added some comments

### Saad Malick
- **Day 1:** Started working on parser
- **Day 2:** Worked on parser more and made tests for parser
- **Day 3:** Conitnued work on parser and Started working on incidents
- **Day 4:** Continued work on incidents
- **Day 5** Finished incidents and did debugging with Andrei
- **Day 6-7:** Did debugging for the parser and for incidents
- **Day 8-9:** Just did debugging to find out where we were going wrong, wrote some system tests for that
- **Day 10** Made unit tests for scenario and big parser and also for harvest actions


---

## Usage of Generative AI

**Andrei Covaci:** will not use AI for implementation or testing. Might use GPT-5.0 to find functions/best practices in Kotlin. **Update**: used Claude Sonnet 4 to write the JSON classes used for JSON parsing

**Ivan Burmistrov:** will use Gemini-2.5-pro for the language reference (I'm too lazy to actually read the docs)

**Jawad Jbara:** will use chat gpt (gpt-5-thinking) only for debugging purposes. It won't be used to generate code from scratch but rather for finding errors in code and rewrite it. Also for researching Kotlin libraries and functionality. Update: chat gpt for thinking about and finding edge cases to test.

**Maryna Dernovaia:** will use gpt 5 as fast version of lookup for kotlin functional features (aka cool preimplemented functions) **upd**: cool guy provided a visualizer written by chat-gpt, which is used pretty often for system tests

**Saad Malick:** ued ai to find kotlin functions and to write strings for tests cases in map parser tests. also used ai to think of edge cases.

**Vaasu Changotra:** will use ChatGPT-5 and GitHub CoPilot for fine tuning and Kotlin Documentation. For debugging, CoPilot if I reach a dead end. **Update**: Used Gemini 2.5 Pro and Claude Sonnet 4 for debugging and testing. Also used the visualiser python script from the forum to visualise the map.

**Yun-Jhu Wang:** used ChatGPT-5 for searching Kotlin functionality and improve methods' complexity.