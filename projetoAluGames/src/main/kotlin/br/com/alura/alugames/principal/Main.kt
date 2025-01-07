package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoAPI
import transformarEmIdade
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro deu bom! Dados do gamer:")
    println(gamer)
    println("Idade do gamer: ${gamer.dataNascimento?.transformarEmIdade()}")

    do {
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
            println("Jogo inexistente. Tente outro id.")
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
            gamer.jogosBuscados.add(meuJogo)
        }


        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()


    } while (resposta.equals("s", true))

      println("Jogos buscados:")
      println(gamer.jogosBuscados)

      println("\nJogos ordenados por títulos:")
      gamer.jogosBuscados.sortBy {
          it?.titulo

      }

      gamer.jogosBuscados.forEach {
          println("Título: " + it?.titulo)
      }

      val jogosFiltrados = gamer.jogosBuscados.filter {
          it?.titulo?.contains("Batman", true) ?: false

      }
      println("\nJogos filtrados:")
      println(jogosFiltrados)
        println("Deseja excluir algum jogo da lista original? S/N")
        val opcoes = leitura.nextLine()
        if (opcoes.equals("s", true)){
            println(gamer.jogosBuscados)
            println("Informe a posição do jogo que quer excluir?:")
            val posicao = leitura.nextInt()
            gamer.jogosBuscados.removeAt(posicao)
        }
        println("\n Lista atualizada")
        println(gamer.jogosBuscados)


      println("Busca finalizada com sucesso.")


}
