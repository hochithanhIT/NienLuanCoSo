CREATE DATABASE tourism_website;

-- Table `tourism_website`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`admin` (
 `AD_UserAdmin` VARCHAR(8) NOT NULL,
 `AD_Password` VARCHAR(45) NOT NULL,
 `AD_State` INT NOT NULL,
 PRIMARY KEY (`AD_UserAdmin`));


-- -----------------------------------------------------
-- Table `tourism_website`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`booking` (
 `BOOKING_ID` VARCHAR(8) NOT NULL,
 PRIMARY KEY (`BOOKING_ID`));


-- -----------------------------------------------------
-- Table `tourism_website`.`destination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`destination` (
 `DES_ID` INT NOT NULL,
 `DES_Name` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`DES_ID`));


-- -----------------------------------------------------
-- Table `tourism_website`.`tour_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`tour_type` (
 `TT_ID` INT NOT NULL,
 `TT_TypeName` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`TT_ID`));



-- -----------------------------------------------------
-- Table `tourism_website`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`tour` (
 `TOUR_ID` VARCHAR(8) NOT NULL,
 `TOUR_Title` VARCHAR(200) NOT NULL,
 `TOUR_Description` VARCHAR(500) NOT NULL,
 `TOUR_Day` VARCHAR(45) NOT NULL, 
 `TOUR_Slot` INT NOT NULL, 
 `TOUR_DepartureDay` DATE NOT NULL,
 `TOUR_BackDay` DATE NOT NULL, 
 `TT_ID` INT NOT NULL,
 `DES_ID` INT NOT NULL,
 PRIMARY KEY (`TOUR_ID`),
 CONSTRAINT `FK_DES`
  FOREIGN KEY (`DES_ID`)
  REFERENCES `tourism_website`.`destination` (`DES_ID`),
 CONSTRAINT `FK_TT`
  FOREIGN KEY (`TT_ID`)
  REFERENCES `tourism_website`.`tour_type` (`TT_ID`));


-- -----------------------------------------------------
-- Table `tourism_website`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`user` (
 `US_ID` VARCHAR(8) NOT NULL,
 `US_Name` VARCHAR(45) NOT NULL,
 `US_Password` VARCHAR(45) NOT NULL,
 `US_FullName` VARCHAR(45) NOT NULL,
 `US_Gender` INT NOT NULL,
 `US_Email` VARCHAR(100) NOT NULL,
 `US_PhoneNumber` VARCHAR(10) NOT NULL,
 `US_IDCardNumber` VARCHAR(12) NOT NULL,
 `US_Address` VARCHAR(100) NOT NULL,
 PRIMARY KEY (`US_ID`));


-- -----------------------------------------------------
-- Table `tourism_website`.`booking_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`booking_detail` (
 `B_ID` VARCHAR(8) NOT NULL,
 `TOUR_ID` VARCHAR(8) NOT NULL,
 `US_ID` VARCHAR(8) NOT NULL,
 `BD_Time` DATETIME NOT NULL, 
 `BD_Quantity` INT NOT NULL, 
 `BD_TotalMoney` DECIMAL(10,0) NOT NULL, 
 PRIMARY KEY (`B_ID`, `TOUR_ID`),
 CONSTRAINT `FK_BOOKING`
  FOREIGN KEY (`B_ID`)
  REFERENCES `tourism_website`.`booking` (`BOOKING_ID`),
 CONSTRAINT `FK_TOUR_BOOKING`
  FOREIGN KEY (`TOUR_ID`)
  REFERENCES `tourism_website`.`tour` (`TOUR_ID`),
 CONSTRAINT `FK_USER`
  FOREIGN KEY (`US_ID`)
  REFERENCES `tourism_website`.`user` (`US_ID`));


-- -----------------------------------------------------
-- Table `tourism_website`.`effective_day`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`effective_day` (
 `ED_EffectiveDay` DATE NOT NULL, 
 PRIMARY KEY (`ED_EffectiveDay`));


-- -----------------------------------------------------
-- Table `tourism_website`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`images` (
 `IM_ID` INT NOT NULL,
 `IM_Url` VARCHAR(200) NOT NULL, 
 `TOUR_ID` VARCHAR(8) NOT NULL,
 PRIMARY KEY (`IM_ID`),
 INDEX `FK_TOUR` (`TOUR_ID` ASC) VISIBLE,
 CONSTRAINT `FK_TOUR`
  FOREIGN KEY (`TOUR_ID`)
  REFERENCES `tourism_website`.`tour` (`TOUR_ID`));


-- -----------------------------------------------------
-- Table `tourism_website`.`unit_price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`unit_price` (
 `TOUR_ID` VARCHAR(8) NOT NULL,
 `ED_EffectiveDay` DATE NOT NULL,
 `UP_UnitPrice` DECIMAL(10,0) NOT NULL, 
 PRIMARY KEY (`TOUR_ID`, `ED_EffectiveDay`),
 INDEX `FK_EffectiveDay` (`ED_EffectiveDay` ASC) VISIBLE,
 CONSTRAINT `FK_EffectiveDay`
  FOREIGN KEY (`ED_EffectiveDay`)
  REFERENCES `tourism_website`.`effective_day` (`ED_EffectiveDay`),
 CONSTRAINT `FK_Tour_Price`
  FOREIGN KEY (`TOUR_ID`)
  REFERENCES `tourism_website`.`tour` (`TOUR_ID`));
  

