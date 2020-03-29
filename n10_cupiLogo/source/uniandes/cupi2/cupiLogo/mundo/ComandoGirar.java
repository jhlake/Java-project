package uniandes.cupi2.cupiLogo.mundo;

import java.awt.Graphics2D;

public class ComandoGirar extends ComandoTransformacion{

	public final static String COMANDO = "girar";
	
	public final static int HORARIO = 0;
	
	public final static int ANTI_HORARIO = 1;
	
	public ComandoGirar(double pValor, int pDireccion) {
		super(pValor, pDireccion);
		// TODO Auto-generated constructor stub
		nombre = COMANDO;
	}

	@Override
	public void ejecutar(Tortuga pTortuga, Graphics2D pG) {
		// TODO Auto-generated method stub
		switch(direccion)
		{
		case HORARIO:
			pTortuga.modificarOrientacion(valor + pTortuga.darOrientacion());
			break;
		case ANTI_HORARIO:
			pTortuga.modificarOrientacion(pTortuga.darOrientacion() - valor);
			break;
		}

	}

}
