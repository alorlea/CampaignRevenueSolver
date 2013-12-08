
package optimizer.knapsack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Knapsack {
    private HashMap<Campaign,Integer> campaigns;
    private int sizeOfCampaigns;
    private int totalValueOfKnapsack;

    public Knapsack() {
        this.sizeOfCampaigns = 0;
        this.totalValueOfKnapsack = 0;
        this.campaigns = new HashMap();
    }

    public HashMap<Campaign,Integer> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(HashMap<Campaign,Integer> campaigns) {
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
        if(campaigns.containsKey(campaign)){
            Integer value = campaigns.get(campaign);
            campaigns.put(campaign, value+1);
        }
        else{
            campaigns.put(campaign, 0);
        }
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
