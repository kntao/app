namespace java com.juniorchina.service
struct UserRequest
{
	1:string id
}
struct UserResponse
{
	1:string code
	2:map<string,string> params
}
service UserService
{
	UserResponse userInfo(1:UserRequest request),
	void ping(),
	string ping1(1:string s)
}
