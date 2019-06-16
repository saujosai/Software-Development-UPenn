public class countVowels {
	public static void main(String[] args) {
		String word = "Test String";
		int vowelNum = 0;
		int i = 0;
		while (i < word.length()) {
			if (word.charAt(i) == 'a' ||
					word.charAt(i) == 'e' || 
					word.charAt(i) == 'i' || 
					word.charAt(i) == 'o'|| 
					word.charAt(i) == 'u') {
				vowelNum++;
			}
			i++;
		}
		System.out.println(vowelNum);

	}
}
