namespace java mp3.utils.thrift.services

include "../models/TAlbum.thrift"

service TAlbumServices{
	bool putAlbum(1: TAlbum.TAlbum album),
	TAlbum.TAlbumResult getAlbumById(1: string id),
	i64 getTotalNumberAlbums()
}
