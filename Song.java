package Models;
import java.sql.Time;
import java.util.Date;

public class Song
{
    private int SongID;
    private String SongName;
    private String SongGenre;
    private String SongArtist;
    private Time SongDuration;
    private Date PublishedOn;
    private String FilePath;

    public Song()
    {

    }

    public Song(int SongID, String SongName, String SongGenre, String SongArtist,Time SongDuration,Date PublishedOn,String FilePath)
    {
        this.SongID = SongID;
        this.SongName= SongName;
        this.SongGenre = SongGenre;
        this.SongArtist = SongArtist;
        this.SongDuration = SongDuration;
        this.PublishedOn=PublishedOn;
        this.FilePath=FilePath;
    }

    public int getSongID()
    {
        return SongID;
    }

    public void setSongID(int SongID)
    {
        this.SongID = SongID;
    }

    public String getSongName()
    {
        return SongName;
    }

    public void setSongName(String SongName)
    {
        this.SongName = SongName;
    }

    public String getSongGenre()
    {
        return SongGenre;
    }

    public void setSongGenre(String SongGenre)
    {
        this.SongGenre = SongGenre;
    }

    public String getSongArtist()
    {
        return SongArtist;
    }

    public void setSongArtist(String SongArtist)
    {
        this.SongArtist = SongArtist;
    }

    public Time getSongDuration()
    {
        return SongDuration;
    }

    public void setSongDuration(Time SongDuration)
    {
        this.SongDuration = SongDuration;
    }

    public Date getPublishedOn()
    {
        return PublishedOn;
    }

    public void setPublishedOn(Date PublishedOn)
    {
        this.PublishedOn = PublishedOn;
    }

    public String getFilePath()
    {
        return FilePath;
    }

    public void setFilePath(String FilePath)
    {
        this.FilePath = FilePath;
    }

    @Override
    public String toString()
    {
        return "Song{" + "SongID=" + SongID + ", SongName='" + SongName+ '\'' + ", SongGenre='" + SongGenre+ '\'' + ", SongArtist='" + SongArtist + '\'' + ", SongDuration=" + SongDuration +'\'' + ", PublishedOn=" + PublishedOn + '}';
    }
}
