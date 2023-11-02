Esta demo permite utilizar la BBDD asociada al pool de conexiones "jdbc/stwH2", 
que debe haber sido previamente definido en el container (Payara en nuestro 
caso) que vayamos a utilizar.
Se considera que la tabla "Cliente" ya ha sido previamente creada en la BBDD, 
y coincide con la estructura presente en la clase "Cliente.java". En este 
sentido, lo más cómodo será ejecutar en primer lugar la demo "stw_DB01_conJPA",
que se encarga de crear la clases de forma automática, y eso seguramtne nos 
evitará más de un problema.

El IU de esta demo sugiere que va a ocuparse de realizar las operaciones CRUD 
sobre la tabla "Clientes", pero en realidad solo llega a mostrar el contenido
de dicha tabla (lo que sería un "SELECT *"). El resto de operaciones (C, U y D)
quedan sin implementar.
Lo que sí se hace es establecer la "fontanería" necesaria para poder hacer uso 
de la BBDD a partir del nombre JNDI que referencia el pool de conexiones al que
está asociada.
