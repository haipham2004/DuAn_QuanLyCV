CREATE DATABASE BaiTech;
GO
USE BaiTech;
GO

-- Tạo bảng Role
--CREATE TABLE Role (
   --role_id INT PRIMARY KEY IDENTITY(1,1),
   -- role_name NVARCHAR(50) NOT NULL
--);

-- Bảng Account
CREATE TABLE Account (
    ma VARCHAR(50) PRIMARY KEY,
    passwords VARCHAR(200),
    enableds BIT
);

-- Bảng Roles với khóa chính mới
CREATE TABLE Roles (
    role_id INT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    UNIQUE(ma, authority),
    CONSTRAINT FK_RO_AC FOREIGN KEY (ma) REFERENCES Account(ma)
);

INSERT INTO  Account VALUES
('HaiPham1','{bcrypt}$2a$12$6eF5x.vDFF5ZJ5PSto1RreCCckFIKRtWPhTXfxezkEHbAK7Vu2KaG',1),
('HaiPham2','{bcrypt}$2a$12$4OD6G/0peAQniCtESBi8lOp3yDcjar1h68xS81GivPDEY6aAXr5JW',1),
('HaiPham3','{bcrypt}$2a$12$Kz9lCK7tyqKfOSPvyQemX.V98UP0ZBHpBftucdLsvOn/kWEQ9k/v6',1)
INSERT INTO  Roles VALUES
('HaiPham1','ROLE_ADMIN'),
('HaiPham2','ROLE_MANAGER'),
('HaiPham3','ROLE_MEMBER')

-- Tạo bảng PhongBan
CREATE TABLE PhongBan (
    phong_ban_id INT PRIMARY KEY IDENTITY(1,1),
    ma_phong_ban NVARCHAR(10) NOT NULL UNIQUE,
    ten_phong_ban NVARCHAR(100) NOT NULL
);

-- Tạo bảng ViTriCongViec
CREATE TABLE ViTriCongViec (
    vi_tri_id INT PRIMARY KEY IDENTITY(1,1),
    ma_vi_tri NVARCHAR(10) NOT NULL UNIQUE,
    ten_vi_tri NVARCHAR(100) NOT NULL,
    level NVARCHAR(20) NOT NULL CHECK (level IN ('Inter', 'Fresher', 'Junior', 'Mid', 'Senior', 'Leader', 'Manager', 'Director'))
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
    ma_account VARCHAR(50) NOT NULL, -- Nên sử dụng VARCHAR để khớp với bảng Account
	roles_id INT NOT NULL,
    FOREIGN KEY (vi_tri_id) REFERENCES ViTriCongViec(vi_tri_id),
    FOREIGN KEY (nhan_vien_quan_ly_id) REFERENCES NhanVien(nhan_vien_id),
    FOREIGN KEY (ma_account) REFERENCES Account(ma),
	FOREIGN KEY (roles_id) REFERENCES Roles(role_id),
);


-- Tạo bảng DotTuyenDung
CREATE TABLE DotTuyenDung (
    dot_tuyen_dung_id INT PRIMARY KEY IDENTITY(1,1),
    ma_dot_tuyen_dung NVARCHAR(10) NOT NULL UNIQUE,
    ten_dot_tuyen_dung NVARCHAR(100) NOT NULL,
    noi_dung NVARCHAR(MAX) NOT NULL,
    deadline DATE NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES NhanVien(nhan_vien_id)
);

-- Tạo bảng CV
CREATE TABLE CV (
    cv_id INT PRIMARY KEY IDENTITY(1,1),
    apply_datetime DATETIME NOT NULL,
    full_name NVARCHAR(100) NOT NULL,
    gender NVARCHAR(10) NOT NULL CHECK (gender IN ('Male', 'Female', 'Other')),
    email NVARCHAR(100) NOT NULL UNIQUE,
    tel NVARCHAR(15) NOT NULL,
    city NVARCHAR(100) NOT NULL,
    job_name NVARCHAR(100) NOT NULL,
    tong_nam_kinh_nghiem DECIMAL(5,2) NOT NULL,
    note NVARCHAR(MAX),
    link_cv NVARCHAR(255) NOT NULL,
    nguon_tuyen_dung NVARCHAR(20) NOT NULL CHECK (nguon_tuyen_dung IN ('Fb', 'Giới thiệu', 'TopCV', 'Linkedin', 'Khác')),
    trang_thai NVARCHAR(50) NOT NULL CHECK (trang_thai IN ('Chưa pvan', 'Pass pvan', 'Fail pvan', 'Đăng cân nhắc', 'Nghỉ việc', 'Bị thôi việc')),
    share BIT DEFAULT 0,
    hr_user_id INT,
    FOREIGN KEY (hr_user_id) REFERENCES NhanVien(nhan_vien_id)
);

-- Tạo bảng Activity
CREATE TABLE Activity (
    activity_id INT PRIMARY KEY IDENTITY(1,1),
    activity_type NVARCHAR(20) NOT NULL CHECK (activity_type IN ('Gọi điện', 'Meeting', 'Chat Zalo')),
    activity_note NVARCHAR(MAX),
    user_id INT,
    share BIT DEFAULT 0,
    create_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES NhanVien(nhan_vien_id)
);




