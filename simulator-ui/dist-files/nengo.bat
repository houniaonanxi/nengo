@echo off
java -Xms100m -Xmx800m -cp .;nengo-BUILDNUMBER.jar;lib/Blas.jar;lib/colt.jar;lib/commons-collections-3.2.jar;lib/formsrt.jar;lib/Jama-1.0.2.jar;lib/jcommon-1.0.0.jar;lib/jfreechart-1.0.1.jar;lib/jmatio.jar;lib/jung-1.7.6.jar;lib/jython.jar;lib/log4j-1.2.16.jar;piccolo.jar;lib/piccolox.jar;lib/qdox-1.6.3.jar;lib/ssj.jar;lib/jgrapht-jdk1.5-0.7.3.jar ca.nengo.ui.NengoLauncher
