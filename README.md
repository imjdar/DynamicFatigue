# 🌙 DynamicFatigue | [cite_start]Professional Server Infrastructure [cite: 876]
[cite_start]**Author:** JDAR [cite: 3, 876]
[cite_start]**Version:** 1.21.1 (Java 21) [cite: 4, 13, 876]
[cite_start]**Framework:** Paper API [cite: 20, 43, 876]

---

## 📌 Overview
[cite_start]**DynamicFatigue** is a high-performance, immersive fatigue system designed for technical survival servers[cite: 580, 877]. [cite_start]Unlike traditional plugins that rely on heavy database lookups or complex event interceptions, this system leverages native Minecraft statistics and modern architectural patterns to provide a seamless, lag-free experience[cite: 614, 615, 877, 878].

---

## 🛠 Architectural Highlights
[cite_start]This project was built following professional software engineering principles to ensure maximum scalability and resource optimization[cite: 10, 11, 878]:

* [cite_start]**Flyweight Pattern**: Potion effects are pre-instantiated and cached in memory upon server startup[cite: 620, 621, 879]. [cite_start]This prevents the overhead of creating thousands of new objects during the execution loop, drastically reducing Garbage Collector (GC) pressure[cite: 618, 619, 646, 880].
* [cite_start]**Stateless UI Design**: By utilizing the **Action Bar** (`net.kyori.adventure`) instead of persistent BossBars, the plugin maintains a zero-memory footprint for UI management[cite: 383, 775, 881]. [cite_start]No active player data is stored in the JVM heap, ensuring high performance even with hundreds of concurrent users[cite: 776, 777, 882].
* [cite_start]**Native Data Integration**: The system uses the vanilla `Statistic.TIME_SINCE_REST` to track sleep cycles[cite: 366, 367, 591, 883]. [cite_start]This ensures data persistence is handled natively by the Minecraft engine, requiring **zero** additional disk I/O or external databases[cite: 614, 615, 616, 884].

---

## 🎮 Mechanics & Progression
[cite_start]The system evaluates players every **5 seconds** (**100 ticks**) to maintain high efficiency[cite: 519, 642, 885]. 
| Stage | Trigger (Days) | Effects (Cumulative) |
| :--- | :--- | :--- |
| **Well Rested** | Day 0 | [cite_start]None (Green Action Bar) [cite: 384, 886] |
| **Stage 1** | Day 1-2 | [cite_start]Hunger I [cite: 743, 886] |
| **Stage 2** | Day 3-4 | + [cite_start]Slowness I [cite: 744, 887] |
| **Stage 3** | Day 5-6 | + [cite_start]Weakness I [cite: 745, 888] |
| **Stage 4** | Day 7-8 | + [cite_start]Mining Fatigue I [cite: 746, 888] |
| **Stage 5** | Day 9-10 | + [cite_start]Blindness [cite: 749, 889] |
| **Stage 6** | Day 11-12 | + [cite_start]Darkness [cite: 750, 890] |
| **CRITICAL** | **Day 13+** | [cite_start]**Wither I (Lethal damage)** [cite: 754, 768, 890] |

> [cite_start]**Note**: The fatigue only resets if the player successfully sleeps through a full night, respecting the vanilla core mechanics[cite: 578, 592, 891].

---

## 🚀 CI/CD Integration
[cite_start]This repository is equipped with a **GitHub Actions** workflow that automatically validates and compiles the project upon every push to the `main` branch[cite: 166, 803, 808, 892].

* [cite_start]**Automated Builds**: Uses Java 21 and Gradle to generate the final `.jar` artifact[cite: 808, 809, 893].
* [cite_start]**Environment Validation**: Ensures the code remains compliant with the Paper 1.21.1 API standards[cite: 808, 894].
