package com.blue.ailk.constant;

public class PDMConst {
	/**
	 * pdm中表的KEY路径 .
	 */
	public final static String TABLE_PATH = "o:RootObject.c:Children.o:Model.c:Tables.o:Table";

	/**
	 * pdm中表的KEY路径 .
	 */
	public final static String PACKAGE_TABLE_PATH = "o:RootObject.c:Children.o:Model.c:Packages.o:Package.c:Tables.o:Table";
	/**
	 * Reference中表的KEY路径 .
	 */
	public final static String REF_PATH = "o:RootObject.c:Children.o:Model.c:References.o:Reference";

	/**
	 * Reference中表的KEY路径 .
	 */
	public final static String PACKAGE_REF_PATH = "o:RootObject.c:Children.o:Model.c:Packages.o:Package.c:References.o:Reference";
	/**
	 * Sequence中表的KEY路径 .
	 */
	public final static String PACKAGE_SEQ_PATH = "o:RootObject.c:Children.o:Model.c:Packages.o:Package.c:Sequences.o:Sequence";

	/**
	 * Sequence中表的KEY路径 .
	 */
	public final static String SEQ_PATH = "o:RootObject.c:Children.o:Model.c:Sequences.o:Sequence";
	/**
	 * 用户域中表的KEY路径 .
	 */
	public final static String USER_PATH = "o:RootObject.c:Children.o:Model.c:Users.o:User";
	/**
	 * PDM文件中注释配置KEY名称 .
	 */
	public final static String COMMENT_KEY = "a:Comment";
	/**
	 * PDM文件中NOTE配置KEY名称 .
	 */
	public final static String DESC_KEY = "a:Description";
	/**
	 * PDM文件中CODE配置KEY名称 .
	 */
	public final static String CODE_KEY = "a:Code";
	/**
	 * PDM文件中NAME配置KEY名称 .
	 */
	public final static String NAME_KEY = "a:Name";

	/**
	 * PDM文件中列长度配置KEY名称 .
	 */
	public final static String LENGTH_KEY = "a:Length";
	/**
	 * PDM文件中归属用户配置KEY名称 .
	 */
	public final static String OWER_USER_KEY = "c:Owner.o:User";
	/**
	 * PDM文件PK配置KEY名称 .
	 */
	public final static String PRI_KEY = "c:PrimaryKey.o:Key";
	/**
	 * PDM文件PK配置KEY名称 .
	 */
	public final static String PK_KEY = "c:Keys.o:Key";

	/**
	 * PDM文件PK名称配置KEY名称 .
	 */
	public final static String PK_NAME_KEY = "a:ConstraintName";
	/**
	 * PDM文件PK列配置KEY名称 .
	 */
	public final static String PK_COLUMN_KEY = "c:Key..Columns.o:Column";
	/**
	 * PDM文件REF名称 .
	 */
	public final static String REF = "Ref";

	public final static String LOW_VALUE_KEY = "a:LowValue";

	public final static String HIGH_VALUE_KEY = "a:HighValue";

	public final static String REF_JOIN = "c:Joins.o:ReferenceJoin";
	/**
	 * 分区表SQL
	 */
	public final static String PART_SQL = "a:PhysicalOptions";
}
