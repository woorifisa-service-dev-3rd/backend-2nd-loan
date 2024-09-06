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
    `interest_rate`            DECIMAL NOT NULL,
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
INSERT INTO `LOAN`.`member` (`id`, `name`, `email`, `phone_number`, `address`, `registered_date`, `credit_score`,
                             `is_active`)
VALUES (1, '김수민', 'minsu.kim@example.com', '010-1234-5678', '서울특별시 강남구 삼성로 123', '2023-01-15', 750, 1),
       (2, '이영희', 'younghee.lee@example.com', '010-2345-6789', '부산광역시 해운대구 해운대해변로 456', '2023-02-10', 680, 1),
       (3, '박지훈', 'jihoon.park@example.com', '010-3456-7890', '대구광역시 수성구 범어동 789', '2023-03-05', 720, 0),
       (4, '최예린', 'yerin.choi@example.com', '010-4567-8901', '인천광역시 연수구 송도동 123', '2023-04-20', 800, 1),
       (5, '정수빈', 'subin.jung@example.com', '010-5678-9012', '광주광역시 북구 각화동 456', '2023-05-15', 700, 1);

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
VALUES (1, '농협 은행 ', 1),
       (2, '우리 은행', 1),
       (3, '기업 은행', 1);

-- LoanProducts 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`loan_products` (`id`, `start_date`, `interest_rate`, `max_limit`, `repayment_period`,
                                    `required_credit_score`, `type_id`, `application_method_id`,
                                    `loan_products_feature_id`,
                                    `provider_id`)
VALUES (1, '2024-01-01', 5, 1000000, 6, 700, 1, 1, 1, 1),
       (2, '2024-07-01', 4, 500000, 12, 650, 2, 2, 2, 2),
       (3, '2024-03-01', 6, 2000000, 24, 720, 1, 1, 3, 3),
       (4, '2024-05-01', 7, 1500000, 60, 680, 2, 1, 4, 3),
       (5, '2024-02-15', 3.5, 300000, 12, 640, 1, 1, 1, 1),
       (6, '2024-06-10', 5.2, 800000, 18, 680, 2, 2, 1, 2),
       (7, '2024-09-22', 4.5, 600000, 24, 700, 3, 3, 2, 3),
       (8, '2024-10-05', 6.1, 900000, 36, 710, 1, 1, 2, 1),
       (9, '2024-11-11', 7.0, 1500000, 48, 750, 2, 2, 3, 2),
       (10, '2024-12-01', 5.8, 500000, 60, 650, 3, 3, 3, 3),
       (11, '2025-01-15', 4.3, 2000000, 6, 630, 1, 1, 4, 1),
       (12, '2025-02-10', 6.7, 1000000, 12, 680, 2, 2, 4, 2),
       (13, '2025-03-08', 5.1, 750000, 18, 700, 3, 3, 1, 3),
       (14, '2025-04-20', 3.9, 2500000, 24, 720, 1, 1, 2, 1),
       (15, '2025-05-15', 6.2, 1700000, 36, 690, 2, 2, 3, 2),
       (16, '2025-06-01', 4.8, 500000, 48, 640, 3, 3, 4, 3),
       (17, '2025-07-01', 3.6, 1000000, 6, 670, 1, 1, 1, 1),
       (18, '2025-08-10', 6.5, 1300000, 12, 710, 2, 2, 2, 2),
       (19, '2025-09-25', 5.3, 900000, 18, 660, 3, 3, 3, 3),
       (20, '2025-10-15', 3.7, 1800000, 24, 750, 1, 1, 4, 1),
       (21, '2025-11-30', 7.1, 1200000, 36, 690, 2, 2, 1, 2),
       (22, '2025-12-20', 5.4, 1400000, 48, 700, 3, 3, 2, 3),
       (23, '2026-01-10', 4.0, 800000, 6, 640, 1, 1, 3, 1),
       (24, '2026-02-14', 6.0, 1600000, 12, 670, 2, 2, 4, 2),
       (25, '2026-03-18', 5.7, 1100000, 18, 710, 3, 3, 1, 3),
       (26, '2026-04-22', 4.9, 900000, 24, 680, 1, 1, 2, 1),
       (27, '2026-05-15', 6.8, 1500000, 36, 720, 2, 2, 3, 2),
       (28, '2026-06-01', 5.6, 750000, 48, 700, 3, 3, 4, 3),
       (29, '2026-07-07', 4.1, 1800000, 6, 650, 1, 1, 1, 1),
       (30, '2026-08-10', 7.3, 2000000, 12, 740, 2, 2, 2, 2),
       (31, '2026-09-20', 5.5, 1300000, 18, 670, 3, 3, 3, 3),
       (32, '2026-10-05', 4.2, 950000, 24, 690, 1, 1, 4, 1),
       (33, '2026-11-01', 6.9, 1200000, 36, 700, 2, 2, 1, 2),
       (34, '2026-12-15', 5.0, 1400000, 48, 660, 3, 3, 2, 3),
       (35, '2027-01-10', 4.6, 750000, 6, 720, 1, 1, 3, 1),
       (36, '2027-02-25', 6.3, 1500000, 12, 680, 2, 2, 4, 2),
       (37, '2027-03-18', 5.2, 1100000, 18, 640, 3, 3, 1, 3),
       (38, '2027-04-14', 3.8, 1800000, 24, 700, 1, 1, 2, 1),
       (39, '2027-05-10', 6.6, 1700000, 36, 710, 2, 2, 3, 2),
       (40, '2027-06-01', 5.9, 950000, 48, 670, 3, 3, 4, 3);


-- MemberLoanProducts 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`member_loan_products` (`id`, `member_id`, `loan_products_id`, `start_date`,
                                           `loan_amount`,
                                           `loan_due_date`, `repayment_count`, `late_payment_count`)


VALUES (1, 1, 1, '2024-01-01', 500000, '2024-06-30', 6, 0),
       (2, 2, 2, '2024-07-01', 300000, '2025-01-01', 12, 1),
       (3, 3, 3, '2024-03-01', 1000000, '2025-03-01', 12, 2),
       (4, 4, 4, '2024-05-01', 700000, '2024-12-31', 8, 0),
       (5, 5, 1, '2024-02-01', 600000, '2024-07-01', 6, 1);
