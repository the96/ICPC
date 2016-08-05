import java.util.Scanner;
public class Main {
	static final char CHECKED = '0';
	char area[][];
	char fruit;
	void run(){
		Scanner scan = new Scanner(System.in);
		while(true){
			int h = scan.nextInt();
			int w = scan.nextInt();
			if(h == 0 && w == 0)break;
			area = new char[h][w];
			for(int i = 0;i < h;i++){
				String col = scan.next();
				for(int j = 0;j < w;j++){
					area[i][j] = col.charAt(j);
				}
			}
			int groupCnt = 0;
			for(int i = 0;i < h;i++){
				for(int j = 0;j < w;j++){
					//チェック済みはスキップ
					if(area[i][j] == CHECKED)continue;
					groupCnt++;
					fruit = area[i][j];
					checkArea(i,j);
				}
			}
			System.out.println(groupCnt);
		}
	}

	//連続する区画があればチェックする
	void checkArea(int y,int x){
		//領域外はNG
		if(y >= area.length || y < 0)return;
		if(x >= area[0].length || x < 0)return;
		if(area[y][x] == fruit){
			area[y][x] = CHECKED;
			checkArea(y + 1,x    );
			checkArea(y - 1,x    );
			checkArea(y    ,x + 1);
			checkArea(y    ,x - 1);
		}
	}

	public static void main(String args[]){
		new Main().run();
	}
}
