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
π π //<m := #knapsacks> <n := #items>
π£1 π£2 ... π£π //<n values of items>
π1 π2 ... ππ //<m knapsack capacities>
π€1,1 π€2,1 ... π€π,1 //<nxm matrix of constraints>
π€1,2 π€2,2 ... π€π,2
................
π€1,π π€2,π ... π€π,π
```

## Output Format
Output is a text file including following:
```
Total_Value 
π₯1 
π₯2
.
.
.
π₯π
```
First line is Total_Value which is the sum of the values of the included items (Ξ£π£<sub>π</sub>π₯<sub>π</sub>). Next lines include zeros and ones

## Contributors
- [Mehmet Ali YΓΌksel](https://github.com/mehmetaliyuksel)
- [Eymen TopΓ§uoΔlu](https://github.com/eymentopcuoglu)
