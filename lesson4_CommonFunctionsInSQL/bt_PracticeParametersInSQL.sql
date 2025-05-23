use quanlysinhvien;
SELECT * FROM Subject
WHERE credit = (
    SELECT MAX(credit)
    FROM Subject
);
SELECT s.* FROM Subject s JOIN Mark m ON s.subid = m.subid
WHERE m.mark = (
    SELECT MAX(mark)
    FROM Mark
);
SELECT st.studentid, st.full_name, AVG(m.mark) AS average_mark
FROM Student st
JOIN Mark m ON st.studentid = m.studentid
GROUP BY st.studentid, st.full_name
ORDER BY average_mark DESC;