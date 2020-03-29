#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n5_cupiMuseo
# Autor: Vanessa P�rez Romanello - 13-sep-2011
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecuci�n de las pruebas
# ---------------------------------------------------------

cd ../..
	
java -ea -classpath lib/cupiMuseo.jar:test/lib/junit.jar:test/lib/cupiMuseoTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiMuseo.test.CupiMuseoTest
	
java -ea -classpath lib/cupiMuseo.jar:test/lib/junit.jar:test/lib/cupiMuseoTest.jar junit.swingui.TestRunner uniandes.cupi2.cupiMuseo.test.ObraDeArteTest
cd bin/mac

stty echo
