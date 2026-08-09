UPDATE users
SET password_hash = '$2a$10$AxPtVlAla93a2pXCnOR.qe27KPDGi59/N3htVBb5PJrkwQEAPoIHu',
    active = TRUE
WHERE email = 'admin@vms.com';
