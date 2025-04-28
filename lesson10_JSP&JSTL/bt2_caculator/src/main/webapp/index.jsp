<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Simple Calculator</title>
</head>
<body>
<h1>Calculator</h1>
<form action="calculate" method="post">
    <label>First operand:</label>
    <input type="text" name="operand1" required><br>
    <label>Operator:</label>
    <select name="operator">
        <option value="Addition">Addition</option>
        <option value="Subtraction">Subtraction</option>
        <option value="Multiplication">Multiplication</option>
        <option value="Division">Division</option>
    </select><br>
    <label>Second operand:</label>
    <input type="text" name="operand2" required><br>
    <input type="submit" value="Calculate">
</form>
</body>
</html>