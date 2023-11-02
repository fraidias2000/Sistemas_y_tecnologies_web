Esta demo clierra la trilogía de la utilización de una BBDD asociada al 
pool de conexiones "jdbc/stwH2", que debe haber sido previamente definido 
en el container (Payara en nuestro caso) que vayamos a utilizar.
Considera 3 tablas "Cliente", "Producto" y "Pedido".
El IU permite crear clientes y productos. Posteriormente permite que un
cliente compre productos (que haga pedidos).
Cuando se elimina un cliente, se eliminan automáticamente (en cascada) los
pedidos que tuviera registrados. Sin embargo los productos permanecen.
