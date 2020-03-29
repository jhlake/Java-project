package uniandes.cupi2.cupiZoologico.mundo;

public class Animal {
	//Constantes
	public final static String HERBIVORO = "Herb�voro";
	public final static String CARNIVORO = "Carn�voro";
	public final static String ACUATICO = "Acu�tico";
	public final static String TERRESTRE = "Terrestre";
	
	//Atributos
	private String nombre;
	private String especie;
	private String tipo;
	private String habitat;
	private String imagen;
	
	//Constructor
	public Animal(String nNombre, String nEspecie, String nTipo, String nHabitat, String nImagen)
	{
		nombre = nNombre;
		especie = nEspecie;
		tipo = nTipo;
		habitat = nHabitat;
		imagen = nImagen;
	}
	
	//M�todos
	public String darNombre()
	{
		return nombre;
	}
	public String darEspecie()
	{
		return especie;
	}
	public String darTipo()
	{
		return tipo;
	}
	public String darHabitat()
	{
		return habitat;
	}
	public String darImagen()
	{
		return imagen;
	}
	public String toString()
	{
		return nombre + " (" + especie + ")";
	}
}
