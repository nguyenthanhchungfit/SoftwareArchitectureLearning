namespace java mp3.utils.thrift.services

include "../models/TLyric.thrift"

service TLyricServices{
	TLyric.TLyricResult getLyricByIdAndPage(1: string id, 2: string page),
	list<TLyric.TDataLyric> getDataLyricsById(1: string id),
	i64 getTotalNumberLyrics()
}
