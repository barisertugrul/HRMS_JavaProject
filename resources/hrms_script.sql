-- This script was generated by a beta version of the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE public.candidates
(
    user_id smallint NOT NULL,
    first_name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    nationality_id character varying(15) NOT NULL,
    year_of_birth smallint NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.cities
(
    city_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    city_name character varying NOT NULL,
    PRIMARY KEY (city_id)
);

CREATE TABLE public.cv_languages
(
    language_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    cv_id smallint NOT NULL,
    language_name character varying(30) NOT NULL,
    language_level smallint NOT NULL,
    PRIMARY KEY (language_id)
);

CREATE TABLE public.cvs
(
    cv_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    user_id smallint NOT NULL,
    github_link character varying(255),
    linkedin_link character varying(255),
    summary text,
    PRIMARY KEY (cv_id)
);

CREATE TABLE public.educations
(
    education_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    cv_id smallint NOT NULL,
    school_name character varying(100) NOT NULL,
    program_name character varying(100) NOT NULL,
    start_date date NOT NULL,
    graduation_date date,
    PRIMARY KEY (education_id)
);

CREATE TABLE public.employees
(
    user_id smallint NOT NULL,
    first_name character varying(35) NOT NULL,
    last_name character varying(35) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.employers
(
    user_id smallint NOT NULL,
    company_name character varying(100) NOT NULL,
    phone_number character varying(15) NOT NULL,
    admin_confirm boolean NOT NULL,
    website character varying(50) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.experiences
(
    experience_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    cv_id smallint NOT NULL,
    workplace_name character varying(100) NOT NULL,
    job_title character varying(50) NOT NULL,
    start_date date NOT NULL,
    departure_date date,
    PRIMARY KEY (experience_id)
);

CREATE TABLE public.job_advertisements
(
    advertisement_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    position_id smallint NOT NULL,
    city_id smallint NOT NULL,
    job_definition text NOT NULL,
    min_salary numeric(10, 2),
    max_salary numeric(10, 2),
    number_of_open_positions smallint NOT NULL,
    created_date date NOT NULL,
    expiration_date date,
    employer_id smallint NOT NULL,
    is_active boolean NOT NULL,
    expiration_dated date,
    PRIMARY KEY (advertisement_id)
);

CREATE TABLE public.job_positions
(
    position_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    position_name character varying(30) NOT NULL,
    position_description text,
    PRIMARY KEY (position_id)
);

CREATE TABLE public.photos
(
    photo_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    cv_id smallint NOT NULL,
    title character varying(50) NOT NULL,
    image character varying(255) NOT NULL,
    created_date date NOT NULL,
    PRIMARY KEY (photo_id)
);

CREATE TABLE public.skills
(
    skill_id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    cv_id smallint NOT NULL,
    skill character varying(50) NOT NULL,
    skill_level smallint,
    PRIMARY KEY (skill_id)
);

CREATE TABLE public.users
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
    email character varying(50) NOT NULL,
    password character varying(500) NOT NULL,
    register_date timestamp with time zone NOT NULL,
    activation_code character varying(500) NOT NULL,
    email_comfirm boolean NOT NULL,
    is_active boolean NOT NULL,
    is_deleted boolean NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.candidates
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.cities
    ADD FOREIGN KEY (city_id)
    REFERENCES public.cities (city_id)
    NOT VALID;


ALTER TABLE public.cv_languages
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.cvs
    ADD FOREIGN KEY (user_id)
    REFERENCES public.candidates (user_id)
    NOT VALID;


ALTER TABLE public.educations
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.employees
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.employers
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.experiences
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.job_advertisements
    ADD FOREIGN KEY (city_id)
    REFERENCES public.cities (city_id)
    NOT VALID;


ALTER TABLE public.job_advertisements
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.employers (user_id)
    NOT VALID;


ALTER TABLE public.job_advertisements
    ADD FOREIGN KEY (position_id)
    REFERENCES public.job_positions (position_id)
    NOT VALID;


ALTER TABLE public.photos
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.skills
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;

END;