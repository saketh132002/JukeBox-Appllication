package Services;
import Models.Playlist;
import Models.Song;
import Connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistServices
{
    // Insert playlist into database
    public void insertPlaylist(Playlist playlist)
    {
        try (Connection connection = DBConnection.getConnection())
        {
            String query = "INSERT INTO Playlist (PlaylistName) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.setString(1,playlist.getPlaylistName());
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Insert song into a playlist in the DB
    public void insertSongIntoPlaylist(int playlistID, int songID)
    {
        try (Connection connection = DBConnection.getConnection())
        {
            String query = "INSERT INTO PlaylistInfo (PlaylistID, SongID) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.setInt(1, playlistID);
                preparedStatement.setInt(2, songID);
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Retrieve all playlists from the DB
    public List<Playlist> getAllPlaylists()
    {
        List<Playlist> playlists = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection())
        {
            String query = "SELECT * FROM Playlist";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    Playlist playlist = new Playlist();
                    playlist.setPlaylistID(resultSet.getInt("PlaylistID"));
                    playlist.setPlaylistName(resultSet.getString("PlaylistName"));
                    playlists.add(playlist);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return playlists;
    }
}
