
package optimizer.knapsack;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Knapsack {
    private Campaign lastAddedCampaign;
    private int sizeOfCampaigns;
    private int totalValueOfKnapsack;

    public Knapsack() {
        this.sizeOfCampaigns = 0;
        this.totalValueOfKnapsack = 0;
        this.lastAddedCampaign = new Campaign("", -1, -1);
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
        lastAddedCampaign = campaign;
        sizeOfCampaigns+=campaign.getImpressionsPerCampaign();
        totalValueOfKnapsack+=campaign.getValuePerCampaign();
    }

    public Campaign getLastAddedCampaign() {
        return lastAddedCampaign;
    }

    public void setLastAddedCampaign(Campaign lastAddedCampaign) {
        this.lastAddedCampaign = lastAddedCampaign;
    }

    
    @Override
    public String toString() {
        return "Knapsack{" + "lastAddedCampaign=" + lastAddedCampaign 
                + ", sizeOfCampaigns=" + sizeOfCampaigns 
                + ", totalValueOfKnapsack=" + totalValueOfKnapsack + '}';
    }

   

    
    
    
}
