
public class Ranking {
	static int numbers=0;
	static void ranking(int score) {
		// TODO Auto-generated method stub
		int i;
		
		numbers+=1;											//~�� ���������� �˸�
		char[] element = new char[numbers];					//~�� ���� �迭�� ����
		for(i=0;i<numbers;i++){
			element[i] = (char)score;							//�迭�� ���� ����
		}
		
		if(numbers==1) {
			System.out.printf("%d", score);					//���� ó������ ���� ������ 1���
		}
		
		Sort.bubbling(element);
	}
}