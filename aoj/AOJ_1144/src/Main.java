import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	int map[][];
	static final int TOP = 0;
	static final int RIGHT = 1;
	static final int BOTTOM = 2;
	static final int LEFT = 3;
	static final int SPACE = 0;
	static final int BLOCK = 1;
	static final int START = 2;
	static final int GOAL = 3;
	static final int OUT = -1;
	void run(){
		Scanner scan = new Scanner(System.in);
		int h,w;
		while(true){
			w = scan.nextInt();
			h = scan.nextInt();
			if(w == 0 && h == 0)break;
			map = new int[h][w];
			int x = 0;
			int y = 0;
			for(int i = 0;i < h;i++){
				for(int j = 0;j < w;j++){
					map[i][j] = scan.nextInt();
					if(map[i][j] == START){
						map[i][j] = SPACE;
						x = j;
						y = i;
					}
				}
			}
			System.out.println(solve(x,y));
		}
	}

	int solve(int x,int y){
		ArrayList<Integer[]> que = new ArrayList<Integer[]>();
		if(y > 0 && map[y - 1][x] != BLOCK){
			Integer newQue[] = {TOP};
			que.add(newQue);
		}
		if(x < map[0].length - 1 && map[y][x + 1] != BLOCK){
			Integer newQue[] = {RIGHT};
			que.add(newQue);
		}
		if(y < map.length - 1 && map[y + 1][x] != BLOCK){
			Integer newQue[] = {BOTTOM};
			que.add(newQue);
		}
		if(x > 0 && map[y][x - 1] != BLOCK){
			Integer newQue[] = {LEFT};
			que.add(newQue);
		}
		int cnt = 1;
		while(true){
			int amount = que.size();
			for(int i = 0;i < amount;i++){
				int tmpX = x;
				int tmpY = y;
				int tmpMap[][] = new int[map.length][map[0].length];
				for(int a = 0;a < map.length;a++)
					for(int b = 0;b < map[0].length;b++)
						tmpMap[a][b] = map[a][b];
				arrayMove:for(int j = 0;j < que.get(0).length;j++){
					move:while(true){
						if(tmpMap[tmpY][tmpX] == GOAL){
							return cnt;
						}
						switch(que.get(0)[j]){
						case TOP:
							if(tmpY - 1 < 0){
								break arrayMove;
							}
							if(tmpMap[tmpY - 1][tmpX] == BLOCK){
								tmpMap[tmpY - 1][tmpX] = SPACE;
								break move;
							}
							tmpY--;
							break;
						case RIGHT:
							if(tmpX + 1 >= tmpMap[0].length){
								break arrayMove;
							}
							if(tmpMap[tmpY][tmpX + 1] == BLOCK){
								tmpMap[tmpY][tmpX + 1] = SPACE;
								break move;
							}
							tmpX++;
							break;
						case BOTTOM:
							if(tmpY + 1 >= tmpMap.length){
								break arrayMove;
							}
							if(tmpMap[tmpY + 1][tmpX] == BLOCK){
								tmpMap[tmpY + 1][tmpX] = SPACE;
								break move;
							}
							tmpY++;
							break;
						case LEFT:
							if(tmpX - 1 < 0){
								break arrayMove;
							}
							if(tmpMap[tmpY][tmpX - 1] == BLOCK){
								tmpMap[tmpY][tmpX - 1] = SPACE;
								break move;
							}
							tmpX--;
							break;
						}
					}
				}
				if(tmpY >= 0 && tmpX >= 0 && tmpY < tmpMap.length && tmpX < tmpMap[0].length){
					if(tmpY - 1 >= 0 && tmpMap[tmpY - 1][tmpX] != BLOCK){
						Integer newQue[] = makeNewQue(cnt,que.get(0));
						newQue[cnt] = TOP;
						que.add(newQue);
					}
					if(tmpX + 1 < tmpMap[0].length && tmpMap[tmpY][tmpX + 1] != BLOCK){
						Integer newQue[] = makeNewQue(cnt,que.get(0));
						newQue[cnt] = RIGHT;
						que.add(newQue);
					}
					if(tmpY + 1 < tmpMap.length && tmpMap[tmpY + 1][tmpX] != BLOCK){
						Integer newQue[] = makeNewQue(cnt,que.get(0));
						newQue[cnt] = BOTTOM;
						que.add(newQue);
					}
					if(tmpX - 1 >= 0 && tmpMap[tmpY][tmpX - 1] != BLOCK){
						Integer newQue[] = makeNewQue(cnt,que.get(0));
						newQue[cnt] = LEFT;
						que.add(newQue);
					}
				}
				que.remove(0);
			}
			cnt++;
			if(cnt > 10)
				return -1;
		}
	}

	Integer[] makeNewQue(int size,Integer array[]){
		Integer newQue[] = new Integer[size + 1];
		for(int i = 0;i < size;i++)
			newQue[i] = array[i];
		return newQue;
	}

	public static void main(String args[]){
	new Main().run();
	}
}
