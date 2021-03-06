USE [master]
GO
/****** Object:  Database [pcStore]    Script Date: 31/5/2021 3:57:36 PM ******/
CREATE DATABASE [pcStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'pcStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\pcStore.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'pcStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\pcStore_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [pcStore] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [pcStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [pcStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [pcStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [pcStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [pcStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [pcStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [pcStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [pcStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [pcStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [pcStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [pcStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [pcStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [pcStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [pcStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [pcStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [pcStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [pcStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [pcStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [pcStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [pcStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [pcStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [pcStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [pcStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [pcStore] SET RECOVERY FULL 
GO
ALTER DATABASE [pcStore] SET  MULTI_USER 
GO
ALTER DATABASE [pcStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [pcStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [pcStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [pcStore] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [pcStore] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'pcStore', N'ON'
GO
USE [pcStore]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 31/5/2021 3:57:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[NameVN] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Customers]    Script Date: 31/5/2021 3:57:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[Id] [nvarchar](20) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Photo] [nvarchar](max) NOT NULL,
	[Activated] [bit] NOT NULL,
	[Admin] [bit] NOT NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 31/5/2021 3:57:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NOT NULL,
	[ProductId] [int] NOT NULL,
	[UnitPrice] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Discount] [float] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 31/5/2021 3:57:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[CustomerId] [nvarchar](20) NOT NULL,
	[OrderDate] [datetime] NOT NULL,
	[Address] [nvarchar](max) NOT NULL,
	[Amount] [float] NOT NULL,
	[Description] [nvarchar](1000) NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Products]    Script Date: 31/5/2021 3:57:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](60) NOT NULL,
	[UnitPrice] [float] NOT NULL,
	[Image] [nvarchar](max) NOT NULL,
	[ProductDate] [date] NOT NULL,
	[Available] [bit] NOT NULL,
	[CategoryId] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Description] [nvarchar](max) NULL,
	[Discount] [float] NOT NULL,
	[ViewCount] [int] NOT NULL,
	[Special] [bit] NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([Id], [Name], [NameVN]) VALUES (18, N'LapTop', N'Máy Tính Xách Tay')
INSERT [dbo].[Categories] ([Id], [Name], [NameVN]) VALUES (19, N'DeskTop', N'Máy Tính Bàn')
INSERT [dbo].[Categories] ([Id], [Name], [NameVN]) VALUES (20, N'Keyboard ', N'Bàn Phím')
INSERT [dbo].[Categories] ([Id], [Name], [NameVN]) VALUES (21, N'Mouse', N'Chuột')
INSERT [dbo].[Categories] ([Id], [Name], [NameVN]) VALUES (22, N'Monitor', N'Màng Hình')
INSERT [dbo].[Categories] ([Id], [Name], [NameVN]) VALUES (23, N'Headphone', N'Tai Nghe')
SET IDENTITY_INSERT [dbo].[Categories] OFF
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'1111111111111', N'1111111111111', N'11111111', N'1@gmail.com', N'user.jpq', 1, 0)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'1234564', N'4444444', N'44444444', N'nguyentoi53@gmail.com', N'3_zing.jpg', 1, 0)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'1234567', N'1234567', N'1234567dddd', N'nguyentoi531@gmail.com', N'coca.jpg', 1, 0)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'123456aaaaa', N'aaaaaaaaa', N'aaaaaaaa', N'g@gmail.com', N'user.jpg', 1, 0)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'23456789', N'123456', N'2345678910', N'5@gmail.com', N'avt.jpg', 1, 1)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'dfgdfgdfgdf', N'dfgdfgdfgdfg', N'dfgdfg', N'f@gmail.com', N'pngtree-blue-samurai-esports-logo-gaming-mascot-logo-png-image_1003048.jpg', 1, 0)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'dssdsdfgsdfg', N'sdfgdfsdfgsdf', N'sdfgsdfgfsd', N'sd@gmail.com', N'user.jpg', 1, 0)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'nguyentoi53', N'1234567', N'Nguyễn Trần Ngọc Tới ', N'nguyentoi54@gmail.com', N'user.jpg', 1, 0)
INSERT [dbo].[Customers] ([Id], [Password], [Fullname], [Email], [Photo], [Activated], [Admin]) VALUES (N'nguyentoi57', N'123456', N'Nguyễn Trần Ngọc Tới', N'a@gmail.com', N'dax8mou-f6d6f0fa-bf98-46d4-a8b7-a578b7896f64.jpg', 1, 0)
SET IDENTITY_INSERT [dbo].[OrderDetails] ON 

INSERT [dbo].[OrderDetails] ([Id], [OrderId], [ProductId], [UnitPrice], [Quantity], [Discount]) VALUES (38, 36, 1, 50, 1, 0.1)
INSERT [dbo].[OrderDetails] ([Id], [OrderId], [ProductId], [UnitPrice], [Quantity], [Discount]) VALUES (39, 36, 2, 100, 1, 0.1)
INSERT [dbo].[OrderDetails] ([Id], [OrderId], [ProductId], [UnitPrice], [Quantity], [Discount]) VALUES (40, 36, 3, 70, 1, 0.2)
SET IDENTITY_INSERT [dbo].[OrderDetails] OFF
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([Id], [CustomerId], [OrderDate], [Address], [Amount], [Description]) VALUES (28, N'1111111111111', CAST(N'2021-05-30 00:00:00.000' AS DateTime), N'', 50, N'')
INSERT [dbo].[Orders] ([Id], [CustomerId], [OrderDate], [Address], [Amount], [Description]) VALUES (29, N'1234564', CAST(N'2021-05-30 00:00:00.000' AS DateTime), N'', 75, N'')
INSERT [dbo].[Orders] ([Id], [CustomerId], [OrderDate], [Address], [Amount], [Description]) VALUES (34, N'1234567', CAST(N'2021-05-31 00:00:00.000' AS DateTime), N'', 100, N'')
INSERT [dbo].[Orders] ([Id], [CustomerId], [OrderDate], [Address], [Amount], [Description]) VALUES (35, N'1234567', CAST(N'2021-05-31 00:00:00.000' AS DateTime), N'', 75, N'')
INSERT [dbo].[Orders] ([Id], [CustomerId], [OrderDate], [Address], [Amount], [Description]) VALUES (36, N'23456789', CAST(N'2021-05-31 00:00:00.000' AS DateTime), N'', 191, N'')
SET IDENTITY_INSERT [dbo].[Orders] OFF
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (1, N'LapTop Acer1', 50, N'Acer-Nitro-5-AN515-54-01.jpg', CAST(N'2021-01-01' AS Date), 0, 18, 10, N'<br>', 0.1, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (2, N'LapTop Acer2', 100, N'aspire7_a715-75g-41g_black-01.png', CAST(N'2021-01-02' AS Date), 1, 18, 10, N'<br>', 0.1, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (3, N'LapTop Acer3', 70, N'PH315-52-6-1.jpg', CAST(N'2021-01-03' AS Date), 1, 18, 10, N'<br>', 0.2, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (4, N'LapTop Acer4', 80, N'nitro-7-6-1.jpg', CAST(N'2021-01-04' AS Date), 1, 18, 60, N'<br>', 0, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (5, N'LapTop Msi 1', 100, N'4-1.jpg', CAST(N'2000-05-06' AS Date), 1, 18, 10, N'<br>', 0.5, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (6, N'LapTop Msi 2', 100, N'Bravo-1.jpg', CAST(N'2000-05-09' AS Date), 1, 18, 5, N'<br>', 0.4, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (8, N'LapTop Msi 3', 50, N'GF63_8RD_1.jpg', CAST(N'2021-01-01' AS Date), 1, 18, 10, N'<br>', 0, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (9, N'LapTop Msi 4', 50, N'GF63_8RD_2.jpg', CAST(N'2021-01-04' AS Date), 1, 18, 10, N'<br>', 0.2, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (10, N'LapTop Asus 1', 50, N'FX505_1.jpg', CAST(N'2021-01-03' AS Date), 1, 18, 10, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (12, N'LapTop Asus 2', 50, N'tuf-a15-1.jpg', CAST(N'2021-01-12' AS Date), 1, 18, 1, N'<br>', 0.1, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (13, N'LapTop Asus 3', 50, N'G531GD-02-1.jpg', CAST(N'2021-01-12' AS Date), 1, 18, 1, N'<br>', 0.1, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (14, N'LapTop Asus 4', 50, N'zep-g14-1.jpg', CAST(N'2021-01-12' AS Date), 1, 18, 1, N'<br>', 0.1, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (15, N'LapTop Dell 1', 100, N'Dell-G3-3590-5-1.jpg', CAST(N'2021-01-01' AS Date), 1, 18, 10, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (16, N'LapTop Dell 2', 50, N'g5-5590-1-1.jpg', CAST(N'2021-01-01' AS Date), 1, 18, 20, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (17, N'LapTop Lenovo 1', 70, N'L340-2-1.jpg', CAST(N'2021-01-09' AS Date), 1, 18, 20, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (18, N'LapTop Lenovo 2', 70, N'Y530_1.jpg', CAST(N'2021-10-10' AS Date), 0, 18, 30, N'<br>', 0, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (19, N'LapTop Lenovo 3', 80, N'ideapad-gaming-3-1.jpg', CAST(N'2021-01-01' AS Date), 1, 18, 50, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (20, N'PC 1', 200, N'Strike-_new_mouse_compressed.jpg', CAST(N'2021-01-03' AS Date), 1, 19, 30, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (21, N'PC 2', 100, N'wave_new_compressed.jpg', CAST(N'2021-01-08' AS Date), 1, 19, 20, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (22, N'PC 3', 100, N'IMG_3715_compressed.jpg', CAST(N'2021-01-11' AS Date), 1, 19, 30, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (23, N'PC 4', 50, N'X_Aorus_compressed.jpg', CAST(N'2021-01-12' AS Date), 1, 19, 80, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (24, N'Ban Phim 1', 10, N'Logitech-G213.jpg', CAST(N'2021-05-05' AS Date), 1, 20, 12, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (25, N'Bàn Phím 2', 2, N'Alloy_fps_pro_1_compressed-1.jpg', CAST(N'2021-01-08' AS Date), 1, 20, 10, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (26, N'Bàn Phím 3', 9, N'Alloy_Elite_RGB_1_compressed.jpg', CAST(N'2021-01-10' AS Date), 1, 20, 10, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (28, N'Bàn Phím 4', 10, N'SK621_1_compressed-1.jpg', CAST(N'2021-08-08' AS Date), 1, 20, 10, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (29, N'Chuột 1', 1, N'M2_1_compressed.jpg', CAST(N'2021-05-05' AS Date), 1, 21, 12, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (30, N'Chuột 2', 3, N'G402_nopad_compressed.jpg', CAST(N'2021-05-09' AS Date), 1, 21, 15, N'<br>', 0.2, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (31, N'Chuột 3', 8, N'DA_Essential_1_compressed-1.jpg', CAST(N'2021-08-10' AS Date), 1, 21, 15, N'<br>', 0, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (32, N'Chuột 4', 12, N'13019089_master.jpg', CAST(N'2021-05-12' AS Date), 1, 21, 5, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (33, N'Màn Hình 1', 50, N'01-XG32VQR.jpg', CAST(N'2021-05-05' AS Date), 1, 22, 12, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (34, N'Màn Hình 2', 100, N'C27G75T_001_Front_Black_compressed.jpg', CAST(N'2021-05-09' AS Date), 1, 22, 5, N'<br>', 0.1, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (35, N'Màn Hình 3', 150, N'X34P_1_compressed.jpg', CAST(N'2021-05-08' AS Date), 1, 22, 10, N'<br>', 0, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (36, N'Màn Hình 4', 200, N'CV27F_1_compressed-1.jpg', CAST(N'2021-05-07' AS Date), 1, 22, 5, N'<br>', 0, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (37, N'Tai Nghe 1', 20, N'Outlier-Black_1_compressed-1.jpg', CAST(N'2021-07-07' AS Date), 1, 23, 5, N'<br>', 0, 0, 1)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (38, N'Tai Nghe 2', 12, N'RIG500HX_compressed.jpg', CAST(N'2021-06-06' AS Date), 1, 23, 12, N'<br>', 0.1, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (39, N'Tai Nghe 3', 14, N'HS50_1.jpg', CAST(N'2021-04-04' AS Date), 1, 23, 80, N'<br>', 0.1, 0, 0)
INSERT [dbo].[Products] ([Id], [Name], [UnitPrice], [Image], [ProductDate], [Available], [CategoryId], [Quantity], [Description], [Discount], [ViewCount], [Special]) VALUES (40, N'Tai Nghe 4', 5, N'G331_1_compressed-1.jpg', CAST(N'2021-03-05' AS Date), 1, 23, 100, N'<br>', 0, 0, 0)
SET IDENTITY_INSERT [dbo].[Products] OFF
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Orders] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Orders]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Products] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Products]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Customers] FOREIGN KEY([CustomerId])
REFERENCES [dbo].[Customers] ([Id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Customers]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories1] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Categories] ([Id])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories1]
GO
USE [master]
GO
ALTER DATABASE [pcStore] SET  READ_WRITE 
GO
