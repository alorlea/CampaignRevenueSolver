
package optimizer.knapsack;

import java.util.HashMap;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Knapsack {
    private HashMap<Campaign,Integer> campaigns;
    private int valueOfSolution;
    private int sizeOfSolution;

    public Knapsack() {
        this.valueOfSolution = 0;
        this.sizeOfSolution = 0;
        this.campaigns= new HashMap();
    }

    public Knapsack(int valueOfSolution, int sizeOfSolution) {
        this.valueOfSolution = valueOfSolution;
        this.sizeOfSolution = sizeOfSolution;
        this.campaigns = new HashMap();
    }
    
    
    public int getValueOfSolution() {
        return valueOfSolution;
    }

    public void setValueOfSolution(int valueOfSolution) {
        this.valueOfSolution = valueOfSolution;
    }

    public int getSizeOfSolution() {
        return sizeOfSolution;
    }

    public void setSizeOfSolution(int sizeOfSolution) {
        this.sizeOfSolution = sizeOfSolution;
    }

    public HashMap<Campaign, Integer> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(HashMap<Campaign, Integer> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public String toString() {
        return "Knapsack{" + "campaigns=" + campaigns 
                + ", valueOfSolution=" + valueOfSolution 
                + ", sizeOfSolution=" + sizeOfSolution + '}';
    }
    
    
}
