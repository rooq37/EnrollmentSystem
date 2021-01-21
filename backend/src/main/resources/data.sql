-- DROP TABLE IF EXISTS STUDENTS_ENROLLMENT_BLOCKS;
-- DROP TABLE IF EXISTS ENROLLMENT_BLOCKS;
-- DROP TABLE IF EXISTS STUDENTS;
--
-- CREATE TABLE STUDENTS (
--     INDEX BIGINT PRIMARY KEY
-- );
--
-- CREATE TABLE ENROLLMENT_BLOCKS (
--     ID SERIAL PRIMARY KEY,
--     NAME VARCHAR(50) NOT NULL,
--     IS_LIMITED BOOLEAN NOT NULL,
--     START_DATE TIMESTAMP NOT NULL,
--     END_DATE TIMESTAMP NOT NULL,
--     IS_CORRECTION BOOLEAN NOT NULL
-- );
--
-- CREATE TABLE STUDENTS_ENROLLMENT_BLOCKS (
--     STUDENT_INDEX BIGINT NOT NULL,
--     ENROLLMENT_BLOCK_ID BIGINT NOT NULL,
--     CONSTRAINT PK_STUDENTS_ENROLLMENT_BLOCKS PRIMARY KEY (STUDENT_INDEX, ENROLLMENT_BLOCK_ID),
--     CONSTRAINT FK_STUDENT_ID FOREIGN KEY (STUDENT_INDEX) REFERENCES STUDENTS(INDEX),
--     CONSTRAINT FK_ENROLLMENT_BLOCK_ID FOREIGN KEY (ENROLLMENT_BLOCK_ID) REFERENCES ENROLLMENT_BLOCKS(ID)
-- );

INSERT INTO STUDENTS (INDEX) VALUES (238123);
INSERT INTO ENROLLMENT_BLOCKS (ID, NAME, IS_LIMITED, START_DATE, END_DATE, IS_CORRECTION)
VALUES (1, 'Zapisy Lato 2020/2021', TRUE, '2021-01-15T08:00:00.000', '2021-01-15T22:00:00.000', FALSE);
INSERT INTO STUDENTS_ENROLLMENT_BLOCKS (STUDENT_INDEX, ENROLLMENT_BLOCK_ID) VALUES (238123, 1);
