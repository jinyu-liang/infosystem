########################################################
#
# ��̨��̬�� Makefile ��д��
# ��������ʵ��gmake install_idlʱ����../idl2Ŀ¼��demo.sdl��demo_def.sdl���Ƶ�$(OB_REL)/idl/demo��
#     ����$(OB_REL)/idl/rating_billing/user_mdb_demo/user_mdb_serv.sdl��$(OB_REL)/idl/balance/abm_interface_topup.sdl 
#			���Ƶ�download/idlĿ¼��
#
########################################################

# ����������ϵͳ�������塾���ܸ��ġ�
include $(OB_REL)/etc/NGbasedefine.mk

########################################################

#����һ��
#����Ŀ�����ͣ���ʾJAVAģ��
DEST_TYPE=JAVA

#Ҫ��װ��SDL���ƣ�֧�ִ�·����
IMPL_SDL=./ims-core/config/imssdl/ims_sync_def.sdl ./ims-core/config/imssdl/ims_sync.sdl ./ims-core/config/imsxdr/ims_xdr.sdl ./ims-intfsh/config/imssdl/ims_intfsh_sync_def.sdl ./ims-intfsh/config/imssdl/ims_intfsh_sync.sdl 
#��װ��SDL·��
SELFDEF_SDL_INSTALL_PATH=$(OB_REL)/idl/ims

# ���������� Makefile �����ļ������ܸ��ġ�
include $(OB_REL)/etc/NGCmake