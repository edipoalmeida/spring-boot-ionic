package com.ealmeida.springbootionic.cursomc;

import com.ealmeida.springbootionic.cursomc.domain.*;
import com.ealmeida.springbootionic.cursomc.domain.enums.EstadoPagamento;
import com.ealmeida.springbootionic.cursomc.domain.enums.TipoCliente;
import com.ealmeida.springbootionic.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Categoria c3 = new Categoria(null, "Cama mesa e banho");
		Categoria c4 = new Categoria(null, "Eletrônicos");
		Categoria c5 = new Categoria(null, "Jardinagem");
		Categoria c6 = new Categoria(null, "Decoração");
		Categoria c7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().add(p2);

		p1.getCategorias().add(c1);
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().add(c1);

		categoriaRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", e1);
		Cidade cid2 = new Cidade(null, "São Paulo", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);

		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco end1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "38220834", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		Pedido ped1 = new Pedido(null, LocalDateTime.of(2017, Month.SEPTEMBER, 30, 10, 32), cli1, end1);
		Pedido ped2 = new Pedido(null, LocalDateTime.of(2017, Month.OCTOBER, 10, 19, 35), cli1, end2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,  ped2, LocalDateTime.of(2017, Month.OCTOBER, 20, 00,00), null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, BigDecimal.valueOf(2000.0));
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, BigDecimal.valueOf(80.0));
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, BigDecimal.valueOf(800.0));

		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().add(ip3);

		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);

		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

	}
}
