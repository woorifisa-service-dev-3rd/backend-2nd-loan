SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema LOAN
-- -----------------------------------------------------
DROP DATABASE LOAN;
CREATE SCHEMA IF NOT EXISTS `LOAN` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `LOAN`;
-- -----------------------------------------------------
-- Table `LOAN`.`ApplicationMethod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`application_method`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `LOAN`.`LoanProductsType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`loan_products_type`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Table `LOAN`.`LoanProductsFeatures`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`loan_products_features`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `LOAN`.`provider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`provider`
(
    `id`        BIGINT      NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(45) NOT NULL,
    `is_active` TINYINT     NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Table `LOAN`.`LoanProducts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`loan_products`
(
    `id`                       BIGINT  NOT NULL AUTO_INCREMENT,
    `start_date`               DATE    NOT NULL,
    `end_date`                 DATE    NOT NULL DEFAULT '9999-12-31',
    `interest_rate`            DECIMAL(5,4) NOT NULL,
    `max_limit`                BIGINT  NOT NULL,
    `repayment_period`         INT     NOT NULL,
    `required_credit_score`    INT     NOT NULL,
    `type_id`                  BIGINT  NOT NULL,
    `application_method_id`    BIGINT  NOT NULL,
    `loan_products_feature_id` BIGINT  NOT NULL,
    `provider_id`              BIGINT  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_LoanProducts_ApplicationMethod_idx` (`application_method_id` ASC) VISIBLE,
    INDEX `fk_LoanProducts_LoanProductsFeatures_idx` (`loan_products_feature_id` ASC) VISIBLE,
    CONSTRAINT `fk_LoanProducts_ApplicationMethod`
        FOREIGN KEY (`application_method_id`)
            REFERENCES `LOAN`.`application_method` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_LoanProducts_LoanProductsType`
        FOREIGN KEY (`type_id`)
            REFERENCES `LOAN`.`loan_products_type` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_LoanProducts_LoanProductsFeatures`
        FOREIGN KEY (`loan_products_feature_id`)
            REFERENCES `LOAN`.`loan_products_features` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_LoanProducts_provider`
        FOREIGN KEY (`provider_id`)
            REFERENCES `LOAN`.`provider` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `LOAN`.`Member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`member`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(45) NOT NULL,
    `email`           VARCHAR(45) NOT NULL,
    `phone_number`    VARCHAR(45) NOT NULL,
    `address`         VARCHAR(45) NOT NULL,
    `registered_date` DATE        NOT NULL,
    `credit_score`    INT         NOT NULL,
    `is_active`       TINYINT     NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `LOAN`.`MemberLoanProducts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`member_loan_products`
(
    `id`                 BIGINT NOT NULL AUTO_INCREMENT,
    `member_id`          BIGINT NOT NULL,
    `loan_products_id`   BIGINT NOT NULL,
    `start_date`         DATE   NOT NULL,
    `end_date`           DATE   NOT NULL DEFAULT '9999-12-31',
    `loan_amount`        BIGINT NOT NULL,
    `loan_due_date`      DATE   NOT NULL,
    `repayment_count`    INT    NOT NULL,
    `late_payment_count` INT    NOT NULL,
    -- 매월 상환해야 할 금액
    `goal_amount`        BIGINT NOT NULL,
    -- 여태 상환한 총금액
    `total_paid_amount`  BIGINT NOT NULL default 0,
    -- 목표 상환 금액 (대출금+이자)
    `total_repayment_amount`   BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Member_has_LoanProducts_LoanProducts1_idx` (`loan_products_id` ASC) VISIBLE,
    INDEX `fk_Member_has_LoanProducts_Member_idx` (`member_id` ASC) VISIBLE,
    CONSTRAINT `fk_Member_has_LoanProducts_LoanProducts`
        FOREIGN KEY (`loan_products_id`)
            REFERENCES `LOAN`.`loan_products` (`id`),
    CONSTRAINT `fk_Member_has_LoanProducts_Member`
        FOREIGN KEY (`member_id`)
            REFERENCES `LOAN`.`member` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
-- Member 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`member` (`name`, `email`, `phone_number`, `address`, `registered_date`, `credit_score`, `is_active`)
VALUES ('김수민', 'minsu.kim@example.com', '010-1234-5678', '서울특별시 강남구 삼성로 123', '2023-01-15', 750, 1),
       ('이영희', 'younghee.lee@example.com', '010-2345-6789', '부산광역시 해운대구 해운대해변로 456', '2023-02-10', 680, 1),
       ('박지훈', 'jihoon.park@example.com', '010-3456-7890', '대구광역시 수성구 범어동 789', '2023-03-05', 720, 0),
       ('최예린', 'yerin.choi@example.com', '010-4567-8901', '인천광역시 연수구 송도동 123', '2023-04-20', 800, 1),
       ('정수빈', 'subin.jung@example.com', '010-5678-9012', '광주광역시 북구 각화동 456', '2023-05-15', 700, 1),
       ('이상호', 'sangho.lee@example.com', '010-6789-0123', '서울특별시 종로구 청운효자동 789', '2023-06-10', 650, 0),
       ('이수진', 'sujin.lee@example.com', '010-7890-1234', '경기도 성남시 분당구 야탑동 123', '2023-07-05', 690, 1),
       ('김동훈', 'donghoon.kim@example.com', '010-8901-2345', '대전광역시 유성구 복용동 456', '2023-08-15', 730, 1),
       ('홍영희', 'younghee.hong@example.com', '010-9012-3456', '울산광역시 남구 삼산동 789', '2023-09-20', 670, 0),
       ('최지혜', 'jihae.choi@example.com', '010-0123-4567', '서울특별시 강북구 수유동 123', '2023-10-10', 710, 1),
       ('조민우', 'minwoo.jo@example.com', '010-1234-5679', '부산광역시 부산진구 전포동 456', '2023-11-01', 660, 1),
       ('박서준', 'seojun.park@example.com', '010-2345-6780', '서울특별시 서대문구 충정로 789', '2023-12-15', 690, 0),
       ('김혜진', 'hyejin.kim@example.com', '010-3456-7891', '광주광역시 서구 화정동 123', '2024-01-05', 750, 1),
       ('이한결', 'hankyul.lee@example.com', '010-4567-8902', '대구광역시 중구 삼덕동 456', '2024-02-10', 680, 1),
       ('장예원', 'yewon.jang@example.com', '010-5678-9013', '서울특별시 동대문구 청량리 789', '2024-03-15', 720, 1),
       ('신지훈', 'jihoon.shin@example.com', '010-6789-0124', '부산광역시 사하구 하단동 123', '2024-04-20', 690, 1),
       ('박현정', 'hyeonjeong.park@example.com', '010-7890-1235', '서울특별시 용산구 이촌동 456', '2024-05-25', 740, 1),
       ('이민수', 'minsu.lee@example.com', '010-8901-2346', '경기도 고양시 일산동구 장항동 789', '2024-06-30', 660, 0),
       ('전지현', 'jihyeon.jeon@example.com', '010-9012-3457', '대전광역시 동구 대동 123', '2024-07-15', 720, 1),
       ('유하은', 'haeun.yu@example.com', '010-0123-4568', '광주광역시 동구 중앙로 456', '2024-08-10', 680, 1),
       ('김현수', 'hyeonsu.kim@example.com', '010-1234-5670', '울산광역시 북구 진장동 789', '2024-09-05', 690, 0),
       ('정하늘', 'haneul.jeong@example.com', '010-2345-6781', '경기도 안양시 동안구 호계동 123', '2024-10-20', 700, 1),
       ('송민서', 'minseo.song@example.com', '010-3456-7892', '서울특별시 노원구 상계동 456', '2024-11-01', 740, 1),
       ('박지영', 'jiyeong.park@example.com', '010-4567-8903', '부산광역시 남구 대연동 789', '2024-12-10', 660, 1),
       ('권태희', 'taehee.kwon@example.com', '010-5678-9014', '서울특별시 강서구 가양동 123', '2025-01-15', 680, 0),
       ('홍성민', 'seongmin.hong@example.com', '010-6789-0125', '대구광역시 동구 신천동 456', '2025-02-25', 710, 1),
       ('이소영', 'soyoung.lee@example.com', '010-7890-1236', '경기도 시흥시 신천동 789', '2025-03-30', 690, 1),
       ('조현정', 'hyeonjeong.jo@example.com', '010-8901-2347', '광주광역시 남구 월산동 123', '2025-04-20', 680, 1),
       ('이경수', 'kyungsoo.lee@example.com', '010-9012-3458', '서울특별시 중랑구 상봉동 456', '2025-05-15', 700, 0),
       ('정세은', 'seeun.jeong@example.com', '010-0123-4569', '부산광역시 금정구 장전동 789', '2025-06-25', 720, 1),
       ('최지민', 'jimin.choi@example.com', '010-1234-5671', '대전광역시 서구 둔산동 123', '2025-07-30', 660, 1),
       ('박시연', 'siyeon.park@example.com', '010-2345-6782', '경기도 화성시 동탄동 456', '2025-08-15', 680, 1),
       ('이찬우', 'chanwoo.lee@example.com', '010-3456-7893', '서울특별시 동작구 사당동 789', '2025-09-10', 700, 0),
       ('김유진', 'yujin.kim@example.com', '010-4567-8904', '광주광역시 서구 치평동 123', '2025-10-25', 710, 1),
       ('정진호', 'jinho.jeong@example.com', '010-5678-9015', '부산광역시 동래구 명륜동 456', '2025-11-20', 680, 1),
       ('이상희', 'sanghee.lee@example.com', '010-6789-0126', '대전광역시 중구 오류동 789', '2025-12-15', 700, 0);

-- ApplicationMethod 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`application_method` (`id`, `name`)
VALUES (1, '온라인'),
       (2, '지점 방문'),
       (3, '모바일 앱');

-- LoanProductsType 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`loan_products_type` (`id`, `name`)
VALUES (1, '개인 대출'),
       (2, '주택 대출'),
       (3, '자동차 대출');

-- LoanProductsFeatures 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`loan_products_features` (`id`, `name`)
VALUES (1, '낮은 이자율'),
       (2, '유연한 상환'),
       (3, '조기 상환 수수료 없음'),
       (4, '빠른 승인');

-- provider 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`provider` (`id`, `name`, is_active)
VALUES (1, '우리 은행', 1),
       (2, '농협 은행', 1),
       (3, '기업 은행', 1),
       (4, '신한 은행', 1),
       (5, '국민 은행', 1),
       (6, '새마을 금고', 1),
       (7, '한국 은행', 1),
       (8, '러시안캐시', 1),
       (9, '콩팥머니', 1),
       (10, '산림 조합', 1),
       (11, '하나 은행', 1),
       (12, '씨티 은행', 1),
       (13, '미래에셋 은행', 1),
       (14, '우리금융', 1),
       (15, '산업 은행', 1),
       (16, '대구 은행', 1),
       (17, '부산 은행', 1),
       (18, '경남 은행', 1),
       (19, '제주 은행', 1),
       (20, '광주 은행', 1),
       (21, '전북 은행', 1),
       (22, '카카오 은행', 1);

-- LoanProducts 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`loan_products` (`id`, `start_date`, `interest_rate`, `max_limit`, `repayment_period`,
                                    `required_credit_score`, `type_id`, `application_method_id`,
                                    `loan_products_feature_id`,
                                    `provider_id`)
VALUES (1, '1992-01-15', 5.0, 1000000, 12, 650, 1, 1, 1, 1),
       (2, '1993-02-28', 4.5, 500000, 24, 670, 2, 2, 2, 2),
       (3, '1994-04-10', 6.0, 2000000, 36, 690, 3, 1, 1, 3),
       (4, '1995-05-25', 7.0, 1500000, 48, 710, 1, 2, 3, 4),
       (5, '1996-07-18', 3.5, 300000, 60, 630, 2, 1, 4, 5),
       (6, '1997-09-12', 5.2, 800000, 18, 650, 3, 2, 1, 6),
       (7, '1998-11-04', 4.8, 600000, 24, 670, 1, 1, 2, 7),
       (8, '1999-12-15', 6.1, 900000, 36, 690, 2, 2, 3, 8),
       (9, '2000-01-20', 7.0, 1500000, 48, 710, 3, 1, 4, 9),
       (10, '2001-03-05', 5.5, 750000, 60, 660, 1, 2, 1, 10),
       (11, '2002-04-15', 4.4, 1000000, 12, 670, 2, 1, 2, 11),
       (12, '2003-06-01', 6.2, 1200000, 24, 690, 3, 2, 3, 12),
       (13, '2004-07-20', 5.8, 800000, 36, 680, 1, 1, 4, 13),
       (14, '2005-09-10', 4.7, 1500000, 48, 700, 2, 2, 1, 14),
       (15, '2006-10-25', 6.0, 1700000, 60, 690, 3, 1, 2, 15),
       (16, '2007-12-15', 5.3, 1100000, 12, 670, 1, 2, 3, 16),
       (17, '2008-01-10', 3.9, 2000000, 24, 720, 2, 1, 4, 17),
       (18, '2009-03-30', 6.4, 1500000, 36, 680, 3, 2, 1, 18),
       (19, '2010-05-20', 5.6, 950000, 48, 700, 1, 1, 2, 19),
       (20, '2011-07-15', 4.5, 1200000, 60, 690, 2, 2, 3, 20),
       (21, '2012-09-05', 6.1, 1500000, 12, 680, 3, 1, 4, 21),
       (22, '2013-10-25', 5.7, 800000, 24, 670, 1, 2, 1, 22),
       (23, '2014-12-10', 4.6, 1000000, 36, 710, 2, 1, 2, 1),
       (24, '2015-01-20', 6.3, 1300000, 48, 690, 3, 2, 3, 2),
       (25, '2016-03-05', 5.8, 1400000, 60, 680, 1, 1, 4, 3),
       (26, '2017-05-15', 4.4, 1700000, 12, 700, 2, 2, 1, 4),
       (27, '2018-07-25', 6.0, 2000000, 24, 710, 3, 1, 2, 5),
       (28, '2019-09-15', 5.5, 950000, 36, 690, 1, 2, 3, 6),
       (29, '2020-11-30', 4.8, 1500000, 48, 670, 2, 1, 4, 7),
       (30, '2021-01-10', 6.2, 1300000, 60, 680, 3, 2, 1, 8),
       (31, '2022-03-25', 5.9, 1000000, 12, 700, 1, 1, 2, 9),
       (32, '2023-05-15', 4.5, 1200000, 24, 690, 2, 2, 3, 10),
       (33, '2024-01-20', 6.1, 1500000, 36, 680, 3, 1, 4, 11),
       (34, '2024-07-01', 5.2, 1800000, 48, 700, 1, 2, 1, 12),
       (35, '2024-07-15', 4.9, 800000, 60, 690, 2, 1, 2, 13),
       (36, '2024-06-30', 6.3, 1500000, 12, 680, 3, 2, 3, 14),
       (37, '2024-05-15', 5.6, 950000, 24, 700, 1, 1, 4, 15),
       (38, '2024-04-01', 4.3, 1700000, 36, 690, 2, 2, 1, 16),
       (39, '2024-03-10', 6.0, 2000000, 48, 680, 3, 1, 2, 17),
       (40, '2024-02-20', 5.7, 1200000, 60, 700, 1, 2, 3, 18),
       (41, '2024-01-05', 4.8, 1500000, 12, 710, 2, 1, 4, 19),
       (42, '2023-12-15', 6.1, 1300000, 24, 690, 3, 2, 1, 20),
       (43, '2023-11-10', 5.4, 950000, 36, 680, 1, 1, 2, 21),
       (44, '2023-10-01', 4.9, 1700000, 48, 700, 2, 2, 3, 22),
       (45, '2023-09-20', 6.0, 2000000, 60, 690, 3, 1, 4, 1),
       (46, '2023-08-10', 5.5, 1400000, 12, 680, 1, 2, 1, 2),
       (47, '2023-07-01', 4.6, 1200000, 24, 690, 2, 1, 2, 3),
       (48, '2023-06-15', 6.2, 1000000, 36, 700, 3, 2, 3, 4),
       (49, '2023-05-05', 5.8, 1500000, 48, 680, 1, 1, 4, 5),
       (50, '2023-04-20', 4.4, 1700000, 60, 690, 2, 2, 1, 6),
       (51, '2023-03-10', 6.1, 2000000, 12, 680, 3, 1, 2, 7),
       (52, '2023-02-01', 5.7, 1200000, 24, 690, 1, 2, 3, 8),
       (53, '2023-01-20', 4.5, 1500000, 36, 680, 2, 1, 4, 9),
       (54, '2022-12-15', 6.3, 1300000, 48, 700, 3, 2, 1, 10),
       (55, '2022-11-05', 5.6, 2000000, 60, 690, 1, 1, 2, 11),
       (56, '2022-10-01', 4.7, 1000000, 12, 680, 2, 2, 3, 12),
       (57, '2022-09-15', 6.0, 1200000, 24, 700, 3, 1, 4, 13),
       (58, '2022-08-10', 5.3, 1500000, 36, 690, 1, 2, 1, 14),
       (59, '2022-07-05', 4.8, 2000000, 48, 680, 2, 1, 2, 15),
       (60, '2022-06-01', 6.2, 1300000, 60, 700, 3, 2, 3, 16),
       (61, '2022-05-15', 5.7, 1400000, 12, 690, 1, 1, 4, 17),
       (62, '2022-04-01', 4.9, 1500000, 24, 680, 2, 2, 1, 18),
       (63, '2022-03-10', 6.1, 2000000, 36, 690, 3, 1, 2, 19),
       (64, '2022-02-01', 5.6, 1000000, 48, 700, 1, 2, 3, 20),
       (65, '2022-01-20', 4.7, 1200000, 60, 690, 2, 1, 4, 21),
       (66, '2021-12-15', 6.2, 1500000, 12, 680, 3, 2, 1, 22),
       (67, '2021-11-01', 5.8, 2000000, 24, 690, 1, 1, 2, 1),
       (68, '2021-10-20', 4.9, 1300000, 36, 700, 2, 2, 3, 2),
       (69, '2021-09-05', 6.0, 1200000, 48, 680, 3, 1, 4, 3),
       (70, '2021-08-15', 5.7, 1400000, 60, 690, 1, 2, 1, 4),
       (71, '2021-07-01', 4.6, 1500000, 12, 680, 2, 1, 2, 5),
       (72, '2021-06-15', 6.3, 1700000, 24, 690, 3, 2, 3, 6),
       (73, '2021-05-05', 5.6, 1000000, 36, 700, 1, 1, 4, 7),
       (74, '2021-04-20', 4.7, 2000000, 48, 690, 2, 2, 1, 8),
       (75, '2021-03-15', 6.2, 1500000, 60, 680, 3, 1, 2, 9),
       (76, '2021-02-01', 5.8, 1300000, 12, 700, 1, 2, 3, 10),
       (77, '2021-01-20', 4.9, 1200000, 24, 690, 2, 1, 4, 11),
       (78, '2020-12-15', 6.1, 1400000, 36, 680, 3, 2, 1, 12),
       (79, '2020-11-01', 5.7, 2000000, 48, 690, 1, 1, 2, 13),
       (80, '2020-10-15', 4.6, 1000000, 60, 700, 2, 2, 3, 14),
       (81, '2020-09-01', 6.2, 1500000, 12, 680, 3, 1, 4, 15),
       (82, '2020-08-10', 5.8, 1700000, 24, 690, 1, 2, 1, 16),
       (83, '2020-07-01', 4.7, 2000000, 36, 700, 2, 1, 2, 17),
       (84, '2020-06-15', 6.0, 1200000, 48, 680, 3, 2, 3, 18),
       (85, '2020-05-01', 5.5, 1400000, 60, 690, 1, 1, 4, 19),
       (86, '2020-04-15', 4.8, 1000000, 12, 680, 2, 2, 1, 20),
       (87, '2020-03-01', 6.1, 2000000, 24, 700, 3, 1, 2, 21),
       (88, '2020-02-10', 5.6, 1300000, 36, 690, 1, 2, 3, 22),
       (89, '2020-01-01', 4.5, 1500000, 48, 680, 2, 1, 4, 1),
       (90, '2019-12-15', 6.0, 1200000, 60, 690, 3, 2, 1, 2),
       (91, '2019-11-10', 5.7, 2000000, 12, 680, 1, 1, 2, 3),
       (92, '2019-10-01', 4.8, 1000000, 24, 690, 2, 2, 3, 4),
       (93, '2019-09-15', 6.2, 1500000, 36, 700, 3, 1, 4, 5),
       (94, '2019-08-01', 5.5, 1700000, 48, 680, 1, 2, 1, 6),
       (95, '2019-07-20', 4.7, 2000000, 60, 690, 2, 1, 2, 7),
       (96, '2019-06-15', 6.3, 1300000, 12, 680, 3, 2, 3, 8),
       (97, '2019-05-01', 5.6, 1500000, 24, 690, 1, 1, 4, 9),
       (98, '2019-04-10', 4.9, 1200000, 36, 680, 2, 2, 1, 10),
       (99, '2019-03-15', 6.1, 1000000, 48, 700, 3, 1, 2, 11),
       (100, '2019-02-01', 5.8, 2000000, 60, 690, 1, 2, 3, 12);


-- MemberLoanProducts 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`member_loan_products`
(`member_id`, `loan_products_id`, `start_date`, `end_date`, `loan_amount`, `loan_due_date`, `repayment_count`, `late_payment_count`, `goal_amount`, `total_paid_amount`, `total_repayment_amount`)
VALUES
    (1, 2, '2023-01-10', '2026-01-10', 500000, '2026-01-10', 24, 0, 21000, 100000, 504000),  -- 김수민
    (2, 3, '2023-02-15', '2025-02-15', 2000000, '2025-02-15', 36, 1, 62000, 180000, 2232000),  -- 이영희
    (3, 4, '2022-12-01', '2024-12-01', 1500000, '2024-12-01', 24, 0, 65000, 260000, 1560000),  -- 박지훈
    (4, 5, '2023-03-20', '2026-03-20', 300000, '2026-03-20', 36, 2, 15000, 60000, 315000),  -- 최예린
    (5, 6, '2022-09-05', '2025-09-05', 800000, '2025-09-05', 24, 0, 35000, 120000, 840000),  -- 정수빈
    (1, 7, '2021-08-12', '2024-08-12', 600000, '2024-08-12', 36, 0, 28000, 84000, 616000),  -- 김수민
    (2, 8, '2020-11-25', '2023-11-25', 900000, '2023-11-25', 24, 1, 38000, 76000, 938000),  -- 이영희
    (3, 9, '2021-01-30', '2024-01-30', 1500000, '2024-01-30', 36, 0, 55000, 220000, 1655000),  -- 박지훈
    (4, 10, '2021-05-05', '2024-05-05', 750000, '2024-05-05', 36, 1, 31000, 93000, 775000),  -- 최예린
    (5, 1, '2022-07-18', '2025-07-18', 500000, '2025-07-18', 24, 0, 22000, 66000, 510000);  -- 정수빈

