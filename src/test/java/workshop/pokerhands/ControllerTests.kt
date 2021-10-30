package workshop.pokerhands

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@WebMvcTest(CoolController::class)
class ControllerTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: com.fasterxml.jackson.databind.ObjectMapper

    private var mockPokerService: PokerService = mockk()

    @Test
    fun `pokerHands returns draw`() {

        val firstHand = listOf(
            Card(faceValue=FaceValue.One, suit=Suit.Spade),
            Card(faceValue=FaceValue.Two, suit=Suit.Spade),
            Card(faceValue=FaceValue.Three, suit=Suit.Spade),
            Card(faceValue=FaceValue.Four, suit=Suit.Spade),
            Card(faceValue=FaceValue.Five, suit=Suit.Spade)
        )

        val secondHand = listOf(
            Card(faceValue=FaceValue.Six, suit=Suit.Spade),
            Card(faceValue=FaceValue.Seven, suit=Suit.Spade),
            Card(faceValue=FaceValue.Eight, suit=Suit.Spade),
            Card(faceValue=FaceValue.Nine, suit=Suit.Spade),
            Card(faceValue=FaceValue.Ten, suit=Suit.Spade)
        )

        val hands = Hands(firstHand, secondHand)

        every {mockPokerService.getWinner(hands)} returns "Draw"

        val request = MockMvcRequestBuilders.post("/pokerHands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(hands))
            .accept(MediaType.APPLICATION_JSON)

        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(content().string("Draw"))
    }

    @Test
    fun `pokerHands returns High card wins`() {
        val request = MockMvcRequestBuilders.post("/pokerHands")
            .contentType(MediaType.APPLICATION_JSON)
            .content("")
        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(content().string("first hand wins"))
    }
}