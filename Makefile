########################################################
#
# 后台动态库 Makefile 的写法
# 该例子是实现gmake install_idl时，将../idl2目录的demo.sdl、demo_def.sdl复制到$(OB_REL)/idl/demo，
#     并将$(OB_REL)/idl/rating_billing/user_mdb_demo/user_mdb_serv.sdl，$(OB_REL)/idl/balance/abm_interface_topup.sdl 
#			复制到download/idl目录。
#
########################################################

# 包含基本的系统参数定义【不能更改】
include $(OB_REL)/etc/NGbasedefine.mk

########################################################

#方法一：
#定义目标类型，表示JAVA模块
DEST_TYPE=JAVA

#要安装的SDL名称（支持带路径）
IMPL_SDL=./ims-core/config/imssdl/ims_sync_def.sdl ./ims-core/config/imssdl/ims_sync.sdl ./ims-core/config/imsxdr/ims_xdr.sdl ./ims-intfsh/config/imssdl/ims_intfsh_sync_def.sdl ./ims-intfsh/config/imssdl/ims_intfsh_sync.sdl 
#安装的SDL路径
SELFDEF_SDL_INSTALL_PATH=$(OB_REL)/idl/ims

# 包含基本的 Makefile 规则文件【不能更改】
include $(OB_REL)/etc/NGCmake