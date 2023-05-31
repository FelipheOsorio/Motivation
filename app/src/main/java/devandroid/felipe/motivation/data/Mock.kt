package devandroid.felipe.motivation.data

import devandroid.felipe.motivation.infrastructure.MotivationConstants
import kotlin.random.Random

data class Phrase(val description: String, val categoryId: Int)

class Mock {

    private val all = MotivationConstants.Category.ALL
    private val emoji = MotivationConstants.Category.EMOJI
    private val sun = MotivationConstants.Category.SUN

    private val mListPhrase: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", emoji),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", emoji),
        Phrase("Quando está mais escuro, vemos mais estrelas!", emoji),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", emoji),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", emoji),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", emoji),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sun),
        Phrase("Você perde todas as chances que você não aproveita.", sun),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sun),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sun),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sun),
        Phrase("Se você acredita, faz toda a diferença.", sun),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sun)
    )


    // Obtém frase aleatória de acordo com o filtro
    fun getPhrase(value: Int): String {
        val filtered = mListPhrase.filter { (it.categoryId == value || value == all) }

        // Número aleatório de 0 ao tamanho da lista retornada do filtro
        val rand = Random.nextInt(filtered.size)


        // Retorna string
        return filtered[rand].description
    }

}