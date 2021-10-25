package examSiZe;

import java.util.Stack;


/**
 ��������ʶ���������Ŀ�ַ���Ȼ�������
 **/
public class Calculator {
    final public static Calculator calculator = new Calculator();
    //�洢���ʽ�е���ֵ
    private Stack<Fraction> fractionStack = null;
    //�洢����������źʹ�������
    private Stack<Character> operationStack = null;

    //��������������������ʽ�����ؼ�����
    public Fraction calculate(String numStr) {
        if(numStr!=null)
            numStr=numStr.replaceAll(" ", "");
        else {
            return new Fraction(0);
        }

        // ����������ʽβ��û�С�=���ţ�����β����ӡ�=������ʾ������
        if (numStr.length() >= 1 && !('='==numStr.charAt(numStr.length() - 1))) {
            numStr += "=";
        }

        // �����ʽ�Ƿ�Ϸ�
        if (!isStandard(numStr)) {
            System.out.println(new Throwable().getStackTrace()[0]+":"+numStr);
            return new Fraction(0);
        }
        // ��ʼ��ջ
        fractionStack = new Stack<Fraction>();
        operationStack = new Stack<Character>();
        // ���ڻ������֣���Ϊ���ֿ����Ƕ�λ��
        StringBuffer nowFractionNum = new StringBuffer();
        // �ӱ��ʽ�ĵ�һ���ַ���ʼ����
        for (int i = 0; i < numStr.length(); i++) {
            char nowChar = numStr.charAt(i); // ��ȡһ���ַ�
            if (isNumber(nowChar)) { // ����ǰ�ַ�������
                nowFractionNum.append(nowChar); // ���뵽���ֻ�����
            } else { // �����ֵ����
                String checkFractionNum = nowFractionNum.toString(); // �����ֻ���תΪ�ַ���
                if (!checkFractionNum.isEmpty()) {
                    int num = Integer.parseInt(checkFractionNum); // �������ַ���תΪ��������
                    fractionStack.push(new Fraction(num)); // ������ѹջ
                    nowFractionNum = new StringBuffer(); // �������ֻ���
                }
                //����ǰ��������ȼ�С��ջβ�����ȼ�����Ӧ���ȴ���ǰ�������
                while (!comparePri(nowChar) && !operationStack.empty()) {
                    Fraction b = fractionStack.pop(); // ��ջ��ȡ�����֣�����ȳ�
                    Fraction a = fractionStack.pop();
                    // ȡ��ջβ�����������Ӧ���㣬���ѽ��ѹջ������һ������
                    switch (operationStack.pop()) {
                        //���������
                        case '��':
                            fractionStack.push(Fraction.with(a , b));
                            break;
                        //������ͨ�����
                        case '+':
                            fractionStack.push(Fraction.add(a , b));
                            break;
                        case '-':
                            fractionStack.push(Fraction.sub(a , b));
                            break;
                        case '��':
                        case '*':
                            fractionStack.push(Fraction.mul(a ,  b));
                            break;
                        case '/':
                        case '��':
                            fractionStack.push(Fraction.div(a ,  b));
                            break;
                        default:
                            break;
                    }
                }
                if (nowChar != '=') {
                    operationStack.push(nowChar); // ������ջ
                    if (nowChar == ')') { // ȥ����
                        operationStack.pop();
                        operationStack.pop();
                    }
                }
            }
        }
        return fractionStack.pop(); // ���ؼ�����
    }

    //����ǲ��ǺϷ��ı��ʽ
    private boolean isStandard(String numStr) {
        if (numStr == null || numStr.isEmpty()) // ���ʽ����Ϊ��
            return false;
        Stack<Character> stack = new Stack<Character>(); // �����������ţ�������������Ƿ�ƥ��
        boolean haveEq = false; // �������'='�����Ƿ���ڶ��
        for (int i = 0; i < numStr.length(); i++) {
            char nowChar = numStr.charAt(i);
            // �ж��ַ��Ƿ�Ϸ�
            if (!(
                    isNumber(nowChar)
                            || '('==nowChar
                            || ')'==nowChar
                            || '+'==nowChar
                            || '-'==nowChar
                            || '*'==nowChar
                            || '/'==nowChar
                            || '='==nowChar
                            || '��'==nowChar
                            || '��'==nowChar
                            || '��'==nowChar
            )) {
                return false;
            }
            // ��������ѹջ������������������Ž���ƥ��
            if ('('==nowChar) {
                stack.push(nowChar);
            }
            if (')'==nowChar) { // ƥ������
                if (stack.isEmpty() || !('('==stack.pop())) // �����Ƿ�ƥ��
                    return false;
            }
            // ����Ƿ��ж��'='��
            if ('='==nowChar) {
                if (haveEq)
                    return false;
                haveEq = true;
            }
        }
        // ���ܻ���ȱ�������ŵ����
        if (!stack.isEmpty())
            return false;
        // ���'='���Ƿ���ĩβ
        return '=' == numStr.charAt(numStr.length() - 1);
    }

    //�ж��ַ��Ƿ���0-9������
    private boolean isNumber(char num) {
        return num >= '0' && num <= '9';
    }
    //�Ƚ����ȼ��������ǰ�������ջ��Ԫ����������ȼ����򷵻�true�����򷵻�false
    private boolean comparePri(char symbol) {
        if (operationStack.empty()) { // ��ջ����ture
            return true;
        }

        /* �������ȼ�˵�����Ӹߵ��ͣ�:
         ��1��: (
         ��2��: * /
         ��3��: + -
         ��4��: )
        */
        char top = operationStack.peek(); // �鿴��ջ�����Ķ���ע�ⲻ�ǳ�ջ
        if (top == '(') {
            return true;
        }
        // �Ƚ����ȼ�
        switch (symbol) {
            case '(': // ���ȼ����
                return true;
            case '/':
                return true;
            case '��':
                // ���ȼ���+��-��
                return top == '+' || top == '-' || top == '��' || top == '��' || top == '*';
            case '��':
            case '*':
            case '��':
                // ���ȼ���+��-��
                return top == '+' || top == '-';
            case '+':
            case '-':
                return false;
            case ')': // ���ȼ����
                return false;
            case '=': // ������
                return false;
            default:
                break;
        }
        return true;
    }
}

