import java.util.Scanner;
public class Main {
	static final int BALL_NUM = 10;
	int[] ball;
	void run(){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for(int i = 0;i < n;i++){
			//データセット読込
			ball = new int[BALL_NUM];
			for(int j = 0;j < BALL_NUM;j++)
				ball[j] = scan.nextInt();
			String result = solve()?"YES":"NO";
			System.out.println(result);
		}
	}
	boolean solve(){
		int b = 0,c = 0;
		for(int i = 0;i < BALL_NUM;i++){
			if(c >= ball[i])return false;
			if(b < ball[i]){
				b = ball[i];
			}else{
				c = ball[i];
			}
		}
		return true;
	}

	public static void main(String args[]){
		new Main().run();
	}
}
