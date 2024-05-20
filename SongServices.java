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

public class SongServices
{
    List<Song> listofsongs;

    //display all songs
    public List<Song> getAllSongs()
    {
        listofsongs = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection())
        {
            String query = "SELECT * FROM Song";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    Song song = new Song();
                    song.setSongID(resultSet.getInt("SongID"));
                    song.setSongName(resultSet.getString("SongName"));
                    song.setSongGenre(resultSet.getString("SongGenre"));
                    song.setSongArtist(resultSet.getString("SongArtist"));
                    song.setSongDuration(resultSet.getTime("SongDuration"));
                    song.setPublishedOn(resultSet.getDate("PublishedOn"));
                    song.setFilePath(resultSet.getString("FilePath"));
                    listofsongs.add(song);
                }
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return listofsongs;
    }

    public static void displaySongs(List<Song> viewsongs)
    {
        // Display songs in tabular format
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-30s | %-20s | %-15s | %-15s | %-10s |%n",
                "SongID", "SongName", "SongGenre", "SongArtist", "SongDuration", "PublishedOn");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Song song : viewsongs)
        {
            System.out.printf("| %-5d | %-30s | %-20s | %-15s | %-15s | %-10s |%n",
                    song.getSongID(), song.getSongName(), song.getSongGenre(), song.getSongArtist(),
                    song.getSongDuration(), song.getPublishedOn());
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    //searching
    public static List<Song> searchSongs(List<Song> songList, String searchCategory, String searchValue)
    {
        List<Song> searchResults = new ArrayList<>();
        for (Song song : songList)
        {
            switch (searchCategory)
            {
                case "SongName":
                    if (song.getSongName().equalsIgnoreCase(searchValue))
                    {
                        searchResults.add(song);
                    }
                    break;
                case "SongArtist":
                    if (song.getSongArtist().equalsIgnoreCase(searchValue))
                    {
                        searchResults.add(song);
                    }
                    break;
                case "SongGenre":
                    if (song.getSongGenre().equalsIgnoreCase(searchValue))
                    {
                        searchResults.add(song);
                    }
                    break;
                default:
                    System.out.println("Invalid search category.");
                    break;
            }
        }
        return searchResults;
    }

    public List<Integer> getPlaylistSongs(int playID)
    {
        List<Integer> songs = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection())
        {
            String query = "SELECT SongID FROM PlaylistInfo where PlaylistID="+playID;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                ResultSet resultSet = preparedStatement.executeQuery();
                int i=0;
                while (resultSet.next())
                {
                    i=resultSet.getInt(1);
                    songs.add(i);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return songs;
    }

    public List<Song> getAllSongsPerPlaylist(List<Integer>songs)
    {
        listofsongs = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection())
        {
            for(int i=0;i<songs.size();i++)
            {
                int j=songs.get(i);
                String query = "SELECT * FROM Song where SongID="+j;

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Song song = new Song();
                        song.setSongID(resultSet.getInt("SongID"));
                        song.setSongName(resultSet.getString("SongName"));
                        song.setSongGenre(resultSet.getString("SongGenre"));
                        song.setSongArtist(resultSet.getString("SongArtist"));
                        song.setSongDuration(resultSet.getTime("SongDuration"));
                        song.setPublishedOn(resultSet.getDate("PublishedOn"));
                        listofsongs.add(song);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return listofsongs;
    }
}
