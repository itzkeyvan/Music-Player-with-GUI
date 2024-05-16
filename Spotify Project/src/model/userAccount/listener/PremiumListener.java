package model.userAccount.listener;
import model.PremiumPlans;
import model.userAccount.Listener;

import java.util.Date;

public class PremiumListener extends Listener
{
    private int subscriptionLeftDays;

    public PremiumListener(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate, int subscriptionLeftDays, PremiumPlans premiumPlan) {
        super(userName, password, firstAndLastName, email, phoneNumber, birthDate,premiumPlan);
        this.subscriptionLeftDays=subscriptionLeftDays;
    }

    public int getSubscriptionLeftDays() {
        return subscriptionLeftDays;
    }

    public void setSubscriptionLeftDays(int subscriptionLeftDays) {
        this.subscriptionLeftDays = subscriptionLeftDays;
    }
}
