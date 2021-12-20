INSERT INTO qakashilliacea_user(id, verified_email, password, username)
VALUES (1, true, 'SYSTEM', 'SYSTEM');
INSERT INTO qakashilliacea_user(id, verified_email, password, username)
VALUES (2, true, 'bcrypt:13:60:16:y::$2y$13$J3oU97suEy8SzDjEPODrN.E1CzFqmcOfQrs9eBqlkyvhnxV5hH0SO', 'admin');
INSERT INTO public.qakashilliacea_role(id, name)
VALUES (1, 'USER');
INSERT INTO public.qakashilliacea_role(id, name)
VALUES (2, 'ADMIN');
INSERT INTO public.qakashilliacea_user_roles(user_id, roles_id)
VALUES (2, 1);
INSERT INTO public.qakashilliacea_user_roles(user_id, roles_id)
VALUES (2, 2);
INSERT INTO public.qakashilliacea_publication_type(id, type)
VALUES (1, 'news');
INSERT INTO public.qakashilliacea_publication_type(id, type)
VALUES (2, 'blog');
