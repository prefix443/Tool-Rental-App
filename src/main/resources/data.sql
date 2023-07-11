DELETE FROM tools;
DELETE FROM tool_types;

INSERT INTO tool_types (id ,tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge)
VALUES
    (1, 'LADDER', 1.99, true, true, false),
    (2, 'CHAINSAW', 1.49, true, false, true),
    (3, 'JACKHAMMER', 2.99, true, false, false);

INSERT INTO tools (id, tool_code, brand, tool_type_id)
VALUES
    (1, 'LADW', 'Werner', 1),
    (2, 'CHNS', 'Stihl', 2),
    (3, 'JAKR', 'Warner', 3),
    (4, 'JAKD', 'DeWalt', 3);
