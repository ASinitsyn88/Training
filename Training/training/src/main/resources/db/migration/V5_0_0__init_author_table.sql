CREATE SEQUENCE authors_id_seq INCREMENT 1 MINVALUE 101 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE IF NOT EXISTS authors (
    id bigint DEFAULT nextval('authors_id_seq') NOT NULL,
    name text CONSTRAINT namechk CHECK (char_length(name) <= 20),
    age integer,
    CONSTRAINT authors_pkey PRIMARY KEY (id)
);

INSERT INTO authors (id, name, age)
VALUES
    (1,	'Abigail Rose',	80),
    (2,	'Adam Parker',	36),
    (3,	'Adeline Rivers',	49),
    (4,	'Aiden Knight',	87),
    (5,	'Alexa Stone',	34),
    (6,	'Alexander Chase',	22),
    (7,	'Alexandria Blake',	43),
    (8,	'Allison Ford',	56),
    (9,	'Amelia Reed',	63),
    (10,	'Andrew Knight',	77),
    (11,	'Annabelle Scott',	68),
    (12,	'Anthony Green',	70),
    (13,	'Ariana Cole',	84),
    (14,	'Ashton Stone',	28),
    (15,	'Audrey Morgan',	48),
    (16,	'Ava Barnes',	81),
    (17,	'Benjamin Parker',	25),
    (18,	'Bethany Grey',	38),
    (19,	'Blake Walker',	67),
    (20,	'Bradley Evans',	69),
    (21,	'Brianna Flynn',	54),
    (22,	'Brooke Stevens',	79),
    (23,	'Caleb Davis',	72),
    (24,	'Cameron Riley',	66),
    (25,	'Caroline King',	46),
    (26,	'Carter Scott',	57),
    (27,	'Casey Edwards',	75),
    (28,	'Cassandra Hill',	74),
    (29,	'Charlotte Hayes',	45),
    (30,	'Chelsea Jones',	58),
    (31,	'Claire Collins',	71),
    (32,	'Cody Matthews',	35),
    (33,	'Cole Brooks',	52),
    (34,	'Cooper Daniels',	89),
    (35,	'Daisy Cooper',	53),
    (36,	'Daniel Ford',	51),
    (37,	'Daniella Rose',	41),
    (38,	'David James',	64),
    (39,	'Delilah Greene',	26),
    (40,	'Dylan Baker',	44),
    (41,	'Elizabeth Wells',	85),
    (42,	'Emily Campbell',	23),
    (43,	'Emma Taylor',	61),
    (44,	'Ethan Grey',	86),
    (45,	'Eva Parker',	76),
    (46,	'Finn Walker',	55),
    (47,	'Gabrielle Jones',	30),
    (48,	'Gavin Sullivan',	33),
    (49,	'Georgia Young',	88),
    (50,	'Grace Cooper',	82),
    (51,	'Haley Anderson',	27),
    (52,	'Harper Thompson',	42),
    (53,	'Hayden Foster',	78),
    (54,	'Isabella James',	50),
    (55,	'Jackson Ford',	60),
    (56,	'Jacob Matthews',	37),
    (57,	'Jade Taylor',	83),
    (58,	'Jameson Reid',	31),
    (59,	'Jasmine Cooper',	24),
    (60,	'Jason Scott',	62),
    (61,	'Jayden Knight',	73),
    (62,	'Jenna Evans',	47),
    (63,	'Jessica Stone',	65),
    (64,	'John Davis',	39),
    (65,	'Jordan Greene',	32),
    (66,	'Josephine King',	59),
    (67,	'Joshua Campbell',	40),
    (68,	'Julia Parker',	72),
    (69,	'Justin Evans',	58),
    (70,	'Kaitlyn Thompson',	31),
    (71,	'Kate Johnson',	24),
    (72,	'Katherine Anderson',	75),
    (73,	'Kathryn Greene',	41),
    (74,	'Kelly Collins',	63),
    (75,	'Kennedy Foster',	69),
    (76,	'Kimberly Jones',	52),
    (77,	'Kyle Reed',	28),
    (78,	'Lauren Mitchell',	76),
    (79,	'Leah Cooper',	85),
    (80,	'Leo Sullivan',	87),
    (81,	'Lily Matthews',	77),
    (82,	'Logan Green',	35),
    (83,	'Lucas Taylor',	60),
    (84,	'Mackenzie Parker',	46),
    (85,	'Madeline Jones',	34),
    (86,	'Marcus Evans',	86),
    (87,	'Margaret Scott',	80),
    (88,	'Maria King',	53),
    (89,	'Mason Anderson',	54),
    (90,	'Matthew Brown',	42),
    (91,	'Megan Foster',	30),
    (92,	'Michael Reed',	67),
    (93,	'Miranda Hayes',	50),
    (94,	'Natalie Campbell',	56),
    (95,	'Nathan Grey',	78),
    (96,	'Nicholas Baker',	89),
    (97,	'Olivia Thompson',	49),
    (98,	'Patrick Green',	81),
    (99,	'Penelope Davis',	61),
    (100,	'Rachel Wilson',	38);