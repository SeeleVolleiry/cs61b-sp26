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




# Task 1: Checkpoint

No access to gradescope.com

# Task 2: Technical Design Doc
