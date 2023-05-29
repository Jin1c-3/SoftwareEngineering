USE SEP


DECLARE @currentDate DATE;

SET @currentDate = GETDATE();


DECLARE @command1 NVARCHAR(MAX);
DECLARE @command2 NVARCHAR(MAX);
SET @command1 = N'EXEC GenerateSeats ''' + CONVERT(NVARCHAR(10), @currentDate, 120) + N'''';
SET @command2= N'EXEC  seat_availability_''' + CONVERT(NVARCHAR(10), @currentDate, 120) + N'''';


EXEC msdb.dbo.sp_add_job
    @job_name = 'create_table';
EXEC msdb.dbo.sp_add_jobstep
    @job_name = 'create_table',
    @step_name = 'Step1',
    @subsystem = 'TSQL',
    @command =@command1;
EXEC msdb.dbo.sp_add_jobstep
    @job_name ='create_table',
    @step_name = 'Step2',
    @subsystem = 'TSQL',
    @command = @command2;




EXEC msdb.dbo.sp_attach_schedule
    @job_name =  'create_table',
    @schedule_name = 'Schedule1';

DECLARE @table_name1 NVARCHAR(50);
SET @table_name1 ='seat_' + CONVERT(varchar(10), @currentDate, 120)
DECLARE @table_name2 NVARCHAR(50);
SET @table_name2 = 'seat_availability_' + CONVERT(VARCHAR(10), @currentDate, 120);
DECLARE @command3 NVARCHAR(MAX);
SET @command3 = N'DROP TABLE ' + QUOTENAME(@table_name1);
DECLARE @command4 NVARCHAR(MAX);
SET @command4 = N'DROP TABLE ' + QUOTENAME(@table_name2);
EXEC msdb.dbo.sp_add_job
    @job_name = 'delete_table';

EXEC msdb.dbo.sp_add_jobstep
    @job_name =  'delete_table',
    @step_name = 'Step2',
    @subsystem = 'TSQL',
    @command = @command4;

EXEC msdb.dbo.sp_add_jobstep
    @job_name =  'delete_table',
    @step_name = 'Step1',
    @subsystem = 'TSQL',
    @command = @command3;

EXEC msdb.dbo.sp_attach_schedule
    @job_name = 'delete_table',
    @schedule_name = 'Schedule2';






use msdb
go
EXEC sp_add_job @job_name = 'Check for timeout orders', 
    @description = 'Checks for orders over 20 minutes',
    @enabled = 1,
    @delete_level = 0
	
EXEC sp_add_jobstep @job_name = 'Check for timeout orders', 
    @step_name = 'Select old orders',
    @subsystem = 'TSQL',  
    @command = 'exec check_order'


EXEC msdb.dbo.sp_attach_schedule
    @job_name =  'Check for timeout orders',
    @schedule_name = 'check for order';



