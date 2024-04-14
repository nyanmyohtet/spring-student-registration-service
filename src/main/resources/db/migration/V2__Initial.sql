CREATE TABLE IF NOT EXISTS students (
  id BIGINT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   address VARCHAR(255) NULL,
   created_date datetime NULL,
   updated_date datetime NULL,
   CONSTRAINT pk_students PRIMARY KEY (id)
);

ALTER TABLE students ADD CONSTRAINT uc_2fa9b1b7386257800c3dd43d1 UNIQUE (id);

CREATE PROCEDURE `student-registration-service`.getStudents(IN pageNumber INT, IN pageSize INT)
begin
	DECLARE offsetValue INT DEFAULT 0;
    SET offsetValue = (pageNumber) * pageSize;

	select
        id,
        std.first_name as first_name,
        std.last_name as last_name,
        std.address as address,
        std.created_date as created_date,
        std.updated_date as updated_date
    from
        students std
    order by
        std.updated_date desc limit pageSize offset offsetValue;
END