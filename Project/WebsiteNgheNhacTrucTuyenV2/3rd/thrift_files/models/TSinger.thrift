namespace java mp3.utils.thrift.models
include "TReferencer.thrift"

struct TSinger{
	1: required string id,
	2: string name,
	3: string realname,
	4: string dob,
	5: string country,
	6: string description,
	7: list<TReferencer.TReferencer> songs,
	8: list<TReferencer.TReferencer> albums,
	9: list<TReferencer.TReferencer> videos,
	10: string imgCover,
	11: string imgAvatar 			 		
}

struct TSingerResult{
	1: required i16 result = -1,
	2: optional TSinger singer
}


