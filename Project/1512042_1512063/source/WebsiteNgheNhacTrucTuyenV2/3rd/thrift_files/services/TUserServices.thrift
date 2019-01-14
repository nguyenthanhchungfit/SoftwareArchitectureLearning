namespace java mp3.utils.thrift.services
include "../models/TUser.thrift"

service TUserServices{
	string login(1: string username, 2: string password),
	bool signup(1: TUser.TUser user),
	i64 getTotalNumberUsers(),
	void logout(1: string c_user),
	bool isAdminSession(1: string c_user)
}
