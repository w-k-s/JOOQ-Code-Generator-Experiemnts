CREATE SCHEMA jooq_experiment;
CREATE TABLE jooq_experiment.person(
    id BIGSERIAL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_person_id PRIMARY KEY(id)
);