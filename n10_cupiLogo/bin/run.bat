@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n10_cupiLogo
REM Autor: Equipo Cupi2 2016
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion del programa
REM ---------------------------------------------------------

cd..
java -ea -classpath ./lib/cupiLogo.jar uniandes.cupi2.cupiLogo.interfaz.InterfazCupiLogo
cd bin