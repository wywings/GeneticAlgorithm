public class GeneticAlgorithm{

    private int populationSize;//种群规模
    private double mutationRate;//变异率
    private double crossoverRate;//交叉率
    private int elitismCount;//精英数量
    public GeneticAlgorithm(int populationSize,double mutationRate,double crossoverRate,int elitismCount)
    {
        this.populationSize=populationSize;
        this.mutationRate=mutationRate;
        this.crossoverRate=crossoverRate;
        this.elitismCount=elitismCount;
    }
    public Population initPopulation(int chromosomeLength){
        Population population=new Population(this.populationSize,chromosomeLength);
        return population;
    }
    public PopulationDec initPopulationDec(double leftRange,double rightRange){
        PopulationDec population=new PopulationDec(this.populationSize,leftRange,rightRange);
        return population;
    }
    public double calcFitness(IndividualDec individual)
    {
        double x=individual.getGene();
        double newFitness=Math.sin(x)+x*Math.cos(x);
        individual.setFitness(newFitness);
        return newFitness;
    }
    public Individual selectParent(Population population)
    {
        Individual individuals[] = population.getIndividuals();

        double populationFitness=population.getPopulationFitness();
        double rouletteWheelPosition=Math.random()*populationFitness;

        double spinWheel=0;
        for(Individual individual:individuals){
            spinWheel+=individual.getFitness();
            if(spinWheel>=rouletteWheelPosition)
            {
                return individual;
            }
        }
        return individuals[population.size()-1];
    }
    public IndividualDec selectParentDec(PopulationDec population)
    {
        IndividualDec individuals[] = population.getIndividuals();

        double populationFitness=population.getPopulationFitness();
        double rouletteWheelPosition=Math.random()*populationFitness;

        double spinWheel=0;
        for(IndividualDec individual:individuals){
            spinWheel+=individual.getFitness();
            if(spinWheel>=rouletteWheelPosition)
            {
                return individual;
            }
        }
        return individuals[population.size()-1];
    }
    public Population crossoverPopulation(Population population){
        Population newPopulation=new Population(population.size());

        for(int populationIndex=0;populationIndex<population.size();populationIndex++)
        {
            Individual parent1=population.getFittest(populationIndex);
            
            if(this.crossoverRate>Math.random() && populationIndex>=this.elitismCount)
            {
                Individual offspring = new Individual(parent1.getChromosomeLength());
                Individual parent2=this.selectParent(population);
                int swapPoint =(int)(Math.random()*(parent1.getChromosomeLength()+1));
                for(int geneIndex=0;geneIndex<parent1.getChromosomeLength();geneIndex++)
                {
                    if(geneIndex<swapPoint)
                    {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    }
                    else
                    {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }
                newPopulation.setIndividual(populationIndex, offspring);
            }
           else
           {
                newPopulation.setIndividual(populationIndex, parent1);    
           }
        }
        return newPopulation;
    }
    public PopulationDec crossoverPopulationDec(PopulationDec population){
        PopulationDec newPopulation=new PopulationDec(population.size());

        for(int populationIndex=0;populationIndex<population.size();populationIndex++)
        {
            IndividualDec parent1=population.getFittest(populationIndex);
            IndividualDec parent2=this.selectParentDec(population);
            if(this.crossoverRate>Math.random() && populationIndex>=this.elitismCount)
            {
                parent1.setGene(0.9*parent1.getGene()+0.1*parent2.getGene());
            }
            newPopulation.setIndividual(populationIndex,parent1);
        }
        return newPopulation;
    }
    public PopulationDec mutatePopulationDec(PopulationDec population)
    {
        PopulationDec newPopulation=new PopulationDec(this.populationSize);
        for(int populationIndex=0;populationIndex<population.size();populationIndex++)
        {
            IndividualDec individual=population.getFittest(populationIndex);
            if(populationIndex>=this.elitismCount) {
                if (this.mutationRate > Math.random()) {
                    individual.setGene(Math.random()*(individual.getRightRange()-individual.getLeftRange())+individual.getLeftRange());
                }
            }
            newPopulation.setIndividual(populationIndex, individual);
        }
        return newPopulation;
    }
    public Population mutatePopulation(Population population)
    {
        Population newPopulation=new Population(this.populationSize);
        for(int populationIndex=0;populationIndex<population.size();populationIndex++)
        {
            Individual individual=population.getFittest(populationIndex);
            for(int geneIndex=0;geneIndex<individual.getChromosomeLength();geneIndex++)
            {
                if(populationIndex>=this.elitismCount){
                    if(this.mutationRate>Math.random())
                    {
                        int newGene=1;
                        if(individual.getGene(geneIndex)==1)
                        {
                            newGene=0;
                        }
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }
            newPopulation.setIndividual(populationIndex, individual);
        }
        return newPopulation;
    }
    public void evalPopulationDec(PopulationDec population)
    {
        double populationFitness=0;
        for(IndividualDec individual:population.getIndividuals())
        {
            populationFitness+=calcFitness(individual);
        }
        population.setPopulationFitness(populationFitness);
    }
    public boolean isTerminationConditionMetDec(PopulationDec population)
    {
        for(IndividualDec individual:population.getIndividuals())
        {
            if(individual.getFitness()==1)return true;
        }
        return false;
    }
}