package com.example.blackjackncb.Card;

import com.example.blackjackncb.Person.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Service
public class CardService {

    int dealerCount;
    int playerCount;
    boolean isAceElevenForDealer = false;
    int aceValue =0;


    String[] ranks = {"Ace","2","3","4","5","6","7","8","9",
            "Jack", "Queen", "King"};
    Random r=new Random();

    public ArrayList<Player> initializePlayer(){

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player();
        player.setName("player");
        player.setCurrentCardValue(getCardValue(player));
        int[] playersFirstCards = {getCardValue(player),getCardValue(player)};
        player.setInitialCards(playersFirstCards);
        player.setInitialCount(playersFirstCards[0] + playersFirstCards[1]);
        player.setCurrentCardCount(player.getInitialCount());
        players.add(player);
        checkIfPlayerWinOrBurst(player);
        Player dealer = new Player();
        dealer.setName("dealer");
        dealer.setCurrentCardValue(getCardValue(dealer));
        int[] dealersFirstCards = {getCardValue(dealer),getCardValue(dealer)};
        dealer.setInitialCards(dealersFirstCards);
        dealer.setInitialCount(dealersFirstCards[0] + dealersFirstCards[1]);
        dealer.setCurrentCardCount(dealer.getInitialCount());
        players.add(dealer);
        checkIfPlayerWinOrBurst(dealer);

        return players;

    }

    public void cardTracker(int cardValue, String person){

        if(person.equalsIgnoreCase("player")){
            playerCount =+cardValue;
        }else if ((person.equalsIgnoreCase("dealer"))) {
            dealerCount =+ cardValue;
        }
    }

    public int getCardValue( Player player ){
        String cardStr = ranks[new Random().nextInt(ranks.length)];

        int intergerValue = 0;
        boolean isNumeric =  cardStr.matches("[+-]?\\d*(\\.\\d+)?");

        if(isNumeric){
            intergerValue = Integer.parseInt(cardStr);
        }else{
            if(cardStr.equalsIgnoreCase("Ace")){
                intergerValue = getValueOfAce(player);
            } else
            intergerValue = 10;
        }
        return intergerValue;
    }

    public int getValueOfAce(Player player){
        if(player.getName().equalsIgnoreCase("player")){
          String response = acePlayerPrompt();
          if(response.equalsIgnoreCase("Y")){
              return 1;
          }
          return 11;
        }
        if(player.getName().equalsIgnoreCase("dealer") && isAceElevenForDealer){
            return 11;
        }
        return 0;
    }

    public String acePlayerPrompt(){
        System.out.println("Ace=1 (Y) Ace=11 (N)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        return response;
    }

    public Player dealCardToPlayer(Player player){
        String response = CardDealPrompt(player);

        if(response.equalsIgnoreCase("Y")){
            //get a card vlaue and update
            int newCardVale = getCardValue(player);
            player.setCurrentCardCount(player.getCurrentCardCount() + newCardVale);
            // if player win or burst
            checkIfPlayerWinOrBurst(player);
            return player;
        }else{
            return player;
            //deal card to dealer
        }
    }

    public Player dealCardToDealer(Player dealer){
        if(dealer.getCurrentCardCount()<=17){
            int newCardVale = getCardValue(dealer);
            dealer.setCurrentCardCount(dealer.getCurrentCardCount() + newCardVale);
            checkIfPlayerWinOrBurst(dealer);
            return dealer;
        }else {
            String dealerRresponse = CardDealPrompt(dealer);
            if(dealerRresponse.equalsIgnoreCase("Y")){
                int newCardVale = getCardValue(dealer);
                dealer.setCurrentCardCount(dealer.getCurrentCardCount() + newCardVale);
                checkIfPlayerWinOrBurst(dealer);
                return dealer;
            }
            else
                return dealer;
        }

    }

    public void elevenDealerLogic(Player player){
        if(player.getName().equalsIgnoreCase("dealer") && isAceElevenForDealer ){

        }
    }

    public String CardDealPrompt(Player player){
        System.out.println("Do you want a next card "+player.getName() + "?");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        return response;
    }

    public void checkIfPlayerWinOrBurst(Player person){
        if(person.getCurrentCardCount()==21){
            System.out.println("Game End");
            System.out.println("Person:"+ person.getName() +"Won with card count of:"+ person.getCurrentCardCount() );
        }

        if(person.getCurrentCardCount()>21){
            System.out.println("Game End");
            System.out.println("Game End. Player:"+ person.getName() +"lost to burst, with card count of:"+ person.getCurrentCardCount() );
        }
    }

    public String AceIsElevenForDealerPrompt(){
        System.out.println("Do you want ace to always be 11 for Dealer?");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        return response;
    }

    public void SetAceIsEleven(String response){
        if(response.equalsIgnoreCase("Y")){
            isAceElevenForDealer = true;
            aceValue = 11;
        }else {
            isAceElevenForDealer = false;
            aceValue = 1;
        }
    }



}
