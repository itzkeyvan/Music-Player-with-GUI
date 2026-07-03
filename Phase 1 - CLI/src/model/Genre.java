package model;

public enum Genre
{
    ROCK("Rock"),POP("Pop"),JAZZ("Jazz"),HIPHOP("Hiphop"),COUNTRY("Country"),TRUECRIME("Truecrime"),SOCIETY("Society"),INTERVIEW("Interview"),HISTORY("History");
    private String genreName;
    Genre(String name)
    {
            this.genreName=name;
    }

    public String getGenreName()
    {
        return genreName;
    }
}
