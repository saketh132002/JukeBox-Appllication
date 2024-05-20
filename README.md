# JukeBox-Appllication
Basic jukebox application using  various collections and methods of JAVA. 
**********************************************************************
DataBase queries of this project
***********************************************************************
create database jukeapp;
use jukeapp;
create table Song (
                   SongID int ,primary key(SongID),
                   SongName varchar(255),
                   SongGenre varchar(255),
                   SongArtist varchar(255),
                   SongDuration time,
                   PublishedOn date);
create table Playlist(
                      PlaylistID int primary key auto_increment,
                      PlaylistName varchar(255));
create table PlaylistInfo(
                          PlaylistID int,foreign key(PlaylistID) references Playlist(PlaylistID),
                          SongID int,foreign key(SongID) references Song(SongID));
                          insert into Song values(100,'Perfect','Melody','Ed Sheeran','00:04:23','2017-03-17');    
insert into Song values(101,'Rude','Melody','Magic','00:03:44','2013-10-11'); 
insert into Song values(102,'Thunder','Pop','Imagine Dragons','00:03:25','2017-04-27'); 
insert into Song values(103,'Happier','Melody','Marhmello','00:03:54','2018-09-24');
insert into Song values(104,'Baby','Pop','Justin Bieber','00:03:40','2010-02-19');
insert into Song values(105,'Blinding Lights','Electro Pop','Weeknd','00:03:24','2020-01-21');
insert into Song values(106,'SOS','Pop','Avicii','00:02:39','2019-04-11');
insert into Song values(107,'Believer','Pop Rock','Imagine Dragons','00:03:37','2017-03-07');
insert into Song values(108,'Faded','Electro house','Alan Walker','00:03:33','2015-12-04');
insert into Song values(109,'Closer','Electro Pop','Chainsmokers','00:04:07','2016-10-24');
insert into Playlist (PlaylistName)values('Drive'),('Vacation');    
insert into PlaylistInfo values(1,102);  
insert into PlaylistInfo values(1,101);  
insert into PlaylistInfo values(2,103);  
select * from Song;
select * from Playlist;
select * from PlaylistInfo;
alter table Song add FilePath varchar(255);
update Song set FilePath="C:\\wav\\mixkit-a-very-happy-christmas-897.wav"where SongID=101;
update Song set FilePath="C:\\wav\\mixkit-complicated-281.wav"where SongID=102;
update Song set FilePath="C:\\wav\\mixkit-dance-with-me-3.wav"where SongID=103;
update Song set FilePath="C:\\wav\\mixkit-deep-urban-623.wav"where SongID=104;
update Song set FilePath="C:\\wav\\mixkit-driving-ambition-32.wav"where SongID=105;
update Song set FilePath="C:\\wav\\mixkit-hazy-after-hours-132.wav"where SongID=106;
update Song set FilePath="C:\\wav\\mixkit-life-is-a-dream-837.wav"where SongID=107;
update Song set FilePath="C:\\wav\\mixkit-raising-me-higher-34.wav"where SongID=108;
update Song set FilePath="C:\\wav\\mixkit-sun-and-his-daughter-580.wav"where SongID=109;
