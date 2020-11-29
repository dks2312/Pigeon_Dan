package Pigeon;

import java.util.Scanner;

public class PigeonDanGameMain {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("구구단을 외우자! 당신의 구구단 실력을 지금 테스트 해보세요! \n");
		System.out.print("- 아무 키나 입력하면 시작됩니다 - \n");
		System.out.print("A : ");
		scan.next().charAt(0);
		

		PigeonDanGame.state();
		
		System.out.println("[ 게임을 종료합니다 ]");
	}
}
