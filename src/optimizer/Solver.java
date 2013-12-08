/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Solver {

    private int[] subproblems;
    private Campaign[] campaignsAdded;
    private ArrayList<Campaign> campaigns;
    private int valueSolution;
    private int capacityOfSolution;

    public Solver(ArrayList<Campaign> campaigns, int maxImpressions) {
        this.campaigns = campaigns;
        this.subproblems = new int[maxImpressions + 1];
        this.campaignsAdded = new Campaign[maxImpressions + 1];
        this.capacityOfSolution = 0;
        this.valueSolution = 0;
    }

    public void generateSolution() {
        //initialize the arrays to blank knapsack
        for (int i = 0; i < subproblems.length; i++) {
            campaignsAdded[i] = new Campaign("", 0, 0);
        }

        for (int i = 1; i < subproblems.length; i++) {

            /*
             Start dynamic programming approach
             */
            int previous = subproblems[i - 1];
            //Searching aids
            int maxKnapsack = -1;
            Campaign maxCampaign = new Campaign("", 0, 0);
            /*
             first find maximum target of all the elements
             */
            for (Campaign element : campaigns) {
                //target capacity 
                int targetKnapsack = i - element.getImpressionsPerCampaign();
                if (targetKnapsack >= 0) {
                    //see the value of this knapsack
                    int temp = subproblems[targetKnapsack];
                    //Check if this knapsack optimizes the maximum value and
                    //the element that does it
                    if (maxKnapsack < temp + element.getValuePerCampaign()) {
                        maxKnapsack = temp;
                        maxCampaign = element;
                    }
                }
            }
            
            /*
             Got candidate subproblem, check if we keep previous knapsack or get
             new based on item
             */

            if (previous < maxKnapsack + maxCampaign.getValuePerCampaign()) {
                //Update problemknapsack based on max
                subproblems[i] = maxKnapsack+maxCampaign.getValuePerCampaign();
                campaignsAdded[i]= maxCampaign;
                //We made update so this means that if it is the last one, the
                //ideal capacity is the position
                capacityOfSolution=i;
//                System.out.println(i);
//                System.out.println(i-1);
//                System.out.println(previous);
//                System.out.println(actual);
            } else {
                subproblems[i] = subproblems[i-1];
                campaignsAdded[i] = campaignsAdded[i-1];
            }

        }
        valueSolution=subproblems[capacityOfSolution];
    }
    
    public Knapsack obtainKnapsack(){
        Knapsack solution = new Knapsack(valueSolution, capacityOfSolution);
        HashMap<Campaign,Integer> backtrackSolution = new HashMap();
        
        //Start backtrack procedure to recover elements
        Campaign lastCampaign = campaignsAdded[capacityOfSolution];
        int i = capacityOfSolution;
        while(i>0){
            //check if the campaign is already there and update its counter
            if(backtrackSolution.containsKey(lastCampaign)){
                Integer counter = backtrackSolution.get(lastCampaign);
                backtrackSolution.put(lastCampaign, counter+1);
            }
            else{//not in the solution yet, add new entry
                backtrackSolution.put(lastCampaign, 1);
            }
            i-=lastCampaign.getImpressionsPerCampaign();
        }
        solution.setCampaigns(backtrackSolution);
        return solution;
    }

}
