-- Table: public.employees

-- DROP TABLE public.employees;

CREATE TABLE public.employees
(
    employee_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    user_id smallint NOT NULL,
    first_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT employees_pkey PRIMARY KEY (employee_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
)

TABLESPACE pg_default;

ALTER TABLE public.employees
    OWNER to postgres;

-- Table: public.job_positions

-- DROP TABLE public.job_positions;

CREATE TABLE public.job_positions
(
    position_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    position_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    position_description text COLLATE pg_catalog."default",
    CONSTRAINT job_positions_pkey PRIMARY KEY (position_id),
    CONSTRAINT job_positions_position_name_key UNIQUE (position_name)
)

TABLESPACE pg_default;

ALTER TABLE public.job_positions
    OWNER to postgres;


-- Table: public.job_seeker

-- DROP TABLE public.job_seeker;

CREATE TABLE public.job_seeker
(
    job_seeker_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    user_id smallint NOT NULL,
    first_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    nationality_id character varying(15) COLLATE pg_catalog."default" NOT NULL,
    birth_date date NOT NULL,
    CONSTRAINT job_seeker_pkey PRIMARY KEY (job_seeker_id),
    CONSTRAINT job_seeker_nationality_id_key UNIQUE (nationality_id),
    CONSTRAINT job_seeker_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET DEFAULT
)

TABLESPACE pg_default;

ALTER TABLE public.job_seeker
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    user_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(500) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT users_email_key UNIQUE (email)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;