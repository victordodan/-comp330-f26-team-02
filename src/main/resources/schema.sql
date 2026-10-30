CREATE TABLE IF NOT EXISTS support_request (
    id VARCHAR(64) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    category VARCHAR(32) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    student_id VARCHAR(32) NOT NULL,
    status VARCHAR(20) NOT NULL,
    resolution_note VARCHAR(2000) NOT NULL,
    created_at TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    version BIGINT NOT NULL
);
