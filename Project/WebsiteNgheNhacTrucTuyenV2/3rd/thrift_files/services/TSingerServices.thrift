namespace java mp3.utils.thrift.services

include "../models/TSinger.thrift"

service TSingerServices{
	bool putSinger(1: TSinger.TSinger singer),
	list<TSinger.TSinger> getSingersByName(1: string name),
	TSinger.TSingerResult getSingerById(1: string id),
	i64 getTotalNumberSingers()
}