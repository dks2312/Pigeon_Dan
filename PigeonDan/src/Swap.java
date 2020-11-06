
public class Swap {
	static void swap(char[] element, int i, int j) {
		char temp = element[i];
		element[i] = element[j];
		element[j] = temp;
	}
}
