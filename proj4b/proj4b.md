# Project 4B: Wordnet Design

这个proj简单来讲就是在阅读Project 4C、4D后，写一份（技术）设计文档。

# Design Doc Guide of Proj 4B

A design document describes an approach to a specific technical problem. 

In the context of software engineering, it serves as the blueprint and specification for a software program or feature.

Design documents are useful in both communicating and organizing your approach, while also being dynamic drafts that encompass the abstract details of the project.

For this class, design documents will consist of the following components:
- Data
  - What data will you be using? What does the data consist of and how will you parse it for your program? How will your program use the data?

- Data Structures
  - What data structures will you be using? How will your data structures use the data? How will your program use the data structures?

- Algorithms
  - What algorithms will you be using? How will your algorithms use the data structures and data? How will your program use the algorithms?

- Complexity
  - What is the input? How does the time and space complexity change with the input? What are the bounds?

- Questions (Optional):
  - Open Questions: What questions have come up while brainstorming your design? Consider asking them in office hours!
  - Closed question: Move questions here once you’ve found an answer. Logging your decision-making process provides essential context for others wishing to understand/contribute to your work.

- Diagram
  - Visualize your data structures and algorithms into a diagram, such that someone seeing your document for the first time could quickly get a general idea of your implementation. You can use any drawing tool you like, but if you are unsure and looking for one, you can use `draw.io`.

# Task 0: Wordnet Spec

阅读proj 4c, proj 4d的说明书。

## Wordnet data

### Overview of Wordnet dataset

In Project 4BCD, you'll be working with Wordnet data consisting of synset and hyponym files.

`WordNet` is a “semantic lexicon for the English language” that is used extensively by computational linguists and cognitive scientists; for example, it was a key component in IBM’s Watson. WordNet groups words into sets of synonyms called synsets and describes semantic relationships between them.

One such relationship is the is-a relationship, which connects a `hyponym` (more specific synset) to a `hypernym` (more general synset). 

For example, “change” is a hypernym of “demotion”, since “demotion” is-a (type of) “change”. “change” is in turn a hyponym of “action”, since “change” is-a (type of) “action”.

`Synset`s consist of one or more words in English that all have the same meaning. Words in English may belong to multiple synsets. Synsets may include not just words, but also what are known as `collocation`s.

A synset may be a hyponym of multiple synsets.

For example, one synset is “jump, parachuting”, which represents the act of descending to the ground with a parachute. “jump, parachuting” is a hyponym of “descent”, since “jump, parachuting” is-a “descent”.

Here is a list of all of the data files for synsets/hyponyms for this project and a quick explanation on what they will do.
- `synsets/hyponyms_size 82191`: The full data set from the NGrams data set!
- `synsets/hyponyms_eecs`: Is a graph containing the names of various courses in ee/cs with two words (“bee” and “bean”) added in for testing. 
- `synsets/hyponyms_size 11,14,16`: Example graphs used from the spec. 
- `synsets/hyponyms_size 10,25,1000`: Subsets of the full data set used for autograder testing.

### Synset File and Hyponym File

We now describe the two types of data files that store the WordNet dataset. 

These files are in comma separated format, meaning that each line contains a sequence of fields, separated by commas.

`synset file`: the first type of file.
- Each line of a synset file provides comma separated information about a synset.
- The first field is the synset id (an integer), the second field is the synonym set (or synset), and the third field is its dictionary definition.
  - e.g. : `6829,Goofy,a cartoon character created by Walt Disney`, means that the synset { Goofy } has an id number of 6829, and its definition is “a cartoon character created by Walt Disney”. 
  - The individual nouns that comprise a synset are separated by spaces (and a synset element is not permitted to contain a space). 
  - The id numbers are useful because they also appear in the hyponym files

`hyponym file`: the second type of file.
- Each line of a hyponym file provides comma separated information about a hyponym.(the hyponym relationships).
- The first field is a synset id, and subsequent fields are the id numbers of the synset’s direct hyponyms.
  - e.g. : `79537,38611,9007`, means that the synset `79537` (“viceroy vicereine”) has two hyponyms: `38611` (“exarch”) and `9007` (“Khedive”), representing that exarchs and Khedives are both types of viceroys (or vicereine).
  - There may be more than one line that starts with the same synset ID. This indicates that both synsets 12 and 13 are direct hyponyms of synset 11. These two could also have been combined on to one line.

## Project 4C Overview

Starter:
- Task 1: Dummy HyponymsHandler(假的HyponymsHandler实现，先占位用)

