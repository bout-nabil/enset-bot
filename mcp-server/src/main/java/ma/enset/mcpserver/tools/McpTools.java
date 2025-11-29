package ma.enset.mcpserver.tools;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class McpTools {
    @McpTool(name = "getEtudiant",
            description = "Permet de récupérer les informations d'un étudiant donné son nom. Retourne le nom, la note moyenne et l'année d'inscription.")
    public Etudiant getEtudiant(@McpArg(description = "nom d'etudiant") String name){
        return new Etudiant(name, Math.random()*20, 2024);
    }

    @McpTool(name = "listEtudiants",
            description = "Permet de récupérer la liste des étudiants inscrits avec leur nom, note moyenne et année d'inscription.")
    public List<Etudiant> listEtudiants(){
        return List.of(
                new Etudiant("Nabil", 15.5, 2022),
                new Etudiant("Tawfiq", 12.0, 2023),
                new Etudiant("Ayoub", 18.0, 2021)
        );
    }
}
record Etudiant(String name, double notes, int year) {}
