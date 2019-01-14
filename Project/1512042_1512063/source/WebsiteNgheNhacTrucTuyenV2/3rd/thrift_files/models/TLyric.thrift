namespace java mp3.utils.thrift.models

struct TDataLyric{
	1: string contributor,
	2: string content
}

struct TLyric{
	1: required string id,
	2: list<TDataLyric> datas
}

struct TLyricResult{
	1: required i16 error = 0,
	2: optional TLyric lyric
}
