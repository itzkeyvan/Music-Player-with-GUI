package model;

public enum PremiumPlans
{
    ONEMONTH(30,5,"Bronze (1 Month)"),TWOMONTHS(60,9,"Silver (2 Months)"),SIXMONTHS(180,14,"Gold (6 Months)");

    private int planPrice;
    private int numberOfDays;
    private String planName;

    PremiumPlans(int numberOfDays,int planPrice,String planName)
    {
        this.planPrice=planPrice;
        this.numberOfDays=numberOfDays;
        this.planName=planName;
    }

    public int getPlanPrice() {
        return planPrice;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getPlanName() {
        return planName;
    }
}
