package AnalizadorL;

import java.util.Stack;
import java.util.Vector;

public class Semantico
{
	boolean bans=false;
	String comp,token,dato,error="";
	Stack tc = new Stack();
	
	public void ASemantico(String token,String comp) {
		//System.out.println(tc);
		//System.out.println(comp);
		if(comp.equals("td"))
		{
			//System.out.println("Ejecuto 1");
			dato=token;
			bans=true;
		}
		else if(comp.equals(";"))
		{
			bans=false;
		}
		else if(comp.equals("id"))
			if(tc.search(token)==-1 && bans)
			{
				//System.out.println("Declarar"+token);
				tc.push(token);
				tc.push(dato);
			}
			else if(tc.search(token)!=-1 && bans)
			{
				//System.out.println("Declarar"+token);
				error="El identificador "+token+" ya esta declarado";
			}
			else if(tc.search(token)==-1)
				error="El identificador "+token+" no esta declarado";
		else if(comp.equals(","))
			bans=true;
		else
		{
			//System.out.println("Ejecuto 3 "+token+" "+comp);
			bans=false;
			dato="";
		}	
	}
}
