package Pigeon;

import java.util.Scanner;

public class PigeonDanGameMain {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("�������� �ܿ���! ����� ������ �Ƿ��� ���� �׽�Ʈ �غ�����! \n");
		System.out.print("- �ƹ� Ű�� �Է��ϸ� ���۵˴ϴ� - \n");
		System.out.print("A : ");
		scan.next().charAt(0);
		

		PigeonDanGame.state();
		
		System.out.println("[ ������ �����մϴ� ]");
	}
}
