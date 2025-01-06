package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoAPI
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    print("Digite um código de jogo para buscar:")
    val busca = leitura.nextLine()
    val buscaApi = ConsumoAPI()
    val informacaoJogo = buscaApi.buscaJogo(busca)

    if (informacaoJogo == null) {
        println("Jogo inexistente. Tente outro ID.")
        return
    }

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(
            informacaoJogo.info.title,
            informacaoJogo.info.thumb
        )
    }


    resultado.onFailure {
        println("br.com.alura.alugames.modelo.Jogo inexistente. Tente outro id.")
    }

    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            println("Insira a descrição personalizada para o jogo:")
            val descricaoPersonalizada = leitura.nextLine();
            meuJogo?.descricao = descricaoPersonalizada
        } else {
         meuJogo?.descricao = meuJogo?.titulo
        }
    }
    println(meuJogo)

    resultado.onSuccess {
        println("Busca finalizada com sucesso.")
    }
}
