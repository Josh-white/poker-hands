package workshop.pokerhands

import org.springframework.stereotype.Service

@Service
class PokerServiceImp: PokerService  {
    override fun getWinner(hands: Hands): String {
        return "dummy string"
    }
}