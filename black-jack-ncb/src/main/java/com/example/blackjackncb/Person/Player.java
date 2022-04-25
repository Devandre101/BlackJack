package com.example.blackjackncb.Person;

import com.example.blackjackncb.Card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Player {

    @Autowired
    CardService cardService;

    public Integer currentCardCount;
    public String name;
    public Integer currentCardValue;
    public int[] initialCards;
    public int initialCount;


    public int getInitialCount() {
        return initialCount;
    }

    public void setInitialCount(int initialCount) {
        this.initialCount = initialCount;
    }


    public int[] getInitialCards() {
        return initialCards;
    }

    public void setInitialCards(int[] initialCards) {
        this.initialCards = initialCards;
    }

    public Integer getCurrentCardValue() {
        return currentCardValue;
    }

    public void setCurrentCardValue(Integer currentCardValue) {
        this.currentCardValue = currentCardValue;
    }


    public Integer getCurrentCardCount() {
        return currentCardCount;
    }

    public void setCurrentCardCount(Integer currentCardCount) {
        this.currentCardCount = currentCardCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
