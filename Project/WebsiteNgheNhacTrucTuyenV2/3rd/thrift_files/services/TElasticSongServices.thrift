namespace java mp3.utils.thrift.services

include "../models/TElasticSearchSong.thrift"

service TElasticSearchServices{
    list<TElasticSearchSong.TElasticSong> getListSongs(1: string songName)
}