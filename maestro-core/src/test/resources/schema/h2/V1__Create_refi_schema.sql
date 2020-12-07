--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: pg_trgm; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pg_trgm WITH SCHEMA public;


--
-- Name: EXTENSION pg_trgm; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION pg_trgm IS 'text similarity measurement and index searching based on trigrams';


--
-- Name: tablefunc; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS tablefunc WITH SCHEMA public;


--
-- Name: EXTENSION tablefunc; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION tablefunc IS 'functions that manipulate whole tables, including crosstab';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: active_storage_attachments; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.active_storage_attachments (
                                                   id bigint NOT NULL,
                                                   name character varying NOT NULL,
                                                   record_type character varying NOT NULL,
                                                   record_id bigint NOT NULL,
                                                   blob_id bigint NOT NULL,
                                                   created_at timestamp without time zone NOT NULL
);


ALTER TABLE public.active_storage_attachments OWNER TO josiah;

--
-- Name: active_storage_attachments_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.active_storage_attachments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.active_storage_attachments_id_seq OWNER TO josiah;

--
-- Name: active_storage_attachments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.active_storage_attachments_id_seq OWNED BY public.active_storage_attachments.id;


--
-- Name: active_storage_blobs; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.active_storage_blobs (
                                             id bigint NOT NULL,
                                             key character varying NOT NULL,
                                             filename character varying NOT NULL,
                                             content_type character varying,
                                             metadata text,
                                             byte_size bigint NOT NULL,
                                             checksum character varying NOT NULL,
                                             created_at timestamp without time zone NOT NULL
);


ALTER TABLE public.active_storage_blobs OWNER TO josiah;

--
-- Name: active_storage_blobs_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.active_storage_blobs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.active_storage_blobs_id_seq OWNER TO josiah;

--
-- Name: active_storage_blobs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.active_storage_blobs_id_seq OWNED BY public.active_storage_blobs.id;


--
-- Name: addresses; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.addresses (
                                  id integer NOT NULL,
                                  street_address_1 character varying NOT NULL,
                                  street_address_2 character varying,
                                  city character varying NOT NULL,
                                  state character varying(3) NOT NULL,
                                  zip character varying(10) NOT NULL,
                                  customer_id integer NOT NULL,
                                  created_at timestamp without time zone NOT NULL,
                                  updated_at timestamp without time zone NOT NULL,
                                  residency_status character varying,
                                  county character varying,
                                  years integer,
                                  months integer,
                                  rent_status character varying,
                                  monthly_rent integer,
                                  loan_application_id integer
);


ALTER TABLE public.addresses OWNER TO josiah;

--
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.addresses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.addresses_id_seq OWNER TO josiah;

--
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.addresses_id_seq OWNED BY public.addresses.id;


