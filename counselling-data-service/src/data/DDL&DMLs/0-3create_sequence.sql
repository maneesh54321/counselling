-- SEQUENCE: public.access_id_seq

-- DROP SEQUENCE public.access_id_seq;

CREATE SEQUENCE public.access_id_seq
    INCREMENT 5
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.access_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.branch_id_seq

-- DROP SEQUENCE public.branch_id_seq;

CREATE SEQUENCE public.branch_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.branch_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.branch_tag_seq

-- DROP SEQUENCE public.branch_tag_seq;

CREATE SEQUENCE public.branch_tag_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.branch_tag_seq
    OWNER TO postgres;
	
	-- SEQUENCE: public.category_seq

-- DROP SEQUENCE public.category_seq;

CREATE SEQUENCE public.category_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.category_seq
    OWNER TO postgres;

-- SEQUENCE: public.city_id_seq

-- DROP SEQUENCE public.city_id_seq;

CREATE SEQUENCE public.city_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.city_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.college_branch_id_seq

-- DROP SEQUENCE public.college_branch_id_seq;

CREATE SEQUENCE public.college_branch_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.college_branch_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.college_id_seq

-- DROP SEQUENCE public.college_id_seq;

CREATE SEQUENCE public.college_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.college_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.college_type_id_seq

-- DROP SEQUENCE public.college_type_id_seq;

CREATE SEQUENCE public.college_type_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.college_type_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.counselling_data_file_id_seq

-- DROP SEQUENCE public.counselling_data_file_id_seq;

CREATE SEQUENCE public.counselling_data_file_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.counselling_data_file_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.disclaimer_id_seq

-- DROP SEQUENCE public.disclaimer_id_seq;

CREATE SEQUENCE public.disclaimer_id_seq
    INCREMENT 1
    START 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.disclaimer_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.distance_mapping_id_seq

-- DROP SEQUENCE public.distance_mapping_id_seq;

CREATE SEQUENCE public.distance_mapping_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.distance_mapping_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.gender_seq

-- DROP SEQUENCE public.gender_seq;

CREATE SEQUENCE public.gender_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.gender_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.placement_seq

-- DROP SEQUENCE public.placement_seq;

CREATE SEQUENCE public.placement_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.placement_seq
    OWNER TO postgres;

-- SEQUENCE: public.quota_seq

-- DROP SEQUENCE public.quota_seq;

CREATE SEQUENCE public.quota_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.quota_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.rank_id_seq

-- DROP SEQUENCE public.rank_id_seq;

CREATE SEQUENCE public.rank_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.rank_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.rank_type_seq

-- DROP SEQUENCE public.rank_type_seq;

CREATE SEQUENCE public.rank_type_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.rank_type_seq
    OWNER TO postgres;

-- SEQUENCE: public.summary_id_seq

-- DROP SEQUENCE public.summary_id_seq;

CREATE SEQUENCE public.summary_id_seq
    INCREMENT 1
    START 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.summary_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.video_id_seq

-- DROP SEQUENCE public.video_id_seq;

CREATE SEQUENCE public.video_id_seq
    INCREMENT 1
    START 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.video_id_seq
    OWNER TO postgres;