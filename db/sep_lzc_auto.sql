USE [SEP]
GO
/****** Object:  Table [dbo].[aircraft]    Script Date: 2023/4/3 19:12:30 ******/
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
/****** Object:  Table [dbo].[flight_info]    Script Date: 2023/4/3 19:12:30 ******/
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
/****** Object:  Table [dbo].[flight_ticket]    Script Date: 2023/4/3 19:12:30 ******/
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
/****** Object:  Table [dbo].[flight_time]    Script Date: 2023/4/3 19:12:30 ******/
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
/****** Object:  Table [dbo].[service_provider]    Script Date: 2023/4/3 19:12:30 ******/
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
/****** Object:  Table [dbo].[super_usr]    Script Date: 2023/4/3 19:12:30 ******/
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
/****** Object:  Table [dbo].[usr]    Script Date: 2023/4/3 19:12:30 ******/
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
/****** Object:  Table [dbo].[whole_order]    Script Date: 2023/4/3 19:12:30 ******/
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
ALTER TABLE [dbo].[aircraft]  WITH CHECK ADD CHECK  (([aircraft_status]='可用' OR [aircraft_status]='不可用'))
GO
ALTER TABLE [dbo].[flight_info]  WITH CHECK ADD CHECK  (([direct_flag]='是' OR [direct_flag]='否'))
GO
ALTER TABLE [dbo].[service_provider]  WITH CHECK ADD CHECK  ((len([service_provider_pwd])>=(6) AND len([service_provider_pwd])<=(30)))
GO
ALTER TABLE [dbo].[super_usr]  WITH CHECK ADD CHECK  ((len([super_usr_pwd])>=(6) AND len([super_usr_pwd])<=(30)))
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
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'约定提成' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'service_provider', @level2type=N'COLUMN',@level2name=N'push_money'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'存头像地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'usr', @level2type=N'COLUMN',@level2name=N'usr_avatar'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否已支付' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'whole_order', @level2type=N'COLUMN',@level2name=N'order_ID'
GO
