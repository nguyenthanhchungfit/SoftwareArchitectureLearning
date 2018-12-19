namespace java mp3.utils.thrift.services

include "../models/TAlbum.thrift"

service TAlbumServices{
	TAlbum.AlbumResult getAlbumById(1: string id),
	i64 getTotalNumberAlbums()
}
