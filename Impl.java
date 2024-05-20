import Models.Song;
import Models.Playlist;
import Services.AudioPlayerServices;
import Services.PlaylistServices;
import Services.SongServices;
import java.util.List;
import java.util.Scanner;

public class Impl
{

    public static void main(String[] args)
    {
        System.out.println("Welcome Back Listener!!!");
        Scanner scan = new Scanner(System.in);
        SongServices songServicesObj = new SongServices();
        PlaylistServices playlistServicesObj = new PlaylistServices();
        List<Song> songList = songServicesObj.getAllSongs();
        int repeat;
        int wantToContinue;
        do {
            System.out.println("Select a Service: ");
            System.out.println("1. View songs");
            System.out.println("2. Search for songs");
            System.out.println("3. Add songs to new/existing playlists");
            System.out.println("4. Play songs from playlist");
            int menu = scan.nextInt();
            scan.nextLine();
            switch (menu)
            {
                case 1:
                    //display all songs
                    songList = songServicesObj.getAllSongs();
                    SongServices.displaySongs(songList);
                    break;

                case 2:
                    //Searching
                    System.out.println("Enter the Category by which you want to Search?? (SongName,SongArtist and SongGenre)");
                    String searchCategory = scan.nextLine();
                    System.out.println("Enter the Search Value");
                    String searchValue = scan.nextLine();

                    //filtering
                    List<Song> searchResults = SongServices.searchSongs(songList, searchCategory, searchValue);
                    SongServices.displaySongs(searchResults);
                    break;

                case 3:
                    // Create or add song to a playlist
                    System.out.println("Do you want to add songs to a new playlist or add songs to an existing playlist? (new/existing)");
                    String choice = scan.nextLine();
                    if (choice.equalsIgnoreCase("new"))
                    {
                        // Create a new playlist
                        System.out.println("Enter the Name for the new playlist:");
                        Playlist newPlaylist = new Playlist();
                        newPlaylist.setPlaylistName(scan.nextLine());
                        playlistServicesObj.insertPlaylist(newPlaylist);
                        System.out.println("New playlist is created.");
                        List<Playlist> playlists = playlistServicesObj.getAllPlaylists();
                        for (Playlist playlist : playlists)
                        {
                            System.out.printf("ID: %d, Name: %s%n", playlist.getPlaylistID(), playlist.getPlaylistName());
                        }
                    }
                    else if (choice.equalsIgnoreCase("existing"))
                    {
                        // Add song to an existing playlist
                        // Retrieve and display all playlists
                        List<Playlist> playlists = playlistServicesObj.getAllPlaylists();
                        if (playlists.isEmpty())
                        {
                            System.out.println("No playlists found. Please create a new playlist.");
                            break;
                        }

                        System.out.println("Existing Playlists:");
                        for (Playlist playlist : playlists)
                        {
                            System.out.printf("ID: %d, Name: %s%n", playlist.getPlaylistID(), playlist.getPlaylistName());
                        }
                    }
                    else
                    {
                        System.out.println("Invalid option. Please choose 'new' or 'existing'.");
                        break;
                    }

                    // user to select a playlist
                    System.out.println("Enter the ID of the Playlist you want to add a song to:");
                    int playlistID = scan.nextInt();
                    scan.nextLine();
                    List<Integer> playsongs = songServicesObj.getPlaylistSongs(playlistID);
                    List<Song> play = songServicesObj.getAllSongsPerPlaylist(playsongs);
                    System.out.println("Songs available in the selected playlist");
                    SongServices.displaySongs(play);

                    // Prompt user to select a song

                    do {
                        System.out.println("Enter the ID of the Song you want to add to the Playlist:");
                        int songID = scan.nextInt();
                        scan.nextLine();

                        // Checking if the selected song exists
                        Song songToBeAdd = null;
                        for (Song song : songList)
                        {
                            if (song.getSongID() == songID)
                            {
                                songToBeAdd = song;
                                break;
                            }
                        }
                        if (songToBeAdd != null)
                        {
                            playlistServicesObj.insertSongIntoPlaylist(playlistID, songID);
                            System.out.println("Song added to the playlist.");
                        }
                        else
                        {

                            System.out.println("Song not found.");
                        }
                        System.out.println("Do you want to Add  another Song? Enter '1' to add more and '0' to stop adding songs to the playlist");
                        wantToContinue = scan.nextInt();
                        scan.nextLine();
                    } while (wantToContinue != 0);

                    System.out.println(playlistID);
                    playsongs = songServicesObj.getPlaylistSongs(playlistID);
                    play = songServicesObj.getAllSongsPerPlaylist(playsongs);
                    System.out.println("Songs available in the selected playlist after adding the songs");
                    SongServices.displaySongs(play);
                    break;

                case 4:
                    // Code to play songs from the playlist
                    System.out.println("Enter the ID of the playlist you want to play:");
                    int playlistIDToPlay = scan.nextInt();
                    scan.nextLine();

                    List<Integer> songIDsInPlaylist = songServicesObj.getPlaylistSongs(playlistIDToPlay);

                    AudioPlayerServices audioPlayerServicesObj = new AudioPlayerServices();
                    for (int songID : songIDsInPlaylist)
                    {
                        // Find the song by ID
                        Song songToPlay = null;
                        for (Song song : songList)
                        {
                            if (song.getSongID() == songID)
                            {
                                songToPlay = song;
                                break;
                            }
                        }

                        if (songToPlay != null)
                        {
                            System.out.println("Now playing: " + songToPlay.getSongName());
                            audioPlayerServicesObj.play(songToPlay.getFilePath());
                            // Wait for user input to control playback
                            AudioPlayerServices.controlPlayback(audioPlayerServicesObj, scan);
                        }
                        else
                         {
                            System.out.println("Song not found in the playlist.");
                         }
                    }
                    break;

                default:
                    System.out.println("Enter valid option");
            }
            System.out.println("Enter '1' to continue using jukebox services and '0' to close the jukebox");
            repeat = scan.nextInt();
            scan.nextLine();
        } while (repeat != 0);
    scan.close();
    }
}







