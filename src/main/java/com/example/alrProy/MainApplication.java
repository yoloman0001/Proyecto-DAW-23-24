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
					new Categoria(4L, "Otros"));
			productoService.anhadir(
					new Producto(1L, "Manzana reineta", 3.8, "normal", "invierno", false, 50, categoria1));
			productoService.anhadir(
					new Producto(2L, "Manzana golden", 4.0, "normal", "invierno", false, 40, categoria1));
			productoService.anhadir(
					new Producto(3L, "Pera", 1.8, "reducido", "verano", false, 20, categoria1));
			productoService.anhadir(
					new Producto(4L, "Pimiento rojo", 1.5, "normal", "otoño", false, 30, categoria2));
			productoService.anhadir(
					new Producto(5L, "Lechuga iceberg", 2.0, "normal", "invierno", false, 50, categoria2));
			productoService.anhadir(
					new Producto(6L, "Ajo", 2.3, "normal", "verano", false, 30, categoria2));
			productoService.anhadir(
					new Producto(0L, "Lentejas", 4.0, "reducido", "otoño", false, 30, categoria3));
			productoService.anhadir(
					new Producto(0L, "Garbanzos", 4.0, "reducido", "primavera", false, 30, categoria3));
			productoService.anhadir(
					new Producto(0L, "Guisante", 2.0, "reducido", "primavera", false, 60, categoria3));
			productoService.anhadir(
					new Producto(0L, "Miel", 5.7, "normal", "otoño", true, 20, categoria4));
			productoService.anhadir(
					new Producto(0L, "Aceite de girasol", 3.0, "normal", "verano", false, 30, categoria4));
			productoService.anhadir(
					new Producto(0L, "Manzana Pink Lady", 4.4, "normal", "invierno", true, 50, categoria1));
			usuarioService.anhadir(
					new Usuario(1L, "Pepe", "pepe@gmail.com", "Mato Grande 101", "qwerty", Rol.ADMIN));
		};
	}
}
