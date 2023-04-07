USE [master]
GO
/****** Object:  Database [root]    Script Date: 2023/4/6 17:41:28 ******/
CREATE DATABASE [root]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'root', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\root.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'root_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\root_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [root] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [root].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [root] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [root] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [root] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [root] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [root] SET ARITHABORT OFF 
GO
ALTER DATABASE [root] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [root] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [root] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [root] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [root] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [root] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [root] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [root] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [root] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [root] SET  DISABLE_BROKER 
GO
ALTER DATABASE [root] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [root] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [root] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [root] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [root] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [root] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [root] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [root] SET RECOVERY FULL 
GO
ALTER DATABASE [root] SET  MULTI_USER 
GO
ALTER DATABASE [root] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [root] SET DB_CHAINING OFF 
GO
ALTER DATABASE [root] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [root] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [root] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [root] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'root', N'ON'
GO
ALTER DATABASE [root] SET QUERY_STORE = OFF
GO
USE [root]
GO
/****** Object:  Table [dbo].[aircraft]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[aircraft](
	[aircraft_ID] [varchar](20) NOT NULL,
	[aircraft_type] [varchar](20) NULL,
	[aircraft_T_num] [int] NULL,
	[aircraft_M_num] [int] NULL,
	[aircraft_L_num] [int] NULL,
	[aircraft_status] [nvarchar](3) NULL,
	[service_provider_ID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[aircraft_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flight_info]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flight_info](
	[flight_schedule] [varchar](18) NULL,
	[flight_ID] [varchar](25) NOT NULL,
	[flight_start_city] [nvarchar](10) NULL,
	[flight_end_city] [nvarchar](10) NULL,
	[flight_start_port] [nvarchar](10) NULL,
	[flight_end_port] [nvarchar](10) NULL,
	[flight_status] [nvarchar](20) NULL,
	[aircraft_type] [varchar](20) NULL,
	[direct_flag] [nvarchar](2) NULL,
	[aircraft_ID] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[flight_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flight_ticket]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flight_ticket](
	[ticket_ID] [int] IDENTITY(1,1) NOT NULL,
	[flight_ID] [varchar](25) NULL,
	[order_ID] [int] NULL,
	[flight_start_time] [datetime] NULL,
	[flight_end_time] [datetime] NULL,
	[ticket_status] [nvarchar](10) NULL,
	[passenger_name] [nvarchar](15) NULL,
	[seat_type] [nvarchar](10) NULL,
	[flight_start_port] [nvarchar](10) NULL,
	[flight_end_port] [nvarchar](10) NULL,
	[flight_price] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[ticket_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flight_time]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flight_time](
	[flight_start_time] [datetime] NULL,
	[flight_end_time] [datetime] NULL,
	[flight_ID] [varchar](25) NULL,
	[flight_start_city] [nvarchar](10) NULL,
	[flight_end_city] [nvarchar](10) NULL,
	[flight_start_port] [nvarchar](10) NULL,
	[flight_end_port] [nvarchar](10) NULL,
	[flight_order] [int] NULL,
	[flight_L_price] [money] NULL,
	[flight_M_price] [money] NULL,
	[flight_T_price] [money] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[passenger]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[passenger](
	[usr_ID] [varchar](20) NULL,
	[passenger_name] [nvarchar](15) NULL,
	[passenger_ID] [char](18) NULL,
	[phone_number] [varchar](18) NULL,
	[passenger_type] [varchar](20) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[railway_carriage_information]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[railway_carriage_information](
	[train_type] [varchar](20) NOT NULL,
	[one_railway_carriage_seat_num] [int] NULL,
	[railway_carriage_type] [varchar](20) NULL,
	[railway_carriage_ID] [int] NOT NULL,
	[each_row_num] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[train_type] ASC,
	[railway_carriage_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[service_provider]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[service_provider](
	[service_provider_ID] [int] IDENTITY(1,1) NOT NULL,
	[service_provider_pwd] [varchar](30) NULL,
	[service_provider_name] [nvarchar](30) NULL,
	[push_money] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[service_provider_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[super_usr]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[super_usr](
	[super_usr_ID] [char](5) NOT NULL,
	[super_usr_name] [nvarchar](20) NULL,
	[super_usr_pwd] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[super_usr_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[train_information]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[train_information](
	[train_ID] [varchar](20) NOT NULL,
	[train_type] [varchar](20) NULL,
	[train_one_num] [int] NULL,
	[train_two_num] [int] NULL,
	[train_sp_num] [int] NULL,
	[train_ssp_num] [int] NULL,
	[train_status] [nvarchar](3) NULL,
	[train_railway_carriage_num] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[train_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[train_number_details]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[train_number_details](
	[train_arrive_time] [datetime] NULL,
	[train_leave_time] [datetime] NULL,
	[train_number_ID] [varchar](25) NOT NULL,
	[train_arrive_city] [nvarchar](10) NULL,
	[train_arrive_station] [nvarchar](10) NULL,
	[train_order] [int] NOT NULL,
	[mileage] [int] NULL,
	[train_one_price] [int] NULL,
	[train_two_price] [int] NULL,
	[train_sp_price] [int] NULL,
	[train_ssp_price] [int] NULL,
	[dateorder] [int] NULL,
 CONSTRAINT [PK_train_number_details] PRIMARY KEY CLUSTERED 
(
	[train_number_ID] ASC,
	[train_order] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[train_number_information]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[train_number_information](
	[train_number_ID] [varchar](25) NOT NULL,
	[train_ID] [varchar](20) NOT NULL,
	[train_start_city] [nvarchar](10) NULL,
	[train_end_city] [nvarchar](10) NULL,
	[train_start_station] [nvarchar](10) NULL,
	[train_end_station] [nvarchar](10) NULL,
	[train_number_status] [nvarchar](20) NULL,
	[train_start_time] [datetime] NULL,
	[train_end_time] [datetime] NULL,
 CONSTRAINT [PK__train_nu__F25C363EFE64AD60] PRIMARY KEY CLUSTERED 
(
	[train_number_ID] ASC,
	[train_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[train_ticket]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[train_ticket](
	[train_ticket_ID] [int] IDENTITY(1,1) NOT NULL,
	[train_number_ID] [varchar](25) NULL,
	[order_ID] [int] NULL,
	[train_ticket_start_time] [datetime] NULL,
	[train_ticket_end_time] [datetime] NULL,
	[ticket_status] [nvarchar](10) NULL,
	[passenger_name] [nvarchar](15) NULL,
	[seat_type] [nvarchar](10) NULL,
	[train_start_station] [nvarchar](10) NULL,
	[train_end_station] [nvarchar](10) NULL,
	[train_price] [int] NULL,
 CONSTRAINT [PK__train_ti__A26EF9E8588DCBF8] PRIMARY KEY CLUSTERED 
(
	[train_ticket_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usr]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usr](
	[usr_account] [varchar](20) NOT NULL,
	[usr_ID] [varchar](20) NULL,
	[usr_email] [varchar](255) NULL,
	[usr_avatar] [varchar](255) NULL,
	[usr_viplevel] [int] NULL,
	[usr_pwd] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[usr_account] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[usr_email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[usr_avatar] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[usr_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[whole_order]    Script Date: 2023/4/6 17:41:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[whole_order](
	[order_ID] [int] IDENTITY(1,1) NOT NULL,
	[order_time] [datetime] NULL,
	[order_flag] [nvarchar](10) NULL,
	[vehicle_type] [nvarchar](10) NULL,
	[usr_ID] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[order_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[aircraft] ADD  DEFAULT ('可用') FOR [aircraft_status]
GO
ALTER TABLE [dbo].[flight_time] ADD  DEFAULT ((1)) FOR [flight_order]
GO
ALTER TABLE [dbo].[train_information] ADD  DEFAULT ('可用') FOR [train_status]
GO
ALTER TABLE [dbo].[aircraft]  WITH CHECK ADD FOREIGN KEY([service_provider_ID])
REFERENCES [dbo].[service_provider] ([service_provider_ID])
GO
ALTER TABLE [dbo].[flight_info]  WITH CHECK ADD FOREIGN KEY([aircraft_ID])
REFERENCES [dbo].[aircraft] ([aircraft_ID])
GO
ALTER TABLE [dbo].[flight_ticket]  WITH CHECK ADD FOREIGN KEY([flight_ID])
REFERENCES [dbo].[flight_info] ([flight_ID])
GO
ALTER TABLE [dbo].[flight_ticket]  WITH CHECK ADD FOREIGN KEY([order_ID])
REFERENCES [dbo].[whole_order] ([order_ID])
GO
ALTER TABLE [dbo].[flight_time]  WITH CHECK ADD FOREIGN KEY([flight_ID])
REFERENCES [dbo].[flight_info] ([flight_ID])
GO
ALTER TABLE [dbo].[passenger]  WITH CHECK ADD FOREIGN KEY([usr_ID])
REFERENCES [dbo].[usr] ([usr_ID])
GO
ALTER TABLE [dbo].[train_number_information]  WITH CHECK ADD  CONSTRAINT [FK__train_num__train__2F10007B] FOREIGN KEY([train_ID])
REFERENCES [dbo].[train_information] ([train_ID])
GO
ALTER TABLE [dbo].[train_number_information] CHECK CONSTRAINT [FK__train_num__train__2F10007B]
GO
ALTER TABLE [dbo].[aircraft]  WITH CHECK ADD CHECK  (([aircraft_status]='可用' OR [aircraft_status]='不可用'))
GO
ALTER TABLE [dbo].[flight_info]  WITH CHECK ADD CHECK  (([direct_flag]='是' OR [direct_flag]='否'))
GO
ALTER TABLE [dbo].[passenger]  WITH CHECK ADD CHECK  (([passenger_type]='火车' OR [passenger_type]='飞机'))
GO
ALTER TABLE [dbo].[railway_carriage_information]  WITH CHECK ADD CHECK  (([railway_carriage_type]='软卧' OR [railway_carriage_type]='软座/特等座' OR [railway_carriage_type]='硬卧/二等座' OR [railway_carriage_type]='硬座/一等座'))
GO
ALTER TABLE [dbo].[railway_carriage_information]  WITH CHECK ADD CHECK  (([train_type]='新空直达' OR [train_type]='快速' OR [train_type]='新空特快' OR [train_type]='快速' OR [train_type]='新空快速' OR [train_type]='高速动车' OR [train_type]='动车组' OR [train_type]='城际高速' OR [train_type]='新空普客' OR [train_type]='普客' OR [train_type]='新空普快' OR [train_type]='普快'))
GO
ALTER TABLE [dbo].[service_provider]  WITH CHECK ADD CHECK  ((len([service_provider_pwd])>=(6) AND len([service_provider_pwd])<=(30)))
GO
ALTER TABLE [dbo].[super_usr]  WITH CHECK ADD CHECK  ((len([super_usr_pwd])>=(6) AND len([super_usr_pwd])<=(30)))
GO
ALTER TABLE [dbo].[train_information]  WITH CHECK ADD CHECK  (([train_type]='新空直达' OR [train_type]='快速' OR [train_type]='新空特快' OR [train_type]='快速' OR [train_type]='新空快速' OR [train_type]='高速动车' OR [train_type]='动车组' OR [train_type]='城际高速' OR [train_type]='新空普客' OR [train_type]='普客' OR [train_type]='新空普快' OR [train_type]='普快'))
GO
ALTER TABLE [dbo].[train_information]  WITH CHECK ADD CHECK  (([train_status]='可用' OR [train_status]='不可用'))
GO
ALTER TABLE [dbo].[train_number_information]  WITH CHECK ADD  CONSTRAINT [CK__train_num__train__2E1BDC42] CHECK  (([train_number_status]='停运' OR [train_number_status]='正常'))
GO
ALTER TABLE [dbo].[train_number_information] CHECK CONSTRAINT [CK__train_num__train__2E1BDC42]
GO
ALTER TABLE [dbo].[train_ticket]  WITH CHECK ADD  CONSTRAINT [CK__train_tic__ticke__47DBAE45] CHECK  (([ticket_status]='已使用' OR [ticket_status]='已退票' OR [ticket_status]='客票已换开' OR [ticket_status]='客票有效'))
GO
ALTER TABLE [dbo].[train_ticket] CHECK CONSTRAINT [CK__train_tic__ticke__47DBAE45]
GO
ALTER TABLE [dbo].[whole_order]  WITH CHECK ADD CHECK  (([order_flag]='是' OR [order_flag]='否'))
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总起点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_info', @level2type=N'COLUMN',@level2name=N'flight_start_city'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总终点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_info', @level2type=N'COLUMN',@level2name=N'flight_end_city'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总起点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_info', @level2type=N'COLUMN',@level2name=N'flight_start_port'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总终点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_info', @level2type=N'COLUMN',@level2name=N'flight_end_port'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'航班状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_info', @level2type=N'COLUMN',@level2name=N'flight_status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否直飞' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_info', @level2type=N'COLUMN',@level2name=N'direct_flag'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总起点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_ticket', @level2type=N'COLUMN',@level2name=N'flight_start_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总终点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_ticket', @level2type=N'COLUMN',@level2name=N'flight_end_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总起点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_ticket', @level2type=N'COLUMN',@level2name=N'flight_start_port'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总终点' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_ticket', @level2type=N'COLUMN',@level2name=N'flight_end_port'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'经济舱本段价格' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_time', @level2type=N'COLUMN',@level2name=N'flight_L_price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商务舱本段价格' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_time', @level2type=N'COLUMN',@level2name=N'flight_M_price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'头等舱本段价格' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'flight_time', @level2type=N'COLUMN',@level2name=N'flight_T_price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'passenger', @level2type=N'COLUMN',@level2name=N'usr_ID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'乘客姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'passenger', @level2type=N'COLUMN',@level2name=N'passenger_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'身份证号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'passenger', @level2type=N'COLUMN',@level2name=N'passenger_ID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'电话号码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'passenger', @level2type=N'COLUMN',@level2name=N'phone_number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'乘客类型（飞机，火车）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'passenger', @level2type=N'COLUMN',@level2name=N'passenger_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'列车类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'railway_carriage_information', @level2type=N'COLUMN',@level2name=N'train_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'座位数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'railway_carriage_information', @level2type=N'COLUMN',@level2name=N'one_railway_carriage_seat_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'车厢类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'railway_carriage_information', @level2type=N'COLUMN',@level2name=N'railway_carriage_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'车厢号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'railway_carriage_information', @level2type=N'COLUMN',@level2name=N'railway_carriage_ID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'每排座位数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'railway_carriage_information', @level2type=N'COLUMN',@level2name=N'each_row_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'约定提成' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'service_provider', @level2type=N'COLUMN',@level2name=N'push_money'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'火车编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_information', @level2type=N'COLUMN',@level2name=N'train_ID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'火车类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_information', @level2type=N'COLUMN',@level2name=N'train_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'硬卧/一等座数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_information', @level2type=N'COLUMN',@level2name=N'train_one_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'硬座/二等座数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_information', @level2type=N'COLUMN',@level2name=N'train_two_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'软座/特等座数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_information', @level2type=N'COLUMN',@level2name=N'train_sp_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'软卧数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_information', @level2type=N'COLUMN',@level2name=N'train_ssp_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'车厢总数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_information', @level2type=N'COLUMN',@level2name=N'train_railway_carriage_num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'到达该站时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_arrive_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'离开该站时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_leave_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'车次编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_number_ID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'到达站点所在城市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_arrive_city'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'到达站点名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_arrive_station'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'站序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_order'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'里程' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'mileage'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'硬卧/一等座票价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_one_price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'硬座/二等座票价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_two_price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'软座/特等座票价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_sp_price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'软卧票价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'train_ssp_price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'相对日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_details', @level2type=N'COLUMN',@level2name=N'dateorder'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'始发城市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_information', @level2type=N'COLUMN',@level2name=N'train_start_city'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'终点城市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_information', @level2type=N'COLUMN',@level2name=N'train_end_city'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'始发站' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_information', @level2type=N'COLUMN',@level2name=N'train_start_station'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'终点站' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_information', @level2type=N'COLUMN',@level2name=N'train_end_station'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'车次状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_information', @level2type=N'COLUMN',@level2name=N'train_number_status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'始发时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_information', @level2type=N'COLUMN',@level2name=N'train_start_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'终点时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_number_information', @level2type=N'COLUMN',@level2name=N'train_end_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_ticket', @level2type=N'COLUMN',@level2name=N'train_ticket_ID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单编号  自增' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'train_ticket', @level2type=N'COLUMN',@level2name=N'order_ID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'存头像地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'usr', @level2type=N'COLUMN',@level2name=N'usr_avatar'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否已支付' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'whole_order', @level2type=N'COLUMN',@level2name=N'order_ID'
GO
USE [master]
GO
ALTER DATABASE [root] SET  READ_WRITE 
GO
