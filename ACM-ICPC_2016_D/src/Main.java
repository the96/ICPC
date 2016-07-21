import java.util.Scanner;
public class Main {
	void run(){
		Scanner scan = new Scanner(System.in);
		while(true){
			int n = scan.nextInt();
			if(n == 0)break;
			int w[] = new int[n];
			for(int i = 0;i < n;i++ ){
				w[i] = scan.nextInt();
			}
			System.out.println(solve(w));
		}
	}
	int solve(int w[]){
		int len = w.length;
		int erasable[][] = new int[len][len];
		for(int a = 0;a < len;a++){
			erasable[a][a] = 1;
		}
		for(int i = 1;i < len;i++){
			for(int j = 0;i + j < len;j++){
				//System.out.println("i:" + i + ":j:" + j);
				if(erasable[j + 1][i + j - 1] == 0 && Math.abs(w[j] - w[i + j]) <= 1){
					//System.out.println(w[j] + "!" + w[i + j]);
					System.out.println(0);
					erasable[j][i + j] = 0;
					continue;
				}
				int min = 100000;
				for(int x = 1;x <= i;x++){
					//System.out.println(i+":"+j);
					//System.out.println((j + i - x + 1) + ":" + (i + j) + ":::" + (j) + ":" + (i + j - x));
					//System.out.println("!!" + erasable[j + i - x + 1][i + j] + " !!" +  erasable[j][i + j - x]);
					min = Math.min(erasable[j + i - x + 1][i + j] + erasable[j][i + j - x],min);
				}
				//配列のindex
				erasable[j][i + j] = min;
				//System.out.println(min);
			}
		}
		return len - erasable[0][len-1];
	}
	public static void main(String args[]){
		new Main().run();
	}
}