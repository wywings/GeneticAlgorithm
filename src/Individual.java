public class Individual{
        private int[] chromosome;//染色体
        private double fitness=-1;//适应率
        public Individual(int[] chromosome)
        {
            this.chromosome=chromosome;
        }//个体构造函数

        public Individual(int chromosomeLength)
        {
            this.chromosome=new int [chromosomeLength];//重载构造函数随机生成个体
            for(int gene=0;gene<chromosomeLength;gene++)
            {
                if(Math.random()>0.5){
                    this.setGene(gene,1);
                }
                else
                {
                    this.setGene(gene,0);
                }
            }
        }
        public int[] getChromosome()
        {
            return this.chromosome;
        }
        public int getChromosomeLength()
        {
            return this.chromosome.length;
        }
        public void setGene(int offset,int gene)
        {
            this.chromosome[offset]=gene;
        }
        public int getGene(int offset)
        {
            return this.chromosome[offset];
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
            String output="";
            for(int gene=0;gene<this.chromosome.length;gene++)
            {
                output+=this.chromosome[gene];
            }
            return output;
        }
    }