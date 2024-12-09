--No 1
SELECT * FROM employee;

--No 2
SELECT COUNT(*) AS total_manager
FROM employee
WHERE title = 'Manager';

--No 3
SELECT name, salary
FROM employee
WHERE department IN ('Sales', 'Marketing');

--No 4
SELECT round(AVG(salary),2)  AS avg_salary
FROM employee
WHERE EXTRACT(YEAR FROM join_date) >= EXTRACT(YEAR FROM CURRENT_DATE) - 5;

--No 5
SELECT e.name, SUM(s.sales) AS total_sales
FROM employee e
         JOIN saless s ON e.id = s.employee_id
GROUP BY e.name
ORDER BY total_sales DESC
    LIMIT 5;

--No 6
-- hitung rata2 gaji keseluruhan
-- Cari departemen dengan rata-rata gaji lebih tinggi.
-- Ambil data karyawan di departemen tersebut.
WITH avg_salary_all AS (
    SELECT AVG(salary) AS overall_avg_salary FROM employee
),
     dept_avg_salary AS (
         SELECT department, AVG(salary) AS dept_avg_salary
         FROM employee
         GROUP BY department
         HAVING AVG(salary) > (SELECT overall_avg_salary FROM avg_salary_all)
     )
SELECT e.name, e.salary, d.dept_avg_salary
FROM employee e
         JOIN dept_avg_salary d ON e.department = d.department;

--No 7
SELECT e.name, SUM(s.sales) AS total_sales,
       RANK() OVER (ORDER BY SUM(s.sales) DESC) AS rank
FROM employee e
         JOIN saless s ON e.id = s.employee_id
GROUP BY e.name;

--No 8
-- stored procedure Digunakan untuk menjalankan operasi tanpa mengembalikan hasil query ke klien.
-- maka saya membuatnya dengan function, yang cocok untuk mengembalikan data hasil query.
-- buat dulu function nya
CREATE OR REPLACE FUNCTION get_total_salary(a VARCHAR)
RETURNS TABLE(name VARCHAR, total_salary NUMERIC)
LANGUAGE sql
AS $$
SELECT e.name, SUM(e.salary) AS total_salary
FROM employee e
WHERE e.department = a
GROUP BY e.name;
$$;

-- lalu panggil function nya
select * from get_total_salary('IT')

