ALTER TABLE vendor_departments
    MODIFY assigned_date DATE NOT NULL DEFAULT (CURRENT_DATE);
