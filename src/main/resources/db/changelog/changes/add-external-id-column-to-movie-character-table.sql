--liquibase formatted sql
--changeset <amarkelov>:<add-external-id-column-to-movie-character-table>

ALTER TABLE public.movie_characters ADD external_id bigint;

--rollback ALTER TABLE DROP COLUMN external_id;