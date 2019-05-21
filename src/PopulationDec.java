import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PopulationDec {
    private IndividualDec population[];//种群
    private double populationFitness=-1;//种群适应率
    public PopulationDec(int populationSize)
    {
        this.population=new IndividualDec[populationSize];
    }
    public PopulationDec(int populationSize,double leftRange,double rightRange)
    {
        this.population = new IndividualDec[populationSize];
        for(int i=0;i<populationSize;i++)
        {
            IndividualDec individual=new IndividualDec(leftRange,rightRange);
            this.population[i]=individual;
        }
    }//随机生成种群中所有个体
    public IndividualDec[] getIndividuals()
    {
        return this.population;
    }
    public IndividualDec getFittest(int offset)
    {
        Arrays.sort(this.population,new Comparator<IndividualDec>(){
            @Override
            public int compare(IndividualDec o1,IndividualDec o2)
            {
                if(o1.getFitness()>o2.getFitness())
                {
                    return -1;
                }
                else if (o1.getFitness()<o2.getFitness())
                {
                    return 1;
                }
                return 0;
            }
        });
        return this.population[offset];
    }//重写比较器以找出适应率排序

    public void setPopulationFitness(double fitness)
    {
        this.populationFitness=fitness;
    }
    public double getPopulationFitness()
    {
        return this.populationFitness;
    }

    public int size()
    {
        return this.population.length;
    }
    public IndividualDec setIndividual(int offset,IndividualDec individual)
    {
        return population[offset]=individual;
    }
    public IndividualDec getIndividual(int offset)
    {
        return this.population[offset];
    }
    public void shuffle(){
        Random rnd = new Random();
        for(int i=population.length-1;i>0;i--)
        {
            int index=rnd.nextInt(i+1);
            IndividualDec a =population[index];
            population[index]=population[i];
            population[i]=a;
        }
    }//打乱种群中个体顺序
}
