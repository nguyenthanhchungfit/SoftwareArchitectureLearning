namespace java mp3.utils.thrift.models
include "TReferencer.thrift"

struct TAlbum{
	1: required string id,
	2: string name,
	3: list<TReferencer.TReferencer> songs,
	4: string image
}

struct TAlbumResult{
	1: required i16 error = 0,
	2: optional TAlbum album
}
