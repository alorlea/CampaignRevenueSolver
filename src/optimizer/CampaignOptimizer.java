package optimizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import optimizer.knapsack.Campaign;
import optimizer.knapsack.Knapsack;

/**
 * Campaign optimizer allows the user to obtain the most optimal solution on the
 * combination of campaigns it needs to use in order to achieve the highest
 * profit.
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
             First impose ordering of items in order of their best ratio 
             impression/value.
             Then apply dominance relation
             */
            knapsackSolver.applyPriorityCampaigns();
            knapsackSolver.applyDominance();

            /*
             Generate the solution with dynamic programming and optimizations
             */
            knapsackSolver.generateSolution();

            /*
             Obtain the solution through backpointers of the elements
             */
            Knapsack solution = knapsackSolver.obtainKnapsack();

            /*
             Print the solution
             */
            optimizer.showFormattedSolution(solution);

            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("Error, the file could not be found");
            System.exit(1);
        } catch (IOException io) {

        }
    }

    /**
     * Constructor of the Campaign optimizer, we attach the source file reader
     * in here.
     *
     * @param reader
     */
    public CampaignOptimizer(BufferedReader reader) {
        this.reader = reader;
        this.totalImpressions = 0;
        this.campaigns = new ArrayList();
    }

    /**
     * Generates the campaigns by reading the information from the file path
     * defined by the user when running this application.
     *
     * @throws IOException
     */
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
        reader.close();
    }

    /**
     * Generates the output for the user in the following format from a Knapsack
     * solution previously generated. The format is as follows: customer, number
     * of campaigns to sell, total impressions for customer, total revenue for
     * customer ... total number of impressions , total revenue
     *
     * @param solution
     */
    public void showFormattedSolution(Knapsack solution) {

        Iterator<Campaign> solutionCampaigns
                = solution.getCampaigns().keySet().iterator();
        
        int totalCapacity = 0;
        
        while (solutionCampaigns.hasNext()) {
            StringBuilder builder = new StringBuilder();
            Campaign element = solutionCampaigns.next();
            Integer numCampaigns = solution.getCampaigns().get(element);
            
            //generate capacity of knapsack
            totalCapacity += element.getImpressionsPerCampaign()
                    * numCampaigns.intValue();
            
            builder.append(element.getCustomer()).append(",")
                    .append(numCampaigns.intValue()).append(",")
                    .append(element.getImpressionsPerCampaign()
                            * numCampaigns.intValue())
                    .append(",")
                    .append(element.getValuePerCampaign()
                            * numCampaigns.intValue());
            //print formatted campaign
            System.out.println(builder.toString());
        }
        //last line to print
        System.out.println(totalCapacity
                + "," + solution.getValueOfSolution());
    }

    /**
     * Getter of the target impressions we are looking the solution to
     *
     * @return number of total impressions for the month
     */
    public int getTotalImpressions() {
        return totalImpressions;
    }

    /**
     * Getter of the campaigns obtained from the file and need to be scheduled
     *
     * @return list of campaigns for this month
     */
    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }
}
