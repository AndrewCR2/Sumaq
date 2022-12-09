# Sumaq

Proyecto académico que se basa en un negocio que busca revalorizar productos originarios del Perú los cuales son consumidos desde la época de los Incas por su alto valor nutricional y por sus beneficios a las distintas afecciones que sufre el ser humano, Sumaq ofrece estos productos elaborados naturales contribuyendo aún más a la salud y bienestar de nuestros clientes.

![web](assets/web.png)


```java
//Categoria
@OneToMany(mappedBy = "categoria")
private List<Productos> productos = ArrayList<>();

//Productos
// lazy - hace que no se haga el get no se carga de la base de datos

@ManyToOne(fetch = FechType.Lazy) 
@JoinColum(name = "idCategoria")
private Categoria categoria;
```

