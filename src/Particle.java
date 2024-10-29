
public class Particle {
boolean position[];
int velocity;
boolean Pbest[];

public Particle(int size,int velocity) {
	initPos(size);
	this.velocity=velocity;
	Pbest=position;
}

public boolean[] getPosition() {
	return position;
}

public void setPosition(boolean[] position) {
	this.position = position;
}

public int getVelocity() {
	return velocity;
}

public void setVelocity(int velocity) {
	this.velocity = velocity;
}

public boolean[] getPbest() {
	return Pbest;
}
public void updatPos(int i,boolean bool) {
	this.position[i]=bool;
}
public void setPbest(boolean[] pbest) {
	Pbest = pbest;
}
public void initPos(int size) {
	boolean tab[]=new boolean[size];
	Interval interval=new Interval(10,20);
	for(int i=0;i<size;i++) {
		if(interval.getRandom()<(interval.getStart()+interval.end)/2)
			tab[i]=true;
		else
			tab[i]=false;
	}
	position=tab;
}
// move randomly too randomly
/*
public void move(Interval inter) {
	int i=0;
	while(i<this.velocity) {
		this.updatPos((int)inter.getRandom(),!position[(int)inter.getRandom()]);
		i++;
	}
}*/

// another move
public void move(Interval inter) {
	int i=0;
	int rand;
	while(i<this.velocity) {
		rand=(int)inter.getRandom();
		this.updatPos(rand,!position[rand]);
		i++;
	}
}
}
