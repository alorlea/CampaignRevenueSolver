/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;
import optimizer.utils.CampaignComparator;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class CampaignOptimizer {

    private final BufferedReader reader;
    private int totalImpressions;
    private final ArrayList<Campaign> campaigns;

    public static void main(String[] args) {
        File input = new File(args[0]);
        try {
            //DataReader reader = new DataReader(new FileInputStream(input));
            BufferedReader reader = new BufferedReader(new FileReader(input));
            CampaignOptimizer optimizer = new CampaignOptimizer(reader);

            /*
             Generate the data
             */
            optimizer.preprocessing();

            /*
             Create solver with data
             */
            Solver knapsackSolver = new Solver(optimizer.getCampaigns(),
                    optimizer.getTotalImpressions());
            /*
             Generate the solution with dynamic programming
             */
            knapsackSolver.generateSolution();

            /*
             Obtain the solution through backtracking of the elements
             */
            Knapsack solution = knapsackSolver.obtainKnapsack();
            System.out.println(solution);
        } catch (FileNotFoundException e) {
            System.out.println("Error, the file could not be found");
            System.exit(1);
        } catch (IOException io) {

        }
    }

    public CampaignOptimizer(BufferedReader reader) {
        this.reader = reader;
        this.totalImpressions = 0;
        this.campaigns = new ArrayList();
    }

    public void preprocessing() throws IOException {
        totalImpressions = Integer.parseInt(reader.readLine());
        String campaignLine;
        while ((campaignLine = reader.readLine()) != null) {
            String[] campaignData = campaignLine.split(",");
            Campaign temp = new Campaign(campaignData[0],
                    Integer.parseInt(campaignData[1]),
                    Integer.parseInt(campaignData[2]));
            campaigns.add(temp);
        }
        Collections.sort(campaigns, new CampaignComparator());
        reader.close();
    }

    public int getTotalImpressions() {
        return totalImpressions;
    }

    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }
}
