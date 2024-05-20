package Models;

public class Playlist
{
    private int PlaylistID;
    private String PlaylistName;

    public Playlist()
    {

    }

    public Playlist(int PlaylistID, String PlaylistName)
    {
        this.PlaylistID = PlaylistID;
        this.PlaylistName = PlaylistName;
    }

    public int getPlaylistID()
    {
        return PlaylistID;
    }

    public void setPlaylistID(int PlaylistID)
    {
        this.PlaylistID = PlaylistID;
    }

    public String getPlaylistName()
    {
        return PlaylistName;
    }

    public void setPlaylistName(String PlaylistName)
    {
        this.PlaylistName = PlaylistName;
    }

    @Override
    public String toString()
    {
        return "Playlist{" + "PlaylistID=" + PlaylistID + ", PlaylistName='" + PlaylistName + '\'' + '}';
    }
}
