# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/neakvigran/itcs382-lab.git

## Tools

| Item | Version or name |
| --- | --- |
| JDK | 25.0.5-ea |
| Maven | 3.9.12 |
| IDE | Visual Studio Code 1.132.0 |
| SonarQube for IDE | 6.1.1 |
| AI tool and exact model | ChatGPT / GPT-6 |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change: f829079

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | `java:S106` | `Main.java:9` | Replace this use of `System.out` by a logger. |
| 2 | `java:S106` | `Main.java:15` | Replace this use of `System.out` by a logger. |
| 3 | `java:S106` | `Main.java:17` | Replace this use of `System.out` by a logger. |
| 4 | `java:S106` | `Main.java:22` | Replace this use of `System.out` by a logger. |
| 5 | `java:S2143` | `ParcelService.java:1` | Use the `java.time` API for date and time. |
| 6 | `java:S1128` | `ParcelService.java:6` | Remove this unused import `java.util.Date`. |
| 7 | `java:S1068` | `ParcelService.java:12` | Remove this unused `"maxParcels"` private field. |
| 8 | `java:S106` | `ParcelService.java:30` | Replace this use of `System.out` by a logger. |
| 9 | `java:S4973` | `ParcelService.java:42` | Strings and Boxed types should be compared using `"equals()"`. |
| 10 | `java:S106` | `ParcelService.java:67` | Replace this use of `System.out` by a logger. |
| 11 | `java:S106` | `ParcelService.java:69` | Replace this use of `System.out` by a logger. |
| 12 | `java:S2095` | `ParcelService.java:76` | Use try-with-resources or close this `"FileWriter"` in a `"finally"` clause. |
| 13 | `java:S108` | `ParcelService.java:80` | Remove this block of code, fill it in, or add a comment explaining why it is empty. |

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 2026-10-21 |
| AI tool and exact model | ChatGPT / GPT-6 |
| Commit reviewed | f829079 |
| How the AI read the files (attached the files, opened the folder, or pasted the files) | attached the files |
| Output file in the reviews folder | reviews/parcelpoint-review.md |

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | Main.java:9 | Replace System.out by a logger. | SonarQube | java:S106 | Yes, System.out is used. However, console output is required for this command-line program, so retaining intentional output can be justified. |
| 2 | Main.java:15 | Replace System.out by a logger. | SonarQube | java:S106 | Yes, System.out is used for command output, although console output is intentional in this program. |
| 3 | Main.java:17 | Replace System.out by a logger. | SonarQube | java:S106 | Yes, System.out is used for command output, although console output is intentional in this program. |
| 4 | Main.java:22 | Replace System.out by a logger. | SonarQube | java:S106 | Yes, System.out is used. However, console output is required for this command-line program, so retaining intentional output can be justified. |
| 5 | ParcelService.java:1 | Use the java.time API for date and time. | SonarQube | java:S2143 | No, the supplied source does not use a date/time API. The Date import is unused, so there is no date operation to replace. |
| 6 | ParcelService.java:6 | Remove the unused java.util.Date import. | Both | java:S1128; code smell | Yes, Date is imported but never used. |
| 7 | ParcelService.java:12 | Remove the unused maxParcels field. | Both | java:S1068; code smell | Yes, maxParcels is declared but never read or used to enforce a limit. |
| 8 | ParcelService.java:30 | Replace System.out by a logger. | SonarQube | java:S106 | Yes, the service prints an SMS message directly. A separate notification component would improve separation of concerns. |
| 9 | ParcelService.java:42 | Compare parcel codes using equals() instead of ==. | Both | java:S4973; probable bug | Yes, == compares String references, not their contents. This can prevent a parcel from being found when picking it up. |
| 10 | ParcelService.java:67 | Replace System.out by a logger. | SonarQube | java:S106 | Yes, System.out is used for staff reporting. Whether to retain it depends on the intended console-output design. |
| 11 | ParcelService.java:69 | Replace System.out by a logger. | SonarQube | java:S106 | Yes, System.out is used for staff reporting. Whether to retain it depends on the intended console-output design. |
| 12 | ParcelService.java:76-80 | FileWriter is not closed or flushed reliably. | Both | java:S2095; resource management | Yes, the writer is not closed. Try-with-resources would ensure proper cleanup and flushing. |
| 13 | ParcelService.java:80-81 | The empty catch block silently ignores file-writing errors. | Both | java:S108; error handling | Yes, an IOException can be swallowed without informing the user or recording the failure. |
| 14 | Main.java:11-17 | Missing command arguments can cause ArrayIndexOutOfBoundsException. | AI | Input validation; probable bug | Yes, commands such as store with too few arguments can access array positions that do not exist. |
| 15 | Main.java:13,17 | Invalid numeric input can cause NumberFormatException. | AI | Exception handling; input validation | Yes, Integer.parseInt() can throw NumberFormatException for non-numeric input. |
| 16 | Main.java:8-26 | The Scanner wrapping System.in is not closed. | AI | Resource management | Yes, the Scanner is never closed explicitly. Cleanup should be considered at the end of command processing, without closing System.in prematurely. |
| 17 | ParcelService.java:28,44,68; Parcel.java:7,13 | Parcel status values are hard-coded strings across classes. | AI | Connascence of convention; code smell | Yes, several classes must agree on exact strings such as NEW, STORED and PICKED_UP. An enum would reduce the risk of inconsistent values. |
| 18 | ParcelService.java:39-50 | A failed pickup returns 0, which is indistinguishable from a successful pickup with no fee. | AI | Error handling; ambiguous return value | Yes, a missing parcel and a successful pickup with zero fee both return 0. |
| 19 | ParcelService.java:24-33 | Duplicate parcel codes are not prevented. | AI | Input validation; probable bug | Yes, storeParcel() does not check whether a parcel with the same code already exists. |
| 20 | ParcelService.java:39-46 | Negative storage days are accepted. | AI | Input validation; probable bug | Yes, negative days are not rejected and are treated as zero-fee days. |
| 21 | ParcelService.java:24-36 | storeParcel() combines locker selection, parcel updates, notification and persistence. | AI | Separation of concerns; method design | Yes, the method coordinates several responsibilities that could be delegated to focused components or methods. |
| 22 | Main.java:6-27 | main() contains a long conditional chain for command parsing and execution. | AI | Method complexity; maintainability | Yes, the main method handles input, dispatch, parsing and output in one long chain of conditions. |
| 23 | Main.java:11 | Splitting input on one space does not handle repeated spaces reliably. | AI | Input parsing; probable bug | Yes, split(" ") can produce empty tokens when multiple spaces occur between arguments. |
| 24 | ParcelService.java:74-81 | File saving overwrites the CSV and there is no startup loading. | AI | Persistence; maintainability | No, overwriting the file with a complete in-memory snapshot can be intentional. Startup loading could be useful, but the stated lab behavior does not explicitly require restoring data after a restart. |
| 25 | ParcelService.java:64-72 | printParcels() has logical cohesion because a boolean selects different reporting behavior. | AI | Logical cohesion | Yes, the forStaff flag changes which reporting operation is performed. Separate reporting methods would make the behavior clearer. |
| 26 | Main.java:6-27 | main() has logical cohesion because it selects among command operations based on input. | AI | Logical cohesion | Yes, the method selects different command-processing paths according to the entered command. |
| 27 | ParcelService.java:14-18 | The constructor has temporal cohesion because it initializes the lockers. | AI | Temporal cohesion | No, constructing the service and initializing its locker collection are related setup activities. This is not clearly a weak-cohesion problem. |
| 28 | ParcelService.java:24-36 | storeParcel() has procedural rather than functional cohesion. | AI | Procedural cohesion | No, the operations are closely related to the single goal of storing a parcel. The method has several responsibilities, but the specific cohesion classification is debatable. |
| 29 | ParcelService.java:39-50 | pickUp() has procedural rather than functional cohesion. | AI | Procedural cohesion | No, the operations collectively implement the pickup process. Separating persistence or fee calculation may improve design, but procedural cohesion is not conclusively established. |
| 30 | ParcelService.java:9-82 | ParcelService combines parcel storage, fee calculation, notifications, reporting and file persistence. | AI | Weak class cohesion; separation of concerns | Yes, the class handles several distinct responsibilities that could be separated into dedicated classes. |
| 31 | Parcel.java:7; ParcelService.java:28,44,68 | ParcelService directly accesses and modifies Parcel.status, exposing internal state. | AI | Content coupling; encapsulation | Yes, status is public, so ParcelService modifies the field directly instead of using controlled behavior. |
| 32 | Main.java:19-21 | A boolean parameter controls the behavior of printParcels(). | AI | Control coupling | Yes, Main passes true or false to choose different reporting behavior. Separate methods would avoid the control flag. |
| 33 | Main.java:22 | Main obtains the entire locker collection to count free lockers. | AI | Stamp coupling; strong coupling | Yes, Main uses the returned collection and inspects Locker objects when it only needs a free-locker count. A count method in ParcelService would reduce coupling. |
| 34 | ParcelService.java:25-31 | ParcelService depends on both Locker and Parcel through getters and state-changing methods. | AI | Coupling | No, these classes are essential to parcel-service behavior, and use of public getters and methods alone does not prove excessively strong coupling. |
| 35 | Parcel.java:7,13; ParcelService.java:26,56-61 | Parcel and Locker sizes rely on the same numeric-code convention. | AI | Connascence of convention | Yes, both classes represent sizes with integers, so their meaning must remain consistent. A shared enum would make the relationship clearer. |
| 36 | Main.java:13; Parcel.java:9 | The Parcel constructor relies on the order code, studentId and size. | AI | Connascence of position | Yes, the first two parameters are both Strings, so exchanging them would compile but associate the values incorrectly. |
| 37 | Main.java:17; ParcelService.java:39 | Pickup relies on passing code before days. | AI | Connascence of position | No, the parameters have different types (String and int), so reversing them would be caught by the compiler. The claimed risk is weak. |
| 38 | ParcelService.java:20-21 | getLockers() returns the internal mutable locker list. | AI | Encapsulation; collection exposure | Yes, callers can add or remove lockers directly. Returning a defensive copy or an unmodifiable view would better protect the collection. |
| 39 | Locker.java:25-26 | setParcel() allows assignment or removal without validating locker availability or parcel size. | AI | Encapsulation; invariant protection | Yes, the public setter can bypass the checks performed by storeParcel(). Validation or restricted mutation would help preserve locker invariants. |

