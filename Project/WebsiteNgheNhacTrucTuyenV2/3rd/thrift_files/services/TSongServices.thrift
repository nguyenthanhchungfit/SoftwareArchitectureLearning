namespace java mp3.utils.thrift.services

include "../models/TSong.thrift"

service TSongServices{
	list<TSong.TSong> getSongsSearchAPIByName(1: string name),
	TSong.TSongResult getSongById(1: string id),
	list<TSong.TSong> getSongsSearchESEByName(1: string name),
	i64 getTotalNumberSongs()
}
