package com.example.alrProy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.alrProy.domain.Categoria;
import com.example.alrProy.domain.Producto;
import com.example.alrProy.domain.Rol;
import com.example.alrProy.domain.Usuario;
import com.example.alrProy.services.CategoriaServiceImpl;
import com.example.alrProy.services.ProductoServiceImpl;
import com.example.alrProy.services.UsuarioServiceImpl;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner initData(ProductoServiceImpl productoService, CategoriaServiceImpl categoriaService,
			UsuarioServiceImpl usuarioService) {
	
		return args -> {
			Categoria categoria1 = categoriaService.anhadir(
					new Categoria(1L, "Frutas"));
			Categoria categoria2 = categoriaService.anhadir(
					new Categoria(2L, "Verduras"));
			Categoria categoria3 = categoriaService.anhadir(
					new Categoria(3L, "Legumbres"));
			Categoria categoria4 = categoriaService.anhadir(
					new Categoria(4L, "Hortalizas"));
			Categoria categoria5 = categoriaService.anhadir(
					new Categoria(5L, "Otros"));
			productoService.anhadir(
					new Producto(0L, "Manzana reineta", 3.8, "normal", "invierno", false, 50, categoria1));
			productoService.anhadir(
					new Producto(0L, "Manzana golden", 4.0, "normal", "invierno", false, 40, categoria1));
			productoService.anhadir(
					new Producto(0L, "Manzana Pink Lady", 4.4, "normal", "invierno", true, 50, categoria1));
			productoService.anhadir(
					new Producto(0L, "Pera conferencia", 1.8, "reducido", "invierno", false, 20, categoria1));
			productoService.anhadir(
					new Producto(0L, "Plátano ecológico", 3.8, "normal", "todo el año", true, 20, categoria1));
			productoService.anhadir(
					new Producto(0L, "Limón", 1.6, "normal", "otoño", false, 30, categoria1));
			productoService.anhadir(
					new Producto(0L, "Pomelo", 2.1, "reducido", "invierno", true, 20, categoria1));
			productoService.anhadir(
					new Producto(0L, "Naranja de mesa", 2.3, "reducido", "invierno", false, 50, categoria1));
			productoService.anhadir(
					new Producto(0L, "Mandarina", 1.9, "normal", "invierno", false, 50, categoria1));
			productoService.anhadir(
					new Producto(0L, "Fresa", 2.2, "normal", "primavera", true, 60, categoria1));
			productoService.anhadir(
					new Producto(0L, "Cereza", 5.8, "normal", "verano", false, 60, categoria1));
			productoService.anhadir(
					new Producto(0L, "Ciruela", 4.9, "normal", "verano", true, 30, categoria1));
			productoService.anhadir(
					new Producto(0L, "Kiwi", 3.8, "normal", "otoño", false, 30, categoria1));
			productoService.anhadir(
					new Producto(0L, "Melocotón", 3.3, "reducido", "verano", false, 20, categoria1));
			productoService.anhadir(
					new Producto(0L, "Higo", 2.0, "reducido", "verano", true, 30, categoria1));
			productoService.anhadir(
					new Producto(0L, "Pimiento rojo", 2.5, "normal", "otoño", false, 40, categoria2));
			productoService.anhadir(
					new Producto(0L, "Pimiento verde", 2.2, "normal", "otoño", false, 40, categoria2));
			productoService.anhadir(
					new Producto(0L, "Pimiento de Padrón", 1.9, "normal", "otoño", true, 40, categoria2));
			productoService.anhadir(
					new Producto(0L, "Calabacín", 2.8, "normal", "todo el año", false, 50, categoria2));
			productoService.anhadir(
					new Producto(0L, "Berenjena", 2.5, "reducido", "todo el año", false, 50, categoria2));
			productoService.anhadir(
					new Producto(0L, "Lechuga iceberg", 1.9, "normal", "invierno", false, 50, categoria2));
			productoService.anhadir(
					new Producto(0L, "Lechuga romana", 1.6, "normal", "invierno", false, 30, categoria2));
			productoService.anhadir(
					new Producto(0L, "Acelga", 1.7, "normal", "invierno", false, 50, categoria2));
			productoService.anhadir(
					new Producto(0L, "Tomate cherry", 2.3, "normal", "verano", false, 50, categoria2));
			productoService.anhadir(
					new Producto(0L, "Cebolla", 2.2, "normal", "todo el año", false, 30, categoria2));
			productoService.anhadir(
					new Producto(0L, "Tomate rama", 1.3, "normal", "verano", false, 30, categoria2));
			productoService.anhadir(
					new Producto(0L, "Lentejas", 4.0, "reducido", "otoño", false, 60, categoria3));
			productoService.anhadir(
					new Producto(0L, "Garbanzos", 4.0, "reducido", "primavera", false, 30, categoria3));
			productoService.anhadir(
					new Producto(0L, "Guisantes", 2.0, "reducido", "primavera", false, 60, categoria3));
			productoService.anhadir(
					new Producto(0L, "Judías verdes", 1.5, "normal", "invierno", false, 60, categoria3));
			productoService.anhadir(
					new Producto(0L, "Alubias blancas", 1.3, "normal", "invierno", false, 50, categoria3));
			productoService.anhadir(
					new Producto(0L, "Alubias rojas", 2.1, "normal", "invierno", false, 60, categoria3));
			productoService.anhadir(
					new Producto(0L, "Cacahuete", 1.9, "normal", "todo el año", true, 60, categoria3));
			productoService.anhadir(
					new Producto(0L, "Frijol común", 1.9, "normal", "verano", false, 50, categoria3));
			productoService.anhadir(
					new Producto(0L, "Zanahoria", 1.0, "reducido", "primavera", true, 40, categoria4));
			productoService.anhadir(
					new Producto(0L, "Remolacha", 1.0, "normal", "primavera", false, 40, categoria4));
			productoService.anhadir(
					new Producto(0L, "Rábano rojo", 1.0, "normal", "primavera", true, 40, categoria4));
			productoService.anhadir(
					new Producto(0L, "Cebolleta", 4.0, "normal", "primavera", true, 40, categoria4));
			productoService.anhadir(
					new Producto(0L, "Ajo", 2.3, "normal", "todo el año", false, 30, categoria4));
			productoService.anhadir(
					new Producto(0L, "Patata", 1.4, "reducido", "primavera", true, 30, categoria4));
			productoService.anhadir(
					new Producto(0L, "Boniato", 2.6, "normal", "primavera", true, 30, categoria4));
			productoService.anhadir(
					new Producto(0L, "Miel", 5.0, "normal", "primavera", true, 20, categoria5));
			productoService.anhadir(
					new Producto(0L, "Pipas de girasol", 1.4, "reducido", "verano", false, 30, categoria5));
			productoService.anhadir(
					new Producto(0L, "Aceite de girasol", 3.0, "normal", "verano", true, 30, categoria5));
			productoService.anhadir(
					new Producto(0L, "Aceite de oliva", 8.9, "normal", "verano", true, 30, categoria5));
			productoService.anhadir(
					new Producto(0L, "Seta de ostra", 3.8, "normal", "otoño", false, 50, categoria5));
			productoService.anhadir(
					new Producto(0L, "Jengibre", 1.7, "normal", "invierno", false, 30, categoria5));
			usuarioService.anhadir(
					new Usuario(1L, "Pepe", "pepe@gmail.com", "Mato Grande 101", "qwerty", Rol.ADMIN));
			usuarioService.anhadir(
					new Usuario(2L, "test", "test@gmail.com", "Calle Real 1", "12345¡", Rol.USUARIO));
		};
	}*/
}