**2.1 Name two problems that only the AI review found.**

The AI found that `Main.java:11–17` does not validate the number of command arguments, which can cause an `ArrayIndexOutOfBoundsException`. It also found that `ParcelService.java:24–33` allows storing a parcel with a duplicate code. Neither problem was reported by SonarQube.

**2.2 Name one problem that only SonarQube found, or write none.**

SonarQube reported `java:S106` at `Main.java:9`, recommending that `System.out` be replaced by a logger. The AI review did not report this specific issue. However, console output is necessary for this command-line program, so this finding can reasonably be kept with an explanation.

**2.3 Name one AI finding that was wrong or too vague, and explain why.**

The AI claimed that `ParcelService.java:25–31` has strong coupling because it depends on `Locker` and `Parcel` through getters and state-changing calls. This finding is too vague because these classes must interact to store parcels, and using getters does not automatically mean that coupling is excessively strong. A more specific example of strong coupling is `Main.java:22`, where `Main` accesses the entire locker collection just to count free lockers.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | Kept | Console output is intentionally used to display information to the user in this command-line program. |
| 2 | Kept | Console output is intentionally used to display command results to the user. |
| 3 | Kept | Console output is intentionally used to display command results to the user. |
| 4 | Kept | Console output is intentionally used to display command results to the user. |
| 5 | Kept | The finding was not applicable because the source did not use a date/time API; the unused `Date` import was removed instead. Commit: `88646bf`. |
| 6 | Fixed | Removed the unused `java.util.Date` import. Commit: `88646bf`. |
| 7 | Fixed | Removed the unused `maxParcels` field. Commit: `2f13ca6`. |
| 8 | Kept | Console output is intentionally used to notify the user when a parcel is stored. |
| 9 | Fixed | Replaced `==` with `.equals()` to compare parcel codes correctly. Commit: `e70c82f`. |
| 10 | Kept | Console output is intentionally used to display parcel information for staff. |
| 11 | Kept | Console output is intentionally used to display parcel information for staff. |
| 12 | Fixed | Used try-with-resources to close the `FileWriter` automatically. Commit: `47475c1`. |
| 13 | Fixed | Added error reporting to the previously empty catch block. Commit: `147830a`. |

