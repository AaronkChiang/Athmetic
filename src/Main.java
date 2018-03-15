
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 
 * @author Dushik
 * 
 */
public class Main {
    
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");  
	
	public static void main(String[] args){
		//System.out.println("�����룺");
		//Scanner nu=new Scanner(System.in);
		//int num=nu.nextInt();
		int num=Integer.parseInt(args[0]);
		//�жϲ����Ϸ��ԡ�����		
		char[] operator=new char[]{'+','-','*','/'};
		Random random=new Random();
		ArrayList<String> expression=new ArrayList<String>();
		for(int i=0;i<num;i++){
			int n=random.nextInt(3)+3; //3-5�������
			int[] number=new int[n+1]; 
			String ex=new String();
			for(int j=0;j<=n;j++){
				number[j]=random.nextInt(100)+1; //4-5������
			}
			for(int j=0;j<n;j++){
				int s=random.nextInt(4);//���ѡ��ĳ�������
				ex+=String.valueOf(number[j])+String.valueOf(operator[s]);///5+4+6+9
				if(s==3){number[j+1]=decide(number[j],number[j+1]);}
			}
			ex+=String.valueOf(number[n]);
			expression.add(ex);
		}
		WriteToFile write=new WriteToFile(
				"ArithmeticExpression.txt",calculate(expression));
		
	}
	/**
	 * �漴ȡx,yΪ1-100֮�䣬x��������y��yֵ
	 * @param x
	 * @param y
	 * @return
	 */
	private static int decide(int x,int y){
		Random random=new Random();
		if(x%y!=0){
			y=random.nextInt(100)+1;
			return decide(x,y);
		}
		else{
			return y; 
		}
	}
	
	/**
	 * ����ÿ����ʽ�Ľ��������������ʽ
	 * @param arrayList 
	 * @return arrayList
	 */
	private static ArrayList<String> calculate(ArrayList<String> arrayList){
		ArrayList<String> ArithExpress=new ArrayList<String>();
		for(String ax:arrayList){
			try {
				ax=ax+"="+jse.eval(ax);
				System.out.println(ax);
				ArithExpress.add(ax);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ArithExpress;
	}
}
