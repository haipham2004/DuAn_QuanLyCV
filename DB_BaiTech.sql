-- Tạo cơ sở dữ liệu mới
CREATE DATABASE BaiTech;
GO
USe BaiTech
GO 
-- Tạo bảng Account
CREATE TABLE Account (
    ma VARCHAR(50) PRIMARY KEY,
    passwords VARCHAR(200),
    enableds BIT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ,
    deleted_at BIT DEFAULT 0
);

-- Tạo bảng Roles
CREATE TABLE Roles (
    role_id INT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    UNIQUE(ma, authority),
    CONSTRAINT FK_RO_AC FOREIGN KEY (ma) REFERENCES Account(ma),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted_at BIT DEFAULT 0
);

-- Tạo bảng PhongBan
CREATE TABLE PhongBan (
    phong_ban_id INT PRIMARY KEY IDENTITY(1,1),
    ma_phong_ban NVARCHAR(10) NOT NULL UNIQUE,
    ten_phong_ban NVARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted_at BIT DEFAULT 0
);

-- Tạo bảng ViTriCongViec
CREATE TABLE ViTriCongViec (
    vi_tri_id INT PRIMARY KEY IDENTITY(1,1),
    ma_vi_tri NVARCHAR(10) NOT NULL UNIQUE,
    ten_vi_tri NVARCHAR(100) NOT NULL,
    level NVARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted_at BIT DEFAULT 0
);

-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    nhan_vien_id INT PRIMARY KEY IDENTITY(1,1),
    ma_nhan_vien NVARCHAR(10) NOT NULL UNIQUE,
    ten_nhan_vien NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    sdt NVARCHAR(15) NOT NULL,
    vi_tri_id INT,
    nhan_vien_quan_ly_id INT,
    ma_account VARCHAR(50) NOT NULL,
    roles_id INT NOT NULL,
    FOREIGN KEY (vi_tri_id) REFERENCES ViTriCongViec(vi_tri_id),
    FOREIGN KEY (nhan_vien_quan_ly_id) REFERENCES NhanVien(nhan_vien_id),
    FOREIGN KEY (ma_account) REFERENCES Account(ma),
    FOREIGN KEY (roles_id) REFERENCES Roles(role_id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ,
    deleted_at BIT DEFAULT 0
);

