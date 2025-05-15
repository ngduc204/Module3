CREATE DATABASE RentalManagement;
USE RentalManagement;

CREATE TABLE MatBang (
    maMatBang VARCHAR(50) PRIMARY KEY,
    dienTich DOUBLE NOT NULL,
    tang INT NOT NULL,
    loaiMatBang VARCHAR(50) NOT NULL,
    giaTien DOUBLE NOT NULL,
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL
);

INSERT INTO MatBang (maMatBang, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc) 
VALUES 
('MB001', 100, 2, 'Văn phòng chia sẻ', 2000000, '2020-11-20', '2021-11-25'),
('MB002', 240, 5, 'Văn phòng trọn gói', 5000000, '2020-04-03', '2020-10-03');

SELECT * FROM MatBang;