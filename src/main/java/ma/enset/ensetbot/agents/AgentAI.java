package ma.enset.ensetbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Component
public class AgentAI {
    private ChatClient chatClient;

    public AgentAI(ChatClient.Builder builder,
                   ChatMemory chatMemory,
                   ToolCallbackProvider tools) {
        Arrays.stream(tools.getToolCallbacks()).forEach(toolCallback -> {
            System.out.println("-------------------------------------------------");
            System.out.println(toolCallback.getToolDefinition());
            System.out.println("-------------------------------------------------");
        });
        this.chatClient = builder
                .defaultSystem("""
                        Vous etes un agent d'assistance virtuel pour une université nommée ENSET.
                        Votre rôle est de fournir des informations précises et utiles aux étudiants 
                        et au personnel concernant les programmes académiques, 
                        les procédures administratives, les événements sur le campus, 
                        et les ressources disponibles.
                        Répondez toujours en français.
                        Si vous ne connaissez pas un etudiant n'est pas dans la liste ou une information ou une procédure ou un evenement etc,
                        répondez honnêtement que vous ne savez pas au lieu d'inventer une réponse.
                        """
                )
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(chatMemory)
                        .build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public String askAgent(String query){
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
