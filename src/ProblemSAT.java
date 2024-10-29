import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProblemSAT {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pso pso;
		Instance instance=new Instance(325 , 75);
		//String nomF="C:\\Users/Dell/Desktop/meta hauristique/uuf75-325/UUF75.325.100/uuf75-0100.cnf";
		String nomF="C:\\Users/Dell/Desktop/meta hauristique/uf75-325/ai/hoos/Shortcuts/UF75.325.100/uf75-01.cnf";
		//tout les clauses des benchmarks uuf75 commences par un espace alors faut ajusté se probleme
		try{
			//input the benchmark 
			InputStream ips=new FileInputStream(nomF); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String mot1="";
			String mot2="";
			String mot3="";
			Literal lit1=null;
			Literal lit2=null;
			Literal lit3=null;
			int i = 0;
			while ((ligne=br.readLine())!=null){
				if(ligne.startsWith("c")||ligne.startsWith("p")||ligne.startsWith("%")||ligne.startsWith("0")){
				}
				else{
					Clause clause=null;
					while((ligne.charAt(i)!=' ')&&(i<ligne.length())){
						mot1=mot1 + ligne.charAt(i);
						i++;
					}
					if(Integer.parseInt(mot1)<0) 
						lit1=new Literal(Integer.parseInt(mot1)*-1, true);
					else 
						lit1=new Literal(Integer.parseInt(mot1), false);
					mot1="";
					i++;
					while((ligne.charAt(i)!=' ')&&(i<ligne.length())){
						mot2=mot2 + ligne.charAt(i);
						i++;
					}
					if(Integer.parseInt(mot2)<0) 
						lit2=new Literal(Integer.parseInt(mot2)*-1, true);
					else 
						lit2=new Literal(Integer.parseInt(mot2), false);
					mot2="";
					i++;
					while((ligne.charAt(i)!=' ')&&(i<ligne.length())){
						mot3=mot3 + ligne.charAt(i);
						i++;
					}
					if(Integer.parseInt(mot3)<0) 
						lit3=new Literal(Integer.parseInt(mot3)*-1, true);
					else 
						lit3=new Literal(Integer.parseInt(mot3), false);
					mot3="";
					i++;
                  clause=new Clause(lit1, lit2, lit3);
                  instance.addClause(clause);
				}
				i=0;
			}
		br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		System.out.println(instance);
		
		//--------------start PSO processing-------------------
		
		pso=new Pso(325, 700, 225, 2, 2, 0.7,instance.Nvar);
		pso.Initialize();
		pso.Gbest=pso.ParticlesTab.get(0).getPbest();
		for(int i=1;i<pso.NumberOfParticles;i++) {
			if(pso.fitnessFonc(pso.ParticlesTab.get(i), instance)>=pso.fitnessFonc(pso.Gbest, instance))
				pso.Gbest=pso.ParticlesTab.get(i).getPosition();
		}
		int v;
		Interval interval=new Interval(0,74);
		double r1,r2;
		for(int it=0;it<pso.getNumberOfIterations();it++) {
			for(int parts=0;parts<pso.getNumberOfParticles();parts++) {
				//updating the velocity
				v=pso.ParticlesTab.get(parts).getVelocity();
				r1=Math.random();
				r2=Math.random();
				pso.ParticlesTab.get(parts).setVelocity((int)(pso.getW()*v+pso.getC1()*r1*pso.distance(pso.ParticlesTab.get(parts).getPbest(), pso.ParticlesTab.get(parts).getPosition())+pso.getC2()*r2*pso.distance(pso.getGbest(), pso.ParticlesTab.get(parts).getPosition())));
			    // move the particle and evaluate it fitness
				pso.ParticlesTab.get(parts).move(interval);
				if(pso.fitnessFonc(pso.ParticlesTab.get(parts).getPosition(), instance)>=pso.fitnessFonc(pso.ParticlesTab.get(parts).getPbest(), instance))
					pso.ParticlesTab.get(parts).setPbest(pso.ParticlesTab.get(parts).getPosition());
			}
			// updating Gbest 
			for(int j=0;j<pso.NumberOfParticles;j++) {
				if(pso.fitnessFonc(pso.ParticlesTab.get(j).getPbest(), instance)>=pso.fitnessFonc(pso.Gbest, instance))
					pso.Gbest=pso.ParticlesTab.get(j).getPbest();
			}
		}
		System.out.print("the pso result is : [");
		for(int s=0;s<pso.getGbest().length;s++) {
			if(pso.Gbest[s]==true)
				System.out.print("1");
			else
				System.out.print("0");
		}
		System.out.println("]");
		System.out.println("the fitness of the solution obtained is : "+ pso.fitnessFonc(pso.Gbest, instance));
		System.out.println("is it the solution ? "+ instance.ifSolution(pso.getGbest()));
	}

}
