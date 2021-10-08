CREATE TABLE public.persons (
    id int PRIMARY KEY,
    firstName varchar(255),
    lastName varchar(255)
);
ALTER TABLE public.persons owner to postgres;

INSERT INTO public.persons (id, firstName, lastName) VALUES (1, 'Валерий', 'Михайлов');
INSERT INTO public.persons (id, firstName, lastName) VALUES (2, 'Александр', 'Синицын');