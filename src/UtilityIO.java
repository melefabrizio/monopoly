import java.util.*;


public class UtilityIO 
{
final static char SIMBOLO_CORNICE='*';
final static String ERRORE_INPUT_STRINGA="[ERRORE] Stringa NON VALIDA";
final static String ERRORE_INTERVALLO="[ERRORE] Valore non incluso nell'intervallo";
final static String ERRORE_INT="[ERRORE] Valore non e' un intero";
final static String ERRORE_DOUBLE="[ERRORE] Valore non e' un double";
final static String ERRORE_CHAR="[ERRORE] Carattere non valido";
final static String ERRORE_SESSO="[ERRORE] Carattere NON CORRISPONDE ad un SESSO";

	public static void header(String titolo)
	{
	int lung=titolo.length();
	
	
		for(int i=0;i<lung+4;i++)
			System.out.print(SIMBOLO_CORNICE);
		
		System.out.printf("\n"+SIMBOLO_CORNICE+" %s "+SIMBOLO_CORNICE+"\n",titolo);
		
		for(int i=0;i<lung+4;i++)
			System.out.print(SIMBOLO_CORNICE);
		
		System.out.println("\n");
	}
	
	public static void header(String titolo, char simboloCorniceSpecifico)
	{
	int lung=titolo.length();
	
	
		for(int i=0;i<lung+4;i++)
			System.out.print(simboloCorniceSpecifico);
		
		System.out.printf("\n"+simboloCorniceSpecifico+" %s "+simboloCorniceSpecifico+"\n",titolo);
		
		for(int i=0;i<lung+4;i++)
			System.out.print(simboloCorniceSpecifico);
		
		System.out.println("\n");
	}
	
	public static String headerString(String titolo)
	{
	int lung=titolo.length();
	StringBuffer output=new StringBuffer();
	
		for(int i=0;i<lung+4;i++)
			output.append(SIMBOLO_CORNICE);
		
		output.append("\n"+SIMBOLO_CORNICE+" "+titolo+" "+SIMBOLO_CORNICE+"\n");
		
		for(int i=0;i<lung+4;i++)
			output.append(SIMBOLO_CORNICE);
		
		return output.toString();
	}
	
	private static Scanner creaScanner ()
	{
		Scanner creato = new Scanner(System.in);
		creato.useDelimiter(System.getProperty("line.separator"));
		return creato;
	}
	
	public static String leggiStringa(String messaggioUtente)
	{
	Scanner stdin=creaScanner();
	
	String bufferIn;	
	boolean errore=false;
	
		do
		{
			errore=false;
			
			System.out.printf(">> %s\n",messaggioUtente);
			bufferIn=stdin.nextLine();
			
			if(bufferIn.length()==0)
			{
				System.out.printf("%s\n",ERRORE_INPUT_STRINGA);
				errore=true;
			}
			else if((bufferIn.trim()).length()==0)
			{
				System.out.printf("%s\n",ERRORE_INPUT_STRINGA);
				errore=true;
			}
			
		}while(errore);
		
		return bufferIn; 	
	}
	
	public static int leggiInt(String messaggioUtente)
	{
	boolean finito = false;
	int valoreLetto = 0;
	Scanner stdin= creaScanner();
	
		do
		{
			System.out.printf(">> %s\n",messaggioUtente);
 
			try
			{
				valoreLetto = stdin.nextInt();
				finito = true;
			}
			catch (InputMismatchException e)
			{
				System.out.println(ERRORE_INT);
				String daButtare = stdin.next();
			}
		} while (!finito);
   
		return valoreLetto;

	}
	
