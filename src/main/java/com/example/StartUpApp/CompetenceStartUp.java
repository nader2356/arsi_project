package com.example.StartUpApp;


import com.example.dto.requestDto.CompetenceRequest;
import com.example.service.CompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@DependsOn("categoryStartUp")
public class CompetenceStartUp implements CommandLineRunner {

    private final CompetenceService competenceService;
    
    @Override
    public void run(String... args) throws Exception {
        competenceService.addCompetence(new CompetenceRequest(
                1L,
                "azeazeaze",
                "azeazeaze aze aze azeaze azeaz eaez azeaeae"
        ));
        competenceService.addCompetence(new CompetenceRequest(
                1L,
                "aze",
                " Le génie logiciel applique les principes et techniques d'ingénierie à la conception de systèmes logiciels, intégrés et à grande échelle. L'ingénieur logiciel doit maîtriser la théorie et les méthodes de l'informatique."
        ));
        competenceService.addCompetence(new CompetenceRequest(
                1L,
                "sdqfsdf",
                "azedsqdqsdqsdqsdqsdqsdqsdqsdqsdqdqd"
        ));


    }
}