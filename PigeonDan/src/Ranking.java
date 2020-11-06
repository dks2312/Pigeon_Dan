
public class Ranking {
	static int numbers=0;
	static void ranking(int score) {
		// TODO Auto-generated method stub
		int i;
		
		numbers+=1;											//~번 지나갔음을 알림
		char[] element = new char[numbers];					//~번 도는 배열을 생성
		for(i=0;i<numbers;i++){
			element[i] = (char)score;							//배열에 숫자 저장
		}
		
		if(numbers==1) {
			System.out.printf("%d", score);					//만약 처음으로 숫자 넣으면 1출력
		}
		
		Sort.bubbling(element);
	}
}