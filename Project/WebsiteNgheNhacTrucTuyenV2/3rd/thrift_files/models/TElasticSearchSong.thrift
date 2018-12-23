namespace java mp3.utils.thrift.models

struct TElasticSong{
    1: required string id,
    2: string name
}

struct TElasticSongResult{
    1: required i16 error = 0,
    2: optional TElasticSong eSong
}