package model.userAccount.listener;
import model.PremiumPlans;
import model.userAccount.Listener;

import java.util.Calendar;
import java.util.Date;

public class FreeListener extends Listener
{
    private final static int addingMusicsToPlaylistLimit=10;
    private final static int creatingPlaylistsLimit=3;

    public FreeListener(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate) {
        super(userName, password, firstAndLastName, email, phoneNumber, birthDate,null);
    }
    public PremiumListener upgradeToPremium(PremiumPlans premiumPlanType)
    {
        PremiumListener premiumListener=new PremiumListener(this.getUserName(),this.getPassword(),this.getFirstAndLastName(),this.getEmail(),this.getPhoneNumber(),this.getBirthDate(),premiumPlanType.getNumberOfDays(),premiumPlanType);
        premiumListener.setAccountCredit(this.getAccountCredit()-premiumPlanType.getPlanPrice());
        premiumListener.setPlaylistsList(this.getPlaylistsList());
        premiumListener.setFollowingsList(this.getFollowingsList());
        premiumListener.setNumberOfPlays(this.getNumberOfPlays());
        premiumListener.setLikedAudios(this.getLikedAudios());
        premiumListener.setSubscriptionLeftDays(premiumPlanType.getNumberOfDays());
        premiumListener.setFavouriteGenres(this.getFavouriteGenres());
        premiumListener.setNumberOfPlaysByGenre(this.getNumberOfPlaysByGenre());
        premiumListener.setGenresScores(this.getGenresScores());
        return premiumListener;
    }

    public static int getCreatingPlaylistsLimit() {
        return creatingPlaylistsLimit;
    }
    public static int getAddingMusicsToPlaylistLimit() {
        return addingMusicsToPlaylistLimit;
    }
}
