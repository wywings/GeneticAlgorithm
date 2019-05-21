import java.util.Random;

public class MinValue {
    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm(1000,0.1,0.75,0);
        PopulationDec population = ga.initPopulationDec(0,5);
        ga.evalPopulationDec(population);
        int generation=1;
        for(int i=0;i<population.size();i++)
        {
            System.out.println(population.getIndividual(i).toString());
        }
        for(int i=0;i<5000;i++)
        {
            population=ga.crossoverPopulationDec(population);
            population=ga.mutatePopulationDec(population);
            ga.evalPopulationDec(population);
            System.out.println(population.getFittest(0));
            generation++;
        }
        for(int i=0;i<population.size();i++)
        {
            System.out.println(population.getIndividual(i).toString());
        }
    }
}
