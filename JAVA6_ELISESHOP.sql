USE [master]
GO
/****** Object:  Database [JAVA6_ELISESHOP]    Script Date: 30-Nov-23 6:41:03 PM ******/
CREATE DATABASE [JAVA6_ELISESHOP]
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [JAVA6_ELISESHOP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET ARITHABORT OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET  ENABLE_BROKER 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET RECOVERY FULL 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET  MULTI_USER 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'JAVA6_ELISESHOP', N'ON'
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET QUERY_STORE = OFF
GO
USE [JAVA6_ELISESHOP]
GO
/****** Object:  Table [dbo].[account]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[username] [nvarchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[fullname] [nvarchar](80) NULL,
	[phone] [varchar](12) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[address] [nvarchar](200) NOT NULL,
	[role] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[authority]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[authority](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[role_id] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[banner]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[banner](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[collection_id] [varchar](5) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[collection]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[collection](
	[id] [varchar](5) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[create_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[image]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [varchar](20) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NOT NULL,
	[product_id] [varchar](20) NOT NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [varchar](20) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[price] [money] NOT NULL,
	[type] [nvarchar](20) NOT NULL,
	[collection_id] [varchar](5) NOT NULL,
	[length] [float] NOT NULL,
	[material] [nvarchar](150) NULL,
	[line] [nvarchar](100) NULL,
	[form] [nvarchar](150) NULL,
	[total_sold] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [varchar](10) NOT NULL,
	[role_name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shopping_order]    Script Date: 30-Nov-23 6:41:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shopping_order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_date] [date] NOT NULL,
	[account_id] [nvarchar](50) NOT NULL,
	[status] [nvarchar](20) NOT NULL,
	[payment] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'abc-1234', N'123', N'Trương Ngọc Hoa', N'0917271469', N'truonghoa@gmail.com', N'113/17 Nguyễn Kiệm', 0)
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'admin', N'123', N'Trương Hoàng Nam', N'0971187266', N'namthps25634@fpt.edu.vn', N'Công viên phần mềm Quang Trung, Q. 12', 1)
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'elise shop', N'123', N'Elise Shop', N'0982131667', N'thaoltpps25649@fpt.edu.vn', N'Công viên phần mềm Quang Trung, Q. 12', 1)
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'haru', N'123', N'Nguyễn Ngọc Xuân', N'0914011948', N'haru_nnx@gmail.com', N'225 Lý Chính Thắng, Q. 3', 0)
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'harutoyume18c146b4bf4', N'123', N'Phương', N'', N'harutoyume@gmail.com', N'', 0)
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'lttp', N'123', N'Lê Phương Thảo', N'0352462001', N'lethiphuongthaokt22@gmail.com', N'182/40 Bạch Đằng, Q. Bình Thạnh', 0)
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'mailling', N'123', N'Nông Ngọc Linh', N'0957762181', N'linh_mail@gmail.com', N'234/15 Phan Văn Hân, Q. Bình Thạnh', 0)
INSERT [dbo].[account] ([username], [password], [fullname], [phone], [email], [address], [role]) VALUES (N'staff1', N'123', N'Nguyễn Thị Yến Nhi', N'0976257331', N'nhintyps25666@fpt.edu.vn', N'Công viên phần mềm Quang Trung, Q.12', 1)
GO
SET IDENTITY_INSERT [dbo].[authority] ON 

INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (7, N'abc-1234', N'CUS')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (6, N'admin', N'CUS')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (3, N'admin', N'STA')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (5, N'elise shop', N'CUS')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (1, N'elise shop', N'MAN')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (4, N'elise shop', N'STA')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (8, N'haru', N'CUS')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (11, N'harutoyume18c146b4bf4', N'CUS')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (9, N'lttp', N'CUS')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (10, N'mailling', N'CUS')
INSERT [dbo].[authority] ([id], [username], [role_id]) VALUES (2, N'staff1', N'STA')
SET IDENTITY_INSERT [dbo].[authority] OFF
GO
SET IDENTITY_INSERT [dbo].[banner] ON 

INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (1, N'003', N'a-leisure-day-1.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (2, N'003', N'a-leisure-day-2.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (3, N'003', N'a-leisure-day-3.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (4, N'002', N'hey-1.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (5, N'002', N'hey-2.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (6, N'002', N'hey-3.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (7, N'001', N'ha-lam-1.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (8, N'001', N'ha-lam-2.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (9, N'001', N'ha-lam-3.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (10, N'004', N'theme-of-fall-1.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (11, N'004', N'theme-of-fall-2.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (12, N'004', N'theme-of-fall-3.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (13, N'005', N'reverie-1.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (14, N'005', N'reverie-2.jpeg')
INSERT [dbo].[banner] ([id], [collection_id], [name]) VALUES (15, N'005', N'reverie-3.jpeg')
SET IDENTITY_INSERT [dbo].[banner] OFF
GO
INSERT [dbo].[collection] ([id], [name], [create_date]) VALUES (N'001', N'Hạ Lam', CAST(N'2023-05-15' AS Date))
INSERT [dbo].[collection] ([id], [name], [create_date]) VALUES (N'002', N'Hey!', CAST(N'2023-10-10' AS Date))
INSERT [dbo].[collection] ([id], [name], [create_date]) VALUES (N'003', N'A leisure day', CAST(N'2023-08-15' AS Date))
INSERT [dbo].[collection] ([id], [name], [create_date]) VALUES (N'004', N'Theme of fall', CAST(N'2022-08-15' AS Date))
INSERT [dbo].[collection] ([id], [name], [create_date]) VALUES (N'005', N'Reverie', CAST(N'2022-10-15' AS Date))
GO
SET IDENTITY_INSERT [dbo].[image] ON 

INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (1, N'1AD026TI', N'1AD026TI-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (2, N'1AD026TI', N'1AD026TI-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (3, N'1AD026TI', N'1AD026TI-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (4, N'1AD026TI', N'1AD026TI-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (5, N'1AK063TR', N'1AK063TR-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (6, N'1AK063TR', N'1AK063TR-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (7, N'1AK063TR', N'1AK063TR-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (8, N'1AK063TR', N'1AK063TR-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (9, N'1VA01844TR', N'1VA01844TR-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (10, N'1VA01844TR', N'1VA01844TR-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (11, N'1VA01844TR', N'1VA01844TR-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (12, N'1VA01844TR', N'1VA01844TR-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (13, N'1VA02053XL', N'1VA02053XL-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (14, N'1VA02053XL', N'1VA02053XL-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (15, N'1VA02053XL', N'1VA02053XL-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (16, N'1VA02053XL', N'1VA02053XL-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (17, N'1VA02056BE', N'1VA02056BE-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (18, N'1VA02056BE', N'1VA02056BE-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (19, N'1VA02056BE', N'1VA02056BE-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (20, N'1VA02056BE', N'1VA02056BE-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (21, N'1VA02139TI', N'1VA02139TI-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (22, N'1VA02139TI', N'1VA02139TI-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (23, N'1VA02139TI', N'1VA02139TI-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (24, N'1VA02139TI', N'1VA02139TI-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (25, N'1VA02143HO', N'1VA02143HO-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (26, N'1VA02143HO', N'1VA02143HO-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (27, N'1VA02143HO', N'1VA02143HO-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (28, N'1VA02143HO', N'1VA02143HO-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (29, N'1VA02155HO', N'1VA02155HO-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (30, N'1VA02155HO', N'1VA02155HO-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (31, N'1VA02155HO', N'1VA02155HO-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (32, N'1VA02155HO', N'1VA02155HO-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (33, N'1VA2093DE', N'1VA2093DE-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (34, N'1VA2093DE', N'1VA2093DE-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (35, N'1VA2093DE', N'1VA2093DE-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (36, N'1VA2093DE', N'1VA2093DE-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (37, N'2VA01667TI', N'2VA01667TI-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (38, N'2VA01667TI', N'2VA01667TI-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (39, N'2VA01667TI', N'2VA01667TI-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (40, N'2VA01667TI', N'2VA01667TI-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (41, N'2VA159TI', N'2VA159TI-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (42, N'2VA159TI', N'2VA159TI-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (43, N'2VA159TI', N'2VA159TI-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (44, N'2VA159TI', N'2VA159TI-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (45, N'3VA01829DO', N'3VA01829DO-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (46, N'3VA01829DO', N'3VA01829DO-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (47, N'3VA01829DO', N'3VA01829DO-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (48, N'3VA01829DO', N'3VA01829DO-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (49, N'7VA1311HO', N'7VA1311HO-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (50, N'7VA1311HO', N'7VA1311HO-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (51, N'7VA1311HO', N'7VA1311HO-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (52, N'7VA1311HO', N'7VA1311HO-4.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (53, N'AD027HO', N'AD027HO-1.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (54, N'AD027HO', N'AD027HO-2.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (55, N'AD027HO', N'AD027HO-3.jpeg')
INSERT [dbo].[image] ([id], [product_id], [name]) VALUES (56, N'AD027HO', N'AD027HO-4.jpeg')
SET IDENTITY_INSERT [dbo].[image] OFF
GO
SET IDENTITY_INSERT [dbo].[order_detail] ON 

INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity]) VALUES (2, 1, N'AD027HO', 1)
INSERT [dbo].[order_detail] ([id], [order_id], [product_id], [quantity]) VALUES (3, 1, N'1VA01844TR', 2)
SET IDENTITY_INSERT [dbo].[order_detail] OFF
GO
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1AD026TI', N'Áo Dài Cẩm Nguyệt', 1250000.0000, N'Áo dài', N'001', 135, N'Vải organza, xuyên thấu, chất óng, có độ đơ, 2 lớp lót, phối vải trơn', N'Lep'', hoạ tiết thiết kế', N'Đầm cổ tàu, phối quần, lưng kín, tạo cảm giác sang trọng, quý phái', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1AK063TR', N'Áo Khoác Tơ Tay Dài', 450000.0000, N'Áo', N'001', 35, N'Chất tơ mềm, 1 lớp chính, phối cúc', N'Lep'', Hoạ tiết có sẵn', N'Áo khoác dáng cổ V, tay dài măng séc, tạo cảm giác tiểu thư, sang trọng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1VA01844TR', N'Váy Nhún 3 Tầng', 1050000.0000, N'Đầm', N'004', 100, N'Chất organza, xuyên thấu, có độ đơ, 2 lớp lót, không phối', N'Chất organza, xuyên thấu, có độ đơ, 2 lớp lót, không phối', N'Đầm hai dây kết hợp áo ren xuyên thấu, cổ V, tạo cảm giác thanh lịch, nhẹ nhàng', 2)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1VA02053XL', N'Váy 2 Dây Tùng Bèo', 895000.0000, N'Đầm', N'004', 110, N'Vải organza, mềm mại, bay bổng, 2 lớp lót, phối hoa', N'Premium, hoạ tiết thiết kế', N'Đầm hai dây, tùng xoè, hở lưng, tạo cảm giác thoải mái, nhẹ nhàng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1VA02056BE', N'Váy Cổ V Bèo Đổ', 1250000.0000, N'Đầm', N'003', 115, N'Chất tuýt si, sang trọng, tạo form tốt, 2 lớp lót, phối vải trơn', N'Lep'', Hoạ tiết có sẵn', N'Đầm cổ V, tùng xoè, hở lưng, tạo cảm giác tiểu thư, sang trọng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1VA02139TI', N'Váy Cổ Cô Tấm Tùng Bèo', 795000.0000, N'Đầm', N'003', 88, N'Vải organza, mềm mại, bay bổng, 2 lớp lót, phối hoa', N'Lep'', hoạ tiết thiết kế', N'Đầm cổ tròn cô tấm, nhún ngực, lưng kín, tạo cảm giác tiểu thư, sang trọng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1VA02143HO', N'Váy Bẹt Vai 3 Tầng', 1450000.0000, N'Đầm', N'004', 115, N'Chất tơ, mềm mại, bay bổng, 2 lớp lót, phối hoa', N'Premium, hoạ tiết thiết kế', N'Đầm bèo nhún, hở vai, hở lưng, tạo cảm giác tiểu thư, sang trọng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1VA02155HO', N'Váy Wrap Hoa Giấy', 795000.0000, N'Đầm', N'003', 115, N'Chất tơ sống, xuyên thấu, bay bổng, 2 lớp lót, không phối', N'Lep'', hoạ tiết thiết kế', N'Đầm wrap, tùng xoè, lưng kín, tạo cảm giác tiểu thư, sang trọng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'1VA2093DE', N'Váy 1 Dây Soắn Ngực', 750000.0000, N'Đầm', N'003', 110, N'Chất Organza, mềm mại, bay bổng, 2 lớp lót, phối vải trơn', N'Lep'', Hoạ tiết có sẵn', N'Lep'', Hoạ tiết có sẵn', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'2VA01667TI', N'Váy Ngực Nơ In Hoa Tím', 1150000.0000, N'Đầm', N'002', 115, N'Chất tơ sống mỏng, mềm mại', N'Premium', N'Váy ngực nơ mát, hoa xinh, dáng dễ mặc', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'2VA159TI', N'Váy Wrap Thắt Nơ Eo', 795000.0000, N'Đầm', N'002', 115, N'Chất tơ, mềm mại, bay bổng, 2 lớp lót, không phối', N'Lep'', hoạ tiết thiết kế', N'Đầm cổ V, dây nơ cổ, lưng kín, tạo cảm giác tiểu thư, sang trọng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'3VA01829DO', N'Váy Phối Áo Khoác Lửng', 750000.0000, N'Đầm', N'002', 105, N'Chất voan, mềm mại, phối ren', N'Lep'', phối vải ren thân trên', N'Đầm cổ V tùng xòe, tôn dáng dễ mặc, mang lại cảm giác sang trọng, quý phái
', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'7VA1311HO', N'Váy Quây Vai Tùng Xoè', 650000.0000, N'Đầm', N'002', 110, N'Chất tơ, mềm mại, bay bổng, 2 lớp lót, phối hoa', N'Lep''', N'Đầm trễ vai, nhún eo, tùng xòe, tạo cảm giác thanh lịch, nhẹ nhàng', 0)
INSERT [dbo].[product] ([id], [name], [price], [type], [collection_id], [length], [material], [line], [form], [total_sold]) VALUES (N'AD027HO', N'Áo Dài Hoa Tràn', 950000.0000, N'Áo dài', N'001', 135, N'Vải organza, xuyên thấu, chất óng, có độ đơ, 2 lớp lót, phối vải trơn', N'Lep'', hoạ tiết thiết kế', N'Đầm cổ tàu, phối tay màu, lưng kín, tạo cảm giác sang trọng, quý phái', 1)
GO
INSERT [dbo].[role] ([id], [role_name]) VALUES (N'CUS', N'Customer')
INSERT [dbo].[role] ([id], [role_name]) VALUES (N'MAN', N'Manager')
INSERT [dbo].[role] ([id], [role_name]) VALUES (N'STA', N'Staff')
GO
SET IDENTITY_INSERT [dbo].[shopping_order] ON 

INSERT [dbo].[shopping_order] ([id], [order_date], [account_id], [status], [payment]) VALUES (1, CAST(N'2023-10-18' AS Date), N'lttp', N'completed', N'COD')
SET IDENTITY_INSERT [dbo].[shopping_order] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__account__AB6E6164347C5E94]    Script Date: 30-Nov-23 6:41:04 PM ******/
ALTER TABLE [dbo].[account] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__authorit__24BB532E4B4B4AEF]    Script Date: 30-Nov-23 6:41:04 PM ******/
ALTER TABLE [dbo].[authority] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[collection] ADD  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[product] ADD  DEFAULT ((0)) FOR [total_sold]
GO
ALTER TABLE [dbo].[authority]  WITH CHECK ADD FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[authority]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[account] ([username])
GO
ALTER TABLE [dbo].[banner]  WITH CHECK ADD  CONSTRAINT [FK_banner_collection] FOREIGN KEY([collection_id])
REFERENCES [dbo].[collection] ([id])
GO
ALTER TABLE [dbo].[banner] CHECK CONSTRAINT [FK_banner_collection]
GO
ALTER TABLE [dbo].[image]  WITH CHECK ADD  CONSTRAINT [FK_image_product] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[image] CHECK CONSTRAINT [FK_image_product]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[shopping_order] ([id])
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK_product_collection] FOREIGN KEY([collection_id])
REFERENCES [dbo].[collection] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK_product_collection]
GO
ALTER TABLE [dbo].[shopping_order]  WITH CHECK ADD FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([username])
GO
USE [master]
GO
ALTER DATABASE [JAVA6_ELISESHOP] SET  READ_WRITE 
GO