	public static int leggiInt(String messaggioUtente, int extrInf, int extrSup)
	{
	Scanner stdin= creaScanner();
	int valoreLetto = 0;
	boolean errore=false;
		
			do
			{
				errore=false;
				
				System.out.printf(">> %s [Min: %d, Max: %d]\n",messaggioUtente,extrInf,extrSup);
				
				try
				{
					valoreLetto=stdin.nextInt();
				
					if(valoreLetto<extrInf || valoreLetto>extrSup)
					{
						System.out.printf("%s [Min: %d,Max: %d]\n",ERRORE_INTERVALLO,extrInf,extrSup);
						errore=true;
					}
				}
				catch (InputMismatchException e)
				{
					System.out.printf("%s\n",ERRORE_INT);
					String trash=stdin.next();
					errore=true;
				}
				
			}while(errore);
			
			return valoreLetto; 	
	}
	
	public static int leggiIntMin(String messaggioUtente, int extrInf)
	{
	Scanner stdin= creaScanner();
	int valoreLetto = 0;
	boolean errore=false;
		
			do
			{
				errore=false;
				
				System.out.printf(">> %s [Min: %d]\n",messaggioUtente,extrInf);
				
				try
				{
					valoreLetto=stdin.nextInt();
				
					if(valoreLetto<extrInf)
					{
						System.out.printf("%s [Min: %d]\n",ERRORE_INTERVALLO,extrInf);
						errore=true;
					}
				}
				catch (InputMismatchException e)
				{
					System.out.printf("%s\n",ERRORE_INT);
					String trash=stdin.next();
					errore=true;
				}
				
			}while(errore);
			
			return valoreLetto; 	
	}
	
	public static int leggiIntMax(String messaggioUtente, int extrSup)
	{
	Scanner stdin= creaScanner();
	int valoreLetto = 0;
	boolean errore=false;
		
			do
			{
				errore=false;
				
				System.out.printf(">> %s [Max: %d]\n",messaggioUtente,extrSup);
				
				try
				{
					valoreLetto=stdin.nextInt();
				
					if(valoreLetto>extrSup)
					{
						System.out.printf("%s [Max: %d]\n",ERRORE_INTERVALLO,extrSup);
						errore=true;
					}
				}
				catch (InputMismatchException e)
				{
					System.out.printf("%s\n",ERRORE_INT);
					String trash=stdin.next();
					errore=true;
				}
				
			}while(errore);
			
			return valoreLetto; 	
	}
	
	public static double leggiDouble(String messaggioUtente)
	{
	boolean finito = false;
	double valoreLetto = 0;
	Scanner stdin= creaScanner();
	
		do
		{
			System.out.printf(">> %s\n",messaggioUtente);
 
			try
			{
				valoreLetto = stdin.nextDouble();
				finito = true;
			}
			catch (InputMismatchException e)
			{
				System.out.println(ERRORE_DOUBLE);
				String daButtare = stdin.next();
			}
		} while (!finito);
   
		return valoreLetto;

	}
	
	public static double leggiDouble(String messaggioUtente, double extrInf, double extrSup)
	{
	Scanner stdin= creaScanner();
	double valoreLetto = 0;
	boolean errore=false;
		
			do
			{
				errore=false;
				
				System.out.printf(">> %s [Min: %.3f, Max: %.3f]\n",messaggioUtente,extrInf,extrSup);
				
				try
				{
					valoreLetto=stdin.nextDouble();
				
					if(valoreLetto<extrInf || valoreLetto>extrSup)
					{
						System.out.printf("%s [Min: %.3f,Max: %.3f]\n",ERRORE_INTERVALLO,extrInf,extrSup);
						errore=true;
					}
				}
				catch (InputMismatchException e)
				{
					System.out.printf("%s\n",ERRORE_DOUBLE);
					String trash=stdin.next();
					errore=true;
				}
				
			}while(errore);
			
			return valoreLetto; 	
	}
	
	public static double leggiDoubleMin(String messaggioUtente, double extrInf)
	{
	Scanner stdin= creaScanner();
	double valoreLetto = 0;
	boolean errore=false;
		
			do
			{
				errore=false;
				
				System.out.printf(">> %s [Min: %.3f]\n",messaggioUtente,extrInf);
				
				try
				{
					valoreLetto=stdin.nextDouble();
				
					if(valoreLetto<extrInf)
					{
						System.out.printf("%s [Min: %.3f]\n",ERRORE_INTERVALLO,extrInf);
						errore=true;
					}
				}
				catch (InputMismatchException e)
				{
					System.out.printf("%s\n",ERRORE_DOUBLE);
					String trash=stdin.next();
					errore=true;
				}
				
			}while(errore);
			
			return valoreLetto; 	
	}
	
