package model;

public enum PremiumPlans
{
    ONEMONTH(30,5),TWOMONTHS(60,9),SIXMONTHS(180,14);
    private int planPrice;
    private int numberOfDays;
    PremiumPlans(int numberOfDays,int planPrice)
    {
        this.planPrice=planPrice;
        this.numberOfDays=numberOfDays;
    }

    public int getPlanPrice() {
        return planPrice;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}
