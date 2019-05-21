import java.util.Random;
public class Crossover{
    public static void main(String[] args) {
        Random rnd = new Random();
        int testPSize=rnd.nextInt(100)+1;
        GeneticAlgorithm ga = new GeneticAlgorithm(5,1,1,0);//种群大小1-100，变异率0.01，交叉率1，精英个体0

        Population population = ga.initPopulation(6);//染色体长度6
        System.out.println("before");
        for(int i=0;i<population.size();i++)
        {
            System.out.println(population.getIndividual(i).toString());
        }

        System.out.println("after crossover");
        for(int i=0;i<population.size();i++)
        {
            System.out.println(ga.crossoverPopulation(population).getIndividual(i).toString());
        }

        System.out.println("after mutate");
        for(int i=0;i<population.size();i++)
        {
            System.out.println(ga.mutatePopulation(population).getIndividual(i).toString());
        }

    }
}
//change test