-- -----------------------------------------------------
-- Insert data into `tourism_website`.`admin`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`admin` VALUES
('admin', 'admin', 1);


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`booking`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`booking` VALUES
('B0000001'),
('B0000002');


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`destination`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`destination` VALUES
(40, 'Nha Trang - Khánh Hòa'),
(51, 'Bà Rịa - Vũng Tàu');


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`effective_day`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`effective_day` VALUES
('2024-04-23');


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`tour_type`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`tour_type` VALUES
(1, 'Tour cao cấp'),
(2, 'Tour tiêu chuẩn'),
(3, 'Tour tiết kiệm');


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`tour`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`tour` VALUES
('T0000001', 'Du lịch nghỉ dưỡng với tour du lịch Nha Trang tới Hòn Tằm',
'Tham gia tour du lịch tới Hòn Tằm hay còn gọi là đảo Thủy Kim Sơn, du khách sẽ được chiêm ngưỡng vẻ đẹp của khu du lịch sinh thái nổi tiếng nằm cách thành phố Nha Trang 7km về phía Đông Nam. Nơi đây rất thích hợp cho những chuyến du lịch nghỉ dưỡng bởi nét thuần khiết chưa bị du lịch hóa và đặc thù khí hậu quanh năm ôn hòa nơi đây.
Hòn Tằm rộng hơn 110ha với bờ biển dài 1km, du khách tới đây có thể nằm dài trên bãi cát trắng mịn, đắm mình vào làn nước biển xanh, ngắm nhìn những chòi lá xen kẽ với núi rừng rợp bóng cây. Chính cảnh quanh cát trắng - nắng vàng tạo điều kiện cho các khu resort hiện đại và sang trọng mọc lên, đồng thời những trò chơi thể thao như chèo thuyền kayak, đua motor nước, dù kéo cũng phát triển mạnh mẽ.
Lưu ý, các tour trong ngày tới Hòn Tằm đều chỉ đến chiều (khoảng 16h) là kết thúc, nếu muốn tận hưởng cảm giác nghỉ dưỡng tại đây nhiều hơn thì có thể thuê lều du lịch (ở đảo có dịch vụ cho thuê) và cắm trại qua đêm trên bãi biển.',
'2 ngày 1 đêm', 15, '2024-05-01', '2024-05-02', 2, 40),
('T0000002', 'Tour du lịch Hồ Tràm 2 ngày 1 đêm – The Grand Hồ Tràm Strip 5 sao',
'Một điểm du lịch nghỉ dưỡng chỉ cách trung tâm Thành phố Hồ Chí Minh chỉ 120km, nơi những bãi tắm còn hoang sơ với cát trắng biển xanh nắng vàng tại khu du lịch Hồ Tràm. Có thể nói khu du lịch Hồ Tràm được mẹ thiên nhiên ưu đãi ban tặng một vẻ đẹp hoang sơ, một thiên đường bí mật giữa thành phố biển Vũng Tàu nhộn nhịp. 
Hồ Tràm từng được kênh truyền hình CNN (Mỹ) bình chọn là một trong những bãi biển được thiên nhiên ưu ái với vẻ đẹp hoang sơ nhất trên thế giới, 
Nổi tiếng với hàng cây xanh trải dài, bãi cát trắng, biển xanh xa xa là những chiếc thuyền thúng và hình ảnh những người ngư dân lao động hăng say cùng với đó là sự hoàn thiện về cơ sở hạ tầng nhằm phục vụ chu đáo cho du khách khi tới khu du lịch Hồ Tràm.', 
'3 ngày 2 đêm', 10, '2024-04-30', '2024-05-2', 1, 51);


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`admin`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`unit_price` VALUES
('T0000001', '2024-04-23', 1650000),
('T0000002', '2024-04-23', 2890000);


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`user`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`user` VALUES
('user3', 'nguyen_van_a', 'password789', 'Nguyễn Văn A', 1, 'nguyenvana@example.com', '0987654321', '123456789012', '123 Đường Ấp, Thành phố'),
('user4', 'le_thi_b', 'passwordabc', 'Lê Thị B', 2, 'lethib@example.com', '0123456789', '987654321098', '456 Đường Xóm, Thị trấn');


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`images`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`images` VALUES
(1, 'https://reviewvilla.vn/wp-content/uploads/2022/04/hon-tam-nha-trang-2.jpg', 'T0000001'),
(2, 'https://bazantravel.com/cdn/medias/uploads/59/59459-ho-tram-6-700x700.jpg', 'T0000002');


-- -----------------------------------------------------
-- Insert data into `tourism_website`.`booking_detail`
-- -----------------------------------------------------
INSERT INTO `tourism_website`.`booking_detail` VALUES
('B0000001', 'T0000001', 'user3', '2024-03-24 7:05:32', 3, 4950000, 0),
('B0000002', 'T0000002', 'user4', '2024-03-24 7:07:24', 4, 11560000, 1);
