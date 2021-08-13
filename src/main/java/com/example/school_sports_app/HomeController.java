package com.example.school_sports_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping("/login")
    public String login () {
        return "login";
    }

    @RequestMapping("/admin")
    public String admin () {
        return "admin";
    }

    @RequestMapping("/logout")
    public String logout () {
        return "redirect:/login?logout=true";
    }

    @RequestMapping("/list")
    public String homePage () {
        return "list";
    }


    @RequestMapping("/")
    public String index (Model model){
        // First lets create a Team
        Team team = new Team();

        team.setName("The Cicidas");
        team.setGenre("Basketball");

        // Now let's create a player
        Player player = new Player();
        player.setFirstName("Maurice");
        player.setLastName("Andrews");
        player.setAge(25);

        // Add the player to an empty list
        Set<Player> players = new HashSet<Player>();
        players.add(player);

        player = new Player();
        player.setFirstName("Darrell");
        player.setLastName("Marcus");
        player.setAge(28);

        // Add the list of players to the team's player list
        //team.setPlayers(players);

        // Save the director to the database
        teamRepository.save(team);


        //Grab all the teams from the database and send them to the template
        model.addAttribute("teams", teamRepository.findAll());
        return "index";
    }
}
