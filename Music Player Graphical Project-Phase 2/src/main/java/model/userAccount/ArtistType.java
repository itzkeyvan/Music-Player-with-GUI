package model.userAccount;

public enum ArtistType
{
    SINGER("Singer"),PODCASTER("Podcaster");

    ArtistType(String artistTypeInString)
    {
        this.artistTypeInString = artistTypeInString;
    }
    private String artistTypeInString;

    public String getArtistTypeInString() {
        return artistTypeInString;
    }

    public void setArtistTypeInString(String artistTypeInString) {
        this.artistTypeInString = artistTypeInString;
    }
}
