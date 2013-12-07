/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;
import optimizer.utils.CampaignComparator;
import optimizer.utils.DataReader;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class CampaignOptimizer {

    private final DataReader reader;
    private int totalImpressions;
    private TreeSet<Campaign> campaigns;

    public static void main(String[] args) {
        File input = new File(args[0]);
        try {
            DataReader reader = new DataReader(new FileInputStream(input));
            CampaignOptimizer optimizer = new CampaignOptimizer(reader);
            
            /*
            Generate the data
            */
            optimizer.preprocessing();
            
            Solver knapsackSolver = new Solver(optimizer.getCampaigns(), optimizer.getTotalImpressions());
            Knapsack solution = knapsackSolver.generateSolution();
            
            System.out.println(solution);
        } catch (FileNotFoundException e) {
            System.out.println("Error, the file could not be found");
            System.exit(1);
        }
    }

    public CampaignOptimizer(DataReader reader) {
        this.reader = reader;
        this.totalImpressions = 0;
        this.campaigns = new TreeSet(new CampaignComparator());
    }

    public void preprocessing() {
        totalImpressions = reader.getInt();
        while (reader.hasMoreTokens()) {
            Campaign temp = new Campaign(reader.getWord(), 
                    reader.getInt(), reader.getInt());
            campaigns.add(temp);
        }
        
        reader.close();
    }

    public int getTotalImpressions() {
        return totalImpressions;
    }

    public void setTotalImpressions(int totalImpressions) {
        this.totalImpressions = totalImpressions;
    }

    public TreeSet<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(TreeSet<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
    
    

}
