import java.util.ArrayList;

public class Pso {
int NumberOfParticles;
int NumberOfIterations;
int Vmax;
int lenght;  //the number of variables in the instance 
double c1;
double c2;
double w;
ArrayList<Particle> ParticlesTab;
boolean Gbest[];

public Pso(int number,int iteration,int vmax,double c1,double c2,double w,int l) {
	NumberOfParticles=number;
	NumberOfIterations=iteration;
	Vmax=vmax;
	lenght=l;
	this.c1=c1;
	this.c2=c2;
	ParticlesTab=new ArrayList<Particle>();
}
public void addParticle(Particle p) {
	ParticlesTab.add(p);
}
public int getNumberOfParticles() {
	return NumberOfParticles;
}
public void setNumberOfParticles(int numberOfParticles) {
	NumberOfParticles = numberOfParticles;
}
public int getNumberOfIterations() {
	return NumberOfIterations;
}
public void setNumberOfIterations(int numberOfIterations) {
	NumberOfIterations = numberOfIterations;
}
public int getVmax() {
	return Vmax;
}
public void setVmax(int vmax) {
	Vmax = vmax;
}
public double getC1() {
	return c1;
}
public void setC1(double c1) {
	this.c1 = c1;
}
public double getC2() {
	return c2;
}
public void setC2(double c2) {
	this.c2 = c2;
}
public double getW() {
	return w;
}
public void setW(double w) {
	this.w = w;
}
public ArrayList<Particle> getParticlesTab() {
	return ParticlesTab;
}
public void setParticlesTab(ArrayList<Particle> particlesTab) {
	ParticlesTab = particlesTab;
}
public boolean[] getGbest() {
	return Gbest;
}
public void setGbest(boolean[] gbest) {
	Gbest = gbest;
}
public void Initialize() {
	Particle p;
	Interval intval=new Interval(1,Vmax);
	for(int i=0;i<NumberOfParticles;i++) {
		p=new Particle(lenght,(int)intval.getRandom());
		ParticlesTab.add(p);
	}
}

public double fitnessFonc(Particle p,Instance inst) {
	return inst.evaluate(p);
}
public double fitnessFonc(boolean gbest[],Instance inst) {
	return inst.evaluate(gbest);
}

public int distance(boolean x1[],boolean x2[]) {
	int distance=0;
	for(int i=0;i<x1.length;i++) {
		if(x1[i]!=x2[i])
			distance++;
	}
	return distance;
}
}
