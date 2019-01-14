namespace java mp3.utils.thrift.services

include "../models/TSong.thrift"

service TSongServices{
	bool putSong(1: TSong.TSong song),
	list<TSong.TSong> getListSongSearchByName(1: string name),
	TSong.TSongResult getSongById(1: string id),
	i64 getTotalNumberSongs()
}
