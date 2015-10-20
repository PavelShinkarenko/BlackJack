package controllers;


import domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.PlayerService;

@Controller
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public @ResponseBody String entry(@RequestParam String name){
        int id = playerService.addPlayerAndGetId(name);
        return "player/" + id;
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public String gameTable(){
        return "table";
    }

    @RequestMapping(value = "/player/{id}/getPlayer", method = RequestMethod.GET)
    public @ResponseBody
    Player getPlayer(@PathVariable Integer id){
        return playerService.getPlayerById(id);
    }

    @RequestMapping(value = "/player/{id}/updateCash", method = RequestMethod.POST)
    public @ResponseBody double addCash(@RequestParam("cash") String cash, @PathVariable Integer id){
        Player player = playerService.updatePlayerCashById(Double.valueOf(cash), id);
        return  player.getCash();
    }

}
