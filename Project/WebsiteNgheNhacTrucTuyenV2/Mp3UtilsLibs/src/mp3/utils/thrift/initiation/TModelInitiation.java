/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.thrift.initiation;

import java.util.ArrayList;
import mp3.utils.thrift.models.TAlbum;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSinger;
import mp3.utils.thrift.models.TSong;
import mp3.utils.thrift.models.TUser;

/**
 *
 * @author chungnt
 */
public class TModelInitiation {
    public static void initTSong(TSong song){
        song.album = new TReferencer("", "");
        song.singers = new ArrayList<>();
        song.composers = new ArrayList<>();  
        song.kinds = new ArrayList<>();
        song.id = song.name = song.kara = song.comment = song.image = song.lyrics = "";
        song.views = song.duration = 0;
        
    }
    
    public static void initTLyric(TLyric lyric){
        lyric.id = "";
        lyric.datas = new ArrayList<>();
    }
    
    public static void initTSinger(TSinger singer){
        singer.id = singer.name = singer.realname = singer.dob = singer.country 
                = singer.description = singer.imgAvatar = singer.imgCover = "";
        singer.albums = new ArrayList<>();
        singer.songs = new ArrayList<>();
        singer.videos = new ArrayList<>();
    }
    
    public static void initUser(TUser user){
        user.password = user.username = "";
        user.createDate = user.updateDate = System.currentTimeMillis();
        user.typeUser = 0;
    }
    
    public static void initTAlbum(TAlbum album){
        album.id = album.name = album.image = "";
        album.songs = new ArrayList<>();
    }
    
    public static void initTDataLyric(TDataLyric dataLyric){
        dataLyric.content = dataLyric.contributor = "";
    }
}
