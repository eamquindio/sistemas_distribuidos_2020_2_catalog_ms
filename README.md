# sistemas_distribuidos_2020_2_catalog_ms
verificar el archivo aplication.properties y verificar o modificar los datos
para la coneccion ala base de datos root, password, nombre de base de datos

crear base de datos en postgreslq y despues darle clic y en restore se abre una ventana darle clic 
en los tres puntos donde dice Filename, se abre una nueva ventana en la parte de abajo donde dice format
seleccionamos allfiles y buscamos el archivo BD_API2 lo seleccionamos y por ultimo damos clic en restore

para ejecutar redis ejecutar los siguietes comandos en un cmd
primero 
docker pull redis
despues 
docker run -p 6379:6379 --rm --name redis redis

y por ultimo para conectarnos abrimos otro cmd y ejecutamos
docker run -it --link redis:redis --rm redis redis-cli -h redis -p 6379

nota: los datos tienen una duracion de 60 segundos en redis despues 
de este tiempo se borran automaticamente



para usar el api

para consultar todos los datos de category
get http://localhost:8080/categories

para consultar por by-name en category en la api
get http://localhost:8080/categories/by-name?name={nombre}

para consultar por id de category en la api
get http://localhost:8080/categories/{id}


para crear datos en category en la api
post http://localhost:8080/categories
y le manda por body los siguientes datos
nota: en la base de datos BD_API2 ya hay datos agregar con id mayo a 9000
{
        "id": 2,
        "name": " granos"
}

para actualizar los datos en category en la api
put http://localhost:8080/categories/{id}

y se le mandan los datos por el body en json
{
        "id": 2,
        "name": " graneros"
}


para borrar un dato en category en la api
delete http://localhost:8080/categories/{id}
