package optimizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;
import optimizer.utils.CampaignComparator;

/**
 * Solver for the unbounded knapsack problem (i.e: duplicate items allowed)
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Solver {

    private int[] subproblems;
    private Campaign[] campaignsAdded;
    private ArrayList<Campaign> campaigns;
    private int valueSolution;
    private int backtrackOfSolution;

    /**
     * Constructor for the solver, initialize it with the campaigns to be used
     * to calculate the optimal solution and the maximum size of the knapsack
     *
     * @param campaigns
     * @param maxImpressions
     */
    public Solver(ArrayList<Campaign> campaigns, int maxImpressions) {
        this.campaigns = campaigns;
        this.subproblems = new int[maxImpressions + 1];
        this.campaignsAdded = new Campaign[maxImpressions + 1];
        this.backtrackOfSolution = 0;
        this.valueSolution = 0;
    }

    /**
     * Generate the solution from the specified data. Obtained the solution
     * through dynamic programming approach by calculating the subproblems until
     * getting the target solution.
     *
     * Running time O(nC) where n is the number of campaigns and C the maximum
     * number of impressions.
     *
     *
     */
    public void generateSolution() {
        //initialize the arrays to blank knapsack

        for (int i = 1; i < subproblems.length; i++) {

            /*
             Start dynamic programming approach
             */

            /*
             first find maximum target of all the elements
             */
            for (Campaign element : campaigns) {
                //target capacity 
                int itemWeight = element.getImpressionsPerCampaign();
                int itemValue = element.getValuePerCampaign();
                if (itemWeight<=i
                        &&(itemValue+subproblems[i-itemWeight])>subproblems[i]) {
                        //this updates the entry everytime and eventually will 
                        //not change anymore if the value is maximum
                        subproblems[i]= itemValue+subproblems[i-itemWeight];
                        campaignsAdded[i]=element;
                }
            }
        }
        valueSolution = subproblems[subproblems.length-1];
        backtrackOfSolution=subproblems.length-1;
    }

    /**
     * Generate the solution in a Knapsack object. This object will have the
     * value of the solution, the capacity of impressions for this month and the
     * mappings of the campaigns for this months impressions which are part of
     * the solution
     *
     * @return knapsack with the solution value and capacity and the items of
     * the solution
     */
    public Knapsack obtainKnapsack() {
        Knapsack solution = new Knapsack(valueSolution, backtrackOfSolution);
        HashMap<Campaign, Integer> backtrackSolution = new HashMap();

        //Start backtrack procedure to recover elements
        Campaign lastCampaign = campaignsAdded[backtrackOfSolution];
        int i = backtrackOfSolution;
        while (lastCampaign!=null&&i >= 0) {
            //check if the campaign is already there and update its counter
            //note avoid the null reference, that is for empty knapsack!
            if (backtrackSolution.containsKey(lastCampaign)) {
                Integer counter = backtrackSolution.get(lastCampaign);
                backtrackSolution.put(lastCampaign, counter + 1);
            } else {//not in the solution yet, add new entry
                backtrackSolution.put(lastCampaign, 1);
            }
            i -= lastCampaign.getImpressionsPerCampaign();
            lastCampaign=campaignsAdded[i];
        }
        solution.setCampaigns(backtrackSolution);
        return solution;
    }

    /**
     * For improved performance for the Solver running time, we order the
     * elements with the ratio impression/value to give priority to most
     * profitable ones for the solution.
     * 
     * Sort takes O(nlogn) average case
     */
    public void applyPriorityCampaigns() {
        Collections.sort(campaigns, new CampaignComparator());
    }

    /**
     * Executing this method before running the solver will optimize the system
     * with a dominance relation on the items of the knapsack.
     *
     * Martello and Toth algorithm for dominance relations
     *
     * If the campaigns are ordered by impression/weight ratio, we fetch the
     * best element and we selectively discard the items which are fairly
     * dominated by this item.
     * 
     * This part takes O(mn)
     *
     */
    public void applyDominance() {
        int m = campaigns.size();
        for(int i=0;i<m-1;i++){
            Campaign temp1 = campaigns.get(i);
            for(int j=i+1;j<m-1;j++){
                Campaign temp2 = campaigns.get(j);
                //(wi*pj/pi)<wj
                if(((temp1.getImpressionsPerCampaign()
                        *temp2.getValuePerCampaign())
                        /temp1.getValuePerCampaign())
                        <temp2.getImpressionsPerCampaign()){
                    campaigns.remove(j);
                    m--;
                }
                    
            }
        }
    }

}
