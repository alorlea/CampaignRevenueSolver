/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizer;

import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;

/**
 *
 * @author AsusG53s
 */
public class Solver {
    private Knapsack[] subproblems;
    private Campaign[] campaigns;

    public Solver(Campaign[] campaigns,int maxImpressions) {
        this.campaigns = campaigns;
        this.subproblems = new Knapsack[maxImpressions];
    }
    
    
}
