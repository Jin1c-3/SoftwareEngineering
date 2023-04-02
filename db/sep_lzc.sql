----����Ա��  00001Ϊroot����Ա
create table super_usr(
<<<<<<< Updated upstream
	super_usr_ID char(5) primary key,
	super_usr_name nvarchar(20),
	super_usr_pwd varchar(30) check (len(super_usr_pwd) between 6 and 30)
=======
		super_usr_ID char(5) primary key,
		super_usr_name nvarchar(20),
		super_usr_pwd varchar(30) check (len(super_usr_pwd) between 6 and 30)
>>>>>>> Stashed changes
)
----�����̱�
create table service_provider(
	service_provider_ID int primary key identity(1,1),
<<<<<<< Updated upstream
	service_provider_pwd varchar(30) check (len(service_provider_pwd) between 6 and 30),	--����6-30λ
	service_provider_name nvarchar(30),
	push_money float	--Լ�����
=======
	service_provider_pwd varchar(30) check (len(service_provider_pwd) between 6 and 30),
	service_provider_name nvarchar(30),
	push_money float
>>>>>>> Stashed changes
)
----�ɻ���Ϣ��
create table aircraft(
	aircraft_ID varchar(20) primary key,
	aircraft_type varchar(20),
<<<<<<< Updated upstream
	aircraft_T_num int,	--ͷ�Ȳ�
	aircraft_M_num int,	--�����
	aircraft_L_num int,	--���ò�
=======
	Air_T_num int,
	Air_M_num int,
	Air_L_num int,
>>>>>>> Stashed changes
	aircraft_status nvarchar(3) check (aircraft_status = '����' or aircraft_status = '������' ) default('����'),
	service_provider_ID int ,
	foreign key(service_provider_ID)  references service_provider(service_provider_ID)
)
----������Ϣ��
create table flight_info(
	--����
	 T varchar(18),
	 flight_ID varchar(25) primary key,
	 flight_start_city nvarchar(10),
	 flight_end_city nvarchar(10),
	 flight_start_port nvarchar(10),
	 flight_end_port nvarchar(10),
	 flight_status nvarchar(20),
	 aircraft_type varchar(20),
	 direct_flag nvarchar(2) check(direct_flag = '��' or direct_flag = '��'),
	 aircraft_ID varchar(20),
	 foreign key(aircraft_ID) references Aircraft(aircraft_ID)
)
----�ɻ��г���Ϣ��
create table flight_time(
	 flight_start_time datetime,   
	 flight_end_time datetime,
	 flight_ID varchar(25),
	 ----�����  ���
	 flight_start_city nvarchar(10),
	 flight_end_city nvarchar(10),
	 flight_start_port nvarchar(10),
	 flight_end_port nvarchar(10),
	 --վ��
<<<<<<< Updated upstream
	 flight_order int default(1),
	 flight_price int,
	  foreign key (flight_ID) references flight_info(flight_ID)
=======
	 f_order int default(1),
	 f_price int,
	  foreign key (f_ID) references flight_info(flight_ID)
>>>>>>> Stashed changes
)

---�û�
create table usr(
	usr_account varchar(20) primary key,  --�û��˺� ע��ʱ�������
	usr_ID varchar(20) unique,   --uuid
	usr_email varchar(35) unique,  
	usr_avater varchar(70) unique, --��ͷ���ַ
	usr_viplevel int,
	usr_pwd varchar(30)
)


--�ܶ����� �����ɻ���
create table whole_order(
	order_ID int identity(1,1) primary key,--�������
	order_time datetime,
	order_status nvarchar(10),  --�Ƿ���֧��
	vehicle_type nvarchar(10),
	usr_ID varchar(20) 
)

create table flight_ticket(
	  ---����
	 ticket_ID int identity(1,1) primary key,
	 --���
	 flight_ID varchar(25),
	 order_ID int,--�������  ����

	 flight_start_time datetime,
	 flight_end_time datetime,
	 ticket_status nvarchar(10),
	 passenger_name nvarchar(15),
	 seat_type nvarchar(10),
<<<<<<< Updated upstream
	 flight_start_port nvarchar(10),
	 flight_end_port nvarchar(10),
	 flight_price int,
	 foreign key(flight_ID) references flight_info(flight_ID),
=======
	 f_st_port nvarchar(10),
	 f_en_port nvarchar(10),
	 f_price int,
	 foreign key(f_ID) references flight_info(flight_ID),
>>>>>>> Stashed changes
	 foreign key(order_ID) references whole_order(order_ID)
)

