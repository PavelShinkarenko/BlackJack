package controllers;

import domain.Card;
import domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.GameService;
import services.PlayerService;

import java.util.List;

@Controller
public class GameController {
    private GameService gameService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/player/{id}/bet", method = RequestMethod.GET)
    public @ResponseBody List<Card> bet(){
        return gameService.distribution();
    }

    @RequestMapping(value = "/player/*/hit", method = RequestMethod.GET)
    public @ResponseBody List<Card> hit(){
        return gameService.hit();
    }

    @RequestMapping(value = "/player/*/stand", method = RequestMethod.GET)
    public @ResponseBody List<Card> stand(){
    return gameService.stand();
    }

    @RequestMapping(value = "/player/*/checkAfterBet", method = RequestMethod.GET)
    public @ResponseBody double checkAfterBet(){
       return gameService.checkAfterBet();
    }

    @RequestMapping(value = "/player/*/checkAfterHit", method = RequestMethod.GET)
    public @ResponseBody int checkAfterHit(){
        return gameService.checkAfterHit();
    }

    @RequestMapping(value = "/player/*/checkAfterStand", method = RequestMethod.GET)
    public @ResponseBody int checkAfterStand(){
        return gameService.checkAfterStand();
    }

    @RequestMapping(value = "/player/*/getDealerCards", method = RequestMethod.GET)
    public @ResponseBody List<Card> getDealerCards(){
        return gameService.getDealerCards();
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public void exit(){
        gameService.cleanHands();
    }

}


