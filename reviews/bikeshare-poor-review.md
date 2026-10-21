Files read: `bikeshare-poor/src/main/java/BikeShare.java` (uploaded as `BikeShare.java`, 145 lines). No other Java files were provided.

## Code Review Findings

| No. | Check   | Code location (file)                 | Finding                                                                                                                                                 | Severity   | Suggested fix                                                                  |
| --- | ------- | ------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------- | ------------------------------------------------------------------------------ |
| 1   | Check 1 | BikeShare.java:39–121                | `process()` is too long and handles renting, returning, fee calculation, file saving, and receipt printing.                                             | Should fix | Split the method into smaller methods with one responsibility each.            |
| 2   | Check 1 | BikeShare.java:18–36                 | `main()` uses an infinite loop with multiple command conditions, mixing command interpretation and execution.                                           | Note       | Extract command handling into a separate method.                               |
| 3   | Check 1 | BikeShare.java:98                    | Fee calculation uses hard-coded values `2` and `3`.                                                                                                     | Should fix | Replace the numbers with named constants.                                      |
| 4   | Check 1 | BikeShare.java:75, 96–98             | Ride information is stored in an `int[]` and accessed through numeric indexes, making its meaning unclear.                                              | Should fix | Introduce a `Ride` class with named fields.                                    |
| 5   | Check 1 | BikeShare.java:100–101               | CSV values are concatenated without escaping member IDs containing commas, quotes, or newlines.                                                         | Should fix | Escape CSV fields before writing them.                                         |
| 6   | Check 1 | BikeShare.java:98                    | Integer multiplication can overflow when calculating fees for very large ride durations.                                                                | Should fix | Use `long` arithmetic or validate the maximum allowed duration.                |
| 7   | Check 1 | BikeShare.java:12–14                 | Mutable global fields allow unrelated code to modify station names, bike locations, and active rides.                                                   | Should fix | Make the fields private and control modifications through methods.             |
| 8   | Check 1 | BikeShare.java:13–14, 103–115        | Bike locations and active rides exist only in memory, while completed rides are saved to disk, so active rentals are lost after restarting the program. | Should fix | Persist active rentals and bike availability if rentals must survive restarts. |
| 9   | Check 2 | BikeShare.java:18–36                 | `main()` has procedural cohesion, coordinating input, command selection, and execution.                                                                 | Note       | Move command processing into a dedicated method.                               |
| 10  | Check 2 | BikeShare.java:39–121                | `process()` has logical cohesion, selecting between two different operations using the `renting` boolean.                                               | Should fix | Replace the boolean-controlled method with separate rent and return methods.   |
| 11  | Check 2 | BikeShare.java:10–143                | `BikeShare` has weak class cohesion because it manages user interaction, bike inventory, rental records, pricing, persistence, and receipts.            | Should fix | Separate these responsibilities into focused classes.                          |
| 12  | Check 2 | BikeShare.java:12–14                 | Public static mutable fields create common coupling for any external class accessing the shared state.                                                  | Should fix | Encapsulate shared state in private instance fields.                           |
| 13  | Check 2 | BikeShare.java:29–31, 39             | The boolean argument `renting` creates control coupling between `main()` and `process()`.                                                               | Should fix | Call separate rent and return operations instead of passing a control flag.    |
| 14  | Check 2 | BikeShare.java:75, 96–98             | The structure of the ride array creates connascence of position, because index `0` must represent the bike and index `1` the member type.               | Should fix | Replace the positional array with a class containing named fields.             |
| 15  | Check 2 | BikeShare.java:67–75, 83–98, 124–134 | Station numbers depend on an agreed numeric convention, creating connascence of convention between station selection and rental processing.             | Note       | Represent stations using an enum instead of numeric codes.                     |
| 16  | Check 2 | BikeShare.java:15–16, 62, 98         | Member types rely on matching integer codes, creating connascence of convention between validation and fee calculation.                                 | Should fix | Replace integer member types with an enum.                                     |
| 17  | Check 2 | BikeShare.java:12–14                 | `stations`, `bikeLocations`, and `rides` are public mutable fields, violating information hiding.                                                       | Should fix | Declare the fields private and provide controlled access where needed.         |
| 18  | Check 2 | BikeShare.java:14                    | The public `rides` map exposes an internal mutable collection, allowing external callers to change active rental records.                               | Should fix | Make the map private and expose only required operations.                      |
| 19  | Check 2 | BikeShare.java:13                    | The public `bikeLocations` array exposes internal inventory state to external modification.                                                             | Should fix | Make the array private and update it through controlled methods.               |

## Method cohesion assessment

| Method            | Lines   | Cohesion   | Reportable?      |
| ----------------- | ------- | ---------- | ---------------- |
| `main()`          | 18–36   | Procedural | Yes — finding 9  |
| `process()`       | 39–121  | Logical    | Yes — finding 10 |
| `chooseStation()` | 124–135 | Sequential | No               |
| `readNumber()`    | 137–143 | Functional | No               |

## Additional checks

| Requested issue                                         | Result    |
| ------------------------------------------------------- | --------- |
| Unclosed resources                                      | Not found |
| Incorrect object comparisons using `==`                 | Not found |
| Unused imports, fields, or parameters                   | Not found |
| Empty blocks                                            | Not found |
| Repeated string literals constituting a clear smell     | Not found |
| Inter-class stamp coupling                              | Not found |
| Inter-class content coupling                            | Not found |
| Classes depending on many other application classes     | Not found |
| Calls reaching through one object to another            | Not found |
| Methods returning internal collections                  | Not found |
| Connascence of algorithm between classes                | Not found |
| Connascence of name or type between application classes | Not found |

Because the provided code contains only one application class, findings about inter-class coupling describe potential external access through its public API rather than demonstrated interactions between multiple application classes.

## Number of findings

Check 1 — Code quality

# 8

Check 2 — Design quality

# 11

Total findings: 19