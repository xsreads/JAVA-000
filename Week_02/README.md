学习笔记

Java 8 默认的堆内存是物理内存的 1/4，堆内存小会导致 GC 次数变多，甚至会出现 OOM。

Young GC 只会发生在 Young 区

Full GC Young 区、Old 区都会发生变化。

Minor GC（小型 GC）Young 区的 GC

Major GC（大型 GC）Old 区的 GC

1. 串行 GC（SerialGC）

    -XX: +UserSerialGC 配置串行 GC
    
    串行 GC 对年轻代使用 mark-copy（标记-复制）算法，对老年代使用 mark-sweep-compact（标记-清除-整理）算法。
    
    两者都是单线程的垃圾收集器，不能进行并行处理，所以都会触发全线暂停（STW），停止所有的应用线程。效率低。
    
    CPU 利用率高，暂停时间长。
    
    适合几百 MB 堆内存的 JVM，而且是单核 CPU 时比较有用。
    
    Minor GC：Eden 区和S0 区中的对象复制到 S1 区，同时有部分对象可能会晋升到老年代。
    
    Full GC：

2. 并行 GC（ParallelGC）

    -XX: +UserParallelGC
    
    -XX: +UserParallelOldGC
    
    年轻代和老年代的垃圾回收都会触发 STW 事件。
    
    在年轻代使用 标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理（mark-sweep-compact）算法。
    
    -XX: ParallelGCThreads=N 来指定 GC 线程数，其默认值为 CPU 核心数。
    
    并行垃圾回收器适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到更高的吞吐量。
    - 在 GC 期间，所以 CPU 内核都在并行清理垃圾，所以总暂停时间更短；
    - 在两次 GC 周期的间隔期，没有 GC 线程在运行，不会消耗任何系统资源。
    
    Java 8 默认的垃圾收集器。

    FullGC 时 young 区减少很多，old 区减少比较少

3. CMS GC（ConcMarkSweepGC）

    -XX: +UseConcMarkSweepGC
    
    其对年轻代采用并行 STW 方式的 mark-copy（标记-复制）算法，对老年代主要使用并发 mark-sweep（标记-清除）算法。
    
    如果服务器是多核 CPU，并且主要调优目标是降低 GC 停顿导致的系统延迟，那么使用 CMS 是个很明智的选择。进行老年代的并发回收时，可能会伴随着多次年轻代的 minor GC。

4. G1 GC

    -XX: +UseG1GC
    
    G1 GC 最主要的设计目标是：将 STW 停顿的时间和分布，变成可预期且可配置的。
    
    G1 GC 是一款软实时垃圾收集器，可以为其设置某项特定的性能指标。为了达成可预期停顿时间的指标，G1 GC 有一些独特的实现。
    
    首先，堆不再分成年轻代和老年代，而是划分为多个可以存放对象的小块堆区域。每个小块，可能一会被定义成 Eden 区，一会被指定为 Survivor 区或者 Old 区。