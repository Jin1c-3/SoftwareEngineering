
----管理员表  00001为root管理员
create table manager(
		manager_ID char(5) primary key,
		manager_name nvarchar(20),
		manager_pwd varchar(30) check (len(manager_pwd) between 6 and 30)
)
----服务商表
create table SerProviders(
	Ser_ID int primary key identity(1,1),
	Ser_pwd varchar(30) check (len(Ser_pwd) between 6 and 30),
	Ser_name nvarchar(30),
	profit float
)
----飞机信息表
create table Aircraft(
	Air_ID varchar(20) primary key,
	Air_type varchar(20),
	Air_T_num int,
	Air_M_num int,
	Air_L_num int,
	Air_status nvarchar(3) check (Air_status = '可用' or Air_status = '不可用' ) default('可用'),
	Ser_ID int ,
	foreign key(Ser_ID)  references SerProviders(Ser_ID)
)
----航班信息表
create table F_Info(
	--班期
	 T varchar(18),
	 f_ID varchar(25) primary key,
	 f_st_city nvarchar(10),
	 f_en_city nvarchar(10),
	 f_st_port nvarchar(10),
	 f_en_port nvarchar(10),
	 f_status nvarchar(20),
	 Air_type varchar(20),
	 zhifei nvarchar(2) check(zhifei = '是' or zhifei = '否'),
	 Air_ID varchar(20),
	 foreign key(Air_ID) references Aircraft(Air_ID)
)
----飞机行程信息表
create table F_time(
	 f_st_time datetime,
	 f_en_time datetime,
	 f_ID varchar(25),
	 ----航班号  外键
	 f_st_city nvarchar(10),
	 f_en_city nvarchar(10),
	 f_st_port nvarchar(10),
	 f_en_port nvarchar(10),
	 --站序
	 f_order int default(1),
	 f_price int,
	  foreign key (f_ID) references F_Info(f_ID)
)

-----
---用户
create table usr(
	usr_account varchar(20) primary key,  --注册时随机分配
	usr_ID varchar(20),   
	usr_email varchar(35),
	usr_avater varchar(70), --存头像地址
	usr_viplevel int,
	usr_pwd varchar(30)
)



create table whole_order(
	order_ID int identity(1,1) primary key,--订单编号
	order_time datetime,
	order_status nvarchar(10),  --是否已支付
	vehicle_type nvarchar(10),
	usr_ID varchar(20) 
)

create table f_ticket(
	  ---主键
	 ticket_ID int identity(1,1) primary key,
	 --外键
	 f_ID varchar(25),
	 order_ID int,--订单编号  自增

	 f_st_time datetime,
	 f_en_time datetime,
	 ticket_status nvarchar(10),
	 passenger_name nvarchar(15),
	 seat_type nvarchar(10),
	 f_st_port nvarchar(10),
	 f_en_port nvarchar(10),
	 f_price int,
	 foreign key(f_ID) references F_Info(f_ID),
	 foreign key(order_ID) references whole_order(order_ID)

)

