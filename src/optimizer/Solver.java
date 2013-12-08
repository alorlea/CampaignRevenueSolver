/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizer;

import java.util.ArrayList;
import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Solver {

    private Knapsack[] subproblems;
    private ArrayList<Campaign> campaigns;

    public Solver(ArrayList<Campaign> campaigns, int maxImpressions) {
        this.campaigns = campaigns;
        this.subproblems = new Knapsack[maxImpressions + 1];
        //Arrays.fill(subproblems, new Knapsack());
    }

    public Knapsack generateSolution() {
        //initialize the arrays to blank knapsack
        for(int i= 0; i<subproblems.length;i++){
            subproblems[i]=new Knapsack();
        }

        for (int i = 1; i < subproblems.length; i++) {

            /*
             Start dynamic programming approach
             */
            Knapsack previous = subproblems[i - 1];
            Knapsack actual = subproblems[i];
            //Searching aids
            Knapsack maxKnapsack = subproblems[0];
            Campaign maxCampaign = new Campaign("", -1, -1);
            /*
             first find maximum target of all the elements
             */
            for (Campaign element : campaigns) {
                int targetKnapsack = i - element.getImpressionsPerCampaign();
                if (targetKnapsack >= 0) {
                    Knapsack temp = subproblems[targetKnapsack];
                    //Check if this knapsack optimizes the maximum value and
                    //the element that does it
                    if (maxKnapsack.getTotalValueOfKnapsack()
                            < temp.getTotalValueOfKnapsack()
                            + element.getValuePerCampaign()) {
                        maxKnapsack = temp;
                        maxCampaign = element;
                    }
                }
            }
            /*
             Got candidate subproblem, check if we keep previous knapsack or get
             new based on item
             */

            if (previous.getTotalValueOfKnapsack()
                    < maxKnapsack.getTotalValueOfKnapsack()
                    + maxCampaign.getValuePerCampaign()) {
                //Update problemknapsack based on max
                actual.setSizeOfCampaigns(maxKnapsack.getSizeOfCampaigns());
                actual.setTotalValueOfKnapsack(
                        maxKnapsack.getTotalValueOfKnapsack());
                actual.addCampaign(maxCampaign);

                //add the new campaign
                actual.addCampaign(maxCampaign);
            } else {
                actual.setLastAddedCampaign(previous.getLastAddedCampaign());
                actual.setSizeOfCampaigns(previous.getSizeOfCampaigns());
                actual.setTotalValueOfKnapsack(previous.getTotalValueOfKnapsack());
            }

        }
        return subproblems[subproblems.length - 1];
    }

}
