package uniandes.cupi2.cupiLogo.mundo;

import java.awt.Graphics2D;

public class ComandoCentrar extends ComandoSimple {

	public static final String COMANDO = "centrar";

	public ComandoCentrar()
	{
		nombre = COMANDO;
	}
	@Override
	public void ejecutar(Tortuga pTortuga, Graphics2D pG) {

		pTortuga.modificarXActual(pTortuga.darXInicial());
		pTortuga.modificarYActual(pTortuga.darYInicial());

	}

}
