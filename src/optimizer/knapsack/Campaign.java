package optimizer.knapsack;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class Campaign {
    
    private String customer;
    private int impressionsPerCampaign;
    private int valuePerCampaign;

    public Campaign(String customer, int impressionsPerCampaign, 
            int valuePerCampaign) {
        this.customer = customer;
        this.impressionsPerCampaign = impressionsPerCampaign;
        this.valuePerCampaign = valuePerCampaign;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getImpressionsPerCampaign() {
        return impressionsPerCampaign;
    }

    public void setImpressionsPerCampaign(int impressionsPerCampaign) {
        this.impressionsPerCampaign = impressionsPerCampaign;
    }

    public int getValuePerCampaign() {
        return valuePerCampaign;
    }

    public void setValuePerCampaign(int valuePerCampaign) {
        this.valuePerCampaign = valuePerCampaign;
    }
    
    
    
}
