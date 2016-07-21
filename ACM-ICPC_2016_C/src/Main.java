import java.util.Scanner;

public class Main {
	void run(){
		Scanner scan = new Scanner(System.in);
		while(true){
			int m = scan.nextInt();
			int n = scan.nextInt();
			if(n == 0 && m == 0)break;
			boolean num[] = new boolean[7368792];
			for(int i = m;;i++){
				if(!num[i]){
					if(n <= 0){
						System.out.println(i);
						break;
					}
					n--;
					for(int j = i;j < num.length;j+=i)
						num[j] = true;
				}
			}
		}
		scan.close();
	}

	public static void main(String args[]){
		new Main().run();
	}
}