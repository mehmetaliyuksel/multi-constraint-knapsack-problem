public class Individual implements Comparable<Individual>{
    private String genome;
    private int fitness;

    public Individual(String genome, int fitness) {
        this.genome = genome;
        this.fitness = fitness;
    }

    @Override
    public int compareTo(Individual o) {
        return Integer.compare(this.fitness, o.getFitness());
    }

    public String getGenome() {
        return genome;
    }

    public void setGenome(String genome) {
        this.genome = genome;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
