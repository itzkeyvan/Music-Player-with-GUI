package model.audio;

public enum AudioType
{
    MUSIC("Music"),PODCAST("Podcast");
    AudioType(String audioTypeInString)
    {
        this.audioTypeInString = audioTypeInString;
    }
    private String audioTypeInString;

    public String getAudioTypeInString() {
        return audioTypeInString;
    }

    public void setAudioTypeInString(String audioTypeInString) {
        this.audioTypeInString = audioTypeInString;
    }
}
