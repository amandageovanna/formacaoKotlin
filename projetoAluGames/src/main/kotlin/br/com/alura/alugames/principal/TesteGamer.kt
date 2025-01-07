package br.com.alura.alugames.principal
import br.com.alura.alugames.modelo.Gamer
fun main() {
    val gamer1 = Gamer("amandageovanna", "amanda@email.com")
    println(gamer1)

    val gamer2 = Gamer(
        "enzo",
        "ezpmartins@email.com",
        "06/09/2003",
        "ezpmartins")

    println(gamer2)

    gamer1.let {
        it.dataNascimento = "12/08/2001"
        it.usuario = "amandageovanna"

    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "amanda"
    println(gamer1)
}