
import java.math.BigDecimal;
import java.util.Stack;

/**
 * 写一个计算器类（Calculator），可以实现两个数的加、减、乘、除运算，并可以进行undo和redo操作
 */
public class Calculator {
    private BigDecimal currentValue;
    private final Stack<BigDecimal> history;
    private final Stack<BigDecimal> future;

    public Calculator() {
        currentValue = BigDecimal.ZERO;
        history = new Stack<>();
        future = new Stack<>();
    }

    public Calculator add(BigDecimal num) {
        history.push(currentValue);
        currentValue = currentValue.add(num);
        future.clear();
        return this;
    }

    public Calculator subtract(BigDecimal num) {
        history.push(currentValue);
        currentValue = currentValue.subtract(num);
        future.clear();
        return this;
    }

    public Calculator multiply(BigDecimal num) {
        history.push(currentValue);
        currentValue = currentValue.multiply(num);
        future.clear();
        return this;
    }

    public Calculator divide(BigDecimal num) {
        if (BigDecimal.ZERO.compareTo(num) != 0) {
            history.push(currentValue);
            currentValue = currentValue.divide(num, 4, BigDecimal.ROUND_HALF_UP);
            future.clear();
        } else {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public Calculator undo() {
        if (!history.isEmpty()) {
            future.push(currentValue);
            currentValue = history.pop();
        }
        return this;
    }

    public Calculator redo() {
        if (!future.isEmpty()) {
            history.push(currentValue);
            currentValue = future.pop();
        }
        return this;
    }

    public Calculator printResult() {
        System.out.println("Result: " + currentValue);
        return this;
    }

    public static void main(String[] args) {
        new Calculator()
                .add(new BigDecimal("4"))
                .add(new BigDecimal("6"))
                .multiply(new BigDecimal("5"))
                .printResult()
                .undo()
                .printResult()
                .divide(new BigDecimal("2"))
                .printResult()
                .undo()
                .printResult()
                .undo()
                .printResult();


    }
}
