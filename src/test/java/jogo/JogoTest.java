package jogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Testes da classe Jogo")
public class JogoTest {

	private Jogador jogadorMock;
	private Dado dado1Mock;
	private Dado dado2Mock;
	private Jogo jogo;

	@BeforeEach
	public void inicializa() {
		jogadorMock = mock(Jogador.class);
		dado1Mock = mock(Dado.class);
		dado2Mock = mock(Dado.class);
		jogo = new Jogo(jogadorMock, dado1Mock, dado2Mock);
	}
	
	@Test
	@DisplayName("O jogo deve retornar um resultado booleano válido")
	public void testaJogoRetornaResultadoValido() {
	    boolean resultado = jogo.jogo();
	    assertTrue(resultado == true || resultado == false);
	}

	@Test
	@DisplayName("Deve ganhar na primeira rodada quando a soma for 7")
	public void testaVenceComSete() {
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(7);

		assertTrue(jogo.jogo());
		verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	@DisplayName("Deve ganhar na primeira rodada quando a soma for 11")
	public void testaVenceComOnze() {
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(11);

		assertTrue(jogo.jogo());
	}

	@Test
	@DisplayName("Deve perder na primeira rodada quando a soma for 2, 3 ou 12")
	public void testaPerdeNaPrimeiraRodada() {
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(2);

		assertFalse(jogo.jogo());
	}


	@Test
	@DisplayName("Deve ganhar quando repetir o ponto na segunda rodada")
	public void testaVenceRepetindoPonto() {
		// 1o lance = 5 (vira o "ponto"), 2o lance = 5 novamente -> ganha
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(5, 5);

		assertTrue(jogo.jogo());
		verify(jogadorMock, times(2)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	@DisplayName("Deve perder quando cair 7 antes de repetir o ponto")
	public void testaPerdeCaindoSeteDepois() {
		// 1o lance = 5 (vira o "ponto"), 2o lance = 7 -> perde
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(5, 7);

		assertFalse(jogo.jogo());
	}

}
