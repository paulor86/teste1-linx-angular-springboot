package com.linx.teste.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.linx.teste.entities.Categoria;
import com.linx.teste.entities.ItemPedido;
import com.linx.teste.entities.Pagamento;
import com.linx.teste.entities.Pedido;
import com.linx.teste.entities.Produto;
import com.linx.teste.entities.Usuario;
import com.linx.teste.entities.enums.StatusPedido;
import com.linx.teste.repositories.CategoriaRepository;
import com.linx.teste.repositories.ItemPedidoRepository;
import com.linx.teste.repositories.PedidoRepository;
import com.linx.teste.repositories.ProdutoRepository;
import com.linx.teste.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TesteAplicacao implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		//-------------- Injeção de Dependência na Categoria e Produto --------------------
		
		Categoria categoria1 = new Categoria(null, "Eletronicos");
		Categoria categoria2 = new Categoria(null, "Books");
		Categoria categoria3 = new Categoria(null, "Computadores");
		
		Produto produto1 = new Produto(null, "The Lord of the Rings", "O Senhor dos Anéis é um livro de alta fantasia.", 90.5, null, "");
		Produto produto2 = new Produto(null, "Smart TV", "40 Polegadas com Youtube, Netflix, Globo Play.", 2190.0, null, "");
		Produto produto3 = new Produto(null, "Macbook Pro", "Notebooks mais potentes da Apple, com processadores rápidos.", 1250.0, null, "");
		Produto produto4 = new Produto(null, "PC Gamer", "Monitor LED Intel Core i5 8GB HD 500GB.", 1200.0, null, "");
		Produto produto5 = new Produto(null, "Harry Potter", "Narra as aventuras de um jovem chamado Harry James Potter.", 100.99, null, "");

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
						
		//-------------- Injeção de Dependência do Produto na Categoria --------------------
		
		produto1.getCategorias().add(categoria2);
		produto2.getCategorias().add(categoria1);
		produto2.getCategorias().add(categoria3);
		produto3.getCategorias().add(categoria3);
		produto4.getCategorias().add(categoria3);
		produto5.getCategorias().add(categoria2);
		
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));
		
		//-------------- Injeção de Dependência nos usuários e Pedido --------------------
		
		Usuario usuario1 = new Usuario(null, "Paulo Roberto", "Cascavel", "92994073579", "paulo@gamil.com", "123456");
		Usuario usuario2 = new Usuario(null, "Paulo Bitencourt", "Cascavel", "92992204996", "paulo@gamil.com", "123456");

		Pedido pedido1 = new Pedido(null, Instant.parse("2020-09-30T19:53:07Z"), StatusPedido.PAGAMENTO_EFETUADO, usuario1);
		Pedido pedido2 = new Pedido(null, Instant.parse("2020-09-30T03:42:10Z"), StatusPedido.AGUARDANDO_PAGAMENTO, usuario2);
		Pedido pedido3 = new Pedido(null, Instant.parse("2020-09-30T15:21:22Z"), StatusPedido.AGUARDANDO_PAGAMENTO, usuario1);

		usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
		
		//-------------- Injeção de Dependência nos Items do Pedido -----------------------
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 2, produto1.getPrecoVenda());
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 1, produto3.getPrecoVenda());
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto3, 2, produto3.getPrecoVenda());
		ItemPedido itemPedido4 = new ItemPedido(pedido3, produto5, 2, produto5.getPrecoVenda());
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3, itemPedido4));
		
		//-------------- Injeção de Dependência no Pagamento -----------------------------
		
		Pagamento pagamento1 = new Pagamento(null, Instant.parse("2020-09-30T21:53:07Z"), pedido1);
		pedido1.setPagamento(pagamento1);
		
		pedidoRepository.saveAll(Arrays.asList(pedido1));
	}
}
