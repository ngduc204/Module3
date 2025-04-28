import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double operand1 = Double.parseDouble(request.getParameter("operand1"));
        double operand2 = Double.parseDouble(request.getParameter("operand2"));
        String operator = request.getParameter("operator");

        Calculator calculator = new Calculator();
        String result;

        try {
            double calculationResult = calculator.calculate(operand1, operand2, operator);
            result = "Result: " + operand1 + " " + operator + " " + operand2 + " = " + calculationResult;
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }

        request.setAttribute("result", result);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}