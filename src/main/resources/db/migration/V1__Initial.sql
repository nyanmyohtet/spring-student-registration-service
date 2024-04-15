-- hibernate_sequence table
CREATE TABLE hibernate_sequence (
    next_val BIGINT
);

INSERT INTO hibernate_sequence VALUES (1);

-- students table
CREATE TABLE IF NOT EXISTS students (
  id BIGINT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   age INT NOT NULL,
   address VARCHAR(255) NULL,
   created_date datetime NULL,
   updated_date datetime NULL,
   CONSTRAINT pk_students PRIMARY KEY (id)
);

ALTER TABLE students ADD CONSTRAINT uc_2fa9b1b7386257800c3dd43d1 UNIQUE (id);

-- Stored Procedure
CREATE PROCEDURE `student-registration-service`.getStudents(IN pageNumber INT, IN pageSize INT)
begin
	DECLARE offsetValue INT DEFAULT 0;
    SET offsetValue = (pageNumber) * pageSize;

	SELECT
        id,
        first_name,
        last_name,
        address,
        created_date,
        updated_date
    FROM
        students std
    ORDER BY
        std.updated_date DESC
    LIMIT pageSize OFFSET offsetValue;
END