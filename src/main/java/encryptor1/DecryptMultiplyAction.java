package encryptor1;

public class DecryptMultiplyAction extends Action {

	public DecryptMultiplyAction(String data) {
		super(data);
	}

	public DecryptMultiplyAction(String data, int key) {
		super(data, key);
	}

	@Override
	public String performAction() {
		String newStr = "";
		int modulu = 256;
		int encrypt_key = powMod(5, key, modulu);
		int decrypt_key = inverse(encrypt_key, modulu);
		for (char ch : data.toCharArray()) {
			newStr += (char) (Math.floorMod(ch * decrypt_key, modulu));
		}
		return newStr;
	}

	private int powMod(int a, int b, int n) {
		int pow = 1;
		for (int i = 0; i < b; i++) {
			pow = Math.floorMod(pow * a, n);
		}
		return pow;
	}

	private int inverse(int k, int n) {
		int[] vals = gcd(k, n);
		int d = vals[0];
		int a = vals[1];
		// int b = vals[2];
		if (d > 1) {
			System.out.println("Inverse does not exist.");
			return 0;
		}
		if (a > 0)
			return a;
		return n + a;
	}

	private int[] gcd(int p, int q) {
		if (q == 0)
			return new int[] { p, 1, 0 };
		int[] vals = gcd(q, p % q);
		int d = vals[0];
		int a = vals[2];
		int b = vals[1] - (p / q) * vals[2];
		return new int[] { d, a, b };
	}
}