Hyponyms Button implementation(2 & 3 & 4):
- The button do: 返回输入词及其所有下位词组成的列表。要求按照字母排序、不重复且大小写敏感。
- Task 2: Graph implementation. Implement a directed graph to represent Wordnet(build your own graph class or classes).
- Task 3: Reading the WordNet Dataset files. Parse the Wordnet data files and use graph (from task 2) to store the data.
- Task 4: Traversing the WordNet Graph. Use an algorithm on your directed graph implementation to return the hyponyms of the query.

Handling the lists of words: return common hyponyms of each word in the list.
- Getting the intersection of words, not nodes.
- Task 5: Lists of Words. Modify your `HyponymsHandler` and the rest of your implementation to deal with the list of words case.
- Task 6: Autograder Buddy. Open `AutograderBuddy.java` and fill in the `getHyponymHandler` method such that it returns a HyponymsHandler that uses synsetFile and hyponymFile. Your code here should be quite similar to your code in `Main.java`.

Summary:
- Project 4C 的目标是实现HyponymsHandler。接收网页传来的word(s)，返回其共有的下位word(s)。——> 接收输入，返回输入中每个word共有的字类。
- 输入输出有了，现在需要的是思考输入与输出之间的黑匣子。
- 中间的第一个问题很明显——为了找到字类，我要到数据中去查询。这就引发了接下来的问题：我用来查询的数据是哪里来？数据怎么存储在内存中/什么class（数据结构）？查询的方法（算法）？
  - 数据源是WordNet这一数据库，文件都打包在了data文件夹中。两种文件及其格式解读都在前文简要叙述了。
  - 读取的WordNet数据库的数据怎么存？数据库的数据一张网，用于查询此之间的上下位关系（基类和字类）。这种关系应该首先考虑树或者图。proj的说明已经提出了要使用有向图。——> 实现一个自己的有向图class。
- 查询的方法，以目前的直觉来看就是图的遍历。图的遍历有DFS pre-order, DFS post-order, BFS。具体选择哪种还有待商榷。
- 数据结构确定为图结构，距离完整的实现差“逻辑结构”“数据操作”“存储结构”“代码实现”这几步。
  - 逻辑结构：
    - 数据库有两类文件，一类是synset（word及其ID和一个word的描述），一类是hyponym（各个词/ID之间的关系）。
    - 直觉上感觉：synset充当着节点的角色（vertex），hyponym起着关系连接的作用（directed edge）。
  - 数据操作：
    - 构造函数的构想: 读入两类文件，创建两个实例变量（vertices, edges）
    - 遍历方法：广度优先遍历BFS？
    - 返回所有字类words的列表：以输入为起点，遍历其所有子类并记录每个值。（可能是集合添加值，最后转换为列表）
  - 存储结构：每个synset总共有三个字段的信息（字段id，同义词的集合，该synset的字典描述），每个hyponym总共有两大类字段（第一个数字是基类的id，剩余的都是子类的id）。
    - 将有向图定义为自定义节点类的聚合。——>一个有向图类，一个包含在图类中的节点类。
    - 有向图设计为：实例变量——属于节点类数组的顶点变量？、许多个一对多的关系的序列？；节点类设计为：一个变量存储id？一个集合存储同义词？一个字符串记录描述？一个变量记录children？——>构造器的参数？

## Project 4D Overview

Handling `k != 0`: k represents the maximum number of hyponyms that we want in output.
- 返回的是要求的时间范围内，出现次数最多的那k个单词（按照大小写排序）。
- 因此，需要记录每个单词在每个时间出现的次数，这样才能求和。Project 4A的NGMap？TimeSeries？

## Summary of Design

要读入的数据主要有两部分：WordNet数据库中的数据（图结构，完成Project 4C）；wordHistory + yearHistory的NGMap、TimeSeries（来自 Project 4A，用于按照时间范围内的出现次数高低进行排序，解决4D的问题）。

数据结构：
- NGMap，TimesSeries：
- Graph（own implementation of directed graph）：a list of Sets, a list of synsetNodes

算法：
1. 给出wordsList的hyponyms：
   ```
   Pseudocode: 根据word查询ID - 根据ID查询所有子类（graph traversal）
   for word in wordlist:
     wordsID.add( searchID(word) )
   for ID in wordsID:
     hypoWords.add( searchHyponyms(ID) )
   return hypoWords
   ```

2. 给出wordsList的hyponyms中前k个的方法：
   ```
   Pseudocode: 给出wordsList的hyponyms - 计算出现次数 - 排序
   给出wordsList的hyponyms
   计算每个hyponym word的出现次数——> NGMap
   保留最大的五个 ——> 最小PQ？比PQ中的最小大，就remove最小，并添加该值
   ```

3. 的方法：
   ```
   Pseudocode:
   
   ```

# Task 1: Checkpoint

No access to gradescope.com

# Task 2: Technical Design Doc
