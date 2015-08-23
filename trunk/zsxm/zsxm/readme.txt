
2009-12-08:
XT_DLB 新增 19 立项年份
XT_DLB 新增 20 计划类别

XT_DICT lbid=16 新增 005 科教城
XT_DICT lbid=20 新增 数据

2009-12-21：
增加：DW_CDHXXM 表
XT_DLB:  增加 21 ： 合同类别  22:地区
XT_DICT: 

2010-01-10:
XT_MENU: 中新增字段
新增 FZHT_INFO，  FZHT_FZSQ 表
新增 XT_DLB，XT_DICT   号 23 
新增 FZHT_INFO_V
新增存储过程 QRY_FZHT


2010-01-26:
	XM_IFNO_V 视图  修改


2010-02:
	MAIN_EXPERT:  主键自动增加，去除
	
BASE_LEVELTYPE： DM 值改 varchar 有级次关系
base_technical  : 如上


2010-02-11:
FZHT_INFO:  增加 HTENDDATE   datatime  字段

2010-02-22:
DW_INFO 中新增 DWPASSWORD 字段，用于企业登入

--------------------------------------------------------------
2010-05-19:
 	DW_RYXX
 	DW_RYXX_SB
 	两张表中增加： RYID, SFZ, INTIME 字段：
	移除主键，设 RYID 为主键   (注： 原表已有数据 ，所以原表插入此字段后 需要设置值)
	
	
	新建表：DW_RYXX_FJ
	重建表:  JFHSB_SHZF
	
	DW_RYXX_FJ：新增字段：
    	SHBZ      2：审核通过的符件
