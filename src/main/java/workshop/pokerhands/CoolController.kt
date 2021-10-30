package workshop.pokerhands

import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class CoolController {

    @PostMapping("/pokerHands")
    fun pokerHands(@RequestBody hands: Hands, pokerService: PokerService): ResponseEntity<String> {
        val results: String = pokerService.getWinner(hands)
        return ResponseEntity.ok().body(results)
    }
}
