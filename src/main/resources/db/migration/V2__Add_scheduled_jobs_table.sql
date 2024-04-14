CREATE TABLE scheduled_jobs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_name VARCHAR(100) NOT NULL,
    cron_expression VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

INSERT INTO scheduled_jobs (job_name, cron_expression, enabled)
VALUES ('MyCronJob', '*/10 * * * * *', true);