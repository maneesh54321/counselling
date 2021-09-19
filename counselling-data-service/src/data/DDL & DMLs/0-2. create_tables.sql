---------------------------------------------------------------------------------------
----------------------------------Table: public.quota----------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.quota
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT quota_pkey PRIMARY KEY (id),
    CONSTRAINT quota_unique UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.quota OWNER to postgres;

---------------------------------------------------------------------------------------
----------------------------------Table: public.gender---------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.gender
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT gender_pkey PRIMARY KEY (id),
    CONSTRAINT gender_unique UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.gender OWNER to postgres;


---------------------------------------------------------------------------------------
--------------------------------Table: public.category---------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.category
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT category_unique UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.category OWNER to postgres;
	
---------------------------------------------------------------------------------------
------------------------------Table: public.college_type-------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.college_type
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT college_type_pkey PRIMARY KEY (id),
    CONSTRAINT college_type_unique UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.college_type OWNER to postgres;
	
---------------------------------------------------------------------------------------
------------------------------Table: public.rank_type-------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.rank_type
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT rank_type_pkey PRIMARY KEY (id),
    CONSTRAINT rank_type_uniqe UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.rank_type OWNER to postgres;


---------------------------------------------------------------------------------------
----------------------------------Table: public.city-----------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.city
(
    city_id integer NOT NULL,
    display_name character varying(255) NOT NULL COLLATE pg_catalog."default",
    full_name character varying(255) NOT NULL COLLATE pg_catalog."default",
    is_default boolean NOT NULL,
    CONSTRAINT city_pkey PRIMARY KEY (city_id),
    CONSTRAINT city_uniqe UNIQUE (full_name)
)

TABLESPACE pg_default;

ALTER TABLE public.city OWNER to postgres;

---------------------------------------------------------------------------------------
-------------------------------Table: public.branch_tag--------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.branch_tag
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT branch_tag_pkey PRIMARY KEY (id),
    CONSTRAINT branch_tag_uniqe UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.branch_tag OWNER to postgres;
	
---------------------------------------------------------------------------------------
--------------------------------Table: public.college----------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.college
(
    id integer NOT NULL,
    name character varying(255) NOT NULL COLLATE pg_catalog."default",
    college_type_id integer NOT NULL,
    CONSTRAINT college_pkey PRIMARY KEY (id),
    CONSTRAINT college_unique UNIQUE (name),
    CONSTRAINT college_type_fk FOREIGN KEY (college_type_id) REFERENCES public.college_type (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.college OWNER to postgres;
	

---------------------------------------------------------------------------------------
------------------------------Table: public.branch-------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.branch
(
    id integer NOT NULL,
    duration integer NOT NULL,
    name character varying(255) NOT NULL COLLATE pg_catalog."default" NOT NULL,
    branch_tag_id integer NOT NULL,
    CONSTRAINT branch_pkey PRIMARY KEY (id),
    CONSTRAINT branch_tag_id_fk FOREIGN KEY (branch_tag_id) REFERENCES public.branch_tag (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.branch OWNER to postgres;


---------------------------------------------------------------------------------------
------------------------------Table: public.college_branch-----------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.college_branch
(
    college_branch_id integer NOT NULL,
    branch_id integer NOT NULL,
    college_id integer NOT NULL,
    CONSTRAINT college_branch_pkey PRIMARY KEY (college_branch_id),
    CONSTRAINT branch_id_fk FOREIGN KEY (branch_id)  REFERENCES public.branch (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT college_id_fk FOREIGN KEY (college_id) REFERENCES public.college (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.college_branch OWNER to postgres;


---------------------------------------------------------------------------------------
-------------------------------Table: public.placement---------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.placement
(
    id integer NOT NULL,
    average_package integer,
    max_package integer,
    min_package integer,
    student_higher_study_percentage numeric(19,2),
    student_placed_percentage numeric(19,2),
    total_students integer,
    ug_or_pg character varying(255) COLLATE pg_catalog."default",
    year integer NOT NULL,
    college_id integer,
    CONSTRAINT placement_pkey PRIMARY KEY (id),
    CONSTRAINT college_id_fk FOREIGN KEY (college_id) REFERENCES public.college (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.placement  OWNER to postgres;
	
---------------------------------------------------------------------------------------
------------------------------Table: public.rank---------------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.rank
(
    id integer NOT NULL,
    closing_rank integer NOT NULL,
    open_rank integer NOT NULL,
    year integer NOT NULL,
    category_id integer NOT NULL,
    college_branch_id integer NOT NULL,
    gender_id integer NOT NULL,
    quota_id integer NOT NULL,
    rank_type_id integer NOT NULL,
    CONSTRAINT rank_pkey PRIMARY KEY (id),
    CONSTRAINT rank_type_id_fk FOREIGN KEY (rank_type_id) REFERENCES public.rank_type (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT gender_id_fk FOREIGN KEY (gender_id) REFERENCES public.gender (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES public.category (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT college_branch_id_fk FOREIGN KEY (college_branch_id) REFERENCES public.college_branch (college_branch_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT quota_id_fk FOREIGN KEY (quota_id) REFERENCES public.quota (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.rank OWNER to postgres;

---------------------------------------------------------------------------------------
------------------------------Table: public.distance_mapping---------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.distance_mapping
(
    distance_mapping_id integer NOT NULL,
    distance integer NOT NULL,
    city_id integer NOT NULL,
    college_id integer NOT NULL,
    CONSTRAINT distance_mapping_pkey PRIMARY KEY (distance_mapping_id),
    CONSTRAINT city_id_fk FOREIGN KEY (city_id) REFERENCES public.city (city_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT college_id_fk FOREIGN KEY (college_id) REFERENCES public.college (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.distance_mapping OWNER to postgres;
	

---------------------------------------------------------------------------------------
------------------------------Table: public.access_tracker-------------------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.access_tracker
(
    id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    ip character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT access_tracker_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.access_tracker OWNER to postgres;


---------------------------------------------------------------------------------------
------------------------------Table: public.video----------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.video
(
    id integer NOT NULL,
    active boolean NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    uri character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT video_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.video OWNER to postgres;

---------------------------------------------------------------------------------------
------------------------------Table: public.counselling_data_file----------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.counselling_data_file
(
    id integer NOT NULL,
    content bytea NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT counselling_data_file_pkey PRIMARY KEY (id),
    CONSTRAINT data_file_unique UNIQUE (description)
)

TABLESPACE pg_default;

ALTER TABLE public.counselling_data_file OWNER to postgres;

---------------------------------------------------------------------------------------
------------------------------Table: public.summary_data----------------------
---------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS public.summary_data
(
    id integer NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    disclaimer character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT summary_data_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.summary_data OWNER to postgres;