-- Tạo bảng DotTuyenDung
CREATE TABLE DotTuyenDung (
    dot_tuyen_dung_id INT PRIMARY KEY IDENTITY(1,1),
    ma_dot_tuyen_dung NVARCHAR(10) NOT NULL UNIQUE,
    ten_dot_tuyen_dung NVARCHAR(100) NOT NULL,
    noi_dung NVARCHAR(MAX) NOT NULL,
    deadline DATE NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES NhanVien(nhan_vien_id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ,
    deleted_at BIT DEFAULT 0
);

-- Tạo bảng CV
CREATE TABLE CV (
    cv_id INT PRIMARY KEY IDENTITY(1,1),
	maHoSo VARCHAR(20),
    apply_datetime DATE  NULL,
    full_name NVARCHAR(100)  NULL,
    gender NVARCHAR(10)  NULL,
    email NVARCHAR(100)  NULL UNIQUE,
    tel NVARCHAR(15)  NULL,
    city NVARCHAR(100)  NULL,
    job_name NVARCHAR(100)  NULL,
    tong_nam_kinh_nghiem DECIMAL(5,2) NOT NULL,
    note NVARCHAR(MAX),
    link_cv NVARCHAR(255) NOT NULL,
    nguon_tuyen_dung NVARCHAR(20) ,
    trang_thai NVARCHAR(50)  NULL ,
    share BIT DEFAULT 0,
    hr_user_id INT,
    FOREIGN KEY (hr_user_id) REFERENCES NhanVien(nhan_vien_id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ,
    deleted_at BIT DEFAULT 0
);

-- Tạo bảng Activity
CREATE TABLE Activity (
    activity_id INT PRIMARY KEY IDENTITY(1,1),
    activity_type NVARCHAR(20) NOT NULL CHECK (activity_type IN ('Gọi điện', 'Meeting', 'Chat Zalo')),
    activity_note NVARCHAR(255),
    user_id INT,
    share BIT DEFAULT 0,
    create_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES NhanVien(nhan_vien_id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ,
    deleted_at BIT DEFAULT 0
);



-- Thêm dữ liệu mẫu vào bảng Account
INSERT INTO Account (ma, passwords, enableds) VALUES
('HaiPham1', '{bcrypt}$2a$12$6eF5x.vDFF5ZJ5PSto1RreCCckFIKRtWPhTXfxezkEHbAK7Vu2KaG', 1),
('HaiPham2', '{bcrypt}$2a$12$4OD6G/0peAQniCtESBi8lOp3yDcjar1h68xS81GivPDEY6aAXr5JW', 1),
('HaiPham3', '{bcrypt}$2a$12$Kz9lCK7tyqKfOSPvyQemX.V98UP0ZBHpBftucdLsvOn/kWEQ9k/v6', 1);

-- Thêm dữ liệu mẫu vào bảng Roles
INSERT INTO Roles (ma, authority) VALUES
('HaiPham1', 'ROLE_ADMIN'),
('HaiPham2', 'ROLE_MANAGER'),
('HaiPham3', 'ROLE_MEMBER');

-- Thêm dữ liệu mẫu vào bảng PhongBan
INSERT INTO PhongBan (ma_phong_ban, ten_phong_ban) VALUES 
('PB001', N'Phòng Nhân Sự'),
('PB002', N'Phòng Kỹ Thuật'),
('PB003', N'Phòng Tài Chính'),
('PB004', N'Phòng Marketing'),
('PB005', N'Phòng Bán Hàng'),
('PB006', N'Phòng Hỗ Trợ Kỹ Thuật'),
('PB007', N'Phòng Sản Xuất'),
('PB008', N'Phòng Nghiên Cứu & Phát Triển'),
('PB009', N'Phòng Pháp Lý'),
('PB010', N'Phòng Quản Trị'),
('PB011', N'Phòng Đào Tạo'),
('PB012', N'Phòng Dịch Vụ Khách Hàng');


-- Thêm dữ liệu mẫu vào bảng ViTriCongViec
INSERT INTO ViTriCongViec (ma_vi_tri, ten_vi_tri, level) VALUES 
('VT001', N'Nhân viên Kỹ thuật', 'Junior'),
('VT002', N'Trưởng phòng Nhân sự', 'Manager'),
('VT003', N'Nhân viên Marketing', 'Senior'),
('VT004', N'Trưởng phòng Bán hàng', 'Manager'),
('VT005', N'Nhân viên Hỗ trợ kỹ thuật', 'Mid'),
('VT006', N'Giám đốc Sản xuất', 'Director'),
('VT007', N'Nhân viên Nghiên cứu & Phát triển', 'Senior'),
('VT008', N'Nhân viên Pháp lý', 'Junior'),
('VT009', N'Nhân viên Quản trị', 'Mid'),
('VT010', N'Nhân viên Đào tạo', 'Junior'),
('VT011', N'Chuyên viên Dịch vụ Khách hàng', 'Senior'),
('VT012', N'Giám đốc Tài chính', 'Director');

-- Thêm dữ liệu mẫu vào bảng NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, email, sdt, vi_tri_id, nhan_vien_quan_ly_id, ma_account, roles_id) VALUES 
('NV001', N'Phạm Ngọc Hải', 'a.nguyen@company.com', '0900000001', 1, 1, 'HaiPham1', 1), 
('NV002', N'Nguyễn Thị Thuý Hằng', 'b.tran@company.com', '0900000002', 2, 1, 'HaiPham2', 2), 
('NV003', N'Nguyễn Thảo Linh', 'c.le@company.com', '0900000003', 1, 1, 'HaiPham2', 3), 
('NV004', N'Phạm Thị D', 'd.pham@company.com', '0900000004', 2, 2, 'HaiPham3', 3), 
('NV005', N'Hoàng Văn E', 'e.hoang@company.com', '0900000005', 2, 2, 'HaiPham3', 3),
('NV006', N'Phạm Thị T', 'z.pham@company.com', '0900000004', 2, 2, 'HaiPham3', 3), 
('NV007', N'Hoàng Văn Z', 'k.hoang@company.com', '0900000005', 2, 2, 'HaiPham3', 3);

-- Thêm dữ liệu mẫu vào bảng DotTuyenDung
INSERT INTO DotTuyenDung (ma_dot_tuyen_dung, ten_dot_tuyen_dung, noi_dung, deadline, user_id) 
VALUES 
('DTD001', N'Đợt tuyển dụng tháng 1', N'Chi tiết tuyển dụng tháng 1', GETDATE(), 1), 
('DTD002', N'Đợt tuyển dụng tháng 2', N'Chi tiết tuyển dụng tháng 2', GETDATE(), 2), 
('DTD003', N'Đợt tuyển dụng tháng 3', N'Chi tiết tuyển dụng tháng 3',GETDATE(), 3), 
('DTD004', N'Đợt tuyển dụng tháng 4', N'Chi tiết tuyển dụng tháng 4', GETDATE(), 4), 
('DTD005', N'Đợt tuyển dụng tháng 5', N'Chi tiết tuyển dụng tháng 5', GETDATE(), 5), 
('DTD006', N'Đợt tuyển dụng tháng 6', N'Chi tiết tuyển dụng tháng 6', GETDATE(), 6), 
('DTD007', N'Đợt tuyển dụng tháng 7', N'Chi tiết tuyển dụng tháng 7', GETDATE(), 1), 
('DTD008', N'Đợt tuyển dụng tháng 8', N'Chi tiết tuyển dụng tháng 8', GETDATE(), 2), 
('DTD009', N'Đợt tuyển dụng tháng 9', N'Chi tiết tuyển dụng tháng 9', GETDATE(), 3), 
('DTD010', N'Đợt tuyển dụng tháng 10', N'Chi tiết tuyển dụng tháng 10', GETDATE(), 4), 
('DTD011', N'Đợt tuyển dụng tháng 11', N'Chi tiết tuyển dụng tháng 11', GETDATE(), 5), 
('DTD012', N'Đợt tuyển dụng tháng 12', N'Chi tiết tuyển dụng tháng 12', GETDATE(), 6);


-- Thêm dữ liệu mẫu vào bảng CV
INSERT INTO CV (maHoSo, apply_datetime, full_name, gender, email, tel, city, job_name, tong_nam_kinh_nghiem, note, link_cv, nguon_tuyen_dung, trang_thai, share, hr_user_id)
VALUES
('HS001', '2024-09-01', 'Nguyễn Văn A', 'Nam', 'nguyenvana@example.com', '0912345678', 'Hà Nội', 'Lập trình viên', 3.5, 'Có kinh nghiệm về Java', 'http://example.com/cv_nguyenvana', 'LinkedIn', 'Đậu Phỏng Vấn', 1, 1),
('HS002', '2024-09-02', 'Trần Thị B', 'Nữ', 'tranthib@example.com', '0987654321', 'Hồ Chí Minh', 'Kiểm thử', 2.0, 'Tốt nghiệp ngành CNTT', 'http://example.com/cv_tranthib', 'Referral', 'Chưa Phỏng', 0, 2),
('HS003', '2024-09-03', 'Lê Văn C', 'Nam', 'levanc@example.com', '0901234567', 'Đà Nẵng', 'Quản lý dự án', 7.0, 'Quản lý dự án phần mềm', 'http://example.com/cv_levanc', 'Facebook', 'Rớt Phỏng Vấn', 1, 3),
('HS004', '2024-09-04', 'Phạm Thị D', 'Nữ', 'phamthid@example.com', '0934567890', 'Cần Thơ', 'BA', 5.0, 'Kinh nghiệm BA 5 năm', 'http://example.com/cv_phamthid', 'TopCV', 'Cân Nhắc Sau', 0, 4),
('HS005', '2024-09-05', 'Đỗ Văn E', 'Nam', 'dovane@example.com', '0923456789', 'Hải Phòng', 'Nhà thiết kế', 4.0, 'Thiết kế đồ họa', 'http://example.com/cv_dovane', 'LinkedIn', 'Nghỉ Việc', 0, 5),
('HS006', '2024-09-06', 'Hoàng Thị F', 'Nữ', 'hoangthif@example.com', '0945678912', 'Quảng Ninh', 'Marketing', 1.5, 'Kinh nghiệm marketing', 'http://example.com/cv_hoangthif', 'Referral', 'Bị Thôi Việc', 1, 6),
('HS007', '2024-09-07', 'Võ Văn G', 'Nam', 'vovang@example.com', '0956789123', 'Huế', 'QA', 6.0, 'Kiểm thử phần mềm', 'http://example.com/cv_vovang', 'LinkedIn', 'Đậu Phỏng Vấn', 1, 7),
('HS008', '2024-09-08', 'Vũ Thị H', 'Nữ', 'vuthih@example.com', '0967891234', 'Đà Nẵng', 'HR', 4.5, 'Quản lý nhân sự', 'http://example.com/cv_vuthih', 'TopCV', 'Rớt Phỏng Vấn', 0, 1),
('HS009', '2024-09-09', 'Nguyễn Văn I', 'Nam', 'nguyenvani@example.com', '0978912345', 'Hà Nội', 'Lập trình viên', 2.0, 'Lập trình C#', 'http://example.com/cv_nguyenvani', 'LinkedIn', 'Cân Nhắc Sau', 1, 2),
('HS010', '2024-09-10', 'Trần Thị J', 'Nữ', 'tranthij@example.com', '0989123456', 'TP HCM', 'Kiểm thử', 3.0, 'Kiểm thử tự động', 'http://example.com/cv_tranthij', 'Referral', 'Chưa Phỏng', 0, 3),
('HS011', '2024-09-11', 'Lê Văn K', 'Nam', 'levank@example.com', '0991234567', 'Huế', 'BA', 2.5, 'Tư vấn nghiệp vụ', 'http://example.com/cv_levank', 'Facebook', 'Đậu Phỏng Vấn', 1, 4),
('HS012', '2024-09-12', 'Phạm Thị L', 'Nữ', 'phamthil@example.com', '0909876543', 'Hà Nội', 'Marketing', 3.0, 'Digital marketing', 'http://example.com/cv_phamthil', 'LinkedIn', 'Nghỉ Việc', 1, 5);






-- Thêm dữ liệu mẫu vào bảng Activity
INSERT INTO Activity (activity_type, activity_note, user_id, share, create_date) VALUES 
('Gọi điện', 'Gọi điện cho ứng viên Nguyễn Văn A', 1, 1, '2024-09-01 10:00:00'), 
('Meeting', 'Họp phỏng vấn ứng viên Nguyễn Thị B', 2, 0, '2024-09-02 14:00:00');


SELECT*FROM NhanVien
SELECT*FROM PhongBan
SELECT*FROM ViTriCongViec
SELECT*FROM DotTuyenDung
SELECT*FROM CV
UPDATE NhanVien set deleted_at=0