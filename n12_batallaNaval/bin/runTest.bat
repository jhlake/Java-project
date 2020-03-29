@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n12_batallaNaval
REM Autor: Mario Sánchez - 21-feb-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd..
java -classpath ./lib/derby.jar;./lib/batallaNaval.jar;./test/lib/batallaNavalTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.batallaNaval.testServidor.ServidorBatallaNavalTest
java -classpath ./lib/derby.jar;./lib/batallaNaval.jar;./test/lib/batallaNavalTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.batallaNaval.testServidor.AdministradorResultadosTest
java -classpath ./lib/derby.jar;./lib/batallaNaval.jar;./test/lib/batallaNavalTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.batallaNaval.testServidor.EncuentroTest
java -classpath ./lib/derby.jar;./lib/batallaNaval.jar;./test/lib/batallaNavalTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.batallaNaval.testCliente.BarcoTest
java -classpath ./lib/derby.jar;./lib/batallaNaval.jar;./test/lib/batallaNavalTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.batallaNaval.testCliente.BatallaNavalTest
java -classpath ./lib/derby.jar;./lib/batallaNaval.jar;./test/lib/batallaNavalTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.batallaNaval.testCliente.CasillaTest
java -classpath ./lib/derby.jar;./lib/batallaNaval.jar;./test/lib/batallaNavalTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.batallaNaval.testCliente.TablerosJuegoTest
cd bin
