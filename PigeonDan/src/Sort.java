
public class Sort {
	static void bubbling(char[] element) {
		
		int count = Ranking.numbers;
		for (int i = 0; i < count - 1 - i; i++)
			for (int j = 0; j < count - 1 - i; j++)
				if(element[j] > element[j+1])
					Swap.swap(element, j, j+1);
	}
}
