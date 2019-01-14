namespace java mp3.utils.thrift.models
include "TReferencer.thrift"

struct TSong{
	1: required string id,
	2: string name,
	3: TReferencer.TReferencer album,
	4: string lyrics,
	5: list<string> composers,
	6: string kara,
	7: i16 duration = 0,
	8: list<TReferencer.TReferencer> kinds,
	9: list<TReferencer.TReferencer> singers,
	10: i64 views,
	11: string comment,
	12: string image
}

struct TSongResult{
	1: required i16 error = 0,
	2: optional TSong song
}

