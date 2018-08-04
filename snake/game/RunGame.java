package snake.game;

import snake.util.Setting;

public class RunGame extends Thread {
    static final long serialVersionUID = 1;
    //size
    int WIDTH;
    int HEIGHT;
    Player p1;
    Player p2;
    Point[] points;
    public static Wall[] walls;
    public static SnakeCave[] snakeCaves;
    int numberOfPoint;
    //counter
    int move[];
    //keyboard valid[id]
    static boolean valid[];
    DynamicUIData duidata;
    
    public RunGame(DynamicUIData duiData){
        WIDTH = 1000;
        HEIGHT = 600;
        p1 = new Player(5);
        p2 = new Player(5, 2);
        numberOfPoint = 0;
        points = null;
        move = new int[2];
        //keyboard
        valid = new boolean[2];
        Point.eggLeft = 2;
        //put wall
        putWall(Wall.getWalls());
        //put caves
        putCave(SnakeCave.getSnakeCaves());
        //put egg
        putPoint();
        putPoint();
        this.duidata = duiData;
    }
    public void moveSnake(Snake s, Snake other, int id) throws NotEnoughSnakeException, 
                                    ArrayIndexOutOfBoundsException{
    	
    	//System.out.println("move");
        if(s.wait==0) {//wait for two secs
            //move snake
            move[id]+=Setting.unit/20;
            for(int i=0; i<s.length; i++) {
                switch(s.bodies[i].dir) {
                    case 0: //right
                        s.bodies[i].x+=Setting.unit/20;
                        break;
                    case 1: //left
                        s.bodies[i].x-=Setting.unit/20;
                        break;
                    case 2: //up
                        s.bodies[i].y-=Setting.unit/20;
                        break;
                    case 3: //down
                        s.bodies[i].y+=Setting.unit/20;
                        break;
                }
            }
            
            if(move[id]==Setting.unit) {
                for(int i=s.length-1; i>=1; i--) {
                    s.bodies[i].dir = s.bodies[i-1].dir;
                    s.bodies[i].show = s.bodies[i-1].show;
                    if(s.bodies[i].x>WIDTH-200-Setting.unit)
                        s.bodies[i].x = s.bodies[i].x%(WIDTH-200-Setting.unit)-Setting.unit;
                    if(s.bodies[i].x<0)
                        s.bodies[i].x = s.bodies[i].x+WIDTH-200;
                    if(s.bodies[i].y>HEIGHT-Setting.unit)
                        s.bodies[i].y = s.bodies[i].y%(HEIGHT-Setting.unit)-Setting.unit;
                    if(s.bodies[i].y<0)
                        s.bodies[i].y = s.bodies[i].y+HEIGHT;
                }
                if(s.bodies[0].x>WIDTH-200-Setting.unit)
                    s.bodies[0].x = s.bodies[0].x%(WIDTH-200-Setting.unit)-Setting.unit;
                if(s.bodies[0].x<0)
                    s.bodies[0].x = s.bodies[0].x+WIDTH-200;
                if(s.bodies[0].y>HEIGHT-Setting.unit)
                    s.bodies[0].y = s.bodies[0].y%(HEIGHT-Setting.unit)-Setting.unit;
                if(s.bodies[0].y<0)
                    s.bodies[0].y = s.bodies[0].y+HEIGHT;
                s.bodies[0].dir = s.dir;
                move[id] = 0;
                valid[id] = true;
            }
        }
        else {
            move[id] = Setting.unit/20;
            s.wait--;
        }
        
        // get point
        if(getPoint(s)) {
            s.getPoint();
            switch(id) {
                case 0:
                    p1.getPoint();
                    break;
                case 1:
                    p2.getPoint();
                    break;
            }
        }
        //hit head
        if(isHitHead(s, other) && s.wait==0) {
        	p1.numberOfSnack--;
        	p2.numberOfSnack--;
        	int choiceCave = (int) (SnakeCave.CAVE_NUMBER * Math.random());
        	int choiceCave2;
        	while(true) {
            	choiceCave2 = (int) (SnakeCave.CAVE_NUMBER * Math.random());
            	if(choiceCave!=choiceCave2)
            		break;
        	}
            //System.out.println(choiceCave);
            try {
            	move[0] = Setting.unit/20;
                p1.snakes[p1.numberOfSnack-1] = new Snake(RunGame.snakeCaves[choiceCave].x, RunGame.snakeCaves[choiceCave].y);
                //System.out.println(p1.getSnake().bodies[0].x+", "+ p1.getSnake().bodies[0].y);
                //valid[0] = true;
                move[1] = Setting.unit/20;
                p2.snakes[p2.numberOfSnack-1] = new Snake(RunGame.snakeCaves[choiceCave2].x, RunGame.snakeCaves[choiceCave2].y);
                //valid[1] = true;
                //System.out.println(GameWindow.snakeCaves[choiceCave].x+", "+ GameWindow.snakeCaves[choiceCave].y);
            } catch (ArrayIndexOutOfBoundsException e) {
                Setting.GAMEOVER = true;
            }
        }
        //hit wall
        else if(isHit(s, other)) {
            switch(id) {
                case 0:
                    p1.numberOfSnack--;
                    //System.out.println(p1.numberOfSnack);
                    break;
                case 1:
                    p2.numberOfSnack--;
                    //System.out.println(p1.numberOfSnack);
                    break;
            }
            int choiceCave = (int) (SnakeCave.CAVE_NUMBER * Math.random());
            //System.out.println(choiceCave);
            try {
                switch(id) {
                    case 0:
                        move[0] = Setting.unit/20;
                        p1.snakes[p1.numberOfSnack-1] = new Snake(RunGame.snakeCaves[choiceCave].x, RunGame.snakeCaves[choiceCave].y);
                        //System.out.println(p1.getSnake().bodies[0].x+", "+ p1.getSnake().bodies[0].y);
                        //valid[0] = true;
                        break;
                    case 1:
                        move[1] = Setting.unit/20;
                        p2.snakes[p2.numberOfSnack-1] = new Snake(RunGame.snakeCaves[choiceCave].x, RunGame.snakeCaves[choiceCave].y);
                        //valid[1] = true;
                        break;
                }
                //System.out.println(GameWindow.snakeCaves[choiceCave].x+", "+ GameWindow.snakeCaves[choiceCave].y);
            } catch (ArrayIndexOutOfBoundsException e) {
                Setting.GAMEOVER = true;
            }
            //System.out.println("hit function");
        }
        //head go in cave
        if(isCave(s) && s.wait==0) {
            //System.out.println("head go in cave");
            s.bodies[0].show=false;
            s.inCave = 100;
            move[id] = 0;
        }
        //tail go in cave
        else if(!s.isInCave && !s.bodies[s.length-1].show && s.wait==0  && !s.bodies[0].show) {
            s.isInCave = true;
            s.inCave = 2*Snake.speed;
            //System.out.println("tail go in cave");
        }
        //count two secs
        else if(s.inCave!=0 && s.wait==0 && s.isInCave && !s.bodies[0].show) {
            s.inCave--;
            //System.out.println(move);
            //System.out.println("count two secs");
        }
        //head out cave
        else if(s.inCave==0 && !s.bodies[0].show && s.wait==0) {
            //System.out.println("head out cave");
            s.isInCave = false;
            s.bodies[0].show=true;
            int choiceCave = (int) (SnakeCave.CAVE_NUMBER * Math.random());
            s.setPosAndDir(snakeCaves[choiceCave].x, snakeCaves[choiceCave].y);
            move[id] = Setting.unit/20;
            //System.out.println(move);
        }
        speedControl();
        pointControl();
    }
    //--------------------------------------------------------------
    public void putPoint() {
        int x, y;
        while(true) {
            x = Setting.unit * (int)(40*Math.random());
            y = Setting.unit * (int)(30*Math.random());
            if(isValid(x, y))
                break;
        }
        duidata.points[numberOfPoint] = new Point(x, y);
        duidata.numberOfPoint++;
        //System.out.println(numberOfPoint);
    }
    //if the position can set apple 
    private boolean isValid(int x, int y)  {
    	//wall
        Wall[] walls = RunGame.walls;
        SnakeCave[] snakeCaves = RunGame.snakeCaves;
        for(int i=0; i<Wall.WALL_NUMBER; i++) {
            for(int row=0; row<walls[i].height; row++) {
                for(int col=0; col<walls[i].width; col++) {
                    if(x==walls[i].x+Setting.unit*col && y==walls[i].y+Setting.unit*row)
                        return false;
                }
            }
        }
        //cave
        for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) {
            if(x==snakeCaves[i].x && y==snakeCaves[i].y)
                return false;
        }
        //snakes
        try {
            for(int i=0;i<p1.getSnake().length; i++) {
            	if(x==p1.getSnake().bodies[i].x && y==p1.getSnake().bodies[i].y)
            		return false;
            }
            for(int i=0;i<p2.getSnake().length; i++) {
            	if(x==p2.getSnake().bodies[i].x && y==p2.getSnake().bodies[i].y)
            		return false;
            }
        }catch(NotEnoughSnakeException e) {
        	
        }
        return true;
    }
    public void putWall(Wall[] walls) {
        RunGame.walls = walls;
    }
    public void putCave(SnakeCave[] cave) {
        RunGame.snakeCaves = cave;
    }
    //---------hit head----------
    public boolean isHitHead(Snake s, Snake other) throws NotEnoughSnakeException {
    	if(s.bodies[0].x>other.bodies[0].x - Setting.unit
            	&& s.bodies[0].x<other.bodies[0].x + Setting.unit
                && s.bodies[0].y>other.bodies[0].y - Setting.unit
                && s.bodies[0].y<other.bodies[0].y + Setting.unit
                && s.bodies[0].show 
                && other.bodies[0].show)
    		return true;
    	return false;
    }
    //-------------hit wall or other's bodies------------
    public boolean isHit(Snake s, Snake other) throws NotEnoughSnakeException{
    	//hit wall
        for(int i=0; i<Wall.WALL_NUMBER; i++) { //hit wall
            for(int row=0; row<walls[i].height; row++) {
                for(int col=0; col<walls[i].width; col++) {
                    if(s.bodies[0].x>walls[i].x+Setting.unit*col-Setting.unit 
                    	&& s.bodies[0].x<walls[i].x+Setting.unit*col+Setting.unit
                        && s.bodies[0].y>walls[i].y+Setting.unit*row-Setting.unit
                        && s.bodies[0].y<walls[i].y+Setting.unit*row+Setting.unit
                        && s.bodies[0].show)
                        return true;
                }
            }
        }
        //hit bodies
        for(int i=1; i<s.length; i++) {
            if(s.bodies[0].x==s.bodies[i].x
                && s.bodies[0].y==s.bodies[i].y
                && s.bodies[0].show 
                && s.bodies[i].show) {
                    //System.out.println(i);
                    //System.out.println(s.dir);
                    return true;
                }
        }
        //hit the other snake's body
        for(int i=1; i<other.length; i++) {
            if(s.bodies[0].x>other.bodies[i].x - Setting.unit
            	&& s.bodies[0].x<other.bodies[i].x + Setting.unit
                && s.bodies[0].y>other.bodies[i].y - Setting.unit
                && s.bodies[0].y<other.bodies[i].y + Setting.unit
                && s.bodies[0].show 
                && other.bodies[i].show) {
                    //System.out.println(i);
                    //System.out.println(s.dir);
                    return true;
                }
        }
        return false;
    }
    public boolean isCave(Snake s) throws NotEnoughSnakeException{
        for(int i=0; i<SnakeCave.CAVE_NUMBER; i++) { 
            if(s.bodies[0].x==snakeCaves[i].x && s.bodies[0].y==snakeCaves[i].y
                && s.bodies[0].show) {
                    return true;
                }
        }
        return false;
    }
    public boolean getPoint(Snake s) throws NotEnoughSnakeException {
        if(points!=null) {
            for(int i=0; i<numberOfPoint; i++) {
                try{
                    if(s.bodies[0].x==points[i].x && s.bodies[0].y==points[i].y
                        && s.bodies[0].show) {
                        points[i] = null;
                        return true;
                    }
                }catch(NullPointerException e) {

                }
            }
        }
        return false;
    }
    //speed control
    public void speedControl() {
    	if(p1.numberOfPoint>=20 || p2.numberOfPoint>=20)
    		Snake.speed = 300;
    	else if(p1.numberOfPoint>=10 || p2.numberOfPoint>=10)
    		Snake.speed = 200;
    	else
    		Snake.speed = 120;
    }
    //game over control
    public void pointControl() {
    	if(p1.numberOfPoint>=30 || p2.numberOfPoint>=30)
    		Setting.GAMEOVER = true;
    }
    //run
	@Override
	public void run() {
        init();
		int put = 0;
		while(true) {
			/*if(Main.change==1) {
				init();
				Main.change = 0;
			}*/
			if(Setting.window==2) {
				//
				//System.out.println("run");
				try {
					if(!Setting.PAUSE && !Setting.GAMEOVER) moveSnake(duidata.s1, duidata.s2, 0);
					if(!Setting.PAUSE && !Setting.GAMEOVER) moveSnake(duidata.s2, duidata.s1, 1);
				}catch(NotEnoughSnakeException e) {
					Setting.GAMEOVER = true;
				}
				//System.out.println("hi");
			}
			if(Point.eggLeft==0 && !Setting.PAUSE) {
                put++;
            }
            if(put==2*Snake.speed && !Setting.PAUSE) {
                putPoint();
                putPoint();
                Point.eggLeft = 2;
                put = 0;
            }
			try {Thread.sleep(1000/Snake.speed);} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void init() {
    	p1 = new Player(5);
    	p2 = new Player(5, 2);
    	numberOfPoint = 0;
        points = null;
    	move = new int[2];
        //keyboard
    	valid = new boolean[2];
        Point.eggLeft = 2;
        //put wall
        //putWall(Wall.getWalls());
        //put caves
        //putCave(SnakeCave.getSnakeCaves());
        //put egg
        putPoint();
        putPoint();
        //level
        Snake.speed = 120;
    }
}