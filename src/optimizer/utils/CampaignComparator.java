package optimizer.utils;

import java.util.Comparator;
import optimizer.knapsack.Campaign;

/**
 * Comparator to order campaigns upon the ratio impressions/value
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class CampaignComparator implements Comparator<Campaign> {

    @Override
    public int compare(Campaign o1, Campaign o2) {
        double ratioCampaign1 = o1.getImpressionsPerCampaign()
                / o1.getValuePerCampaign();
        double ratioCampaign2 = o2.getImpressionsPerCampaign()
                / o2.getValuePerCampaign();

        if (ratioCampaign1 < ratioCampaign2) {
            return 1;
        } else if (ratioCampaign1 > ratioCampaign2) {
            return -1;
        } else {
            return 0;
        }
    }
}