-- Thêm dữ liệu mẫu vào bảng PhongBan
INSERT INTO PhongBan (ma_phong_ban, ten_phong_ban) VALUES 
('PB001', 'Phòng Nhân Sự'), 
('PB002', 'Phòng Kỹ Thuật');

-- Thêm dữ liệu mẫu vào bảng ViTriCongViec
INSERT INTO ViTriCongViec (ma_vi_tri, ten_vi_tri, level) VALUES 
('VT001', 'Nhân viên Kỹ thuật', 'Junior'), 
('VT002', 'Trưởng phòng Nhân sự', 'Manager');

-- Thêm dữ liệu mẫu vào bảng NhanVien
INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, email, sdt, vi_tri_id, nhan_vien_quan_ly_id, ma_account, roles_id) VALUES 
('NV001', N'Phạm Ngọc Hải', 'a.nguyen@company.com', '0900000001', 1, 1, 'HaiPham1', 1), 
('NV002', N'Nguyễn Thị Thuý Hằng', 'b.tran@company.com', '0900000002', 2, 1, 'HaiPham2', 2), 
('NV003', N'Nguyễn Thảo Linh', 'c.le@company.com', '0900000003', 1, 1, 'HaiPham2', 3), 
('NV004', N'Phạm Thị D', 'd.pham@company.com', '0900000004', 2, 2, 'HaiPham3', 3), 
('NV005', N'Hoàng Văn E', 'e.hoang@company.com', '0900000005', 2, 2, 'HaiPham3', 3);

-- Thêm dữ liệu mẫu vào bảng DotTuyenDung
INSERT INTO DotTuyenDung (ma_dot_tuyen_dung, ten_dot_tuyen_dung, noi_dung, deadline, user_id) VALUES 
('DTD001', 'Đợt tuyển dụng 1', 'Tuyển dụng nhân viên kỹ thuật', '2024-12-31', 1), 
('DTD002', 'Đợt tuyển dụng 2', 'Tuyển dụng nhân viên marketing', '2024-11-30', 2), 
('DTD003', 'Đợt tuyển dụng 3', 'Tuyển dụng nhân viên kinh doanh', '2024-10-31', 3), 
('DTD004', 'Đợt tuyển dụng 4', 'Tuyển dụng nhân viên hỗ trợ khách hàng', '2024-09-30', 4), 
('DTD005', 'Đợt tuyển dụng 5', 'Tuyển dụng nhân viên phát triển sản phẩm', '2024-08-31', 5);

-- Thêm dữ liệu mẫu vào bảng CV
INSERT INTO CV (apply_datetime, full_name, gender, email, tel, city, job_name, tong_nam_kinh_nghiem, note, link_cv, nguon_tuyen_dung, trang_thai, share, hr_user_id) VALUES 
('2024-08-01 10:00:00', 'Nguyễn Văn X', 'Male', 'x.nguyen@jobseek.com', '0911111111', 'Hà Nội', 'Nhân viên Kỹ thuật', 2.5, 'Kinh nghiệm làm việc 2.5 năm', 'link_cv_1.pdf', 'TopCV', 'Chưa pvan', 0, 1),
('2024-08-02 11:00:00', 'Trần Thị Y', 'Female', 'y.tran@jobseek.com', '0922222222', 'TP. Hồ Chí Minh', 'Nhân viên Marketing', 1.0, 'Kinh nghiệm làm việc 1 năm', 'link_cv_2.pdf', 'Linkedin', 'Pass pvan', 1, 2),
('2024-08-03 12:00:00', 'Lê Văn Z', 'Male', 'z.le@jobseek.com', '0933333333', 'Đà Nẵng', 'Nhân viên Kinh doanh', 3.0, 'Kinh nghiệm làm việc 3 năm', 'link_cv_3.pdf', 'Fb', 'Fail pvan', 0, 3),
('2024-08-04 13:00:00', 'Phạm Thị W', 'Female', 'w.pham@jobseek.com', '0944444444', 'Hải Phòng', 'Nhân viên Hỗ trợ khách hàng', 1.5, 'Kinh nghiệm làm việc 1.5 năm', 'link_cv_4.pdf', 'Giới thiệu', 'Đăng cân nhắc', 1, 4),
('2024-08-05 14:00:00', 'Hoàng Văn Q', 'Male', 'q.hoang@jobseek.com', '0955555555', 'Cần Thơ', 'Nhân viên Phát triển sản phẩm', 4.0, 'Kinh nghiệm làm việc 4 năm', 'link_cv_5.pdf', 'Khác', 'Nghỉ việc', 0, 5);

-- Thêm dữ liệu mẫu vào bảng Activity
INSERT INTO Activity (activity_type, activity_note, user_id, share, create_date) VALUES 
('Gọi điện', 'Gọi điện tư vấn công việc cho ứng viên X', 1, 0, '2024-08-01 15:00:00'),
('Meeting', 'Họp trao đổi về đợt tuyển dụng 1', 2, 1, '2024-08-02 09:00:00'),
('Chat Zalo', 'Trao đổi công việc qua Zalo với ứng viên Y', 3, 0, '2024-08-03 16:00:00'),
('Gọi điện', 'Gọi điện nhắc lịch phỏng vấn cho ứng viên Z', 4, 1, '2024-08-04 14:00:00'),
('Meeting', 'Họp tổng kết đợt tuyển dụng 2', 5, 0, '2024-08-05 10:00:00');

select*from NhanVien

select*from Activity

SELECT*FROM NhanVien