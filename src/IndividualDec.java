import java.util.Random;
public class IndividualDec {
    private double leftRange;
    private double rightRange;
    private double chromosome;
    private double fitness=-1;
    public IndividualDec(double chromosome)
    {
        this.chromosome=chromosome;
    }
    public IndividualDec(double leftRange,double rightRange)//区间
    {
        this.leftRange=leftRange;
        this.rightRange=rightRange;
        Random rnd = new Random();
        this.chromosome=rnd.nextDouble()*(rightRange-leftRange)+leftRange;
    }
    public double getLeftRange()
    {
        return this.leftRange;
    }
    public double getRightRange()
    {
        return this.rightRange;
    }
    public double getChromosome()
    {
        return this.chromosome;
    }
    public void setGene(double gene)
    {
        this.chromosome=gene;
    }
    public double getGene()
    {
        return this.chromosome;
    }
    public void setFitness(double fitness)
    {
        this.fitness=fitness;
    }
    public double getFitness()
    {
        return this.fitness;
    }
    public String toString()
    {
        return Double.toString(this.chromosome);
    }
}
