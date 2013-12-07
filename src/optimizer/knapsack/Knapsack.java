
package optimizer.knapsack;

import java.util.ArrayList;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Knapsack {
    private ArrayList<Campaign> campaigns;
    private int sizeOfCampaigns;
    private int totalValueOfKnapsack;

    public Knapsack() {
        this.sizeOfCampaigns = 0;
        this.totalValueOfKnapsack = 0;
        this.campaigns = new ArrayList();
    }

    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(ArrayList<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public int getSizeOfCampaigns() {
        return sizeOfCampaigns;
    }

    public void setSizeOfCampaigns(int sizeOfCampaigns) {
        this.sizeOfCampaigns = sizeOfCampaigns;
    }

    public int getTotalValueOfKnapsack() {
        return totalValueOfKnapsack;
    }

    public void setTotalValueOfKnapsack(int totalValueOfKnapsack) {
        this.totalValueOfKnapsack = totalValueOfKnapsack;
    }
    
    public void addCampaign(Campaign campaign){
        campaigns.add(campaign);
        sizeOfCampaigns+=campaign.getImpressionsPerCampaign();
        totalValueOfKnapsack+=campaign.getValuePerCampaign();
    }

    @Override
    public String toString() {
        return "Knapsack{" + "campaigns=" + campaigns + ", sizeOfCampaigns=" 
                + sizeOfCampaigns + ", totalValueOfKnapsack=" 
                + totalValueOfKnapsack + '}';
    }
    
    
}
