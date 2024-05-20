package Models;

public class PlaylistInfo
{
    private int PlaylistID;
    private int SongID;

    public PlaylistInfo()
    {

    }

    public PlaylistInfo(int PlaylistID, int SongID)
    {
        this.PlaylistID = PlaylistID;
        this.SongID = SongID;
    }

    public int getPlaylistID()
    {
        return PlaylistID;
    }

    public void setPlaylistID(int playlistID)
    {
        this.PlaylistID = PlaylistID;
    }

    public int getSongID()
    {
        return SongID;
    }

    public void setSongID(int SongID)
    {
        this.SongID = SongID;
    }

    @Override
    public String toString()
    {
        return "PlaylistInfo{" + ", PlaylistID=" + PlaylistID + ", SongID=" + SongID + '}';
    }
}