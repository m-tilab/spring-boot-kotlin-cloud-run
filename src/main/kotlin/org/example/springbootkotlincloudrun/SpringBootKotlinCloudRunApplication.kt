package org.example.springbootkotlincloudrun

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringBootKotlinCloudRunApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinCloudRunApplication>(*args)
}


@RestController
@RequestMapping("/cards")
class CardController(private val cardService: CardService) {

    @PostMapping
    fun issueNewCard(@RequestBody issueCardRequest: IssueCardRequest): IssueCardResponse {
        val card = Card(issueCardRequest.card, issueCardRequest.description)
        val savedCard = cardService.issueNewCard(card)
        return IssueCardResponse(savedCard.id ?: -1, savedCard.card, savedCard.description)
    }

    @GetMapping
    fun countCards(): Long {
        return cardService.countCard()
    }
}

data class IssueCardRequest(val card: String, val description: String)

data class IssueCardResponse(val id: Long, val card: String, val description: String)

@Service
class CardService(private val repository: CardRepository) {

    fun issueNewCard(card: Card): Card {
        return repository.save(card)
    }

    fun countCard(): Long {
        return repository.count()
    }
}

@Repository
interface CardRepository : JpaRepository<Card, Long>

@Entity
class Card() {
    constructor(card: String, description: String) : this() {
        this.card = card
        this.description = description
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var card: String
    lateinit var description: String
}
