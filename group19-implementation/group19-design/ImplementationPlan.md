# Implementation Plan

## Responsibilities

The main modules we decided to work on are:
 - **Map**
     - Tiles
     - Map Controller
 - **Farms**
     - Machine Router
     - Farm Controller & Actions
 - **Scenario**
     - Incidents
     - Clouds

The following table summarizes the primary responsibilities for each team member, including their main implementation tasks and assigned testing duties.

| Responsible      | Implementation                                   | Tests                    |
|------------------|--------------------------------------------------|--------------------------|
| Yun-Jhu Wang     | Implementation of the Map                        | Farm Controller          |
| Jawad Jbara      | Implementation of the Map                        | Machine Router           |
| Ivan Burmistrov  | Implementation of the Machine Router             | Map & Incidents          |
| Maryna Dernovaia | Implementation of the Farm Controller & Actions  | Clouds                   |
| Vaasu Changotra  | Implementation of the Clouds                     | Incidents                |
| Andrei Covaci    | Implementation of the Incidents & Parser         | Map & Machine Router     |
| Saad Malick      | Implementation of the Incidents & Parser         | Farm Controller & Clouds |

For people working on the same module, we will roughly split responsabilites as such:
 - Andrei Covaci: (Incidents) Bee Happy, Cloud Creation, (Parser) validation
 - Saad Malick: (Incidents) City Expand, Animal Attack, Broken Machine, Drought, (Parser) file reading & parsing JSON
 - Yun-Jhu Wang: (Map) path finding functions
 - Jawad Jbara: (Map) Tile & Environmental Modifications


### Plan Justification

This is roughly how the specification is split, and how we designed our class "packages". Hence, it is a good way to also split responsabilities along these lines, since there is relatively little coupling between the modules. A different approach might be to "work through" the simulation, one phase at a time (environment factors, then clouds, farms, etc.), and add functionality as needed. We opted for the more decentralised approach. This way, a bug or slow progress in one module may hinder progress for 1-2 people, but the majority can continue working. We will try to implement all public-facing methods first (even if slightly buggy), so that the project builds and can be tested. After that, we will focus on internal logic.

Overall, the three modules seem to require a similar amount of effort. We decided to add more/less people per module based on prior experience with programming. Everyone will start by working on the Parser, and Andrei Covaci will finalise it (since he works on Incidents, which can only be implemented after there is some progress on the other modules). We will try to stick to our plan, but of course, we will recalibrate during the implementation phase based on individual ~~lack of~~ progress.

---

## Timeline

- **Day 1:**
    - implement a working Parser (mainly Andrei Covaci & Saad Malick, potentially with help from others when parsing their modules)
    - create the structure of the project (classes & method definitions; everyone will work on their respective module)
    - start implementing basic functinality with focus on public-facing methods (everyone will work on their respective module)
- **Day 2-3:**
    - finalise Parser (Andrei Covaci & Saad Malick, with help from people depending on the module they are assigned to)
    - Jawad Jbara & Yun-Jhu Wang will finish the Map Controller a.s.a.p. (as many other modules depend on it)
    - write some basic unit tests (everyone, based on their assigned testing duties)
    - generally work on every module (everyone, based on their assigned implementation duties)
- **Day 4:**
    - have a project that builds
    - the map should be fully implemented (by Jawad Jbara & Yun-Jhu Wang)
    - write first integration/system tests (mainly Jawad Jbara & Yun-Jhu Wang)
    - move focus to Incidents (Andrei Covaci & Saad Malick)
    - continue with previous tasks (everyone else)
- **Day 5-7:** 
    - bring most modules to a (semi-)finished stage
        - Ivan Burmistrov - the Machine Assignement logic should be in place, and Sowing Plans should be mostly done
        - Maryna Dernovaia - the Action scheduling should be done, and most Actions should have their desired effect
        - Saad Malick - finish assigned Incidents
        - Vaasu Changotra - Cloud logic should be done
        - Andrei Covaci - finish assigned Incidents
    - write more complex unit/integration tests (mainly people that are done with their modules: Jawad Jbara, Yun-Jhu Wang, Saad Malick, Vaasu Changotra, Andrei Covaci)
- **Day 8-10:**
    - hopefully have a (generally) working project
    - focus on writing integration/system tests (everyone together)
    - debug the inevitably buggy code
- **Day 11:** more debugging & final touches

---

## Usage of Generative AI

**Andrei Covaci:** will not use AI for implementation or testing. Might use GPT-5.0 to find functions/best practices in Kotlin.
**Ivan Burmistrov:** will use Gemini-2.5-pro for the language reference (I'm too lazy to actually read the docs)
**Jawad Jbara:** will use chat gpt (gpt-5-thinking) only for debugging purposes. It won't be used to generate code from scratch but rather for finding errors in code and rewrite it. Also for researching Kotlin libraries and functionality.
**Maryna Dernovaia:** will use gpt 5 as fast version of lookup for kotlin functional features (aka cool preimplemented functions)
**Saad Malick:** will use chat gpt (gpt-5-thinking) only for debugging purposes. Will also be used for rewriting code during debugging. Will also be used for resarching purposes.
**Vaasu Changotra:** will use ChatGPT-5 and GitHub CoPilot for fine tuning and Kotlin Documentation. For debugging, CoPilot if I reach a dead end.
**Yun-Jhu Wang:** will use GPT-5 for looking up Kotlin and debugging.

We are aware of the potential dangers of using these tools and will take full responsibility for any code, documents and other content produced during the group phase.