Number of SonarQube issues that remain after your fixes: 8

The remaining findings concern intentional console output using `System.out` and `System.err`. The `System.err` finding appeared after adding error reporting.

The design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Control coupling | Replaced the boolean-controlled `printParcels()` method with separate `printWaitingParcels()` and `printParcelsForStaff()` methods. | `50d33e0` |
| Stamp coupling | Added `getFreeLockerCount()` to `ParcelService` so `Main` no longer needs to inspect the entire locker collection to count free lockers. | `90ea319` |
| Encapsulation | Changed `getLockers()` to return a defensive copy of the internal locker list. | `60e967c` |

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
Commands: store <code> <studentId> <size 1-3>, pickup <code> <days>, list, staff, quit
store P1 6501 2
SMS to 6501: parcel P1 is in locker 2
Locker 2
pickup P1 5
Fee: 30.0
staff
P1 6501 PICKED_UP
Free lockers: 3
quit

Content of parcels.csv: P1,PICKED_UP
```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | 2026-10-21 | 2026-10-21 |
| AI tool and exact model | ChatGPT / GPT-6 | ChatGPT / GPT-6 |
| Prompt (B or C as given, or your changed prompt in full) | Prompt B as given, with the BikeShare specification inserted | Prompt C as given, with the BikeShare specification inserted |
| Builds with mvn package (yes or no) | Yes | Yes |
| Number of classes, interfaces, enums and records | 1 class, 0 interfaces, 0 enums, 0 records | 7 classes, 1 interface, 1 enum, 1 record |
| SonarQube issues, total | 26 | 20 |
| SonarQube issues, by rule | `java:S106`: 20; `java:S2386`: 3; `java:S1220`: 1; `java:S3776`: 1; `java:S6213`: 1 | `java:S1220`: 10; `java:S106`: 10 |
| AI review findings in Check 1 | 8 | 10 |
| AI review findings in Check 2 | 11 | 10 |
| Review output file in the reviews folder | `reviews/bikeshare-poor-review.md` | `reviews/bikeshare-good-review.md` |

**4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.**

No. SonarQube did not report cohesion or coupling problems in bikeshare-poor. Its findings were code-quality issues such as `java:S106` "Replace this use of System.out by a logger", `java:S2386` "Make this member protected", and `java:S3776` "Refactor this method to reduce its Cognitive Complexity".

**4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.**

Yes. The AI reported control coupling in bikeshare-poor because `process(Scanner, boolean renting)` changes its behavior based on the boolean `renting` parameter. This finding does not appear in the bikeshare-good review.

**4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?**

I would rather extend bikeshare-good because member types are represented by the `MemberType` enum and fee calculation is separated into `FeeCalculator`. Adding a visitor would therefore require smaller, more localized changes instead of modifying shared state and conditional logic in one large class.

## Task 5. Reflection

**5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.**

SonarQube checks code-quality issues such as possible bugs, code smells, resource handling, and excessive complexity. In this lab, it detected issues such as using `==` for String comparison, an unclosed `FileWriter`, unused fields, and high cognitive complexity, but it did not directly evaluate cohesion, coupling, or connascence.

**5.2 When should you not trust the AI review? Give an example from this lab.**

I should not trust an AI review without checking the actual source code because the AI can make incorrect or overly broad claims. For example, the ParcelPoint AI review described some interactions between `ParcelService`, `Locker`, and `Parcel` as strong coupling even though those classes naturally need to interact to perform the application's operations.

**5.3 What will you do differently in Project Phase 2 after this lab?**

In Project Phase 2, I will use both automated analysis and manual design review instead of relying on only one tool. I will check SonarQube findings, review cohesion, coupling, and connascence myself, and verify AI findings against the actual code before making design decisions.

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| ChatGPT / GPT-6 | Review the ParcelPoint starter code for code quality and design quality | `reviews/parcelpoint-review.md`; review findings used in `LAB_REPORT.md` Task 2 | Compared the AI findings with the actual Java source code and SonarQube findings. I checked each AI finding and recorded whether it was correct. |
| ChatGPT / GPT-6 | Generate the intentionally poor BikeShare design using Prompt B | `bikeshare-poor/pom.xml`; `bikeshare-poor/src/main/java/BikeShare.java` | Built the project with Maven, ran the program, tested a rental and return, and checked `rides.csv`. I also analyzed the design with SonarQube and Prompt A. |
| ChatGPT / GPT-6 | Generate the good BikeShare design using Prompt C | `bikeshare-good/pom.xml`; all Java files under `bikeshare-good/src/main/java/` | Built the project with Maven, ran the program, tested a rental and return, checked `rides.csv`, and analyzed the design with SonarQube and Prompt A. |
| ChatGPT / GPT-6 | Help implement and explain selected ParcelPoint fixes | Changes to `parcelpoint/src/main/java/parcelpoint/Main.java` and `ParcelService.java` | Built and ran the program after each change, checked the output, checked `parcels.csv`, and committed each verified change separately. |
