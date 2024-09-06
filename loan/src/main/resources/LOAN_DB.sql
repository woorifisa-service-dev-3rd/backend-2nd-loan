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
-- Table `LOAN`.`ApplicationMethod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LOAN`.`ApplicationMethod`
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
CREATE TABLE IF NOT EXISTS `LOAN`.`LoanProductsType`
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
CREATE TABLE IF NOT EXISTS `LOAN`.`LoanProductsFeatures`
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
CREATE TABLE IF NOT EXISTS `LOAN`.`LoanProducts`
(
    `id`                       BIGINT  NOT NULL AUTO_INCREMENT,
    `start_date`               DATE    NOT NULL,
    `end_date`                 DATE    NOT NULL,
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
            REFERENCES `LOAN`.`ApplicationMethod` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_LoanProducts_LoanProductsType`
        FOREIGN KEY (`type_id`)
            REFERENCES `LOAN`.`LoanProductsType` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_LoanProducts_LoanProductsFeatures`
        FOREIGN KEY (`loan_products_feature_id`)
            REFERENCES `LOAN`.`LoanProductsFeatures` (`id`)
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
CREATE TABLE IF NOT EXISTS `LOAN`.`Member`
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
CREATE TABLE IF NOT EXISTS `LOAN`.`MemberLoanProducts`
(
    `id`                 BIGINT NOT NULL AUTO_INCREMENT,
    `member_id`          BIGINT NOT NULL,
    `loan_products_id`   BIGINT NOT NULL,
    `start_date`         DATE   NOT NULL,
    `end_date`           DATE   NOT NULL,
    `loan_amount`        BIGINT NOT NULL,
    `loan_due_date`      DATE   NOT NULL,
    `repayment_count`    INT    NOT NULL,
    `late_payment_count` INT    NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Member_has_LoanProducts_LoanProducts1_idx` (`loan_products_id` ASC) VISIBLE,
    INDEX `fk_Member_has_LoanProducts_Member_idx` (`Member_id` ASC) VISIBLE,
    CONSTRAINT `fk_Member_has_LoanProducts_LoanProducts1`
        FOREIGN KEY (`loan_products_id`)
            REFERENCES `LOAN`.`LoanProducts` (`id`),
    CONSTRAINT `fk_Member_has_LoanProducts_Member`
        FOREIGN KEY (`Member_id`)
            REFERENCES `LOAN`.`Member` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
-- Member 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`Member` (`id`, `name`, `email`, `phone_number`, `address`, `registered_date`, `credit_score`,
                             `is_active`)
VALUES (1, '김수민', 'minsu.kim@example.com', '010-1234-5678', '서울특별시 강남구 삼성로 123', '2023-01-15', 750, 1),
       (2, '이영희', 'younghee.lee@example.com', '010-2345-6789', '부산광역시 해운대구 해운대해변로 456', '2023-02-10', 680, 1),
       (3, '박지훈', 'jihoon.park@example.com', '010-3456-7890', '대구광역시 수성구 범어동 789', '2023-03-05', 720, 0),
       (4, '최예린', 'yerin.choi@example.com', '010-4567-8901', '인천광역시 연수구 송도동 123', '2023-04-20', 800, 1),
       (5, '정수빈', 'subin.jung@example.com', '010-5678-9012', '광주광역시 북구 각화동 456', '2023-05-15', 700, 1);

-- ApplicationMethod 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`ApplicationMethod` (`id`, `name`)
VALUES (1, '온라인'),
       (2, '지점 방문'),
       (3, '모바일 앱');

-- LoanProductsType 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`LoanProductsType` (`id`, `name`)
VALUES (1, '개인 대출'),
       (2, '주택 대출'),
       (3, '자동차 대출');

-- LoanProductsFeatures 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`LoanProductsFeatures` (`id`, `name`)
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
INSERT INTO `LOAN`.`LoanProducts` (`id`, `start_date`, `end_date`, `interest_rate`, `max_limit`, `repayment_period`,
                                   `required_credit_score`, `type_id`, `application_method_id`,
                                   `loan_products_feature_id`,
                                   `provider_id`)
VALUES (1, '2024-01-01', '2024-06-30', 5, 1000000,6, 700, 1, 1, 1, 1),
       (2, '2024-07-01', '2025-01-01', 4, 500000, 12, 650, 2, 2, 2, 2),
       (3, '2024-03-01', '2025-03-01', 6, 2000000, 24, 720, 1, 1, 3, 3),
       (4, '2024-05-01', '2024-12-31', 7, 1500000, 60, 680, 2, 1, 4, 4);

-- MemberLoanProducts 테이블에 더미 데이터 삽입
INSERT INTO `LOAN`.`MemberLoanProducts` (`id`, `member_id`, `loan_products_id`, `start_date`, `end_date`, `loan_amount`,
                                         `loan_due_date`, `repayment_count`, `late_payment_count`)
VALUES (1, 1, 1, '2024-01-01', '2024-06-30', 500000, '2024-06-30', 6, 0),
       (2, 2, 2, '2024-07-01', '2025-01-01', 300000, '2025-01-01', 12, 1),
       (3, 3, 3, '2024-03-01', '2025-03-01', 1000000, '2025-03-01', 12, 2),
       (4, 4, 4, '2024-05-01', '2024-12-31', 700000, '2024-12-31', 8, 0),
       (5, 5, 1, '2024-02-01', '2024-07-01', 600000, '2024-07-01', 6, 1);
