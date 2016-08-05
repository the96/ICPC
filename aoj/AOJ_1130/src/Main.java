import java.util.Scanner;

public class Main {
	static final char CHECKED = 'C';
	static final char BLACK = '.';
	static final char START = '@';
	char tile[][];
	void run(){
		Scanner s = new Scanner(System.in);
		while(true){
			int w = s.nextInt();
			int h = s.nextInt();
			if(w == 0 && h == w)break;
			int x = 0;
			int y = 0;
			tile = new char[h][w];
			for(int i = 0;i < h;i++){
				String col = s.next();
				for(int j = 0;j < w;j++){
					tile[i][j] = col.charAt(j);
					if(tile[i][j] == START){
						y = i;
						x = j;
						tile[i][j] = BLACK;
					}
				}
			}
			System.out.println(cntTile(y,x));
		}
	}

	int cntTile(int y,int x){
		//領域外の場合はそれ以上の探索を行わず、0個一致を返す
		if(y >= tile.length || y < 0)return 0;
		if(x >= tile[0].length || x < 0)return 0;
		int cnt = 0;
		if(tile[y][x] == BLACK){
			tile[y][x] = CHECKED;
			cnt++;
			//深さ優先探索？上下左右を探索しタイル数を返す
			cnt += cntTile(y + 1,x);
			cnt += cntTile(y - 1,x);
			cnt += cntTile(y,x + 1);
			cnt += cntTile(y,x - 1);
			return cnt;
		}else{
			return 0;
		}
	}

public static void main(String args[]){
		new Main().run();
	}
}
