-- MySQL Workbench Forward Engineering

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
-- Table `LOAN`.`Member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`Member`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(45) NOT NULL,
    `email`         VARCHAR(45) NOT NULL,
    `phone_number`  VARCHAR(45) NOT NULL,
    `address`       VARCHAR(45) NOT NULL,
    `registed_date` DATE        NOT NULL,
    `credit_score`  INT         NOT NULL,
    `is_active`     TINYINT     NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `LOAN`.`LoanProducts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`LoanProducts`
(
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `start_date`            DATE   NOT NULL,
    `end_date`              DATE   NOT NULL,
    `interest_rate`         INT    NOT NULL,
    `max_limit`             BIGINT NOT NULL,
    `repayment_period`      DATE   NOT NULL,
    `required_credit_score` INT    NOT NULL,
    `LoanProductsType_id`   BIGINT NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LOAN`.`MemberLoanProducts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`MemberLoanProducts`
(
    `id`                 BIGINT NOT NULL AUTO_INCREMENT,
    `Member_id`          BIGINT NOT NULL,
    `LoanProducts_id`    BIGINT NOT NULL,
    `start_date`         DATE   NOT NULL,
    `end_date`           DATE   NOT NULL,
    `loan_amount`        BIGINT NOT NULL,
    `loan_due_date`      DATE   NOT NULL,
    `repayment_count`    INT    NOT NULL,
    `late_payment_count` INT    NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Member_has_LoanProducts_LoanProducts1_idx` (`LoanProducts_id` ASC) VISIBLE,
    INDEX `fk_Member_has_LoanProducts_Member_idx` (`Member_id` ASC) VISIBLE,
    CONSTRAINT `fk_Member_has_LoanProducts_Member`
        FOREIGN KEY (`Member_id`)
            REFERENCES `LOAN`.`Member` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Member_has_LoanProducts_LoanProducts1`
        FOREIGN KEY (`LoanProducts_id`)
            REFERENCES `LOAN`.`LoanProducts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `LOAN`.`LoanProductsFeatures`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`LoanProductsFeatures`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `NAME`            VARCHAR(45) NULL,
    `LoanProducts_id` BIGINT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_LoanProductsFeatures_LoanProducts1_idx` (`LoanProducts_id` ASC) VISIBLE,
    CONSTRAINT `fk_LoanProductsFeatures_LoanProducts1`
        FOREIGN KEY (`LoanProducts_id`)
            REFERENCES `LOAN`.`LoanProducts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LOAN`.`LoanProductsType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`LoanProductsType`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(45) NOT NULL,
    `LoanProducts_id` BIGINT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_LoanProductsType_LoanProducts1_idx` (`LoanProducts_id` ASC) VISIBLE,
    CONSTRAINT `fk_LoanProductsType_LoanProducts1`
        FOREIGN KEY (`LoanProducts_id`)
            REFERENCES `LOAN`.`LoanProducts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `LOAN`.`ApplicationMethod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`ApplicationMethod`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `NAME`            VARCHAR(45) NOT NULL,
    `LoanProducts_id` BIGINT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_ApplicationMethod_LoanProducts1_idx` (`LoanProducts_id` ASC) VISIBLE,
    CONSTRAINT `fk_ApplicationMethod_LoanProducts1`
        FOREIGN KEY (`LoanProducts_id`)
            REFERENCES `LOAN`.`LoanProducts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `LOAN`.`provider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`provider`
(
    `id`              INT         NOT NULL AUTO_INCREMENT,
    `NAME`            VARCHAR(45) NOT NULL,
    `LoanProducts_id` BIGINT      NOT NULL,
    PRIMARY KEY (`id`, `LoanProducts_id`),
    INDEX `fk_provider_LoanProducts1_idx` (`LoanProducts_id` ASC) VISIBLE,
    CONSTRAINT `fk_provider_LoanProducts1`
        FOREIGN KEY (`LoanProducts_id`)
            REFERENCES `LOAN`.`LoanProducts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
-- Member 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`Member` (`id`, `name`, `email`, `phone_number`, `address`, `registed_date`, `credit_score`,
                             `is_active`)
VALUES (1, '김민수', 'minsu.kim@example.com', '010-1234-5678', '서울특별시 강남구 삼성로 123', '2023-01-15', 750, 1),
       (2, '이영희', 'younghee.lee@example.com', '010-2345-6789', '부산광역시 해운대구 해운대해변로 456', '2023-02-10', 680, 1),
       (3, '박지훈', 'jihoon.park@example.com', '010-3456-7890', '대구광역시 수성구 범어동 789', '2023-03-05', 720, 0),
       (4, '최예린', 'yerin.choi@example.com', '010-4567-8901', '인천광역시 연수구 송도동 123', '2023-04-20', 800, 1),
       (5, '정수빈', 'subin.jung@example.com', '010-5678-9012', '광주광역시 북구 각화동 456', '2023-05-15', 700, 1);

-- LoanProducts 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`LoanProducts` (`id`, `start_date`, `end_date`, `interest_rate`, `max_limit`, `repayment_period`,
                                   `required_credit_score`, `LoanProductsType_id`)
VALUES (1, '2023-01-01', '2024-01-01', 5, 1000000, '2024-01-01', 600, 1),
       (2, '2023-02-01', '2024-02-01', 4, 2000000, '2024-02-01', 650, 2),
       (3, '2023-03-01', '2024-03-01', 6, 1500000, '2024-03-01', 700, 1),
       (4, '2023-04-01', '2024-04-01', 3.5, 2500000, '2024-04-01', 620, 3),
       (5, '2023-05-01', '2024-05-01', 4.5, 500000, '2024-05-01', 580, 2);

-- MemberLoanProducts 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`MemberLoanProducts` (`Member_id`, `LoanProducts_id`, `start_date`, `end_date`, `loan_amount`,
                                         `loan_due_date`, `repayment_count`, `late_payment_count`)
VALUES (1, 1, '2023-01-15', '2024-01-15', 1000000, '2024-01-15', 12, 0),
       (2, 2, '2023-02-10', '2024-02-10', 1500000, '2024-02-10', 12, 1),
       (3, 3, '2023-03-05', '2024-03-05', 2000000, '2024-03-05', 12, 0),
       (1, 4, '2023-04-20', '2024-04-20', 500000, '2024-04-20', 6, 2),
       (4, 5, '2023-05-15', '2024-05-15', 800000, '2024-05-15', 12, 0);

-- LoanProductsFeatures 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`LoanProductsFeatures` (`id`, `NAME`, `LoanProducts_id`)
VALUES (1, '저금리', 1),
       (2, '무담보', 1),
       (3, '신용카드 할인', 2),
       (4, '장기 대출', 3),
       (5, '조기 상환 가능', 4);

-- LoanProductsType 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`LoanProductsType` (`id`, `name`, `LoanProducts_id`)
VALUES (1, '개인 대출', 1),
       (2, '신용 대출', 2),
       (3, '담보 대출', 3);

-- ApplicationMethod 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`ApplicationMethod` (`id`, `NAME`, `LoanProducts_id`)
VALUES (1, '온라인 신청', 1),
       (2, '지점 방문', 2),
       (3, '전화 상담', 3),
       (4, '앱 신청', 4);

-- provider 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`provider` (`id`, `NAME`, `LoanProducts_id`)
VALUES (1, '우리은행', 1),
       (2, '국민은행', 2),
       (3, '신한은행', 3),
       (4, '하나은행', 4);
