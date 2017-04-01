sdl java server端开发流程：

1、定义结构体：统一在ims_sync_def.sdl文件中定义，格式请参考SProductStatus结构。
sdl中的数据类型和java中的数据类型对应关系如下：

char	char
string	String
int16	short
int32	int
int64	long
float	float
double	double
date	java.util.Date
time	java.sql.Time
datetime	java.sql.Timestamp

2、定义服务端提供的方法：统一在ims_sync.sdl文件中定义。
返回类型统一为int32，对于输入参数用in开头，输出参数用out开头。

3、执行generate_server.bat，会在当前目录生成3个文件夹:MImsSyncDef,service,MImsSyncApp，
把MImsSyncDef和service文件夹拷贝到interface/src/main/java/com/ailk/easyframe/sdl文件夹中(覆盖原有文件)。
打开MImsSyncApp/IImsSyncAppImp.java文件，找到你在ims_sync.sdl中增加的一个（或者多个）方法，
拷贝到/interface/src/main/java/com/ailk/easyframe/sdl/MImsSyncApp/IImsSyncAppImp.java中。

4、增加真正的业务实现到IImsSyncAppImp.java中各自的方法中。

5、在/interface/src/main/java/com/ailk/ims/sdlsync包下定义各自业务的具体逻辑的类和方法，供IImsSyncAppImp.java中的方法调用。
即IImsSyncAppImp.java中的方法，只是做了简单的转发调用工作。




sdl java client端开发流程（用于测试服务端定义的方法是否正确完成业务功能）：
在服务端定义好ims_sync_def.sdl中的结构和ims_sync.sdl中的提供接口的前提下：
1、执行generate_server.bat，生成MImsSyncApp和MImsSyncDef文件夹，
剪切到interface/src/main/java/com/ailk/easyframe/sdl文件夹下（覆盖原有文件）。

2、在/interface/src/main/java/com/ailk/easyframe/sdl/TestClient.java文件中，自己写测试方法测试。（ 请参考现有实现）
直接执行TestClient的main方法即可运行。



