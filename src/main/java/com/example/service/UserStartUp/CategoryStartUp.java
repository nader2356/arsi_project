package com.example.startUpApp;


import com.example.dto.requestDto.CategoryRequest;
import com.example.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryStartUp implements CommandLineRunner {

    private final CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {

        categoryService.addCategory(new CategoryRequest(
                "Genie civil",
                "L'ingénieur génie civil a pour fonction principale de concevoir des infrastructures (routes, bâtiments, ponts, tunnels) et différents types d'aménagement"
        ));
        categoryService.addCategory(new CategoryRequest(
                "Génie logiciel",
                " Le génie logiciel applique les principes et techniques d'ingénierie à la conception de systèmes logiciels, intégrés et à grande échelle. L'ingénieur logiciel doit maîtriser la théorie et les méthodes de l'informatique."
        ));
        categoryService.addCategory(new CategoryRequest(
                "médecine",
                "Ensemble des connaissances scientifiques et des moyens de tous ordres mis en œuvre pour la prévention, la guérison ou le soulagement des maladies, blessures ou infirmités."
        ));

    }
}