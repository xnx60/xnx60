package examSiZe;

import java.util.Random;
import java.util.Scanner;



public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //����������Ŀ������
        System.out.println("���������ɵ���Ŀ������:");
        int questionNum = scanner.nextInt();
        if(questionNum<1) { 
            System.out.println("��Ŀ��������С��1�����������룺");
            questionNum = scanner.nextInt();
        }
        //������Ŀ����ֵ�ķ�Χ
        System.out.println("��������Ŀ����ֵ�ķ�Χ��");
        int range = scanner.nextInt();
        if(range<1) {    
            System.out.println("��ֵ��Χ����С��1�����������룺");
            range = scanner.nextInt();
        }


        for(int i = 0;i<questionNum;i++){
            Random r = new Random();
			/* ����4����ֵ������4������ */
            // ����
            int molecule1 =  r.nextInt(range);         
            int molecule2 =  r.nextInt(range);         
            int molecule3 =  r.nextInt(range);         
            int molecule4 =  r.nextInt(range);         
            // ��ĸ
            int denominator1 = r.nextInt(range)+1;     
            int denominator2 = r.nextInt(range)+1;     
            int denominator3 = r.nextInt(range)+1;    
            int denominator4 = r.nextInt(range)+1;    

            //���������Ŀ�������3��
            int operatorNum = r.nextInt(3) + 1;
            char[] operator = {'+','-','��','��'};    //���������
            int j1 = r.nextInt(4);
            int j2 = r.nextInt(4);
            int j3 = r.nextInt(4);

            //�����4����ֵ���½�4������������Ҳ�ɿ��ɷ�����
            Fraction num1 = new Fraction(molecule1,denominator1);
            Fraction num2 = new Fraction(molecule2,denominator2);
            Fraction num3 = new Fraction(molecule3,denominator3);
            Fraction num4 = new Fraction(molecule4,denominator4);


            //���������Ŀ��1����ʱ��
            if(operatorNum == 1){
                String question = num1 + " " + operator[j1]+ " " + num2 + " = ";
                if(operator[j1] == '��' && num2.molecule == 0) {
                    i--;
                    continue;
                }
                //ʹ�ü���������Ŀ��
                Fraction answer=Calculator.calculator.calculate(question);
                //���ָ��������²�����Ŀ
                if(answer.molecule < 0 || answer.denominator < 0) {
                    i--;
                    continue;
                }
                System.out.println("��Ŀ" + (i+1) + ": " + question);
                //��Ŀ��𰸴���Ŀ¼�ļ���
                QuestionWrite qw = new QuestionWrite();
                qw.Write(question);
                AnswerWrite aw = new AnswerWrite();
                aw.Write(answer);
            }



            if(operatorNum == 2) {
                //ʹ��str�ַ��������Ŀ
                String question = num1 + " " + operator[j1] + " "+ num2 + " " + operator[j2] + " " + num3 + " = ";
                //���ź��治����0
                if((operator[j1] == '��' && num2.molecule == 0) || (operator[j2] == '��' && num3.molecule == 0)) {
                    i--;
                    continue;
                }
                //ʹ�ü���������Ŀ��
                Fraction answer=Calculator.calculator.calculate(question);
                //���ָ��������²�����Ŀ
                if(answer.molecule < 0 || answer.denominator < 0) {
                    i--;
                    continue;
                }
                System.out.println("��Ŀ" + (i+1) + ": " + question);
                QuestionWrite qw = new QuestionWrite();
                qw.Write(question);
                AnswerWrite aw = new AnswerWrite();
                aw.Write(answer);
            }



            if(operatorNum == 3) {
                //ʹ��str�ַ��������Ŀ
                String question = num1 + " " + operator[j1] + " " + num2 + " " + operator[j2] + " " + num3 + " " + operator[j3] + " " + num4 + " = ";
                //���ź��治����0
                if((operator[j1] == '��' && num2.molecule == 0) || (operator[j2] == '��' && num3.molecule == 0) || (operator[j3] == '��' && num4.molecule == 0)) {
                    i--;
                    continue;
                }
                //ʹ�ü���������Ŀ��
                Fraction answer=Calculator.calculator.calculate(question);
                //���ָ��������²�����Ŀ
                if(answer.molecule < 0 || answer.denominator < 0) {
                    i--;
                    continue;
                }
                System.out.println("��Ŀ" + (i+1) + ": " + question);
                QuestionWrite qw = new QuestionWrite();
                qw.Write(question);
                AnswerWrite aw = new AnswerWrite();
                aw.Write(answer);
            }



        }
        QuestionWrite.save();   //������Ŀ�ʹ𰸵�
        AnswerWrite.save();


    }
}

