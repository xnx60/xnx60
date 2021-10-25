package examSiZe;

//public class Fraction {
//
//}
//package cn.itcase.FourOperations;
/*
    ���������Լ�ֺͻ������
    �������Կ����Ƿ�ĸΪ1�ķ���
*/
public class Fraction {
    boolean reduced=false;
    public int molecule;     //����
    public int denominator;   //��ĸ

    //����
    public Fraction(int molecule, int denominator) {
        this.molecule = molecule;
        this.denominator = denominator;
        reduction();
    }
    public Fraction(int molecule) {
        this.molecule = molecule;
        denominator = 1;
    }

    //����
    public int getNum(){
        if(denominator!=1){
            System.out.println(new Throwable().getStackTrace()[0].toString());
        }
        return molecule;
    }

    //Լ�ִ���
    public void reduction(){
        int x=gcd(molecule,denominator);   //�������
        if(denominator==0){
            System.out.println(new Throwable().getStackTrace()[0]);
        }
        if(x==0){
            //System.out.println(new Throwable().getStackTrace()[0]);
            return;
        }
        molecule /=x;       //���ӳ���������������
        denominator/=x;     //��ĸ��������������ĸ
        if(x>1) {
            this.reduced = true;
        }
    }

    public boolean isFraction(){    //����Ƿ����ͷ���1
        return !(molecule %denominator==0);
    }
    //���ط�����ʽ
    @Override
    public String toString() {
        if(isFraction()){      //�Ƿ����ͷ����������ʽ
            if(molecule >denominator){
                return (molecule /denominator)+"��"+ molecule %denominator+"/"+denominator;  //������
            }
            return molecule +"/"+denominator;   //�����
        }
        else return ""+ molecule /denominator;  //����
    }

    //���������
    private static int gcd(int a,int b) {
        return (b==0)?a:gcd(b,a%b);
    }


    //�ӷ�
    public static Fraction add(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.denominator+b.molecule *a.denominator,b.denominator*a.denominator);
    }
    //����
    public static Fraction sub(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.denominator-b.molecule *a.denominator,b.denominator*a.denominator);
    }
    //�˷�
    public static Fraction mul(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.molecule,b.denominator*a.denominator);
    }
    //����
    public static Fraction div(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.denominator,b.molecule *a.denominator);
    }
    //����������������
    public static Fraction with(Fraction a, Fraction b) {
        return new Fraction(b.denominator*a.getNum()+b.molecule,b.denominator);
    }
}

