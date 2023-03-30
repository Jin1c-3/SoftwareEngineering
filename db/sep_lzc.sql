
----����Ա��  00001Ϊroot����Ա
create table manager(
		manager_ID char(5) primary key,
		manager_name nvarchar(20),
		manager_pwd varchar(30) check (len(manager_pwd) between 6 and 30)
)
----�����̱�
create table SerProviders(
	Ser_ID int primary key identity(1,1),
	Ser_pwd varchar(30) check (len(Ser_pwd) between 6 and 30),
	Ser_name nvarchar(30),
	profit float
)
----�ɻ���Ϣ��
create table Aircraft(
	Air_ID varchar(20) primary key,
	Air_type varchar(20),
	Air_T_num int,
	Air_M_num int,
	Air_L_num int,
	Air_status nvarchar(3) check (Air_status = '����' or Air_status = '������' ) default('����'),
	Ser_ID int ,
	foreign key(Ser_ID)  references SerProviders(Ser_ID)
)
----������Ϣ��
create table F_Info(
	--����
	 T varchar(18),
	 f_ID varchar(25) primary key,
	 f_st_city nvarchar(10),
	 f_en_city nvarchar(10),
	 f_st_port nvarchar(10),
	 f_en_port nvarchar(10),
	 f_status nvarchar(20),
	 Air_type varchar(20),
	 zhifei nvarchar(2) check(zhifei = '��' or zhifei = '��'),
	 Air_ID varchar(20),
	 foreign key(Air_ID) references Aircraft(Air_ID)
)
----�ɻ��г���Ϣ��
create table F_time(
	 f_st_time datetime,
	 f_en_time datetime,
	 f_ID varchar(25),
	 ----�����  ���
	 f_st_city nvarchar(10),
	 f_en_city nvarchar(10),
	 f_st_port nvarchar(10),
	 f_en_port nvarchar(10),
	 --վ��
	 f_order int default(1),
	 f_price int,
	  foreign key (f_ID) references F_Info(f_ID)
)

-----
---�û�
create table usr(
	usr_account varchar(20) primary key,  --ע��ʱ�������
	usr_ID varchar(20),   
	usr_email varchar(35),
	usr_avater varchar(70), --��ͷ���ַ
	usr_viplevel int,
	usr_pwd varchar(30)
)



create table whole_order(
	order_ID int identity(1,1) primary key,--�������
	order_time datetime,
	order_status nvarchar(10),  --�Ƿ���֧��
	vehicle_type nvarchar(10),
	usr_ID varchar(20) 
)

create table f_ticket(
	  ---����
	 ticket_ID int identity(1,1) primary key,
	 --���
	 f_ID varchar(25),
	 order_ID int,--�������  ����

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

