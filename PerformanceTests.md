# Performance tests #

JMapper uses the Javassist framework for writing dynamic bytecode, so the generated mapping is equivalent to mapping written statically.
Performance tests are the most difficult to make, because you have to consider different parameters so that the data obtained are real, such as:
  * **JVM warmup**: Running the code will initially be slow and will go worse and worse until you reach the steady state.
  * **Class loading**: The first time you start a benchmark all classes used must be charged.
  * **Just In Time Compiler**: when the JVM identifies a hot part of the code is compiled into machine language.
  * **Garbage collector**: a garbage collection can be invoked during the execution of the benchmark, greatly increasing the time.

All these variables are very well managed by the library Benchmark downloaded at: http://www.ellipticgroup.com/html/benchmarkingArticle.html.

## Hardware ##

The tests were carried out on a machine with the following features:
  * processor: Intel(R) Core(TM) i5-2520M CPU @ 2,50GHz 773MHz
  * RAM: 2,94GB
  * OS: XP sp3
  * JVM: 1.6.0

## Results ##
Below are the results accompanied by a brief description.

Mapping between primitive and wrapper types (`int <-> Integer`)
|mapping|mean|standard deviation|
|:------|:---|:-----------------|
|hand code|158.094 fs (CI deltas: -897.636 as, +1.591 fs)|13.358 ns (CI deltas: -4.451 ns, +10.788 ns) |
|JMapper|160.068 fs (CI deltas: -2.345 fs, +6.515 fs) |41.242 ns (CI deltas: -24.152 ns, +45.822 ns)|

Mapping between equal Arrays (`String[] <-> String[]`)
|mapping|mean|standard deviation|
|:------|:---|:-----------------|
|hand code|115.899 fs (CI deltas: -1.507 fs, +1.942 fs) |27.709 ns (CI deltas: -5.182 ns, +6.500 ns)|
|JMapper|116.097 fs (CI deltas: -1.594 fs, +2.215 fs)|21.411 ns (CI deltas: -4.498 ns, +9.111 ns)|

Mapping between Array to convert (`String[] <-> Integer[]`)
|mapping|mean|standard deviation|
|:------|:---|:-----------------|
|hand code|707.309 fs (CI deltas: -5.970 fs, +7.097 fs) |37.094 ns (CI deltas: -5.323 ns, +10.031 ns)|
|JMapper|698.158 fs (CI deltas: -4.053 fs, +4.980 fs)|25.677 ns (CI deltas: -4.191 ns, +7.743 ns) |

Mapping between collections with the same items but different structures (`List<String> <-> Set<String>`)
|mapping|mean|standard deviation|
|:------|:---|:-----------------|
|hand code|164.124 ps (CI deltas: -1.697 ps, +2.069 ps) |758.374 ns (CI deltas: -115.668 ns, +160.554 ns)|
|JMapper|157.973 ps (CI deltas: -1.232 ps, +1.557 ps)|558.779 ns (CI deltas: -100.508 ns, +127.997 ns)|

Mapping between collections to convert (`List<String> <-> Set<Integer>`)
|mapping|mean|standard deviation|
|:------|:---|:-----------------|
|hand code|25.492 ps (CI deltas: -208.432 fs, +274.701 fs)|192.671 ns (CI deltas: -37.621 ns, +55.091 ns)|
|JMapper|22.665 ps (CI deltas: -199.980 fs, +273.326 fs)|264.898 ns (CI deltas: -61.959 ns, +101.369 ns)|

Mapping between maps with the same items but different structures  (`Map<String, String> <-> SortedMap<String, String>`)
|mapping|mean|standard deviation|
|:------|:---|:-----------------|
|hand code|65.715 ps (CI deltas: -482.950 fs, +638.210 fs)|317.208 ns (CI deltas: -65.186 ns, +82.966 ns)|
|JMapper|65.653 ps (CI deltas: -492.875 fs, +560.095 fs)|301.149 ns (CI deltas: -43.988 ns, +67.963 ns)|

Mapping between maps to convert (`Map<String, Integer> <-> SortedMap<Integer, String>`)
|mapping|mean|standard deviation|
|:------|:---|:-----------------|
|hand code|81.435 ps (CI deltas: -630.198 fs, +848.006 fs)|414.311 ns (CI deltas: -87.921 ns, +140.424 ns)|
|JMapper|90.518 ps (CI deltas: -3.702 ps, +14.551 ps)|3.777 us (CI deltas: -3.220 us, +4.951 us)|