# Multi-Constraint Knapsack Solver
A genetic algorithm based multi-constraint knapsack problem solver 

##  Compilation

```bash
$ javac Main.java
```

## Usage

```bash
$ java Main -i <Input file name> 
            -o <Output file name> 
            -p <Population size> 
            -g <Number of generations> 
            -m <Mutation rate> 
            -c <Cross over rate>
```

## Default Parameters

```
POPULATION_SIZE = 200
NUM_OF_GENERATIONS = 250000
MUTATION_RATE = 0.05
CROSS_OVER_RATE = 0.5
INPUT_FILE = input.txt
OUTPUT_FILE = output.txt
```

## Input Format
Inputs will always be given as a text file. Input file format, using 10 columns, whenever possible, should be as follows:
```
𝑚 𝑛 //<m := #knapsacks> <n := #items>
𝑣1 𝑣2 ... 𝑣𝑛 //<n values of items>
𝑊1 𝑊2 ... 𝑊𝑚 //<m knapsack capacities>
𝑤1,1 𝑤2,1 ... 𝑤𝑛,1 //<nxm matrix of constraints>
𝑤1,2 𝑤2,2 ... 𝑤𝑛,2
................
𝑤1,𝑚 𝑤2,𝑚 ... 𝑤𝑛,𝑚
```

## Output Format
Output is a text file including following:
```
Total_Value 
𝑥1 
𝑥2
.
.
.
𝑥𝑛
```
First line is Total_Value which is the sum of the values of the included items (Σ𝑣<sub>𝑖</sub>𝑥<sub>𝑖</sub>). Next lines include zeros and ones

## Contributors
- [Mehmet Ali Yüksel](https://github.com/mehmetaliyuksel)
- [Eymen Topçuoğlu](https://github.com/eymentopcuoglu)
