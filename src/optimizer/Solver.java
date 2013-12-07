/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizer;

import java.util.Arrays;
import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;
import optimizer.utils.CampaignComparator;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Solver {
    private Knapsack[] subproblems;
    private Campaign[] campaigns;

    public Solver(Campaign[] campaigns,int maxImpressions) {
        this.campaigns = campaigns;
        this.subproblems = new Knapsack[maxImpressions];
    }
    
    /**
     * Method to prepare conditions of Knapsack, reorder list of campaigns
     * upon the ratio impressions/value
     */
    public void preprocessing(){
        Arrays.sort(campaigns, new CampaignComparator());
    }
    
    
}
