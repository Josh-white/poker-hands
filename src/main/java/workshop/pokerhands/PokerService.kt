package workshop.pokerhands

interface PokerService {
    fun getWinner(hands: Hands):String
}