	public static double leggiDoubleMax(String messaggioUtente, double extrSup)
	{
	Scanner stdin= creaScanner();
	double valoreLetto = 0;
	boolean errore=false;
		
			do
			{
				errore=false;
				
				System.out.printf(">> %s [Max: %.3f]\n",messaggioUtente,extrSup);
				
				try
				{
					valoreLetto=stdin.nextDouble();
				
					if(valoreLetto>extrSup)
					{
						System.out.printf("%s [Max: %.3f]\n",ERRORE_INTERVALLO,extrSup);
						errore=true;
					}
				}
				catch (InputMismatchException e)
				{
					System.out.printf("%s\n",ERRORE_DOUBLE);
					String trash=stdin.next();
					errore=true;
				}
				
			}while(errore);
			
			return valoreLetto; 	
	}
	
	public static char leggiChar(String messaggioUtente)
	{
	Scanner stdin= creaScanner();
	
	String bufferIn;	
	boolean errore=false;
	
		do
		{
			errore=false;
			
			System.out.printf(">> %s\n",messaggioUtente);
			bufferIn=stdin.nextLine();
			
			if(bufferIn.length()==0)
			{
				System.out.printf("%s\n",ERRORE_CHAR);
				errore=true;
			}
			else if((bufferIn.trim()).length()==0)
			{
				System.out.printf("%s\n",ERRORE_CHAR);
				errore=true;
			}
			else if(bufferIn.length()>1)
			{
				System.out.printf("%s\n",ERRORE_CHAR);
				errore=true;
			}
			
		}while(errore);
		
		return bufferIn.charAt(0); 		
	}
	
	public static char leggiCharIntervallo(String messaggioUtente, String caratteriAmmissibili)
	{
	char bufferIn;	
	boolean errore=false;	
	
		do
		{
			errore=false;
			bufferIn=UtilityIO.leggiChar(messaggioUtente);
			
			if(caratteriAmmissibili.indexOf(bufferIn) == -1)
			{
				System.out.printf("%s: {",ERRORE_INTERVALLO);
				stampaStringaConSeparatore(',',caratteriAmmissibili);
				System.out.printf("}\n\n");
				errore=true;
			}
			
		}while(errore);
		
		return bufferIn; 
		
	}
	
	public static void stampaStringaConSeparatore(char separatore, String daStampare)
	{
		/*Stampa "daStampare" con il carattere "separatore" tra ogni carattere di "daStampare" */
		
		for(int i=0;i<daStampare.length();i++)
		{
			if(i!=0)
				System.out.printf("%c%s",separatore,daStampare.charAt(i));
			else
				System.out.printf("%s",daStampare.charAt(i));
		}
		
	}
	
	public static char leggiSesso(String messaggioUtente)
	{
	final String caratteriAmmissibili="mMfF";
	
	char bufferIn;	
	boolean errore=false;
	
		do
		{
			errore=false;
			bufferIn=UtilityIO.leggiCharIntervallo(messaggioUtente,caratteriAmmissibili);
			
		}while(errore);
		
		return bufferIn; 		
	}
	
	public static boolean yesOrNo(String messaggio)
	{
		String mioMessaggio = messaggio + " (s/S=si; n/N=no)";
		char valoreLetto = UtilityIO.leggiChar(mioMessaggio);
		valoreLetto=Character.toUpperCase(valoreLetto);
		
		if (valoreLetto == 'S')
			return true;
		else
			return false;
	}
	
	public static void msgErrore(String message)
	{
		System.out.printf("%s...\t[ERRORE]\n",message);
	}
  
	public static void msgSuccesso(String message)
	{
		System.out.printf("%s...\t[OK]\n",message);
	}
}
	