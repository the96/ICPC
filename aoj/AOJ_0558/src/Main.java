import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	char map[][];
	int x,y;
	static final char START = 'S';//巣（スタート地点）
	static final char BLOCK = 'X';//障害物
	static final char SPACE = '.';//空き地
	static final char CHECKED = 'C';
	void run(){
		Scanner scan = new Scanner(System.in);
		int h,w,n;
		h = scan.nextInt();//高さ(1 <= h <= 1000)
		w = scan.nextInt();//幅(1 <= w <= 1000)
		n = scan.nextInt();//チーズ工場の数(1 <= n <= 9)
		map = new char[h][w];
		for(int i = 0;i < h;i++){
			String rows = scan.next();
			for(int j = 0;j < w;j++){
				map[i][j] = rows.charAt(j);
				if(map[i][j] == START){
					map[i][j] = SPACE;
					y = i;
					x = j;
				}
			}
		}
		int result = 0;
		for(int i = 1;i <= n;i++){
			result += eat(i);
		}
		System.out.println(result);
	}

	int eat(int cheese){
		ArrayList<Integer> xQue = new ArrayList<Integer>();
		ArrayList<Integer> yQue = new ArrayList<Integer>();
		char moveMap[][] = new char[map.length][map[0].length];
		for(int i = 0;i < map.length;i++){
			moveMap[i] = map[i].clone();
		}
		int cnt = 0;
		xQue.add(x);
		yQue.add(y);
		moveMap[y][x] = CHECKED;
		while(true){
			int amount = xQue.size();
			for(int i = 0;i < amount;i++){
				int x0 = xQue.get(0);
				int y0 = yQue.get(0);
				//チーズの数は1 < N < 9なので問題なし
				//目的の工場に到達したら探査終了、未到達の場合はstackに座標を追加
				if(map[y0][x0] == Integer.toString(cheese).charAt(0)){
					this.x = x0;
					this.y = y0;
					return cnt;
				}else{
					if(chkTile(x0 + 1,y0,moveMap)){
						moveMap[y0][x0 + 1] = CHECKED;
						xQue.add(x0 + 1);
						yQue.add(y0    );
					}
					if(chkTile(x0 - 1,y0,moveMap)){
						moveMap[y0][x0 - 1] = CHECKED;
						xQue.add(x0 - 1);
						yQue.add(y0    );
					}
					if(chkTile(x0,y0 + 1,moveMap)){
						moveMap[y0 + 1][x0] = CHECKED;
						xQue.add(x0    );
						yQue.add(y0 + 1);
					}
					if(chkTile(x0,y0 - 1,moveMap)){
						moveMap[y0 - 1][x0] = CHECKED;
						xQue.add(x0    );
						yQue.add(y0 - 1);
					}
					xQue.remove(0);
					yQue.remove(0);
				}
			}
			cnt++;
		}
	}

	boolean chkTile(int x,int y,char[][] map){
		if(x < 0 || x >= map[0].length)return false;
		if(y < 0 || y >= map.length)return false;
		return map[y][x] != BLOCK && map[y][x] != CHECKED;
	}

	public static void main(String args[]){
		new Main().run();
	}
}

/*
巣:S
障害物:X
空き地:.
チーズ工場:1,2,3,...,9
*/