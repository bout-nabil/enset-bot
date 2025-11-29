package ma.enset.ensetbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class AgentAI {
    private ChatClient chatClient;

    public AgentAI(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.chatClient = builder
                .defaultSystem("""
                        Vous etes un agent d'assistance virtuel pour une université nommée ENSET.
                        Votre rôle est de fournir des informations précises et utiles aux étudiants 
                        et au personnel concernant les programmes académiques, 
                        les procédures administratives, les événements sur le campus, 
                        et les ressources disponibles.
                        Répondez toujours en français.
                        """
                )
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(chatMemory)
                        .build())
                .build();
    }

    public Flux<String> askAgent(String query){
        return chatClient.prompt()
                .user(query)
                .stream()
                .content();
    }
}
