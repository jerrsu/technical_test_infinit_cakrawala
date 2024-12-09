insert into public.roles (id, name)
values  ('a1b28965-627e-465e-81aa-ce4fd615b6ae', 'admin')
ON CONFLICT (id) DO NOTHING;;

-- password is "pass"
insert into public.users (id, password, role_id, user_name)
values  ('2b0fcf69-1e97-4b33-baa1-13c9ff275587', '$2a$10$br6owJ5a4QJf/YZtpgFh1ON19xE0LYzB1KtzRL6JVeuMvo4vXwOF6', 'a1b28965-627e-465e-81aa-ce4fd615b6ae', 'admin')
ON CONFLICT (id) DO NOTHING;;