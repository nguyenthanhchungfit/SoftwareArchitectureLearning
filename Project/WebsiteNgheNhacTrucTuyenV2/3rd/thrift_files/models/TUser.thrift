namespace java mp3.utils.thrift.models

struct TUser{
	1: required string username,
	2: string password,
    3: i64 createDate, 
	4: i64 updateDate,
    5: i16 typeUser
}

struct TUserResult{
	1: required i16 error = -1,
	2: optional TUser user
}
