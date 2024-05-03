CREATE TABLE IF NOT EXISTS flyway_schema_history (
                                                     installed_rank INT PRIMARY KEY,
                                                     version VARCHAR(50),
    description VARCHAR(200),
    type VARCHAR(20),
    script VARCHAR(1000),
    checksum INT,
    installed_by VARCHAR(100),
    installed_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    execution_time INT,
    success BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.internships
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    description character varying(255) COLLATE pg_catalog."default",
    end_date date,
    end_date_recording date,
    name character varying(255) COLLATE pg_catalog."default",
    start_date date,
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT internships_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.lessons
(
    lesson_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    date_time date,
    description character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    internship_id bigint,
    CONSTRAINT lessons_pkey PRIMARY KEY (lesson_id),
    CONSTRAINT fkoxqp1ujtwobc8wbgymcydga38 FOREIGN KEY (internship_id)
    REFERENCES public.internships (id) MATCH SIMPLE
                        ON UPDATE NO ACTION
                        ON DELETE NO ACTION
    );

CREATE TABLE IF NOT EXISTS public.participants
(
    participant_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    about character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default",
    course integer NOT NULL,
    date_of_birth date,
    education_status character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    faculty character varying(255) COLLATE pg_catalog."default",
    full_name character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    specialty character varying(255) COLLATE pg_catalog."default",
    telegram_id character varying(255) COLLATE pg_catalog."default",
    university character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT participants_pkey PRIMARY KEY (participant_id)
    );

CREATE TABLE IF NOT EXISTS public.tasks
(
    task_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    gitlab_repository character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    internship_id bigint,
    CONSTRAINT tasks_pkey PRIMARY KEY (task_id),
    CONSTRAINT fk3iq0gxkfwgyxdmlukvibuuqs FOREIGN KEY (internship_id)
    REFERENCES public.internships (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

CREATE TABLE IF NOT EXISTS public.performances
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    comment character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    participant_id bigint,
    task_id bigint,
    CONSTRAINT performances_pkey PRIMARY KEY (id),
    CONSTRAINT fkg3e7hke997elnsekic2yu823n FOREIGN KEY (participant_id)
    REFERENCES public.participants (participant_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fknbtaum5dyuv45i1cb1qfpwwd FOREIGN KEY (task_id)
    REFERENCES public.tasks (task_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

CREATE TABLE IF NOT EXISTS public.messages
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    message character varying(255) COLLATE pg_catalog."default",
    participant_id bigint,
    task_id bigint,
    read boolean NOT NULL,
    CONSTRAINT messages_pkey PRIMARY KEY (id),
    CONSTRAINT fk1fe2brsvkicrj653duknqxf7h FOREIGN KEY (task_id)
    REFERENCES public.tasks (task_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkkdw1063c1a5o8xyvt0hvfpq3c FOREIGN KEY (participant_id)
    REFERENCES public.participants (participant_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