--
-- Name: admin_sessions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.admin_sessions (
                                       id integer NOT NULL,
                                       user_id integer NOT NULL,
                                       uuid character varying NOT NULL,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.admin_sessions OWNER TO josiah;

--
-- Name: admin_sessions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.admin_sessions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_sessions_id_seq OWNER TO josiah;

--
-- Name: admin_sessions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.admin_sessions_id_seq OWNED BY public.admin_sessions.id;


--
-- Name: ads_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ads_requests (
                                     id bigint NOT NULL,
                                     body text,
                                     response_code character varying,
                                     raw_response text,
                                     loan_application_id integer NOT NULL,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.ads_requests OWNER TO josiah;

--
-- Name: ads_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ads_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ads_requests_id_seq OWNER TO josiah;

--
-- Name: ads_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ads_requests_id_seq OWNED BY public.ads_requests.id;


--
-- Name: ads_vehicle_service_contract_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ads_vehicle_service_contract_documents (
                                                               id bigint NOT NULL,
                                                               ads_vehicle_service_contract_id integer,
                                                               contract_identifier character varying,
                                                               encoded_data text,
                                                               created_at timestamp without time zone NOT NULL,
                                                               updated_at timestamp without time zone NOT NULL,
                                                               contract_data jsonb DEFAULT '{}'::jsonb
);


ALTER TABLE public.ads_vehicle_service_contract_documents OWNER TO josiah;

--
-- Name: ads_vehicle_service_contract_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ads_vehicle_service_contract_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ads_vehicle_service_contract_documents_id_seq OWNER TO josiah;

--
-- Name: ads_vehicle_service_contract_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ads_vehicle_service_contract_documents_id_seq OWNED BY public.ads_vehicle_service_contract_documents.id;


--
-- Name: ads_vehicle_service_contracts; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ads_vehicle_service_contracts (
                                                      id bigint NOT NULL,
                                                      loan_application_id bigint,
                                                      provider character varying NOT NULL,
                                                      name character varying NOT NULL,
                                                      description character varying NOT NULL,
                                                      product_code character varying NOT NULL,
                                                      product_id character varying NOT NULL,
                                                      aasm_state character varying,
                                                      months integer,
                                                      mileage integer,
                                                      wholesale_cost numeric(6,2),
                                                      suggested_retail_cost numeric(6,2),
                                                      contract_identifier character varying,
                                                      product_data jsonb DEFAULT '{}'::jsonb,
                                                      created_at timestamp without time zone NOT NULL,
                                                      updated_at timestamp without time zone NOT NULL,
                                                      sold_by integer,
                                                      vehicle_data jsonb DEFAULT '{}'::jsonb
);


ALTER TABLE public.ads_vehicle_service_contracts OWNER TO josiah;

--
-- Name: ads_vehicle_service_contracts_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ads_vehicle_service_contracts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ads_vehicle_service_contracts_id_seq OWNER TO josiah;

--
-- Name: ads_vehicle_service_contracts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ads_vehicle_service_contracts_id_seq OWNED BY public.ads_vehicle_service_contracts.id;


--
-- Name: ahoy_events; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ahoy_events (
                                    id bigint NOT NULL,
                                    visit_id bigint,
                                    user_id bigint,
                                    name character varying,
                                    properties jsonb,
                                    "time" timestamp without time zone
);


ALTER TABLE public.ahoy_events OWNER TO josiah;

--
-- Name: ahoy_events_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ahoy_events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ahoy_events_id_seq OWNER TO josiah;

--
-- Name: ahoy_events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ahoy_events_id_seq OWNED BY public.ahoy_events.id;


--
-- Name: ahoy_visits; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ahoy_visits (
                                    id bigint NOT NULL,
                                    visit_token character varying,
                                    visitor_token character varying,
                                    user_id bigint,
                                    ip character varying,
                                    user_agent text,
                                    referrer text,
                                    referring_domain character varying,
                                    search_keyword character varying,
                                    landing_page text,
                                    browser character varying,
                                    os character varying,
                                    device_type character varying,
                                    country character varying,
                                    region character varying,
                                    city character varying,
                                    utm_source character varying,
                                    utm_medium character varying,
                                    utm_term character varying,
                                    utm_content character varying,
                                    utm_campaign character varying,
                                    started_at timestamp without time zone
);


ALTER TABLE public.ahoy_visits OWNER TO josiah;

--
-- Name: ahoy_visits_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ahoy_visits_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ahoy_visits_id_seq OWNER TO josiah;

--
-- Name: ahoy_visits_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ahoy_visits_id_seq OWNED BY public.ahoy_visits.id;


--
-- Name: alloy_evaluations; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.alloy_evaluations (
                                          id integer NOT NULL,
                                          customer_id integer NOT NULL,
                                          loan_application_id integer NOT NULL,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL,
                                          encrypted_raw_response_body text,
                                          encrypted_raw_response_body_iv character varying
);


ALTER TABLE public.alloy_evaluations OWNER TO josiah;

--
-- Name: alloy_evaluations_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.alloy_evaluations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alloy_evaluations_id_seq OWNER TO josiah;

--
-- Name: alloy_evaluations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.alloy_evaluations_id_seq OWNED BY public.alloy_evaluations.id;


--
-- Name: api_request_cache; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.api_request_cache (
                                          id bigint NOT NULL,
                                          request_name character varying NOT NULL,
                                          request_params character varying,
                                          response text,
                                          ttl integer,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.api_request_cache OWNER TO josiah;

--
-- Name: api_request_cache_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.api_request_cache_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.api_request_cache_id_seq OWNER TO josiah;

--
-- Name: api_request_cache_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.api_request_cache_id_seq OWNED BY public.api_request_cache.id;


--
-- Name: ar_internal_metadata; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ar_internal_metadata (
                                             key character varying NOT NULL,
                                             value character varying,
                                             created_at timestamp without time zone NOT NULL,
                                             updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.ar_internal_metadata OWNER TO josiah;

--
-- Name: authentication_tokens; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.authentication_tokens (
                                              id bigint NOT NULL,
                                              loan_application_id bigint,
                                              token character varying NOT NULL,
                                              sent_to_phone_number character varying,
                                              expires_at timestamp without time zone NOT NULL,
                                              converted_at timestamp without time zone,
                                              created_at timestamp without time zone NOT NULL,
                                              updated_at timestamp without time zone NOT NULL,
                                              context character varying NOT NULL,
                                              authenticated_type character varying NOT NULL,
                                              authenticated_id integer NOT NULL
);


ALTER TABLE public.authentication_tokens OWNER TO josiah;

--
-- Name: authentication_tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.authentication_tokens_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authentication_tokens_id_seq OWNER TO josiah;

--
-- Name: authentication_tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.authentication_tokens_id_seq OWNED BY public.authentication_tokens.id;


--
-- Name: auto_loan_selections; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.auto_loan_selections (
                                             id bigint NOT NULL,
                                             credit_pull_id bigint NOT NULL,
                                             loan_application_id bigint NOT NULL,
                                             selected_loan_hash character varying,
                                             created_at timestamp without time zone NOT NULL,
                                             updated_at timestamp without time zone NOT NULL,
                                             no_auto_loan boolean DEFAULT false
);


ALTER TABLE public.auto_loan_selections OWNER TO josiah;

--
-- Name: auto_loan_selections_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.auto_loan_selections_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auto_loan_selections_id_seq OWNER TO josiah;

--
-- Name: auto_loan_selections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.auto_loan_selections_id_seq OWNED BY public.auto_loan_selections.id;


--
-- Name: autoconfiado_customers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.autoconfiado_customers (
                                               id bigint NOT NULL,
                                               customer_id bigint NOT NULL,
                                               created_at timestamp without time zone NOT NULL,
                                               updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.autoconfiado_customers OWNER TO josiah;

--
-- Name: autoconfiado_customers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.autoconfiado_customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.autoconfiado_customers_id_seq OWNER TO josiah;

--
-- Name: autoconfiado_customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.autoconfiado_customers_id_seq OWNED BY public.autoconfiado_customers.id;


--
-- Name: careers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.careers (
                                id bigint NOT NULL,
                                key character varying,
                                short_name character varying,
                                long_name character varying,
                                team character varying,
                                role_description text,
                                active boolean,
                                responsibilities character varying[] DEFAULT '{}'::character varying[],
                                requirements character varying[] DEFAULT '{}'::character varying[],
                                bonus_points character varying[] DEFAULT '{}'::character varying[],
                                created_at timestamp without time zone NOT NULL,
                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.careers OWNER TO josiah;

--
-- Name: careers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.careers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.careers_id_seq OWNER TO josiah;

--
-- Name: careers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.careers_id_seq OWNED BY public.careers.id;


--
-- Name: caregard_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.caregard_requests (
                                          id bigint NOT NULL,
                                          path character varying,
                                          parameters text,
                                          body text,
                                          response_code character varying,
                                          raw_response text,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL,
                                          loan_application_id integer
);


ALTER TABLE public.caregard_requests OWNER TO josiah;

--
-- Name: caregard_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.caregard_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.caregard_requests_id_seq OWNER TO josiah;

--
-- Name: caregard_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.caregard_requests_id_seq OWNED BY public.caregard_requests.id;


--
-- Name: carfax_responses; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.carfax_responses (
                                         id integer NOT NULL,
                                         license_plate_number character varying NOT NULL,
                                         license_plate_state character varying NOT NULL,
                                         raw_response text NOT NULL,
                                         match_found boolean DEFAULT false,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.carfax_responses OWNER TO josiah;

--
-- Name: carfax_responses_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.carfax_responses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.carfax_responses_id_seq OWNER TO josiah;

--
-- Name: carfax_responses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.carfax_responses_id_seq OWNED BY public.carfax_responses.id;


--
-- Name: contact_messages; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.contact_messages (
                                         id integer NOT NULL,
                                         email character varying,
                                         message_content character varying,
                                         domain character varying NOT NULL,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.contact_messages OWNER TO josiah;

--
-- Name: contact_messages_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.contact_messages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_messages_id_seq OWNER TO josiah;

--
-- Name: contact_messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.contact_messages_id_seq OWNED BY public.contact_messages.id;


--
-- Name: contact_preferences; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.contact_preferences (
                                            id bigint NOT NULL,
                                            customer_id bigint NOT NULL,
                                            receives_promotional boolean DEFAULT false NOT NULL,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.contact_preferences OWNER TO josiah;

--
-- Name: contact_preferences_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.contact_preferences_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_preferences_id_seq OWNER TO josiah;

--
-- Name: contact_preferences_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.contact_preferences_id_seq OWNED BY public.contact_preferences.id;


--
-- Name: corporate_codes; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.corporate_codes (
                                        id bigint NOT NULL,
                                        lender_id bigint,
                                        code character varying NOT NULL,
                                        state character varying NOT NULL,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.corporate_codes OWNER TO josiah;

--
-- Name: corporate_codes_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.corporate_codes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.corporate_codes_id_seq OWNER TO josiah;

--
-- Name: corporate_codes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.corporate_codes_id_seq OWNED BY public.corporate_codes.id;


--
-- Name: cosmetic_package_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.cosmetic_package_documents (
                                                   id bigint NOT NULL,
                                                   cosmetic_package_id integer NOT NULL,
                                                   contract_identifier character varying,
                                                   encoded_data text,
                                                   contract_data jsonb DEFAULT '{}'::jsonb,
                                                   created_at timestamp without time zone NOT NULL,
                                                   updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.cosmetic_package_documents OWNER TO josiah;

--
-- Name: cosmetic_package_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.cosmetic_package_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cosmetic_package_documents_id_seq OWNER TO josiah;

--
-- Name: cosmetic_package_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.cosmetic_package_documents_id_seq OWNED BY public.cosmetic_package_documents.id;


--
-- Name: cosmetic_packages; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.cosmetic_packages (
                                          id bigint NOT NULL,
                                          loan_application_id bigint NOT NULL,
                                          provider character varying NOT NULL,
                                          name character varying NOT NULL,
                                          description character varying NOT NULL,
                                          product_code character varying NOT NULL,
                                          product_id character varying NOT NULL,
                                          aasm_state character varying,
                                          months integer,
                                          mileage integer,
                                          wholesale_cost numeric(6,2),
                                          suggested_retail_cost numeric(6,2),
                                          contract_identifier character varying,
                                          product_class integer NOT NULL,
                                          product_data jsonb DEFAULT '{}'::jsonb,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL,
                                          sold_by integer
);


ALTER TABLE public.cosmetic_packages OWNER TO josiah;

--
-- Name: cosmetic_packages_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.cosmetic_packages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cosmetic_packages_id_seq OWNER TO josiah;

--
-- Name: cosmetic_packages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.cosmetic_packages_id_seq OWNED BY public.cosmetic_packages.id;


--
-- Name: cpe_evaluation_profiles; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.cpe_evaluation_profiles (
                                                id bigint NOT NULL,
                                                credit_policy_entry_id bigint NOT NULL,
                                                loan_application_id bigint NOT NULL,
                                                customer_id bigint NOT NULL,
                                                profile text DEFAULT ''::text,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.cpe_evaluation_profiles OWNER TO josiah;

--
-- Name: cpe_evaluation_profiles_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.cpe_evaluation_profiles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cpe_evaluation_profiles_id_seq OWNER TO josiah;

--
-- Name: cpe_evaluation_profiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.cpe_evaluation_profiles_id_seq OWNED BY public.cpe_evaluation_profiles.id;


--
-- Name: credit_policies; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.credit_policies (
                                        id bigint NOT NULL,
                                        lender_id bigint NOT NULL,
                                        aasm_state character varying NOT NULL,
                                        effective_at timestamp without time zone,
                                        ends_at timestamp without time zone,
                                        monthly_quota integer,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL,
                                        hard_cut_criteria jsonb,
                                        ineligible_vehicle_makes jsonb,
                                        notes text,
                                        secondary_hard_cut_criteria jsonb,
                                        manual_approval boolean DEFAULT false NOT NULL
);


ALTER TABLE public.credit_policies OWNER TO josiah;

--
-- Name: credit_policies_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.credit_policies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_policies_id_seq OWNER TO josiah;

--
-- Name: credit_policies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.credit_policies_id_seq OWNED BY public.credit_policies.id;


--
-- Name: credit_policy_entries; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.credit_policy_entries (
                                              id integer NOT NULL,
                                              minimum_credit_score integer NOT NULL,
                                              maximum_mileage integer NOT NULL,
                                              minimum_year integer NOT NULL,
                                              term integer NOT NULL,
                                              rate numeric(5,3) NOT NULL,
                                              additional_criteria text NOT NULL,
                                              created_at timestamp without time zone NOT NULL,
                                              updated_at timestamp without time zone NOT NULL,
                                              maximum_vehicle_age integer DEFAULT 100,
                                              minimum_loan_value integer DEFAULT 0,
                                              minimum_customer_age integer DEFAULT 18,
                                              state character varying,
                                              credit_policy_id bigint NOT NULL
);


ALTER TABLE public.credit_policy_entries OWNER TO josiah;

--
-- Name: credit_policy_entries_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.credit_policy_entries_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_policy_entries_id_seq OWNER TO josiah;

--
-- Name: credit_policy_entries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.credit_policy_entries_id_seq OWNED BY public.credit_policy_entries.id;


--
-- Name: credit_policy_exception_definitions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.credit_policy_exception_definitions (
                                                            id bigint NOT NULL,
                                                            aasm_state character varying,
                                                            effective_at timestamp without time zone,
                                                            ends_at timestamp without time zone,
                                                            hard_cut_exception_criteria jsonb,
                                                            hard_cut_conforming_criteria jsonb,
                                                            offer_exception_criteria jsonb,
                                                            offer_conforming_criteria jsonb,
                                                            lender_id bigint NOT NULL,
                                                            created_at timestamp without time zone NOT NULL,
                                                            updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.credit_policy_exception_definitions OWNER TO josiah;

--
-- Name: credit_policy_exception_definitions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.credit_policy_exception_definitions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_policy_exception_definitions_id_seq OWNER TO josiah;

--
-- Name: credit_policy_exception_definitions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.credit_policy_exception_definitions_id_seq OWNED BY public.credit_policy_exception_definitions.id;


--
-- Name: credit_pull_details; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.credit_pull_details (
                                            id integer NOT NULL,
                                            credit_pull_id integer NOT NULL,
                                            expires_at timestamp without time zone NOT NULL,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL,
                                            encrypted_returned_fico_score character varying,
                                            encrypted_returned_fico_score_iv character varying
);


ALTER TABLE public.credit_pull_details OWNER TO josiah;

--
-- Name: credit_pull_details_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.credit_pull_details_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_pull_details_id_seq OWNER TO josiah;

--
-- Name: credit_pull_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.credit_pull_details_id_seq OWNED BY public.credit_pull_details.id;


--
-- Name: credit_pulls; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.credit_pulls (
                                     id integer NOT NULL,
                                     customer_id integer NOT NULL,
                                     hard_pull boolean DEFAULT false NOT NULL,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL,
                                     credit_bureau_name character varying NOT NULL,
                                     encrypted_raw_credit_bureau_response text,
                                     encrypted_raw_credit_bureau_response_iv character varying,
                                     failure_messages text,
                                     encrypted_request_data text,
                                     encrypted_request_data_iv character varying,
                                     encrypted_open_auto_loan_data text,
                                     encrypted_open_auto_loan_data_iv character varying
);


ALTER TABLE public.credit_pulls OWNER TO josiah;

--
-- Name: credit_pulls_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.credit_pulls_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_pulls_id_seq OWNER TO josiah;

--
-- Name: credit_pulls_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.credit_pulls_id_seq OWNED BY public.credit_pulls.id;


--
-- Name: cudl_callbacks; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.cudl_callbacks (
                                       id bigint NOT NULL,
                                       encrypted_payload text NOT NULL,
                                       encrypted_payload_iv character varying NOT NULL,
                                       encrypted_response_payload text,
                                       encrypted_response_payload_iv character varying,
                                       authenticated boolean DEFAULT false NOT NULL,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL,
                                       loan_application_id integer
);


ALTER TABLE public.cudl_callbacks OWNER TO josiah;

--
-- Name: cudl_callbacks_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.cudl_callbacks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cudl_callbacks_id_seq OWNER TO josiah;

--
-- Name: cudl_callbacks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.cudl_callbacks_id_seq OWNED BY public.cudl_callbacks.id;


--
-- Name: cudl_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.cudl_requests (
                                      id bigint NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      path character varying NOT NULL,
                                      response_code character varying NOT NULL,
                                      encrypted_request_body character varying,
                                      encrypted_request_body_iv character varying,
                                      encrypted_raw_response character varying,
                                      encrypted_raw_response_iv character varying,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.cudl_requests OWNER TO josiah;

--
-- Name: cudl_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.cudl_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cudl_requests_id_seq OWNER TO josiah;

--
-- Name: cudl_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.cudl_requests_id_seq OWNED BY public.cudl_requests.id;


--
-- Name: cudl_statuses; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.cudl_statuses (
                                      id bigint NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      external_id character varying NOT NULL,
                                      decision_status character varying,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL,
                                      autodecisioned boolean DEFAULT false NOT NULL
);


ALTER TABLE public.cudl_statuses OWNER TO josiah;

--
-- Name: cudl_statuses_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.cudl_statuses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cudl_statuses_id_seq OWNER TO josiah;

--
-- Name: cudl_statuses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.cudl_statuses_id_seq OWNED BY public.cudl_statuses.id;


--
-- Name: customer_loan_applications; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.customer_loan_applications (
                                                   id integer NOT NULL,
                                                   customer_id integer NOT NULL,
                                                   loan_application_id integer NOT NULL,
                                                   created_at timestamp without time zone NOT NULL,
                                                   updated_at timestamp without time zone NOT NULL,
                                                   role character varying NOT NULL
);


ALTER TABLE public.customer_loan_applications OWNER TO josiah;

--
-- Name: customer_loan_applications_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.customer_loan_applications_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_loan_applications_id_seq OWNER TO josiah;

--
-- Name: customer_loan_applications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.customer_loan_applications_id_seq OWNED BY public.customer_loan_applications.id;


--
-- Name: customer_site_entry_locations; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.customer_site_entry_locations (
                                                      id bigint NOT NULL,
                                                      customer_id bigint,
                                                      url character varying,
                                                      created_at timestamp without time zone NOT NULL,
                                                      updated_at timestamp without time zone NOT NULL,
                                                      utm_record_id bigint
);


ALTER TABLE public.customer_site_entry_locations OWNER TO josiah;

--
-- Name: customer_site_entry_locations_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.customer_site_entry_locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_site_entry_locations_id_seq OWNER TO josiah;

--
-- Name: customer_site_entry_locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.customer_site_entry_locations_id_seq OWNED BY public.customer_site_entry_locations.id;


--
-- Name: customer_vehicles; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.customer_vehicles (
                                          id integer NOT NULL,
                                          customer_id integer NOT NULL,
                                          vehicle_id integer NOT NULL,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.customer_vehicles OWNER TO josiah;

--
-- Name: customer_vehicles_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.customer_vehicles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_vehicles_id_seq OWNER TO josiah;

--
-- Name: customer_vehicles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.customer_vehicles_id_seq OWNED BY public.customer_vehicles.id;


--
-- Name: customers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.customers (
                                  id integer NOT NULL,
                                  first_name character varying NOT NULL,
                                  last_name character varying NOT NULL,
                                  email character varying NOT NULL,
                                  encrypted_ssn character varying,
                                  encrypted_ssn_iv character varying,
                                  birthdate date NOT NULL,
                                  annual_income numeric,
                                  additional_income numeric,
                                  created_at timestamp without time zone NOT NULL,
                                  updated_at timestamp without time zone NOT NULL,
                                  origination_channel character varying,
                                  password_digest character varying,
                                  sign_in_count integer,
                                  current_sign_in_at timestamp without time zone,
                                  current_sign_in_ip character varying,
                                  last_sign_in_at timestamp without time zone,
                                  last_sign_in_ip character varying,
                                  account_created boolean DEFAULT false,
                                  middle_name character varying,
                                  encrypted_drivers_license_number character varying,
                                  encrypted_drivers_license_number_iv character varying,
                                  drivers_license_state character varying(10),
                                  us_citizenship boolean,
                                  legal_first_name character varying,
                                  legal_middle_name character varying,
                                  legal_last_name character varying,
                                  legal_suffix character varying,
                                  marketing_opt_out boolean DEFAULT false,
                                  do_not_call boolean DEFAULT false NOT NULL,
                                  drivers_license_expiration_date date,
                                  drivers_license_issued_date date,
                                  spanish_only boolean DEFAULT false
);


ALTER TABLE public.customers OWNER TO josiah;

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO josiah;

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- Name: debt_cancellation_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.debt_cancellation_documents (
                                                    id bigint NOT NULL,
                                                    debt_cancellation_id bigint,
                                                    contract_identifier character varying,
                                                    authorization_code character varying,
                                                    encoded_data text,
                                                    created_at timestamp without time zone NOT NULL,
                                                    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.debt_cancellation_documents OWNER TO josiah;

--
-- Name: debt_cancellation_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.debt_cancellation_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.debt_cancellation_documents_id_seq OWNER TO josiah;

--
-- Name: debt_cancellation_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.debt_cancellation_documents_id_seq OWNED BY public.debt_cancellation_documents.id;


--
-- Name: debt_cancellations; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.debt_cancellations (
                                           id bigint NOT NULL,
                                           loan_application_id integer NOT NULL,
                                           provider character varying NOT NULL,
                                           name character varying NOT NULL,
                                           description character varying NOT NULL,
                                           product_code character varying NOT NULL,
                                           aasm_state character varying,
                                           months integer,
                                           mileage integer,
                                           wholesale_cost numeric(6,2),
                                           suggested_retail_cost numeric(6,2),
                                           customer_cost numeric(6,2),
                                           contract_identifier character varying,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL,
                                           sold_by integer,
                                           request_id integer NOT NULL
);


ALTER TABLE public.debt_cancellations OWNER TO josiah;

--
-- Name: debt_cancellations_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.debt_cancellations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.debt_cancellations_id_seq OWNER TO josiah;

--
-- Name: debt_cancellations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.debt_cancellations_id_seq OWNED BY public.debt_cancellations.id;


--
-- Name: defi_callbacks; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.defi_callbacks (
                                       id bigint NOT NULL,
                                       encrypted_payload text NOT NULL,
                                       encrypted_payload_iv character varying NOT NULL,
                                       encrypted_response_payload text,
                                       encrypted_response_payload_iv character varying,
                                       authenticated boolean DEFAULT false NOT NULL,
                                       loan_application_id bigint,
                                       external_id character varying,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.defi_callbacks OWNER TO josiah;

--
-- Name: defi_callbacks_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.defi_callbacks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.defi_callbacks_id_seq OWNER TO josiah;

--
-- Name: defi_callbacks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.defi_callbacks_id_seq OWNED BY public.defi_callbacks.id;


--
-- Name: defi_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.defi_requests (
                                      id bigint NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      path character varying NOT NULL,
                                      response_code character varying NOT NULL,
                                      encrypted_request_body character varying,
                                      encrypted_request_body_iv character varying,
                                      encrypted_raw_response character varying,
                                      encrypted_raw_response_iv character varying,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.defi_requests OWNER TO josiah;

--
-- Name: defi_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.defi_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.defi_requests_id_seq OWNER TO josiah;

--
-- Name: defi_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.defi_requests_id_seq OWNED BY public.defi_requests.id;


--
-- Name: defi_statuses; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.defi_statuses (
                                      id bigint NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      external_id character varying NOT NULL,
                                      decision_status character varying,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.defi_statuses OWNER TO josiah;

--
-- Name: defi_statuses_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.defi_statuses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.defi_statuses_id_seq OWNER TO josiah;

--
-- Name: defi_statuses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.defi_statuses_id_seq OWNED BY public.defi_statuses.id;


--
-- Name: direct_mail_batches; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.direct_mail_batches (
                                            id integer NOT NULL,
                                            label character varying NOT NULL,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL,
                                            duplicates_skiplist json,
                                            already_exists_skiplist json,
                                            recipient_count integer,
                                            stats json
);


ALTER TABLE public.direct_mail_batches OWNER TO josiah;

--
-- Name: direct_mail_batches_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.direct_mail_batches_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.direct_mail_batches_id_seq OWNER TO josiah;

--
-- Name: direct_mail_batches_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.direct_mail_batches_id_seq OWNED BY public.direct_mail_batches.id;


--
-- Name: direct_mail_conversions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.direct_mail_conversions (
                                                id integer NOT NULL,
                                                customer_id integer NOT NULL,
                                                direct_mail_recipient_id integer NOT NULL,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL,
                                                manual_correlation boolean DEFAULT false,
                                                label character varying
);


ALTER TABLE public.direct_mail_conversions OWNER TO josiah;

--
-- Name: direct_mail_conversions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.direct_mail_conversions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.direct_mail_conversions_id_seq OWNER TO josiah;

--
-- Name: direct_mail_conversions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.direct_mail_conversions_id_seq OWNED BY public.direct_mail_conversions.id;


--
-- Name: direct_mail_edits; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.direct_mail_edits (
                                          id integer NOT NULL,
                                          direct_mail_recipient_id integer NOT NULL,
                                          key_edited character varying,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.direct_mail_edits OWNER TO josiah;

--
-- Name: direct_mail_edits_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.direct_mail_edits_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.direct_mail_edits_id_seq OWNER TO josiah;

--
-- Name: direct_mail_edits_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.direct_mail_edits_id_seq OWNED BY public.direct_mail_edits.id;


--
-- Name: direct_mail_lookups; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.direct_mail_lookups (
                                            id bigint NOT NULL,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL,
                                            code character varying,
                                            direct_mail_recipient_id bigint
);


ALTER TABLE public.direct_mail_lookups OWNER TO josiah;

--
-- Name: direct_mail_lookups_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.direct_mail_lookups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.direct_mail_lookups_id_seq OWNER TO josiah;

--
-- Name: direct_mail_lookups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.direct_mail_lookups_id_seq OWNED BY public.direct_mail_lookups.id;


--
-- Name: direct_mail_recipients; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.direct_mail_recipients (
                                               id integer NOT NULL,
                                               direct_mail_batch_id integer NOT NULL,
                                               recipient_information json,
                                               created_at timestamp without time zone NOT NULL,
                                               updated_at timestamp without time zone NOT NULL,
                                               lookup_code character varying NOT NULL,
                                               drop_date date,
                                               first_name character varying,
                                               last_name character varying,
                                               street_address_1 character varying,
                                               street_address_2 character varying,
                                               city character varying,
                                               state character varying,
                                               zip character varying,
                                               permid character varying
);


ALTER TABLE public.direct_mail_recipients OWNER TO josiah;

--
-- Name: direct_mail_recipients_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.direct_mail_recipients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.direct_mail_recipients_id_seq OWNER TO josiah;

--
-- Name: direct_mail_recipients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.direct_mail_recipients_id_seq OWNED BY public.direct_mail_recipients.id;


--
-- Name: document_approvals; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.document_approvals (
                                           id integer NOT NULL,
                                           document_id integer NOT NULL,
                                           user_id integer NOT NULL,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.document_approvals OWNER TO josiah;

--
-- Name: document_approvals_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.document_approvals_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.document_approvals_id_seq OWNER TO josiah;

--
-- Name: document_approvals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.document_approvals_id_seq OWNED BY public.document_approvals.id;


--
-- Name: document_rejections; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.document_rejections (
                                            id integer NOT NULL,
                                            document_id integer NOT NULL,
                                            user_id integer,
                                            reason character varying NOT NULL,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL,
                                            by_customer boolean DEFAULT false NOT NULL
);


ALTER TABLE public.document_rejections OWNER TO josiah;

--
-- Name: document_rejections_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.document_rejections_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.document_rejections_id_seq OWNER TO josiah;

--
-- Name: document_rejections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.document_rejections_id_seq OWNED BY public.document_rejections.id;


--
-- Name: documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.documents (
                                  id integer NOT NULL,
                                  loan_application_id integer NOT NULL,
                                  created_at timestamp without time zone NOT NULL,
                                  updated_at timestamp without time zone NOT NULL,
                                  lender_document_definition_id integer NOT NULL,
                                  docusign_document_index character varying,
                                  envelope_id integer,
                                  content_type character varying NOT NULL,
                                  original_filename character varying,
                                  customer_id integer NOT NULL
);


ALTER TABLE public.documents OWNER TO josiah;

--
-- Name: documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.documents_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.documents_id_seq OWNER TO josiah;

--
-- Name: documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.documents_id_seq OWNED BY public.documents.id;


--
-- Name: docusign_tab_details; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.docusign_tab_details (
                                             id bigint NOT NULL,
                                             document_id bigint NOT NULL,
                                             raw_data json NOT NULL,
                                             created_at timestamp without time zone NOT NULL,
                                             updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.docusign_tab_details OWNER TO josiah;

--
-- Name: docusign_tab_details_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.docusign_tab_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.docusign_tab_details_id_seq OWNER TO josiah;

--
-- Name: docusign_tab_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.docusign_tab_details_id_seq OWNED BY public.docusign_tab_details.id;


--
-- Name: docusign_templates; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.docusign_templates (
                                           id integer NOT NULL,
                                           docusign_uuid character varying NOT NULL,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL,
                                           lender_document_definition_id integer NOT NULL,
                                           interpolated_fields json NOT NULL
);


ALTER TABLE public.docusign_templates OWNER TO josiah;

--
-- Name: docusign_templates_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.docusign_templates_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.docusign_templates_id_seq OWNER TO josiah;

--
-- Name: docusign_templates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.docusign_templates_id_seq OWNED BY public.docusign_templates.id;


--
-- Name: docusign_webhook_callbacks; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.docusign_webhook_callbacks (
                                                   id integer NOT NULL,
                                                   payload text NOT NULL,
                                                   created_at timestamp without time zone NOT NULL,
                                                   updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.docusign_webhook_callbacks OWNER TO josiah;

--
-- Name: docusign_webhook_callbacks_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.docusign_webhook_callbacks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.docusign_webhook_callbacks_id_seq OWNER TO josiah;

--
-- Name: docusign_webhook_callbacks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.docusign_webhook_callbacks_id_seq OWNED BY public.docusign_webhook_callbacks.id;


--
-- Name: elt_numbers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.elt_numbers (
                                    id bigint NOT NULL,
                                    lender_id bigint NOT NULL,
                                    code character varying NOT NULL,
                                    state character varying NOT NULL,
                                    created_at timestamp without time zone NOT NULL,
                                    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.elt_numbers OWNER TO josiah;

--
-- Name: elt_numbers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.elt_numbers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.elt_numbers_id_seq OWNER TO josiah;

--
-- Name: elt_numbers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.elt_numbers_id_seq OWNED BY public.elt_numbers.id;


--
-- Name: employment_details; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.employment_details (
                                           id bigint NOT NULL,
                                           customer_id bigint,
                                           employer_name character varying NOT NULL,
                                           street_address_1 character varying,
                                           street_address_2 character varying,
                                           city character varying,
                                           state character varying(3),
                                           zip character varying(10),
                                           phone_number character varying,
                                           hire_date date,
                                           departure_date date,
                                           job_title character varying,
                                           manager_name character varying,
                                           monthly_gross_income numeric(8,2),
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.employment_details OWNER TO josiah;

--
-- Name: employment_details_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.employment_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employment_details_id_seq OWNER TO josiah;

--
-- Name: employment_details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.employment_details_id_seq OWNED BY public.employment_details.id;


--
-- Name: envelope_preapprovals; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.envelope_preapprovals (
                                              id bigint NOT NULL,
                                              envelope_id bigint NOT NULL,
                                              user_id bigint NOT NULL,
                                              created_at timestamp without time zone NOT NULL,
                                              updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.envelope_preapprovals OWNER TO josiah;

--
-- Name: envelope_preapprovals_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.envelope_preapprovals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.envelope_preapprovals_id_seq OWNER TO josiah;

--
-- Name: envelope_preapprovals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.envelope_preapprovals_id_seq OWNED BY public.envelope_preapprovals.id;


--
-- Name: envelope_trial_runs; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.envelope_trial_runs (
                                            id bigint NOT NULL,
                                            loan_application_id bigint NOT NULL,
                                            created_by_id bigint NOT NULL,
                                            aasm_state character varying NOT NULL,
                                            envelope_uuid character varying NOT NULL,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.envelope_trial_runs OWNER TO josiah;

--
-- Name: envelope_trial_runs_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.envelope_trial_runs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.envelope_trial_runs_id_seq OWNER TO josiah;

--
-- Name: envelope_trial_runs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.envelope_trial_runs_id_seq OWNED BY public.envelope_trial_runs.id;


--
-- Name: envelopes; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.envelopes (
                                  id integer NOT NULL,
                                  docusign_envelope_uuid character varying NOT NULL,
                                  status character varying,
                                  created_at timestamp without time zone NOT NULL,
                                  updated_at timestamp without time zone NOT NULL,
                                  loan_application_id integer NOT NULL,
                                  purpose character varying NOT NULL
);


ALTER TABLE public.envelopes OWNER TO josiah;

--
-- Name: envelopes_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.envelopes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.envelopes_id_seq OWNER TO josiah;

--
-- Name: envelopes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.envelopes_id_seq OWNED BY public.envelopes.id;


--
-- Name: external_hard_pulls; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.external_hard_pulls (
                                            id bigint NOT NULL,
                                            customer_id bigint NOT NULL,
                                            loan_application_id bigint NOT NULL,
                                            encrypted_credit_score character varying NOT NULL,
                                            encrypted_credit_score_iv character varying NOT NULL,
                                            encrypted_credit_pull_date character varying NOT NULL,
                                            encrypted_credit_pull_date_iv character varying NOT NULL,
                                            encrypted_better_than_pctg character varying NOT NULL,
                                            encrypted_better_than_pctg_iv character varying NOT NULL,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL,
                                            bureau_name character varying,
                                            score_model_upper_bound integer,
                                            score_model_lower_bound integer,
                                            score_model_name character varying,
                                            score_factors jsonb
);


ALTER TABLE public.external_hard_pulls OWNER TO josiah;

--
-- Name: external_hard_pulls_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.external_hard_pulls_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.external_hard_pulls_id_seq OWNER TO josiah;

--
-- Name: external_hard_pulls_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.external_hard_pulls_id_seq OWNED BY public.external_hard_pulls.id;


--
-- Name: external_shipments; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.external_shipments (
                                           id bigint NOT NULL,
                                           loan_application_id bigint,
                                           user_id bigint,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.external_shipments OWNER TO josiah;

--
-- Name: external_shipments_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.external_shipments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.external_shipments_id_seq OWNER TO josiah;

--
-- Name: external_shipments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.external_shipments_id_seq OWNED BY public.external_shipments.id;


--
-- Name: fake_loan_applications; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.fake_loan_applications (
                                               id bigint NOT NULL,
                                               loan_application_id bigint NOT NULL,
                                               created_at timestamp without time zone NOT NULL,
                                               updated_at timestamp without time zone NOT NULL,
                                               targeted_lender_id integer
);


ALTER TABLE public.fake_loan_applications OWNER TO josiah;

--
-- Name: fake_loan_applications_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.fake_loan_applications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fake_loan_applications_id_seq OWNER TO josiah;

--
-- Name: fake_loan_applications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.fake_loan_applications_id_seq OWNED BY public.fake_loan_applications.id;


--
-- Name: faq_questions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.faq_questions (
                                      id bigint NOT NULL,
                                      blob json,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.faq_questions OWNER TO josiah;

--
-- Name: faq_questions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.faq_questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.faq_questions_id_seq OWNER TO josiah;

--
-- Name: faq_questions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.faq_questions_id_seq OWNED BY public.faq_questions.id;


--
-- Name: fedex_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.fedex_requests (
                                       id integer NOT NULL,
                                       address_id integer NOT NULL,
                                       loan_application_id integer NOT NULL,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.fedex_requests OWNER TO josiah;

--
-- Name: fedex_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.fedex_requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fedex_requests_id_seq OWNER TO josiah;

--
-- Name: fedex_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.fedex_requests_id_seq OWNED BY public.fedex_requests.id;


--
-- Name: finastra_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.finastra_requests (
                                          id bigint NOT NULL,
                                          lender_id bigint NOT NULL,
                                          path character varying NOT NULL,
                                          parameters text NOT NULL,
                                          body text NOT NULL,
                                          response_code character varying NOT NULL,
                                          raw_response text NOT NULL,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL,
                                          loan_application_id integer,
                                          encrypted_body character varying,
                                          encrypted_body_iv character varying,
                                          encrypted_raw_response character varying,
                                          encrypted_raw_response_iv character varying
);


ALTER TABLE public.finastra_requests OWNER TO josiah;

--
-- Name: finastra_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.finastra_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.finastra_requests_id_seq OWNER TO josiah;

--
-- Name: finastra_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.finastra_requests_id_seq OWNED BY public.finastra_requests.id;


--
-- Name: hard_cut_evaluation_profiles; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.hard_cut_evaluation_profiles (
                                                     id bigint NOT NULL,
                                                     loan_application_id bigint NOT NULL,
                                                     credit_policy_id bigint NOT NULL,
                                                     customer_id bigint NOT NULL,
                                                     profile text DEFAULT ''::text,
                                                     created_at timestamp without time zone NOT NULL,
                                                     updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.hard_cut_evaluation_profiles OWNER TO josiah;

--
-- Name: hard_cut_evaluation_profiles_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.hard_cut_evaluation_profiles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hard_cut_evaluation_profiles_id_seq OWNER TO josiah;

--
-- Name: hard_cut_evaluation_profiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.hard_cut_evaluation_profiles_id_seq OWNED BY public.hard_cut_evaluation_profiles.id;


--
-- Name: inbound_document_email_attachments; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.inbound_document_email_attachments (
                                                           id bigint NOT NULL,
                                                           inbound_document_email_id bigint NOT NULL,
                                                           filename character varying NOT NULL,
                                                           content_type character varying NOT NULL,
                                                           blob bytea NOT NULL,
                                                           created_at timestamp without time zone NOT NULL,
                                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.inbound_document_email_attachments OWNER TO josiah;

--
-- Name: inbound_document_email_attachments_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.inbound_document_email_attachments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inbound_document_email_attachments_id_seq OWNER TO josiah;

--
-- Name: inbound_document_email_attachments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.inbound_document_email_attachments_id_seq OWNED BY public.inbound_document_email_attachments.id;


--
-- Name: inbound_document_emails; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.inbound_document_emails (
                                                id bigint NOT NULL,
                                                "from" character varying NOT NULL,
                                                subject character varying,
                                                body character varying,
                                                archived_at timestamp without time zone,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.inbound_document_emails OWNER TO josiah;

--
-- Name: inbound_document_emails_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.inbound_document_emails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inbound_document_emails_id_seq OWNER TO josiah;

--
-- Name: inbound_document_emails_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.inbound_document_emails_id_seq OWNED BY public.inbound_document_emails.id;


--
-- Name: inbound_document_mms; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.inbound_document_mms (
                                             id bigint NOT NULL,
                                             params json NOT NULL,
                                             inbound_document_email_id bigint,
                                             created_at timestamp without time zone NOT NULL,
                                             updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.inbound_document_mms OWNER TO josiah;

--
-- Name: inbound_document_mms_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.inbound_document_mms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inbound_document_mms_id_seq OWNER TO josiah;

--
-- Name: inbound_document_mms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.inbound_document_mms_id_seq OWNED BY public.inbound_document_mms.id;


--
-- Name: its_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.its_requests (
                                     id bigint NOT NULL,
                                     vehicle_id bigint,
                                     path character varying,
                                     parameters text,
                                     body text,
                                     response_code character varying,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL,
                                     data_migration_verified boolean
);


ALTER TABLE public.its_requests OWNER TO josiah;

--
-- Name: its_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.its_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.its_requests_id_seq OWNER TO josiah;

--
-- Name: its_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.its_requests_id_seq OWNED BY public.its_requests.id;


--
-- Name: key_replacement_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.key_replacement_documents (
                                                  id bigint NOT NULL,
                                                  key_replacement_id bigint,
                                                  contract_identifier character varying,
                                                  encoded_data text,
                                                  created_at timestamp without time zone NOT NULL,
                                                  updated_at timestamp without time zone NOT NULL,
                                                  contract_data jsonb DEFAULT '{}'::jsonb
);


ALTER TABLE public.key_replacement_documents OWNER TO josiah;

--
-- Name: key_replacement_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.key_replacement_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.key_replacement_documents_id_seq OWNER TO josiah;

--
-- Name: key_replacement_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.key_replacement_documents_id_seq OWNED BY public.key_replacement_documents.id;


--
-- Name: key_replacements; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.key_replacements (
                                         id bigint NOT NULL,
                                         loan_application_id bigint,
                                         provider character varying NOT NULL,
                                         name character varying NOT NULL,
                                         description character varying NOT NULL,
                                         product_code character varying NOT NULL,
                                         product_id character varying NOT NULL,
                                         aasm_state character varying,
                                         months integer,
                                         mileage integer,
                                         wholesale_cost numeric(6,2),
                                         suggested_retail_cost numeric(6,2),
                                         contract_identifier character varying,
                                         product_data jsonb DEFAULT '{}'::jsonb,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL,
                                         sold_by integer
);


ALTER TABLE public.key_replacements OWNER TO josiah;

--
-- Name: key_replacements_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.key_replacements_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.key_replacements_id_seq OWNER TO josiah;

--
-- Name: key_replacements_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.key_replacements_id_seq OWNED BY public.key_replacements.id;


--
-- Name: ldd_approvals; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ldd_approvals (
                                      id bigint NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      lender_document_definition_id bigint NOT NULL,
                                      approved_by_id bigint NOT NULL,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL,
                                      customer_id integer NOT NULL
);


ALTER TABLE public.ldd_approvals OWNER TO josiah;

--
-- Name: ldd_approvals_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ldd_approvals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ldd_approvals_id_seq OWNER TO josiah;

--
-- Name: ldd_approvals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ldd_approvals_id_seq OWNED BY public.ldd_approvals.id;


--
-- Name: ldd_rejections; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ldd_rejections (
                                       id bigint NOT NULL,
                                       loan_application_id bigint NOT NULL,
                                       lender_document_definition_id bigint NOT NULL,
                                       rejected_by_id bigint NOT NULL,
                                       reason character varying NOT NULL,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL,
                                       customer_id integer NOT NULL
);


ALTER TABLE public.ldd_rejections OWNER TO josiah;

--
-- Name: ldd_rejections_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ldd_rejections_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ldd_rejections_id_seq OWNER TO josiah;

--
-- Name: ldd_rejections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ldd_rejections_id_seq OWNED BY public.ldd_rejections.id;


--
-- Name: ldp_overrides; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.ldp_overrides (
                                      id bigint NOT NULL,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      created_by_id bigint NOT NULL,
                                      old_ldp_id bigint NOT NULL,
                                      new_ldp_id bigint NOT NULL
);


ALTER TABLE public.ldp_overrides OWNER TO josiah;

--
-- Name: ldp_overrides_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.ldp_overrides_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ldp_overrides_id_seq OWNER TO josiah;

--
-- Name: ldp_overrides_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.ldp_overrides_id_seq OWNED BY public.ldp_overrides.id;


--
-- Name: legal_terms_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.legal_terms_documents (
                                              id integer NOT NULL,
                                              label character varying NOT NULL,
                                              contents text NOT NULL,
                                              created_at timestamp without time zone NOT NULL,
                                              updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.legal_terms_documents OWNER TO josiah;

--
-- Name: legal_terms_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.legal_terms_documents_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.legal_terms_documents_id_seq OWNER TO josiah;

--
-- Name: legal_terms_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.legal_terms_documents_id_seq OWNED BY public.legal_terms_documents.id;


--
-- Name: lender_addresses; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_addresses (
                                         id bigint NOT NULL,
                                         lender_id bigint,
                                         kind character varying NOT NULL,
                                         street_address character varying NOT NULL,
                                         city character varying NOT NULL,
                                         state character varying NOT NULL,
                                         county character varying NOT NULL,
                                         phone_number character varying NOT NULL,
                                         zip character varying NOT NULL,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL,
                                         name character varying
);


ALTER TABLE public.lender_addresses OWNER TO josiah;

--
-- Name: lender_addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_addresses_id_seq OWNER TO josiah;

--
-- Name: lender_addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_addresses_id_seq OWNED BY public.lender_addresses.id;


--
-- Name: lender_applications; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_applications (
                                            id bigint NOT NULL,
                                            lender_id bigint NOT NULL,
                                            loan_application_id bigint NOT NULL,
                                            customer_identifier character varying NOT NULL,
                                            loan_application_identifier character varying NOT NULL,
                                            transaction_identifier character varying,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL,
                                            aasm_state character varying NOT NULL,
                                            secondary_customer_identifier character varying
);


ALTER TABLE public.lender_applications OWNER TO josiah;

--
-- Name: lender_applications_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_applications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_applications_id_seq OWNER TO josiah;

--
-- Name: lender_applications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_applications_id_seq OWNED BY public.lender_applications.id;


--
-- Name: lender_credit_bureau_credentials; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_credit_bureau_credentials (
                                                         id bigint NOT NULL,
                                                         lender_id bigint NOT NULL,
                                                         credit_bureau character varying NOT NULL,
                                                         encrypted_subscriber_code character varying NOT NULL,
                                                         encrypted_subscriber_code_iv character varying,
                                                         encrypted_password character varying,
                                                         encrypted_password_iv character varying,
                                                         created_at timestamp without time zone NOT NULL,
                                                         updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_credit_bureau_credentials OWNER TO josiah;

--
-- Name: lender_credit_bureau_credentials_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_credit_bureau_credentials_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_credit_bureau_credentials_id_seq OWNER TO josiah;

--
-- Name: lender_credit_bureau_credentials_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_credit_bureau_credentials_id_seq OWNED BY public.lender_credit_bureau_credentials.id;


--
-- Name: lender_document_definitions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_document_definitions (
                                                    id integer NOT NULL,
                                                    lender_document_package_id integer NOT NULL,
                                                    title character varying NOT NULL,
                                                    additional_attributes json NOT NULL,
                                                    document_type character varying NOT NULL,
                                                    created_at timestamp without time zone NOT NULL,
                                                    updated_at timestamp without time zone NOT NULL,
                                                    "position" integer NOT NULL,
                                                    description character varying NOT NULL,
                                                    category_identifier integer,
                                                    exclude_from_zip boolean DEFAULT false NOT NULL
);


ALTER TABLE public.lender_document_definitions OWNER TO josiah;

--
-- Name: lender_document_definitions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_document_definitions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_document_definitions_id_seq OWNER TO josiah;

--
-- Name: lender_document_definitions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_document_definitions_id_seq OWNED BY public.lender_document_definitions.id;


--
-- Name: lender_document_packages; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_document_packages (
                                                 id integer NOT NULL,
                                                 lender_id integer NOT NULL,
                                                 published_at timestamp without time zone,
                                                 created_at timestamp without time zone NOT NULL,
                                                 updated_at timestamp without time zone NOT NULL,
                                                 aasm_state character varying NOT NULL,
                                                 notes text
);


ALTER TABLE public.lender_document_packages OWNER TO josiah;

--
-- Name: lender_document_packages_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_document_packages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_document_packages_id_seq OWNER TO josiah;

--
-- Name: lender_document_packages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_document_packages_id_seq OWNED BY public.lender_document_packages.id;


--
-- Name: lender_eligibility_constraint_failures; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_eligibility_constraint_failures (
                                                               id bigint NOT NULL,
                                                               lender_eligibility_constraint_id bigint NOT NULL,
                                                               customer_id bigint NOT NULL,
                                                               loan_application_id bigint NOT NULL,
                                                               description character varying
);


ALTER TABLE public.lender_eligibility_constraint_failures OWNER TO josiah;

--
-- Name: lender_eligibility_constraint_failures_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_eligibility_constraint_failures_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_eligibility_constraint_failures_id_seq OWNER TO josiah;

--
-- Name: lender_eligibility_constraint_failures_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_eligibility_constraint_failures_id_seq OWNED BY public.lender_eligibility_constraint_failures.id;


--
-- Name: lender_eligibility_constraints; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_eligibility_constraints (
                                                       id bigint NOT NULL,
                                                       type character varying,
                                                       lender_id bigint,
                                                       constraint_data jsonb,
                                                       enabled boolean DEFAULT true,
                                                       created_at timestamp without time zone NOT NULL,
                                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_eligibility_constraints OWNER TO josiah;

--
-- Name: lender_eligibility_constraints_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_eligibility_constraints_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_eligibility_constraints_id_seq OWNER TO josiah;

--
-- Name: lender_eligibility_constraints_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_eligibility_constraints_id_seq OWNED BY public.lender_eligibility_constraints.id;


--
-- Name: lender_preferred_score_models; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_preferred_score_models (
                                                      id bigint NOT NULL,
                                                      lender_id bigint NOT NULL,
                                                      bureau_name character varying NOT NULL,
                                                      score_model_name character varying NOT NULL,
                                                      score_model_rank integer NOT NULL,
                                                      product_code character varying NOT NULL,
                                                      created_at timestamp without time zone NOT NULL,
                                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_preferred_score_models OWNER TO josiah;

--
-- Name: lender_preferred_score_models_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_preferred_score_models_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_preferred_score_models_id_seq OWNER TO josiah;

--
-- Name: lender_preferred_score_models_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_preferred_score_models_id_seq OWNED BY public.lender_preferred_score_models.id;


--
-- Name: lender_product_policies; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_product_policies (
                                                id bigint NOT NULL,
                                                lender_id bigint NOT NULL,
                                                product_criteria jsonb,
                                                active boolean DEFAULT true,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_product_policies OWNER TO josiah;

--
-- Name: lender_product_policies_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_product_policies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_product_policies_id_seq OWNER TO josiah;

--
-- Name: lender_product_policies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_product_policies_id_seq OWNED BY public.lender_product_policies.id;


--
-- Name: lender_state_fees; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_state_fees (
                                          id bigint NOT NULL,
                                          lender_id bigint,
                                          state_fee_id bigint,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_state_fees OWNER TO josiah;

--
-- Name: lender_state_fees_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_state_fees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_state_fees_id_seq OWNER TO josiah;

--
-- Name: lender_state_fees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_state_fees_id_seq OWNED BY public.lender_state_fees.id;


--
-- Name: lender_user_invitations; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_user_invitations (
                                                id bigint NOT NULL,
                                                lender_id bigint NOT NULL,
                                                created_by_id bigint NOT NULL,
                                                first_name character varying NOT NULL,
                                                last_name character varying NOT NULL,
                                                email character varying NOT NULL,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_user_invitations OWNER TO josiah;

--
-- Name: lender_user_invitations_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_user_invitations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_user_invitations_id_seq OWNER TO josiah;

--
-- Name: lender_user_invitations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_user_invitations_id_seq OWNED BY public.lender_user_invitations.id;


--
-- Name: lender_users; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_users (
                                     id integer NOT NULL,
                                     lender_id integer NOT NULL,
                                     user_id integer NOT NULL,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_users OWNER TO josiah;

--
-- Name: lender_users_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_users_id_seq OWNER TO josiah;

--
-- Name: lender_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_users_id_seq OWNED BY public.lender_users.id;


--
-- Name: lender_zip_downloads; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lender_zip_downloads (
                                             id bigint NOT NULL,
                                             downloaded_by_id bigint NOT NULL,
                                             document_id bigint NOT NULL,
                                             created_at timestamp without time zone NOT NULL,
                                             updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lender_zip_downloads OWNER TO josiah;

--
-- Name: lender_zip_downloads_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lender_zip_downloads_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lender_zip_downloads_id_seq OWNER TO josiah;

--
-- Name: lender_zip_downloads_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lender_zip_downloads_id_seq OWNED BY public.lender_zip_downloads.id;


--
-- Name: lenders; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lenders (
                                id integer NOT NULL,
                                name character varying NOT NULL,
                                created_at timestamp without time zone NOT NULL,
                                updated_at timestamp without time zone NOT NULL,
                                street_address character varying,
                                city character varying,
                                state character varying(3),
                                zip character varying(10),
                                primary_phone character varying,
                                county character varying,
                                ein character varying,
                                elt_code character varying,
                                additional_attributes json,
                                template_folder_name character varying NOT NULL,
                                logo_url character varying,
                                referral_url character varying,
                                preferred_credit_bureau character varying,
                                preferred_accrual_method character varying DEFAULT 'actual'::character varying NOT NULL
);


ALTER TABLE public.lenders OWNER TO josiah;

--
-- Name: lenders_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lenders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lenders_id_seq OWNER TO josiah;

--
-- Name: lenders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lenders_id_seq OWNED BY public.lenders.id;


--
-- Name: loan_application_pauses; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loan_application_pauses (
                                                id integer NOT NULL,
                                                loan_application_id integer NOT NULL,
                                                reason character varying NOT NULL,
                                                aasm_state character varying NOT NULL,
                                                additional_attributes json NOT NULL,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL,
                                                prior_loan_application_state character varying NOT NULL
);


ALTER TABLE public.loan_application_pauses OWNER TO josiah;

--
-- Name: loan_application_pauses_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loan_application_pauses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_application_pauses_id_seq OWNER TO josiah;

--
-- Name: loan_application_pauses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loan_application_pauses_id_seq OWNED BY public.loan_application_pauses.id;


--
-- Name: loan_applications; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loan_applications (
                                          id integer NOT NULL,
                                          uuid character varying NOT NULL,
                                          vehicle_id integer NOT NULL,
                                          aasm_state character varying NOT NULL,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL,
                                          loan_amount numeric(8,2) NOT NULL,
                                          claimed_by integer,
                                          estimated_monthly_payment numeric(8,2),
                                          processing_fee numeric(6,2),
                                          lienholder_account_number character varying,
                                          lienholder_name character varying,
                                          processor_user_id integer,
                                          multiple_applicants boolean DEFAULT false NOT NULL,
                                          secondary_borrower_relationship character varying,
                                          contract_executed_at timestamp without time zone,
                                          required_ldd_count integer DEFAULT 0 NOT NULL,
                                          approved_ldd_count integer DEFAULT 0 NOT NULL,
                                          ops_status integer DEFAULT 1000000 NOT NULL,
                                          unanswered_text boolean DEFAULT false,
                                          override_state_requires_title_and_poa boolean,
                                          funder_user_id integer,
                                          auto_closeable boolean DEFAULT true NOT NULL,
                                          last_touched timestamp without time zone,
                                          first_touched timestamp without time zone,
                                          funded_at timestamp without time zone,
                                          payoff_issuance_recipient character varying,
                                          ops_status_updated_at timestamp without time zone,
                                          savings_lmh character varying,
                                          payoff_cashing_date date,
                                          savings_lmh_mps numeric,
                                          savings_lmh_tps numeric,
                                          duration_to_first_touch_in_minutes integer
);


ALTER TABLE public.loan_applications OWNER TO josiah;

--
-- Name: loan_applications_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loan_applications_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_applications_id_seq OWNER TO josiah;

--
-- Name: loan_applications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loan_applications_id_seq OWNED BY public.loan_applications.id;


--
-- Name: loan_archivals; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loan_archivals (
                                       id bigint NOT NULL,
                                       document_id bigint NOT NULL,
                                       archived_by_id bigint NOT NULL,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.loan_archivals OWNER TO josiah;

--
-- Name: loan_archivals_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loan_archivals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_archivals_id_seq OWNER TO josiah;

--
-- Name: loan_archivals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loan_archivals_id_seq OWNED BY public.loan_archivals.id;


--
-- Name: loan_data_captures; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loan_data_captures (
                                           id bigint NOT NULL,
                                           loan_application_id bigint NOT NULL,
                                           data json NOT NULL,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.loan_data_captures OWNER TO josiah;

--
-- Name: loan_data_captures_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loan_data_captures_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_data_captures_id_seq OWNER TO josiah;

--
-- Name: loan_data_captures_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loan_data_captures_id_seq OWNED BY public.loan_data_captures.id;


--
-- Name: loan_exemptions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loan_exemptions (
                                        id bigint NOT NULL,
                                        loan_application_id bigint NOT NULL,
                                        user_id bigint NOT NULL,
                                        loan_amount numeric,
                                        vehicle_value numeric,
                                        annual_income numeric,
                                        notes text NOT NULL,
                                        enabled boolean DEFAULT true NOT NULL,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL,
                                        bypass_hard_cuts boolean DEFAULT false
);


ALTER TABLE public.loan_exemptions OWNER TO josiah;

--
-- Name: loan_exemptions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loan_exemptions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_exemptions_id_seq OWNER TO josiah;

--
-- Name: loan_exemptions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loan_exemptions_id_seq OWNED BY public.loan_exemptions.id;


--
-- Name: loan_referrals; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loan_referrals (
                                       id bigint NOT NULL,
                                       loan_application_id bigint NOT NULL,
                                       referral_code_id bigint NOT NULL,
                                       aasm_state character varying NOT NULL,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.loan_referrals OWNER TO josiah;

--
-- Name: loan_referrals_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loan_referrals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loan_referrals_id_seq OWNER TO josiah;

--
-- Name: loan_referrals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loan_referrals_id_seq OWNED BY public.loan_referrals.id;


--
-- Name: loanspq_callbacks; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loanspq_callbacks (
                                          id bigint NOT NULL,
                                          created_at timestamp without time zone NOT NULL,
                                          updated_at timestamp without time zone NOT NULL,
                                          encrypted_payload text,
                                          encrypted_payload_iv character varying,
                                          external_id integer,
                                          loan_application_id integer
);


ALTER TABLE public.loanspq_callbacks OWNER TO josiah;

--
-- Name: loanspq_callbacks_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loanspq_callbacks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loanspq_callbacks_id_seq OWNER TO josiah;

--
-- Name: loanspq_callbacks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loanspq_callbacks_id_seq OWNED BY public.loanspq_callbacks.id;


--
-- Name: loanspq_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loanspq_requests (
                                         id bigint NOT NULL,
                                         loan_application_id bigint NOT NULL,
                                         path character varying NOT NULL,
                                         response_code character varying NOT NULL,
                                         encrypted_request_body character varying,
                                         encrypted_request_body_iv character varying,
                                         encrypted_raw_response character varying,
                                         encrypted_raw_response_iv character varying,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.loanspq_requests OWNER TO josiah;

--
-- Name: loanspq_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loanspq_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loanspq_requests_id_seq OWNER TO josiah;

--
-- Name: loanspq_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loanspq_requests_id_seq OWNED BY public.loanspq_requests.id;


--
-- Name: loanspq_statuses; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.loanspq_statuses (
                                         id bigint NOT NULL,
                                         loan_application_id bigint NOT NULL,
                                         external_uuid character varying NOT NULL,
                                         external_id integer NOT NULL,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL,
                                         decision_status character varying,
                                         autodecisioned boolean DEFAULT false NOT NULL
);


ALTER TABLE public.loanspq_statuses OWNER TO josiah;

--
-- Name: loanspq_statuses_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.loanspq_statuses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.loanspq_statuses_id_seq OWNER TO josiah;

--
-- Name: loanspq_statuses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.loanspq_statuses_id_seq OWNED BY public.loanspq_statuses.id;


--
-- Name: lob_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lob_requests (
                                     id bigint NOT NULL,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL,
                                     request_type character varying NOT NULL,
                                     request_parameters text,
                                     response_code integer NOT NULL,
                                     raw_response text NOT NULL,
                                     payoff_issuance_id bigint
);


ALTER TABLE public.lob_requests OWNER TO josiah;

--
-- Name: lob_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lob_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lob_requests_id_seq OWNER TO josiah;

--
-- Name: lob_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lob_requests_id_seq OWNED BY public.lob_requests.id;


--
-- Name: lui_conversions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.lui_conversions (
                                        id bigint NOT NULL,
                                        lender_user_invitation_id bigint NOT NULL,
                                        user_id bigint NOT NULL,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.lui_conversions OWNER TO josiah;

--
-- Name: lui_conversions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.lui_conversions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lui_conversions_id_seq OWNER TO josiah;

--
-- Name: lui_conversions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.lui_conversions_id_seq OWNED BY public.lui_conversions.id;


--
-- Name: manual_loan_decisions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.manual_loan_decisions (
                                              id bigint NOT NULL,
                                              loan_application_id bigint NOT NULL,
                                              user_id bigint NOT NULL,
                                              decision character varying NOT NULL,
                                              offer_id bigint NOT NULL,
                                              reason character varying,
                                              created_at timestamp without time zone NOT NULL,
                                              updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.manual_loan_decisions OWNER TO josiah;

--
-- Name: manual_loan_decisions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.manual_loan_decisions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.manual_loan_decisions_id_seq OWNER TO josiah;

--
-- Name: manual_loan_decisions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.manual_loan_decisions_id_seq OWNED BY public.manual_loan_decisions.id;


--
-- Name: mb_distinct_visitor_tokens; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_distinct_visitor_tokens AS
SELECT DISTINCT p1.visitor_token
FROM ( SELECT e2.visitor_token
       FROM (( SELECT ahoy_visits.user_id,
                      min(ahoy_visits.started_at) AS min_started_at
               FROM public.ahoy_visits
               WHERE (ahoy_visits.user_id IS NOT NULL)
               GROUP BY ahoy_visits.user_id) e1
                LEFT JOIN LATERAL ( SELECT ahoy_visits.visitor_token
                                    FROM public.ahoy_visits
                                    WHERE ((ahoy_visits.user_id = e1.user_id) AND (ahoy_visits.started_at = e1.min_started_at))) e2 ON (true))
       UNION
       SELECT ahoy_visits.visitor_token
       FROM public.ahoy_visits
       WHERE (ahoy_visits.user_id IS NULL)) p1
WHERE ((p1.visitor_token)::text <> 'null'::text);


ALTER TABLE public.mb_distinct_visitor_tokens OWNER TO josiah;

--
-- Name: mb_visit_app_starts; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_visit_app_starts AS
SELECT ahoy_visits.visitor_token,
       'App Start'::text AS state,
       min(ahoy_events."time") AS "timestamp"
FROM (public.ahoy_visits
         JOIN public.ahoy_events ON (((ahoy_events.visit_id = ahoy_visits.id) AND ((ahoy_events.name)::text = '$view'::text) AND ((ahoy_events.properties ->> 'url'::text) ~~ 'https://apply.motorefi.com%'::text))))
WHERE (ahoy_visits.started_at >= '2018-05-01 00:00:00'::timestamp without time zone)
GROUP BY ahoy_visits.visitor_token;


ALTER TABLE public.mb_visit_app_starts OWNER TO josiah;

--
-- Name: mb_visit_first_utm_params; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_visit_first_utm_params AS
SELECT e1.visitor_token,
       e2.utm_campaign,
       e2.utm_medium,
       e2.utm_source,
       e2.utm_term,
       e2.utm_content
FROM (( SELECT ahoy_visits.visitor_token,
               min(ahoy_visits.started_at) AS min_started_at
        FROM public.ahoy_visits
        WHERE (ahoy_visits.utm_campaign IS NOT NULL)
        GROUP BY ahoy_visits.visitor_token) e1
         LEFT JOIN LATERAL ( SELECT ahoy_visits.utm_campaign,
                                    ahoy_visits.utm_medium,
                                    ahoy_visits.utm_source,
                                    ahoy_visits.utm_term,
                                    ahoy_visits.utm_content
                             FROM public.ahoy_visits
                             WHERE (((ahoy_visits.visitor_token)::text = (e1.visitor_token)::text) AND (ahoy_visits.started_at = e1.min_started_at))) e2 ON (true));


ALTER TABLE public.mb_visit_first_utm_params OWNER TO josiah;

--
-- Name: offers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.offers (
                               id integer NOT NULL,
                               uuid character varying NOT NULL,
                               loan_application_id integer NOT NULL,
                               nominal_annual_percentage_rate numeric(6,6) NOT NULL,
                               loan_term integer NOT NULL,
                               monthly_payment numeric(8,2) NOT NULL,
                               expires_at timestamp without time zone,
                               aasm_state character varying NOT NULL,
                               created_at timestamp without time zone NOT NULL,
                               updated_at timestamp without time zone NOT NULL,
                               credit_policy_entry_id integer NOT NULL,
                               description character varying,
                               offer_group_id bigint
);


ALTER TABLE public.offers OWNER TO josiah;

--
-- Name: mb_visit_loan_app_offers_generated_state_changes; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_visit_loan_app_offers_generated_state_changes AS
SELECT ahoy_visits.visitor_token,
       'Approved'::text AS state,
       min(offers.created_at) AS "timestamp"
FROM ((((public.ahoy_visits
    JOIN public.customers ON ((ahoy_visits.user_id = customers.id)))
    JOIN public.customer_loan_applications ON ((customer_loan_applications.customer_id = customers.id)))
    JOIN public.loan_applications ON ((loan_applications.id = customer_loan_applications.loan_application_id)))
         JOIN public.offers ON ((loan_applications.id = offers.loan_application_id)))
WHERE (ahoy_visits.started_at >= '2018-05-01 00:00:00'::timestamp without time zone)
GROUP BY ahoy_visits.visitor_token, 'Approved'::text;


ALTER TABLE public.mb_visit_loan_app_offers_generated_state_changes OWNER TO josiah;

--
-- Name: versions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.versions (
                                 id integer NOT NULL,
                                 item_type character varying NOT NULL,
                                 item_id integer NOT NULL,
                                 event character varying NOT NULL,
                                 whodunnit character varying,
                                 object text,
                                 created_at timestamp without time zone,
                                 whodunnit_type character varying,
                                 object_changes jsonb
);


ALTER TABLE public.versions OWNER TO josiah;

--
-- Name: mb_visit_loan_app_state_changes; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_visit_loan_app_state_changes AS
WITH salesqueuename(aasm_state, sales_state) AS (
    SELECT salesqueuename_1.aasm_state,
           salesqueuename_1.sales_state
    FROM ( VALUES ('submitted'::text,'App Submitted'::text), ('offer_accepted'::text,'Selected Offer'::text), ('offer_acknowledged'::text,'Reached Doc Upload'::text), ('documents_completed'::text,'Doc Upload Complete'::text), ('funded'::text,'Funded'::text), ('referral_offers_generated'::text,'Referred'::text), ('closed'::text,'Dead'::text)) salesqueuename_1(aasm_state, sales_state)
)
SELECT ahoy_visits.visitor_token,
       salesqueuename.sales_state AS state,
       versions.created_at AS "timestamp"
FROM (((((public.ahoy_visits
    JOIN public.customers ON ((ahoy_visits.user_id = customers.id)))
    JOIN public.customer_loan_applications ON ((customer_loan_applications.customer_id = customers.id)))
    JOIN public.loan_applications ON ((loan_applications.id = customer_loan_applications.loan_application_id)))
    JOIN public.versions ON (((versions.item_id = loan_applications.id) AND ((versions.item_type)::text = 'LoanApplication'::text) AND ((versions.object_changes ->> 'aasm_state'::text) IS NOT NULL) AND ((versions.object_changes #>> '{aasm_state,1}'::text[]) = ANY (ARRAY['submitted'::text, 'offer_accepted'::text, 'offer_acknowledged'::text, 'documents_completed'::text, 'funded'::text, 'referral_offers_generated'::text, 'closed'::text])))))
         JOIN salesqueuename ON (((versions.object_changes #>> '{aasm_state,1}'::text[]) = salesqueuename.aasm_state)))
WHERE (ahoy_visits.started_at >= '2018-05-01 00:00:00'::timestamp without time zone);


ALTER TABLE public.mb_visit_loan_app_state_changes OWNER TO josiah;

--
-- Name: mb_visit_start_ats; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_visit_start_ats AS
SELECT ahoy_visits.visitor_token,
       'Visit Start'::text AS state,
       min(ahoy_events."time") AS "timestamp"
FROM (public.ahoy_visits
         JOIN public.ahoy_events ON (((ahoy_events.visit_id = ahoy_visits.id) AND ((ahoy_events.name)::text = '$view'::text) AND (((ahoy_events.properties ->> 'url'::text) ~~ 'https://motorefi.com%'::text) OR ((ahoy_events.properties ->> 'url'::text) ~~ 'https://www.motorefi.com%'::text)))))
WHERE (ahoy_visits.started_at >= '2018-05-01 00:00:00'::timestamp without time zone)
GROUP BY ahoy_visits.visitor_token;


ALTER TABLE public.mb_visit_start_ats OWNER TO josiah;

--
-- Name: mb_visit_state_changes; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_visit_state_changes AS
SELECT p1.visitor_token,
       p1.state,
       p1."timestamp",
       mb_visit_first_utm_params.utm_campaign,
       mb_visit_first_utm_params.utm_medium,
       mb_visit_first_utm_params.utm_source,
       mb_visit_first_utm_params.utm_term,
       mb_visit_first_utm_params.utm_content
FROM ((( SELECT mb_visit_start_ats.visitor_token,
                mb_visit_start_ats.state,
                mb_visit_start_ats."timestamp"
         FROM public.mb_visit_start_ats
         UNION
         SELECT mb_visit_app_starts.visitor_token,
                mb_visit_app_starts.state,
                mb_visit_app_starts."timestamp"
         FROM public.mb_visit_app_starts
         UNION
         SELECT mb_visit_loan_app_state_changes.visitor_token,
                mb_visit_loan_app_state_changes.state,
                mb_visit_loan_app_state_changes."timestamp"
         FROM public.mb_visit_loan_app_state_changes
         UNION
         SELECT mb_visit_loan_app_offers_generated_state_changes.visitor_token,
                mb_visit_loan_app_offers_generated_state_changes.state,
                mb_visit_loan_app_offers_generated_state_changes."timestamp"
         FROM public.mb_visit_loan_app_offers_generated_state_changes) p1
    JOIN public.mb_distinct_visitor_tokens ON (((p1.visitor_token)::text = (mb_distinct_visitor_tokens.visitor_token)::text)))
         LEFT JOIN public.mb_visit_first_utm_params ON (((p1.visitor_token)::text = (mb_visit_first_utm_params.visitor_token)::text)));


ALTER TABLE public.mb_visit_state_changes OWNER TO josiah;

--
-- Name: mb_states_reacheds; Type: VIEW; Schema: public; Owner: josiah
--

CREATE VIEW public.mb_states_reacheds AS
WITH time_mapping_last_state(name, start, _end, _order) AS (
    SELECT t.column1,
           t.column2,
           t.column3,
           t.column4
    FROM ( VALUES ('Current Queue'::text,date_trunc('month'::text, ('2018-05-01'::date)::timestamp with time zone),timezone('EST'::text, now()),1), ('Last Hour'::text,(timezone('EST'::text, now()) - '01:00:00'::interval),timezone('EST'::text, now()),2), ('Last 24 Hours'::text,(timezone('EST'::text, now()) - '1 day'::interval),timezone('EST'::text, now()),3), ('1 day ago'::text,date_trunc('day'::text, (timezone('EST'::text, now()) + '-1 days'::interval)),date_trunc('day'::text, timezone('EST'::text, now())),4), ('2 days ago'::text,date_trunc('day'::text, (timezone('EST'::text, now()) + '-2 days'::interval)),date_trunc('day'::text, (timezone('EST'::text, now()) + '-1 days'::interval)),5)) t
)
SELECT s1.name,
       s1.state,
       count(s1.state) AS _count,
       s1.utm_campaign,
       s1.utm_source,
       s1.utm_medium,
       s1.utm_term,
       s1.utm_content
FROM ( SELECT time_mapping_last_state.name,
              (array_agg(mb_visit_state_changes.state ORDER BY mb_visit_state_changes."timestamp" DESC))[1] AS state,
              mb_visit_state_changes.visitor_token,
              mb_visit_state_changes.utm_campaign,
              mb_visit_state_changes.utm_source,
              mb_visit_state_changes.utm_medium,
              mb_visit_state_changes.utm_term,
              mb_visit_state_changes.utm_content,
              time_mapping_last_state._order
       FROM (time_mapping_last_state
                LEFT JOIN public.mb_visit_state_changes ON (((mb_visit_state_changes."timestamp" >= time_mapping_last_state.start) AND (mb_visit_state_changes."timestamp" <= time_mapping_last_state._end))))
       GROUP BY time_mapping_last_state.name, mb_visit_state_changes.visitor_token, mb_visit_state_changes.utm_campaign, mb_visit_state_changes.utm_source, mb_visit_state_changes.utm_medium, mb_visit_state_changes.utm_term, mb_visit_state_changes.utm_content, time_mapping_last_state._order) s1
GROUP BY s1.name, s1.state, s1.utm_campaign, s1.utm_source, s1.utm_medium, s1.utm_term, s1.utm_content, s1._order
ORDER BY s1._order;


ALTER TABLE public.mb_states_reacheds OWNER TO josiah;

--
-- Name: message_logs; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.message_logs (
                                     id integer NOT NULL,
                                     message_class character varying,
                                     sent_at timestamp without time zone,
                                     message_channel character varying,
                                     recipient_type character varying,
                                     recipient_id integer,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.message_logs OWNER TO josiah;

--
-- Name: message_logs_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.message_logs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_logs_id_seq OWNER TO josiah;

--
-- Name: message_logs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.message_logs_id_seq OWNED BY public.message_logs.id;


--
-- Name: military_status_indications; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.military_status_indications (
                                                    id integer NOT NULL,
                                                    customer_id integer NOT NULL,
                                                    indication boolean NOT NULL,
                                                    created_at timestamp without time zone NOT NULL,
                                                    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.military_status_indications OWNER TO josiah;

--
-- Name: military_status_indications_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.military_status_indications_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.military_status_indications_id_seq OWNER TO josiah;

--
-- Name: military_status_indications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.military_status_indications_id_seq OWNED BY public.military_status_indications.id;


--
-- Name: myautoloan_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.myautoloan_requests (
                                            id bigint NOT NULL,
                                            app_identifier character varying,
                                            request_body text,
                                            response_body text,
                                            response_code integer,
                                            loan_application_id integer,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.myautoloan_requests OWNER TO josiah;

--
-- Name: myautoloan_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.myautoloan_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.myautoloan_requests_id_seq OWNER TO josiah;

--
-- Name: myautoloan_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.myautoloan_requests_id_seq OWNED BY public.myautoloan_requests.id;


--
-- Name: nada_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.nada_requests (
                                      id integer NOT NULL,
                                      method_name character varying,
                                      path character varying,
                                      parameters text,
                                      body text,
                                      response_code character varying,
                                      raw_response text,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL,
                                      loan_application_id bigint
);


ALTER TABLE public.nada_requests OWNER TO josiah;

--
-- Name: nada_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.nada_requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nada_requests_id_seq OWNER TO josiah;

--
-- Name: nada_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.nada_requests_id_seq OWNED BY public.nada_requests.id;


--
-- Name: nada_tokens; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.nada_tokens (
                                    id integer NOT NULL,
                                    username character varying NOT NULL,
                                    token character varying NOT NULL,
                                    expires_at timestamp without time zone NOT NULL,
                                    created_at timestamp without time zone NOT NULL,
                                    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.nada_tokens OWNER TO josiah;

--
-- Name: nada_tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.nada_tokens_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nada_tokens_id_seq OWNER TO josiah;

--
-- Name: nada_tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.nada_tokens_id_seq OWNED BY public.nada_tokens.id;


--
-- Name: nada_vehicle_accessories; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.nada_vehicle_accessories (
                                                 id bigint NOT NULL,
                                                 vehicle_id bigint NOT NULL,
                                                 "desc" character varying NOT NULL,
                                                 code character varying NOT NULL,
                                                 included boolean,
                                                 retail_value integer NOT NULL,
                                                 created_at timestamp without time zone NOT NULL,
                                                 updated_at timestamp without time zone NOT NULL,
                                                 inclusive character varying,
                                                 exclusive character varying
);


ALTER TABLE public.nada_vehicle_accessories OWNER TO josiah;

--
-- Name: nada_vehicle_accessories_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.nada_vehicle_accessories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nada_vehicle_accessories_id_seq OWNER TO josiah;

--
-- Name: nada_vehicle_accessories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.nada_vehicle_accessories_id_seq OWNED BY public.nada_vehicle_accessories.id;


--
-- Name: naln_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.naln_requests (
                                      id bigint NOT NULL,
                                      path character varying NOT NULL,
                                      response_code character varying NOT NULL,
                                      encrypted_parameters text NOT NULL,
                                      encrypted_body text NOT NULL,
                                      encrypted_raw_response text NOT NULL,
                                      encrypted_parameters_iv character varying NOT NULL,
                                      encrypted_body_iv character varying NOT NULL,
                                      encrypted_raw_response_iv character varying NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.naln_requests OWNER TO josiah;

--
-- Name: naln_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.naln_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.naln_requests_id_seq OWNER TO josiah;

--
-- Name: naln_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.naln_requests_id_seq OWNED BY public.naln_requests.id;


--
-- Name: noaas; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.noaas (
                              id integer NOT NULL,
                              partner_displayed_at timestamp without time zone,
                              created_at timestamp without time zone NOT NULL,
                              updated_at timestamp without time zone NOT NULL,
                              customer_id bigint NOT NULL,
                              loan_application_id bigint NOT NULL,
                              decline_reasons text,
                              reporting_agency character varying
);


ALTER TABLE public.noaas OWNER TO josiah;

--
-- Name: noaas_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.noaas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.noaas_id_seq OWNER TO josiah;

--
-- Name: noaas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.noaas_id_seq OWNED BY public.noaas.id;


--
-- Name: notes; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.notes (
                              id bigint NOT NULL,
                              notable_id integer NOT NULL,
                              notable_type character varying NOT NULL,
                              user_id bigint NOT NULL,
                              kind character varying NOT NULL,
                              content text NOT NULL,
                              created_at timestamp without time zone NOT NULL,
                              updated_at timestamp without time zone NOT NULL,
                              flagged boolean DEFAULT false NOT NULL,
                              category integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.notes OWNER TO josiah;

--
-- Name: notes_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.notes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notes_id_seq OWNER TO josiah;

--
-- Name: notes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.notes_id_seq OWNED BY public.notes.id;


--
-- Name: offer_groups; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.offer_groups (
                                     id bigint NOT NULL,
                                     presented_at timestamp without time zone,
                                     invalidated_at timestamp without time zone,
                                     description character varying NOT NULL,
                                     loan_application_id bigint NOT NULL,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.offer_groups OWNER TO josiah;

--
-- Name: offer_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.offer_groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.offer_groups_id_seq OWNER TO josiah;

--
-- Name: offer_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.offer_groups_id_seq OWNED BY public.offer_groups.id;


--
-- Name: offer_rejections; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.offer_rejections (
                                         id bigint NOT NULL,
                                         loan_application_id bigint NOT NULL,
                                         rejection_reasons text,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.offer_rejections OWNER TO josiah;

--
-- Name: offer_rejections_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.offer_rejections_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.offer_rejections_id_seq OWNER TO josiah;

--
-- Name: offer_rejections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.offer_rejections_id_seq OWNED BY public.offer_rejections.id;


--
-- Name: offers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.offers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.offers_id_seq OWNER TO josiah;

--
-- Name: offers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.offers_id_seq OWNED BY public.offers.id;


--
-- Name: open_lending_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.open_lending_requests (
                                              id bigint NOT NULL,
                                              loan_application_id bigint,
                                              path character varying,
                                              response_code character varying,
                                              encrypted_request_body character varying,
                                              encrypted_request_body_iv character varying,
                                              encrypted_raw_response character varying,
                                              encrypted_raw_response_iv character varying,
                                              created_at timestamp without time zone NOT NULL,
                                              updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.open_lending_requests OWNER TO josiah;

--
-- Name: open_lending_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.open_lending_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.open_lending_requests_id_seq OWNER TO josiah;

--
-- Name: open_lending_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.open_lending_requests_id_seq OWNED BY public.open_lending_requests.id;


--
-- Name: opt_outs; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.opt_outs (
                                 id bigint NOT NULL,
                                 customer_id bigint NOT NULL,
                                 created_at timestamp without time zone NOT NULL,
                                 updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.opt_outs OWNER TO josiah;

--
-- Name: opt_outs_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.opt_outs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.opt_outs_id_seq OWNER TO josiah;

--
-- Name: opt_outs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.opt_outs_id_seq OWNED BY public.opt_outs.id;


--
-- Name: origination_percentages; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.origination_percentages (
                                                id bigint NOT NULL,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL,
                                                lender_id bigint NOT NULL,
                                                percentage character varying NOT NULL,
                                                starts_at timestamp without time zone NOT NULL
);


ALTER TABLE public.origination_percentages OWNER TO josiah;

--
-- Name: origination_percentages_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.origination_percentages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.origination_percentages_id_seq OWNER TO josiah;

--
-- Name: origination_percentages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.origination_percentages_id_seq OWNED BY public.origination_percentages.id;


--
-- Name: outbound_text_templates; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.outbound_text_templates (
                                                id bigint NOT NULL,
                                                context character varying,
                                                body character varying NOT NULL,
                                                user_id bigint NOT NULL,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.outbound_text_templates OWNER TO josiah;

--
-- Name: outbound_text_templates_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.outbound_text_templates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.outbound_text_templates_id_seq OWNER TO josiah;

--
-- Name: outbound_text_templates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.outbound_text_templates_id_seq OWNED BY public.outbound_text_templates.id;


--
-- Name: payoff_issuances; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.payoff_issuances (
                                         id bigint NOT NULL,
                                         created_at timestamp without time zone NOT NULL,
                                         updated_at timestamp without time zone NOT NULL,
                                         loan_application_id bigint NOT NULL,
                                         issued_by_id bigint NOT NULL,
                                         payoff_amount numeric(8,2) NOT NULL,
                                         recipient_name character varying NOT NULL,
                                         lob_address_identifier character varying NOT NULL,
                                         lob_check_identifier character varying NOT NULL,
                                         cancelled boolean DEFAULT false,
                                         check_number character varying,
                                         check_image_url character varying
);


ALTER TABLE public.payoff_issuances OWNER TO josiah;

--
-- Name: payoff_issuances_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.payoff_issuances_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payoff_issuances_id_seq OWNER TO josiah;

--
-- Name: payoff_issuances_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.payoff_issuances_id_seq OWNED BY public.payoff_issuances.id;


--
-- Name: payoff_quotes; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.payoff_quotes (
                                      id bigint NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      collected_by_id bigint NOT NULL,
                                      amount numeric(8,2) NOT NULL,
                                      valid_until date NOT NULL,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL,
                                      used boolean DEFAULT false NOT NULL,
                                      per_diem_amount numeric(8,2) NOT NULL,
                                      days integer NOT NULL
);


ALTER TABLE public.payoff_quotes OWNER TO josiah;

--
-- Name: payoff_quotes_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.payoff_quotes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payoff_quotes_id_seq OWNER TO josiah;

--
-- Name: payoff_quotes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.payoff_quotes_id_seq OWNED BY public.payoff_quotes.id;


--
-- Name: phone_applications; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.phone_applications (
                                           id bigint NOT NULL,
                                           loan_application_id bigint NOT NULL,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.phone_applications OWNER TO josiah;

--
-- Name: phone_applications_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.phone_applications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phone_applications_id_seq OWNER TO josiah;

--
-- Name: phone_applications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.phone_applications_id_seq OWNED BY public.phone_applications.id;


--
-- Name: phone_number_texts; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.phone_number_texts (
                                           id bigint NOT NULL,
                                           phone_number_id bigint,
                                           text_id bigint,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.phone_number_texts OWNER TO josiah;

--
-- Name: phone_number_texts_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.phone_number_texts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phone_number_texts_id_seq OWNER TO josiah;

--
-- Name: phone_number_texts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.phone_number_texts_id_seq OWNED BY public.phone_number_texts.id;


--
-- Name: phone_numbers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.phone_numbers (
                                      id bigint NOT NULL,
                                      customer_id bigint NOT NULL,
                                      phone_number character varying NOT NULL,
                                      phone_number_type character varying NOT NULL,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.phone_numbers OWNER TO josiah;

--
-- Name: phone_numbers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.phone_numbers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phone_numbers_id_seq OWNER TO josiah;

--
-- Name: phone_numbers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.phone_numbers_id_seq OWNED BY public.phone_numbers.id;


--
-- Name: prequeue_data_collections; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.prequeue_data_collections (
                                                  id bigint NOT NULL,
                                                  loan_application_id bigint NOT NULL,
                                                  data json NOT NULL,
                                                  created_at timestamp without time zone NOT NULL,
                                                  updated_at timestamp without time zone NOT NULL,
                                                  customer_id integer NOT NULL
);


ALTER TABLE public.prequeue_data_collections OWNER TO josiah;

--
-- Name: prequeue_data_collections_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.prequeue_data_collections_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prequeue_data_collections_id_seq OWNER TO josiah;

--
-- Name: prequeue_data_collections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.prequeue_data_collections_id_seq OWNED BY public.prequeue_data_collections.id;


--
-- Name: processing_fees; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.processing_fees (
                                        id bigint NOT NULL,
                                        fee numeric(6,0) NOT NULL,
                                        state character varying NOT NULL,
                                        effective_date timestamp without time zone,
                                        deactivated_at timestamp without time zone
);


ALTER TABLE public.processing_fees OWNER TO josiah;

--
-- Name: processing_fees_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.processing_fees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.processing_fees_id_seq OWNER TO josiah;

--
-- Name: processing_fees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.processing_fees_id_seq OWNED BY public.processing_fees.id;


--
-- Name: product_fees; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.product_fees (
                                     id bigint NOT NULL,
                                     fee character varying NOT NULL,
                                     product_class character varying NOT NULL,
                                     effective_date timestamp without time zone,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone NOT NULL,
                                     filter jsonb DEFAULT '{}'::jsonb
);


ALTER TABLE public.product_fees OWNER TO josiah;

--
-- Name: product_fees_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.product_fees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_fees_id_seq OWNER TO josiah;

--
-- Name: product_fees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.product_fees_id_seq OWNED BY public.product_fees.id;


--
-- Name: promax_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.promax_requests (
                                        id bigint NOT NULL,
                                        path character varying,
                                        parameters text,
                                        body text,
                                        response_code character varying,
                                        raw_response text,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL,
                                        loan_application_id integer NOT NULL
);


ALTER TABLE public.promax_requests OWNER TO josiah;

--
-- Name: promax_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.promax_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.promax_requests_id_seq OWNER TO josiah;

--
-- Name: promax_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.promax_requests_id_seq OWNED BY public.promax_requests.id;


--
-- Name: referral_codes; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.referral_codes (
                                       id bigint NOT NULL,
                                       customer_id bigint NOT NULL,
                                       code character varying NOT NULL,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.referral_codes OWNER TO josiah;

--
-- Name: referral_codes_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.referral_codes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.referral_codes_id_seq OWNER TO josiah;

--
-- Name: referral_codes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.referral_codes_id_seq OWNED BY public.referral_codes.id;


--
-- Name: referral_offers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.referral_offers (
                                        id bigint NOT NULL,
                                        uuid character varying NOT NULL,
                                        loan_application_id bigint NOT NULL,
                                        credit_policy_id bigint NOT NULL,
                                        referral_url character varying,
                                        referral_confirmed boolean DEFAULT false NOT NULL,
                                        referral_confirmed_at timestamp without time zone,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL,
                                        active boolean DEFAULT true
);


ALTER TABLE public.referral_offers OWNER TO josiah;

--
-- Name: referral_offers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.referral_offers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.referral_offers_id_seq OWNER TO josiah;

--
-- Name: referral_offers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.referral_offers_id_seq OWNED BY public.referral_offers.id;


--
-- Name: report_runs; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.report_runs (
                                    id integer NOT NULL,
                                    report_name character varying,
                                    run_by integer,
                                    report_data text,
                                    approved_at timestamp without time zone,
                                    approved_by integer,
                                    created_at timestamp without time zone NOT NULL,
                                    updated_at timestamp without time zone NOT NULL,
                                    time_type character varying,
                                    time_interval integer
);


ALTER TABLE public.report_runs OWNER TO josiah;

--
-- Name: report_runs_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.report_runs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.report_runs_id_seq OWNER TO josiah;

--
-- Name: report_runs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.report_runs_id_seq OWNED BY public.report_runs.id;


--
-- Name: schema_migrations; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.schema_migrations (
    version character varying NOT NULL
);


ALTER TABLE public.schema_migrations OWNER TO josiah;

--
-- Name: sessions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.sessions (
                                 id integer NOT NULL,
                                 customer_id integer NOT NULL,
                                 uuid character varying NOT NULL,
                                 created_at timestamp without time zone NOT NULL,
                                 updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.sessions OWNER TO josiah;

--
-- Name: sessions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.sessions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sessions_id_seq OWNER TO josiah;

--
-- Name: sessions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.sessions_id_seq OWNED BY public.sessions.id;


--
-- Name: shipments; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.shipments (
                                  id bigint NOT NULL,
                                  loan_application_id bigint NOT NULL,
                                  from_address jsonb DEFAULT '{}'::jsonb NOT NULL,
                                  to_address jsonb DEFAULT '{}'::jsonb NOT NULL,
                                  shipment_data jsonb DEFAULT '{}'::jsonb NOT NULL,
                                  rate_data jsonb DEFAULT '{}'::jsonb NOT NULL,
                                  status character varying NOT NULL,
                                  shipment_type character varying NOT NULL,
                                  user_id bigint NOT NULL,
                                  created_at timestamp without time zone NOT NULL,
                                  updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.shipments OWNER TO josiah;

--
-- Name: shipments_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.shipments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shipments_id_seq OWNER TO josiah;

--
-- Name: shipments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.shipments_id_seq OWNED BY public.shipments.id;


--
-- Name: si_banking_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.si_banking_requests (
                                            id bigint NOT NULL,
                                            loan_application_id bigint NOT NULL,
                                            path character varying NOT NULL,
                                            response_code character varying NOT NULL,
                                            encrypted_request_body character varying,
                                            encrypted_request_body_iv character varying,
                                            encrypted_raw_response character varying,
                                            encrypted_raw_response_iv character varying,
                                            encrypted_hash_key character varying,
                                            encrypted_hash_key_iv character varying,
                                            created_at timestamp without time zone NOT NULL,
                                            updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.si_banking_requests OWNER TO josiah;

--
-- Name: si_banking_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.si_banking_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.si_banking_requests_id_seq OWNER TO josiah;

--
-- Name: si_banking_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.si_banking_requests_id_seq OWNED BY public.si_banking_requests.id;


--
-- Name: simple_sales_user_reports; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.simple_sales_user_reports (
                                                  id bigint NOT NULL,
                                                  user_id bigint NOT NULL,
                                                  created_at timestamp without time zone NOT NULL,
                                                  updated_at timestamp without time zone NOT NULL,
                                                  status_counts json DEFAULT '{}'::json NOT NULL
);


ALTER TABLE public.simple_sales_user_reports OWNER TO josiah;

--
-- Name: simple_sales_user_reports_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.simple_sales_user_reports_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.simple_sales_user_reports_id_seq OWNER TO josiah;

--
-- Name: simple_sales_user_reports_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.simple_sales_user_reports_id_seq OWNED BY public.simple_sales_user_reports.id;


--
-- Name: slow_tasks; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.slow_tasks (
                                   id bigint NOT NULL,
                                   started_by_id bigint NOT NULL,
                                   title character varying NOT NULL,
                                   aasm_state character varying NOT NULL,
                                   when_done_url character varying NOT NULL,
                                   started_at timestamp without time zone,
                                   finished_at timestamp without time zone,
                                   notes text,
                                   created_at timestamp without time zone NOT NULL,
                                   updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.slow_tasks OWNER TO josiah;

--
-- Name: slow_tasks_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.slow_tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.slow_tasks_id_seq OWNER TO josiah;

--
-- Name: slow_tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.slow_tasks_id_seq OWNED BY public.slow_tasks.id;


--
-- Name: sms_records; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.sms_records (
                                    id bigint NOT NULL,
                                    customer_id bigint NOT NULL,
                                    loan_application_id bigint NOT NULL,
                                    phone_number character varying NOT NULL,
                                    message character varying NOT NULL,
                                    api_response text NOT NULL,
                                    created_at timestamp without time zone NOT NULL,
                                    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.sms_records OWNER TO josiah;

--
-- Name: sms_records_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.sms_records_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sms_records_id_seq OWNER TO josiah;

--
-- Name: sms_records_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.sms_records_id_seq OWNED BY public.sms_records.id;


--
-- Name: split_records; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.split_records (
                                      id bigint NOT NULL,
                                      loan_application_id bigint NOT NULL,
                                      customer_id bigint NOT NULL,
                                      experiment_name character varying NOT NULL,
                                      variant character varying NOT NULL,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.split_records OWNER TO josiah;

--
-- Name: split_records_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.split_records_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.split_records_id_seq OWNER TO josiah;

--
-- Name: split_records_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.split_records_id_seq OWNED BY public.split_records.id;


--
-- Name: state_apr_data; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.state_apr_data (
                                       id bigint NOT NULL,
                                       state character varying,
                                       credit_score_range character varying,
                                       apr numeric(6,3),
                                       loan_term integer,
                                       record_count integer DEFAULT 0,
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.state_apr_data OWNER TO josiah;

--
-- Name: state_apr_data_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.state_apr_data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.state_apr_data_id_seq OWNER TO josiah;

--
-- Name: state_apr_data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.state_apr_data_id_seq OWNED BY public.state_apr_data.id;


--
-- Name: state_fees; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.state_fees (
                                   id bigint NOT NULL,
                                   description character varying NOT NULL,
                                   state_abbreviation character varying NOT NULL,
                                   fee_type character varying NOT NULL,
                                   amount character varying NOT NULL,
                                   effective_at timestamp without time zone NOT NULL,
                                   created_at timestamp without time zone NOT NULL,
                                   updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.state_fees OWNER TO josiah;

--
-- Name: state_fees_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.state_fees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.state_fees_id_seq OWNER TO josiah;

--
-- Name: state_fees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.state_fees_id_seq OWNED BY public.state_fees.id;


--
-- Name: supplementary_document_requirements; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.supplementary_document_requirements (
                                                            id bigint NOT NULL,
                                                            comment text NOT NULL,
                                                            user_id bigint NOT NULL,
                                                            loan_application_id bigint NOT NULL,
                                                            lender_document_definition_id bigint NOT NULL,
                                                            created_at timestamp without time zone NOT NULL,
                                                            updated_at timestamp without time zone NOT NULL,
                                                            customer_id integer NOT NULL
);


ALTER TABLE public.supplementary_document_requirements OWNER TO josiah;

--
-- Name: supplementary_document_requirements_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.supplementary_document_requirements_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.supplementary_document_requirements_id_seq OWNER TO josiah;

--
-- Name: supplementary_document_requirements_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.supplementary_document_requirements_id_seq OWNED BY public.supplementary_document_requirements.id;


--
-- Name: texts; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.texts (
                              id bigint NOT NULL,
                              message character varying NOT NULL,
                              inbound boolean DEFAULT false NOT NULL,
                              contact_number character varying NOT NULL,
                              sent boolean DEFAULT true NOT NULL,
                              error_response text,
                              message_sid character varying,
                              user_id integer,
                              created_at timestamp without time zone NOT NULL,
                              updated_at timestamp without time zone NOT NULL,
                              phone_number_id integer,
                              marked_read boolean DEFAULT false,
                              internal_number character varying NOT NULL
);


ALTER TABLE public.texts OWNER TO josiah;

--
-- Name: texts_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.texts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.texts_id_seq OWNER TO josiah;

--
-- Name: texts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.texts_id_seq OWNED BY public.texts.id;


--
-- Name: title_document_transfers; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.title_document_transfers (
                                                 id bigint NOT NULL,
                                                 vehicle_title_id bigint NOT NULL,
                                                 document_id bigint NOT NULL,
                                                 transaction_id character varying NOT NULL,
                                                 created_at timestamp without time zone NOT NULL,
                                                 updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.title_document_transfers OWNER TO josiah;

--
-- Name: title_document_transfers_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.title_document_transfers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.title_document_transfers_id_seq OWNER TO josiah;

--
-- Name: title_document_transfers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.title_document_transfers_id_seq OWNED BY public.title_document_transfers.id;


--
-- Name: title_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.title_documents (
                                        id bigint NOT NULL,
                                        vehicle_title_id bigint NOT NULL,
                                        name character varying NOT NULL,
                                        encoded_data text NOT NULL,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL,
                                        download boolean DEFAULT false
);


ALTER TABLE public.title_documents OWNER TO josiah;

--
-- Name: title_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.title_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.title_documents_id_seq OWNER TO josiah;

--
-- Name: title_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.title_documents_id_seq OWNED BY public.title_documents.id;


--
-- Name: title_poa_shipment_customer_emails; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.title_poa_shipment_customer_emails (
                                                           id bigint NOT NULL,
                                                           shipment_id bigint NOT NULL,
                                                           customer_id bigint NOT NULL,
                                                           user_id bigint NOT NULL,
                                                           created_at timestamp without time zone NOT NULL,
                                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.title_poa_shipment_customer_emails OWNER TO josiah;

--
-- Name: title_poa_shipment_customer_emails_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.title_poa_shipment_customer_emails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.title_poa_shipment_customer_emails_id_seq OWNER TO josiah;

--
-- Name: title_poa_shipment_customer_emails_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.title_poa_shipment_customer_emails_id_seq OWNED BY public.title_poa_shipment_customer_emails.id;


--
-- Name: tos_acceptances; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.tos_acceptances (
                                        id integer NOT NULL,
                                        legal_terms_document_id integer NOT NULL,
                                        loan_application_id integer NOT NULL,
                                        created_at timestamp without time zone NOT NULL,
                                        updated_at timestamp without time zone NOT NULL,
                                        customer_id integer NOT NULL
);


ALTER TABLE public.tos_acceptances OWNER TO josiah;

--
-- Name: tos_acceptances_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.tos_acceptances_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tos_acceptances_id_seq OWNER TO josiah;

--
-- Name: tos_acceptances_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.tos_acceptances_id_seq OWNED BY public.tos_acceptances.id;


--
-- Name: total_loss_protection_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.total_loss_protection_documents (
                                                        id bigint NOT NULL,
                                                        total_loss_protection_id integer NOT NULL,
                                                        contract_identifier character varying,
                                                        encoded_data text,
                                                        contract_data jsonb DEFAULT '{}'::jsonb,
                                                        created_at timestamp without time zone NOT NULL,
                                                        updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.total_loss_protection_documents OWNER TO josiah;

--
-- Name: total_loss_protection_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.total_loss_protection_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.total_loss_protection_documents_id_seq OWNER TO josiah;

--
-- Name: total_loss_protection_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.total_loss_protection_documents_id_seq OWNED BY public.total_loss_protection_documents.id;


--
-- Name: total_loss_protections; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.total_loss_protections (
                                               id bigint NOT NULL,
                                               loan_application_id bigint,
                                               provider character varying NOT NULL,
                                               name character varying NOT NULL,
                                               description character varying NOT NULL,
                                               product_code character varying NOT NULL,
                                               product_id character varying NOT NULL,
                                               aasm_state character varying,
                                               months integer,
                                               mileage integer,
                                               wholesale_cost numeric(6,2),
                                               suggested_retail_cost numeric(6,2),
                                               contract_identifier character varying,
                                               product_data jsonb DEFAULT '{}'::jsonb,
                                               sold_by integer,
                                               created_at timestamp without time zone NOT NULL,
                                               updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.total_loss_protections OWNER TO josiah;

--
-- Name: total_loss_protections_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.total_loss_protections_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.total_loss_protections_id_seq OWNER TO josiah;

--
-- Name: total_loss_protections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.total_loss_protections_id_seq OWNED BY public.total_loss_protections.id;


--
-- Name: underwriting_exceptions; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.underwriting_exceptions (
                                                id bigint NOT NULL,
                                                loan_application_id bigint NOT NULL,
                                                credit_policy_exception_definition_id bigint NOT NULL,
                                                credit_policy_entry_id bigint,
                                                active boolean DEFAULT true,
                                                exception_type character varying,
                                                exception_field_name character varying,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.underwriting_exceptions OWNER TO josiah;

--
-- Name: underwriting_exceptions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.underwriting_exceptions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.underwriting_exceptions_id_seq OWNER TO josiah;

--
-- Name: underwriting_exceptions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.underwriting_exceptions_id_seq OWNED BY public.underwriting_exceptions.id;


--
-- Name: user_deactivations; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.user_deactivations (
                                           id bigint NOT NULL,
                                           deactivated_by_id bigint NOT NULL,
                                           user_id bigint NOT NULL,
                                           created_at timestamp without time zone NOT NULL,
                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.user_deactivations OWNER TO josiah;

--
-- Name: user_deactivations_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.user_deactivations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_deactivations_id_seq OWNER TO josiah;

--
-- Name: user_deactivations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.user_deactivations_id_seq OWNED BY public.user_deactivations.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.users (
                              id integer NOT NULL,
                              first_name character varying NOT NULL,
                              last_name character varying NOT NULL,
                              email character varying NOT NULL,
                              password_digest character varying NOT NULL,
                              roles_mask integer DEFAULT 0,
                              sign_in_count integer,
                              current_sign_in_at timestamp without time zone,
                              current_sign_in_ip character varying,
                              last_sign_in_at timestamp without time zone,
                              last_sign_in_ip character varying,
                              created_at timestamp without time zone NOT NULL,
                              updated_at timestamp without time zone NOT NULL,
                              failed_login_count integer DEFAULT 0,
                              slack_user_name character varying,
                              active_for_leads boolean DEFAULT true
);


ALTER TABLE public.users OWNER TO josiah;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO josiah;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: utm_records; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.utm_records (
                                    id bigint NOT NULL,
                                    customer_id bigint,
                                    utm_source character varying,
                                    utm_medium character varying,
                                    utm_term character varying,
                                    utm_content character varying,
                                    utm_campaign character varying,
                                    created_at timestamp without time zone NOT NULL,
                                    updated_at timestamp without time zone NOT NULL,
                                    ext_id character varying,
                                    direct_mail_lookup_id bigint
);


ALTER TABLE public.utm_records OWNER TO josiah;

--
-- Name: utm_records_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.utm_records_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utm_records_id_seq OWNER TO josiah;

--
-- Name: utm_records_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.utm_records_id_seq OWNED BY public.utm_records.id;


--
-- Name: validated_income_amounts; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.validated_income_amounts (
                                                 id bigint NOT NULL,
                                                 validated_by_id bigint NOT NULL,
                                                 customer_id bigint NOT NULL,
                                                 loan_application_id bigint NOT NULL,
                                                 amount numeric NOT NULL,
                                                 additional_info text
);


ALTER TABLE public.validated_income_amounts OWNER TO josiah;

--
-- Name: validated_income_amounts_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.validated_income_amounts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.validated_income_amounts_id_seq OWNER TO josiah;

--
-- Name: validated_income_amounts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.validated_income_amounts_id_seq OWNED BY public.validated_income_amounts.id;


--
-- Name: vehicle_service_contract_documents; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.vehicle_service_contract_documents (
                                                           id bigint NOT NULL,
                                                           vehicle_service_contract_id bigint,
                                                           contract_identifier character varying NOT NULL,
                                                           authorization_code character varying,
                                                           encoded_data text,
                                                           created_at timestamp without time zone NOT NULL,
                                                           updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.vehicle_service_contract_documents OWNER TO josiah;

--
-- Name: vehicle_service_contract_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.vehicle_service_contract_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehicle_service_contract_documents_id_seq OWNER TO josiah;

--
-- Name: vehicle_service_contract_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.vehicle_service_contract_documents_id_seq OWNED BY public.vehicle_service_contract_documents.id;


--
-- Name: vehicle_service_contracts; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.vehicle_service_contracts (
                                                  id integer NOT NULL,
                                                  provider character varying NOT NULL,
                                                  name character varying NOT NULL,
                                                  description text NOT NULL,
                                                  months integer NOT NULL,
                                                  mileage integer NOT NULL,
                                                  deductible numeric(6,2),
                                                  product_code character varying NOT NULL,
                                                  contract_identifier character varying,
                                                  aasm_state character varying,
                                                  wholesale_cost numeric(6,2),
                                                  suggested_retail_cost numeric(6,2),
                                                  created_at timestamp without time zone NOT NULL,
                                                  updated_at timestamp without time zone NOT NULL,
                                                  customer_cost numeric(6,2) DEFAULT 0.0,
                                                  sold_by integer,
                                                  loan_application_id integer NOT NULL,
                                                  request_id integer NOT NULL
);


ALTER TABLE public.vehicle_service_contracts OWNER TO josiah;

--
-- Name: vehicle_service_contracts_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.vehicle_service_contracts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehicle_service_contracts_id_seq OWNER TO josiah;

--
-- Name: vehicle_service_contracts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.vehicle_service_contracts_id_seq OWNED BY public.vehicle_service_contracts.id;


--
-- Name: vehicle_titles; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.vehicle_titles (
                                       id bigint NOT NULL,
                                       vehicle_id bigint NOT NULL,
                                       loan_application_id bigint NOT NULL,
                                       approver_id integer,
                                       requirements json NOT NULL,
                                       aasm_state character varying NOT NULL,
                                       cost numeric(8,2),
                                       created_at timestamp without time zone NOT NULL,
                                       updated_at timestamp without time zone NOT NULL,
                                       transaction_id character varying,
                                       status character varying,
                                       fees json,
                                       download_complete boolean DEFAULT false,
                                       titling_processor character varying
);


ALTER TABLE public.vehicle_titles OWNER TO josiah;

--
-- Name: vehicle_titles_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.vehicle_titles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehicle_titles_id_seq OWNER TO josiah;

--
-- Name: vehicle_titles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.vehicle_titles_id_seq OWNED BY public.vehicle_titles.id;


--
-- Name: vehicles; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.vehicles (
                                 id integer NOT NULL,
                                 make character varying,
                                 model character varying,
                                 "trim" character varying,
                                 year integer,
                                 mileage integer,
                                 vin character varying,
                                 license_plate_number character varying,
                                 license_plate_state character varying,
                                 estimated_value numeric(8,2),
                                 created_at timestamp without time zone NOT NULL,
                                 updated_at timestamp without time zone NOT NULL,
                                 nada_uid character varying,
                                 title_state character varying,
                                 aasm_state character varying DEFAULT 'pending'::character varying NOT NULL,
                                 weight integer,
                                 body_type character varying,
                                 fuel_type character varying,
                                 color character varying,
                                 license_plate_expiration date,
                                 mileage_adjustment integer,
                                 retail_value integer,
                                 loan_value integer,
                                 trade_in_value integer,
                                 nada_updated_at timestamp without time zone,
                                 title_county character varying
);


ALTER TABLE public.vehicles OWNER TO josiah;

--
-- Name: vehicles_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.vehicles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehicles_id_seq OWNER TO josiah;

--
-- Name: vehicles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.vehicles_id_seq OWNED BY public.vehicles.id;


--
-- Name: versions_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.versions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.versions_id_seq OWNER TO josiah;

--
-- Name: versions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.versions_id_seq OWNED BY public.versions.id;


--
-- Name: wolters_kluwer_requests; Type: TABLE; Schema: public; Owner: josiah
--

CREATE TABLE public.wolters_kluwer_requests (
                                                id bigint NOT NULL,
                                                vehicle_id bigint,
                                                path character varying,
                                                parameters text,
                                                body text,
                                                response_code character varying,
                                                raw_response text,
                                                created_at timestamp without time zone NOT NULL,
                                                updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.wolters_kluwer_requests OWNER TO josiah;

--
-- Name: wolters_kluwer_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: josiah
--

CREATE SEQUENCE public.wolters_kluwer_requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wolters_kluwer_requests_id_seq OWNER TO josiah;

--
-- Name: wolters_kluwer_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josiah
--

ALTER SEQUENCE public.wolters_kluwer_requests_id_seq OWNED BY public.wolters_kluwer_requests.id;


--
-- Name: active_storage_attachments id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.active_storage_attachments ALTER COLUMN id SET DEFAULT nextval('public.active_storage_attachments_id_seq'::regclass);


--
-- Name: active_storage_blobs id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.active_storage_blobs ALTER COLUMN id SET DEFAULT nextval('public.active_storage_blobs_id_seq'::regclass);


--
-- Name: addresses id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.addresses ALTER COLUMN id SET DEFAULT nextval('public.addresses_id_seq'::regclass);


--
-- Name: admin_sessions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.admin_sessions ALTER COLUMN id SET DEFAULT nextval('public.admin_sessions_id_seq'::regclass);


--
-- Name: ads_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_requests ALTER COLUMN id SET DEFAULT nextval('public.ads_requests_id_seq'::regclass);


--
-- Name: ads_vehicle_service_contract_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_vehicle_service_contract_documents ALTER COLUMN id SET DEFAULT nextval('public.ads_vehicle_service_contract_documents_id_seq'::regclass);


--
-- Name: ads_vehicle_service_contracts id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_vehicle_service_contracts ALTER COLUMN id SET DEFAULT nextval('public.ads_vehicle_service_contracts_id_seq'::regclass);


--
-- Name: ahoy_events id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ahoy_events ALTER COLUMN id SET DEFAULT nextval('public.ahoy_events_id_seq'::regclass);


--
-- Name: ahoy_visits id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ahoy_visits ALTER COLUMN id SET DEFAULT nextval('public.ahoy_visits_id_seq'::regclass);


--
-- Name: alloy_evaluations id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.alloy_evaluations ALTER COLUMN id SET DEFAULT nextval('public.alloy_evaluations_id_seq'::regclass);


--
-- Name: api_request_cache id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.api_request_cache ALTER COLUMN id SET DEFAULT nextval('public.api_request_cache_id_seq'::regclass);


--
-- Name: authentication_tokens id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.authentication_tokens ALTER COLUMN id SET DEFAULT nextval('public.authentication_tokens_id_seq'::regclass);


--
-- Name: auto_loan_selections id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.auto_loan_selections ALTER COLUMN id SET DEFAULT nextval('public.auto_loan_selections_id_seq'::regclass);


--
-- Name: autoconfiado_customers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.autoconfiado_customers ALTER COLUMN id SET DEFAULT nextval('public.autoconfiado_customers_id_seq'::regclass);


--
-- Name: careers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.careers ALTER COLUMN id SET DEFAULT nextval('public.careers_id_seq'::regclass);


--
-- Name: caregard_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.caregard_requests ALTER COLUMN id SET DEFAULT nextval('public.caregard_requests_id_seq'::regclass);


--
-- Name: carfax_responses id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.carfax_responses ALTER COLUMN id SET DEFAULT nextval('public.carfax_responses_id_seq'::regclass);


--
-- Name: contact_messages id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.contact_messages ALTER COLUMN id SET DEFAULT nextval('public.contact_messages_id_seq'::regclass);


--
-- Name: contact_preferences id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.contact_preferences ALTER COLUMN id SET DEFAULT nextval('public.contact_preferences_id_seq'::regclass);


--
-- Name: corporate_codes id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.corporate_codes ALTER COLUMN id SET DEFAULT nextval('public.corporate_codes_id_seq'::regclass);


--
-- Name: cosmetic_package_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cosmetic_package_documents ALTER COLUMN id SET DEFAULT nextval('public.cosmetic_package_documents_id_seq'::regclass);


--
-- Name: cosmetic_packages id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cosmetic_packages ALTER COLUMN id SET DEFAULT nextval('public.cosmetic_packages_id_seq'::regclass);


--
-- Name: cpe_evaluation_profiles id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cpe_evaluation_profiles ALTER COLUMN id SET DEFAULT nextval('public.cpe_evaluation_profiles_id_seq'::regclass);


--
-- Name: credit_policies id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policies ALTER COLUMN id SET DEFAULT nextval('public.credit_policies_id_seq'::regclass);


--
-- Name: credit_policy_entries id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policy_entries ALTER COLUMN id SET DEFAULT nextval('public.credit_policy_entries_id_seq'::regclass);


--
-- Name: credit_policy_exception_definitions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policy_exception_definitions ALTER COLUMN id SET DEFAULT nextval('public.credit_policy_exception_definitions_id_seq'::regclass);


--
-- Name: credit_pull_details id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_pull_details ALTER COLUMN id SET DEFAULT nextval('public.credit_pull_details_id_seq'::regclass);


--
-- Name: credit_pulls id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_pulls ALTER COLUMN id SET DEFAULT nextval('public.credit_pulls_id_seq'::regclass);


--
-- Name: cudl_callbacks id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_callbacks ALTER COLUMN id SET DEFAULT nextval('public.cudl_callbacks_id_seq'::regclass);


--
-- Name: cudl_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_requests ALTER COLUMN id SET DEFAULT nextval('public.cudl_requests_id_seq'::regclass);


--
-- Name: cudl_statuses id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_statuses ALTER COLUMN id SET DEFAULT nextval('public.cudl_statuses_id_seq'::regclass);


--
-- Name: customer_loan_applications id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_loan_applications ALTER COLUMN id SET DEFAULT nextval('public.customer_loan_applications_id_seq'::regclass);


--
-- Name: customer_site_entry_locations id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_site_entry_locations ALTER COLUMN id SET DEFAULT nextval('public.customer_site_entry_locations_id_seq'::regclass);


--
-- Name: customer_vehicles id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_vehicles ALTER COLUMN id SET DEFAULT nextval('public.customer_vehicles_id_seq'::regclass);


--
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- Name: debt_cancellation_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.debt_cancellation_documents ALTER COLUMN id SET DEFAULT nextval('public.debt_cancellation_documents_id_seq'::regclass);


--
-- Name: debt_cancellations id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.debt_cancellations ALTER COLUMN id SET DEFAULT nextval('public.debt_cancellations_id_seq'::regclass);


--
-- Name: defi_callbacks id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_callbacks ALTER COLUMN id SET DEFAULT nextval('public.defi_callbacks_id_seq'::regclass);


--
-- Name: defi_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_requests ALTER COLUMN id SET DEFAULT nextval('public.defi_requests_id_seq'::regclass);


--
-- Name: defi_statuses id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_statuses ALTER COLUMN id SET DEFAULT nextval('public.defi_statuses_id_seq'::regclass);


--
-- Name: direct_mail_batches id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_batches ALTER COLUMN id SET DEFAULT nextval('public.direct_mail_batches_id_seq'::regclass);


--
-- Name: direct_mail_conversions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_conversions ALTER COLUMN id SET DEFAULT nextval('public.direct_mail_conversions_id_seq'::regclass);


--
-- Name: direct_mail_edits id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_edits ALTER COLUMN id SET DEFAULT nextval('public.direct_mail_edits_id_seq'::regclass);


--
-- Name: direct_mail_lookups id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_lookups ALTER COLUMN id SET DEFAULT nextval('public.direct_mail_lookups_id_seq'::regclass);


--
-- Name: direct_mail_recipients id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_recipients ALTER COLUMN id SET DEFAULT nextval('public.direct_mail_recipients_id_seq'::regclass);


--
-- Name: document_approvals id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_approvals ALTER COLUMN id SET DEFAULT nextval('public.document_approvals_id_seq'::regclass);


--
-- Name: document_rejections id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_rejections ALTER COLUMN id SET DEFAULT nextval('public.document_rejections_id_seq'::regclass);


--
-- Name: documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.documents ALTER COLUMN id SET DEFAULT nextval('public.documents_id_seq'::regclass);


--
-- Name: docusign_tab_details id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_tab_details ALTER COLUMN id SET DEFAULT nextval('public.docusign_tab_details_id_seq'::regclass);


--
-- Name: docusign_templates id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_templates ALTER COLUMN id SET DEFAULT nextval('public.docusign_templates_id_seq'::regclass);


--
-- Name: docusign_webhook_callbacks id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_webhook_callbacks ALTER COLUMN id SET DEFAULT nextval('public.docusign_webhook_callbacks_id_seq'::regclass);


--
-- Name: elt_numbers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.elt_numbers ALTER COLUMN id SET DEFAULT nextval('public.elt_numbers_id_seq'::regclass);


--
-- Name: employment_details id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.employment_details ALTER COLUMN id SET DEFAULT nextval('public.employment_details_id_seq'::regclass);


--
-- Name: envelope_preapprovals id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_preapprovals ALTER COLUMN id SET DEFAULT nextval('public.envelope_preapprovals_id_seq'::regclass);


--
-- Name: envelope_trial_runs id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_trial_runs ALTER COLUMN id SET DEFAULT nextval('public.envelope_trial_runs_id_seq'::regclass);


--
-- Name: envelopes id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelopes ALTER COLUMN id SET DEFAULT nextval('public.envelopes_id_seq'::regclass);


--
-- Name: external_hard_pulls id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_hard_pulls ALTER COLUMN id SET DEFAULT nextval('public.external_hard_pulls_id_seq'::regclass);


--
-- Name: external_shipments id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_shipments ALTER COLUMN id SET DEFAULT nextval('public.external_shipments_id_seq'::regclass);


--
-- Name: fake_loan_applications id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.fake_loan_applications ALTER COLUMN id SET DEFAULT nextval('public.fake_loan_applications_id_seq'::regclass);


--
-- Name: faq_questions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.faq_questions ALTER COLUMN id SET DEFAULT nextval('public.faq_questions_id_seq'::regclass);


--
-- Name: fedex_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.fedex_requests ALTER COLUMN id SET DEFAULT nextval('public.fedex_requests_id_seq'::regclass);


--
-- Name: finastra_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.finastra_requests ALTER COLUMN id SET DEFAULT nextval('public.finastra_requests_id_seq'::regclass);


--
-- Name: hard_cut_evaluation_profiles id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.hard_cut_evaluation_profiles ALTER COLUMN id SET DEFAULT nextval('public.hard_cut_evaluation_profiles_id_seq'::regclass);


--
-- Name: inbound_document_email_attachments id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_email_attachments ALTER COLUMN id SET DEFAULT nextval('public.inbound_document_email_attachments_id_seq'::regclass);


--
-- Name: inbound_document_emails id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_emails ALTER COLUMN id SET DEFAULT nextval('public.inbound_document_emails_id_seq'::regclass);


--
-- Name: inbound_document_mms id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_mms ALTER COLUMN id SET DEFAULT nextval('public.inbound_document_mms_id_seq'::regclass);


--
-- Name: its_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.its_requests ALTER COLUMN id SET DEFAULT nextval('public.its_requests_id_seq'::regclass);


--
-- Name: key_replacement_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.key_replacement_documents ALTER COLUMN id SET DEFAULT nextval('public.key_replacement_documents_id_seq'::regclass);


--
-- Name: key_replacements id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.key_replacements ALTER COLUMN id SET DEFAULT nextval('public.key_replacements_id_seq'::regclass);


--
-- Name: ldd_approvals id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_approvals ALTER COLUMN id SET DEFAULT nextval('public.ldd_approvals_id_seq'::regclass);


--
-- Name: ldd_rejections id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_rejections ALTER COLUMN id SET DEFAULT nextval('public.ldd_rejections_id_seq'::regclass);


--
-- Name: ldp_overrides id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldp_overrides ALTER COLUMN id SET DEFAULT nextval('public.ldp_overrides_id_seq'::regclass);


--
-- Name: legal_terms_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.legal_terms_documents ALTER COLUMN id SET DEFAULT nextval('public.legal_terms_documents_id_seq'::regclass);


--
-- Name: lender_addresses id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_addresses ALTER COLUMN id SET DEFAULT nextval('public.lender_addresses_id_seq'::regclass);


--
-- Name: lender_applications id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_applications ALTER COLUMN id SET DEFAULT nextval('public.lender_applications_id_seq'::regclass);


--
-- Name: lender_credit_bureau_credentials id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_credit_bureau_credentials ALTER COLUMN id SET DEFAULT nextval('public.lender_credit_bureau_credentials_id_seq'::regclass);


--
-- Name: lender_document_definitions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_document_definitions ALTER COLUMN id SET DEFAULT nextval('public.lender_document_definitions_id_seq'::regclass);


--
-- Name: lender_document_packages id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_document_packages ALTER COLUMN id SET DEFAULT nextval('public.lender_document_packages_id_seq'::regclass);


--
-- Name: lender_eligibility_constraint_failures id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_eligibility_constraint_failures ALTER COLUMN id SET DEFAULT nextval('public.lender_eligibility_constraint_failures_id_seq'::regclass);


--
-- Name: lender_eligibility_constraints id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_eligibility_constraints ALTER COLUMN id SET DEFAULT nextval('public.lender_eligibility_constraints_id_seq'::regclass);


--
-- Name: lender_preferred_score_models id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_preferred_score_models ALTER COLUMN id SET DEFAULT nextval('public.lender_preferred_score_models_id_seq'::regclass);


--
-- Name: lender_product_policies id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_product_policies ALTER COLUMN id SET DEFAULT nextval('public.lender_product_policies_id_seq'::regclass);


--
-- Name: lender_state_fees id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_state_fees ALTER COLUMN id SET DEFAULT nextval('public.lender_state_fees_id_seq'::regclass);


--
-- Name: lender_user_invitations id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_user_invitations ALTER COLUMN id SET DEFAULT nextval('public.lender_user_invitations_id_seq'::regclass);


--
-- Name: lender_users id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_users ALTER COLUMN id SET DEFAULT nextval('public.lender_users_id_seq'::regclass);


--
-- Name: lender_zip_downloads id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_zip_downloads ALTER COLUMN id SET DEFAULT nextval('public.lender_zip_downloads_id_seq'::regclass);


--
-- Name: lenders id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lenders ALTER COLUMN id SET DEFAULT nextval('public.lenders_id_seq'::regclass);


--
-- Name: loan_application_pauses id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_application_pauses ALTER COLUMN id SET DEFAULT nextval('public.loan_application_pauses_id_seq'::regclass);


--
-- Name: loan_applications id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_applications ALTER COLUMN id SET DEFAULT nextval('public.loan_applications_id_seq'::regclass);


--
-- Name: loan_archivals id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_archivals ALTER COLUMN id SET DEFAULT nextval('public.loan_archivals_id_seq'::regclass);


--
-- Name: loan_data_captures id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_data_captures ALTER COLUMN id SET DEFAULT nextval('public.loan_data_captures_id_seq'::regclass);


--
-- Name: loan_exemptions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_exemptions ALTER COLUMN id SET DEFAULT nextval('public.loan_exemptions_id_seq'::regclass);


--
-- Name: loan_referrals id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_referrals ALTER COLUMN id SET DEFAULT nextval('public.loan_referrals_id_seq'::regclass);


--
-- Name: loanspq_callbacks id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_callbacks ALTER COLUMN id SET DEFAULT nextval('public.loanspq_callbacks_id_seq'::regclass);


--
-- Name: loanspq_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_requests ALTER COLUMN id SET DEFAULT nextval('public.loanspq_requests_id_seq'::regclass);


--
-- Name: loanspq_statuses id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_statuses ALTER COLUMN id SET DEFAULT nextval('public.loanspq_statuses_id_seq'::regclass);


--
-- Name: lob_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lob_requests ALTER COLUMN id SET DEFAULT nextval('public.lob_requests_id_seq'::regclass);


--
-- Name: lui_conversions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lui_conversions ALTER COLUMN id SET DEFAULT nextval('public.lui_conversions_id_seq'::regclass);


--
-- Name: manual_loan_decisions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.manual_loan_decisions ALTER COLUMN id SET DEFAULT nextval('public.manual_loan_decisions_id_seq'::regclass);


--
-- Name: message_logs id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.message_logs ALTER COLUMN id SET DEFAULT nextval('public.message_logs_id_seq'::regclass);


--
-- Name: military_status_indications id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.military_status_indications ALTER COLUMN id SET DEFAULT nextval('public.military_status_indications_id_seq'::regclass);


--
-- Name: myautoloan_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.myautoloan_requests ALTER COLUMN id SET DEFAULT nextval('public.myautoloan_requests_id_seq'::regclass);


--
-- Name: nada_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_requests ALTER COLUMN id SET DEFAULT nextval('public.nada_requests_id_seq'::regclass);


--
-- Name: nada_tokens id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_tokens ALTER COLUMN id SET DEFAULT nextval('public.nada_tokens_id_seq'::regclass);


--
-- Name: nada_vehicle_accessories id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_vehicle_accessories ALTER COLUMN id SET DEFAULT nextval('public.nada_vehicle_accessories_id_seq'::regclass);


--
-- Name: naln_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.naln_requests ALTER COLUMN id SET DEFAULT nextval('public.naln_requests_id_seq'::regclass);


--
-- Name: noaas id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.noaas ALTER COLUMN id SET DEFAULT nextval('public.noaas_id_seq'::regclass);


--
-- Name: notes id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.notes ALTER COLUMN id SET DEFAULT nextval('public.notes_id_seq'::regclass);


--
-- Name: offer_groups id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offer_groups ALTER COLUMN id SET DEFAULT nextval('public.offer_groups_id_seq'::regclass);


--
-- Name: offer_rejections id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offer_rejections ALTER COLUMN id SET DEFAULT nextval('public.offer_rejections_id_seq'::regclass);


--
-- Name: offers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offers ALTER COLUMN id SET DEFAULT nextval('public.offers_id_seq'::regclass);


--
-- Name: open_lending_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.open_lending_requests ALTER COLUMN id SET DEFAULT nextval('public.open_lending_requests_id_seq'::regclass);


--
-- Name: opt_outs id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.opt_outs ALTER COLUMN id SET DEFAULT nextval('public.opt_outs_id_seq'::regclass);


--
-- Name: origination_percentages id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.origination_percentages ALTER COLUMN id SET DEFAULT nextval('public.origination_percentages_id_seq'::regclass);


--
-- Name: outbound_text_templates id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.outbound_text_templates ALTER COLUMN id SET DEFAULT nextval('public.outbound_text_templates_id_seq'::regclass);


--
-- Name: payoff_issuances id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_issuances ALTER COLUMN id SET DEFAULT nextval('public.payoff_issuances_id_seq'::regclass);


--
-- Name: payoff_quotes id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_quotes ALTER COLUMN id SET DEFAULT nextval('public.payoff_quotes_id_seq'::regclass);


--
-- Name: phone_applications id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_applications ALTER COLUMN id SET DEFAULT nextval('public.phone_applications_id_seq'::regclass);


--
-- Name: phone_number_texts id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_number_texts ALTER COLUMN id SET DEFAULT nextval('public.phone_number_texts_id_seq'::regclass);


--
-- Name: phone_numbers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_numbers ALTER COLUMN id SET DEFAULT nextval('public.phone_numbers_id_seq'::regclass);


--
-- Name: prequeue_data_collections id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.prequeue_data_collections ALTER COLUMN id SET DEFAULT nextval('public.prequeue_data_collections_id_seq'::regclass);


--
-- Name: processing_fees id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.processing_fees ALTER COLUMN id SET DEFAULT nextval('public.processing_fees_id_seq'::regclass);


--
-- Name: product_fees id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.product_fees ALTER COLUMN id SET DEFAULT nextval('public.product_fees_id_seq'::regclass);


--
-- Name: promax_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.promax_requests ALTER COLUMN id SET DEFAULT nextval('public.promax_requests_id_seq'::regclass);


--
-- Name: referral_codes id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.referral_codes ALTER COLUMN id SET DEFAULT nextval('public.referral_codes_id_seq'::regclass);


--
-- Name: referral_offers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.referral_offers ALTER COLUMN id SET DEFAULT nextval('public.referral_offers_id_seq'::regclass);


--
-- Name: report_runs id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.report_runs ALTER COLUMN id SET DEFAULT nextval('public.report_runs_id_seq'::regclass);


--
-- Name: sessions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.sessions ALTER COLUMN id SET DEFAULT nextval('public.sessions_id_seq'::regclass);


--
-- Name: shipments id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.shipments ALTER COLUMN id SET DEFAULT nextval('public.shipments_id_seq'::regclass);


--
-- Name: si_banking_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.si_banking_requests ALTER COLUMN id SET DEFAULT nextval('public.si_banking_requests_id_seq'::regclass);


--
-- Name: simple_sales_user_reports id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.simple_sales_user_reports ALTER COLUMN id SET DEFAULT nextval('public.simple_sales_user_reports_id_seq'::regclass);


--
-- Name: slow_tasks id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.slow_tasks ALTER COLUMN id SET DEFAULT nextval('public.slow_tasks_id_seq'::regclass);


--
-- Name: sms_records id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.sms_records ALTER COLUMN id SET DEFAULT nextval('public.sms_records_id_seq'::regclass);


--
-- Name: split_records id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.split_records ALTER COLUMN id SET DEFAULT nextval('public.split_records_id_seq'::regclass);


--
-- Name: state_apr_data id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.state_apr_data ALTER COLUMN id SET DEFAULT nextval('public.state_apr_data_id_seq'::regclass);


--
-- Name: state_fees id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.state_fees ALTER COLUMN id SET DEFAULT nextval('public.state_fees_id_seq'::regclass);


--
-- Name: supplementary_document_requirements id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.supplementary_document_requirements ALTER COLUMN id SET DEFAULT nextval('public.supplementary_document_requirements_id_seq'::regclass);


--
-- Name: texts id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.texts ALTER COLUMN id SET DEFAULT nextval('public.texts_id_seq'::regclass);


--
-- Name: title_document_transfers id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_document_transfers ALTER COLUMN id SET DEFAULT nextval('public.title_document_transfers_id_seq'::regclass);


--
-- Name: title_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_documents ALTER COLUMN id SET DEFAULT nextval('public.title_documents_id_seq'::regclass);


--
-- Name: title_poa_shipment_customer_emails id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_poa_shipment_customer_emails ALTER COLUMN id SET DEFAULT nextval('public.title_poa_shipment_customer_emails_id_seq'::regclass);


--
-- Name: tos_acceptances id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.tos_acceptances ALTER COLUMN id SET DEFAULT nextval('public.tos_acceptances_id_seq'::regclass);


--
-- Name: total_loss_protection_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.total_loss_protection_documents ALTER COLUMN id SET DEFAULT nextval('public.total_loss_protection_documents_id_seq'::regclass);


--
-- Name: total_loss_protections id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.total_loss_protections ALTER COLUMN id SET DEFAULT nextval('public.total_loss_protections_id_seq'::regclass);


--
-- Name: underwriting_exceptions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.underwriting_exceptions ALTER COLUMN id SET DEFAULT nextval('public.underwriting_exceptions_id_seq'::regclass);


--
-- Name: user_deactivations id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.user_deactivations ALTER COLUMN id SET DEFAULT nextval('public.user_deactivations_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: utm_records id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.utm_records ALTER COLUMN id SET DEFAULT nextval('public.utm_records_id_seq'::regclass);


--
-- Name: validated_income_amounts id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.validated_income_amounts ALTER COLUMN id SET DEFAULT nextval('public.validated_income_amounts_id_seq'::regclass);


--
-- Name: vehicle_service_contract_documents id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_service_contract_documents ALTER COLUMN id SET DEFAULT nextval('public.vehicle_service_contract_documents_id_seq'::regclass);


--
-- Name: vehicle_service_contracts id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_service_contracts ALTER COLUMN id SET DEFAULT nextval('public.vehicle_service_contracts_id_seq'::regclass);


--
-- Name: vehicle_titles id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_titles ALTER COLUMN id SET DEFAULT nextval('public.vehicle_titles_id_seq'::regclass);


--
-- Name: vehicles id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicles ALTER COLUMN id SET DEFAULT nextval('public.vehicles_id_seq'::regclass);


--
-- Name: versions id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.versions ALTER COLUMN id SET DEFAULT nextval('public.versions_id_seq'::regclass);


--
-- Name: wolters_kluwer_requests id; Type: DEFAULT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.wolters_kluwer_requests ALTER COLUMN id SET DEFAULT nextval('public.wolters_kluwer_requests_id_seq'::regclass);


--
-- Name: active_storage_attachments active_storage_attachments_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.active_storage_attachments
    ADD CONSTRAINT active_storage_attachments_pkey PRIMARY KEY (id);


--
-- Name: active_storage_blobs active_storage_blobs_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.active_storage_blobs
    ADD CONSTRAINT active_storage_blobs_pkey PRIMARY KEY (id);


--
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- Name: admin_sessions admin_sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.admin_sessions
    ADD CONSTRAINT admin_sessions_pkey PRIMARY KEY (id);


--
-- Name: ads_requests ads_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_requests
    ADD CONSTRAINT ads_requests_pkey PRIMARY KEY (id);


--
-- Name: ads_vehicle_service_contract_documents ads_vehicle_service_contract_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_vehicle_service_contract_documents
    ADD CONSTRAINT ads_vehicle_service_contract_documents_pkey PRIMARY KEY (id);


--
-- Name: ads_vehicle_service_contracts ads_vehicle_service_contracts_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_vehicle_service_contracts
    ADD CONSTRAINT ads_vehicle_service_contracts_pkey PRIMARY KEY (id);


--
-- Name: ahoy_events ahoy_events_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ahoy_events
    ADD CONSTRAINT ahoy_events_pkey PRIMARY KEY (id);


--
-- Name: ahoy_visits ahoy_visits_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ahoy_visits
    ADD CONSTRAINT ahoy_visits_pkey PRIMARY KEY (id);


--
-- Name: alloy_evaluations alloy_evaluations_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.alloy_evaluations
    ADD CONSTRAINT alloy_evaluations_pkey PRIMARY KEY (id);


--
-- Name: api_request_cache api_request_cache_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.api_request_cache
    ADD CONSTRAINT api_request_cache_pkey PRIMARY KEY (id);


--
-- Name: ar_internal_metadata ar_internal_metadata_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ar_internal_metadata
    ADD CONSTRAINT ar_internal_metadata_pkey PRIMARY KEY (key);


--
-- Name: authentication_tokens authentication_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.authentication_tokens
    ADD CONSTRAINT authentication_tokens_pkey PRIMARY KEY (id);


--
-- Name: auto_loan_selections auto_loan_selections_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.auto_loan_selections
    ADD CONSTRAINT auto_loan_selections_pkey PRIMARY KEY (id);


--
-- Name: autoconfiado_customers autoconfiado_customers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.autoconfiado_customers
    ADD CONSTRAINT autoconfiado_customers_pkey PRIMARY KEY (id);


--
-- Name: careers careers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.careers
    ADD CONSTRAINT careers_pkey PRIMARY KEY (id);


--
-- Name: caregard_requests caregard_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.caregard_requests
    ADD CONSTRAINT caregard_requests_pkey PRIMARY KEY (id);


--
-- Name: carfax_responses carfax_responses_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.carfax_responses
    ADD CONSTRAINT carfax_responses_pkey PRIMARY KEY (id);


--
-- Name: contact_messages contact_messages_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.contact_messages
    ADD CONSTRAINT contact_messages_pkey PRIMARY KEY (id);


--
-- Name: contact_preferences contact_preferences_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.contact_preferences
    ADD CONSTRAINT contact_preferences_pkey PRIMARY KEY (id);


--
-- Name: corporate_codes corporate_codes_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.corporate_codes
    ADD CONSTRAINT corporate_codes_pkey PRIMARY KEY (id);


--
-- Name: cosmetic_package_documents cosmetic_package_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cosmetic_package_documents
    ADD CONSTRAINT cosmetic_package_documents_pkey PRIMARY KEY (id);


--
-- Name: cosmetic_packages cosmetic_packages_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cosmetic_packages
    ADD CONSTRAINT cosmetic_packages_pkey PRIMARY KEY (id);


--
-- Name: cpe_evaluation_profiles cpe_evaluation_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cpe_evaluation_profiles
    ADD CONSTRAINT cpe_evaluation_profiles_pkey PRIMARY KEY (id);


--
-- Name: credit_policies credit_policies_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policies
    ADD CONSTRAINT credit_policies_pkey PRIMARY KEY (id);


--
-- Name: credit_policy_entries credit_policy_entries_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policy_entries
    ADD CONSTRAINT credit_policy_entries_pkey PRIMARY KEY (id);


--
-- Name: credit_policy_exception_definitions credit_policy_exception_definitions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policy_exception_definitions
    ADD CONSTRAINT credit_policy_exception_definitions_pkey PRIMARY KEY (id);


--
-- Name: credit_pull_details credit_pull_details_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_pull_details
    ADD CONSTRAINT credit_pull_details_pkey PRIMARY KEY (id);


--
-- Name: credit_pulls credit_pulls_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_pulls
    ADD CONSTRAINT credit_pulls_pkey PRIMARY KEY (id);


--
-- Name: cudl_callbacks cudl_callbacks_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_callbacks
    ADD CONSTRAINT cudl_callbacks_pkey PRIMARY KEY (id);


--
-- Name: cudl_requests cudl_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_requests
    ADD CONSTRAINT cudl_requests_pkey PRIMARY KEY (id);


--
-- Name: cudl_statuses cudl_statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_statuses
    ADD CONSTRAINT cudl_statuses_pkey PRIMARY KEY (id);


--
-- Name: customer_loan_applications customer_loan_applications_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_loan_applications
    ADD CONSTRAINT customer_loan_applications_pkey PRIMARY KEY (id);


--
-- Name: customer_site_entry_locations customer_site_entry_locations_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_site_entry_locations
    ADD CONSTRAINT customer_site_entry_locations_pkey PRIMARY KEY (id);


--
-- Name: customer_vehicles customer_vehicles_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_vehicles
    ADD CONSTRAINT customer_vehicles_pkey PRIMARY KEY (id);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: debt_cancellation_documents debt_cancellation_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.debt_cancellation_documents
    ADD CONSTRAINT debt_cancellation_documents_pkey PRIMARY KEY (id);


--
-- Name: debt_cancellations debt_cancellations_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.debt_cancellations
    ADD CONSTRAINT debt_cancellations_pkey PRIMARY KEY (id);


--
-- Name: defi_callbacks defi_callbacks_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_callbacks
    ADD CONSTRAINT defi_callbacks_pkey PRIMARY KEY (id);


--
-- Name: defi_requests defi_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_requests
    ADD CONSTRAINT defi_requests_pkey PRIMARY KEY (id);


--
-- Name: defi_statuses defi_statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_statuses
    ADD CONSTRAINT defi_statuses_pkey PRIMARY KEY (id);


--
-- Name: direct_mail_batches direct_mail_batches_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_batches
    ADD CONSTRAINT direct_mail_batches_pkey PRIMARY KEY (id);


--
-- Name: direct_mail_conversions direct_mail_conversions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_conversions
    ADD CONSTRAINT direct_mail_conversions_pkey PRIMARY KEY (id);


--
-- Name: direct_mail_edits direct_mail_edits_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_edits
    ADD CONSTRAINT direct_mail_edits_pkey PRIMARY KEY (id);


--
-- Name: direct_mail_lookups direct_mail_lookups_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_lookups
    ADD CONSTRAINT direct_mail_lookups_pkey PRIMARY KEY (id);


--
-- Name: direct_mail_recipients direct_mail_recipients_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_recipients
    ADD CONSTRAINT direct_mail_recipients_pkey PRIMARY KEY (id);


--
-- Name: document_approvals document_approvals_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_approvals
    ADD CONSTRAINT document_approvals_pkey PRIMARY KEY (id);


--
-- Name: document_rejections document_rejections_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_rejections
    ADD CONSTRAINT document_rejections_pkey PRIMARY KEY (id);


--
-- Name: documents documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_pkey PRIMARY KEY (id);


--
-- Name: docusign_tab_details docusign_tab_details_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_tab_details
    ADD CONSTRAINT docusign_tab_details_pkey PRIMARY KEY (id);


--
-- Name: docusign_templates docusign_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_templates
    ADD CONSTRAINT docusign_templates_pkey PRIMARY KEY (id);


--
-- Name: docusign_webhook_callbacks docusign_webhook_callbacks_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_webhook_callbacks
    ADD CONSTRAINT docusign_webhook_callbacks_pkey PRIMARY KEY (id);


--
-- Name: elt_numbers elt_numbers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.elt_numbers
    ADD CONSTRAINT elt_numbers_pkey PRIMARY KEY (id);


--
-- Name: employment_details employment_details_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.employment_details
    ADD CONSTRAINT employment_details_pkey PRIMARY KEY (id);


--
-- Name: envelope_preapprovals envelope_preapprovals_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_preapprovals
    ADD CONSTRAINT envelope_preapprovals_pkey PRIMARY KEY (id);


--
-- Name: envelope_trial_runs envelope_trial_runs_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_trial_runs
    ADD CONSTRAINT envelope_trial_runs_pkey PRIMARY KEY (id);


--
-- Name: envelopes envelopes_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelopes
    ADD CONSTRAINT envelopes_pkey PRIMARY KEY (id);


--
-- Name: external_hard_pulls external_hard_pulls_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_hard_pulls
    ADD CONSTRAINT external_hard_pulls_pkey PRIMARY KEY (id);


--
-- Name: external_shipments external_shipments_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_shipments
    ADD CONSTRAINT external_shipments_pkey PRIMARY KEY (id);


--
-- Name: fake_loan_applications fake_loan_applications_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.fake_loan_applications
    ADD CONSTRAINT fake_loan_applications_pkey PRIMARY KEY (id);


--
-- Name: faq_questions faq_questions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.faq_questions
    ADD CONSTRAINT faq_questions_pkey PRIMARY KEY (id);


--
-- Name: fedex_requests fedex_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.fedex_requests
    ADD CONSTRAINT fedex_requests_pkey PRIMARY KEY (id);


--
-- Name: finastra_requests finastra_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.finastra_requests
    ADD CONSTRAINT finastra_requests_pkey PRIMARY KEY (id);


--
-- Name: hard_cut_evaluation_profiles hard_cut_evaluation_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.hard_cut_evaluation_profiles
    ADD CONSTRAINT hard_cut_evaluation_profiles_pkey PRIMARY KEY (id);


--
-- Name: inbound_document_email_attachments inbound_document_email_attachments_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_email_attachments
    ADD CONSTRAINT inbound_document_email_attachments_pkey PRIMARY KEY (id);


--
-- Name: inbound_document_emails inbound_document_emails_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_emails
    ADD CONSTRAINT inbound_document_emails_pkey PRIMARY KEY (id);


--
-- Name: inbound_document_mms inbound_document_mms_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_mms
    ADD CONSTRAINT inbound_document_mms_pkey PRIMARY KEY (id);


--
-- Name: its_requests its_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.its_requests
    ADD CONSTRAINT its_requests_pkey PRIMARY KEY (id);


--
-- Name: key_replacement_documents key_replacement_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.key_replacement_documents
    ADD CONSTRAINT key_replacement_documents_pkey PRIMARY KEY (id);


--
-- Name: key_replacements key_replacements_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.key_replacements
    ADD CONSTRAINT key_replacements_pkey PRIMARY KEY (id);


--
-- Name: ldd_approvals ldd_approvals_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_approvals
    ADD CONSTRAINT ldd_approvals_pkey PRIMARY KEY (id);


--
-- Name: ldd_rejections ldd_rejections_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_rejections
    ADD CONSTRAINT ldd_rejections_pkey PRIMARY KEY (id);


--
-- Name: ldp_overrides ldp_overrides_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldp_overrides
    ADD CONSTRAINT ldp_overrides_pkey PRIMARY KEY (id);


--
-- Name: legal_terms_documents legal_terms_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.legal_terms_documents
    ADD CONSTRAINT legal_terms_documents_pkey PRIMARY KEY (id);


--
-- Name: lender_addresses lender_addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_addresses
    ADD CONSTRAINT lender_addresses_pkey PRIMARY KEY (id);


--
-- Name: lender_applications lender_applications_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_applications
    ADD CONSTRAINT lender_applications_pkey PRIMARY KEY (id);


--
-- Name: lender_credit_bureau_credentials lender_credit_bureau_credentials_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_credit_bureau_credentials
    ADD CONSTRAINT lender_credit_bureau_credentials_pkey PRIMARY KEY (id);


--
-- Name: lender_document_definitions lender_document_definitions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_document_definitions
    ADD CONSTRAINT lender_document_definitions_pkey PRIMARY KEY (id);


--
-- Name: lender_document_packages lender_document_packages_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_document_packages
    ADD CONSTRAINT lender_document_packages_pkey PRIMARY KEY (id);


--
-- Name: lender_eligibility_constraint_failures lender_eligibility_constraint_failures_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_eligibility_constraint_failures
    ADD CONSTRAINT lender_eligibility_constraint_failures_pkey PRIMARY KEY (id);


--
-- Name: lender_eligibility_constraints lender_eligibility_constraints_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_eligibility_constraints
    ADD CONSTRAINT lender_eligibility_constraints_pkey PRIMARY KEY (id);


--
-- Name: lender_preferred_score_models lender_preferred_score_models_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_preferred_score_models
    ADD CONSTRAINT lender_preferred_score_models_pkey PRIMARY KEY (id);


--
-- Name: lender_product_policies lender_product_policies_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_product_policies
    ADD CONSTRAINT lender_product_policies_pkey PRIMARY KEY (id);


--
-- Name: lender_state_fees lender_state_fees_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_state_fees
    ADD CONSTRAINT lender_state_fees_pkey PRIMARY KEY (id);


--
-- Name: lender_user_invitations lender_user_invitations_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_user_invitations
    ADD CONSTRAINT lender_user_invitations_pkey PRIMARY KEY (id);


--
-- Name: lender_users lender_users_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_users
    ADD CONSTRAINT lender_users_pkey PRIMARY KEY (id);


--
-- Name: lender_zip_downloads lender_zip_downloads_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_zip_downloads
    ADD CONSTRAINT lender_zip_downloads_pkey PRIMARY KEY (id);


--
-- Name: lenders lenders_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lenders
    ADD CONSTRAINT lenders_pkey PRIMARY KEY (id);


--
-- Name: loan_application_pauses loan_application_pauses_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_application_pauses
    ADD CONSTRAINT loan_application_pauses_pkey PRIMARY KEY (id);


--
-- Name: loan_applications loan_applications_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_applications
    ADD CONSTRAINT loan_applications_pkey PRIMARY KEY (id);


--
-- Name: loan_archivals loan_archivals_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_archivals
    ADD CONSTRAINT loan_archivals_pkey PRIMARY KEY (id);


--
-- Name: loan_data_captures loan_data_captures_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_data_captures
    ADD CONSTRAINT loan_data_captures_pkey PRIMARY KEY (id);


--
-- Name: loan_exemptions loan_exemptions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_exemptions
    ADD CONSTRAINT loan_exemptions_pkey PRIMARY KEY (id);


--
-- Name: loan_referrals loan_referrals_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_referrals
    ADD CONSTRAINT loan_referrals_pkey PRIMARY KEY (id);


--
-- Name: loanspq_callbacks loanspq_callbacks_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_callbacks
    ADD CONSTRAINT loanspq_callbacks_pkey PRIMARY KEY (id);


--
-- Name: loanspq_requests loanspq_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_requests
    ADD CONSTRAINT loanspq_requests_pkey PRIMARY KEY (id);


--
-- Name: loanspq_statuses loanspq_statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_statuses
    ADD CONSTRAINT loanspq_statuses_pkey PRIMARY KEY (id);


--
-- Name: lob_requests lob_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lob_requests
    ADD CONSTRAINT lob_requests_pkey PRIMARY KEY (id);


--
-- Name: lui_conversions lui_conversions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lui_conversions
    ADD CONSTRAINT lui_conversions_pkey PRIMARY KEY (id);


--
-- Name: manual_loan_decisions manual_loan_decisions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.manual_loan_decisions
    ADD CONSTRAINT manual_loan_decisions_pkey PRIMARY KEY (id);


--
-- Name: message_logs message_logs_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.message_logs
    ADD CONSTRAINT message_logs_pkey PRIMARY KEY (id);


--
-- Name: military_status_indications military_status_indications_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.military_status_indications
    ADD CONSTRAINT military_status_indications_pkey PRIMARY KEY (id);


--
-- Name: myautoloan_requests myautoloan_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.myautoloan_requests
    ADD CONSTRAINT myautoloan_requests_pkey PRIMARY KEY (id);


--
-- Name: nada_requests nada_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_requests
    ADD CONSTRAINT nada_requests_pkey PRIMARY KEY (id);


--
-- Name: nada_tokens nada_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_tokens
    ADD CONSTRAINT nada_tokens_pkey PRIMARY KEY (id);


--
-- Name: nada_vehicle_accessories nada_vehicle_accessories_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_vehicle_accessories
    ADD CONSTRAINT nada_vehicle_accessories_pkey PRIMARY KEY (id);


--
-- Name: naln_requests naln_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.naln_requests
    ADD CONSTRAINT naln_requests_pkey PRIMARY KEY (id);


--
-- Name: noaas noaas_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.noaas
    ADD CONSTRAINT noaas_pkey PRIMARY KEY (id);


--
-- Name: notes notes_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.notes
    ADD CONSTRAINT notes_pkey PRIMARY KEY (id);


--
-- Name: offer_groups offer_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offer_groups
    ADD CONSTRAINT offer_groups_pkey PRIMARY KEY (id);


--
-- Name: offer_rejections offer_rejections_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offer_rejections
    ADD CONSTRAINT offer_rejections_pkey PRIMARY KEY (id);


--
-- Name: offers offers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT offers_pkey PRIMARY KEY (id);


--
-- Name: open_lending_requests open_lending_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.open_lending_requests
    ADD CONSTRAINT open_lending_requests_pkey PRIMARY KEY (id);


--
-- Name: opt_outs opt_outs_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.opt_outs
    ADD CONSTRAINT opt_outs_pkey PRIMARY KEY (id);


--
-- Name: origination_percentages origination_percentages_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.origination_percentages
    ADD CONSTRAINT origination_percentages_pkey PRIMARY KEY (id);


--
-- Name: outbound_text_templates outbound_text_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.outbound_text_templates
    ADD CONSTRAINT outbound_text_templates_pkey PRIMARY KEY (id);


--
-- Name: payoff_issuances payoff_issuances_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_issuances
    ADD CONSTRAINT payoff_issuances_pkey PRIMARY KEY (id);


--
-- Name: payoff_quotes payoff_quotes_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_quotes
    ADD CONSTRAINT payoff_quotes_pkey PRIMARY KEY (id);


--
-- Name: phone_applications phone_applications_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_applications
    ADD CONSTRAINT phone_applications_pkey PRIMARY KEY (id);


--
-- Name: phone_number_texts phone_number_texts_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_number_texts
    ADD CONSTRAINT phone_number_texts_pkey PRIMARY KEY (id);


--
-- Name: phone_numbers phone_numbers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_numbers
    ADD CONSTRAINT phone_numbers_pkey PRIMARY KEY (id);


--
-- Name: prequeue_data_collections prequeue_data_collections_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.prequeue_data_collections
    ADD CONSTRAINT prequeue_data_collections_pkey PRIMARY KEY (id);


--
-- Name: processing_fees processing_fees_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.processing_fees
    ADD CONSTRAINT processing_fees_pkey PRIMARY KEY (id);


--
-- Name: product_fees product_fees_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.product_fees
    ADD CONSTRAINT product_fees_pkey PRIMARY KEY (id);


--
-- Name: promax_requests promax_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.promax_requests
    ADD CONSTRAINT promax_requests_pkey PRIMARY KEY (id);


--
-- Name: referral_codes referral_codes_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.referral_codes
    ADD CONSTRAINT referral_codes_pkey PRIMARY KEY (id);


--
-- Name: referral_offers referral_offers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.referral_offers
    ADD CONSTRAINT referral_offers_pkey PRIMARY KEY (id);


--
-- Name: report_runs report_runs_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.report_runs
    ADD CONSTRAINT report_runs_pkey PRIMARY KEY (id);


--
-- Name: schema_migrations schema_migrations_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.schema_migrations
    ADD CONSTRAINT schema_migrations_pkey PRIMARY KEY (version);


--
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- Name: shipments shipments_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.shipments
    ADD CONSTRAINT shipments_pkey PRIMARY KEY (id);


--
-- Name: si_banking_requests si_banking_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.si_banking_requests
    ADD CONSTRAINT si_banking_requests_pkey PRIMARY KEY (id);


--
-- Name: simple_sales_user_reports simple_sales_user_reports_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.simple_sales_user_reports
    ADD CONSTRAINT simple_sales_user_reports_pkey PRIMARY KEY (id);


--
-- Name: slow_tasks slow_tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.slow_tasks
    ADD CONSTRAINT slow_tasks_pkey PRIMARY KEY (id);


--
-- Name: sms_records sms_records_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.sms_records
    ADD CONSTRAINT sms_records_pkey PRIMARY KEY (id);


--
-- Name: split_records split_records_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.split_records
    ADD CONSTRAINT split_records_pkey PRIMARY KEY (id);


--
-- Name: state_apr_data state_apr_data_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.state_apr_data
    ADD CONSTRAINT state_apr_data_pkey PRIMARY KEY (id);


--
-- Name: state_fees state_fees_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.state_fees
    ADD CONSTRAINT state_fees_pkey PRIMARY KEY (id);


--
-- Name: supplementary_document_requirements supplementary_document_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.supplementary_document_requirements
    ADD CONSTRAINT supplementary_document_requirements_pkey PRIMARY KEY (id);


--
-- Name: texts texts_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.texts
    ADD CONSTRAINT texts_pkey PRIMARY KEY (id);


--
-- Name: title_document_transfers title_document_transfers_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_document_transfers
    ADD CONSTRAINT title_document_transfers_pkey PRIMARY KEY (id);


--
-- Name: title_documents title_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_documents
    ADD CONSTRAINT title_documents_pkey PRIMARY KEY (id);


--
-- Name: title_poa_shipment_customer_emails title_poa_shipment_customer_emails_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_poa_shipment_customer_emails
    ADD CONSTRAINT title_poa_shipment_customer_emails_pkey PRIMARY KEY (id);


--
-- Name: tos_acceptances tos_acceptances_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.tos_acceptances
    ADD CONSTRAINT tos_acceptances_pkey PRIMARY KEY (id);


--
-- Name: total_loss_protection_documents total_loss_protection_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.total_loss_protection_documents
    ADD CONSTRAINT total_loss_protection_documents_pkey PRIMARY KEY (id);


--
-- Name: total_loss_protections total_loss_protections_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.total_loss_protections
    ADD CONSTRAINT total_loss_protections_pkey PRIMARY KEY (id);


--
-- Name: underwriting_exceptions underwriting_exceptions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.underwriting_exceptions
    ADD CONSTRAINT underwriting_exceptions_pkey PRIMARY KEY (id);


--
-- Name: user_deactivations user_deactivations_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.user_deactivations
    ADD CONSTRAINT user_deactivations_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: utm_records utm_records_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.utm_records
    ADD CONSTRAINT utm_records_pkey PRIMARY KEY (id);


--
-- Name: validated_income_amounts validated_income_amounts_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.validated_income_amounts
    ADD CONSTRAINT validated_income_amounts_pkey PRIMARY KEY (id);


--
-- Name: vehicle_service_contract_documents vehicle_service_contract_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_service_contract_documents
    ADD CONSTRAINT vehicle_service_contract_documents_pkey PRIMARY KEY (id);


--
-- Name: vehicle_service_contracts vehicle_service_contracts_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_service_contracts
    ADD CONSTRAINT vehicle_service_contracts_pkey PRIMARY KEY (id);


--
-- Name: vehicle_titles vehicle_titles_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_titles
    ADD CONSTRAINT vehicle_titles_pkey PRIMARY KEY (id);


--
-- Name: vehicles vehicles_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_pkey PRIMARY KEY (id);


--
-- Name: versions versions_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.versions
    ADD CONSTRAINT versions_pkey PRIMARY KEY (id);


--
-- Name: wolters_kluwer_requests wolters_kluwer_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.wolters_kluwer_requests
    ADD CONSTRAINT wolters_kluwer_requests_pkey PRIMARY KEY (id);


--
-- Name: ads_vsc_id_index; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX ads_vsc_id_index ON public.ads_vehicle_service_contract_documents USING btree (ads_vehicle_service_contract_id);


--
-- Name: customers_to_tsvector_idx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX customers_to_tsvector_idx ON public.customers USING gin (to_tsvector('english'::regconfig, (first_name)::text));


--
-- Name: customers_to_tsvector_idx1; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX customers_to_tsvector_idx1 ON public.customers USING gin (to_tsvector('english'::regconfig, (last_name)::text));


--
-- Name: customers_to_tsvector_idx2; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX customers_to_tsvector_idx2 ON public.customers USING gin (to_tsvector('english'::regconfig, (email)::text));


--
-- Name: direct_mail_recipients_to_tsvector_idx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX direct_mail_recipients_to_tsvector_idx ON public.direct_mail_recipients USING gin (to_tsvector('english'::regconfig, (last_name)::text));


--
-- Name: idx_cpe_ue; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX idx_cpe_ue ON public.underwriting_exceptions USING btree (credit_policy_entry_id);


--
-- Name: idx_cped_ue; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX idx_cped_ue ON public.underwriting_exceptions USING btree (credit_policy_exception_definition_id);


--
-- Name: idx_vsc_document_on_authorization_code; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX idx_vsc_document_on_authorization_code ON public.vehicle_service_contract_documents USING btree (authorization_code);


--
-- Name: idx_vsc_document_on_contract_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX idx_vsc_document_on_contract_identifier ON public.vehicle_service_contract_documents USING btree (contract_identifier);


--
-- Name: idx_vsc_document_on_vsc_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX idx_vsc_document_on_vsc_id ON public.vehicle_service_contract_documents USING btree (vehicle_service_contract_id);


--
-- Name: inbound_document_email_attachments_inbound_document_email; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX inbound_document_email_attachments_inbound_document_email ON public.inbound_document_email_attachments USING btree (inbound_document_email_id);


--
-- Name: index_active_storage_attachments_on_blob_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_active_storage_attachments_on_blob_id ON public.active_storage_attachments USING btree (blob_id);


--
-- Name: index_active_storage_attachments_uniqueness; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_active_storage_attachments_uniqueness ON public.active_storage_attachments USING btree (record_type, record_id, name, blob_id);


--
-- Name: index_active_storage_blobs_on_key; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_active_storage_blobs_on_key ON public.active_storage_blobs USING btree (key);


--
-- Name: index_addresses_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_addresses_on_customer_id ON public.addresses USING btree (customer_id);


--
-- Name: index_addresses_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_addresses_on_loan_application_id ON public.addresses USING btree (loan_application_id);


--
-- Name: index_admin_sessions_on_created_at; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_admin_sessions_on_created_at ON public.admin_sessions USING btree (created_at);


--
-- Name: index_admin_sessions_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_admin_sessions_on_user_id ON public.admin_sessions USING btree (user_id);


--
-- Name: index_admin_sessions_on_uuid; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_admin_sessions_on_uuid ON public.admin_sessions USING btree (uuid);


--
-- Name: index_ads_vehicle_service_contracts_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ads_vehicle_service_contracts_on_aasm_state ON public.ads_vehicle_service_contracts USING btree (aasm_state);


--
-- Name: index_ads_vehicle_service_contracts_on_contract_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ads_vehicle_service_contracts_on_contract_identifier ON public.ads_vehicle_service_contracts USING btree (contract_identifier);


--
-- Name: index_ads_vehicle_service_contracts_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ads_vehicle_service_contracts_on_loan_application_id ON public.ads_vehicle_service_contracts USING btree (loan_application_id);


--
-- Name: index_ahoy_events_on_name_and_time; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ahoy_events_on_name_and_time ON public.ahoy_events USING btree (name, "time");


--
-- Name: index_ahoy_events_on_properties_jsonb_path_ops; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ahoy_events_on_properties_jsonb_path_ops ON public.ahoy_events USING gin (properties jsonb_path_ops);


--
-- Name: index_ahoy_events_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ahoy_events_on_user_id ON public.ahoy_events USING btree (user_id);


--
-- Name: index_ahoy_events_on_visit_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ahoy_events_on_visit_id ON public.ahoy_events USING btree (visit_id);


--
-- Name: index_ahoy_visits_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ahoy_visits_on_user_id ON public.ahoy_visits USING btree (user_id);


--
-- Name: index_ahoy_visits_on_visit_token; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_ahoy_visits_on_visit_token ON public.ahoy_visits USING btree (visit_token);


--
-- Name: index_alloy_evaluations_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_alloy_evaluations_on_customer_id ON public.alloy_evaluations USING btree (customer_id);


--
-- Name: index_alloy_evaluations_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_alloy_evaluations_on_loan_application_id ON public.alloy_evaluations USING btree (loan_application_id);


--
-- Name: index_api_request_cache_on_request_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_api_request_cache_on_request_name ON public.api_request_cache USING btree (request_name);


--
-- Name: index_api_request_cache_on_request_name_and_request_params; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_api_request_cache_on_request_name_and_request_params ON public.api_request_cache USING btree (request_name, request_params);


--
-- Name: index_api_request_cache_on_request_params; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_api_request_cache_on_request_params ON public.api_request_cache USING btree (request_params);


--
-- Name: index_authentication_tokens_on_context; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_authentication_tokens_on_context ON public.authentication_tokens USING btree (context);


--
-- Name: index_authentication_tokens_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_authentication_tokens_on_loan_application_id ON public.authentication_tokens USING btree (loan_application_id);


--
-- Name: index_authentication_tokens_on_token; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_authentication_tokens_on_token ON public.authentication_tokens USING btree (token);


--
-- Name: index_auto_loan_selections_on_credit_pull_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_auto_loan_selections_on_credit_pull_id ON public.auto_loan_selections USING btree (credit_pull_id);


--
-- Name: index_auto_loan_selections_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_auto_loan_selections_on_loan_application_id ON public.auto_loan_selections USING btree (loan_application_id);


--
-- Name: index_autoconfiado_customers_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_autoconfiado_customers_on_customer_id ON public.autoconfiado_customers USING btree (customer_id);


--
-- Name: index_careers_on_key; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_careers_on_key ON public.careers USING btree (key);


--
-- Name: index_caregard_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_caregard_requests_on_loan_application_id ON public.caregard_requests USING btree (loan_application_id);


--
-- Name: index_contact_preferences_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_contact_preferences_on_customer_id ON public.contact_preferences USING btree (customer_id);


--
-- Name: index_corporate_codes_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_corporate_codes_on_lender_id ON public.corporate_codes USING btree (lender_id);


--
-- Name: index_cosmetic_package_documents_on_cosmetic_package_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cosmetic_package_documents_on_cosmetic_package_id ON public.cosmetic_package_documents USING btree (cosmetic_package_id);


--
-- Name: index_cosmetic_packages_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cosmetic_packages_on_aasm_state ON public.cosmetic_packages USING btree (aasm_state);


--
-- Name: index_cosmetic_packages_on_contract_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cosmetic_packages_on_contract_identifier ON public.cosmetic_packages USING btree (contract_identifier);


--
-- Name: index_cosmetic_packages_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cosmetic_packages_on_loan_application_id ON public.cosmetic_packages USING btree (loan_application_id);


--
-- Name: index_cosmetic_packages_on_product_class; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cosmetic_packages_on_product_class ON public.cosmetic_packages USING btree (product_class);


--
-- Name: index_cpe_evaluation_profiles_on_credit_policy_entry_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cpe_evaluation_profiles_on_credit_policy_entry_id ON public.cpe_evaluation_profiles USING btree (credit_policy_entry_id);


--
-- Name: index_cpe_evaluation_profiles_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cpe_evaluation_profiles_on_customer_id ON public.cpe_evaluation_profiles USING btree (customer_id);


--
-- Name: index_cpe_evaluation_profiles_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cpe_evaluation_profiles_on_loan_application_id ON public.cpe_evaluation_profiles USING btree (loan_application_id);


--
-- Name: index_credit_policies_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_policies_on_lender_id ON public.credit_policies USING btree (lender_id);


--
-- Name: index_credit_policy_entries_on_credit_policy_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_policy_entries_on_credit_policy_id ON public.credit_policy_entries USING btree (credit_policy_id);


--
-- Name: index_credit_policy_entries_on_minimum_credit_score; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_policy_entries_on_minimum_credit_score ON public.credit_policy_entries USING btree (minimum_credit_score);


--
-- Name: index_credit_policy_entries_on_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_policy_entries_on_state ON public.credit_policy_entries USING btree (state);


--
-- Name: index_credit_policy_exception_definitions_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_policy_exception_definitions_on_lender_id ON public.credit_policy_exception_definitions USING btree (lender_id);


--
-- Name: index_credit_pull_details_on_credit_pull_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_pull_details_on_credit_pull_id ON public.credit_pull_details USING btree (credit_pull_id);


--
-- Name: index_credit_pulls_on_credit_bureau_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_pulls_on_credit_bureau_name ON public.credit_pulls USING btree (credit_bureau_name);


--
-- Name: index_credit_pulls_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_credit_pulls_on_customer_id ON public.credit_pulls USING btree (customer_id);


--
-- Name: index_cudl_callbacks_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cudl_callbacks_on_loan_application_id ON public.cudl_callbacks USING btree (loan_application_id);


--
-- Name: index_cudl_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cudl_requests_on_loan_application_id ON public.cudl_requests USING btree (loan_application_id);


--
-- Name: index_cudl_statuses_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_cudl_statuses_on_loan_application_id ON public.cudl_statuses USING btree (loan_application_id);


--
-- Name: index_customer_loan_applications_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_customer_loan_applications_on_customer_id ON public.customer_loan_applications USING btree (customer_id);


--
-- Name: index_customer_loan_applications_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_customer_loan_applications_on_loan_application_id ON public.customer_loan_applications USING btree (loan_application_id);


--
-- Name: index_customer_site_entry_locations_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_customer_site_entry_locations_on_customer_id ON public.customer_site_entry_locations USING btree (customer_id);


--
-- Name: index_customer_site_entry_locations_on_utm_record_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_customer_site_entry_locations_on_utm_record_id ON public.customer_site_entry_locations USING btree (utm_record_id);


--
-- Name: index_customer_vehicles_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_customer_vehicles_on_customer_id ON public.customer_vehicles USING btree (customer_id);


--
-- Name: index_customer_vehicles_on_vehicle_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_customer_vehicles_on_vehicle_id ON public.customer_vehicles USING btree (vehicle_id);


--
-- Name: index_customers_on_email; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_customers_on_email ON public.customers USING btree (email);


--
-- Name: index_customers_on_encrypted_ssn; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_customers_on_encrypted_ssn ON public.customers USING btree (encrypted_ssn);


--
-- Name: index_debt_cancellation_documents_on_debt_cancellation_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_debt_cancellation_documents_on_debt_cancellation_id ON public.debt_cancellation_documents USING btree (debt_cancellation_id);


--
-- Name: index_debt_cancellations_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_debt_cancellations_on_aasm_state ON public.debt_cancellations USING btree (aasm_state);


--
-- Name: index_debt_cancellations_on_contract_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_debt_cancellations_on_contract_identifier ON public.debt_cancellations USING btree (contract_identifier);


--
-- Name: index_debt_cancellations_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_debt_cancellations_on_loan_application_id ON public.debt_cancellations USING btree (loan_application_id);


--
-- Name: index_defi_callbacks_on_external_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_defi_callbacks_on_external_id ON public.defi_callbacks USING btree (external_id);


--
-- Name: index_defi_callbacks_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_defi_callbacks_on_loan_application_id ON public.defi_callbacks USING btree (loan_application_id);


--
-- Name: index_defi_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_defi_requests_on_loan_application_id ON public.defi_requests USING btree (loan_application_id);


--
-- Name: index_defi_statuses_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_defi_statuses_on_loan_application_id ON public.defi_statuses USING btree (loan_application_id);


--
-- Name: index_direct_mail_conversions_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_conversions_on_customer_id ON public.direct_mail_conversions USING btree (customer_id);


--
-- Name: index_direct_mail_conversions_on_direct_mail_recipient_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_conversions_on_direct_mail_recipient_id ON public.direct_mail_conversions USING btree (direct_mail_recipient_id);


--
-- Name: index_direct_mail_conversions_on_label; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_conversions_on_label ON public.direct_mail_conversions USING btree (label);


--
-- Name: index_direct_mail_edits_on_direct_mail_recipient_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_edits_on_direct_mail_recipient_id ON public.direct_mail_edits USING btree (direct_mail_recipient_id);


--
-- Name: index_direct_mail_lookups_on_code; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_lookups_on_code ON public.direct_mail_lookups USING btree (code);


--
-- Name: index_direct_mail_lookups_on_direct_mail_recipient_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_lookups_on_direct_mail_recipient_id ON public.direct_mail_lookups USING btree (direct_mail_recipient_id);


--
-- Name: index_direct_mail_recipients_on_direct_mail_batch_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_recipients_on_direct_mail_batch_id ON public.direct_mail_recipients USING btree (direct_mail_batch_id);


--
-- Name: index_direct_mail_recipients_on_last_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_recipients_on_last_name ON public.direct_mail_recipients USING btree (last_name);


--
-- Name: index_direct_mail_recipients_on_lookup_code; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_direct_mail_recipients_on_lookup_code ON public.direct_mail_recipients USING btree (lookup_code);


--
-- Name: index_direct_mail_recipients_on_permid; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_direct_mail_recipients_on_permid ON public.direct_mail_recipients USING btree (permid);


--
-- Name: index_document_approvals_on_document_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_document_approvals_on_document_id ON public.document_approvals USING btree (document_id);


--
-- Name: index_document_approvals_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_document_approvals_on_user_id ON public.document_approvals USING btree (user_id);


--
-- Name: index_document_rejections_on_document_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_document_rejections_on_document_id ON public.document_rejections USING btree (document_id);


--
-- Name: index_document_rejections_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_document_rejections_on_user_id ON public.document_rejections USING btree (user_id);


--
-- Name: index_documents_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_documents_on_customer_id ON public.documents USING btree (customer_id);


--
-- Name: index_documents_on_envelope_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_documents_on_envelope_id ON public.documents USING btree (envelope_id);


--
-- Name: index_documents_on_lender_document_definition_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_documents_on_lender_document_definition_id ON public.documents USING btree (lender_document_definition_id);


--
-- Name: index_documents_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_documents_on_loan_application_id ON public.documents USING btree (loan_application_id);


--
-- Name: index_docusign_tab_details_on_document_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_docusign_tab_details_on_document_id ON public.docusign_tab_details USING btree (document_id);


--
-- Name: index_docusign_templates_on_docusign_uuid; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_docusign_templates_on_docusign_uuid ON public.docusign_templates USING btree (docusign_uuid);


--
-- Name: index_docusign_templates_on_lender_document_definition_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_docusign_templates_on_lender_document_definition_id ON public.docusign_templates USING btree (lender_document_definition_id);


--
-- Name: index_elt_numbers_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_elt_numbers_on_lender_id ON public.elt_numbers USING btree (lender_id);


--
-- Name: index_employment_details_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_employment_details_on_customer_id ON public.employment_details USING btree (customer_id);


--
-- Name: index_envelope_preapprovals_on_envelope_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_envelope_preapprovals_on_envelope_id ON public.envelope_preapprovals USING btree (envelope_id);


--
-- Name: index_envelope_preapprovals_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_envelope_preapprovals_on_user_id ON public.envelope_preapprovals USING btree (user_id);


--
-- Name: index_envelope_trial_runs_on_created_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_envelope_trial_runs_on_created_by_id ON public.envelope_trial_runs USING btree (created_by_id);


--
-- Name: index_envelope_trial_runs_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_envelope_trial_runs_on_loan_application_id ON public.envelope_trial_runs USING btree (loan_application_id);


--
-- Name: index_envelopes_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_envelopes_on_loan_application_id ON public.envelopes USING btree (loan_application_id);


--
-- Name: index_external_hard_pulls_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_external_hard_pulls_on_customer_id ON public.external_hard_pulls USING btree (customer_id);


--
-- Name: index_external_hard_pulls_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_external_hard_pulls_on_loan_application_id ON public.external_hard_pulls USING btree (loan_application_id);


--
-- Name: index_external_shipments_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_external_shipments_on_loan_application_id ON public.external_shipments USING btree (loan_application_id);


--
-- Name: index_external_shipments_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_external_shipments_on_user_id ON public.external_shipments USING btree (user_id);


--
-- Name: index_fake_loan_applications_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_fake_loan_applications_on_loan_application_id ON public.fake_loan_applications USING btree (loan_application_id);


--
-- Name: index_fedex_requests_on_address_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_fedex_requests_on_address_id ON public.fedex_requests USING btree (address_id);


--
-- Name: index_fedex_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_fedex_requests_on_loan_application_id ON public.fedex_requests USING btree (loan_application_id);


--
-- Name: index_finastra_requests_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_finastra_requests_on_lender_id ON public.finastra_requests USING btree (lender_id);


--
-- Name: index_finastra_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_finastra_requests_on_loan_application_id ON public.finastra_requests USING btree (loan_application_id);


--
-- Name: index_hard_cut_evaluation_profiles_on_credit_policy_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_hard_cut_evaluation_profiles_on_credit_policy_id ON public.hard_cut_evaluation_profiles USING btree (credit_policy_id);


--
-- Name: index_hard_cut_evaluation_profiles_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_hard_cut_evaluation_profiles_on_customer_id ON public.hard_cut_evaluation_profiles USING btree (customer_id);


--
-- Name: index_hard_cut_evaluation_profiles_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_hard_cut_evaluation_profiles_on_loan_application_id ON public.hard_cut_evaluation_profiles USING btree (loan_application_id);


--
-- Name: index_inbound_document_mms_on_inbound_document_email_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_inbound_document_mms_on_inbound_document_email_id ON public.inbound_document_mms USING btree (inbound_document_email_id);


--
-- Name: index_its_requests_on_vehicle_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_its_requests_on_vehicle_id ON public.its_requests USING btree (vehicle_id);


--
-- Name: index_key_replacement_documents_on_key_replacement_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_key_replacement_documents_on_key_replacement_id ON public.key_replacement_documents USING btree (key_replacement_id);


--
-- Name: index_key_replacements_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_key_replacements_on_aasm_state ON public.key_replacements USING btree (aasm_state);


--
-- Name: index_key_replacements_on_contract_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_key_replacements_on_contract_identifier ON public.key_replacements USING btree (contract_identifier);


--
-- Name: index_key_replacements_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_key_replacements_on_loan_application_id ON public.key_replacements USING btree (loan_application_id);


--
-- Name: index_ldd_approvals_on_approved_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_approvals_on_approved_by_id ON public.ldd_approvals USING btree (approved_by_id);


--
-- Name: index_ldd_approvals_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_approvals_on_customer_id ON public.ldd_approvals USING btree (customer_id);


--
-- Name: index_ldd_approvals_on_lender_document_definition_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_approvals_on_lender_document_definition_id ON public.ldd_approvals USING btree (lender_document_definition_id);


--
-- Name: index_ldd_approvals_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_approvals_on_loan_application_id ON public.ldd_approvals USING btree (loan_application_id);


--
-- Name: index_ldd_ldp_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_ldp_id ON public.lender_document_definitions USING btree (lender_document_package_id);


--
-- Name: index_ldd_rejections_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_rejections_on_customer_id ON public.ldd_rejections USING btree (customer_id);


--
-- Name: index_ldd_rejections_on_lender_document_definition_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_rejections_on_lender_document_definition_id ON public.ldd_rejections USING btree (lender_document_definition_id);


--
-- Name: index_ldd_rejections_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_rejections_on_loan_application_id ON public.ldd_rejections USING btree (loan_application_id);


--
-- Name: index_ldd_rejections_on_rejected_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldd_rejections_on_rejected_by_id ON public.ldd_rejections USING btree (rejected_by_id);


--
-- Name: index_ldp_overrides_on_created_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldp_overrides_on_created_by_id ON public.ldp_overrides USING btree (created_by_id);


--
-- Name: index_ldp_overrides_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldp_overrides_on_loan_application_id ON public.ldp_overrides USING btree (loan_application_id);


--
-- Name: index_ldp_overrides_on_new_ldp_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldp_overrides_on_new_ldp_id ON public.ldp_overrides USING btree (new_ldp_id);


--
-- Name: index_ldp_overrides_on_old_ldp_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_ldp_overrides_on_old_ldp_id ON public.ldp_overrides USING btree (old_ldp_id);


--
-- Name: index_legal_terms_documents_on_label; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_legal_terms_documents_on_label ON public.legal_terms_documents USING btree (label);


--
-- Name: index_lender_addresses_on_kind; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_addresses_on_kind ON public.lender_addresses USING btree (kind);


--
-- Name: index_lender_addresses_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_addresses_on_lender_id ON public.lender_addresses USING btree (lender_id);


--
-- Name: index_lender_applications_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_applications_on_lender_id ON public.lender_applications USING btree (lender_id);


--
-- Name: index_lender_applications_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_lender_applications_on_loan_application_id ON public.lender_applications USING btree (loan_application_id);


--
-- Name: index_lender_credit_bureau_credentials_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_credit_bureau_credentials_on_lender_id ON public.lender_credit_bureau_credentials USING btree (lender_id);


--
-- Name: index_lender_document_packages_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_document_packages_on_aasm_state ON public.lender_document_packages USING btree (aasm_state);


--
-- Name: index_lender_document_packages_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_document_packages_on_lender_id ON public.lender_document_packages USING btree (lender_id);


--
-- Name: index_lender_eligibility_constraint_failures_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_eligibility_constraint_failures_on_customer_id ON public.lender_eligibility_constraint_failures USING btree (customer_id);


--
-- Name: index_lender_eligibility_constraints_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_eligibility_constraints_on_lender_id ON public.lender_eligibility_constraints USING btree (lender_id);


--
-- Name: index_lender_preferred_score_models_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_preferred_score_models_on_lender_id ON public.lender_preferred_score_models USING btree (lender_id);


--
-- Name: index_lender_product_policies_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_product_policies_on_lender_id ON public.lender_product_policies USING btree (lender_id);


--
-- Name: index_lender_state_fees_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_state_fees_on_lender_id ON public.lender_state_fees USING btree (lender_id);


--
-- Name: index_lender_state_fees_on_state_fee_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_state_fees_on_state_fee_id ON public.lender_state_fees USING btree (state_fee_id);


--
-- Name: index_lender_user_invitations_on_created_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_user_invitations_on_created_by_id ON public.lender_user_invitations USING btree (created_by_id);


--
-- Name: index_lender_user_invitations_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_user_invitations_on_lender_id ON public.lender_user_invitations USING btree (lender_id);


--
-- Name: index_lender_users_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_users_on_lender_id ON public.lender_users USING btree (lender_id);


--
-- Name: index_lender_users_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_users_on_user_id ON public.lender_users USING btree (user_id);


--
-- Name: index_lender_zip_downloads_on_document_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_zip_downloads_on_document_id ON public.lender_zip_downloads USING btree (document_id);


--
-- Name: index_lender_zip_downloads_on_downloaded_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lender_zip_downloads_on_downloaded_by_id ON public.lender_zip_downloads USING btree (downloaded_by_id);


--
-- Name: index_lenders_on_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_lenders_on_name ON public.lenders USING btree (name);


--
-- Name: index_lenders_on_template_folder_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_lenders_on_template_folder_name ON public.lenders USING btree (template_folder_name);


--
-- Name: index_loan_application_pauses_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_application_pauses_on_loan_application_id ON public.loan_application_pauses USING btree (loan_application_id);


--
-- Name: index_loan_applications_on_auto_closeable; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_applications_on_auto_closeable ON public.loan_applications USING btree (auto_closeable);


--
-- Name: index_loan_applications_on_claimed_by; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_applications_on_claimed_by ON public.loan_applications USING btree (claimed_by);


--
-- Name: index_loan_applications_on_first_touched; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_applications_on_first_touched ON public.loan_applications USING btree (first_touched);


--
-- Name: index_loan_applications_on_last_touched; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_applications_on_last_touched ON public.loan_applications USING btree (last_touched);


--
-- Name: index_loan_applications_on_ops_status; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_applications_on_ops_status ON public.loan_applications USING btree (ops_status);


--
-- Name: index_loan_applications_on_ops_status_updated_at; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_applications_on_ops_status_updated_at ON public.loan_applications USING btree (ops_status_updated_at);


--
-- Name: index_loan_applications_on_vehicle_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_applications_on_vehicle_id ON public.loan_applications USING btree (vehicle_id);


--
-- Name: index_loan_archivals_on_archived_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_archivals_on_archived_by_id ON public.loan_archivals USING btree (archived_by_id);


--
-- Name: index_loan_archivals_on_document_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_archivals_on_document_id ON public.loan_archivals USING btree (document_id);


--
-- Name: index_loan_data_captures_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_data_captures_on_loan_application_id ON public.loan_data_captures USING btree (loan_application_id);


--
-- Name: index_loan_exemptions_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_exemptions_on_loan_application_id ON public.loan_exemptions USING btree (loan_application_id);


--
-- Name: index_loan_exemptions_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_exemptions_on_user_id ON public.loan_exemptions USING btree (user_id);


--
-- Name: index_loan_referrals_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_loan_referrals_on_loan_application_id ON public.loan_referrals USING btree (loan_application_id);


--
-- Name: index_loan_referrals_on_referral_code_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loan_referrals_on_referral_code_id ON public.loan_referrals USING btree (referral_code_id);


--
-- Name: index_loanspq_callbacks_on_external_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loanspq_callbacks_on_external_id ON public.loanspq_callbacks USING btree (external_id);


--
-- Name: index_loanspq_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loanspq_requests_on_loan_application_id ON public.loanspq_requests USING btree (loan_application_id);


--
-- Name: index_loanspq_statuses_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_loanspq_statuses_on_loan_application_id ON public.loanspq_statuses USING btree (loan_application_id);


--
-- Name: index_lob_requests_on_payoff_issuance_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lob_requests_on_payoff_issuance_id ON public.lob_requests USING btree (payoff_issuance_id);


--
-- Name: index_lui_conversions_on_lender_user_invitation_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lui_conversions_on_lender_user_invitation_id ON public.lui_conversions USING btree (lender_user_invitation_id);


--
-- Name: index_lui_conversions_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_lui_conversions_on_user_id ON public.lui_conversions USING btree (user_id);


--
-- Name: index_manual_loan_decisions_on_decision; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_manual_loan_decisions_on_decision ON public.manual_loan_decisions USING btree (decision);


--
-- Name: index_manual_loan_decisions_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_manual_loan_decisions_on_loan_application_id ON public.manual_loan_decisions USING btree (loan_application_id);


--
-- Name: index_manual_loan_decisions_on_offer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_manual_loan_decisions_on_offer_id ON public.manual_loan_decisions USING btree (offer_id);


--
-- Name: index_manual_loan_decisions_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_manual_loan_decisions_on_user_id ON public.manual_loan_decisions USING btree (user_id);


--
-- Name: index_message_logs_on_recipient_type_and_recipient_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_message_logs_on_recipient_type_and_recipient_id ON public.message_logs USING btree (recipient_type, recipient_id);


--
-- Name: index_military_status_indications_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_military_status_indications_on_customer_id ON public.military_status_indications USING btree (customer_id);


--
-- Name: index_myautoloan_requests_on_app_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_myautoloan_requests_on_app_identifier ON public.myautoloan_requests USING btree (app_identifier);


--
-- Name: index_nada_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_nada_requests_on_loan_application_id ON public.nada_requests USING btree (loan_application_id);


--
-- Name: index_nada_requests_on_method_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_nada_requests_on_method_name ON public.nada_requests USING btree (method_name);


--
-- Name: index_nada_requests_on_response_code; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_nada_requests_on_response_code ON public.nada_requests USING btree (response_code);


--
-- Name: index_nada_tokens_on_username; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_nada_tokens_on_username ON public.nada_tokens USING btree (username);


--
-- Name: index_nada_vehicle_accessories_on_vehicle_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_nada_vehicle_accessories_on_vehicle_id ON public.nada_vehicle_accessories USING btree (vehicle_id);


--
-- Name: index_naln_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_naln_requests_on_loan_application_id ON public.naln_requests USING btree (loan_application_id);


--
-- Name: index_noaas_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_noaas_on_customer_id ON public.noaas USING btree (customer_id);


--
-- Name: index_noaas_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_noaas_on_loan_application_id ON public.noaas USING btree (loan_application_id);


--
-- Name: index_notes_on_notable_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_notes_on_notable_id ON public.notes USING btree (notable_id);


--
-- Name: index_notes_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_notes_on_user_id ON public.notes USING btree (user_id);


--
-- Name: index_offer_groups_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_offer_groups_on_loan_application_id ON public.offer_groups USING btree (loan_application_id);


--
-- Name: index_offer_rejections_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_offer_rejections_on_loan_application_id ON public.offer_rejections USING btree (loan_application_id);


--
-- Name: index_offers_on_credit_policy_entry_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_offers_on_credit_policy_entry_id ON public.offers USING btree (credit_policy_entry_id);


--
-- Name: index_offers_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_offers_on_loan_application_id ON public.offers USING btree (loan_application_id);


--
-- Name: index_offers_on_loan_application_id_and_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_offers_on_loan_application_id_and_aasm_state ON public.offers USING btree (loan_application_id, aasm_state) WHERE ((aasm_state)::text = 'accepted'::text);


--
-- Name: index_offers_on_offer_group_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_offers_on_offer_group_id ON public.offers USING btree (offer_group_id);


--
-- Name: index_open_lending_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_open_lending_requests_on_loan_application_id ON public.open_lending_requests USING btree (loan_application_id);


--
-- Name: index_opt_outs_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_opt_outs_on_customer_id ON public.opt_outs USING btree (customer_id);


--
-- Name: index_origination_percentages_on_lender_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_origination_percentages_on_lender_id ON public.origination_percentages USING btree (lender_id);


--
-- Name: index_outbound_text_templates_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_outbound_text_templates_on_user_id ON public.outbound_text_templates USING btree (user_id);


--
-- Name: index_payoff_issuances_on_issued_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_payoff_issuances_on_issued_by_id ON public.payoff_issuances USING btree (issued_by_id);


--
-- Name: index_payoff_issuances_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_payoff_issuances_on_loan_application_id ON public.payoff_issuances USING btree (loan_application_id);


--
-- Name: index_payoff_quotes_on_collected_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_payoff_quotes_on_collected_by_id ON public.payoff_quotes USING btree (collected_by_id);


--
-- Name: index_payoff_quotes_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_payoff_quotes_on_loan_application_id ON public.payoff_quotes USING btree (loan_application_id);


--
-- Name: index_phone_applications_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_phone_applications_on_loan_application_id ON public.phone_applications USING btree (loan_application_id);


--
-- Name: index_phone_number_texts_on_phone_number_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_phone_number_texts_on_phone_number_id ON public.phone_number_texts USING btree (phone_number_id);


--
-- Name: index_phone_number_texts_on_text_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_phone_number_texts_on_text_id ON public.phone_number_texts USING btree (text_id);


--
-- Name: index_phone_numbers_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_phone_numbers_on_customer_id ON public.phone_numbers USING btree (customer_id);


--
-- Name: index_prequeue_data_collections_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_prequeue_data_collections_on_loan_application_id ON public.prequeue_data_collections USING btree (loan_application_id);


--
-- Name: index_product_fees_on_product_class; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_product_fees_on_product_class ON public.product_fees USING btree (product_class);


--
-- Name: index_product_fees_on_product_class_and_effective_date; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_product_fees_on_product_class_and_effective_date ON public.product_fees USING btree (product_class, effective_date);


--
-- Name: index_promax_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_promax_requests_on_loan_application_id ON public.promax_requests USING btree (loan_application_id);


--
-- Name: index_referral_codes_on_code; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_referral_codes_on_code ON public.referral_codes USING btree (code);


--
-- Name: index_referral_codes_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_referral_codes_on_customer_id ON public.referral_codes USING btree (customer_id);


--
-- Name: index_referral_offers_on_credit_policy_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_referral_offers_on_credit_policy_id ON public.referral_offers USING btree (credit_policy_id);


--
-- Name: index_referral_offers_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_referral_offers_on_loan_application_id ON public.referral_offers USING btree (loan_application_id);


--
-- Name: index_referral_offers_on_uuid; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_referral_offers_on_uuid ON public.referral_offers USING btree (uuid);


--
-- Name: index_report_runs_on_report_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_report_runs_on_report_name ON public.report_runs USING btree (report_name);


--
-- Name: index_sessions_on_created_at; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_sessions_on_created_at ON public.sessions USING btree (created_at);


--
-- Name: index_sessions_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_sessions_on_customer_id ON public.sessions USING btree (customer_id);


--
-- Name: index_sessions_on_uuid; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_sessions_on_uuid ON public.sessions USING btree (uuid);


--
-- Name: index_shipments_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_shipments_on_loan_application_id ON public.shipments USING btree (loan_application_id);


--
-- Name: index_shipments_on_status; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_shipments_on_status ON public.shipments USING btree (status);


--
-- Name: index_shipments_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_shipments_on_user_id ON public.shipments USING btree (user_id);


--
-- Name: index_si_banking_requests_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_si_banking_requests_on_loan_application_id ON public.si_banking_requests USING btree (loan_application_id);


--
-- Name: index_simple_sales_user_reports_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_simple_sales_user_reports_on_user_id ON public.simple_sales_user_reports USING btree (user_id);


--
-- Name: index_slow_tasks_on_started_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_slow_tasks_on_started_by_id ON public.slow_tasks USING btree (started_by_id);


--
-- Name: index_sms_records_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_sms_records_on_customer_id ON public.sms_records USING btree (customer_id);


--
-- Name: index_sms_records_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_sms_records_on_loan_application_id ON public.sms_records USING btree (loan_application_id);


--
-- Name: index_split_records_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_split_records_on_customer_id ON public.split_records USING btree (customer_id);


--
-- Name: index_split_records_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_split_records_on_loan_application_id ON public.split_records USING btree (loan_application_id);


--
-- Name: index_state_apr_data_on_credit_score_range; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_state_apr_data_on_credit_score_range ON public.state_apr_data USING btree (credit_score_range);


--
-- Name: index_state_apr_data_on_loan_term; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_state_apr_data_on_loan_term ON public.state_apr_data USING btree (loan_term);


--
-- Name: index_state_apr_data_on_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_state_apr_data_on_state ON public.state_apr_data USING btree (state);


--
-- Name: index_supplementary_document_requirements_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_supplementary_document_requirements_on_customer_id ON public.supplementary_document_requirements USING btree (customer_id);


--
-- Name: index_supplementary_document_requirements_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_supplementary_document_requirements_on_user_id ON public.supplementary_document_requirements USING btree (user_id);


--
-- Name: index_texts_on_contact_number; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_texts_on_contact_number ON public.texts USING btree (contact_number);


--
-- Name: index_title_document_transfers_on_document_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_title_document_transfers_on_document_id ON public.title_document_transfers USING btree (document_id);


--
-- Name: index_title_document_transfers_on_vehicle_title_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_title_document_transfers_on_vehicle_title_id ON public.title_document_transfers USING btree (vehicle_title_id);


--
-- Name: index_title_documents_on_vehicle_title_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_title_documents_on_vehicle_title_id ON public.title_documents USING btree (vehicle_title_id);


--
-- Name: index_title_poa_shipment_customer_emails_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_title_poa_shipment_customer_emails_on_customer_id ON public.title_poa_shipment_customer_emails USING btree (customer_id);


--
-- Name: index_title_poa_shipment_customer_emails_on_shipment_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_title_poa_shipment_customer_emails_on_shipment_id ON public.title_poa_shipment_customer_emails USING btree (shipment_id);


--
-- Name: index_title_poa_shipment_customer_emails_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_title_poa_shipment_customer_emails_on_user_id ON public.title_poa_shipment_customer_emails USING btree (user_id);


--
-- Name: index_tos_acceptances_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_tos_acceptances_on_customer_id ON public.tos_acceptances USING btree (customer_id);


--
-- Name: index_tos_acceptances_on_legal_terms_document_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_tos_acceptances_on_legal_terms_document_id ON public.tos_acceptances USING btree (legal_terms_document_id);


--
-- Name: index_tos_acceptances_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_tos_acceptances_on_loan_application_id ON public.tos_acceptances USING btree (loan_application_id);


--
-- Name: index_total_loss_protections_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_total_loss_protections_on_aasm_state ON public.total_loss_protections USING btree (aasm_state);


--
-- Name: index_total_loss_protections_on_contract_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_total_loss_protections_on_contract_identifier ON public.total_loss_protections USING btree (contract_identifier);


--
-- Name: index_total_loss_protections_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_total_loss_protections_on_loan_application_id ON public.total_loss_protections USING btree (loan_application_id);


--
-- Name: index_underwriting_exceptions_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_underwriting_exceptions_on_loan_application_id ON public.underwriting_exceptions USING btree (loan_application_id);


--
-- Name: index_user_deactivations_on_deactivated_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_user_deactivations_on_deactivated_by_id ON public.user_deactivations USING btree (deactivated_by_id);


--
-- Name: index_user_deactivations_on_user_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_user_deactivations_on_user_id ON public.user_deactivations USING btree (user_id);


--
-- Name: index_users_on_email; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX index_users_on_email ON public.users USING btree (email);


--
-- Name: index_users_on_last_name; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_users_on_last_name ON public.users USING btree (last_name);


--
-- Name: index_utm_records_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_utm_records_on_customer_id ON public.utm_records USING btree (customer_id);


--
-- Name: index_utm_records_on_utm_campaign; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_utm_records_on_utm_campaign ON public.utm_records USING btree (utm_campaign);


--
-- Name: index_validated_income_amounts_on_customer_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_validated_income_amounts_on_customer_id ON public.validated_income_amounts USING btree (customer_id);


--
-- Name: index_validated_income_amounts_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_validated_income_amounts_on_loan_application_id ON public.validated_income_amounts USING btree (loan_application_id);


--
-- Name: index_validated_income_amounts_on_validated_by_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_validated_income_amounts_on_validated_by_id ON public.validated_income_amounts USING btree (validated_by_id);


--
-- Name: index_vehicle_service_contracts_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_vehicle_service_contracts_on_aasm_state ON public.vehicle_service_contracts USING btree (aasm_state);


--
-- Name: index_vehicle_service_contracts_on_contract_identifier; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_vehicle_service_contracts_on_contract_identifier ON public.vehicle_service_contracts USING btree (contract_identifier);


--
-- Name: index_vehicle_service_contracts_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_vehicle_service_contracts_on_loan_application_id ON public.vehicle_service_contracts USING btree (loan_application_id);


--
-- Name: index_vehicle_titles_on_loan_application_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_vehicle_titles_on_loan_application_id ON public.vehicle_titles USING btree (loan_application_id);


--
-- Name: index_vehicle_titles_on_vehicle_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_vehicle_titles_on_vehicle_id ON public.vehicle_titles USING btree (vehicle_id);


--
-- Name: index_vehicles_on_aasm_state; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_vehicles_on_aasm_state ON public.vehicles USING btree (aasm_state);


--
-- Name: index_versions_on_item_type_and_item_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_versions_on_item_type_and_item_id ON public.versions USING btree (item_type, item_id);


--
-- Name: index_wolters_kluwer_requests_on_vehicle_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX index_wolters_kluwer_requests_on_vehicle_id ON public.wolters_kluwer_requests USING btree (vehicle_id);


--
-- Name: lecf_la_idx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX lecf_la_idx ON public.lender_eligibility_constraint_failures USING btree (loan_application_id);


--
-- Name: lecf_lec_idx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX lecf_lec_idx ON public.lender_eligibility_constraint_failures USING btree (lender_eligibility_constraint_id);


--
-- Name: sdr_la_idx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX sdr_la_idx ON public.supplementary_document_requirements USING btree (loan_application_id);


--
-- Name: sdr_ldd_idx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX sdr_ldd_idx ON public.supplementary_document_requirements USING btree (lender_document_definition_id);


--
-- Name: srd_uniq_la_id_ldd_id; Type: INDEX; Schema: public; Owner: josiah
--

CREATE UNIQUE INDEX srd_uniq_la_id_ldd_id ON public.supplementary_document_requirements USING btree (loan_application_id, lender_document_definition_id);


--
-- Name: tlp_index; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX tlp_index ON public.total_loss_protection_documents USING btree (total_loss_protection_id);


--
-- Name: trgm_dmr_last_name_indx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX trgm_dmr_last_name_indx ON public.direct_mail_recipients USING gist (last_name public.gist_trgm_ops);


--
-- Name: trgm_email_indx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX trgm_email_indx ON public.customers USING gist (email public.gist_trgm_ops);


--
-- Name: trgm_first_name_indx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX trgm_first_name_indx ON public.customers USING gist (first_name public.gist_trgm_ops);


--
-- Name: trgm_last_name_indx; Type: INDEX; Schema: public; Owner: josiah
--

CREATE INDEX trgm_last_name_indx ON public.customers USING gist (last_name public.gist_trgm_ops);


--
-- Name: referral_codes fk_rails_0072034b52; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.referral_codes
    ADD CONSTRAINT fk_rails_0072034b52 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: lender_addresses fk_rails_03661ee2ea; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_addresses
    ADD CONSTRAINT fk_rails_03661ee2ea FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: credit_pull_details fk_rails_03eb7b57d8; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_pull_details
    ADD CONSTRAINT fk_rails_03eb7b57d8 FOREIGN KEY (credit_pull_id) REFERENCES public.credit_pulls(id);


--
-- Name: finastra_requests fk_rails_05921c3056; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.finastra_requests
    ADD CONSTRAINT fk_rails_05921c3056 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: split_records fk_rails_061b6d6f06; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.split_records
    ADD CONSTRAINT fk_rails_061b6d6f06 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: defi_statuses fk_rails_062b6a06f2; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_statuses
    ADD CONSTRAINT fk_rails_062b6a06f2 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: fake_loan_applications fk_rails_06a06f3730; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.fake_loan_applications
    ADD CONSTRAINT fk_rails_06a06f3730 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: ldp_overrides fk_rails_08b2b87650; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldp_overrides
    ADD CONSTRAINT fk_rails_08b2b87650 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: tos_acceptances fk_rails_09ba703722; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.tos_acceptances
    ADD CONSTRAINT fk_rails_09ba703722 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: customer_loan_applications fk_rails_0c4c2969ff; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_loan_applications
    ADD CONSTRAINT fk_rails_0c4c2969ff FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: nada_requests fk_rails_0d34337674; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_requests
    ADD CONSTRAINT fk_rails_0d34337674 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: corporate_codes fk_rails_0f1af89718; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.corporate_codes
    ADD CONSTRAINT fk_rails_0f1af89718 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: tos_acceptances fk_rails_0f48ab5511; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.tos_acceptances
    ADD CONSTRAINT fk_rails_0f48ab5511 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: direct_mail_lookups fk_rails_1019bb7828; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_lookups
    ADD CONSTRAINT fk_rails_1019bb7828 FOREIGN KEY (direct_mail_recipient_id) REFERENCES public.direct_mail_recipients(id);


--
-- Name: direct_mail_recipients fk_rails_1081c70484; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_recipients
    ADD CONSTRAINT fk_rails_1081c70484 FOREIGN KEY (direct_mail_batch_id) REFERENCES public.direct_mail_batches(id);


--
-- Name: offers fk_rails_111cd63fbb; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT fk_rails_111cd63fbb FOREIGN KEY (credit_policy_entry_id) REFERENCES public.credit_policy_entries(id);


--
-- Name: user_deactivations fk_rails_11518eab62; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.user_deactivations
    ADD CONSTRAINT fk_rails_11518eab62 FOREIGN KEY (deactivated_by_id) REFERENCES public.users(id);


--
-- Name: manual_loan_decisions fk_rails_11dae8ddec; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.manual_loan_decisions
    ADD CONSTRAINT fk_rails_11dae8ddec FOREIGN KEY (offer_id) REFERENCES public.offers(id);


--
-- Name: total_loss_protections fk_rails_13a3b9190a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.total_loss_protections
    ADD CONSTRAINT fk_rails_13a3b9190a FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: cudl_requests fk_rails_1605052951; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_requests
    ADD CONSTRAINT fk_rails_1605052951 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: cudl_callbacks fk_rails_1673b97de0; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_callbacks
    ADD CONSTRAINT fk_rails_1673b97de0 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: lender_eligibility_constraint_failures fk_rails_190755e400; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_eligibility_constraint_failures
    ADD CONSTRAINT fk_rails_190755e400 FOREIGN KEY (lender_eligibility_constraint_id) REFERENCES public.lender_eligibility_constraints(id);


--
-- Name: debt_cancellations fk_rails_19c97094ff; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.debt_cancellations
    ADD CONSTRAINT fk_rails_19c97094ff FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: inbound_document_email_attachments fk_rails_1a458fee8b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_email_attachments
    ADD CONSTRAINT fk_rails_1a458fee8b FOREIGN KEY (inbound_document_email_id) REFERENCES public.inbound_document_emails(id);


--
-- Name: lender_eligibility_constraint_failures fk_rails_1b612b819a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_eligibility_constraint_failures
    ADD CONSTRAINT fk_rails_1b612b819a FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: noaas fk_rails_1c3847fff1; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.noaas
    ADD CONSTRAINT fk_rails_1c3847fff1 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: loan_application_pauses fk_rails_1e64119f40; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_application_pauses
    ADD CONSTRAINT fk_rails_1e64119f40 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: ldp_overrides fk_rails_2167d166e2; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldp_overrides
    ADD CONSTRAINT fk_rails_2167d166e2 FOREIGN KEY (old_ldp_id) REFERENCES public.lender_document_packages(id);


--
-- Name: key_replacement_documents fk_rails_21b74e9627; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.key_replacement_documents
    ADD CONSTRAINT fk_rails_21b74e9627 FOREIGN KEY (key_replacement_id) REFERENCES public.key_replacements(id);


--
-- Name: payoff_issuances fk_rails_23d9803c04; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_issuances
    ADD CONSTRAINT fk_rails_23d9803c04 FOREIGN KEY (issued_by_id) REFERENCES public.users(id);


--
-- Name: loan_referrals fk_rails_246c3060bb; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_referrals
    ADD CONSTRAINT fk_rails_246c3060bb FOREIGN KEY (referral_code_id) REFERENCES public.referral_codes(id);


--
-- Name: envelope_trial_runs fk_rails_25628c8d85; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_trial_runs
    ADD CONSTRAINT fk_rails_25628c8d85 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: loan_exemptions fk_rails_258c1bf9bc; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_exemptions
    ADD CONSTRAINT fk_rails_258c1bf9bc FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: opt_outs fk_rails_26ef1ea636; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.opt_outs
    ADD CONSTRAINT fk_rails_26ef1ea636 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: ads_requests fk_rails_2bd27ca76f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_requests
    ADD CONSTRAINT fk_rails_2bd27ca76f FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: validated_income_amounts fk_rails_2c1f607004; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.validated_income_amounts
    ADD CONSTRAINT fk_rails_2c1f607004 FOREIGN KEY (validated_by_id) REFERENCES public.users(id);


--
-- Name: external_shipments fk_rails_2fe78e2f6b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_shipments
    ADD CONSTRAINT fk_rails_2fe78e2f6b FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: lender_document_definitions fk_rails_323a4c96e8; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_document_definitions
    ADD CONSTRAINT fk_rails_323a4c96e8 FOREIGN KEY (lender_document_package_id) REFERENCES public.lender_document_packages(id);


--
-- Name: loan_exemptions fk_rails_327281ee8b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_exemptions
    ADD CONSTRAINT fk_rails_327281ee8b FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: envelope_preapprovals fk_rails_3452db2a82; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_preapprovals
    ADD CONSTRAINT fk_rails_3452db2a82 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: credit_policy_exception_definitions fk_rails_34731f3ea3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policy_exception_definitions
    ADD CONSTRAINT fk_rails_34731f3ea3 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: si_banking_requests fk_rails_355a3f74b9; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.si_banking_requests
    ADD CONSTRAINT fk_rails_355a3f74b9 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: validated_income_amounts fk_rails_359fd0f048; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.validated_income_amounts
    ADD CONSTRAINT fk_rails_359fd0f048 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: envelope_preapprovals fk_rails_35c9d6ad94; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_preapprovals
    ADD CONSTRAINT fk_rails_35c9d6ad94 FOREIGN KEY (envelope_id) REFERENCES public.envelopes(id);


--
-- Name: offers fk_rails_37d06340f7; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT fk_rails_37d06340f7 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: lui_conversions fk_rails_3869d59331; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lui_conversions
    ADD CONSTRAINT fk_rails_3869d59331 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: title_documents fk_rails_3b351f66af; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_documents
    ADD CONSTRAINT fk_rails_3b351f66af FOREIGN KEY (vehicle_title_id) REFERENCES public.vehicle_titles(id);


--
-- Name: loan_applications fk_rails_3bb73240c0; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_applications
    ADD CONSTRAINT fk_rails_3bb73240c0 FOREIGN KEY (processor_user_id) REFERENCES public.users(id);


--
-- Name: payoff_quotes fk_rails_3c2303a3f0; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_quotes
    ADD CONSTRAINT fk_rails_3c2303a3f0 FOREIGN KEY (collected_by_id) REFERENCES public.users(id);


--
-- Name: ldd_rejections fk_rails_3c79af96da; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_rejections
    ADD CONSTRAINT fk_rails_3c79af96da FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: documents fk_rails_3c7ace951c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT fk_rails_3c7ace951c FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: shipments fk_rails_41e1946876; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.shipments
    ADD CONSTRAINT fk_rails_41e1946876 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: sms_records fk_rails_4506e9b51f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.sms_records
    ADD CONSTRAINT fk_rails_4506e9b51f FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: lender_credit_bureau_credentials fk_rails_45fe433b5b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_credit_bureau_credentials
    ADD CONSTRAINT fk_rails_45fe433b5b FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: loanspq_statuses fk_rails_48049cd183; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_statuses
    ADD CONSTRAINT fk_rails_48049cd183 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: admin_sessions fk_rails_485432b69c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.admin_sessions
    ADD CONSTRAINT fk_rails_485432b69c FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: prequeue_data_collections fk_rails_4db8f80220; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.prequeue_data_collections
    ADD CONSTRAINT fk_rails_4db8f80220 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: referral_offers fk_rails_4dcafbf404; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.referral_offers
    ADD CONSTRAINT fk_rails_4dcafbf404 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: customer_vehicles fk_rails_4ead444e7c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_vehicles
    ADD CONSTRAINT fk_rails_4ead444e7c FOREIGN KEY (vehicle_id) REFERENCES public.vehicles(id);


--
-- Name: ldd_approvals fk_rails_4efc32aeb3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_approvals
    ADD CONSTRAINT fk_rails_4efc32aeb3 FOREIGN KEY (lender_document_definition_id) REFERENCES public.lender_document_definitions(id);


--
-- Name: user_deactivations fk_rails_50def79879; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.user_deactivations
    ADD CONSTRAINT fk_rails_50def79879 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: customer_site_entry_locations fk_rails_5161f50074; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_site_entry_locations
    ADD CONSTRAINT fk_rails_5161f50074 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: slow_tasks fk_rails_5253b7091b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.slow_tasks
    ADD CONSTRAINT fk_rails_5253b7091b FOREIGN KEY (started_by_id) REFERENCES public.users(id);


--
-- Name: document_rejections fk_rails_548084a08f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_rejections
    ADD CONSTRAINT fk_rails_548084a08f FOREIGN KEY (document_id) REFERENCES public.documents(id);


--
-- Name: alloy_evaluations fk_rails_54e8a2fe34; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.alloy_evaluations
    ADD CONSTRAINT fk_rails_54e8a2fe34 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: cosmetic_packages fk_rails_58fa23f742; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cosmetic_packages
    ADD CONSTRAINT fk_rails_58fa23f742 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: envelope_trial_runs fk_rails_59f304bb9a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelope_trial_runs
    ADD CONSTRAINT fk_rails_59f304bb9a FOREIGN KEY (created_by_id) REFERENCES public.users(id);


--
-- Name: hard_cut_evaluation_profiles fk_rails_5bb0aea29d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.hard_cut_evaluation_profiles
    ADD CONSTRAINT fk_rails_5bb0aea29d FOREIGN KEY (credit_policy_id) REFERENCES public.credit_policies(id);


--
-- Name: lender_user_invitations fk_rails_5d042d60c0; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_user_invitations
    ADD CONSTRAINT fk_rails_5d042d60c0 FOREIGN KEY (created_by_id) REFERENCES public.users(id);


--
-- Name: loan_archivals fk_rails_5d0c538508; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_archivals
    ADD CONSTRAINT fk_rails_5d0c538508 FOREIGN KEY (archived_by_id) REFERENCES public.users(id);


--
-- Name: loan_referrals fk_rails_5d3ca62f34; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_referrals
    ADD CONSTRAINT fk_rails_5d3ca62f34 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: phone_numbers fk_rails_5e309a8714; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_numbers
    ADD CONSTRAINT fk_rails_5e309a8714 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: lender_user_invitations fk_rails_5ee15451a5; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_user_invitations
    ADD CONSTRAINT fk_rails_5ee15451a5 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: hard_cut_evaluation_profiles fk_rails_6101759153; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.hard_cut_evaluation_profiles
    ADD CONSTRAINT fk_rails_6101759153 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: ldd_rejections fk_rails_63ae0d1afc; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_rejections
    ADD CONSTRAINT fk_rails_63ae0d1afc FOREIGN KEY (rejected_by_id) REFERENCES public.users(id);


--
-- Name: cpe_evaluation_profiles fk_rails_65a277f4a3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cpe_evaluation_profiles
    ADD CONSTRAINT fk_rails_65a277f4a3 FOREIGN KEY (credit_policy_entry_id) REFERENCES public.credit_policy_entries(id);


--
-- Name: ldd_rejections fk_rails_6abc8ef59b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_rejections
    ADD CONSTRAINT fk_rails_6abc8ef59b FOREIGN KEY (lender_document_definition_id) REFERENCES public.lender_document_definitions(id);


--
-- Name: report_runs fk_rails_6af74e6939; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.report_runs
    ADD CONSTRAINT fk_rails_6af74e6939 FOREIGN KEY (run_by) REFERENCES public.users(id);


--
-- Name: addresses fk_rails_6b4f947000; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT fk_rails_6b4f947000 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: loan_archivals fk_rails_6c595528ed; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_archivals
    ADD CONSTRAINT fk_rails_6c595528ed FOREIGN KEY (document_id) REFERENCES public.documents(id);


--
-- Name: title_document_transfers fk_rails_6d69cbc26a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_document_transfers
    ADD CONSTRAINT fk_rails_6d69cbc26a FOREIGN KEY (document_id) REFERENCES public.documents(id);


--
-- Name: title_poa_shipment_customer_emails fk_rails_6d9a421f73; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_poa_shipment_customer_emails
    ADD CONSTRAINT fk_rails_6d9a421f73 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: lender_zip_downloads fk_rails_6f8e2e3b68; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_zip_downloads
    ADD CONSTRAINT fk_rails_6f8e2e3b68 FOREIGN KEY (downloaded_by_id) REFERENCES public.users(id);


--
-- Name: vehicle_service_contracts fk_rails_6fa567b488; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_service_contracts
    ADD CONSTRAINT fk_rails_6fa567b488 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: contact_preferences fk_rails_70f38a472f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.contact_preferences
    ADD CONSTRAINT fk_rails_70f38a472f FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: shipments fk_rails_71a7b88fec; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.shipments
    ADD CONSTRAINT fk_rails_71a7b88fec FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: documents fk_rails_73f3748c45; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT fk_rails_73f3748c45 FOREIGN KEY (lender_document_definition_id) REFERENCES public.lender_document_definitions(id);


--
-- Name: lender_zip_downloads fk_rails_74b356200c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_zip_downloads
    ADD CONSTRAINT fk_rails_74b356200c FOREIGN KEY (document_id) REFERENCES public.documents(id);


--
-- Name: defi_callbacks fk_rails_78c716d286; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_callbacks
    ADD CONSTRAINT fk_rails_78c716d286 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: payoff_issuances fk_rails_7ab6e5529e; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_issuances
    ADD CONSTRAINT fk_rails_7ab6e5529e FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: finastra_requests fk_rails_7c679d7cdf; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.finastra_requests
    ADD CONSTRAINT fk_rails_7c679d7cdf FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: lui_conversions fk_rails_7d2b88ed74; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lui_conversions
    ADD CONSTRAINT fk_rails_7d2b88ed74 FOREIGN KEY (lender_user_invitation_id) REFERENCES public.lender_user_invitations(id);


--
-- Name: vehicle_service_contract_documents fk_rails_7db14e02c9; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_service_contract_documents
    ADD CONSTRAINT fk_rails_7db14e02c9 FOREIGN KEY (vehicle_service_contract_id) REFERENCES public.vehicle_service_contracts(id);


--
-- Name: documents fk_rails_7e400e46c8; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT fk_rails_7e400e46c8 FOREIGN KEY (envelope_id) REFERENCES public.envelopes(id);


--
-- Name: notes fk_rails_7f2323ad43; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.notes
    ADD CONSTRAINT fk_rails_7f2323ad43 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: cpe_evaluation_profiles fk_rails_8075bb8611; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cpe_evaluation_profiles
    ADD CONSTRAINT fk_rails_8075bb8611 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: supplementary_document_requirements fk_rails_857e300e56; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.supplementary_document_requirements
    ADD CONSTRAINT fk_rails_857e300e56 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: auto_loan_selections fk_rails_878e546f19; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.auto_loan_selections
    ADD CONSTRAINT fk_rails_878e546f19 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: vehicle_titles fk_rails_88827e91b4; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_titles
    ADD CONSTRAINT fk_rails_88827e91b4 FOREIGN KEY (vehicle_id) REFERENCES public.vehicles(id);


--
-- Name: phone_number_texts fk_rails_89d2e3737a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_number_texts
    ADD CONSTRAINT fk_rails_89d2e3737a FOREIGN KEY (text_id) REFERENCES public.texts(id);


--
-- Name: documents fk_rails_8b49d7b757; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT fk_rails_8b49d7b757 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: external_hard_pulls fk_rails_8c4472661d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_hard_pulls
    ADD CONSTRAINT fk_rails_8c4472661d FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: supplementary_document_requirements fk_rails_8cf18b4b8d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.supplementary_document_requirements
    ADD CONSTRAINT fk_rails_8cf18b4b8d FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: cudl_statuses fk_rails_8d04b3e505; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cudl_statuses
    ADD CONSTRAINT fk_rails_8d04b3e505 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: docusign_templates fk_rails_8e9d1629f1; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_templates
    ADD CONSTRAINT fk_rails_8e9d1629f1 FOREIGN KEY (lender_document_definition_id) REFERENCES public.lender_document_definitions(id);


--
-- Name: auto_loan_selections fk_rails_959bb04e56; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.auto_loan_selections
    ADD CONSTRAINT fk_rails_959bb04e56 FOREIGN KEY (credit_pull_id) REFERENCES public.credit_pulls(id);


--
-- Name: lender_state_fees fk_rails_9768c964f2; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_state_fees
    ADD CONSTRAINT fk_rails_9768c964f2 FOREIGN KEY (state_fee_id) REFERENCES public.state_fees(id);


--
-- Name: defi_requests fk_rails_99402f1501; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.defi_requests
    ADD CONSTRAINT fk_rails_99402f1501 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: ldd_approvals fk_rails_9c46f4fb83; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_approvals
    ADD CONSTRAINT fk_rails_9c46f4fb83 FOREIGN KEY (approved_by_id) REFERENCES public.users(id);


--
-- Name: title_poa_shipment_customer_emails fk_rails_9cda6f65b3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_poa_shipment_customer_emails
    ADD CONSTRAINT fk_rails_9cda6f65b3 FOREIGN KEY (shipment_id) REFERENCES public.shipments(id);


--
-- Name: report_runs fk_rails_9d88e8733d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.report_runs
    ADD CONSTRAINT fk_rails_9d88e8733d FOREIGN KEY (approved_by) REFERENCES public.users(id);


--
-- Name: debt_cancellation_documents fk_rails_a33fa6403c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.debt_cancellation_documents
    ADD CONSTRAINT fk_rails_a33fa6403c FOREIGN KEY (debt_cancellation_id) REFERENCES public.debt_cancellations(id);


--
-- Name: title_poa_shipment_customer_emails fk_rails_a6e187b0b3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_poa_shipment_customer_emails
    ADD CONSTRAINT fk_rails_a6e187b0b3 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: lob_requests fk_rails_a70b6a7f9d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lob_requests
    ADD CONSTRAINT fk_rails_a70b6a7f9d FOREIGN KEY (payoff_issuance_id) REFERENCES public.payoff_issuances(id);


--
-- Name: lender_applications fk_rails_a7488547e6; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_applications
    ADD CONSTRAINT fk_rails_a7488547e6 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: lender_eligibility_constraint_failures fk_rails_a7907b7c7e; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_eligibility_constraint_failures
    ADD CONSTRAINT fk_rails_a7907b7c7e FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: credit_pulls fk_rails_aa90320029; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_pulls
    ADD CONSTRAINT fk_rails_aa90320029 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: noaas fk_rails_ad4a3cb635; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.noaas
    ADD CONSTRAINT fk_rails_ad4a3cb635 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: lender_state_fees fk_rails_af57cfd204; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_state_fees
    ADD CONSTRAINT fk_rails_af57cfd204 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: ldd_approvals fk_rails_b037d3e57e; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_approvals
    ADD CONSTRAINT fk_rails_b037d3e57e FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: alloy_evaluations fk_rails_b24a393784; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.alloy_evaluations
    ADD CONSTRAINT fk_rails_b24a393784 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: offer_groups fk_rails_b25cf106c9; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offer_groups
    ADD CONSTRAINT fk_rails_b25cf106c9 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: document_approvals fk_rails_b472a4024f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_approvals
    ADD CONSTRAINT fk_rails_b472a4024f FOREIGN KEY (document_id) REFERENCES public.documents(id);


--
-- Name: document_approvals fk_rails_b48c9590fd; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_approvals
    ADD CONSTRAINT fk_rails_b48c9590fd FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: offers fk_rails_b4a7d9125c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT fk_rails_b4a7d9125c FOREIGN KEY (offer_group_id) REFERENCES public.offer_groups(id);


--
-- Name: loan_applications fk_rails_b52148f3d3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_applications
    ADD CONSTRAINT fk_rails_b52148f3d3 FOREIGN KEY (vehicle_id) REFERENCES public.vehicles(id);


--
-- Name: utm_records fk_rails_b7515f2d68; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.utm_records
    ADD CONSTRAINT fk_rails_b7515f2d68 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: envelopes fk_rails_b77f408cb1; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.envelopes
    ADD CONSTRAINT fk_rails_b77f408cb1 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: payoff_quotes fk_rails_b7c2a608b4; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.payoff_quotes
    ADD CONSTRAINT fk_rails_b7c2a608b4 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: underwriting_exceptions fk_rails_b8b7b9daba; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.underwriting_exceptions
    ADD CONSTRAINT fk_rails_b8b7b9daba FOREIGN KEY (credit_policy_exception_definition_id) REFERENCES public.credit_policy_exception_definitions(id);


--
-- Name: cpe_evaluation_profiles fk_rails_b9a2292466; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.cpe_evaluation_profiles
    ADD CONSTRAINT fk_rails_b9a2292466 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: lender_applications fk_rails_b9dd78d97f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_applications
    ADD CONSTRAINT fk_rails_b9dd78d97f FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: nada_vehicle_accessories fk_rails_ba62f529ac; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.nada_vehicle_accessories
    ADD CONSTRAINT fk_rails_ba62f529ac FOREIGN KEY (vehicle_id) REFERENCES public.vehicles(id);


--
-- Name: lender_users fk_rails_bd2169584c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_users
    ADD CONSTRAINT fk_rails_bd2169584c FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: employment_details fk_rails_bdf393162d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.employment_details
    ADD CONSTRAINT fk_rails_bdf393162d FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: lender_preferred_score_models fk_rails_be4ac3e4ef; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_preferred_score_models
    ADD CONSTRAINT fk_rails_be4ac3e4ef FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: supplementary_document_requirements fk_rails_bec776fa03; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.supplementary_document_requirements
    ADD CONSTRAINT fk_rails_bec776fa03 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: manual_loan_decisions fk_rails_becee0311d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.manual_loan_decisions
    ADD CONSTRAINT fk_rails_becee0311d FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: autoconfiado_customers fk_rails_bf05c250b0; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.autoconfiado_customers
    ADD CONSTRAINT fk_rails_bf05c250b0 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: inbound_document_mms fk_rails_bf803011e0; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.inbound_document_mms
    ADD CONSTRAINT fk_rails_bf803011e0 FOREIGN KEY (inbound_document_email_id) REFERENCES public.inbound_document_emails(id);


--
-- Name: external_hard_pulls fk_rails_c2d2fa0f27; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_hard_pulls
    ADD CONSTRAINT fk_rails_c2d2fa0f27 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: customer_loan_applications fk_rails_c5e155218a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_loan_applications
    ADD CONSTRAINT fk_rails_c5e155218a FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: lender_product_policies fk_rails_c66de76d79; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_product_policies
    ADD CONSTRAINT fk_rails_c66de76d79 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: authentication_tokens fk_rails_c804bb2a94; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.authentication_tokens
    ADD CONSTRAINT fk_rails_c804bb2a94 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: referral_offers fk_rails_c824d80c3a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.referral_offers
    ADD CONSTRAINT fk_rails_c824d80c3a FOREIGN KEY (credit_policy_id) REFERENCES public.credit_policies(id);


--
-- Name: supplementary_document_requirements fk_rails_c8b01a4d82; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.supplementary_document_requirements
    ADD CONSTRAINT fk_rails_c8b01a4d82 FOREIGN KEY (lender_document_definition_id) REFERENCES public.lender_document_definitions(id);


--
-- Name: document_rejections fk_rails_ca141f602b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.document_rejections
    ADD CONSTRAINT fk_rails_ca141f602b FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: prequeue_data_collections fk_rails_ca60b45f49; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.prequeue_data_collections
    ADD CONSTRAINT fk_rails_ca60b45f49 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: customer_site_entry_locations fk_rails_cafbcd7f6a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_site_entry_locations
    ADD CONSTRAINT fk_rails_cafbcd7f6a FOREIGN KEY (utm_record_id) REFERENCES public.utm_records(id);


--
-- Name: elt_numbers fk_rails_cafe6db1f8; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.elt_numbers
    ADD CONSTRAINT fk_rails_cafe6db1f8 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: loanspq_requests fk_rails_ccc3b6cc57; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_requests
    ADD CONSTRAINT fk_rails_ccc3b6cc57 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: fedex_requests fk_rails_cd6c33649f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.fedex_requests
    ADD CONSTRAINT fk_rails_cd6c33649f FOREIGN KEY (address_id) REFERENCES public.addresses(id);


--
-- Name: loan_data_captures fk_rails_cd91446124; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loan_data_captures
    ADD CONSTRAINT fk_rails_cd91446124 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: simple_sales_user_reports fk_rails_d0e8facf5e; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.simple_sales_user_reports
    ADD CONSTRAINT fk_rails_d0e8facf5e FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: naln_requests fk_rails_d1a346d41a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.naln_requests
    ADD CONSTRAINT fk_rails_d1a346d41a FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: direct_mail_edits fk_rails_d592ffe4c6; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_edits
    ADD CONSTRAINT fk_rails_d592ffe4c6 FOREIGN KEY (direct_mail_recipient_id) REFERENCES public.direct_mail_recipients(id);


--
-- Name: offer_rejections fk_rails_d5b07dc90d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.offer_rejections
    ADD CONSTRAINT fk_rails_d5b07dc90d FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: addresses fk_rails_d5f9efddd3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT fk_rails_d5f9efddd3 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: loanspq_callbacks fk_rails_d8ab45f019; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.loanspq_callbacks
    ADD CONSTRAINT fk_rails_d8ab45f019 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: ldp_overrides fk_rails_d9022a7b81; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldp_overrides
    ADD CONSTRAINT fk_rails_d9022a7b81 FOREIGN KEY (new_ldp_id) REFERENCES public.lender_document_packages(id);


--
-- Name: tos_acceptances fk_rails_db7114b152; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.tos_acceptances
    ADD CONSTRAINT fk_rails_db7114b152 FOREIGN KEY (legal_terms_document_id) REFERENCES public.legal_terms_documents(id);


--
-- Name: manual_loan_decisions fk_rails_dcd6698df2; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.manual_loan_decisions
    ADD CONSTRAINT fk_rails_dcd6698df2 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: lender_users fk_rails_dfd78a3a37; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_users
    ADD CONSTRAINT fk_rails_dfd78a3a37 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: texts fk_rails_dffe89dc62; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.texts
    ADD CONSTRAINT fk_rails_dffe89dc62 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: ads_vehicle_service_contracts fk_rails_e1b6f92a42; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ads_vehicle_service_contracts
    ADD CONSTRAINT fk_rails_e1b6f92a42 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: lender_document_packages fk_rails_e26bd52ab0; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.lender_document_packages
    ADD CONSTRAINT fk_rails_e26bd52ab0 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: direct_mail_conversions fk_rails_e39897ed18; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_conversions
    ADD CONSTRAINT fk_rails_e39897ed18 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: credit_policies fk_rails_e3f85a1778; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policies
    ADD CONSTRAINT fk_rails_e3f85a1778 FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: key_replacements fk_rails_e469c6e384; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.key_replacements
    ADD CONSTRAINT fk_rails_e469c6e384 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: customer_vehicles fk_rails_e59b4371ae; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.customer_vehicles
    ADD CONSTRAINT fk_rails_e59b4371ae FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: title_document_transfers fk_rails_e5a7690eee; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.title_document_transfers
    ADD CONSTRAINT fk_rails_e5a7690eee FOREIGN KEY (vehicle_title_id) REFERENCES public.vehicle_titles(id);


--
-- Name: fedex_requests fk_rails_e5c693f664; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.fedex_requests
    ADD CONSTRAINT fk_rails_e5c693f664 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: origination_percentages fk_rails_e75719bbbd; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.origination_percentages
    ADD CONSTRAINT fk_rails_e75719bbbd FOREIGN KEY (lender_id) REFERENCES public.lenders(id);


--
-- Name: direct_mail_conversions fk_rails_e7b28cf490; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.direct_mail_conversions
    ADD CONSTRAINT fk_rails_e7b28cf490 FOREIGN KEY (direct_mail_recipient_id) REFERENCES public.direct_mail_recipients(id);


--
-- Name: underwriting_exceptions fk_rails_e8a14690f2; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.underwriting_exceptions
    ADD CONSTRAINT fk_rails_e8a14690f2 FOREIGN KEY (credit_policy_entry_id) REFERENCES public.credit_policy_entries(id);


--
-- Name: underwriting_exceptions fk_rails_e928e767bb; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.underwriting_exceptions
    ADD CONSTRAINT fk_rails_e928e767bb FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: ldp_overrides fk_rails_e9e26f462c; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldp_overrides
    ADD CONSTRAINT fk_rails_e9e26f462c FOREIGN KEY (created_by_id) REFERENCES public.users(id);


--
-- Name: sms_records fk_rails_ed111b62d3; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.sms_records
    ADD CONSTRAINT fk_rails_ed111b62d3 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: utm_records fk_rails_eda3dc6dfb; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.utm_records
    ADD CONSTRAINT fk_rails_eda3dc6dfb FOREIGN KEY (direct_mail_lookup_id) REFERENCES public.direct_mail_lookups(id);


--
-- Name: phone_number_texts fk_rails_efdd74b925; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_number_texts
    ADD CONSTRAINT fk_rails_efdd74b925 FOREIGN KEY (phone_number_id) REFERENCES public.phone_numbers(id);


--
-- Name: split_records fk_rails_f01514458b; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.split_records
    ADD CONSTRAINT fk_rails_f01514458b FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: phone_applications fk_rails_f0e1bc314d; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.phone_applications
    ADD CONSTRAINT fk_rails_f0e1bc314d FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: military_status_indications fk_rails_f3f91454ca; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.military_status_indications
    ADD CONSTRAINT fk_rails_f3f91454ca FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: external_shipments fk_rails_f4310ebd3f; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.external_shipments
    ADD CONSTRAINT fk_rails_f4310ebd3f FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: docusign_tab_details fk_rails_f43aa76012; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.docusign_tab_details
    ADD CONSTRAINT fk_rails_f43aa76012 FOREIGN KEY (document_id) REFERENCES public.documents(id);


--
-- Name: credit_policy_entries fk_rails_f44e80e2d6; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.credit_policy_entries
    ADD CONSTRAINT fk_rails_f44e80e2d6 FOREIGN KEY (credit_policy_id) REFERENCES public.credit_policies(id);


--
-- Name: vehicle_titles fk_rails_f702eaf8c8; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.vehicle_titles
    ADD CONSTRAINT fk_rails_f702eaf8c8 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: hard_cut_evaluation_profiles fk_rails_f79588ee77; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.hard_cut_evaluation_profiles
    ADD CONSTRAINT fk_rails_f79588ee77 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: ldd_approvals fk_rails_f8aab7bbd9; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_approvals
    ADD CONSTRAINT fk_rails_f8aab7bbd9 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: open_lending_requests fk_rails_fa13c6055a; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.open_lending_requests
    ADD CONSTRAINT fk_rails_fa13c6055a FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: validated_income_amounts fk_rails_fba32a5019; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.validated_income_amounts
    ADD CONSTRAINT fk_rails_fba32a5019 FOREIGN KEY (loan_application_id) REFERENCES public.loan_applications(id);


--
-- Name: ldd_rejections fk_rails_fd92504061; Type: FK CONSTRAINT; Schema: public; Owner: josiah
--

ALTER TABLE ONLY public.ldd_rejections
    ADD CONSTRAINT fk_rails_fd92504061 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- PostgreSQL database dump complete
--
