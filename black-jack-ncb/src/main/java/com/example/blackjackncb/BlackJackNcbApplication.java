package com.example.blackjackncb;

import com.example.blackjackncb.Card.CardDeck;
import com.example.blackjackncb.Card.CardService;
import com.example.blackjackncb.Person.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class BlackJackNcbApplication {


	@Autowired
	CardService cardService;


	public static void main(String[] args) {

		SpringApplication.run(BlackJackNcbApplication.class, args);


	}

	@PostConstruct
	public void init(){
		ArrayList<Player> players = cardService.initializePlayer();
		Player player = players.get(0);
		Player dealer = players.get(1);

		//Ask if Ace is 11
		String response = cardService.AceIsElevenForDealerPrompt();
		cardService.SetAceIsEleven(response);

		while ((dealer.getCurrentCardCount() <= 21) ) {
			cardService.dealCardToPlayer(player);
			cardService.dealCardToDealer(dealer);
		}




	}






}
