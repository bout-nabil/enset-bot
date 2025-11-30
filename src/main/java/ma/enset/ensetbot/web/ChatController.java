package ma.enset.ensetbot.web;

import ma.enset.ensetbot.agents.AgentAI;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;

@RestController
public class ChatController {
    private AgentAI agentAI;

    public ChatController(AgentAI agentAI) {
        this.agentAI = agentAI;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(@RequestParam(name = "query") String query){
        return agentAI.askAgent(query);
    }
}
